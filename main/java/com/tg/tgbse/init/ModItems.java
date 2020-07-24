package com.tg.tgbse.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import com.tg.tgbse.BetterSteelProduction;
import com.tg.tgbse.items.CustomArmor;
import com.tg.tgbse.items.CustomAxe;
import com.tg.tgbse.items.CustomHoe;
import com.tg.tgbse.items.CustomPickaxe;
import com.tg.tgbse.items.CustomShovel;
import com.tg.tgbse.items.CustomSword;

@ObjectHolder(BetterSteelProduction.MODID)
public class ModItems {

	public static final Item STEEL_INGOT = null;
	
	public static final CustomSword STEEL_SWORD = null;
	
	public static final CustomPickaxe STEEL_PICKAXE = null;
	
	public static final CustomAxe STEEL_AXE = null;
	
	public static final CustomShovel STEEL_SHOVEL = null;
	
	public static final CustomHoe STEEL_HOE = null;
	
	public static final ItemBlock STEEL_BLOCK = null;
	
	public static final ItemArmor STEEL_HELMET = null;
	
	public static final ItemArmor STEEL_CHESTPLATE = null;
	
	public static final ItemArmor STEEL_LEGGINGS = null;
	
	public static final ItemArmor STEEL_BOOTS = null;
	
	public static final ItemBlock STEEL_FURNACE = null;
	
	public static final Item STEEL_NUGGET = null;

	public static final ItemBlock QUARRY = null;
	
	@EventBusSubscriber(modid = BetterSteelProduction.MODID)
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerItems(Register<Item> event) {
			final Item[] items =
				{
					new Item().setUnlocalizedName("steel_ingot").setRegistryName(new ResourceLocation(BetterSteelProduction.MODID, "steel_ingot")),
					new CustomSword(ModMaterials.steelmaterial, "steel_sword").setCreativeTab(CreativeTabs.COMBAT),
					new CustomPickaxe(ModMaterials.steelmaterial, "steel_pickaxe").setCreativeTab(CreativeTabs.TOOLS),
					new CustomHoe(ModMaterials.steelmaterial, "steel_hoe").setCreativeTab(CreativeTabs.TOOLS),
					new CustomShovel(ModMaterials.steelmaterial, "steel_shovel").setCreativeTab(CreativeTabs.TOOLS),
					new CustomAxe(ModMaterials.steelmaterial, "steel_axe", 2, 2),
					new CustomArmor(ModMaterials.steelarmormaterial, "steel_helmet", 1, EntityEquipmentSlot.HEAD).setCreativeTab(CreativeTabs.COMBAT),
					new CustomArmor(ModMaterials.steelarmormaterial, "steel_chestplate", 1, EntityEquipmentSlot.CHEST).setCreativeTab(CreativeTabs.COMBAT),
					new CustomArmor(ModMaterials.steelarmormaterial, "steel_leggings", 2, EntityEquipmentSlot.LEGS).setCreativeTab(CreativeTabs.COMBAT),
					new CustomArmor(ModMaterials.steelarmormaterial, "steel_boots", 1, EntityEquipmentSlot.FEET).setCreativeTab(CreativeTabs.COMBAT),
					new ItemBlock(ModBlocks.steel_furnace).setRegistryName(ModBlocks.steel_furnace.getRegistryName()),
					new ItemBlock(ModBlocks.steel_block).setRegistryName(ModBlocks.steel_block.getRegistryName()),
					new Item().setUnlocalizedName("steel_nugget").setRegistryName(new ResourceLocation(BetterSteelProduction.MODID, "steel_nugget")),
					new ItemBlock(ModBlocks.quarry).setRegistryName(ModBlocks.quarry.getRegistryName())
				};
			event.getRegistry().registerAll(items);
		}
	}
}
