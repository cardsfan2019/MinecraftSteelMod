package com.tg.tgbse.client;

import com.tg.tgbse.blocks.TileEntityQuarry;
import com.tg.tgbse.blocks.TileEntitySteelFurnace;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySteelFurnace.class, new ResourceLocation("steel_furnace"));
		GameRegistry.registerTileEntity(TileEntityQuarry.class, new ResourceLocation("quarry"));
	}
}
