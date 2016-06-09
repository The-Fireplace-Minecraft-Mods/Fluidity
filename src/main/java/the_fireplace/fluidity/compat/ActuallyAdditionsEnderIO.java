package the_fireplace.fluidity.compat;

import crazypants.enderio.machine.recipe.RecipeOutput;
import crazypants.enderio.machine.sagmill.SagMillRecipeManager;
import de.ellpeck.actuallyadditions.mod.blocks.InitBlocks;
import de.ellpeck.actuallyadditions.mod.blocks.metalists.TheMiscBlocks;
import de.ellpeck.actuallyadditions.mod.items.InitItems;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheDusts;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author The_Fireplace
 */
public class ActuallyAdditionsEnderIO implements IModCompat {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(InitBlocks.blockMisc, 1, TheMiscBlocks.ORE_QUARTZ.ordinal()), SagMillRecipeManager.ORE_ENERGY_COST,
                new RecipeOutput(new ItemStack(InitItems.itemDust, 2, TheDusts.QUARTZ_BLACK.ordinal())),
                new RecipeOutput(new ItemStack(InitItems.itemDust, 1, TheDusts.QUARTZ_BLACK.ordinal()), 0.12F),
                new RecipeOutput(new ItemStack(Blocks.COBBLESTONE), 0.15F));
    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {

    }
}
