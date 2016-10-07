package the_fireplace.fluidity.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.entity.tile.TileEntityFluidityIronChest;
import the_fireplace.fluidity.enums.FluidityIronChestType;
import the_fireplace.fluidity.client.particle.ParticleIronChest;

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
		this.setRegistryName("fluidity_iron_chest");
	}

	@Override
	public int getLightValue(IBlockState state){
		if(state.getValue(VARIANT_PROP).equals(FluidityIronChestType.STARSTEEL)){
			return (int)(15.0F * 0.5F);
		}else{
			return this.lightValue;
		}
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
			InventoryHelper.dropInventoryItems(world, pos, tileentitychest);
		}
		super.breakBlock(world, pos, blockState);
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

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager)
	{
		BlockPos pos = target.getBlockPos();
		Random rand = new Random();
		EnumFacing side = target.sideHit;
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		float f = 0.1F;
		AxisAlignedBB axisalignedbb = state.getBoundingBox(worldObj, pos);
		double d0 = (double)i + rand.nextDouble() * (axisalignedbb.maxX - axisalignedbb.minX - (double)(f * 2.0F)) + (double)f + axisalignedbb.minX;
		double d1 = (double)j + rand.nextDouble() * (axisalignedbb.maxY - axisalignedbb.minY - (double)(f * 2.0F)) + (double)f + axisalignedbb.minY;
		double d2 = (double)k + rand.nextDouble() * (axisalignedbb.maxZ - axisalignedbb.minZ - (double)(f * 2.0F)) + (double)f + axisalignedbb.minZ;

		if (side == EnumFacing.DOWN)
		{
			d1 = (double)j + axisalignedbb.minY - (double)f;
		}

		if (side == EnumFacing.UP)
		{
			d1 = (double)j + axisalignedbb.maxY + (double)f;
		}

		if (side == EnumFacing.NORTH)
		{
			d2 = (double)k + axisalignedbb.minZ - (double)f;
		}

		if (side == EnumFacing.SOUTH)
		{
			d2 = (double)k + axisalignedbb.maxZ + (double)f;
		}

		if (side == EnumFacing.WEST)
		{
			d0 = (double)i + axisalignedbb.minX - (double)f;
		}

		if (side == EnumFacing.EAST)
		{
			d0 = (double)i + axisalignedbb.maxX + (double)f;
		}

		manager.addEffect((new ParticleIronChest(worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, state)).setBlockPos(pos).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager)
	{
		IBlockState state = world.getBlockState(pos).getActualState(world, pos);
		int i = 4;

		for (int j = 0; j < i; ++j)
		{
			for (int k = 0; k < i; ++k)
			{
				for (int l = 0; l < i; ++l)
				{
					double d0 = (double)pos.getX() + ((double)j + 0.5D) / (double)i;
					double d1 = (double)pos.getY() + ((double)k + 0.5D) / (double)i;
					double d2 = (double)pos.getZ() + ((double)l + 0.5D) / (double)i;
					manager.addEffect((new ParticleIronChest(world, d0, d1, d2, d0 - (double)pos.getX() - 0.5D, d1 - (double)pos.getY() - 0.5D, d2 - (double)pos.getZ() - 0.5D, state)).setBlockPos(pos));
				}
			}
		}
		return true;
	}
}