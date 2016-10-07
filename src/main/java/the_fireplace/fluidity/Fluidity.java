package the_fireplace.fluidity;

import com.google.common.collect.Lists;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.ArrayUtils;
import the_fireplace.fluidity.compat.*;
import the_fireplace.fluidity.config.ConfigValues;
import the_fireplace.fluidity.proxy.CommonProxy;

import java.util.ArrayList;

/**
 * @author The_Fireplace
 */
@Mod(modid=Fluidity.MODID, name=Fluidity.MODNAME, updateJSON = "http://thefireplace.bitnamiapp.com/jsons/fluidity.json", dependencies = "required-after:Forge@[12.18.1.2019,);after:*", guiFactory = "the_fireplace.fluidity.config.FluidityConfigGuiFactory")
public class Fluidity {
	public static final String MODID = "fluidity";
	public static final String MODNAME = "Fluidity";
	public static String VERSION;
	public final ArrayList<String> supportedMods = Lists.newArrayList();
	@Instance(MODID)
	public static Fluidity instance;

	public int entityID = -1;

	@SidedProxy(clientSide = "the_fireplace.fluidity.proxy.ClientProxy", serverSide = "the_fireplace.fluidity.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final CreativeTabs tabFluidity = new TabFluidity();

	public boolean isClient=false;

	public static Configuration config;
	public static Property DISABLEDCOMPAT_PROP;
	
	public static void syncConfig(){
		ConfigValues.DISABLEDCOMPAT = DISABLEDCOMPAT_PROP.getStringList();
		if(config.hasChanged())
			config.save();
	}

	private void addSupported(){
		supportedMods.add("actuallyadditions");
		supportedMods.add("adobeblocks");
		supportedMods.add("basemetals");
		//supportedMods.add("Baubles");
		supportedMods.add("BiomesOPlenty");//Remove in 1.11
		supportedMods.add("biomesoplenty");
		//supportedMods.add("cannibalism");
		supportedMods.add("cookingforblockheads");
		supportedMods.add("electricadvantage");
		supportedMods.add("EnderIO");//Remove in 1.11
		supportedMods.add("enderio");
		supportedMods.add("evilcraft");
		supportedMods.add("frt");
		supportedMods.add("invtweaks");
		supportedMods.add("IronChest");//remove in 1.11
		supportedMods.add("ironchest");
		supportedMods.add("moreanvils");
		supportedMods.add("randomthings");
		supportedMods.add("realstonetools");
		//supportedMods.add("Thaumcraft");
		supportedMods.add("theoneprobe");
		supportedMods.add("xreliquary");
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		if(event.getSide().isClient())
			isClient=true;
		addSupported();
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		DISABLEDCOMPAT_PROP = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.DISABLEDCOMPAT_NAME, ConfigValues.DISABLEDCOMPAT_DEFAULT);
		syncConfig();
		IModCompat compat;

		if(canLoadModule("actuallyadditions") && canLoadModule("randomthings")){
			compat = new ActuallyAdditionsRandomThings();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		if(canLoadModule("adobeblocks") && canLoadModule("frt")){
			compat = new AdobeBlocksFRT();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		if(canLoadModule("basemetals") && canLoadModule("cannibalism")){
			compat = new BaseMetalsCannibalism();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		if(canLoadModule("basemetals") && canLoadModule("moreanvils")){
			compat = new BaseMetalsMoreAnvils();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		if(canLoadModule("basemetals") && canLoadModule("theoneprobe")){
			compat = new BaseMetalsTOP();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		/*if(canLoadModule("cannibalism") && canLoadModule("Thaumcraft")){
			compat = new CannibalismThaumcraft();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}*/
		if(canLoadModule("evilcraft") && canLoadModule("frt")){
			compat = new EvilCraftFRT();
			compat.preInit();
		}
		if(canLoadModule("IronChest") || canLoadModule("ironchest")){
			compat = new FluidityIronChests();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		/*if(canLoadModule("Thaumcraft") && canLoadModule("frt")){
			compat = new ThaumcraftFRT();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}*/
		if(event.getSide().isClient())
			overrideDescription(event.getModMetadata());

		if(canLoadModule("actuallyadditions")){
			compat = new FluidityActuallyAdditions();
			compat.preInit();
		}
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		IModCompat compat;
		if(canLoadModule("actuallyadditions") && canLoadModule("basemetals")){
			compat = new ActuallyAdditionsBaseMetals();
			compat.init();
		}
		if(canLoadModule("actuallyadditions") && (canLoadModule("EnderIO") || canLoadModule("enderio"))){
			compat = new ActuallyAdditionsEnderIO();
			compat.init();
		}
		if(canLoadModule("actuallyadditions") && canLoadModule("randomthings")){
			compat = new ActuallyAdditionsRandomThings();
			compat.init();
		}
		if(canLoadModule("actuallyadditions") && canLoadModule("xreliquary")){
			compat = new ActuallyAdditionsReliquary();
			compat.init();
		}
		if(canLoadModule("adobeblocks") && canLoadModule("frt")){
			compat = new AdobeBlocksFRT();
			compat.init();
		}
		if(canLoadModule("basemetals") && canLoadModule("cannibalism")){
			compat = new BaseMetalsCannibalism();
			compat.init();
		}
		if(canLoadModule("basemetals") && (canLoadModule("EnderIO") || canLoadModule("enderio"))){
			compat = new BaseMetalsEnderIO();
			compat.init();
		}
		if(canLoadModule("basemetals") && canLoadModule("moreanvils")){
			compat = new BaseMetalsMoreAnvils();
			compat.init();
		}
		if(canLoadModule("basemetals") && canLoadModule("theoneprobe")){
			compat = new BaseMetalsTOP();
			compat.init();
		}
		if((canLoadModule("BiomesOPlenty") || canLoadModule("biomesoplenty")) && canLoadModule("realstonetools")){
			compat = new BOPRealStoneTools();
			compat.init();
		}
		if(canLoadModule("cannibalism") && canLoadModule("realstonetools")){
			compat = new CannibalismRealStoneTools();
			compat.init();
		}
		/*if(canLoadModule("cannibalism") && canLoadModule("Thaumcraft")){
			compat = new CannibalismThaumcraft();
			compat.init();
		}*/
		if(canLoadModule("electricadvantage") && (canLoadModule("EnderIO") || canLoadModule("enderio"))){
			compat = new ElectricAdvantageEnderIO();
			compat.init();
		}
		if(canLoadModule("IronChest") || canLoadModule("ironchest")){
			compat = new FluidityIronChests();
			compat.init();
		}
		/*if(canLoadModule("Thaumcraft") && canLoadModule("frt")){
			compat = new ThaumcraftFRT();
			compat.init();
		}*/
		MinecraftForge.EVENT_BUS.register(this);
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		IModCompat compat;
		if(canLoadModule("adobeblocks") && canLoadModule("cookingforblockheads")){
			compat = new AdobeBlocksCookingForBlockheads();
			compat.postInit();
		}
		if(canLoadModule("cookingforblockheads") && canLoadModule("frt")){
			compat = new CookingForBlockheadsFRT();
			compat.postInit();
		}
	}
	private void overrideDescription(ModMetadata meta){
		String mods = "";
		for(String mid:supportedMods)
			if(Loader.isModLoaded(mid))
				for(ModContainer mod:Loader.instance().getActiveModList())
					if(mid.equals(mod.getModId()))
						mods += "\n"+(canLoadModule(mid) ? "" : TextFormatting.RED)+mod.getName()+(canLoadModule(mid) ? "" : TextFormatting.WHITE);
		if(mods.equals(""))
			mods = mods.concat("\n"+ TextFormatting.RED+"none");
		meta.description = I18n.translateToLocal("fluidity.desc.line1")+"\n"
				+ TextFormatting.GREEN + I18n.translateToLocal("fluidity.desc.line2") + TextFormatting.WHITE
				+ mods;
	}

	public static boolean canLoadModule(String modid){
		return Loader.isModLoaded(modid) && !ArrayUtils.contains(ConfigValues.DISABLEDCOMPAT, modid);
	}

	@SubscribeEvent
	public void configChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.getModID().equals(MODID))
			syncConfig();
	}
}
