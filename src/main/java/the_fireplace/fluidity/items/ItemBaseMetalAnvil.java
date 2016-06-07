package the_fireplace.fluidity.items;

import cyano.basemetals.material.MetalMaterial;
import net.minecraft.block.Block;
import net.minecraft.item.ItemAnvilBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import the_fireplace.fluidity.blocks.BaseMetalAnvil;

/**
 * @author The_Fireplace
 */
public class ItemBaseMetalAnvil extends ItemAnvilBlock {
    public MetalMaterial material;

    public ItemBaseMetalAnvil(Block block, ResourceLocation loc) {
        super(block);
        material = ((BaseMetalAnvil)block).metalMaterial;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        switch(stack.getItemDamage()) {
            case 0:
                return String.format(I18n.translateToLocal("fluidity.anvil"), I18n.translateToLocal("fluidity." + material.getName()));
            case 1:
                return String.format(I18n.translateToLocal("fluidity.anvil.slightly_damaged"), I18n.translateToLocal("fluidity."+material.getName()));
            case 2:
                return String.format(I18n.translateToLocal("fluidity.anvil.very_damaged"), I18n.translateToLocal("fluidity."+material.getName()));
            default:
                return String.format(I18n.translateToLocal("fluidity.anvil"), I18n.translateToLocal("fluidity."+material.getName()));
        }
    }
}
