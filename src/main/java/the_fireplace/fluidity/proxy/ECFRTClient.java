package the_fireplace.fluidity.proxy;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.cyclops.evilcraft.item.BloodWaxedCoalConfig;
import the_fireplace.fluidity.entity.projectile.EntityBloodWaxedCoal;
import the_fireplace.frt.client.renderers.AmmoRenderFactory;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class ECFRTClient implements ECFRTProxy {
    @Override
    public void register() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBloodWaxedCoal.class, new AmmoRenderFactory(BloodWaxedCoalConfig._instance.getItemInstance()));
    }
}
