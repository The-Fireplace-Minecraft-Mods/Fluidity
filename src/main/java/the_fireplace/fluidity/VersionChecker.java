package the_fireplace.fluidity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Set;

/**
 * This is the embeddable version checker I made. Designed for convenience, all you have to do is paste this class, change the HostMORNAME, HostMODID, HostVERSION, and how it retrieves the latest version and link to the download page.
 * This is a modified copy designed specifically for Fluidity's lack of a Curse page. Don't use this one normally.
 * @author The_Fireplace
 */
@Mod(modid=VersionChecker.MODID, name=VersionChecker.MODNAME, version=VersionChecker.VERSION, guiFactory = "the_fireplace."+VersionChecker.HostMODID+".VersionChecker$VCGui")
public class VersionChecker {
	protected static final String HostMODID= Fluidity.MODID;
	protected static final String HostMODNAME=Fluidity.MODNAME;
	protected static final String HostVERSION=Fluidity.VERSION;
	static final String MODID=HostMODID+"vc";
	static final String MODNAME=HostMODNAME+" Version Checker";
	static final String VERSION="1.1-fork";
	private String latest;
	private String downloadURL;

	private static Configuration config;
	private static Property FREQUENCY_PROPERTY;
	private static Property LASTCHECKED_PROPERTY;
	private static String freq;
	private static String lc;

	public VersionChecker(){
		latest = stringAt(Fluidity.LATEST);
		downloadURL = stringAt(Fluidity.DOWNLOADURL);
	}

	private void syncConfig(){
		freq = FREQUENCY_PROPERTY.getString();
		lc = LASTCHECKED_PROPERTY.getString();
		if(config.hasChanged())
			config.save();
	}

	private boolean canNotify(){
		if(freq.equals("Always"))
			return true;
		int[] date = new int[3];
		date[0] = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		date[1] = Calendar.getInstance().get(Calendar.MONTH);
		date[2] = Calendar.getInstance().get(Calendar.YEAR);
		if(freq.equals("Daily") && isNotToday(date))
			return true;
		if(freq.equals("Weekly")){
			for(int i=2;i>=0;i--){
				if(i != 0 && date[i] > splitVersion(lc)[i])
					return true;
				if(i == 0 && date[i]-7 > splitVersion(lc)[i])
					return true;
			}
		}
		return false;
	}

	private void tryNotifyClient(EntityPlayer player){
		if(!Loader.isModLoaded("VersionChecker") && isHigherVersion() && canNotify()){
			player.addChatMessage(new ChatComponentText("A new version of "+HostMODNAME+" is available!"));
			player.addChatMessage(new ChatComponentText("=========="+latest+"=========="));
			player.addChatMessage(new ChatComponentText("Get it at the following link:"));
			player.addChatMessage(new ChatComponentText(downloadURL).setChatStyle(new ChatStyle().setItalic(true).setUnderlined(true).setColor(EnumChatFormatting.BLUE).setChatClickEvent(new ClickEvent(Action.OPEN_URL, downloadURL))));
			setChecked();
		}
	}

	private void notifyServer(){
		System.out.println("Version "+latest+" of "+HostMODNAME+" is available!");
		System.out.println("Download it at "+downloadURL);
		setChecked();
	}

