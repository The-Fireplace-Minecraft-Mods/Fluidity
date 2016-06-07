package the_fireplace.fluidity.events;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.fluidity.blocks.BaseMetalAnvil;
import the_fireplace.fluidity.gui.GuiBaseMetalsAnvil;
import the_fireplace.moreanvils.gui.GuiMaterialAnvil;

/**
 * @author The_Fireplace
 */
public class BaseMetalsMoreAnvilsClientEvents {
    @SubscribeEvent
    public void guiOpen(GuiOpenEvent event){
        if(event.getGui() instanceof GuiMaterialAnvil && !(event.getGui() instanceof GuiBaseMetalsAnvil) && ((GuiMaterialAnvil) event.getGui()).matAnv instanceof BaseMetalAnvil){
            event.setGui(new GuiBaseMetalsAnvil((GuiMaterialAnvil)event.getGui()));
        }
    }
}
