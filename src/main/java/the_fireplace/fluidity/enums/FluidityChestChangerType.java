package the_fireplace.fluidity.enums;

import cpw.mods.ironchest.client.ModelHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.items.FluidityItemChestChanger;

import static the_fireplace.fluidity.enums.FluidityIronChestType.*;

public enum FluidityChestChangerType {
	BRONZEINVAR(BRONZE, INVAR, "bronzeInvarUpgrade", "mmm", "msm", "mmm"),
	BRONZEELECTRUM(BRONZE, ELECTRUM, "bronzeElectrumUpgrade", "mmm", "msm", "mmm"),
	BRONZELEAD(BRONZE, LEAD, "bronzeLeadUpgrade", "mGm", "GsG", "mGm"),
	BRONZESTEEL(BRONZE, STEEL, "bronzeSteelUpgrade", "mGm", "GsG", "mGm"),
	BRONZENICKEL(BRONZE, NICKEL, "bronzeNickelUpgrade", "mGm", "GsG", "mGm"),
	BRONZECOLDIRON(BRONZE, COLDIRON, "bronzeColdironUpgrade", "mGm", "GsG", "mGm"),
	TINLEAD(TIN, LEAD, "tinLeadUpgrade", "mmm", "msm", "mmm"),
	TINSTEEL(TIN, STEEL, "tinSteelUpgrade", "mmm", "msm", "mmm"),
	TINNICKEL(TIN, NICKEL, "tinNickelUpgrade", "mmm", "msm", "mmm"),
	TINCOLDIRON(TIN, COLDIRON, "tinColdironUpgrade", "mmm", "msm", "mmm"),
	TINBRONZE(TIN, BRONZE, "tinBronzeUpgrade", "mGm", "GsG", "mGm"),
	BRASSLEAD(BRASS, LEAD, "brassLeadUpgrade", "mmm", "msm", "mmm"),
	BRASSSTEEL(BRASS, STEEL, "brassSteelUpgrade", "mmm", "msm", "mmm"),
	BRASSNICKEL(BRASS, NICKEL, "brassNickelUpgrade", "mmm", "msm", "mmm"),
	BRASSCOLDIRON(BRASS, COLDIRON, "brassColdironUpgrade", "mmm", "msm", "mmm"),
	BRASSBRONZE(BRASS, BRONZE, "brassBronzeUpgrade", "mGm", "GsG", "mGm"),
	LEADINVAR(LEAD, INVAR, "leadInvarUpgrade", "mGm", "GsG", "mGm"),
	LEADELECTRUM(LEAD, ELECTRUM, "leadElectrumUpgrade", "mGm", "GsG", "mGm"),
	STEELINVAR(STEEL, INVAR, "steelInvarUpgrade", "mGm", "GsG", "mGm"),
	STEELELECTRUM(STEEL, ELECTRUM, "steelElectrumUpgrade", "mGm", "GsG", "mGm"),
	NICKELINVAR(NICKEL, INVAR, "nickelInvarUpgrade", "mGm", "GsG", "mGm"),
	NICKELELECTRUM(NICKEL, ELECTRUM, "nickelElectrumUpgrade", "mGm", "GsG", "mGm"),
	COLDIRONINVAR(COLDIRON, INVAR, "coldironInvarUpgrade", "mGm", "GsG", "mGm"),
	COLDIRONELECTRUM(COLDIRON, ELECTRUM, "coldironElectrumUpgrade", "mGm", "GsG", "mGm"),
	INVARMITHRIL(INVAR, MITHRIL, "invarMithrilUpgrade", "mmm", "msm", "mmm"),
	INVARAQUARIUM(INVAR, AQUARIUM, "invarAquariumUpgrade", "mmm", "msm", "mmm"),
	ELECTRUMMITHRIL(ELECTRUM, MITHRIL, "electrumMithrilUpgrade", "mmm", "msm", "mmm"),
	ELECTRUMAQUARIUM(ELECTRUM, AQUARIUM, "electrumAquariumUpgrade", "mmm", "msm", "mmm"),
	MITHRILADAMANTINE(MITHRIL, ADAMANTINE, "mithrilAdamantineUpgrade", "mmm", "msm", "mmm"),
	AQUARIUMADAMANTINE(AQUARIUM, ADAMANTINE, "aquariumAdamantineUpgrade", "mmm", "msm", "mmm"),
	MITHRILSTARSTEEL(MITHRIL, STARSTEEL, "mithrilStarsteelUpgrade", "mmm", "GsG", "mmm"),
	AQUARIUMSTARSTEEL(AQUARIUM, STARSTEEL, "aquariumStarsteelUpgrade", "mmm", "GsG", "mmm");

	private FluidityIronChestType source;
	private FluidityIronChestType target;
	public String sourcename;
	public String targetname;
	public String itemName;
	public FluidityItemChestChanger item;
	private String[] recipe;

	FluidityChestChangerType(FluidityIronChestType source, FluidityIronChestType target, String itemName, String... recipe)
	{
		this.source = source;
		this.target = target;
		this.sourcename = source.getName();
		this.targetname = target.getName();
		this.itemName = itemName;
		this.recipe = recipe;
	}

	public boolean canUpgrade(FluidityIronChestType from)
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
		GameRegistry.registerItem(item, itemName);
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			ModelHelper.registerItem(item, Fluidity.MODID+":" + itemName);
		return item;
	}

	public void addRecipes()
	{
		for (String sourceMat : source.getMatList())
		{
			for (String targetMat : target.getMatList())
			{
				Object targetMaterial = FluidityIronChestType.translateOreName(targetMat);
				Object sourceMaterial = FluidityIronChestType.translateOreName(sourceMat);
				FluidityIronChestType.addRecipe(new ItemStack(item), recipe, 'm', targetMaterial, 's', sourceMaterial, 'G', Blocks.glass, 'O', Blocks.obsidian);
			}
		}
	}

	public static void buildItems()
	{
		for (FluidityChestChangerType type : values())
		{
			type.buildItem();
		}
	}

	public static void generateRecipes()
	{
		for (FluidityChestChangerType item : values())
		{
			item.addRecipes();
		}
	}
}