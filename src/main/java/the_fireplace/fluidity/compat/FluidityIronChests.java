package the_fireplace.fluidity.compat;

import cpw.mods.ironchest.IronChest;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.blocks.BlockFluidityIronChest;
import the_fireplace.fluidity.entity.tile.renderer.TileEntityFluidityChestRenderer;
import the_fireplace.fluidity.enums.FluidityChestChangerType;
import the_fireplace.fluidity.enums.FluidityIronChestChangerType;
import the_fireplace.fluidity.enums.FluidityIronChestType;
import the_fireplace.fluidity.enums.IronFluidityChestChangerType;
import the_fireplace.fluidity.events.IronChestsForgeEvents;
import the_fireplace.fluidity.handler.IronChestsGuiHandler;
import the_fireplace.fluidity.items.ItemFluidityIronChest;
import the_fireplace.fluidity.tools.Registry;

public class FluidityIronChests implements IModCompat {

	public static Block fluidityChest;

	@Override
	public void preInit() {
		fluidityChest = new BlockFluidityIronChest();
	}

	@Override
	public void init() {
		FluidityChestChangerType.buildItems();
		FluidityIronChestChangerType.buildItems();
		IronFluidityChestChangerType.buildItems();
		GameRegistry.registerBlock(fluidityChest, ItemFluidityIronChest.class, "fluidity_iron_chest");
		for (FluidityIronChestType typ : FluidityIronChestType.values())
		{
			GameRegistry.registerTileEntityWithAlternatives(typ.clazz, "Fluidity." + typ.name(), typ.name());
			if(classExists("net.minecraft.client.Minecraft")){
				ClientRegistry.bindTileEntitySpecialRenderer(typ.clazz, new TileEntityFluidityChestRenderer());
			}
		}
		FluidityIronChestType.registerBlocksAndRecipes((BlockFluidityIronChest) fluidityChest);
		FluidityChestChangerType.generateRecipes();
		FluidityIronChestChangerType.generateRecipes();
		IronFluidityChestChangerType.generateRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(Fluidity.instance, new IronChestsGuiHandler());
		addRecipes();
		MinecraftForge.EVENT_BUS.register(new IronChestsForgeEvents());
	}

	private void addRecipes() {
		ItemStack ironChestStack = new ItemStack(IronChest.ironChestBlock, 1, 0);
		ItemStack silverChestStack = new ItemStack(IronChest.ironChestBlock, 1, 4);
		ItemStack goldChestStack = new ItemStack(IronChest.ironChestBlock, 1, 1);
		ItemStack diamondChestStack = new ItemStack(IronChest.ironChestBlock, 1, 2);

		ItemStack bronzeChestStack = new ItemStack(fluidityChest, 1, 0);
		ItemStack invarChestStack = new ItemStack(fluidityChest, 1, 1);
		ItemStack electrumChestStack = new ItemStack(fluidityChest, 1, 2);
		ItemStack tinChestStack = new ItemStack(fluidityChest, 1, 3);
		ItemStack brassChestStack = new ItemStack(fluidityChest, 1, 4);
		ItemStack leadChestStack = new ItemStack(fluidityChest, 1, 5);
		ItemStack steelChestStack = new ItemStack(fluidityChest, 1, 6);
		ItemStack nickelChestStack = new ItemStack(fluidityChest, 1, 7);
		ItemStack coldironChestStack = new ItemStack(fluidityChest, 1, 8);

		Registry.addRecipe(ironChestStack, "igi", "gcg", "igi", 'i', "ingotIron", 'c', tinChestStack, 'g', "blockGlass");
		Registry.addRecipe(ironChestStack, "igi", "gcg", "igi", 'i', "ingotIron", 'c', brassChestStack, 'g', "blockGlass");
		Registry.addRecipe(silverChestStack, "igi", "gcg", "igi", 'i', "ingotSilver", 'c', bronzeChestStack, 'g', "blockGlass");
		Registry.addRecipe(silverChestStack, "iii", "ici", "iii", 'i', "ingotSilver", 'c', tinChestStack);
		Registry.addRecipe(silverChestStack, "iii", "ici", "iii", 'i', "ingotSilver", 'c', brassChestStack);
		Registry.addRecipe(goldChestStack, "iii", "ici", "iii", 'i', "ingotGold", 'c', bronzeChestStack);
		Registry.addRecipe(goldChestStack, "igi", "gcg", "igi", 'i', "ingotGold", 'c', leadChestStack, 'g', "blockGlass");
		Registry.addRecipe(goldChestStack, "igi", "gcg", "igi", 'i', "ingotGold", 'c', coldironChestStack, 'g', "blockGlass");
		Registry.addRecipe(goldChestStack, "igi", "gcg", "igi", 'i', "ingotGold", 'c', nickelChestStack, 'g', "blockGlass");
		Registry.addRecipe(goldChestStack, "igi", "gcg", "igi", 'i', "ingotGold", 'c', steelChestStack, 'g', "blockGlass");
		Registry.addRecipe(diamondChestStack, "ggg", "ici", "ggg", 'i', "gemDiamond", 'c', invarChestStack, 'g', "blockGlass");
		Registry.addRecipe(diamondChestStack, "ggg", "ici", "ggg", 'i', "gemDiamond", 'c', electrumChestStack, 'g', "blockGlass");
	}

	private boolean classExists(String classpath){
		try{
			Class.forName(classpath);
			return true;
		}catch(final ClassNotFoundException e){
			return false;
		}
	}

	@Override
	public void registerInvRenderers() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getBlockModelShapes().registerBuiltInBlocks(fluidityChest);

		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		for (FluidityIronChestType chestType : FluidityIronChestType.values())
		{
			Item chestItem = Item.getItemFromBlock(fluidityChest);
			mesher.register(chestItem, chestType.ordinal(), new ModelResourceLocation(Fluidity.MODID+":chest_" + chestType.getName().toLowerCase(), "inventory"));
			ModelBakery.registerItemVariants(chestItem, new ResourceLocation(Fluidity.MODID+":chest_" + chestType.getName().toLowerCase()));
		}
	}

}
