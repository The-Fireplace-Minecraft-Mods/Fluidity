package the_fireplace.fluidity.compat;

import com.google.common.collect.Maps;
import cyano.basemetals.init.Materials;
import cyano.basemetals.material.MetalMaterial;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.blocks.BaseMetalAnvil;
import the_fireplace.fluidity.client.events.BaseMetalsMoreAnvilsClientEvents;
import the_fireplace.fluidity.items.ItemBaseMetalAnvil;
import the_fireplace.fluidity.tools.Registry;

import java.util.Collection;
import java.util.Map;

/**
 * @author The_Fireplace
 */
public class BaseMetalsMoreAnvils implements IModCompat {

    private static Map<MetalMaterial, Block> anvils = Maps.newHashMap();

    private static Collection<MetalMaterial> materials;

    private void buildAnvils(){
        for(MetalMaterial mat: materials){
            Block anvil = new BaseMetalAnvil(mat);
            anvils.put(mat, anvil);
        }
    }

    private void registerAnvils(){
        for(MetalMaterial mat: materials){
            GameRegistry.registerBlock(anvils.get(mat), ItemBaseMetalAnvil.class);
        }
    }

    private void registerAnvilRenderers(){
        for(MetalMaterial mat: materials){
            registerAnvilRenderer(anvils.get(mat));
        }
    }

    private void addAnvilRecipes(){
        for(MetalMaterial mat: materials){
            addAnvilRecipe(mat, "block"+mat.getCapitalizedName(), "ingot"+mat.getCapitalizedName());
        }
    }

    private void addAnvilRecipe(MetalMaterial mat, String blockName, String ingotName){
        Registry.addRecipe(new ItemStack(anvils.get(mat)), "bbb", " i ", "iii", 'b', blockName, 'i', ingotName);
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
        addAnvilRecipes();
        if(Fluidity.instance.isClient)
            MinecraftForge.EVENT_BUS.register(new BaseMetalsMoreAnvilsClientEvents());
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
        ModelLoader.registerItemVariants(Item.getItemFromBlock(anvil), new ModelResourceLocation("fluidity:" + anvil.getUnlocalizedName().substring(5) + "_intact", "inventory"), new ModelResourceLocation("fluidity:" + anvil.getUnlocalizedName().substring(5) + "_slightly_damaged", "inventory"), new ModelResourceLocation("fluidity:" + anvil.getUnlocalizedName().substring(5) + "_very_damaged", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(anvil), 0, new ModelResourceLocation("fluidity:" + anvil.getUnlocalizedName().substring(5) + "_intact", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(anvil), 1, new ModelResourceLocation("fluidity:" + anvil.getUnlocalizedName().substring(5) + "_slightly_damaged", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(anvil), 2, new ModelResourceLocation("fluidity:" + anvil.getUnlocalizedName().substring(5) + "_very_damaged", "inventory"));
    }
}
