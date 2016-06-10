package the_fireplace.fluidity.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import the_fireplace.fluidity.container.ContainerBaseMetalIronChest;
import the_fireplace.fluidity.entity.tile.TileEntityFluidityIronChest;
import the_fireplace.fluidity.enums.BaseMetalsIronChestType;
import the_fireplace.fluidity.gui.GUIBaseMetalsChest;

public class BaseMetalsIronChestsGuiHandler implements IGuiHandler {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		if (te != null && te instanceof TileEntityFluidityIronChest)
		{
			return GUIBaseMetalsChest.GUI.buildGUI(BaseMetalsIronChestType.values()[ID], player.inventory, (TileEntityFluidityIronChest) te);
		} else
		{
			return null;
		}
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		if (te != null && te instanceof TileEntityFluidityIronChest)
		{
			TileEntityFluidityIronChest icte = (TileEntityFluidityIronChest) te;
			return new ContainerBaseMetalIronChest(player.inventory, icte, icte.getType(), 0, 0);
		}
		else
		{
			return null;
		}
	}
}
