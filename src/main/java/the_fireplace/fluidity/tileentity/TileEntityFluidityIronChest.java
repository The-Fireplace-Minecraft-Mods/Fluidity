package the_fireplace.fluidity.tileentity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.util.Constants;
import the_fireplace.fluidity.compat.FluidityIronChests;
import the_fireplace.fluidity.container.ContainerFluidityIronChest;
import the_fireplace.fluidity.enums.FluidityIronChestType;

import javax.annotation.Nullable;
import java.util.List;

public class TileEntityFluidityIronChest extends TileEntityLockableLoot implements ITickable, IInventory
{
	private int ticksSinceSync = -1;
	public float prevLidAngle;
	public float lidAngle;
	private int numUsingPlayers;
	private FluidityIronChestType type;
	public ItemStack[] chestContents;
	private EnumFacing facing;
	private boolean inventoryTouched;
	private String customName;

	public static final int UPDATE_PLAYERNUMBERS = 1;
	public static final int UPDATE_FACING = 2;

	public TileEntityFluidityIronChest()
	{
		this(FluidityIronChestType.BRONZE);
	}

	protected TileEntityFluidityIronChest(FluidityIronChestType type)
	{
		this.ticksSinceSync = -1;
		this.type = type;
		this.chestContents = new ItemStack[getSizeInventory()];
		this.facing = EnumFacing.NORTH;
	}

	public void setContents(ItemStack[] contents)
	{
		chestContents = new ItemStack[getSizeInventory()];
		for (int i = 0; i < contents.length; i++)
		{
			if (i < chestContents.length)
			{
				chestContents[i] = contents[i];
			}
		}
		inventoryTouched = true;
	}

	@Override
	public int getSizeInventory()
	{
		return type.size;
	}

	public EnumFacing getFacing()
	{
		return this.facing;
	}

	public FluidityIronChestType getType()
	{
		return type;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		this.fillWithLoot(null);

		inventoryTouched = true;
		return chestContents[i];
	}

	@Override
	@Nullable
	public ItemStack decrStackSize(int i, int j)
	{
		this.fillWithLoot(null);

		ItemStack itemstack = ItemStackHelper.getAndSplit(this.chestContents, i, j);

		if (itemstack != null)
		{
			this.markDirty();
		}

		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		this.fillWithLoot(null);

		chestContents[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.customName : type.name();
	}

	@Override
	public boolean hasCustomName()
	{
		return this.customName != null && this.customName.length() > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		this.chestContents = new ItemStack[this.getSizeInventory()];

		if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING))
		{
			this.customName = compound.getString("CustomName");
		}

		if (!this.checkLootAndRead(compound))
		{
			NBTTagList itemList = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);

