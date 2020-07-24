package com.tg.tgbse;
import org.apache.logging.log4j.Logger;

import com.tg.tgbse.client.GuiRegistrationHandler;
import com.tg.tgbse.client.RecipeRegistrationHandler;

import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BetterSteelProduction.MODID, name = BetterSteelProduction.NAME, version = BetterSteelProduction.VERSION, acceptedMinecraftVersions = BetterSteelProduction.MC_VERSION)
public class BetterSteelProduction {

	public static final String MODID = "bsp";
	public static final String NAME = "Better Steel Production";
	public static final String VERSION = "0.0.1";
	public static final String MC_VERSION = "[1.12.2]";
	
	public static final int GUI_STEEL_FURNACE = 0;
	public static final int GUI_QUARRY = 1;


	public static final Logger LOGGER = LogManager.getLogger(BetterSteelProduction.MODID);
	

	@Instance
	public static BetterSteelProduction instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
                
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		RecipeRegistrationHandler.RegisterRecipes();
		GuiRegistrationHandler.registerGui();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
        
	}

}