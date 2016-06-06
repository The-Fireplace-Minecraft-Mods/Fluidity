package the_fireplace.fluidity.events;

import net.blay09.mods.cookingforblockheads.block.ModBlocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.adobeblocks.AdobeBlocks;

/**
 * @author The_Fireplace
 */
public class AdobeBlocksCookingForBlockheadsCommonEvents {
    @SubscribeEvent
    public void blockRightClick(PlayerInteractEvent.RightClickBlock event){
        if(event.getItemStack().getItem() == AdobeBlocks.adobe_capsule && event.getEntityLiving().getEntityWorld().getBlockState(event.getPos()).getBlock() == ModBlocks.sink){
            event.getEntityLiving().setItemStackToSlot(event.getHand() == EnumHand.MAIN_HAND ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND, new ItemStack(AdobeBlocks.filled_adobe_capsule, event.getItemStack().stackSize));
        }
    }
}
