package the_fireplace.fluidity.items;

import ljfa.glassshards.GlassShards;
import net.minecraft.item.ItemSword;
import the_fireplace.fluidity.Fluidity;

public class AdobeGlassSword extends ItemSword {

	public AdobeGlassSword() {
		super(GlassShards.toolMatGlass);
		setUnlocalizedName("adobe_glass_sword");
		setCreativeTab(Fluidity.tabFluidity);
	}

}
