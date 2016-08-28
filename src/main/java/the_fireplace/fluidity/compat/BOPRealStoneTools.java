package the_fireplace.fluidity.compat;

import biomesoplenty.api.item.BOPItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import the_fireplace.fluidity.tools.Registry;

/**
 * @author The_Fireplace
 */
public class BOPRealStoneTools implements IModCompat {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        Registry.removeRecipe(new ItemStack(BOPItems.stone_scythe));
        Registry.addRecipe(new ItemStack(BOPItems.stone_scythe), " MM", "M S", "  S", 'M', Blocks.STONE, 'S', "stickWood");
        Registry.addRecipe(new ItemStack(BOPItems.stone_scythe), "MM ", "S M", "S  ", 'M', Blocks.STONE, 'S', "stickWood");
    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {

    }
}
