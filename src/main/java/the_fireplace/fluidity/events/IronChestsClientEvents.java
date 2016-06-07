package the_fireplace.fluidity.events;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.enums.FluidityIronChestType;

/**
 * @author The_Fireplace
 */
public class IronChestsClientEvents {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPreTextureStiching(TextureStitchEvent.Pre event) {
        if(event.getMap() == FMLClientHandler.instance().getClient().getTextureMapBlocks()) {
            FluidityIronChestType[] var2 = FluidityIronChestType.VALUES;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                FluidityIronChestType t = var2[var4];
                event.getMap().registerSprite(new ResourceLocation(t.getBreakTexture()));
            }
        }

    }
}
