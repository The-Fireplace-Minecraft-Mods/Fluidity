package the_fireplace.fluidity.enums;

import cpw.mods.ironchest.IronChestType;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import the_fireplace.fluidity.items.FluidityItemChestChanger;
import the_fireplace.fluidity.tools.Registry;

import static cpw.mods.ironchest.IronChestType.*;
import static the_fireplace.fluidity.enums.FluidityIronChestType.*;

public enum IronFluidityChestChangerType {
	IRONINVAR(IRON, INVAR, "ironInvarUpgrade", "mmm", "msm", "mmm"),
	IRONELECTRUM(IRON, ELECTRUM, "ironElectrumUpgrade", "mmm", "msm", "mmm"),
	IRONLEAD(IRON, LEAD, "ironLeadUpgrade", "mGm", "GsG", "mGm"),
	IRONSTEEL(IRON, STEEL, "ironSteelUpgrade", "mGm", "GsG", "mGm"),
	IRONNICKEL(IRON, NICKEL, "ironNickelUpgrade", "mGm", "GsG", "mGm"),
	IRONCOLDIRON(IRON, COLDIRON, "ironColdironUpgrade", "mGm", "GsG", "mGm"),
	COPPERLEAD(COPPER, LEAD, "copperLeadUpgrade", "mmm", "msm", "mmm"),
	COPPERSTEEL(COPPER, STEEL, "copperSteelUpgrade", "mmm", "msm", "mmm"),
	COPPERNICKEL(COPPER, NICKEL, "copperNickelUpgrade", "mmm", "msm", "mmm"),
	COPPERCOLDIRON(COPPER, COLDIRON, "copperColdironUpgrade", "mmm", "msm", "mmm"),
	COPPERBRONZE(COPPER, BRONZE, "copperBronzeUpgrade", "mGm", "GsG", "mGm"),
	WOODTIN(WOOD, TIN, "woodTinUpgrade", "mGm", "GsG", "mGm"),
	WOODBRASS(WOOD, BRASS, "woodBrassUpgrade", "mGm", "GsG", "mGm"),
	WOODBRONZE(WOOD, BRONZE, "woodBronzeUpgrade", "mmm", "msm", "mmm"),
	SILVERINVAR(SILVER, INVAR, "silverInvarUpgrade", "mGm", "GsG", "mGm"),
	SILVERELECTRUM(SILVER, ELECTRUM, "silverElectrumUpgrade", "mGm", "GsG", "mGm"),
	GOLDMITHRIL(GOLD, MITHRIL, "goldMithrilUpgrade", "mmm", "msm", "mmm"),
	GOLDAQUARIUM(GOLD, AQUARIUM, "goldAquariumUpgrade", "mmm", "msm", "mmm"),
	DIAMONDADAMANTINE(DIAMOND, ADAMANTINE, "diamondAdamantineUpgrade", "mmm", "msm", "mmm"),
	DIAMONDSTARSTEEL(DIAMOND, STARSTEEL, "diamondStarsteelUpgrade", "mmm", "GsG", "mmm");

	private IronChestType source;
	private FluidityIronChestType target;
	public String sourcename;
	public String targetname;
	public String itemName;
	public FluidityItemChestChanger item;
	private String[] recipe;

	IronFluidityChestChangerType(IronChestType source, FluidityIronChestType target, String itemName, String... recipe)
	{
		this.source = source;
		this.target = target;
		sourcename = source.getName();
		targetname = target.getName();
		this.itemName = itemName;
		this.recipe = recipe;
	}

	public boolean canUpgrade(IronChestType from)
	{
		return from == this.source;
	}

	public int getTarget()
	{
		return this.target.ordinal();
	}

	public FluidityItemChestChanger buildItem()
	{
		item = new FluidityItemChestChanger(this);
		item.setRegistryName(itemName);
		GameRegistry.register(item);
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			Registry.registerRender(item, itemName);
		return item;
	}

	public void addRecipes()
	{
		for (String sourceMat : source.matList)
		{
			for (String targetMat : target.getMatList())
			{
				Object targetMaterial = FluidityIronChestType.translateOreName(targetMat);
				Object sourceMaterial = FluidityIronChestType.translateOreName(sourceMat);
				FluidityIronChestType.addRecipe(new ItemStack(item), recipe, 'm', targetMaterial, 's', sourceMaterial, 'G', Blocks.GLASS, 'O', Blocks.OBSIDIAN);
			}
		}
	}

	public static void buildItems()
	{
		for (IronFluidityChestChangerType type : values())
		{
			type.buildItem();
		}
	}

	public static void generateRecipes()
	{
		for (IronFluidityChestChangerType item : values())
		{
			item.addRecipes();
		}
	}
	public IronChestType getSource(){
		return source;
	}
}