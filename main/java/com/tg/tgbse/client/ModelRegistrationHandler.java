package com.tg.tgbse.client;

import com.tg.tgbse.BetterSteelProduction;
import com.tg.tgbse.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = BetterSteelProduction.MODID)
public class ModelRegistrationHandler {
	
	@SubscribeEvent
	public static void registerItemModels(ModelRegistryEvent event) {
		registerModel(ModItems.STEEL_INGOT, 0);
		registerModel(ModItems.STEEL_SWORD, 0);
		registerModel(ModItems.STEEL_BLOCK, 0);
		registerModel(ModItems.STEEL_PICKAXE, 0);
		registerModel(ModItems.STEEL_AXE, 0);
		registerModel(ModItems.STEEL_SHOVEL, 0);
		registerModel(ModItems.STEEL_HOE, 0);
		registerModel(ModItems.STEEL_HELMET, 0);
		registerModel(ModItems.STEEL_CHESTPLATE, 0);
		registerModel(ModItems.STEEL_LEGGINGS, 0);
		registerModel(ModItems.STEEL_BOOTS, 0);
		registerModel(ModItems.STEEL_FURNACE, 0);
		registerModel(ModItems.STEEL_NUGGET, 0);
		registerModel(ModItems.QUARRY, 0);

	}
	
	private static void registerModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, 
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}