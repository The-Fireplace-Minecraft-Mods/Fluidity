package the_fireplace.fluidity.compat;

import cyano.basemetals.init.Items;
import cyano.basemetals.init.Materials;
import cyano.basemetals.items.ItemMetalArmor;
import cyano.basemetals.material.MetalMaterial;
import mcjty.theoneprobe.items.AddProbeRecipe;
import mcjty.theoneprobe.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.events.BaseMetalsTOPCommonEvents;
import the_fireplace.fluidity.tools.Registry;

import java.util.List;

/**
 * @author The_Fireplace
 */
public class BaseMetalsTOP implements IModCompat {
    
    public static Item adamantineHelmetProbe;
    public static Item aquariumHelmetProbe;
    public static Item brassHelmetProbe;
    public static Item bronzeHelmetProbe;
    public static Item coldironHelmetProbe;
    public static Item copperHelmetProbe;
    public static Item cupronickelHelmetProbe;
    public static Item electrumHelmetProbe;
    public static Item invarHelmetProbe;
    public static Item leadHelmetProbe;
    public static Item mithrilHelmetProbe;
    public static Item nickelHelmetProbe;
    public static Item platinumHelmetProbe;
    public static Item silverHelmetProbe;
    public static Item starsteelHelmetProbe;
    public static Item steelHelmetProbe;
    public static Item tinHelmetProbe;

    @Override
    public void preInit() {
        adamantineHelmetProbe = makeHelmet(Materials.adamantine, "adamantine_helmet_probe");
        aquariumHelmetProbe = makeHelmet(Materials.aquarium, "aquarium_helmet_probe");
        brassHelmetProbe = makeHelmet(Materials.brass, "brass_helmet_probe");
        bronzeHelmetProbe = makeHelmet(Materials.bronze, "bronze_helmet_probe");
        coldironHelmetProbe = makeHelmet(Materials.coldiron, "coldiron_helmet_probe");
        copperHelmetProbe = makeHelmet(Materials.copper, "copper_helmet_probe");
        cupronickelHelmetProbe = makeHelmet(Materials.cupronickel, "cupronickel_helmet_probe");
        electrumHelmetProbe = makeHelmet(Materials.electrum, "electrum_helmet_probe");
        invarHelmetProbe = makeHelmet(Materials.invar, "invar_helmet_probe");
        leadHelmetProbe = makeHelmet(Materials.lead, "lead_helmet_probe");
        mithrilHelmetProbe = makeHelmet(Materials.mithril, "mithril_helmet_probe");
        nickelHelmetProbe = makeHelmet(Materials.nickel, "nickel_helmet_probe");
        platinumHelmetProbe = makeHelmet(Materials.platinum, "platinum_helmet_probe");
        silverHelmetProbe = makeHelmet(Materials.silver, "silver_helmet_probe");
        starsteelHelmetProbe = makeHelmet(Materials.starsteel, "starsteel_helmet_probe");
        steelHelmetProbe = makeHelmet(Materials.steel, "steel_helmet_probe");
        tinHelmetProbe = makeHelmet(Materials.tin, "tin_helmet_probe");
    }

    @Override
    public void init() {
        addProbeRecipe(Items.adamantine_helmet, adamantineHelmetProbe);
        addProbeRecipe(Items.aquarium_helmet, aquariumHelmetProbe);
        addProbeRecipe(Items.brass_helmet, brassHelmetProbe);
        addProbeRecipe(Items.bronze_helmet, bronzeHelmetProbe);
        addProbeRecipe(Items.coldiron_helmet, coldironHelmetProbe);
        addProbeRecipe(Items.copper_helmet, copperHelmetProbe);
        addProbeRecipe(Items.cupronickel_helmet, cupronickelHelmetProbe);
        addProbeRecipe(Items.electrum_helmet, electrumHelmetProbe);
        addProbeRecipe(Items.invar_helmet, invarHelmetProbe);
        addProbeRecipe(Items.lead_helmet, leadHelmetProbe);
        addProbeRecipe(Items.mithril_helmet, mithrilHelmetProbe);
        addProbeRecipe(Items.nickel_helmet, nickelHelmetProbe);
        addProbeRecipe(Items.platinum_helmet, platinumHelmetProbe);
        addProbeRecipe(Items.silver_helmet, silverHelmetProbe);
        addProbeRecipe(Items.starsteel_helmet, starsteelHelmetProbe);
        addProbeRecipe(Items.steel_helmet, steelHelmetProbe);
        addProbeRecipe(Items.tin_helmet, tinHelmetProbe);

        MinecraftForge.EVENT_BUS.register(new BaseMetalsTOPCommonEvents());
    }

    @Override
    public void postInit() {

    }

    public static void addProbeRecipe(Item helmet, Item output){
        GameRegistry.addRecipe(new AddProbeRecipe(helmet, output));
    }

    @Override
    public void registerInvRenderers() {
        Registry.registerRender(adamantineHelmetProbe);
        Registry.registerRender(aquariumHelmetProbe);
        Registry.registerRender(brassHelmetProbe);
        Registry.registerRender(bronzeHelmetProbe);
        Registry.registerRender(coldironHelmetProbe);
        Registry.registerRender(copperHelmetProbe);
        Registry.registerRender(cupronickelHelmetProbe);
        Registry.registerRender(electrumHelmetProbe);
        Registry.registerRender(invarHelmetProbe);
        Registry.registerRender(leadHelmetProbe);
        Registry.registerRender(mithrilHelmetProbe);
        Registry.registerRender(nickelHelmetProbe);
        Registry.registerRender(platinumHelmetProbe);
        Registry.registerRender(silverHelmetProbe);
        Registry.registerRender(starsteelHelmetProbe);
        Registry.registerRender(steelHelmetProbe);
        Registry.registerRender(tinHelmetProbe);
    }

    public static Item makeHelmet(MetalMaterial metal, String name) {
        ItemArmor.ArmorMaterial material = Materials.getArmorMaterialFor(metal);
        ItemMetalArmor item = new ItemMetalArmor(metal, material, -1, EntityEquipmentSlot.HEAD) {
            @Override
            public boolean getHasSubtypes() {
                return true;
            }

            @Override
            public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
                ItemStack stack = new ItemStack(itemIn);
                NBTTagCompound tag = new NBTTagCompound();
                tag.setInteger(ModItems.PROBETAG, 1);
                stack.setTagCompound(tag);
                subItems.add(stack);
            }

            @Override
            public String getItemStackDisplayName(ItemStack stack)
            {
                try {
                    return I18n.translateToLocal("item.basemetals." + ((ItemArmor) stack.getItem()).getArmorMaterial().getName() + "_helmet.name") + " " + I18n.translateToLocal("fluidity.with_probe");
                } catch(NoSuchMethodError e){
                    return "";
                }
            }

            @Override
            @SideOnly(Side.CLIENT)
            public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
            {
                return "fluidity:textures/armor/"+((ItemArmor)stack.getItem()).getArmorMaterial().getName()+"_layer_1.png";
            }
        };
        item.setUnlocalizedName(name);
        item.setRegistryName(name);
        item.setCreativeTab(Fluidity.tabFluidity);
        GameRegistry.register(item);
        return item;
    }
}
