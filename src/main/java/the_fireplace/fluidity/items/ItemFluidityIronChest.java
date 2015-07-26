package the_fireplace.fluidity.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import the_fireplace.fluidity.enums.FluidityIronChestType;

public class ItemFluidityIronChest extends ItemBlock {
	public ItemFluidityIronChest(Block block)
	{
		super(block);

		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return FluidityIronChestType.validateMeta(meta);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return "tile.ironchest:" + FluidityIronChestType.values()[itemstack.getMetadata()].name();
	}
}
