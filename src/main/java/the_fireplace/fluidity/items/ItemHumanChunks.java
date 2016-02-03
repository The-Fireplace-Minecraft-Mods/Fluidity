package the_fireplace.fluidity.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.common.items.IVariantItems;

import java.util.List;

public class ItemHumanChunks extends ItemFood implements IVariantItems {
	public final int itemUseDuration = 10;
	private String[] variants = new String[]{"player", "villager"};

	public ItemHumanChunks() {
		super(1, 0.3F, false);
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return this.itemUseDuration;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int a = 0; a < this.variants.length; ++a) {
			par3List.add(new ItemStack(this, 1, a));
		}

	}
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "." + this.variants[par1ItemStack.getItemDamage()];
	}
	@Override
	public String[] getVariantNames() {
		return this.variants;
	}

	@Override
	public int[] getVariantMeta() {
		return null;
	}
}
