package the_fireplace.fluidity.gui;

import cyano.basemetals.material.MetalMaterial;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.fluidity.blocks.BaseMetalAnvil;
import the_fireplace.moreanvils.container.ContainerMaterialAnvil;
import the_fireplace.moreanvils.gui.GuiMaterialAnvil;

@SideOnly(Side.CLIENT)
public class GuiBaseMetalsAnvil extends GuiMaterialAnvil {
    private static final ResourceLocation anvilResource = new ResourceLocation("textures/gui/container/anvil.png");
    private static final String resource = "fluidity:textures/gui/%s_hammer.png";
    private ContainerMaterialAnvil anvil;
    public MetalMaterial material;

    public GuiBaseMetalsAnvil(InventoryPlayer inventoryIn, World worldIn, BaseMetalAnvil anvil) {
        super(inventoryIn, worldIn, anvil);
        this.anvil = (ContainerMaterialAnvil) this.inventorySlots;
        material = anvil.metalMaterial;
    }

    public GuiBaseMetalsAnvil(GuiMaterialAnvil owner){
        this(owner.playerInventory, owner.anvWorld, (BaseMetalAnvil)owner.matAnv);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(anvilResource);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect(i + 59, j + 20, 0, this.ySize + (this.anvil.getSlot(0).getHasStack() ? 0 : 16), 110, 16);

        if ((this.anvil.getSlot(0).getHasStack() || this.anvil.getSlot(1).getHasStack()) && !this.anvil.getSlot(2).getHasStack()) {
            this.drawTexturedModalRect(i + 99, j + 45, this.xSize, 0, 28, 21);
        }
        this.mc.getTextureManager().bindTexture(new ResourceLocation(String.format(resource, this.material.getName())));
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}