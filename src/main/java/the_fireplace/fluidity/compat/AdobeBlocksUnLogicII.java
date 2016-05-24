package the_fireplace.fluidity.compat;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.adobeblocks.AdobeBlocks;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.tools.Registry;
import the_fireplace.unlogicii.items.internal.ItemPaxel;

public class AdobeBlocksUnLogicII implements IModCompat {

	public static final Item adobe_paxel = new ItemPaxel(AdobeBlocks.adobeTool).setUnlocalizedName("adobe_paxel").setCreativeTab(Fluidity.tabFluidity);

	@Override
	public void preInit() {
		Registry.register(adobe_paxel);
	}

	@Override
	public void init() {
		ItemStack adobePaxelStack = new ItemStack(adobe_paxel);
		ItemStack adobeStack = new ItemStack(AdobeBlocks.adobe_brick);
		ItemStack stoneStickStack = new ItemStack(AdobeBlocks.stone_stick);
		Registry.addRecipe(adobePaxelStack, "aaa", " a ", " s ", 'a', adobeStack, 's', stoneStickStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerInvRenderers() {
		Registry.registerRender(adobe_paxel);
	}

}
