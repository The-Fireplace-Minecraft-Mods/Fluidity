package the_fireplace.fluidity.items;

import java.util.List;

import cyano.basemetals.init.Materials;
import cyano.basemetals.items.MetalToolEffects;
import cyano.basemetals.material.MetalMaterial;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import sorazodia.cannibalism.items.ItemKnife;

public class ItemBaseMetalKnife extends ItemKnife {
	protected final MetalMaterial metal;
	protected final boolean regenerates;
	protected final long regenInterval = 200;

	public ItemBaseMetalKnife(MetalMaterial material) {
		super(Materials.getToolMaterialFor(material));
		this.metal = material;
		if(material == Materials.starsteel){
			regenerates = true;
		}else{
			regenerates = false;
		}
	}
	@Override
	public boolean hitEntity(final ItemStack item, final EntityLivingBase target, final EntityLivingBase attacker) {
		super.hitEntity(item, target, attacker);
		MetalToolEffects.extraEffectsOnAttack(metal,item, target, attacker);
		return true;
	}
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b){
		super.addInformation(stack,player,list,b);
		MetalToolEffects.addToolSpecialPropertiesToolTip(metal,list);
	}
}
