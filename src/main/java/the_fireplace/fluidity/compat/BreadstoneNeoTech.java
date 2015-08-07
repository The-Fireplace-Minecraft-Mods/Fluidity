package the_fireplace.fluidity.compat;

import com.Red.main.ingame.RedBlocks;
import com.dyonovan.neotech.crafting.OreProcessingRegistry;

import net.minecraft.item.ItemStack;

public class BreadstoneNeoTech implements IModCompat {

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		ItemStack flourStack5 = new ItemStack(FluidityBreadstone.flour, 5);
		ItemStack breadStoneStack = new ItemStack(RedBlocks.breadore);
		OreProcessingRegistry.addOreProcessingRecipe(breadStoneStack, flourStack5);
	}

	@Override
	public void registerInvRenderers() {

	}

}
