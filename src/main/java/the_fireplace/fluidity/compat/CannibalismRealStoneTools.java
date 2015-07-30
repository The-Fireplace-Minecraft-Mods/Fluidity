package the_fireplace.fluidity.compat;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import sorazodia.cannibalism.items.manager.ItemList;

public class CannibalismRealStoneTools implements IModCompat {

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		removeRecipe(new ItemStack(ItemList.stoneKnife));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemList.stoneKnife), " r", "s ", 'r', new ItemStack(Blocks.stone), 's', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemList.stoneKnife), " r", "s ", 'r', new ItemStack(Blocks.stone), 's', "rodWood"));
	}

	private void removeRecipe(ItemStack resultItem){
		ItemStack recipeResult = null;
		ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();
		for(int scan = 0;scan < recipes.size();scan++){
			IRecipe tmpRecipe = (IRecipe) recipes.get(scan);
			recipeResult = tmpRecipe.getRecipeOutput();
			if(recipeResult != null){
				if(recipeResult.getItem() == resultItem.getItem() && recipeResult.getItemDamage() == resultItem.getItemDamage()){
					recipes.remove(scan);
					scan--;
				}
			}
		}
	}

	@Override
	public void registerInvRenderers() {

	}

}
