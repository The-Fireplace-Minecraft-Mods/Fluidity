package the_fireplace.fluidity.compat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sorazodia.cannibalism.items.ItemKnife;
import sorazodia.cannibalism.items.manager.ItemList;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.items.ItemsTC;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.items.ItemHumanChunks;
import the_fireplace.fluidity.tools.Registry;

/**
 * @author The_Fireplace
 */
public class CannibalismThaumcraft implements IModCompat {

	public static Item thaumium_knife = new ItemKnife(ThaumcraftMaterials.TOOLMAT_THAUMIUM).setUnlocalizedName("thaumium_knife").setCreativeTab(Fluidity.tabFluidity);
	public static Item human_nugget;

	@Override
	public void preInit() {
		Registry.register(thaumium_knife);
		human_nugget = registerItem(new ItemHumanChunks(), "chunks");
	}

	@Override
	public void init() {
		Registry.addRecipe(new ItemStack(thaumium_knife), " r", "s ", 'r', ItemsTC.ingots, 's', "stickWood");
		ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemList.playerFlesh), new ItemStack(human_nugget));
		ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemList.villagerFlesh), new ItemStack(human_nugget, 1, 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerInvRenderers() {
		Registry.registerRender(thaumium_knife);
		ModelBakery.registerItemVariants(human_nugget, new ModelResourceLocation("fluidity:chunk_human", "inventory"), new ModelResourceLocation("fluidity:chunk_villager", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(human_nugget, 0, new ModelResourceLocation("fluidity:chunk_human", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(human_nugget, 1, new ModelResourceLocation("fluidity:chunk_villager", "inventory"));
	}

	private static Item registerItem(Item item, String name) {
		item.setUnlocalizedName(name);
		item.setCreativeTab(Fluidity.tabFluidity);
		GameRegistry.registerItem(item, name);

		return item;
	}
}
