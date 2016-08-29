package the_fireplace.fluidity.compat;

import crazypants.enderio.machine.alloy.AlloyRecipeManager;
import crazypants.enderio.machine.recipe.*;
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
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(Blocks.zinc_ore), 3600,
                new RecipeOutput(new ItemStack(Items.zinc_powder, 2)),
                new RecipeOutput(new ItemStack(net.minecraft.init.Blocks.COBBLESTONE), 0.15F));
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(Blocks.nickel_ore), 3600,
                new RecipeOutput(new ItemStack(Items.nickel_powder, 2)),
                new RecipeOutput(new ItemStack(Items.platinum_powder, 1), 0.1F));
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(Blocks.adamantine_ore), 3600,
                new RecipeOutput(new ItemStack(Items.adamantine_powder, 2)),
                new RecipeOutput(new ItemStack(net.minecraft.init.Blocks.NETHERRACK), 0.15F));
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(Blocks.coldiron_ore), 3600,
                new RecipeOutput(new ItemStack(Items.coldiron_powder, 2)),
                new RecipeOutput(new ItemStack(net.minecraft.init.Blocks.NETHERRACK), 0.15F));
        SagMillRecipeManager.getInstance().addRecipe(new ItemStack(Blocks.starsteel_ore), 3600,
                new RecipeOutput(new ItemStack(Items.starsteel_powder, 2)),
                new RecipeOutput(new ItemStack(net.minecraft.init.Blocks.END_STONE), 0.15F));
        //3 Copper + 1 Nickel in Alloy Furnace make Cupronickel
        addAlloySmelterRecipe(new ItemStack(Items.cupronickel_ingot, 4), 4000, RecipeBonusType.NONE, new ItemStack(Items.copper_ingot, 3), new ItemStack(Items.nickel_ingot));
        addAlloySmelterRecipe(new ItemStack(Items.cupronickel_ingot, 4), 4000, RecipeBonusType.NONE, new ItemStack(Items.copper_powder, 3), new ItemStack(Items.nickel_ingot));
        addAlloySmelterRecipe(new ItemStack(Items.cupronickel_ingot, 4), 4000, RecipeBonusType.NONE, new ItemStack(Items.copper_powder, 3), new ItemStack(Items.nickel_powder));
        addAlloySmelterRecipe(new ItemStack(Items.cupronickel_ingot, 4), 4000, RecipeBonusType.NONE, new ItemStack(Items.copper_ingot, 3), new ItemStack(Items.nickel_powder));
    }

    private static void addAlloySmelterRecipe(ItemStack output, int energyRequired, RecipeBonusType bonusType, ItemStack... inputs) {
        RecipeOutput rOutput = new RecipeOutput(output);
        RecipeInput[] rInputs = new RecipeInput[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            rInputs[i] = new RecipeInput(inputs[i]);
        }

        AlloyRecipeManager.getInstance().addRecipe(new BasicManyToOneRecipe(new Recipe(rOutput, energyRequired, bonusType, rInputs)));
    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {

    }
}
