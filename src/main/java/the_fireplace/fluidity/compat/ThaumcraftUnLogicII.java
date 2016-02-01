package the_fireplace.fluidity.compat;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.items.ItemsTC;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.tools.Registry;
import the_fireplace.unlogicii.items.internal.ItemPaxel;

/**
 * @author The_Fireplace
 */
public class ThaumcraftUnLogicII implements IModCompat {

	public static final Item thaumium_paxel = new ItemPaxel(ThaumcraftMaterials.TOOLMAT_THAUMIUM).setUnlocalizedName("thaumium_paxel").setCreativeTab(Fluidity.tabFluidity);

	@Override
	public void preInit() {
		Registry.register(thaumium_paxel);
	}

	@Override
	public void init() {
		ItemStack thaumiumStack = new ItemStack(ItemsTC.ingots);
		ItemStack thaumiumPaxelStack = new ItemStack(thaumium_paxel);
		Registry.addRecipe(thaumiumPaxelStack, "ttt", " t ", " s ", 't', thaumiumStack, 's', "stickWood");
	}

	@Override
	public void registerInvRenderers() {
		Registry.registerRender(thaumium_paxel);
	}
}
