package com.tg.tgbse.init;

import com.tg.tgbse.BetterSteelProduction;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials {
	
	public static int[] reductions = { 1 };

	public static final ToolMaterial steelmaterial = EnumHelper.addToolMaterial("STEEL MATERIAL", 4, 4000, 20, 10, 30);
	public static final ArmorMaterial steelarmormaterial = EnumHelper.addArmorMaterial("armor_material_ruby", BetterSteelProduction.MODID + ":steel", 14, 
			new int[] {2, 5, 7, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
}