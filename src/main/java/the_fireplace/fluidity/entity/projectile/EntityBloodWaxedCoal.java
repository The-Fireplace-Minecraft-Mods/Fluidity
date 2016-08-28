package the_fireplace.fluidity.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.cyclops.evilcraft.Configs;
import org.cyclops.evilcraft.block.BloodStainedBlock;
import org.cyclops.evilcraft.block.BloodStainedBlockConfig;
import org.cyclops.evilcraft.client.particle.ParticleBloodSplash;
import the_fireplace.frt.entity.projectile.EntityBazookaAmmo;

import java.util.Random;

/**
 * @author The_Fireplace
 */
public class EntityBloodWaxedCoal extends EntityBazookaAmmo {
    public EntityBloodWaxedCoal(World worldIn) {
        super(worldIn);
    }

    public EntityBloodWaxedCoal(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityBloodWaxedCoal(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void executeImpact(RayTraceResult mop) {
        if(mop.entityHit != null) {
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getPlayerThrower()), 3.5F);
        }else{
            if(Configs.isEnabled(BloodStainedBlockConfig.class)) {
                BlockPos pos = mop.getBlockPos();
                Block block = worldObj.getBlockState(pos).getBlock();
                if(BloodStainedBlock.getInstance().canSetInnerBlock(worldObj.getBlockState(pos), block, worldObj, pos) || block == BloodStainedBlock.getInstance()) {
                    if(!worldObj.isRemote) {
                        BloodStainedBlock.getInstance().stainBlock(worldObj, pos, 375);
                    } else {
                        Random random = new Random();
                        ParticleBloodSplash.spawnParticles(worldObj, pos.add(0, 1, 0), 375/20 + random.nextInt(15), 5 + random.nextInt(5));
                    }
                }
            }
        }
    }
}
