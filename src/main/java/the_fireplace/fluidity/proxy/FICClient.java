package the_fireplace.fluidity.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.entity.tile.renderer.TileEntityFluidityChestRenderer;
import the_fireplace.fluidity.enums.FluidityIronChestType;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class FICClient implements FICProxy {
    public void register(){
        for (FluidityIronChestType typ : FluidityIronChestType.values())
        {
            GameRegistry.registerTileEntityWithAlternatives(typ.clazz, "Fluidity." + typ.name(), typ.name());
            ClientRegistry.bindTileEntitySpecialRenderer(typ.clazz, new TileEntityFluidityChestRenderer());
        }
    }
}
