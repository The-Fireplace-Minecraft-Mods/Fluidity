package the_fireplace.fluidity.items;

import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.TileEntityIronChest;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.compat.FluidityIronChests;
import the_fireplace.fluidity.entity.tile.TileEntityFluidityIronChest;
import the_fireplace.fluidity.enums.FluidityChestChangerType;
import the_fireplace.fluidity.enums.FluidityIronChestChangerType;
import the_fireplace.fluidity.enums.FluidityIronChestType;
import the_fireplace.fluidity.enums.IronFluidityChestChangerType;

public class FluidityItemChestChanger extends Item
{
	/**
	 * The changer type to use. 0 is Fluidity to Fluidity, 1 is Fluidity to Iron, 2 is Iron to Fluidity
	 */
	private byte useType;
	private FluidityChestChangerType type;
	private FluidityIronChestChangerType type2;
	private IronFluidityChestChangerType type3;

	public FluidityItemChestChanger(FluidityChestChangerType type)
	{
		this.type = type;
		this.useType = 0;

		this.setMaxStackSize(1);
		this.setUnlocalizedName("fluidity:" + type.name());
		this.setCreativeTab(Fluidity.tabFluidity);
	}

	public FluidityItemChestChanger(FluidityIronChestChangerType type)
	{
		this.type2 = type;
		this.useType = 1;

		this.setMaxStackSize(1);
		this.setUnlocalizedName("fluidity:" + type.name());
		this.setCreativeTab(Fluidity.tabFluidity);
	}

	public FluidityItemChestChanger(IronFluidityChestChangerType type)
	{
		this.type3 = type;
		this.useType = 2;

		this.setMaxStackSize(1);
		this.setUnlocalizedName("fluidity:" + type.name());
		this.setCreativeTab(Fluidity.tabFluidity);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		return String.format(StatCollector.translateToLocal("fluidity.upgrade"), StatCollector.translateToLocal("fluidity."+this.getSourceName().toLowerCase()), StatCollector.translateToLocal("fluidity."+this.getTargetName().toLowerCase()));
	}

