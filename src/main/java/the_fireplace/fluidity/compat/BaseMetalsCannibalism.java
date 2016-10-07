package the_fireplace.fluidity.compat;

import cyano.basemetals.init.Materials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.items.ItemBaseMetalKnife;
import the_fireplace.fluidity.tools.Registry;

public class BaseMetalsCannibalism implements IModCompat {

	public static Item copper_knife;
	public static Item silver_knife;
	public static Item tin_knife;
	public static Item lead_knife;
	public static Item nickel_knife;
	public static Item bronze_knife;
	public static Item brass_knife;
	public static Item steel_knife;
	public static Item invar_knife;
	public static Item electrum_knife;
	public static Item coldiron_knife;
	public static Item mithril_knife;
	public static Item adamantine_knife;
	public static Item starsteel_knife;
	public static Item aquarium_knife;
	public static Item cupronickel_knife;
	public static Item platinum_knife;

	@Override
	public void preInit() {
		Materials.init();
		copper_knife = new ItemBaseMetalKnife(Materials.copper).setUnlocalizedName("copper_knife").setCreativeTab(Fluidity.tabFluidity);
		silver_knife = new ItemBaseMetalKnife(Materials.silver).setUnlocalizedName("silver_knife").setCreativeTab(Fluidity.tabFluidity);
		tin_knife = new ItemBaseMetalKnife(Materials.tin).setUnlocalizedName("tin_knife").setCreativeTab(Fluidity.tabFluidity);
		lead_knife = new ItemBaseMetalKnife(Materials.lead).setUnlocalizedName("lead_knife").setCreativeTab(Fluidity.tabFluidity);
		nickel_knife = new ItemBaseMetalKnife(Materials.nickel).setUnlocalizedName("nickel_knife").setCreativeTab(Fluidity.tabFluidity);
		bronze_knife = new ItemBaseMetalKnife(Materials.bronze).setUnlocalizedName("bronze_knife").setCreativeTab(Fluidity.tabFluidity);
		brass_knife = new ItemBaseMetalKnife(Materials.brass).setUnlocalizedName("brass_knife").setCreativeTab(Fluidity.tabFluidity);
		steel_knife = new ItemBaseMetalKnife(Materials.steel).setUnlocalizedName("steel_knife").setCreativeTab(Fluidity.tabFluidity);
		invar_knife = new ItemBaseMetalKnife(Materials.invar).setUnlocalizedName("invar_knife").setCreativeTab(Fluidity.tabFluidity);
		electrum_knife = new ItemBaseMetalKnife(Materials.electrum).setUnlocalizedName("electrum_knife").setCreativeTab(Fluidity.tabFluidity);
		coldiron_knife = new ItemBaseMetalKnife(Materials.coldiron).setUnlocalizedName("coldiron_knife").setCreativeTab(Fluidity.tabFluidity);
		mithril_knife = new ItemBaseMetalKnife(Materials.mithril).setUnlocalizedName("mithril_knife").setCreativeTab(Fluidity.tabFluidity);
		adamantine_knife = new ItemBaseMetalKnife(Materials.adamantine).setUnlocalizedName("adamantine_knife").setCreativeTab(Fluidity.tabFluidity);
		starsteel_knife = new ItemBaseMetalKnife(Materials.starsteel).setUnlocalizedName("starsteel_knife").setCreativeTab(Fluidity.tabFluidity);
		aquarium_knife = new ItemBaseMetalKnife(Materials.aquarium).setUnlocalizedName("aquarium_knife").setCreativeTab(Fluidity.tabFluidity);
		cupronickel_knife = new ItemBaseMetalKnife(Materials.cupronickel).setUnlocalizedName("cupronickel_knife").setCreativeTab(Fluidity.tabFluidity);
		platinum_knife = new ItemBaseMetalKnife(Materials.platinum).setUnlocalizedName("platinum_knife").setCreativeTab(Fluidity.tabFluidity);

		Registry.register(copper_knife);
		Registry.register(silver_knife);
		Registry.register(tin_knife);
		Registry.register(lead_knife);
		Registry.register(nickel_knife);
		Registry.register(bronze_knife);
		Registry.register(brass_knife);
		Registry.register(steel_knife);
		Registry.register(invar_knife);
		Registry.register(electrum_knife);
		Registry.register(coldiron_knife);
		Registry.register(mithril_knife);
		Registry.register(adamantine_knife);
		Registry.register(starsteel_knife);
		Registry.register(aquarium_knife);
		Registry.register(cupronickel_knife);
		Registry.register(platinum_knife);
	}

	@Override
	public void init() {
		Registry.addRecipe(new ItemStack(copper_knife), "x ", " y", 'x', "ingotCopper", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(silver_knife), "x ", " y", 'x', "ingotSilver", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(tin_knife), "x ", " y", 'x', "ingotTin", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(lead_knife), "x ", " y", 'x', "ingotLead", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(nickel_knife), "x ", " y", 'x', "ingotNickel", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(bronze_knife), "x ", " y", 'x', "ingotBronze", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(brass_knife), "x ", " y", 'x', "ingotBrass", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(steel_knife), "x ", " y", 'x', "ingotSteel", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(invar_knife), "x ", " y", 'x', "ingotInvar", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(electrum_knife), "x ", " y", 'x', "ingotElectrum", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(coldiron_knife), "x ", " y", 'x', "ingotColdiron", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(mithril_knife), "x ", " y", 'x', "ingotMithril", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(adamantine_knife), "x ", " y", 'x', "ingotAdamantine", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(starsteel_knife), "x ", " y", 'x', "ingotStarsteel", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(aquarium_knife), "x ", " y", 'x', "ingotAquarium", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(cupronickel_knife), "x ", " y", 'x', "ingotCupronickel", 'y', "stickWood");
		Registry.addRecipe(new ItemStack(platinum_knife), "x ", " y", 'x', "ingotPlatinum", 'y', "stickWood");
	}

	@Override
	public void postInit() {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerInvRenderers() {
		Registry.registerRender(copper_knife);
		Registry.registerRender(silver_knife);
		Registry.registerRender(tin_knife);
		Registry.registerRender(lead_knife);
		Registry.registerRender(nickel_knife);
		Registry.registerRender(bronze_knife);
		Registry.registerRender(brass_knife);
		Registry.registerRender(steel_knife);
		Registry.registerRender(invar_knife);
		Registry.registerRender(electrum_knife);
		Registry.registerRender(coldiron_knife);
		Registry.registerRender(mithril_knife);
		Registry.registerRender(adamantine_knife);
		Registry.registerRender(starsteel_knife);
		Registry.registerRender(aquarium_knife);
		Registry.registerRender(cupronickel_knife);
		Registry.registerRender(platinum_knife);
	}
}