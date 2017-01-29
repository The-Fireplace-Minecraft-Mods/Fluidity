package the_fireplace.fluidity.compat;

import net.minecraft.block.BlockDispenser;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.cyclops.evilcraft.item.BloodWaxedCoalConfig;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.entity.projectile.EntityBloodWaxedCoal;
import the_fireplace.fluidity.events.DispenseBehaviorBloodWaxedCoal;
import the_fireplace.fluidity.network.proxy.ECFRTClient;
import the_fireplace.fluidity.network.proxy.ECFRTProxy;

/**
 * @author The_Fireplace
 */
public class EvilCraftFRT implements IModCompat {
    @Override
    public void preInit() {
        if(BloodWaxedCoalConfig._instance.isEnabled()) {
            EntityRegistry.registerModEntity(EntityBloodWaxedCoal.class, "ammo_blood_waxed_coal", ++Fluidity.instance.entityID, Fluidity.instance, 64, 10, true);
            BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BloodWaxedCoalConfig._instance.getItemInstance(), new DispenseBehaviorBloodWaxedCoal());

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
