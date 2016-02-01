package the_fireplace.fluidity;

import com.google.common.collect.Lists;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.fluidity.compat.*;

import java.util.ArrayList;

/**
 * @author The_Fireplace
 */
@Mod(modid=Fluidity.MODID, name=Fluidity.MODNAME)
public class Fluidity {
	public static final String MODID = "fluidity";
	public static final String MODNAME = "Fluidity";
	public static String VERSION;
	public final ArrayList<String> supportedMods = Lists.newArrayList();
	@Instance(MODID)
	public static Fluidity instance;

	public static final CreativeTabs tabFluidity = new TabFluidity();

	private void addSupported(){
		supportedMods.add("adobeblocks");
		supportedMods.add("basemetals");
		supportedMods.add("cannibalism");
		supportedMods.add("invtweaks");
		supportedMods.add("IronChest");
		supportedMods.add("realstonetools");
		supportedMods.add("samscarbonpaper");
		supportedMods.add("Thaumcraft");
		supportedMods.add("unlogicii");
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		addSupported();
		IModCompat compat;
		if(Loader.isModLoaded("adobeblocks") && Loader.isModLoaded("unlogicii")){
			compat = new AdobeBlocksUnLogicII();
			compat.preInit();
		}
		if(Loader.isModLoaded("basemetals") && Loader.isModLoaded("cannibalism")){
			compat = new BaseMetalsCannibalism();
			compat.preInit();
		}
		if(Loader.isModLoaded("cannibalism") && Loader.isModLoaded("Thaumcraft")){
			compat = new CannibalismThaumcraft();
			compat.preInit();
		}
		if(Loader.isModLoaded("IronChest") && canIronChest()){
			compat = new FluidityIronChests();
			compat.preInit();
		}
		if(Loader.isModLoaded("Thaumcraft") && Loader.isModLoaded("unlogicii")){
			compat = new ThaumcraftUnLogicII();
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
		if(Loader.isModLoaded("cannibalism") && Loader.isModLoaded("Thaumcraft")){
			compat = new CannibalismThaumcraft();
			compat.init();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		if(Loader.isModLoaded("IronChest") && canIronChest()){
			compat = new FluidityIronChests();
			compat.init();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		if(Loader.isModLoaded("Thaumcraft") && Loader.isModLoaded("unlogicii")){
			compat = new ThaumcraftUnLogicII();
			compat.init();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
	}
	private boolean canIronChest(){
		return !OreDictionary.getOres("ingotBronze").isEmpty() || !OreDictionary.getOres("ingotInvar").isEmpty() || !OreDictionary.getOres("ingotElectrum").isEmpty() || !OreDictionary.getOres("ingotTin").isEmpty() || !OreDictionary.getOres("ingotBrass").isEmpty() || !OreDictionary.getOres("ingotLead").isEmpty() || !OreDictionary.getOres("ingotSteel").isEmpty() || !OreDictionary.getOres("ingotNickel").isEmpty();
	}
	private void overrideDescription(FMLPreInitializationEvent event){
		String mods = "";
		for(String mid:supportedMods)
			if(Loader.isModLoaded(mid))
				for(ModContainer mod:Loader.instance().getActiveModList())
					if(mid.equals(mod.getModId()))
						mods += "\n"+mod.getName();
		if(mods.equals(""))
			mods = mods.concat("\n"+EnumChatFormatting.RED+"none");
		event.getModMetadata().description = "Adds all kinds of content that should only exist when certain mods are loaded together.\n"
				+ "Loaded mods that Fluidity can improve upon:"
				+ mods;
	}
	public static final String LATEST = "https://dl.dropboxusercontent.com/s/532e9ihhlbnmr2j/latest.version?dl=0";
	public static final String DOWNLOADURL = "https://dl.dropboxusercontent.com/s/6jxiwwls1967f3b/url.txt?dl=0";
}
