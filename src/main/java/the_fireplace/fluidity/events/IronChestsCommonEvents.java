package the_fireplace.fluidity.events;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class IronChestsCommonEvents {
	@SubscribeEvent
	public void changeSittingTaskForOcelots(LivingEvent.LivingUpdateEvent evt)
	{
		/*if (evt.getEntityLiving().ticksExisted < 5 && evt.getEntityLiving() instanceof EntityOcelot)
		{
			EntityOcelot ocelot = (EntityOcelot) evt.getEntityLiving();
			@SuppressWarnings("unchecked")
			Set<EntityAITasks.EntityAITaskEntry> tasks = ocelot.tasks.taskEntries;

			for (EntityAITasks.EntityAITaskEntry task : tasks) {
				if (task.priority == 6 && (task.action instanceof EntityAIOcelotSit) && !(task.action instanceof FluidityIronChestOcelotSit)) {
					task.action = new FluidityIronChestOcelotSit(ocelot, 0.4F);
				}
			}
		}*/
	}
}
