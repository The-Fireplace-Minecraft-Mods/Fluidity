package the_fireplace.fluidity.compat;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import team.chisel.common.init.ChiselBlocks;
import the_fireplace.fluidity.tools.Registry;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.tweaks.feature.StairsMakeMore;
import vazkii.quark.world.QuarkWorld;
import vazkii.quark.world.feature.Basalt;
import vazkii.quark.world.feature.RevampStoneGen;

/**
 * @author The_Fireplace
 */
public class ChiselQuark implements IModCompat {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        if(ModuleLoader.isModuleEnabled(QuarkWorld.class)) {
            if(ModuleLoader.isFeatureEnabled(Basalt.class)) {
                if(ChiselBlocks.basaltextra != null) {
                    OreDictionary.registerOre("stoneBasalt", new ItemStack(ChiselBlocks.basaltextra, 1, 7));
                    Block stairs = GameRegistry.findRegistry(Block.class).getValue(new ResourceLocation("Quark", "stone_basalt_stairs"));
                    if (stairs != null) {
                        Registry.addRecipe(new ItemStack(stairs, ModuleLoader.isFeatureEnabled(StairsMakeMore.class) ? 8 : 4), "b  ", "bb ", "bbb", 'b', "stoneBasalt");
                    }
                }else{
                    System.out.println("basaltextra is null, not taking action");
                }
            }
            if(ModuleLoader.isFeatureEnabled(RevampStoneGen.class)){
                if(ChiselBlocks.marbleextra != null) {
                    OreDictionary.registerOre("stoneMarble", new ItemStack(ChiselBlocks.marbleextra, 1, 7));
                    Block stairs = GameRegistry.findRegistry(Block.class).getValue(new ResourceLocation("Quark", "stone_marble_stairs"));
                    if (stairs != null) {
                        Registry.addRecipe(new ItemStack(stairs, ModuleLoader.isFeatureEnabled(StairsMakeMore.class) ? 8 : 4), "b  ", "bb ", "bbb", 'b', "stoneMarble");
                    }
                }else{
                    System.out.println("marbleextra is null, not taking action");
                }

                if(ChiselBlocks.limestoneextra != null) {
                    OreDictionary.registerOre("stoneLimestone", new ItemStack(ChiselBlocks.limestoneextra, 1, 7));
                    Block stairs = GameRegistry.findRegistry(Block.class).getValue(new ResourceLocation("Quark", "stone_limestone_stairs"));
                    if (stairs != null) {
                        Registry.addRecipe(new ItemStack(stairs, ModuleLoader.isFeatureEnabled(StairsMakeMore.class) ? 8 : 4), "b  ", "bb ", "bbb", 'b', "stoneLimestone");
                    }
                }else{
                    System.out.println("limestoneextra is null, not taking action");
                }
            }
        }
    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {

    }
}
