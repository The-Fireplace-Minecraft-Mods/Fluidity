package the_fireplace.fluidity.compat;

import crazypants.enderio.machine.recipe.RecipeOutput;
import crazypants.enderio.machine.sagmill.SagMillRecipeManager;
import cyano.basemetals.init.Blocks;
import cyano.basemetals.init.Items;
import net.minecraft.item.ItemStack;

/**
 * @author The_Fireplace
 */
public class BaseMetalsEnderIO implements IModCompat {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(Blocks.zinc_ore), SagMillRecipeManager.ORE_ENERGY_COST,
                new RecipeOutput(new ItemStack(Items.zinc_powder, 2)),
                new RecipeOutput(new ItemStack(net.minecraft.init.Blocks.COBBLESTONE), 0.15F));
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(Blocks.nickel_ore), SagMillRecipeManager.ORE_ENERGY_COST,
                new RecipeOutput(new ItemStack(Items.nickel_powder, 2)),
                new RecipeOutput(new ItemStack(Items.platinum_powder, 1), 0.1F));
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(Blocks.adamantine_ore), SagMillRecipeManager.ORE_ENERGY_COST,
                new RecipeOutput(new ItemStack(Items.adamantine_powder, 2)),
                new RecipeOutput(new ItemStack(net.minecraft.init.Blocks.NETHERRACK), 0.15F));
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(Blocks.coldiron_ore), SagMillRecipeManager.ORE_ENERGY_COST,
                new RecipeOutput(new ItemStack(Items.coldiron_powder, 2)),
                new RecipeOutput(new ItemStack(net.minecraft.init.Blocks.NETHERRACK), 0.15F));
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(Blocks.starsteel_ore), SagMillRecipeManager.ORE_ENERGY_COST,
                new RecipeOutput(new ItemStack(Items.starsteel_powder, 2)),
                new RecipeOutput(new ItemStack(net.minecraft.init.Blocks.END_STONE), 0.15F));
    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {

    }
}
