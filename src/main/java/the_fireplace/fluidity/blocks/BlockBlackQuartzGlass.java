package the_fireplace.fluidity.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * @author The_Fireplace
 */
public class BlockBlackQuartzGlass extends Block {
    public BlockBlackQuartzGlass() {
        super(Material.GROUND);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(0.3F);
        this.setUnlocalizedName("black_quartz_glass");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isVisuallyOpaque() {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = worldIn.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        return state != iblockstate?true:(block == this?false:false);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
        AxisAlignedBB blockBox = state.getCollisionBoundingBox(worldIn, pos);
        AxisAlignedBB axisalignedbb = blockBox.offset(pos);
        if(entityBox.intersectsWith(axisalignedbb) && entityIn != null && !(entityIn instanceof EntityPlayer)) {
            collidingBoxes.add(axisalignedbb);
        }

    }
}
