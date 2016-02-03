package the_fireplace.fluidity.events;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.unlogicii.entity.living.ExtendedLivingBase;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class BaublesUnLogicIIClientEvents {
	byte i = 0;
	private final Map colormap = Maps.newHashMap();
	@SubscribeEvent
	public void renderTick(TickEvent.RenderTickEvent t){
		Minecraft mc = Minecraft.getMinecraft();
		if(mc != null)
			if(mc.thePlayer != null)
				if(mc.thePlayer.worldObj != null)
		if(i < 40){
			i++;
		}else{
			World world = mc.thePlayer.worldObj;
			AxisAlignedBB aabb = mc.thePlayer.getEntityBoundingBox().expand(8, 8, 8);
			List entities = world.getEntitiesWithinAABBExcludingEntity(mc.thePlayer, aabb);
			for (Object entity1 : entities) {
				Random rand = new Random();
				Entity entity = (Entity) entity1;
				if (!(entity instanceof EntityLivingBase)) {
					continue;
				}
				byte l;
				int k;
				if (entity instanceof EntityLiving) {
					l = ExtendedLivingBase.get((EntityLivingBase) entity).getSoulType();
					PotionEffect potioneffect;
					if (l == -1) {
						potioneffect = new PotionEffect(Potion.heal.id, 100000);
					} else if (l == 1) {
						potioneffect = new PotionEffect(Potion.moveSpeed.id, 100000);
					} else {
						potioneffect = new PotionEffect(Potion.invisibility.id, 100000);
					}
					colormap.clear();
					colormap.put(potioneffect.getPotionID(), potioneffect);
					k = PotionHelper.calcPotionLiquidColor(colormap.values());
				} else k = 0;
				double d0 = (k >> 16 & 255) / 255.0D;
				double d1 = (k >> 8 & 255) / 255.0D;
				double d2 = (k & 255) / 255.0D;
				mc.theWorld.spawnParticle(EnumParticleTypes.SPELL_MOB, entity.posX + (rand.nextDouble() - 0.5D) * entity.width, entity.posY + rand.nextDouble() * entity.height, entity.posZ + (rand.nextDouble() - 0.5D) * entity.width, d0, d1, d2, null);
				if (ExtendedLivingBase.get((EntityLivingBase) entity) != null)
					if (ExtendedLivingBase.get((EntityLivingBase) entity).getIsInfected()) {
						for (int f = 0; f < 24; f++) {
							mc.theWorld.spawnParticle(EnumParticleTypes.SPELL_MOB, entity.posX + (rand.nextDouble() - 0.5D) * entity.width, entity.posY + rand.nextDouble() * entity.height, entity.posZ + (rand.nextDouble() - 0.5D) * entity.width, d0, d1, d2, null);
						}
					}
			}
			i = 0;
		}
	}
}
