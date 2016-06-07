package the_fireplace.fluidity.compat;

import de.ellpeck.actuallyadditions.mod.blocks.InitBlocks;
import de.ellpeck.actuallyadditions.mod.config.values.ConfigCrafting;
import de.ellpeck.actuallyadditions.mod.items.InitItems;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheCrystals;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheMiscItems;
import de.ellpeck.actuallyadditions.mod.util.RecipeUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.fluidity.tools.Registry;
import xreliquary.init.ModBlocks;
import xreliquary.init.ModItems;
import xreliquary.reference.Reference;
import xreliquary.reference.Settings;

import static de.ellpeck.actuallyadditions.mod.crafting.ItemCrafting.recipeWings;
import static xreliquary.init.XRRecipes.*;

/**
 * @author The_Fireplace
 */
public class ActuallyAdditionsReliquary implements IModCompat {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        OreDictionary.registerOre("wingBat", new ItemStack(InitItems.itemMisc, 1, TheMiscItems.BAT_WING.ordinal()));
        OreDictionary.registerOre("wingBat", new ItemStack(ModItems.mobIngredient, 1, Reference.BAT_INGREDIENT_META));

        addRecipes();
    }

    private void addRecipes(){
        //Reliquary
        if(Settings.EasyModeRecipes.interdictionTorch)
            Registry.addRecipe(new ItemStack(ModBlocks.interdictionTorch, 4, 0), "bm", "nr", 'b', "wingBat", 'm', MOLTEN_CORE, 'n', NEBULOUS_HEART, 'r', Items.BLAZE_ROD);
        else
            Registry.addRecipe(new ItemStack(ModBlocks.interdictionTorch, 4, 0), " n ", "mdm", "bwb", 'n', NEBULOUS_HEART, 'm', MOLTEN_CORE, 'd', "gemDiamond", 'b', Items.BLAZE_ROD, 'w', "wingBat");
        if(Settings.EasyModeRecipes.fortuneCoin)
            Registry.addShapelessRecipe(new ItemStack(ModItems.fortuneCoin, 1), NEBULOUS_HEART, "nuggetGold", SLIME_PEARL, "wingBat");
        if(Settings.EasyModeRecipes.enderStaff)
            Registry.addRecipe(new ItemStack(ModItems.enderStaff, 1, 0), " be", "nvb", "sn ", 'v', ModItems.emptyVoidTear, 'e', Items.ENDER_EYE, 's', "stickWood", 'n', NEBULOUS_HEART, 'b', "wingBat");
        else
            Registry.addRecipe(new ItemStack(ModItems.enderStaff, 1, 0), "nbe", "nvb", "rnn", 'n', NEBULOUS_HEART, 'b', "wingBat", 'e', Items.ENDER_EYE, 'v', EMPTY_VOID_TEAR, 'r', Items.BLAZE_ROD);
        if(Settings.EasyModeRecipes.rendingGale)
            Registry.addRecipe(new ItemStack(ModItems.rendingGale, 1, 0), " be", "gvb", "sg ", 'b', "wingBat", 'e', STORM_EYE, 'g', "ingotGold", 'v', EMPTY_VOID_TEAR, 's', "stickWood");
        else
            Registry.addRecipe(new ItemStack(ModItems.rendingGale, 1, 0), "ebe", "fvb", "rfe", 'e', STORM_EYE, 'b', "wingBat", 'f', ModItems.angelicFeather, 'v', EMPTY_VOID_TEAR, 'r', Items.BLAZE_ROD);
        if(Settings.EasyModeRecipes.rodOfLyssa)
            Registry.addShapelessRecipe(new ItemStack(ModItems.rodOfLyssa, 1, 0), INFERNAL_CLAW, "wingBat", NEBULOUS_HEART, Items.FISHING_ROD);
        else
            Registry.addRecipe(new ItemStack(ModItems.rodOfLyssa, 1, 0), " br", "nms", "r i", 'b', "wingBat", 'r', Items.BLAZE_ROD, 'n', NEBULOUS_HEART, 'm', MOLTEN_CORE, 's', "string", 'i', INFERNAL_CLAW);
        if(Settings.EasyModeRecipes.angelicFeather)
            Registry.addShapelessRecipe(new ItemStack(ModItems.angelicFeather, 1), Items.FEATHER, NEBULOUS_HEART, "wingBat", FERTILE_ESSENCE);
        else
            Registry.addRecipe(new ItemStack(ModItems.angelicFeather, 1), "dgd", "bfb", "ene", 'd', "dustGlowstone", 'g', "ingotGold", 'b', "wingBat", 'e', FERTILE_ESSENCE, 'n', NEBULOUS_HEART, 'f', Items.FEATHER);
        //Actually Additions
        if(ConfigCrafting.BAT_WINGS.isEnabled()){
            Registry.addRecipe(new ItemStack(InitItems.itemWingsOfTheBats), "WNW", "WDW", "WNW", 'W', "wingBat", 'N', new ItemStack(InitBlocks.blockCrystal, 1, TheCrystals.DIAMOND.ordinal()), 'D', new ItemStack(InitItems.itemMisc, 1, TheMiscItems.ENDER_STAR.ordinal()));
            recipeWings = RecipeUtil.lastIRecipe();
        }
    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {

    }
}
