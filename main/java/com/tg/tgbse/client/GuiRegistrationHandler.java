package com.tg.tgbse.client;

import com.tg.tgbse.BetterSteelProduction;

import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiRegistrationHandler {
	public static void registerGui() {
		NetworkRegistry.INSTANCE.registerGuiHandler(BetterSteelProduction.instance, new GuiHandler());
	}
}
