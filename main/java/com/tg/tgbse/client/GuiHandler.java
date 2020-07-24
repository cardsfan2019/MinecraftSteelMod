package com.tg.tgbse.client;

import com.tg.tgbse.BetterSteelProduction;
import com.tg.tgbse.blocks.ContainerQuarry;
import com.tg.tgbse.blocks.ContainerSteelFurnace;
import com.tg.tgbse.blocks.GuiQuarry;
import com.tg.tgbse.blocks.GuiSteelFurnace;
import com.tg.tgbse.blocks.TileEntityQuarry;
import com.tg.tgbse.blocks.TileEntitySteelFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == BetterSteelProduction.GUI_STEEL_FURNACE) return new ContainerSteelFurnace(player.inventory, (TileEntitySteelFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if (ID == BetterSteelProduction.GUI_QUARRY) return new ContainerQuarry((TileEntityQuarry)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == BetterSteelProduction.GUI_STEEL_FURNACE) return new GuiSteelFurnace(player.inventory, (TileEntitySteelFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if (ID == BetterSteelProduction.GUI_QUARRY) return new GuiQuarry((TileEntityQuarry)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

}
