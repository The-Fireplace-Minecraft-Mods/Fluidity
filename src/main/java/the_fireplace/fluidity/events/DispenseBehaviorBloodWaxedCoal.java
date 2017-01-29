package the_fireplace.fluidity.events;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import the_fireplace.fluidity.entity.projectile.EntityBloodWaxedCoal;

/**
 * @author The_Fireplace
 */
public class DispenseBehaviorBloodWaxedCoal extends BehaviorProjectileDispense {
    /**
     * Return the projectile entity spawned by this dispense behavior.
     */
    @Override
    protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stack) {
        return new EntityBloodWaxedCoal(worldIn, position.getX(), position.getY(), position.getZ());
    }
}