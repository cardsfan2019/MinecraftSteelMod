package com.tg.tgbse.items;

import com.tg.tgbse.BetterSteelProduction;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;

public class CustomArmor extends ItemArmor {

	public final int renderIndex;
	
	public CustomArmor(ArmorMaterial materialIn, String unlocalizedName, int renderIndexIn, EntityEquipmentSlot entityEquipmentSlots) {
		super(materialIn, renderIndexIn, entityEquipmentSlots);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(BetterSteelProduction.MODID, unlocalizedName));
		this.renderIndex = renderIndexIn;
	}
	
}
