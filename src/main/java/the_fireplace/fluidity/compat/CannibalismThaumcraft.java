package the_fireplace.fluidity.compat;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sorazodia.cannibalism.items.ItemKnife;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.items.ItemsTC;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.tools.Registry;

/**
 * @author The_Fireplace
 */
public class CannibalismThaumcraft implements IModCompat {

	public static Item thaumium_knife = new ItemKnife(ThaumcraftMaterials.TOOLMAT_THAUMIUM).setUnlocalizedName("thaumium_knife").setCreativeTab(Fluidity.tabFluidity);

	@Override
	public void preInit() {
		Registry.register(thaumium_knife);
	}

	@Override
	public void init() {
		Registry.addRecipe(new ItemStack(thaumium_knife), " r", "s ", 'r', ItemsTC.ingots, 's', "stickWood");
	}

	@Override
	public void registerInvRenderers() {
		Registry.registerRender(thaumium_knife);
	}
}
