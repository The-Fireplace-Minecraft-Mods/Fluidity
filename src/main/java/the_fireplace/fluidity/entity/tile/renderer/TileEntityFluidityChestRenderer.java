package the_fireplace.fluidity.entity.tile.renderer;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.primitives.SignedBytes;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import the_fireplace.fluidity.Fluidity;
import the_fireplace.fluidity.blocks.BlockFluidityIronChest;
import the_fireplace.fluidity.compat.FluidityIronChests;
import the_fireplace.fluidity.entity.tile.TileEntityFluidityIronChest;
import the_fireplace.fluidity.enums.FluidityIronChestType;

public class TileEntityFluidityChestRenderer extends TileEntitySpecialRenderer
{
	private static Map<FluidityIronChestType, ResourceLocation> locations;

	static {
		Builder<FluidityIronChestType, ResourceLocation> builder = ImmutableMap.<FluidityIronChestType,ResourceLocation>builder();
		for (FluidityIronChestType typ : FluidityIronChestType.values()) {
			builder.put(typ, new ResourceLocation(Fluidity.MODID,"textures/model/"+typ.getModelTexture()));
		}
		locations = builder.build();
	}

	private Random random;
	private RenderEntityItem itemRenderer;
	private ModelChest model;

	private static float[][] shifts = { { 0.3F, 0.45F, 0.3F }, { 0.7F, 0.45F, 0.3F }, { 0.3F, 0.45F, 0.7F }, { 0.7F, 0.45F, 0.7F }, { 0.3F, 0.1F, 0.3F },
			{ 0.7F, 0.1F, 0.3F }, { 0.3F, 0.1F, 0.7F }, { 0.7F, 0.1F, 0.7F }, { 0.5F, 0.32F, 0.5F }, };

	public TileEntityFluidityChestRenderer()
	{
		model = new ModelChest();
		random = new Random();
		itemRenderer = new RenderEntityItem(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem()){
			@Override
			public int func_177078_a(ItemStack stack) {
				return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 15) + 1);
			}
			@Override
			public boolean shouldBob() {
				return false;
			}
			@Override
			public boolean shouldSpreadItems() {
				return false;
			}
		};
	}

	public void render(TileEntityFluidityIronChest tile, double x, double y, double z, float partialTick, int breakStage)
	{
		if (tile == null) {
			return;
		}
		int facing = 3;
		FluidityIronChestType type = tile.getType();

		if (tile != null && tile.hasWorldObj() && tile.getWorld().getBlockState(tile.getPos()).getBlock() == FluidityIronChests.fluidityChest) {
			facing = tile.getFacing();
			type = tile.getType();
			IBlockState state = tile.getWorld().getBlockState(tile.getPos());
			type = (FluidityIronChestType)state.getValue(BlockFluidityIronChest.VARIANT_PROP);
		}

		if (breakStage >= 0)
		{
			bindTexture(DESTROY_STAGES[breakStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 4.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		} else
			bindTexture(locations.get(type));
		GlStateManager.pushMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GlStateManager.scale(1.0F, -1F, -1F);
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		int k = 0;
		if (facing == 2) {
			k = 180;
		}
		if (facing == 3) {
			k = 0;
		}
		if (facing == 4) {
			k = 90;
		}
		if (facing == 5) {
			k = -90;
		}
		GlStateManager.rotate(k, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		float lidangle = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTick;
		lidangle = 1.0F - lidangle;
		lidangle = 1.0F - lidangle * lidangle * lidangle;
		model.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);
		// Render the chest itself
		model.renderAll();
		if (breakStage >= 0)
		{
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
		GlStateManager.popMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		if (type.isTransparent() && tile.getDistanceSq(this.rendererDispatcher.entityX, this.rendererDispatcher.entityY, this.rendererDispatcher.entityZ) < 128d) {
			random.setSeed(254L);
			float shiftX;
			float shiftY;
			float shiftZ;
			int shift = 0;
			float blockScale = 0.70F;
			float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
			if (tile.getTopItemStacks()[1] == null) {
				shift = 8;
				blockScale = 0.85F;
			}
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y, (float) z);
			EntityItem customitem = new EntityItem(this.getWorld());
			customitem.hoverStart = 0f;
			for (ItemStack item : tile.getTopItemStacks()) {
				if (shift > shifts.length) {
					break;
				}
				if (item == null) {
					shift++;
					continue;
				}
				shiftX = shifts[shift][0];
				shiftY = shifts[shift][1];
				shiftZ = shifts[shift][2];
				shift++;
				GlStateManager.pushMatrix();
				GlStateManager.translate(shiftX, shiftY, shiftZ);
				GlStateManager.rotate(timeD, 0.0F, 1.0F, 0.0F);
				GlStateManager.scale(blockScale, blockScale, blockScale);
				customitem.setEntityItemStack(item);
				itemRenderer.doRender(customitem, 0, 0, 0, 0, 0);
				GlStateManager.popMatrix();
			}
			GlStateManager.popMatrix();
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTick, int breakStage)
	{
		render((TileEntityFluidityIronChest) tileentity, x, y, z, partialTick, breakStage);
	}
}