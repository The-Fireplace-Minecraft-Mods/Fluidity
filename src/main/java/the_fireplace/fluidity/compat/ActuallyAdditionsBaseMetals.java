package the_fireplace.fluidity.compat;

import cyano.basemetals.registry.CrusherRecipeRegistry;
import de.ellpeck.actuallyadditions.mod.blocks.InitBlocks;
import de.ellpeck.actuallyadditions.mod.blocks.metalists.TheMiscBlocks;
import de.ellpeck.actuallyadditions.mod.items.InitItems;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheDusts;
import net.minecraft.item.ItemStack;

/**
 * @author The_Fireplace
 */
public class ActuallyAdditionsBaseMetals implements IModCompat {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        CrusherRecipeRegistry.addNewCrusherRecipe(new ItemStack(InitBlocks.blockMisc, 1, TheMiscBlocks.ORE_QUARTZ.ordinal()), new ItemStack(InitItems.itemDust, 2, TheDusts.QUARTZ_BLACK.ordinal()));
    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {

    }
}
