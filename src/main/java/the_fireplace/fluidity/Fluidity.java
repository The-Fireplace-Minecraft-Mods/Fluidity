package the_fireplace.fluidity;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.fluidity.compat.AdobeBlocksUnLogicII;
import the_fireplace.fluidity.compat.BaseMetalsCannibalism;
import the_fireplace.fluidity.compat.BaseMetalsCarbonPaper;
import the_fireplace.fluidity.compat.CannibalismRealStoneTools;
import the_fireplace.fluidity.compat.FluidityIronChests;
import the_fireplace.fluidity.compat.IModCompat;

/**
 * @author The_Fireplace
 */
@Mod(modid=Fluidity.MODID, name=Fluidity.MODNAME, version=Fluidity.VERSION)
public class Fluidity {
	public static final String MODID = "fluidity";
	public static final String MODNAME = "Fluidity";
	public static final String VERSION = "2.0.0.5";
	@Instance(MODID)
	public static Fluidity instance;

	public static final CreativeTabs tabFluidity = new TabFluidity();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		IModCompat compat;
		if(Loader.isModLoaded("adobeblocks") && Loader.isModLoaded("unlogicii")){
			compat = new AdobeBlocksUnLogicII();
			compat.preInit();
		}
		if(Loader.isModLoaded("basemetals") && Loader.isModLoaded("cannibalism")){
			compat = new BaseMetalsCannibalism();
			compat.preInit();
		}
		if(Loader.isModLoaded("IronChest") && canIronChest()){
			compat = new FluidityIronChests();
			compat.preInit();
		}
		overrideDescription(event);
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		IModCompat compat;
		if(Loader.isModLoaded("adobeblocks") && Loader.isModLoaded("unlogicii")){
			compat = new AdobeBlocksUnLogicII();
			compat.init();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		if(Loader.isModLoaded("basemetals") && Loader.isModLoaded("cannibalism")){
			compat = new BaseMetalsCannibalism();
			compat.init();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		if(Loader.isModLoaded("basemetals") && Loader.isModLoaded("samscarbonpaper")){
			compat = new BaseMetalsCarbonPaper();
			compat.init();
		}
		if(Loader.isModLoaded("cannibalism") && Loader.isModLoaded("realstonetools")){
			compat = new CannibalismRealStoneTools();
			compat.init();
		}
		if(Loader.isModLoaded("IronChest") && canIronChest()){
			compat = new FluidityIronChests();
			compat.init();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
	}
	private boolean canIronChest(){
		return !OreDictionary.getOres("ingotBronze").isEmpty() || !OreDictionary.getOres("ingotInvar").isEmpty() || !OreDictionary.getOres("ingotElectrum").isEmpty() || !OreDictionary.getOres("ingotTin").isEmpty() || !OreDictionary.getOres("ingotBrass").isEmpty() || !OreDictionary.getOres("ingotLead").isEmpty() || !OreDictionary.getOres("ingotSteel").isEmpty() || !OreDictionary.getOres("ingotNickel").isEmpty();
	}
	private void overrideDescription(FMLPreInitializationEvent event){
		String mods = "";//TODO: Look up the mod name instead of typing it
		if(Loader.isModLoaded("adobeblocks")){
			mods = mods.concat("\nAdobe Blocks");
		}
		if(Loader.isModLoaded("basemetals")){
			mods = mods.concat("\nBase Metals");
		}
		if(Loader.isModLoaded("cannibalism")){
			mods = mods.concat("\nCannibalism");
		}
		if(Loader.isModLoaded("samscarbonpaper")){
			mods = mods.concat("\nCarbon Paper");
		}
		if(Loader.isModLoaded("IronChest")){
			mods = mods.concat("\nIron Chest");
		}
		if(Loader.isModLoaded("realstonetools")){
			mods = mods.concat("\nReal Stone Tools");
		}
		if(Loader.isModLoaded("unlogicii")){
			mods = mods.concat("\nUnLogic II");
		}
		if(mods.equals(""))
			mods = mods.concat("\n"+EnumChatFormatting.RED+"none");
		event.getModMetadata().description = "Adds all kinds of content that should only exist when certain mods are loaded together.\n"
				+ "Loaded mods that Fluidity can improve upon:"
				+ mods;
	}
	public static final String LATEST = "https://dl.dropboxusercontent.com/s/532e9ihhlbnmr2j/latest.version?dl=0";
	public static final String DOWNLOADURL = "https://dl.dropboxusercontent.com/s/6jxiwwls1967f3b/url.txt?dl=0";
}