	@Override
	public boolean onItemUseFirst (ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
			return false;
		switch(useType){
		case 0:{
			if(world.getBlockState(pos) != FluidityIronChests.fluidityChest.getStateFromMeta(FluidityIronChestType.valueOf(type.sourcename.toUpperCase()).ordinal())){
				return false;
			}
			TileEntity te = world.getTileEntity(pos);
			TileEntityFluidityIronChest newchest = new TileEntityFluidityIronChest();
			ItemStack[] chestContents = new ItemStack[27];
			if (te != null)
			{
				if (te instanceof TileEntityFluidityIronChest)
				{
					chestContents = ((TileEntityFluidityIronChest) te).chestContents;
					newchest = FluidityIronChestType.makeEntity(this.getTargetChestOrdinal(this.type.ordinal()));
					if (newchest == null)
						return false;
				}
			}

			te.updateContainingBlockInfo();
			if (te instanceof TileEntityChest)
				((TileEntityChest) te).checkForAdjacentChests();

			world.removeTileEntity(pos);
			world.setBlockToAir(pos);

			world.setTileEntity(pos, newchest);
			world.setBlockState(pos, FluidityIronChests.fluidityChest.getStateFromMeta(newchest.getType().ordinal()), 3);

			world.markBlockForUpdate(pos);

			TileEntity te2 = world.getTileEntity(pos);
			if (te2 instanceof TileEntityFluidityIronChest)
			{
				((TileEntityFluidityIronChest) te2).setContents(chestContents);
			}

			stack.stackSize = player.capabilities.isCreativeMode ? stack.stackSize : stack.stackSize - 1;
			return true;}
		case 1:{
			if(world.getBlockState(pos) != FluidityIronChests.fluidityChest.getStateFromMeta(FluidityIronChestType.valueOf(type2.sourcename.toUpperCase()).ordinal())){
				return false;
			}
			TileEntity te = world.getTileEntity(pos);
			TileEntityIronChest newchest = new TileEntityIronChest();
			ItemStack[] chestContents = new ItemStack[27];
			if (te != null)
			{
				if (te instanceof TileEntityFluidityIronChest)
				{
					chestContents = ((TileEntityFluidityIronChest) te).chestContents;
					newchest = IronChestType.makeEntity(this.getTargetChestOrdinal(this.type2.ordinal()));
					if (newchest == null)
						return false;
				}
			}

			te.updateContainingBlockInfo();
			if (te instanceof TileEntityChest)
				((TileEntityChest) te).checkForAdjacentChests();

			world.removeTileEntity(pos);
			world.setBlockToAir(pos);

			world.setTileEntity(pos, newchest);
			world.setBlockState(pos, IronChest.ironChestBlock.getStateFromMeta(newchest.getType().ordinal()), 3);

			world.markBlockForUpdate(pos);

			TileEntity te2 = world.getTileEntity(pos);
			if (te2 instanceof TileEntityIronChest)
			{
				((TileEntityIronChest) te2).setContents(chestContents);
			}

			stack.stackSize = player.capabilities.isCreativeMode ? stack.stackSize : stack.stackSize - 1;
			return true;}
		case 2:{
			if(type3.getSource() == IronChestType.WOOD){
				if(!(world.getBlockState(pos).getBlock() instanceof BlockChest)){
					return false;
				}
			}else{
				if(world.getBlockState(pos) != IronChest.ironChestBlock.getStateFromMeta(IronChestType.valueOf(type3.sourcename.toUpperCase()).ordinal())){
					return false;
				}
			}
			TileEntity te = world.getTileEntity(pos);
			TileEntityFluidityIronChest newchest = new TileEntityFluidityIronChest();
			ItemStack[] chestContents = new ItemStack[27];
			if (te != null)
			{
				if (te instanceof TileEntityIronChest)
				{
					chestContents = ((TileEntityIronChest) te).chestContents;
					newchest = FluidityIronChestType.makeEntity(this.getTargetChestOrdinal(this.type3.ordinal()));
					if (newchest == null)
						return false;
				}else if (te instanceof TileEntityChest){
					if (((TileEntityChest) te).numPlayersUsing > 0)
						return false;
					if (!type3.canUpgrade(IronChestType.WOOD))
						return false;
					chestContents = new ItemStack[((TileEntityChest) te).getSizeInventory()];
					for (int i = 0; i < chestContents.length; i++)
						chestContents[i] = ((TileEntityChest) te).getStackInSlot(i);
					newchest = FluidityIronChestType.makeEntity(this.getTargetChestOrdinal(this.type3.ordinal()));
				}
			}

			te.updateContainingBlockInfo();
			if (te instanceof TileEntityChest)
				((TileEntityChest) te).checkForAdjacentChests();

			world.removeTileEntity(pos);
			world.setBlockToAir(pos);

			world.setTileEntity(pos, newchest);
			world.setBlockState(pos, FluidityIronChests.fluidityChest.getStateFromMeta(newchest.getType().ordinal()), 3);

			world.markBlockForUpdate(pos);

			TileEntity te2 = world.getTileEntity(pos);
			if (te2 instanceof TileEntityFluidityIronChest)
			{
				((TileEntityFluidityIronChest) te2).setContents(chestContents);
			}

			stack.stackSize = player.capabilities.isCreativeMode ? stack.stackSize : stack.stackSize - 1;
			return true;}
		}
		return false;
	}

	public int getTargetChestOrdinal(int sourceOrdinal)
	{
		switch(useType){
		case 0:
			return type.getTarget();
		case 1:
			return type2.getTarget();
		case 2:
			return type3.getTarget();
		}
		return 0;
	}
	public String getSourceName(){
		switch(useType){
		case 0:
			return type.sourcename;
		case 1:
			return type2.sourcename;
		case 2:
			return type3.sourcename;
		}
		return null;
	}
	public String getTargetName(){
		switch(useType){
		case 0:
			return type.targetname;
		case 1:
			return type2.targetname;
		case 2:
			return type3.targetname;
		}
		return null;
	}
}
