package the_fireplace.fluidity.proxy;

import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.fluidity.enums.FluidityIronChestType;

/**
 * @author The_Fireplace
 */
public class FICCommon implements FICProxy {
    public void register(){
        for (FluidityIronChestType typ : FluidityIronChestType.values())
        {
            GameRegistry.registerTileEntityWithAlternatives(typ.clazz, "Fluidity." + typ.name(), typ.name());
        }
    }
}
