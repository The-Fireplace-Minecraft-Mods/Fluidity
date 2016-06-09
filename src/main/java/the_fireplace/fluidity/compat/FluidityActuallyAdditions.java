package the_fireplace.fluidity.compat;

import de.ellpeck.actuallyadditions.mod.misc.cloud.ISmileyCloudEasterEgg;
import de.ellpeck.actuallyadditions.mod.util.AssetUtil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static de.ellpeck.actuallyadditions.mod.misc.cloud.SmileyCloudEasterEggs.cloudStuff;

/**
 * @author The_Fireplace
 */
public class FluidityActuallyAdditions implements IModCompat {
    @Override
    public void preInit() {
        register(new ISmileyCloudEasterEgg(){
            @Override
            public String[] getTriggerNames(){
                return new String[]{"the_fireplace", "fireplace"};
            }

            @Override
            public void renderExtra(float f){
                renderHoldingItem(false, new ItemStack(Items.BLAZE_ROD));
                renderHoldingItem(true, new ItemStack(Items.FIRE_CHARGE));
                renderHeadBlock(Blocks.OBSIDIAN, 0);
            }
        });
        register(new ISmileyCloudEasterEgg(){
            @Override
            public String[] getTriggerNames(){
                return new String[]{"djacob", "aboose"};
            }

            @Override
            public void renderExtra(float f){
                renderHoldingItem(false, new ItemStack(Items.WOODEN_AXE));
                renderHeadBlock(Blocks.BEDROCK, 0);
            }
        });
    }

    public static void register(ISmileyCloudEasterEgg egg){
        cloudStuff.add(egg);
    }

    public static void renderHoldingItem(boolean leftHand, ItemStack stack){
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180F, 0F, 0F, 1F);
        GlStateManager.rotate(90, 0, 1, 0);
        GlStateManager.translate(0.2, -1F, leftHand ? -0.525F : 0.525F);
        GlStateManager.scale(0.75F, 0.75F, 0.75F);

        AssetUtil.renderItemInWorld(stack);

        GlStateManager.popMatrix();
    }

    public static void renderHeadBlock(Block block, int meta){
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.translate(-0.015F, 0.625F, 0.04F);
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(180F, 1F, 0F, 0F);

        AssetUtil.renderBlockInWorld(block, meta);

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {

    }
}
