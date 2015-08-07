package the_fireplace.fluidity.compat;

import com.Red.main.ingame.RedBlocks;

import cyano.basemetals.registry.CrusherRecipeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.fluidity.Fluidity;

public class BaseMetalsBreadstone implements IModCompat {

	public static final Item flour = new Item().setUnlocalizedName("flour").setCreativeTab(Fluidity.tabFluidity);

	@Override
	public void preInit() {
		GameRegistry.registerItem(flour, "flour");
		OreDictionary.registerOre("flour", flour);
	}

	@Override
	public void init() {
		ItemStack flourStack = new ItemStack(flour);
		ItemStack flourStack5 = new ItemStack(flour, 5);
		ItemStack breadStoneStack = new ItemStack(RedBlocks.breadore);
		ItemStack breadStack = new ItemStack(Items.bread);
		CrusherRecipeRegistry.addNewCrusherRecipe(breadStoneStack, flourStack5);
		GameRegistry.addSmelting(flourStack, breadStack, 0.2F);
	}

	@Override
	public void registerInvRenderers() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(flour, 0, new ModelResourceLocation(Fluidity.MODID+":flour", "inventory"));
	}

}
