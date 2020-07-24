package com.tg.tgbse.items;

import com.tg.tgbse.BetterSteelProduction;

import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;

public class CustomHoe extends ItemHoe {

	public CustomHoe(ToolMaterial material, String unlocalizedName) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(BetterSteelProduction.MODID, unlocalizedName));
	}

}
