package the_fireplace.fluidity.compat;

import cyano.basemetals.init.Materials;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sorazodia.api.registryhelper.OreDicRecipe;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.items.ItemBaseMetalKnife;

public class BaseMetalsCannibalism implements IModCompat {

	public static final Item copper_knife = new ItemBaseMetalKnife(Materials.copper).setUnlocalizedName("copper_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item silver_knife = new ItemBaseMetalKnife(Materials.silver).setUnlocalizedName("silver_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item tin_knife = new ItemBaseMetalKnife(Materials.tin).setUnlocalizedName("tin_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item lead_knife = new ItemBaseMetalKnife(Materials.lead).setUnlocalizedName("lead_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item nickel_knife = new ItemBaseMetalKnife(Materials.nickel).setUnlocalizedName("nickel_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item bronze_knife = new ItemBaseMetalKnife(Materials.bronze).setUnlocalizedName("bronze_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item brass_knife = new ItemBaseMetalKnife(Materials.brass).setUnlocalizedName("brass_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item steel_knife = new ItemBaseMetalKnife(Materials.steel).setUnlocalizedName("steel_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item invar_knife = new ItemBaseMetalKnife(Materials.invar).setUnlocalizedName("invar_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item electrum_knife = new ItemBaseMetalKnife(Materials.electrum).setUnlocalizedName("electrum_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item coldiron_knife = new ItemBaseMetalKnife(Materials.coldiron).setUnlocalizedName("coldiron_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item mithril_knife = new ItemBaseMetalKnife(Materials.mithril).setUnlocalizedName("mithril_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item adamantine_knife = new ItemBaseMetalKnife(Materials.adamantine).setUnlocalizedName("adamantine_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item starsteel_knife = new ItemBaseMetalKnife(Materials.starsteel).setUnlocalizedName("starsteel_knife").setCreativeTab(Fluidity.tabFluidity);
	public static final Item aquarium_knife = new ItemBaseMetalKnife(Materials.aquarium).setUnlocalizedName("aquarium_knife").setCreativeTab(Fluidity.tabFluidity);

	@Override
	public void preInit() {
		registerItem(copper_knife);
		registerItem(silver_knife);
		registerItem(tin_knife);
		registerItem(lead_knife);
		registerItem(nickel_knife);
		registerItem(bronze_knife);
		registerItem(brass_knife);
		registerItem(steel_knife);
		registerItem(invar_knife);
		registerItem(electrum_knife);
		registerItem(coldiron_knife);
		registerItem(mithril_knife);
		registerItem(adamantine_knife);
		registerItem(starsteel_knife);
		registerItem(aquarium_knife);
	}

	@Override
	public void init() {
		OreDicRecipe.shapedOreSimplifer(new ItemStack(copper_knife), " x ", "  y", 'x', "ingotCopper", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(copper_knife), "x  ", " y ", 'x', "ingotCopper", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(silver_knife), " x ", "  y", 'x', "ingotSilver", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(silver_knife), "x  ", " y ", 'x', "ingotSilver", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(tin_knife), " x ", "  y", 'x', "ingotTin", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(tin_knife), "x  ", " y ", 'x', "ingotTin", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(lead_knife), " x ", "  y", 'x', "ingotLead", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(lead_knife), "x  ", " y ", 'x', "ingotLead", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(nickel_knife), " x ", "  y", 'x', "ingotNickel", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(nickel_knife), "x  ", " y ", 'x', "ingotNickel", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(bronze_knife), " x ", "  y", 'x', "ingotBronze", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(bronze_knife), "x  ", " y ", 'x', "ingotBronze", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(brass_knife), " x ", "  y", 'x', "ingotBrass", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(brass_knife), "x  ", " y ", 'x', "ingotBrass", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(steel_knife), " x ", "  y", 'x', "ingotSteel", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(steel_knife), "x  ", " y ", 'x', "ingotSteel", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(invar_knife), " x ", "  y", 'x', "ingotInvar", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(invar_knife), "x  ", " y ", 'x', "ingotInvar", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(electrum_knife), " x ", "  y", 'x', "ingotElectrum", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(electrum_knife), "x  ", " y ", 'x', "ingotElectrum", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(coldiron_knife), " x ", "  y", 'x', "ingotColdiron", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(coldiron_knife), "x  ", " y ", 'x', "ingotColdiron", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(mithril_knife), " x ", "  y", 'x', "ingotMithril", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(mithril_knife), "x  ", " y ", 'x', "ingotMithril", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(adamantine_knife), " x ", "  y", 'x', "ingotAdamantine", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(adamantine_knife), "x  ", " y ", 'x', "ingotAdamantine", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(starsteel_knife), " x ", "  y", 'x', "ingotStarsteel", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(starsteel_knife), "x  ", " y ", 'x', "ingotStarsteel", 'y', "rodWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(aquarium_knife), " x ", "  y", 'x', "ingotAquarium", 'y', "stickWood");
		OreDicRecipe.shapedOreSimplifer(new ItemStack(aquarium_knife), "x  ", " y ", 'x', "ingotAquarium", 'y', "rodWood");
	}

	@Override
	public void registerInvRenderers() {
		registerRender(copper_knife);
		registerRender(silver_knife);
		registerRender(tin_knife);
		registerRender(lead_knife);
		registerRender(nickel_knife);
		registerRender(bronze_knife);
		registerRender(brass_knife);
		registerRender(steel_knife);
		registerRender(invar_knife);
		registerRender(electrum_knife);
		registerRender(coldiron_knife);
		registerRender(mithril_knife);
		registerRender(adamantine_knife);
		registerRender(starsteel_knife);
		registerRender(aquarium_knife);
	}

	private void registerItem(Item item){
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
	private void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Fluidity.MODID+":"+item.getUnlocalizedName().substring(5), "inventory"));
	}
}
