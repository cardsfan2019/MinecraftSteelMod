package com.tg.tgbse.items;

import com.tg.tgbse.BetterSteelProduction;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;

public class CustomPickaxe extends ItemPickaxe {

	public CustomPickaxe(ToolMaterial material, String unlocalizedName) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(BetterSteelProduction.MODID, unlocalizedName));
	}

}
