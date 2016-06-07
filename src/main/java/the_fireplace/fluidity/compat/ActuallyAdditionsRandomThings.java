package the_fireplace.fluidity.compat;

import de.ellpeck.actuallyadditions.mod.blocks.InitBlocks;
import de.ellpeck.actuallyadditions.mod.blocks.metalists.TheMiscBlocks;
import de.ellpeck.actuallyadditions.mod.items.InitItems;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheMiscItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import the_fireplace.fluidity.blocks.BlockBlackQuartzGlass;
import the_fireplace.fluidity.blocks.BlockBlackQuartzLamp;
import the_fireplace.fluidity.tools.Registry;

/**
 * @author The_Fireplace
 */
public class ActuallyAdditionsRandomThings implements IModCompat {

    public static final Block black_quartz_glass = new BlockBlackQuartzGlass();
    public static final Block black_quartz_lamp = new BlockBlackQuartzLamp();

    @Override
    public void preInit() {
        Registry.register(black_quartz_glass);
        Registry.register(black_quartz_lamp);
    }

    @Override
    public void init() {
        ItemStack black_quartz = new ItemStack(InitItems.itemMisc, 1, TheMiscItems.QUARTZ.ordinal());
        ItemStack black_quartz_block = new ItemStack(InitBlocks.blockMisc, 1, TheMiscBlocks.QUARTZ.ordinal());
        Registry.addRecipe(new ItemStack(black_quartz_lamp), " q ", "qlq", " q ", 'q', black_quartz, 'l', Blocks.REDSTONE_LAMP);
        Registry.addRecipe(new ItemStack(black_quartz_glass, 8), "ggg", "gqg", "ggg", 'g', "blockGlass", 'q', black_quartz_block);
    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {
        Registry.registerRender(black_quartz_glass);
        Registry.registerRender(black_quartz_lamp);
    }
}
