package the_fireplace.fluidity.compat;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModCompat {
	void preInit();
	void init();
	@SideOnly(Side.CLIENT)
	void registerInvRenderers();
}
