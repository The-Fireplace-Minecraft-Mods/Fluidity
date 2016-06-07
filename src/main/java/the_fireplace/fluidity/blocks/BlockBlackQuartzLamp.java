package the_fireplace.fluidity.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Random;

/**
 * @author The_Fireplace
 */
public class BlockBlackQuartzLamp extends Block {
    public BlockBlackQuartzLamp() {
        super(Material.GROUND);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(0.3F);
        setUnlocalizedName("black_quartz_lamp");
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.getBlock() != this?state.getBlock().getLightValue(state, world, pos):(FMLCommonHandler.instance().getEffectiveSide().isServer()?15:0);
    }

    @Override
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        if(worldIn.isRemote && worldIn.getLight(pos) == 15) {
            worldIn.checkLightFor(EnumSkyBlock.BLOCK, pos);
        }
    }
}