			for (int itemNumber = 0; itemNumber < itemList.tagCount(); ++itemNumber)
			{
				NBTTagCompound item = itemList.getCompoundTagAt(itemNumber);

				int slot = item.getByte("Slot") & 255;

				if (slot >= 0 && slot < this.chestContents.length)
				{
					this.chestContents[slot] = ItemStack.loadItemStackFromNBT(item);
				}
			}
		}

		this.facing = EnumFacing.VALUES[compound.getByte("facing")];
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		if (!this.checkLootAndWrite(compound))
		{
			NBTTagList itemList = new NBTTagList();

			for (int slot = 0; slot < this.chestContents.length; ++slot)
			{
				if (this.chestContents[slot] != null)
				{
					NBTTagCompound tag = new NBTTagCompound();

					tag.setByte("Slot", (byte) slot);

					this.chestContents[slot].writeToNBT(tag);

					itemList.appendTag(tag);
				}
			}

			compound.setTag("Items", itemList);
		}

		compound.setByte("facing", (byte) this.facing.ordinal());

		if (this.hasCustomName())
		{
			compound.setString("CustomName", this.customName);
		}

		return compound;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		if (worldObj == null) {
			return true;
		}
		return worldObj.getTileEntity(pos) == this && entityplayer.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64D;
	}

	@Override
	public void update()
	{
		// Resynchronize clients with the server state
		if (worldObj != null && !this.worldObj.isRemote && this.numUsingPlayers != 0 && (this.ticksSinceSync + pos.getX() + pos.getY() + pos.getZ()) % 200 == 0)
		{
			this.numUsingPlayers = 0;
			float var1 = 5.0F;
			@SuppressWarnings("unchecked")
			List<EntityPlayer> var2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX() - var1, pos.getY() - var1, pos.getZ() - var1, pos.getX() + 1 + var1, pos.getY() + 1 + var1, pos.getZ() + 1 + var1));

			for (EntityPlayer var4 : var2)
			{
				if (var4.openContainer instanceof ContainerFluidityIronChest)
				{
					++this.numUsingPlayers;
				}
			}
		}

		if (worldObj != null && !worldObj.isRemote && ticksSinceSync < 0)
		{
			worldObj.addBlockEvent(pos, FluidityIronChests.fluidityChest, UPDATE_PLAYERNUMBERS, numUsingPlayers);
			worldObj.addBlockEvent(pos, FluidityIronChests.fluidityChest, UPDATE_FACING, facing.ordinal());
		}
		if (!worldObj.isRemote && inventoryTouched)
		{
			inventoryTouched = false;
		}

		this.ticksSinceSync++;
		prevLidAngle = lidAngle;
		float f = 0.1F;
		if (numUsingPlayers > 0 && lidAngle == 0.0F)
		{
			double d = pos.getX() + 0.5D;
			double d1 = pos.getZ() + 0.5D;
			worldObj.playSound(null, d, pos.getY() + 0.5D, d1, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}
		if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F)
		{
			float f1 = lidAngle;
			if (numUsingPlayers > 0)
			{
				lidAngle += f;
			} else
			{
				lidAngle -= f;
			}
			if (lidAngle > 1.0F)
			{
				lidAngle = 1.0F;
			}
			float f2 = 0.5F;
			if (lidAngle < f2 && f1 >= f2)
			{
				double d2 = pos.getX() + 0.5D;
				double d3 = pos.getZ() + 0.5D;
				worldObj.playSound(null, d2, pos.getY() + 0.5D, d3, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}
			if (lidAngle < 0.0F)
			{
				lidAngle = 0.0F;
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int eventId, int eventArgs)
	{
		if (eventId == UPDATE_PLAYERNUMBERS){
			numUsingPlayers = eventArgs;
		} else if (eventId == UPDATE_FACING){
			facing = EnumFacing.VALUES[eventArgs];
		}
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
		if (worldObj == null)
		{
			return;
		}
		numUsingPlayers++;
		worldObj.addBlockEvent(pos, FluidityIronChests.fluidityChest, UPDATE_PLAYERNUMBERS, numUsingPlayers);
		worldObj.notifyNeighborsOfStateChange(this.pos, FluidityIronChests.fluidityChest);
		worldObj.notifyNeighborsOfStateChange(this.pos.down(), FluidityIronChests.fluidityChest);
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		if (worldObj == null)
		{
			return;
		}
		numUsingPlayers--;
		worldObj.addBlockEvent(pos, FluidityIronChests.fluidityChest, UPDATE_PLAYERNUMBERS, numUsingPlayers);
		worldObj.notifyNeighborsOfStateChange(this.pos, FluidityIronChests.fluidityChest);
		worldObj.notifyNeighborsOfStateChange(this.pos.down(), FluidityIronChests.fluidityChest);
	}

	public void setFacing(EnumFacing newFacing)
	{
		this.facing = newFacing;
		if(this.hasWorldObj())
			worldObj.addBlockEvent(pos, FluidityIronChests.fluidityChest, UPDATE_FACING, facing.ordinal());
	}

	@Override
	public NBTTagCompound getUpdateTag(){
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, type.ordinal(), getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	@Nullable
	public ItemStack removeStackFromSlot(int index)
	{
		this.fillWithLoot(null);

		return ItemStackHelper.getAndRemove(this.chestContents, index);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return type.acceptsStack(itemstack);
	}

	public void rotateAround()
	{
		this.setFacing(this.facing.rotateY());
	}

	public void wasPlaced(EntityLivingBase entityliving, ItemStack itemStack)
	{
	}

	public void removeAdornments()
	{
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		this.fillWithLoot(null);

		for (int i = 0; i < this.chestContents.length; ++i)
		{
			this.chestContents[i] = null;
		}
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
	{
		this.fillWithLoot(null);

		return new ContainerFluidityIronChest(playerInventory, this, type, 0, 0);//TODO: Replace 0,0 with someting else if needed
	}

	@Override
	public String getGuiID()
	{
		return "Fluidity:" + type.name();
	}

	@Override
	public boolean canRenderBreaking()
	{
		return true;
	}
}