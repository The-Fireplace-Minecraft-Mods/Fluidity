package the_fireplace.fluidity.enums;

import cpw.mods.ironchest.IronChestType;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import the_fireplace.fluidity.items.BaseMetalsItemChestChanger;
import the_fireplace.fluidity.tools.Registry;

import static cpw.mods.ironchest.IronChestType.*;
import static the_fireplace.fluidity.enums.BaseMetalsIronChestType.*;

public enum IronBaseMetalsChestChangerType {
	IRONINVAR(IRON, INVAR, "ironInvarUpgrade", "mmm", "msm", "mmm"),
	IRONELECTRUM(IRON, ELECTRUM, "ironElectrumUpgrade", "mmm", "msm", "mmm"),
	IRONPLATINUM(IRON, PLATINUM, "ironPlatinumUpgrade", "mmm", "msm", "mmm"),
	IRONLEAD(IRON, LEAD, "ironLeadUpgrade", "mGm", "GsG", "mGm"),
	IRONSTEEL(IRON, STEEL, "ironSteelUpgrade", "mGm", "GsG", "mGm"),
	IRONNICKEL(IRON, NICKEL, "ironNickelUpgrade", "mGm", "GsG", "mGm"),
	IRONCOLDIRON(IRON, COLDIRON, "ironColdironUpgrade", "mGm", "GsG", "mGm"),
	COPPERLEAD(COPPER, LEAD, "copperLeadUpgrade", "mmm", "msm", "mmm"),
	COPPERSTEEL(COPPER, STEEL, "copperSteelUpgrade", "mmm", "msm", "mmm"),
	COPPERNICKEL(COPPER, NICKEL, "copperNickelUpgrade", "mmm", "msm", "mmm"),
	COPPERCOLDIRON(COPPER, COLDIRON, "copperColdironUpgrade", "mmm", "msm", "mmm"),
	COPPERBRONZE(COPPER, BRONZE, "copperBronzeUpgrade", "mGm", "GsG", "mGm"),
	COPPERCUPRONICKEL(COPPER, CUPRONICKEL, "copperCupronickelUpgrade", "mGm", "GsG", "mGm"),
	WOODTIN(WOOD, TIN, "woodTinUpgrade", "mGm", "GsG", "mGm"),
	WOODBRASS(WOOD, BRASS, "woodBrassUpgrade", "mGm", "GsG", "mGm"),
	WOODBRONZE(WOOD, BRONZE, "woodBronzeUpgrade", "mmm", "msm", "mmm"),
	WOODCUPRONICKEL(WOOD, CUPRONICKEL, "woodCupronickelUpgrade", "mmm", "msm", "mmm"),
	SILVERINVAR(SILVER, INVAR, "silverInvarUpgrade", "mGm", "GsG", "mGm"),
	SILVERELECTRUM(SILVER, ELECTRUM, "silverElectrumUpgrade", "mGm", "GsG", "mGm"),
	SILVERPLATINUM(SILVER, PLATINUM, "silverPlatinumUpgrade", "mGm", "GsG", "mGm"),
	GOLDMITHRIL(GOLD, MITHRIL, "goldMithrilUpgrade", "mmm", "msm", "mmm"),
	GOLDAQUARIUM(GOLD, AQUARIUM, "goldAquariumUpgrade", "mmm", "msm", "mmm"),
	DIAMONDADAMANTINE(DIAMOND, ADAMANTINE, "diamondAdamantineUpgrade", "mmm", "msm", "mmm"),
	DIAMONDSTARSTEEL(DIAMOND, STARSTEEL, "diamondStarsteelUpgrade", "mmm", "GsG", "mmm");

	private IronChestType source;
	private BaseMetalsIronChestType target;
	public String sourcename;
	public String targetname;
	public String itemName;
	public BaseMetalsItemChestChanger item;
	private String[] recipe;

	IronBaseMetalsChestChangerType(IronChestType source, BaseMetalsIronChestType target, String itemName, String... recipe)
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

	public BaseMetalsItemChestChanger buildItem()
	{
		item = new BaseMetalsItemChestChanger(this);
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
				Object targetMaterial = BaseMetalsIronChestType.translateOreName(targetMat);
				Object sourceMaterial = BaseMetalsIronChestType.translateOreName(sourceMat);
				BaseMetalsIronChestType.addRecipe(new ItemStack(item), recipe, 'm', targetMaterial, 's', sourceMaterial, 'G', Blocks.GLASS, 'O', Blocks.OBSIDIAN);
			}
		}
	}

	public static void buildItems()
	{
		for (IronBaseMetalsChestChangerType type : values())
		{
			type.buildItem();
		}
	}

	public static void generateRecipes()
	{
		for (IronBaseMetalsChestChangerType item : values())
		{
			item.addRecipes();
		}
	}
	public IronChestType getSource(){
		return source;
	}
}