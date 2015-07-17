package the_fireplace.fluidity;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import the_fireplace.fluidity.compat.AdobeBlocksUnLogicII;
import the_fireplace.fluidity.compat.IModCompat;

/**
 * @author The_Fireplace
 */
@Mod(modid=Fluidity.MODID, name=Fluidity.MODNAME, version=Fluidity.VERSION)
public class Fluidity {
	public static final String MODID = "fluidity";
	public static final String MODNAME = "Fluidity";
	public static final String VERSION = "2.0.0.1";

	public static final CreativeTabs tabFluidity = new TabFluidity();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		IModCompat compat;
		if(Loader.isModLoaded("adobeblocks") && Loader.isModLoaded("unlogicii")){
			compat = new AdobeBlocksUnLogicII();
			compat.preInit();
		}
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
	}
}
