package com.tg.tgbse.items;

import com.tg.tgbse.BetterSteelProduction;

import net.minecraft.item.ItemSpade;
import net.minecraft.util.ResourceLocation;

public class CustomShovel extends ItemSpade {

	public CustomShovel(ToolMaterial material, String unlocalizedName) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(BetterSteelProduction.MODID, unlocalizedName));
	}

}