	private void setChecked(){
		LASTCHECKED_PROPERTY.set(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"."+Calendar.getInstance().get(Calendar.MONTH)+"."+Calendar.getInstance().get(Calendar.YEAR));
		syncConfig();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		config = new Configuration(new File(event.getModConfigurationDirectory(), "fireplace_update_checker.cfg"));
		config.load();
		FREQUENCY_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, "Frequency", "Always");
		FREQUENCY_PROPERTY.setValidValues(new String[]{"Always","Daily","Weekly"});
		LASTCHECKED_PROPERTY = config.get("hidden", "Last Checked", "0.0.0");
		syncConfig();
		event.getModMetadata().autogenerated=false;
		event.getModMetadata().modId=MODID;
		event.getModMetadata().name=MODNAME;
		event.getModMetadata().description="The version checker for "+HostMODNAME;
		event.getModMetadata().version=VERSION;
		event.getModMetadata().authorList.add("The_Fireplace");
	}
	@EventHandler
	public void init(FMLInitializationEvent event){
		tryNotifyDynious();
		if(event.getSide().isClient())
			MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void serverStarted(FMLServerStartedEvent event){
		if(event.getSide().isServer())
			tryNotifyServer();
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerJoinClient(final ClientConnectedToServerEvent event){
		(new Thread(){
			@Override
			public void run(){
				while(FMLClientHandler.instance().getClientPlayerEntity() == null)
					try{
						Thread.sleep(100);
					}catch(InterruptedException e){

					}
				tryNotifyClient(FMLClientHandler.instance().getClientPlayerEntity());
			}
		}).start();
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void configChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.modID.equals(MODID))
			syncConfig();
	}

	private boolean isHigherVersion(){
		try{
			final int[] _current = splitVersion(HostVERSION);
			final int[] _new = splitVersion(latest);

			for(int i=0;i<Math.max(_current.length, _new.length);i++){
				int curv=0;
				if(i < _current.length)
					curv = _current[i];
				int newv=0;
				if(i<_new.length)
					newv=_new[i];
				if(newv>curv)
					return true;
				else if(curv>newv)
					return false;
			}
			return false;
		}catch(NullPointerException e){
			return false;
		}
	}

	private boolean isNotToday(int[] date){
		final int[] _current = splitVersion(lc);

		for(int i=0;i<Math.max(_current.length, date.length);i++){
			int curv=0;
			if(i < _current.length)
				curv = _current[i];
			int newv=0;
			if(i<date.length)
				newv=date[i];
			if(newv>curv)
				return true;
			else if(curv>newv)
				return false;
		}
		return false;
	}

	private int[] splitVersion(String version){
		final String[] tmp = version.split("\\.");
		final int size=tmp.length;
		final int[] out = new int[size];
		for(int i=0;i<size;i++){
			out[i]=Integer.parseInt(tmp[i]);
		}
		return out;
	}

	private void tryNotifyServer(){
		if(isHigherVersion() && canNotify())
			notifyServer();
	}
	private void tryNotifyDynious(){
		if(isHigherVersion()){
			NBTTagCompound update = new NBTTagCompound();
			update.setString("modDisplayName", HostMODNAME);
			update.setString("oldVersion", HostVERSION);
			update.setString("newVersion", latest);
			update.setString("updateURL", downloadURL);
			update.setBoolean("isDirectLink", false);
			FMLInterModComms.sendRuntimeMessage(HostMODID, "VersionChecker", "addUpdate", update);
		}
	}

	private String stringAt(String url){
		String output = "";
		URLConnection con;
		try{
			con = new URL(url).openConnection();
			if(con != null){
				final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String input;
				StringBuilder buf = new StringBuilder();
				while((input=br.readLine()) != null){
					buf.append(input);
				}
				output = buf.toString();
				br.close();
			}
		}catch(MalformedURLException e){
			return "0.0.0.0";
		}catch(IOException e){
			return "0.0.0.0";
		}
		return output;
	}

	@SideOnly(Side.CLIENT)
	public static class VCGui implements IModGuiFactory {
		@Override
		public void initialize(Minecraft minecraftInstance) {
		}

		@Override
		public Class<? extends GuiScreen> mainConfigGuiClass() {
			return VCCG.class;
		}

		@Override
		public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
			return null;
		}

		@Override
		public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(
				IModGuiFactory.RuntimeOptionCategoryElement element) {
			return null;
		}

		public static class VCCG extends GuiConfig {
			public VCCG(GuiScreen parentScreen) {
				super(parentScreen, new ConfigElement(config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), MODID, false,
						false, GuiConfig.getAbridgedConfigPath(config.toString()));
			}
		}
	}
}