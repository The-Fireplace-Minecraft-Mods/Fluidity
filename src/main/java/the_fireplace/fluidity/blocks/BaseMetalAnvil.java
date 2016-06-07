package the_fireplace.fluidity.blocks;

import cyano.basemetals.init.Materials;
import cyano.basemetals.material.MetalMaterial;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.moreanvils.blocks.MaterialAnvil;

/**
 * @author The_Fireplace
 */
public class BaseMetalAnvil extends MaterialAnvil {
    public MetalMaterial metalMaterial;
    public BaseMetalAnvil(MetalMaterial material){
        super(Materials.getArmorMaterialFor(material));
        setCreativeTab(Fluidity.tabFluidity);
        setUnlocalizedName(material.getName()+"_anvil");
        setRegistryName(material.getName()+"_anvil");
        this.metalMaterial = material;
    }
}
