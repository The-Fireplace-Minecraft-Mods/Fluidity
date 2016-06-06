package the_fireplace.fluidity.events;

import net.blay09.mods.cookingforblockheads.api.event.FoodRegistryInitEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class CookingForBlockheadsFRTCommonEvents {
    @SubscribeEvent
    public void foodRegister(FoodRegistryInitEvent event){
        event.registerNonFoodRecipe(new ItemStack(FRT.blaze_cake));
    }
}
