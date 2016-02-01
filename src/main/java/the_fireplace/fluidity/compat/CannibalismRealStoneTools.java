package the_fireplace.fluidity.compat;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import sorazodia.cannibalism.items.manager.ItemList;
import the_fireplace.fluidity.tools.Registry;

public class CannibalismRealStoneTools implements IModCompat {

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		Registry.removeRecipe(new ItemStack(ItemList.stoneKnife));
		Registry.addRecipe(new ItemStack(ItemList.stoneKnife), " r", "s ", 'r', new ItemStack(Blocks.stone), 's', "stickWood");
	}

	@Override
	public void registerInvRenderers() {

	}

}
