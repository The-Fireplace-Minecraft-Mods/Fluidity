package the_fireplace.fluidity.events;

import cpw.mods.ironchest.IronChestAIOcelotSit;
import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.fluidity.entity.ai.BaseMetalsIronChestOcelotSit;

import java.util.HashSet;

public class FluidityIronChestsCommonEvents {
	@SubscribeEvent
	public void changeSittingTaskForOcelots(LivingEvent.LivingUpdateEvent evt)
	{
		if (evt.getEntityLiving().ticksExisted >= 5 && evt.getEntityLiving().ticksExisted < 10 && evt.getEntityLiving() instanceof EntityOcelot)
		{
			HashSet<EntityAITasks.EntityAITaskEntry> hashset = new HashSet<EntityAITasks.EntityAITaskEntry>();

			EntityOcelot ocelot = (EntityOcelot) evt.getEntityLiving();

			for (EntityAITasks.EntityAITaskEntry task : ocelot.tasks.taskEntries)
			{
				if (task.action.getClass() == EntityAIOcelotSit.class || task.action.getClass() == IronChestAIOcelotSit.class)
				{
					hashset.add(task);
				}
			}

			for (EntityAITasks.EntityAITaskEntry task : hashset)
			{
				ocelot.tasks.removeTask(task.action);
				ocelot.tasks.addTask(task.priority, new BaseMetalsIronChestOcelotSit(ocelot, 0.4F));
			}
		}
	}
}
