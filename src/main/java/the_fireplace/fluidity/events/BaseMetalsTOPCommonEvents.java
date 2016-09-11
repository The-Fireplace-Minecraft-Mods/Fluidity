package the_fireplace.fluidity.events;

import cyano.basemetals.init.Achievements;
import cyano.basemetals.init.Items;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemArmor;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.fluidity.compat.BaseMetalsTOP;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author The_Fireplace
 */
public class BaseMetalsTOPCommonEvents {
    private static final ResourceLocation waterBreathingPotionKey = new ResourceLocation("water_breathing");
    private static final ResourceLocation waterBuffPotionKey = new ResourceLocation("resistance");
    private static final ResourceLocation fatiguePotionKey = new ResourceLocation("mining_fatigue");
    private static final ResourceLocation fireproofPotionKey = new ResourceLocation("fire_resistance");
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if(!(event.getEntityLiving() instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        if(player.inventory.armorInventory[0] == null || player.inventory.armorInventory[1] == null || player.inventory.armorInventory[2] == null || player.inventory.armorInventory[3] == null)
            return;
        PotionEffect waterBreathing;
        if(!(player.inventory.armorInventory[3].getItem() instanceof ItemArmor))
            return;
        ItemArmor armorItem = (ItemArmor)player.inventory.armorInventory[3].getItem();
        World w = player.worldObj;
        if(armorItem == BaseMetalsTOP.coldironHelmetProbe && player.inventory.armorInventory[2] != null && player.inventory.armorInventory[2].getItem() == Items.coldiron_chestplate && player.inventory.armorInventory[1] != null && player.inventory.armorInventory[1].getItem() == Items.coldiron_leggings && player.inventory.armorInventory[0] != null && player.inventory.armorInventory[0].getItem() == Items.coldiron_boots) {
            PotionEffect b11 = new PotionEffect(Potion.REGISTRY.getObject(fireproofPotionKey), 45, 0, false, false);
            player.addPotionEffect(b11);
            if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == Items.coldiron_sword) {
                player.addStat(Achievements.demon_slayer, 1);
            }
        }

        if(armorItem == BaseMetalsTOP.mithrilHelmetProbe && player.inventory.armorInventory[2] != null && player.inventory.armorInventory[2].getItem() == Items.mithril_chestplate && player.inventory.armorInventory[1] != null && player.inventory.armorInventory[1].getItem() == Items.mithril_leggings && player.inventory.armorInventory[0] != null && player.inventory.armorInventory[0].getItem() == Items.mithril_boots) {
            LinkedList b12 = new LinkedList();
            Iterator b22 = player.getActivePotionEffects().iterator();

            Potion protection;
            while(b22.hasNext()) {
                waterBreathing = (PotionEffect)b22.next();
                protection = waterBreathing.getPotion();
                if(protection.isBadEffect()) {
                    b12.add(protection);
                }
            }

            Iterator waterBreathing1 = b12.iterator();

            while(waterBreathing1.hasNext()) {
                protection = (Potion)waterBreathing1.next();
                player.removePotionEffect(protection);
            }

            if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == Items.mithril_sword) {
                player.addStat(Achievements.angel_of_death, 1);
            }
        }

        if(armorItem == BaseMetalsTOP.aquariumHelmetProbe && player.posY > 0.0D && player.posY < 255.0D && player.inventory.armorInventory[2] != null && player.inventory.armorInventory[2].getItem() == Items.aquarium_chestplate && player.inventory.armorInventory[1] != null && player.inventory.armorInventory[1].getItem() == Items.aquarium_leggings && player.inventory.armorInventory[0] != null && player.inventory.armorInventory[0].getItem() == Items.aquarium_boots) {
            Block b13 = w.getBlockState(new BlockPos(player.posX, player.posY, player.posZ)).getBlock();
            Block b23 = w.getBlockState(new BlockPos(player.posX, player.posY + 1.0D, player.posZ)).getBlock();
            if(b13 == Blocks.WATER && b23 == Blocks.WATER) {
                waterBreathing = new PotionEffect(Potion.REGISTRY.getObject(waterBreathingPotionKey), 45, 0, false, false);
                player.addPotionEffect(waterBreathing);
                PotionEffect protection1 = new PotionEffect(Potion.REGISTRY.getObject(waterBuffPotionKey), 45, 0, false, false);
                player.addPotionEffect(protection1);
                player.removePotionEffect(Potion.REGISTRY.getObject(fatiguePotionKey));
                player.addStat(Achievements.scuba_diver, 1);
            }
        }
    }
}
