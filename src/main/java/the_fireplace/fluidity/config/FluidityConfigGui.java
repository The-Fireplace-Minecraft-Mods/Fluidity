package the_fireplace.fluidity.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.Fluidity;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class FluidityConfigGui extends GuiConfig {
    public FluidityConfigGui(GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(Fluidity.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Fluidity.MODID, true,
                true, GuiConfig.getAbridgedConfigPath(Fluidity.config.toString()));
    }
}
