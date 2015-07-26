package the_fireplace.fluidity.compat;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.blocks.BlockFluidityIronChest;
import the_fireplace.fluidity.entity.tile.renderer.TileEntityFluidityChestRenderer;
import the_fireplace.fluidity.enums.FluidityIronChestType;
import the_fireplace.fluidity.handler.IronChestsGuiHandler;
import the_fireplace.fluidity.items.ItemFluidityIronChest;

public class FluidityIronChests implements IModCompat {

	public static Block fluidityChest;

	@Override
	public void preInit() {
		fluidityChest = new BlockFluidityIronChest();
	}

	@Override
	public void init() {
		GameRegistry.registerBlock(fluidityChest, ItemFluidityIronChest.class, "fluidity_iron_chest");
		for (FluidityIronChestType typ : FluidityIronChestType.values())
		{
			GameRegistry.registerTileEntityWithAlternatives(typ.clazz, "Fluidity." + typ.name(), typ.name());
			if(classExists("net.minecraft.client.Minecraft")){
				ClientRegistry.bindTileEntitySpecialRenderer(typ.clazz, new TileEntityFluidityChestRenderer());
			}
		}
		FluidityIronChestType.registerBlocksAndRecipes((BlockFluidityIronChest) fluidityChest);
		NetworkRegistry.INSTANCE.registerGuiHandler(Fluidity.instance, new IronChestsGuiHandler());
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
			ModelBakery.addVariantName(chestItem, Fluidity.MODID+":chest_" + chestType.getName().toLowerCase());
		}
	}

}
