package the_fireplace.fluidity;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TabFluidity extends CreativeTabs {

	public TabFluidity() {
		super("fluidity");
	}

	@Override
	public Item getTabIconItem() {
		return Items.WATER_BUCKET;
	}

}
