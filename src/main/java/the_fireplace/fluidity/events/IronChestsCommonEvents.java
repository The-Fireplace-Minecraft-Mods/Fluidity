package the_fireplace.fluidity.events;

import java.util.List;

import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.fluidity.entity.ai.FluidityIronChestOcelotSit;

public class IronChestsCommonEvents {
	@SubscribeEvent
	public void changeSittingTaskForOcelots(LivingEvent.LivingUpdateEvent evt)
	{
		if (evt.entityLiving.ticksExisted < 5 && evt.entityLiving instanceof EntityOcelot)
		{
			EntityOcelot ocelot = (EntityOcelot) evt.entityLiving;
			@SuppressWarnings("unchecked")
			List<EntityAITasks.EntityAITaskEntry> tasks = ocelot.tasks.taskEntries;

			for (EntityAITasks.EntityAITaskEntry task : tasks) {
				if (task.priority == 6 && (task.action instanceof EntityAIOcelotSit) && !(task.action instanceof FluidityIronChestOcelotSit)) {
					task.action = new FluidityIronChestOcelotSit(ocelot, 0.4F);
				}
			}
		}
	}
}
