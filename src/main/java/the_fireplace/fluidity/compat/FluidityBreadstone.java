package the_fireplace.fluidity.compat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.fluidity.Fluidity;

public class FluidityBreadstone implements IModCompat {
	public static final Item flour = new Item().setUnlocalizedName("flour").setCreativeTab(Fluidity.tabFluidity);

	@Override
	public void preInit() {
		GameRegistry.registerItem(flour, "flour");
		OreDictionary.registerOre("flour", flour);
	}

	@Override
	public void init() {
		ItemStack flourStack = new ItemStack(flour);
		ItemStack breadStack = new ItemStack(Items.bread);
		GameRegistry.addSmelting(flourStack, breadStack, 0.2F);
	}

	@Override
	public void registerInvRenderers() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(flour, 0, new ModelResourceLocation(Fluidity.MODID+":flour", "inventory"));
	}

}
