package the_fireplace.fluidity.compat;

import net.minecraftforge.common.MinecraftForge;
import the_fireplace.fluidity.events.CookingForBlockheadsFRTCommonEvents;

/**
 * @author The_Fireplace
 */
public class CookingForBlockheadsFRT implements IModCompat {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {
        MinecraftForge.EVENT_BUS.register(new CookingForBlockheadsFRTCommonEvents());
    }

    @Override
    public void registerInvRenderers() {

    }
}
