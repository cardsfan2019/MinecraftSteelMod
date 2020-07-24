package com.tg.tgbse.client;

import com.tg.tgbse.blocks.SteelFurnaceRecipes;
import com.tg.tgbse.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistrationHandler {
	public static void RegisterRecipes() {
		GameRegistry.addSmelting(Item.getByNameOrId("minecraft:iron_ingot"), new ItemStack(ModItems.STEEL_INGOT, 1), 0.5f);
		SteelFurnaceRecipes.getInstance().addSteelFurnaceRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL), new ItemStack(ModItems.STEEL_INGOT), 5.0F);
	}
}
