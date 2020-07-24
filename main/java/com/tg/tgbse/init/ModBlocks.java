package com.tg.tgbse.init;

import com.tg.tgbse.BetterSteelProduction;
import com.tg.tgbse.blocks.BlockQuarry;
import com.tg.tgbse.blocks.BlockSteelFurnace;
import com.tg.tgbse.client.TileEntityHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;


@ObjectHolder(BetterSteelProduction.MODID)
public class ModBlocks {
    public static final Block steel_block = null;
    public static final Block steel_furnace = null;
    public static final Block lit_steel_furnace = null;
	public static final Block quarry = null;

	@EventBusSubscriber(modid = BetterSteelProduction.MODID)
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerBlocks(Register<Block> event) {
			final Block[] blocks = {
					new Block(Material.IRON).setHardness(6).setUnlocalizedName("steel_block").setRegistryName("steel_block"),
					new BlockSteelFurnace(Material.ROCK, "steel_furnace", 0.0f).setCreativeTab(CreativeTabs.MISC).setHardness(6),
					new BlockSteelFurnace(Material.ROCK, "lit_steel_furnace", 1.0f).setCreativeTab(CreativeTabs.MISC).setHardness(6),
					new BlockQuarry(Material.ROCK, "quarry").setCreativeTab(CreativeTabs.MISC).setHardness(6)
			};
			
			event.getRegistry().registerAll(blocks);
			TileEntityHandler.registerTileEntities();
		}
	}
}