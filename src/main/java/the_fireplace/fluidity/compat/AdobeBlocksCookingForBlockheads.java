package the_fireplace.fluidity.compat;

import net.minecraftforge.common.MinecraftForge;
import the_fireplace.fluidity.events.AdobeBlocksCookingForBlockheadsCommonEvents;

/**
 * @author The_Fireplace
 */
public class AdobeBlocksCookingForBlockheads implements IModCompat {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(new AdobeBlocksCookingForBlockheadsCommonEvents());
    }

    @Override
    public void registerInvRenderers() {

    }
}
