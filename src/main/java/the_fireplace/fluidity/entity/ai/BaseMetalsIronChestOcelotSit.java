package the_fireplace.fluidity.entity.ai;

import cpw.mods.ironchest.IronChestAIOcelotSit;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import the_fireplace.fluidity.compat.BaseMetalsIronChests;

public class BaseMetalsIronChestOcelotSit extends IronChestAIOcelotSit {
	public BaseMetalsIronChestOcelotSit(EntityOcelot par1EntityOcelot, float par2)
	{
		super(par1EntityOcelot, par2);
	}

	@Override
	protected boolean shouldMoveTo(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock() == BaseMetalsIronChests.fluidityChest || super.shouldMoveTo(world, pos);
	}
}
