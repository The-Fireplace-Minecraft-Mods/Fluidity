package the_fireplace.fluidity.compat;

import com.google.common.collect.Maps;
import cyano.basemetals.init.Materials;
import cyano.basemetals.material.MetalMaterial;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.moreanvils.blocks.BaseMetalAnvil;
import the_fireplace.moreanvils.blocks.MaterialAnvil;
import the_fireplace.moreanvils.item.ItemMaterialAnvil;

import java.util.Collection;
import java.util.Map;

/**
 * @author The_Fireplace
 */
@Deprecated
public class BaseMetalsMoreAnvils implements IModCompat {

    private static Map<MetalMaterial, MaterialAnvil> anvils = Maps.newHashMap();

    private static Collection<MetalMaterial> materials;

    private void buildAnvils(){
        for(MetalMaterial mat: materials){
            MaterialAnvil anvil = new BaseMetalAnvil(mat);
            anvils.put(mat, anvil);
        }
    }

    private void registerAnvils(){
        for(MetalMaterial mat: materials){
            GameRegistry.register(anvils.get(mat));
            GameRegistry.register(new ItemMaterialAnvil(anvils.get(mat)).setRegistryName(anvils.get(mat).getRegistryName()));
        }
    }

    private void registerAnvilRenderers(){
        for(MetalMaterial mat: materials){
            registerAnvilRenderer(anvils.get(mat));
        }
    }

    @Override
    public void preInit() {
        Materials.init();
        materials = Materials.getAllMetals();
        materials.remove(Materials.vanilla_diamond);
        materials.remove(Materials.vanilla_gold);
        materials.remove(Materials.vanilla_iron);
        materials.remove(Materials.vanilla_stone);
        materials.remove(Materials.vanilla_wood);
        materials.remove(Materials.zinc);
        buildAnvils();
        registerAnvils();
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void registerInvRenderers() {
        registerAnvilRenderers();
    }

    @SideOnly(Side.CLIENT)
    public void registerAnvilRenderer(Block anvil) {
        ModelLoader.registerItemVariants(Item.getItemFromBlock(anvil), new ModelResourceLocation("moreanvils:" + anvil.getUnlocalizedName().substring(5) + "_intact", "inventory"), new ModelResourceLocation("moreanvils:" + anvil.getUnlocalizedName().substring(5) + "_slightly_damaged", "inventory"), new ModelResourceLocation("moreanvils:" + anvil.getUnlocalizedName().substring(5) + "_very_damaged", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(anvil), 0, new ModelResourceLocation("moreanvils:" + anvil.getUnlocalizedName().substring(5) + "_intact", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(anvil), 1, new ModelResourceLocation("moreanvils:" + anvil.getUnlocalizedName().substring(5) + "_slightly_damaged", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(anvil), 2, new ModelResourceLocation("moreanvils:" + anvil.getUnlocalizedName().substring(5) + "_very_damaged", "inventory"));
    }
}
