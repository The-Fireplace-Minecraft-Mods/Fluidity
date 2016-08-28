package the_fireplace.fluidity;

import com.google.common.collect.Lists;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import the_fireplace.fluidity.compat.*;
import the_fireplace.fluidity.proxy.CommonProxy;

import java.util.ArrayList;

/**
 * @author The_Fireplace
 */
@Mod(modid=Fluidity.MODID, name=Fluidity.MODNAME, updateJSON = "http://thefireplace.bitnamiapp.com/jsons/fluidity.json", dependencies = "required-after:Forge@[12.18.1.2019,);after:*")
public class Fluidity {
	public static final String MODID = "fluidity";
	public static final String MODNAME = "Fluidity";
	public static String VERSION;
	public final ArrayList<String> supportedMods = Lists.newArrayList();
	@Instance(MODID)
	public static Fluidity instance;

	@SidedProxy(clientSide = "the_fireplace.fluidity.proxy.ClientProxy", serverSide = "the_fireplace.fluidity.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final CreativeTabs tabFluidity = new TabFluidity();

	public boolean isClient=false;

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
		supportedMods.add("frt");
		supportedMods.add("invtweaks");
		supportedMods.add("IronChest");//remove in 1.11
		supportedMods.add("ironchest");
		supportedMods.add("moreanvils");
		supportedMods.add("randomthings");
		supportedMods.add("realstonetools");
		supportedMods.add("theoneprobe");
		supportedMods.add("xreliquary");
		//supportedMods.add("Thaumcraft");
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		if(event.getSide().isClient())
			isClient=true;
		addSupported();
		IModCompat compat;

		if(Loader.isModLoaded("actuallyadditions") && Loader.isModLoaded("randomthings")){
			compat = new ActuallyAdditionsRandomThings();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		if(Loader.isModLoaded("adobeblocks") && Loader.isModLoaded("frt")){
			compat = new AdobeBlocksFRT();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		/*if(Loader.isModLoaded("basemetals") && Loader.isModLoaded("cannibalism")){
			compat = new BaseMetalsCannibalism();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}*/
		if(Loader.isModLoaded("basemetals") && Loader.isModLoaded("moreanvils")){
			compat = new BaseMetalsMoreAnvils();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		if(Loader.isModLoaded("basemetals") && Loader.isModLoaded("theoneprobe")){
			compat = new BaseMetalsTOP();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		/*if(Loader.isModLoaded("cannibalism") && Loader.isModLoaded("Thaumcraft")){
			compat = new CannibalismThaumcraft();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}*/
		if(Loader.isModLoaded("IronChest") || Loader.isModLoaded("ironchest")){
			compat = new FluidityIronChests();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}
		/*if(Loader.isModLoaded("Thaumcraft") && Loader.isModLoaded("frt")){
			compat = new ThaumcraftFRT();
			compat.preInit();
			if(event.getSide().isClient())
				compat.registerInvRenderers();
		}*/
		if(event.getSide().isClient())
			overrideDescription(event.getModMetadata());

		if(Loader.isModLoaded("actuallyadditions")){
			compat = new FluidityActuallyAdditions();
			compat.preInit();
		}
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		IModCompat compat;
		if(Loader.isModLoaded("actuallyadditions") && Loader.isModLoaded("basemetals")){
			compat = new ActuallyAdditionsBaseMetals();
			compat.init();
		}
		if(Loader.isModLoaded("actuallyadditions") && (Loader.isModLoaded("EnderIO") || Loader.isModLoaded("enderio"))){
			compat = new ActuallyAdditionsEnderIO();
			compat.init();
		}
		if(Loader.isModLoaded("actuallyadditions") && Loader.isModLoaded("randomthings")){
			compat = new ActuallyAdditionsRandomThings();
			compat.init();
		}
		if(Loader.isModLoaded("actuallyadditions") && Loader.isModLoaded("xreliquary")){
			compat = new ActuallyAdditionsReliquary();
			compat.init();
		}
		if(Loader.isModLoaded("adobeblocks") && Loader.isModLoaded("frt")){
			compat = new AdobeBlocksFRT();
			compat.init();
		}
		/*if(Loader.isModLoaded("basemetals") && Loader.isModLoaded("cannibalism")){
			compat = new BaseMetalsCannibalism();
			compat.init();
		}*/
		if(Loader.isModLoaded("basemetals") && (Loader.isModLoaded("EnderIO") || Loader.isModLoaded("enderio"))){
			compat = new BaseMetalsEnderIO();
			compat.init();
		}
		if(Loader.isModLoaded("basemetals") && Loader.isModLoaded("moreanvils")){
			compat = new BaseMetalsMoreAnvils();
			compat.init();
		}
		if(Loader.isModLoaded("basemetals") && Loader.isModLoaded("theoneprobe")){
			compat = new BaseMetalsTOP();
			compat.init();
		}
		if((Loader.isModLoaded("BiomesOPlenty") || Loader.isModLoaded("biomesoplenty")) && Loader.isModLoaded("realstonetools")){
			compat = new BOPRealStoneTools();
			compat.init();
		}
		/*if(Loader.isModLoaded("cannibalism") && Loader.isModLoaded("realstonetools")){
			compat = new CannibalismRealStoneTools();
			compat.init();
		}*/
		/*if(Loader.isModLoaded("cannibalism") && Loader.isModLoaded("Thaumcraft")){
			compat = new CannibalismThaumcraft();
			compat.init();
		}*/
		if(Loader.isModLoaded("electricadvantage") && (Loader.isModLoaded("EnderIO") || Loader.isModLoaded("enderio"))){
			compat = new ElectricAdvantageEnderIO();
			compat.init();
		}
		if((Loader.isModLoaded("EnderIO") || Loader.isModLoaded("enderio")) && Loader.isModLoaded("frt")){
			compat = new EnderIOFRT();
			compat.init();
		}
		if(Loader.isModLoaded("IronChest") || Loader.isModLoaded("ironchest")){
			compat = new FluidityIronChests();
			compat.init();
		}
		/*if(Loader.isModLoaded("Thaumcraft") && Loader.isModLoaded("frt")){
			compat = new ThaumcraftFRT();
			compat.init();
		}*/
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		IModCompat compat;
		if(Loader.isModLoaded("adobeblocks") && Loader.isModLoaded("cookingforblockheads")){
			compat = new AdobeBlocksCookingForBlockheads();
			compat.postInit();
		}
		if(Loader.isModLoaded("cookingforblockheads") && Loader.isModLoaded("frt")){
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
						mods += "\n"+mod.getName();
		if(mods.equals(""))
			mods = mods.concat("\n"+ TextFormatting.RED+"none");
		meta.description = I18n.translateToLocal("fluidity.desc.line1")+"\n"
				+ TextFormatting.GREEN + I18n.translateToLocal("fluidity.desc.line2") + TextFormatting.WHITE
				+ mods;
	}
}
