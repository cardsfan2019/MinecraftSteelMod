package com.tg.tgbse.blocks;

import java.util.Map;
import java.util.Map.Entry;

import com.tg.tgbse.init.ModItems;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.item.ItemStack;

public class SteelFurnaceRecipes 
{	
	private static final SteelFurnaceRecipes INSTANCE = new SteelFurnaceRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static SteelFurnaceRecipes getInstance()
	{
		return INSTANCE;
	}
	
	public SteelFurnaceRecipes() 
	{
		//addSteelFurnaceRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL), new ItemStack(ModItems.STEEL_INGOT), 5.0F);
	}
	
	public void addSteelFurnaceRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) 
	{
		if(getSteelFurnaceResult(input1, input2) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getSteelFurnaceResult(ItemStack input1, ItemStack input2) 
	{
		if (input1.getUnlocalizedName().equals("item.ingotIron") && input2.getUnlocalizedName().equals("item.coal")) {
			return new ItemStack(ModItems.STEEL_INGOT);
		} else if (input2.getUnlocalizedName().equals("item.ingotIron") && input1.getUnlocalizedName().equals("item.coal")) {
			return new ItemStack(ModItems.STEEL_INGOT);
		} else return ItemStack.EMPTY; 
	}
	
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() 
	{
		return this.smeltingList;
	}
	
	public float getSteelFurnaceExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}