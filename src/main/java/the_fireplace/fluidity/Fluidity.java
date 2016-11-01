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
import the_fireplace.fluidity.network.proxy.CommonProxy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author The_Fireplace
 */
@Mod(modid=Fluidity.MODID, name=Fluidity.MODNAME, updateJSON = "http://thefireplace.bitnamiapp.com/jsons/fluidity.json", dependencies = "required-after:Forge@[12.18.1.2019,);after:*", guiFactory = "the_fireplace.fluidity.client.gui.FluidityConfigGuiFactory")
public class Fluidity {
	public static final String MODID = "fluidity";
	public static final String MODNAME = "Fluidity";
	public static String VERSION;
	public final ArrayList<String> supportedMods = Lists.newArrayList();
	public final List<IModCompat> compats = new LinkedList<>();
	@Instance(MODID)
	public static Fluidity instance;

	public int entityID = -1;

	@SidedProxy(clientSide = "the_fireplace.fluidity.network.proxy.ClientProxy", serverSide = "the_fireplace.fluidity.network.proxy.CommonProxy")
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
		supportedMods.add("cannibalism");
		supportedMods.add("cookingforblockheads");
		supportedMods.add("electricadvantage");
		supportedMods.add("EnderIO");//Remove in 1.11
		supportedMods.add("enderio");
		supportedMods.add("evilcraft");
		supportedMods.add("frt");
		supportedMods.add("invtweaks");
		supportedMods.add("IronChest");//Remove in 1.11
		supportedMods.add("ironchest");
		supportedMods.add("randomthings");
		supportedMods.add("realstonetools");
		//supportedMods.add("Thaumcraft");
		supportedMods.add("theoneprobe");
		supportedMods.add("xreliquary");
	}

	public void addCompats(){
		if(canLoadModule("actuallyadditions") && canLoadModule("basemetals"))
			compats.add(new ActuallyAdditionsBaseMetals());
		if(canLoadModule("actuallyadditions") && (canLoadModule("EnderIO") || canLoadModule("enderio")))
			compats.add(new ActuallyAdditionsEnderIO());
		if(canLoadModule("actuallyadditions") && canLoadModule("randomthings"))
			compats.add(new ActuallyAdditionsRandomThings());
		if(canLoadModule("actuallyadditions") && canLoadModule("xreliquary"))
			compats.add(new ActuallyAdditionsReliquary());
		if(canLoadModule("adobeblocks") && canLoadModule("cookingforblockheads"))
			compats.add(new AdobeBlocksCookingForBlockheads());
		if(canLoadModule("adobeblocks") && canLoadModule("frt"))
			compats.add(new AdobeBlocksFRT());
		if(canLoadModule("basemetals") && canLoadModule("cannibalism"))
			compats.add(new BaseMetalsCannibalism());
		if(canLoadModule("basemetals") && (canLoadModule("EnderIO") || canLoadModule("enderio")))
			compats.add(new BaseMetalsEnderIO());
		if(canLoadModule("basemetals") && canLoadModule("moreanvils"))
			compats.add(new BaseMetalsMoreAnvils());
		if(canLoadModule("basemetals") && canLoadModule("theoneprobe"))
			compats.add(new BaseMetalsTOP());
		if((canLoadModule("BiomesOPlenty") || canLoadModule("biomesoplenty")) && canLoadModule("realstonetools"))
			compats.add(new BOPRealStoneTools());
		if(canLoadModule("cannibalism") && canLoadModule("realstonetools"))
			compats.add(new CannibalismRealStoneTools());
		//if(canLoadModule("cannibalism") && canLoadModule("Thaumcraft"))
			//compats.add(new CannibalismThaumcraft());
		if(canLoadModule("cookingforblockheads") && canLoadModule("frt"))
			compats.add(new CookingForBlockheadsFRT());
		if(canLoadModule("electricadvantage") && (canLoadModule("EnderIO") || canLoadModule("enderio")))
			compats.add(new ElectricAdvantageEnderIO());
		if(canLoadModule("evilcraft") && canLoadModule("frt"))
			compats.add(new EvilCraftFRT());
		if(canLoadModule("IronChest") || canLoadModule("ironchest"))
			compats.add(new FluidityIronChests());
		//if(canLoadModule("Thaumcraft") && canLoadModule("frt"))
			//compats.add(new ThaumcraftFRT());
		if(canLoadModule("actuallyadditions"))
			compats.add(new FluidityActuallyAdditions());
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
		addCompats();

		compats.forEach(compat -> compat.preInit());

		if(event.getSide().isClient()) {
			compats.forEach(compat -> compat.registerInvRenderers());
			overrideDescription(event.getModMetadata());
		}
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		compats.forEach(compat -> compat.init());
		MinecraftForge.EVENT_BUS.register(this);
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		compats.forEach(compat -> compat.postInit());
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
