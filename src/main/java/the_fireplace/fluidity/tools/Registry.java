package the_fireplace.fluidity.tools;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import the_fireplace.fluidity.Fluidity;

import java.util.ArrayList;

/**
 * @author The_Fireplace
 */
public class Registry {
	public static void register(Item i){
		GameRegistry.registerItem(i, i.getUnlocalizedName().substring(5));
	}

	public static void register(Block b){
		GameRegistry.registerItem(Item.getItemFromBlock(b), b.getUnlocalizedName().substring(5));
	}

	public static void addRecipe(ItemStack stack, Object... args){
		GameRegistry.addRecipe(new ShapedOreRecipe(stack, args));
	}

	public static void addShapelessRecipe(ItemStack stack, Object... args){
		GameRegistry.addRecipe(new ShapelessOreRecipe(stack, args));
	}

	@SideOnly(Side.CLIENT)
	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Fluidity.MODID+":"+item.getUnlocalizedName().substring(5), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerRender(Item item, String name){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Fluidity.MODID+":"+name, "inventory"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerRender(Block block){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Fluidity.MODID+":"+block.getUnlocalizedName().substring(5), "inventory"));
	}

	public static void removeRecipe(ItemStack resultItem){
		ItemStack recipeResult;
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
}
