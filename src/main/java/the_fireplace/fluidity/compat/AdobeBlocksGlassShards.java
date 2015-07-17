package the_fireplace.fluidity.compat;

import ljfa.glassshards.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.items.AdobeGlassSword;

public class AdobeBlocksGlassShards implements IModCompat {

	public static final Item adobe_glass_sword = new AdobeGlassSword();
	public static final Item adobe_glass_shard = new Item().setUnlocalizedName("adobe_glass_shard").setCreativeTab(Fluidity.tabFluidity);

	@Override
	public void preInit() {
		if(Config.enableSword)
			GameRegistry.registerItem(adobe_glass_sword, "adobe_glass_sword");
		GameRegistry.registerItem(adobe_glass_shard, "adobe_glass_shard");
	}

	@Override
	public void init() {
		OreDictionary.registerOre("shardsGlass", adobe_glass_shard);
		ItemStack s = new ItemStack(adobe_glass_sword);
		ItemStack b = new ItemStack(adobe_glass_shard);
		GameRegistry.addRecipe(new ShapedOreRecipe(s, "b", "b", "s", 'b', b, 's', "stickWood"));
	}

	@Override
	public void registerInvRenderers() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(adobe_glass_shard, 0, new ModelResourceLocation("fluidity:adobe_glass_shard", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(adobe_glass_sword, 0, new ModelResourceLocation("fluidity:adobe_glass_sword", "inventory"));
	}
}
