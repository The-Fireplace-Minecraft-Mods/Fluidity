package the_fireplace.fluidity.compat;

import com.lothrazar.samscarbonpaper.ModCarbon;

import cyano.basemetals.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseMetalsCarbonPaper implements IModCompat {

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		ItemStack carbonDustStack = new ItemStack(Items.carbon_powder);
		ItemStack paperStack = new ItemStack(net.minecraft.init.Items.paper);
		ItemStack carbonPaperStack8 = new ItemStack(ModCarbon.carbon_paper, 8);
		GameRegistry.addRecipe(carbonPaperStack8, "ppp", "pcp", "ppp", 'p', paperStack, 'c', carbonDustStack);
	}

	@Override
	public void registerInvRenderers() {

	}

}
