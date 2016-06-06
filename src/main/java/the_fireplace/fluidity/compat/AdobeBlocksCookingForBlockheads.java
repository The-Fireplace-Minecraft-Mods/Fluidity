package the_fireplace.fluidity.compat;

import net.blay09.mods.cookingforblockheads.api.CookingForBlockheadsAPI;
import net.blay09.mods.cookingforblockheads.api.SinkHandler;
import net.minecraft.item.ItemStack;
import the_fireplace.adobeblocks.AdobeBlocks;

/**
 * @author The_Fireplace
 */
public class AdobeBlocksCookingForBlockheads implements IModCompat {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {

    }

    SinkHandler capsuleHandler = new SinkHandler(){
        public ItemStack getSinkOutput(ItemStack input){
            return new ItemStack(AdobeBlocks.filled_adobe_capsule);
        }
    };

    @Override
    public void postInit() {
        CookingForBlockheadsAPI.addSinkHandler(new ItemStack(AdobeBlocks.adobe_capsule), capsuleHandler);
    }

    @Override
    public void registerInvRenderers() {

    }
}
