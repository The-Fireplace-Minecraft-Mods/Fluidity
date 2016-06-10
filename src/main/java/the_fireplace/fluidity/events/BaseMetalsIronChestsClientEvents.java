package the_fireplace.fluidity.events;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.enums.BaseMetalsIronChestType;

/**
 * @author The_Fireplace
 */
public class BaseMetalsIronChestsClientEvents {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPreTextureStiching(TextureStitchEvent.Pre event) {
        if(event.getMap() == FMLClientHandler.instance().getClient().getTextureMapBlocks()) {
            BaseMetalsIronChestType[] chestTypes = BaseMetalsIronChestType.VALUES;
            int chestTypeCount = chestTypes.length;

            for(int i = 0; i < chestTypeCount; ++i) {
                BaseMetalsIronChestType t = chestTypes[i];
                event.getMap().registerSprite(new ResourceLocation(t.getBreakTexture()));
            }
        }

    }
}
