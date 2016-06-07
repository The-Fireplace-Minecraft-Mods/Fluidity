package the_fireplace.fluidity.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.entity.tile.TileEntityFluidityIronChest;
import the_fireplace.fluidity.enums.FluidityIronChestType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockFluidityIronChest extends BlockContainer
{
	public static final PropertyEnum<FluidityIronChestType> VARIANT_PROP = PropertyEnum.create("variant", FluidityIronChestType.class);
	protected static final AxisAlignedBB IRON_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

	public BlockFluidityIronChest()
	{
		super(Material.IRON);

		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, FluidityIronChestType.BRONZE));

		this.setHardness(3.0F);
		this.setUnlocalizedName("iron_chest");
		this.setCreativeTab(Fluidity.tabFluidity);
		this.setRegistryName("fluidity_iron_chest");
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return IRON_CHEST_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState blockState, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing direction, float p_180639_6_, float p_180639_7_, float p_180639_8_)
	{
		TileEntity te = world.getTileEntity(pos);

		if (te == null || !(te instanceof TileEntityFluidityIronChest))
			return true;

		if (world.isSideSolid(pos.add(0, 1, 0), EnumFacing.DOWN))
			return true;

		if (world.isRemote)
			return true;

		player.openGui(Fluidity.instance, ((TileEntityFluidityIronChest) te).getType().ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return FluidityIronChestType.makeEntity(metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		for (FluidityIronChestType type : FluidityIronChestType.values())
			if (type.isValidForCreativeMode())
				list.add(new ItemStack(itemIn, 1, type.ordinal()));
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT_PROP, FluidityIronChestType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState blockState)
	{
		return blockState.getValue(VARIANT_PROP).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, VARIANT_PROP);
	}

	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		ArrayList<ItemStack> items = Lists.newArrayList();
		ItemStack stack = new ItemStack(this, 1, getMetaFromState(state));
		items.add(stack);
		return items;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState blockState)
	{
		super.onBlockAdded(world, pos, blockState);
		world.notifyBlockUpdate(pos, blockState, blockState, 3);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState blockState, EntityLivingBase entityliving, ItemStack itemStack)
	{
		TileEntity te = world.getTileEntity(pos);
		if(te != null && te instanceof TileEntityFluidityIronChest) {
			TileEntityFluidityIronChest teic = (TileEntityFluidityIronChest)te;
			teic.wasPlaced(entityliving, itemStack);
			teic.setFacing(entityliving.getHorizontalFacing().getOpposite());
			world.notifyBlockUpdate(pos, blockState, blockState, 3);
		}
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return state.getValue(VARIANT_PROP).ordinal();
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockState)
	{
		TileEntityFluidityIronChest tileentitychest = (TileEntityFluidityIronChest) world.getTileEntity(pos);
		if (tileentitychest != null)
		{
			tileentitychest.removeAdornments();
			dropContent(0, tileentitychest, world, tileentitychest.getPos());
		}
		super.breakBlock(world, pos, blockState);
	}

	public void dropContent(int newSize, IInventory chest, World world, BlockPos pos)
	{
		Random random = world.rand;

		for (int l = newSize; l < chest.getSizeInventory(); l++)
		{
			ItemStack itemstack = chest.getStackInSlot(l);
			if (itemstack == null)
				continue;
			float f = random.nextFloat() * 0.8F + 0.1F;
			float f1 = random.nextFloat() * 0.8F + 0.1F;
			float f2 = random.nextFloat() * 0.8F + 0.1F;
			while (itemstack.stackSize > 0)
			{
				int i1 = random.nextInt(21) + 10;
				if (i1 > itemstack.stackSize)
					i1 = itemstack.stackSize;
				itemstack.stackSize -= i1;
				EntityItem entityitem = new EntityItem(world, pos.getX() + f, (float) pos.getY() + (newSize > 0 ? 1 : 0) + f1, pos.getZ() + f2, new ItemStack(itemstack.getItem(), i1, itemstack.getMetadata()));
				float f3 = 0.05F;
				entityitem.motionX = (float) random.nextGaussian() * f3;
				entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float) random.nextGaussian() * f3;
				if (itemstack.hasTagCompound())
					entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
				world.spawnEntityInWorld(entityitem);
			}
		}
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion)
	{
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileEntityFluidityIronChest)
		{
			TileEntityFluidityIronChest teic = (TileEntityFluidityIronChest) te;
			if (teic.getType().isExplosionResistant())
				return 10000F;
		}
		return super.getExplosionResistance(world, pos, exploder, explosion);
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
	{
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof IInventory)
			return Container.calcRedstoneFromInventory((IInventory) te);
		return 0;
	}

	private static final EnumFacing[] validRotationAxes = new EnumFacing[] { EnumFacing.UP, EnumFacing.DOWN };

	@Override
	public EnumFacing[] getValidRotations(World worldObj, BlockPos pos)
	{
		return validRotationAxes;
	}

	@Override
	public boolean rotateBlock(World worldObj, BlockPos pos, EnumFacing axis)
	{
		if (worldObj.isRemote)
			return false;
		if (axis == EnumFacing.UP || axis == EnumFacing.DOWN)
		{
			TileEntity tileEntity = worldObj.getTileEntity(pos);
			if (tileEntity instanceof TileEntityFluidityIronChest)
			{
				TileEntityFluidityIronChest icte = (TileEntityFluidityIronChest) tileEntity;
				icte.rotateAround();
			}
			return true;
		}
		return false;
	}
}