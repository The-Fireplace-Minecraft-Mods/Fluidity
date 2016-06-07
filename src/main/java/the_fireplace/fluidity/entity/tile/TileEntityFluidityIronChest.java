package the_fireplace.fluidity.entity.tile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.util.Constants;
import the_fireplace.fluidity.compat.FluidityIronChests;
import the_fireplace.fluidity.container.ContainerFluidityIronChest;
import the_fireplace.fluidity.enums.FluidityIronChestType;

import java.util.List;

public class TileEntityFluidityIronChest extends TileEntityLockable implements ITickable, IInventory
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
		inventoryTouched = true;
		return chestContents[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if (chestContents[i] != null)
		{
			if (chestContents[i].stackSize <= j)
			{
				ItemStack itemstack = chestContents[i];
				chestContents[i] = null;
				markDirty();
				return itemstack;
			}
			ItemStack itemstack1 = chestContents[i].splitStack(j);
			if (chestContents[i].stackSize == 0)
			{
				chestContents[i] = null;
			}
			markDirty();
			return itemstack1;
		} else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
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
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);

		NBTTagList nbttaglist = nbttagcompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		this.chestContents = new ItemStack[getSizeInventory()];

		if (nbttagcompound.hasKey("CustomName", Constants.NBT.TAG_STRING))
		{
			this.customName = nbttagcompound.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 0xff;
			if (j >= 0 && j < chestContents.length)
			{
				chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		facing = EnumFacing.VALUES[nbttagcompound.getByte("facing")];
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < chestContents.length; i++)
		{
			if (chestContents[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				chestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbttagcompound.setTag("Items", nbttaglist);
		nbttagcompound.setByte("facing", (byte)this.facing.ordinal());

		if (this.hasCustomName())
		{
			nbttagcompound.setString("CustomName", this.customName);
		}
		return nbttagcompound;
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
			worldObj.addBlockEvent(pos, FluidityIronChests.fluidityChest, 3, this.numUsingPlayers << 3 & 248 | this.facing.ordinal() & 7);
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
	public boolean receiveClientEvent(int i, int j)
	{
		if (i == 1)
		{
			numUsingPlayers = j;
		} else if (i == 2)
		{
			facing = EnumFacing.VALUES[j];
		} else if (i == 3)
		{
			facing = EnumFacing.VALUES[j & 7];
			numUsingPlayers = (j & 248) >> 3;
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
		worldObj.addBlockEvent(pos, FluidityIronChests.fluidityChest, 1, numUsingPlayers);
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		if (worldObj == null)
		{
			return;
		}
		numUsingPlayers--;
		worldObj.addBlockEvent(pos, FluidityIronChests.fluidityChest, 1, numUsingPlayers);
	}

	public void setFacing(EnumFacing facing2)
	{
		this.facing = facing2;
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setByte("facing", (byte)this.facing.ordinal());

		return new SPacketUpdateTileEntity(this.pos, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		if (pkt.getTileEntityType() == 0)
		{
			NBTTagCompound nbt = pkt.getNbtCompound();
			type = FluidityIronChestType.values()[nbt.getInteger("type")];
			facing = EnumFacing.VALUES[nbt.getByte("facing")];
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int par1)
	{
		if (this.chestContents[par1] != null)
		{
			ItemStack var2 = this.chestContents[par1];
			this.chestContents[par1] = null;
			return var2;
		} else
		{
			return null;
		}
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return type.acceptsStack(itemstack);
	}

	public void rotateAround()
	{
		this.setFacing(this.facing.rotateY());
		worldObj.addBlockEvent(pos, FluidityIronChests.fluidityChest, 2, facing.ordinal());
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
		for (int i = 0; i < this.chestContents.length; ++i)
		{
			this.chestContents[i] = null;
		}
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
	{
		return null;
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