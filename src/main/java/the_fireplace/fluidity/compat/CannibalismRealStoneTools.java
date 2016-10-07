package the_fireplace.fluidity.compat;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sorazodia.cannibalism.items.manager.ItemList;
import the_fireplace.fluidity.tools.Registry;

public class CannibalismRealStoneTools implements IModCompat {

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		Registry.removeRecipe(new ItemStack(ItemList.stoneKnife));
		Registry.addRecipe(new ItemStack(ItemList.stoneKnife), " r", "s ", 'r', new ItemStack(Blocks.STONE), 's', "stickWood");
	}

	@Override
	public void postInit() {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerInvRenderers() {

	}

}