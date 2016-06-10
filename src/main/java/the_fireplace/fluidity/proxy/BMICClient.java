package the_fireplace.fluidity.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.compat.BaseMetalsIronChests;
import the_fireplace.fluidity.entity.tile.renderer.TileEntityFluidityChestRenderer;
import the_fireplace.fluidity.enums.BaseMetalsIronChestType;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class BMICClient implements BMICProxy {
    public void register(){
        Item chestItem = Item.getItemFromBlock(BaseMetalsIronChests.fluidityChest);
        BaseMetalsIronChestType[] chestTypes = BaseMetalsIronChestType.values();
        int chestCount = chestTypes.length;

        int i;
        for(i = 0; i < chestCount; ++i) {
            BaseMetalsIronChestType type = chestTypes[i];
            ModelLoader.setCustomModelResourceLocation(chestItem, type.ordinal(), new ModelResourceLocation(chestItem.getRegistryName(), "variant=" + type.getName()));

            ClientRegistry.bindTileEntitySpecialRenderer(type.clazz, new TileEntityFluidityChestRenderer());
        }
    }
}
