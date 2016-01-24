package the_fireplace.fluidity.entity.ai;

import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import the_fireplace.fluidity.compat.FluidityIronChests;

public class FluidityIronChestOcelotSit extends EntityAIOcelotSit {
	public FluidityIronChestOcelotSit(EntityOcelot par1EntityOcelot, float par2)
	{
		super(par1EntityOcelot, par2);
	}

	@Override
	protected boolean shouldMoveTo(World world, BlockPos pos)
	{
		if (world.getBlockState(pos).getBlock() == FluidityIronChests.fluidityChest)
		{
			return true;
		}
		return super.shouldMoveTo(world, pos);
	}
}
