package the_fireplace.fluidity.enums;

import cpw.mods.ironchest.IronChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import the_fireplace.fluidity.blocks.BlockFluidityIronChest;
import the_fireplace.fluidity.container.ValidatingIronChestSlot;
import the_fireplace.fluidity.entity.tile.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum FluidityIronChestType implements IStringSerializable {
	BRONZE(54, 9, "Bronze Chest", "bronzechest.png", 0, Collections.singletonList("ingotBronze"), TileEntityFluidityIronChest.class, "mmmmCmmmm", "mGmGBGmGm", "mGmG3GmGm", "mGmG4GmGm"),
	INVAR(81, 9, "Invar Chest", "invarchest.png", 1, Collections.singletonList("ingotInvar"), TileEntityInvarChest.class, "mmmm0mmmm", "mmmmdmmmm", "mmmmImmmm", "mGmG5GmGm", "mGmG6GmGm", "mGmG7GmGm", "mGmG8GmGm"),
	ELECTRUM(81, 9, "Electrum Chest", "electrumchest.png", 2, Collections.singletonList("ingotElectrum"), TileEntityElectrumChest.class, "mmmm0mmmm", "mmmmdmmmm", "mmmmImmmm", "mGmG5GmGm", "mGmG6GmGm", "mGmG7GmGm", "mGmG8GmGm"),
	TIN(45, 9, "Tin Chest", "tinchest.png", 3, Collections.singletonList("ingotTin"), TileEntityTinChest.class, "mmmmCmmmm"),
	BRASS(45, 9, "Brass Chest", "brasschest.png", 4, Collections.singletonList("ingotBrass"), TileEntityBrassChest.class, "mmmmCmmmm"),
	LEAD(72, 9, "Lead Chest", "leadchest.png", 5, Collections.singletonList("ingotLead"), TileEntityLeadChest.class, "mmmmBmmmm", "mmmm3mmmm", "mmmm4mmmm", "mGmG0GmGm", "mGmGdGmGm", "mGmGIGmGm"),
	STEEL(72, 9, "Steel Chest", "steelchest.png", 6, Collections.singletonList("ingotSteel"), TileEntitySteelChest.class, "mmmmBmmmm", "mmmm3mmmm", "mmmm4mmmm", "mGmG0GmGm", "mGmGdGmGm", "mGmGIGmGm"),
	NICKEL(72, 9, "Nickel Chest", "nickelchest.png", 7, Collections.singletonList("ingotNickel"), TileEntityNickelChest.class, "mmmmBmmmm", "mmmm3mmmm", "mmmm4mmmm", "mGmG0GmGm", "mGmGdGmGm", "mGmGIGmGm"),
	COLDIRON(72, 9, "Cold-Iron Chest", "coldironchest.png", 8, Collections.singletonList("ingotColdiron"), TileEntityColdIronChest.class, "mmmmBmmmm", "mmmm3mmmm", "mmmm4mmmm", "mGmG0GmGm", "mGmGdGmGm", "mGmGIGmGm"),
	ADAMANTINE(117, 13, "Adamantine Chest", "adamantinechest.png", 9, Collections.singletonList("ingotAdamantine"), TileEntityAdamantineChest.class, "mmmmDmmmm", "mmmmammmm", "mmmmbmmmm"),
	AQUARIUM(108, 12, "Aquarium Chest", "aquariumchest.png", 10, Collections.singletonList("ingotAquarium"), TileEntityAquariumChest.class, "mmmmgmmmm", "mmmm1mmmm", "mmmm2mmmm", "mmmmemmmm"),
	MITHRIL(108, 12, "Mithril Chest", "mithrilchest.png", 11, Collections.singletonList("ingotMithril"), TileEntityMithrilChest.class, "mmmmgmmmm", "mmmm1mmmm", "mmmm2mmmm", "mmmmemmmm"),
	STARSTEEL(117, 13, "Star-Steel Chest", "starsteelchest.png", 12, Collections.singletonList("plateStarsteel"), TileEntityStarSteelChest.class, "mmmGDGmmm", "mmmGaGmmm", "mmmGbGmmm"),
	CUPRONICKEL(54, 14, "Cupronickel Chest", "cupronickelchest.png", 13, Collections.singletonList("ingotCupronickel"), TileEntityCupronickelChest.class, "mmmmCmmmm", "mGmGBGmGm", "mGmG3GmGm", "mGmG4GmGm"),
	PLATINUM(81, 15, "Platinum Chest", "platinumchest.png", 14, Collections.singletonList("ingotPlatinum"), TileEntityPlatinumChest.class, "mmmm0mmmm", "mmmmdmmmm", "mmmmImmmm", "mGmG5GmGm", "mGmG6GmGm", "mGmG7GmGm", "mGmG8GmGm");

	public static final FluidityIronChestType[] VALUES;
	public final int size;
	public final int rowLength;
	public String friendlyName;
	public final ResourceLocation modelTexture;
	private String breakTexture;
	private int textureRow;
	public Class<? extends TileEntityFluidityIronChest> clazz;
	private String[] recipes;
	private ArrayList<String> matList;
	private Item itemFilter;

	FluidityIronChestType(int size, int rowLength, String friendlyName, String modelTexture, int textureRow, List<String> mats,
			Class<? extends TileEntityFluidityIronChest> clazz, String... recipes)
	{
		this(size, rowLength, friendlyName, modelTexture, textureRow, mats, clazz, null, recipes);
	}
	FluidityIronChestType(int size, int rowLength, String friendlyName, String modelTexture, int textureRow, List<String> mats,
			Class<? extends TileEntityFluidityIronChest> clazz, Item itemFilter, String... recipes)
	{
		this.size = size;
		this.rowLength = rowLength;
		this.friendlyName = friendlyName;
		this.modelTexture = new ResourceLocation("fluidity", "textures/model/" + modelTexture);
		this.textureRow = textureRow;
		this.clazz = clazz;
		this.itemFilter = itemFilter;
		this.recipes = recipes;
		this.matList = new ArrayList<String>();
		matList.addAll(mats);
	}

	@Override
	public String getName()
	{
		return name().toLowerCase();
	}

	public ResourceLocation getModelTexture()
	{
		return modelTexture;
	}

	public int getTextureRow()
	{
		return textureRow;
	}

	public static TileEntityFluidityIronChest makeEntity(int metadata)
	{
		int chesttype = validateMeta(metadata);
		if (chesttype == metadata)
		{
			try
			{
				TileEntityFluidityIronChest te = values()[chesttype].clazz.newInstance();
				return te;
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void registerBlocksAndRecipes(BlockFluidityIronChest blockResult)
	{
		for (FluidityIronChestType typ : values())
		{
			generateRecipesForType(blockResult, typ);
		}
	}

	public static void generateRecipesForType(BlockFluidityIronChest blockResult, FluidityIronChestType type)
	{
		for (String recipe : type.recipes)
		{
			String[] recipeSplit = new String[] { recipe.substring(0, 3), recipe.substring(3, 6), recipe.substring(6, 9) };
			Object mainMaterial;
			for (String mat : type.matList)
			{
				mainMaterial = translateOreName(mat);
				addRecipe(new ItemStack(blockResult, 1, type.ordinal()), recipeSplit,
						'm', mainMaterial,
						'G', "blockGlass", 'C', "chestWood",
						'0', new ItemStack(blockResult, 1, 0), /* Bronze Chest */
						'1', new ItemStack(blockResult, 1, 1), /* Invar Chest */
						'2', new ItemStack(blockResult, 1, 2), /* Electrum Chest */
						'3', new ItemStack(blockResult, 1, 3), /* Tin Chest */
						'4', new ItemStack(blockResult, 1, 4), /* Brass Chest */
						'5', new ItemStack(blockResult, 1, 5), /* Lead Chest */
						'6', new ItemStack(blockResult, 1, 6), /* Steel Chest */
						'7', new ItemStack(blockResult, 1, 7), /* Nickel Chest */
						'8', new ItemStack(blockResult, 1, 8), /* Cold-Iron Chest */
						'9', new ItemStack(blockResult, 1, 9), /* Adamantine Chest */
						'a', new ItemStack(blockResult, 1, 10),/* Aquarium Chest */
						'b', new ItemStack(blockResult, 1, 11),/* Mithril Chest */
						'c', new ItemStack(blockResult, 1, 12), /* Star-Steel Chest */
						'd', new ItemStack(blockResult, 1, 13), /* Cupronickel Chest */
						'e', new ItemStack(blockResult, 1, 14), /* Platinum Chest */
						'I', new ItemStack(IronChest.ironChestBlock, 1, 0), /* Iron Chest */
						'g', new ItemStack(IronChest.ironChestBlock, 1, 1), /* Gold Chest */
						'D', new ItemStack(IronChest.ironChestBlock, 1, 2), /* Diamond Chest */
						'B', new ItemStack(IronChest.ironChestBlock, 1, 3), /* Copper Chest */
						'S', new ItemStack(IronChest.ironChestBlock, 1, 4)  /* Silver Chest */
						);
			}
		}
	}

	public static Object translateOreName(String mat)
	{
		return mat;
	}

	public static void addRecipe(ItemStack is, Object... parts)
	{
		ShapedOreRecipe oreRecipe = new ShapedOreRecipe(is, parts);
		GameRegistry.addRecipe(oreRecipe);
	}

	public int getRowCount()
	{
		return size / rowLength;
	}

	public int getRowLength()
	{
		return rowLength;
	}

	public boolean isTransparent()
	{
		return false;
	}

	public List<String> getMatList()
	{
		return matList;
	}

	public static int validateMeta(int i)
	{
		if (i < values().length && values()[i].size > 0)
		{
			return i;
		}
		else
		{
			return 0;
		}
	}

	public boolean isValidForCreativeMode()
	{
		return validateMeta(ordinal()) == ordinal();
	}

	public boolean canFall()
	{
		return this == LEAD;
	}

	public Slot makeSlot(IInventory chestInventory, int index, int x, int y)
	{
		return new ValidatingIronChestSlot(chestInventory, index, x, y, this);
	}

	public boolean acceptsStack(ItemStack itemstack)
	{
		return itemFilter == null || itemstack == null || itemstack.getItem() == itemFilter;
	}
	public boolean isExplosionResistant() {
		return this == ADAMANTINE;
	}

	public String getBreakTexture() {
		if(this.breakTexture == null) {
			this.breakTexture = "basemetals:blocks/" + this.getName() + "_block";
		}

		return this.breakTexture;
	}

	static{
		VALUES = values();
	}
}
