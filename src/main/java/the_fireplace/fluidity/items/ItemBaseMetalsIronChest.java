package the_fireplace.fluidity.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import the_fireplace.fluidity.enums.BaseMetalsIronChestType;

public class ItemBaseMetalsIronChest extends ItemBlock {
	public ItemBaseMetalsIronChest(Block block)
	{
		super(block);

		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setRegistryName("fluidity_iron_chest");
	}

	@Override
	public int getMetadata(int meta)
	{
		return BaseMetalsIronChestType.validateMeta(meta);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return "tile.ironchest:" + BaseMetalsIronChestType.values()[itemstack.getMetadata()].name();
	}
}
