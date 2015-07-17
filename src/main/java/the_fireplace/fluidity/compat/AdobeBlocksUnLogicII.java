package the_fireplace.fluidity.compat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.adobeblocks.AdobeBlocks;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.unlogicii.items.internal.ItemPaxel;

public class AdobeBlocksUnLogicII implements IModCompat {

	public static final Item adobe_paxel = new ItemPaxel(AdobeBlocks.adobeTool).setUnlocalizedName("adobe_paxel").setCreativeTab(Fluidity.tabFluidity);

	@Override
	public void preInit() {
		GameRegistry.registerItem(adobe_paxel, "adobe_paxel");
	}

	@Override
	public void init() {
		ItemStack adobePaxelStack = new ItemStack(adobe_paxel);
		ItemStack adobeStack = new ItemStack(AdobeBlocks.adobe_brick);
		ItemStack stoneStickStack = new ItemStack(AdobeBlocks.stone_stick);
		GameRegistry.addRecipe(adobePaxelStack, "aaa", " a ", " s ", 'a', adobeStack, 's', stoneStickStack);
	}

	@Override
	public void registerInvRenderers() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(adobe_paxel, 0, new ModelResourceLocation("fluidity:adobe_paxel", "inventory"));
	}

}
