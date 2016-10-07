package the_fireplace.fluidity.network.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.compat.FluidityIronChests;
import the_fireplace.fluidity.client.tesr.TileEntityFluidityChestRenderer;
import the_fireplace.fluidity.enums.FluidityIronChestType;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class BMICClient implements BMICProxy {
    public void register(){
        Item chestItem = Item.getItemFromBlock(FluidityIronChests.fluidityChest);

        for(FluidityIronChestType type : FluidityIronChestType.values()) {
            ModelLoader.setCustomModelResourceLocation(chestItem, type.ordinal(), new ModelResourceLocation(chestItem.getRegistryName(), "variant=" + type.getName()));

            ClientRegistry.bindTileEntitySpecialRenderer(type.clazz, new TileEntityFluidityChestRenderer());
        }
    }
}
