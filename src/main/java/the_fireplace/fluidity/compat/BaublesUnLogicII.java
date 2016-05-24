package the_fireplace.fluidity.compat;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.events.BaublesUnLogicIIClientEvents;
import the_fireplace.fluidity.items.ItemCrystalAmulet;
import the_fireplace.fluidity.tools.Registry;
import the_fireplace.unlogicii.UnLogicII;

/**
 * @author The_Fireplace
 */
public class BaublesUnLogicII implements IModCompat {

	public static Item crystal_amulet = new ItemCrystalAmulet();

	@Override
	public void preInit() {
		if(Fluidity.instance.isClient)
			MinecraftForge.EVENT_BUS.register(new BaublesUnLogicIIClientEvents());
		Registry.register(crystal_amulet);
	}

	@Override
	public void init() {
		Registry.addRecipe(new ItemStack(crystal_amulet), " l ", "l l", " c ", 'l', Items.leather, 'c', UnLogicII.crystal_eye);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerInvRenderers() {
		Registry.registerRender(crystal_amulet);
	}
}
