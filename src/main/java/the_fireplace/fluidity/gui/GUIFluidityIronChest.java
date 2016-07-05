package the_fireplace.fluidity.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.container.ContainerFluidityIronChest;
import the_fireplace.fluidity.entity.tile.TileEntityFluidityIronChest;
import the_fireplace.fluidity.enums.FluidityIronChestType;

public class GUIFluidityIronChest extends GuiContainer {
	public enum ResourceList {
		COPPER(new ResourceLocation("ironchest", "textures/gui/coppercontainer.png")),
		IRON(new ResourceLocation("ironchest", "textures/gui/ironcontainer.png")),
		SILVER(new ResourceLocation("ironchest", "textures/gui/silvercontainer.png")),
		GOLD(new ResourceLocation("ironchest", "textures/gui/goldcontainer.png")),
		DIAMOND(new ResourceLocation("ironchest", "textures/gui/diamondcontainer.png")),
		ADAMANTINE(new ResourceLocation(Fluidity.MODID, "textures/gui/adamantinecontainer.png"));
		public final ResourceLocation location;
		ResourceList(ResourceLocation loc) {
			this.location = loc;
		}
	}
	public enum GUI {
		BRONZE(184, 202, ResourceList.IRON, FluidityIronChestType.BRONZE),
		INVAR(184, 256, ResourceList.GOLD, FluidityIronChestType.INVAR),
		ELECTRUM(184, 256, ResourceList.GOLD, FluidityIronChestType.ELECTRUM),
		TIN(184, 184, ResourceList.COPPER, FluidityIronChestType.TIN),
		BRASS(184, 184, ResourceList.COPPER, FluidityIronChestType.BRASS),
		LEAD(184, 238, ResourceList.SILVER, FluidityIronChestType.LEAD),
		STEEL(184, 238, ResourceList.SILVER, FluidityIronChestType.STEEL),
		NICKEL(184, 238, ResourceList.SILVER, FluidityIronChestType.NICKEL),
		COLDIRON(184, 238, ResourceList.SILVER, FluidityIronChestType.COLDIRON),
		ADAMANTINE(256, 256, ResourceList.ADAMANTINE, FluidityIronChestType.ADAMANTINE),
		AQUARIUM(238, 256, ResourceList.DIAMOND, FluidityIronChestType.AQUARIUM),
		MITHRIL(238, 256, ResourceList.DIAMOND, FluidityIronChestType.MITHRIL),
		STARSTEEL(256, 256, ResourceList.ADAMANTINE, FluidityIronChestType.STARSTEEL),
		CUPRONICKEL(184, 202, ResourceList.IRON, FluidityIronChestType.CUPRONICKEL),
		PLATINUM(184, 256, ResourceList.GOLD, FluidityIronChestType.PLATINUM);
		private int xSize;
		private int ySize;
		private ResourceList guiResourceList;
		private FluidityIronChestType mainType;

		GUI(int xSize, int ySize, ResourceList guiResourceList, FluidityIronChestType mainType)
		{
			this.xSize = xSize;
			this.ySize = ySize;
			this.guiResourceList = guiResourceList;
			this.mainType = mainType;

		}

		protected Container makeContainer(IInventory player, IInventory chest)
		{
			return new ContainerFluidityIronChest(player, chest, mainType, xSize, ySize);
		}

		public static GUIFluidityIronChest buildGUI(FluidityIronChestType type, IInventory playerInventory, TileEntityFluidityIronChest chestInventory)
		{
			return new GUIFluidityIronChest(values()[chestInventory.getType().ordinal()], playerInventory, chestInventory);
		}
	}

	public int getRowLength()
	{
		return type.mainType.getRowLength();
	}

	private GUI type;

	private GUIFluidityIronChest(GUI type, IInventory player, IInventory chest)
	{
		super(type.makeContainer(player, chest));
		this.type = type;
		this.xSize = type.xSize;
		this.ySize = type.ySize;
		this.allowUserInput = false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(type.guiResourceList.location);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
