package the_fireplace.fluidity.compat;

import crazypants.enderio.machine.recipe.RecipeOutput;
import crazypants.enderio.machine.sagmill.SagMillRecipeManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class EnderIOFRT implements IModCompat {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(FRT.fossil), SagMillRecipeManager.ORE_ENERGY_COST,
                new RecipeOutput(new ItemStack(Items.DYE, 3, 15)),
                new RecipeOutput(new ItemStack(Items.DYE, 2, 15), 0.75F),
                new RecipeOutput(new ItemStack(Items.DYE, 1, 15), 0.25F),
                new RecipeOutput(new ItemStack(Blocks.COBBLESTONE), 0.05F));
    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {

    }
}
