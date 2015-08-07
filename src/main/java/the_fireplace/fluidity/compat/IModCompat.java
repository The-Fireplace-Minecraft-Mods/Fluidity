package the_fireplace.fluidity.compat;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModCompat {
	public void preInit();
	public void init();
	@SideOnly(Side.CLIENT)
	public void registerInvRenderers();
}
