package com.tg.tgbse.items;

import com.tg.tgbse.BetterSteelProduction;

import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class CustomSword extends ItemSword {

	private int attackDamage;

	public CustomSword(ToolMaterial material, String unlocalizedName) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(BetterSteelProduction.MODID, unlocalizedName));
		this.attackDamage = 100;
	}

}
