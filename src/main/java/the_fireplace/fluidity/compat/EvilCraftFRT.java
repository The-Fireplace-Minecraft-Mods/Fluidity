package the_fireplace.fluidity.compat;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.cyclops.evilcraft.item.BloodWaxedCoalConfig;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.entity.projectile.EntityBloodWaxedCoal;
import the_fireplace.fluidity.network.proxy.ECFRTClient;
import the_fireplace.fluidity.network.proxy.ECFRTProxy;
import the_fireplace.frt.api.AmmoRegistry;

/**
 * @author The_Fireplace
 */
public class EvilCraftFRT implements IModCompat {
    @Override
    public void preInit() {
        if(BloodWaxedCoalConfig._instance.isEnabled()) {
            EntityRegistry.registerModEntity(EntityBloodWaxedCoal.class, "ammo_blood_waxed_coal", ++Fluidity.instance.entityID, Fluidity.instance, 64, 10, true);
            AmmoRegistry.addAmmo(new ItemStack(BloodWaxedCoalConfig._instance.getItemInstance()), EntityBloodWaxedCoal.class);

            ECFRTProxy proxy;
            if(Fluidity.instance.isClient){
                proxy = new ECFRTClient();
                proxy.register();
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {

    }
}
