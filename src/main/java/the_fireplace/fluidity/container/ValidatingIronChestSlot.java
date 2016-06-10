package the_fireplace.fluidity.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import the_fireplace.fluidity.enums.BaseMetalsIronChestType;

public class ValidatingIronChestSlot extends Slot {
	private BaseMetalsIronChestType type;

	public ValidatingIronChestSlot(IInventory par1iInventory, int par2, int par3, int par4, BaseMetalsIronChestType type)
	{
		super(par1iInventory, par2, par3, par4);
		this.type = type;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return type.acceptsStack(par1ItemStack);
	}
}
