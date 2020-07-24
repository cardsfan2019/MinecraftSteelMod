package com.tg.tgbse.blocks;

import com.tg.tgbse.BetterSteelProduction;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;

import net.minecraft.util.ResourceLocation;

public class GuiQuarry extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(BetterSteelProduction.MODID + ":textures/gui/container/quarry.png");
	private final TileEntityQuarry tileentity;

	public GuiQuarry(TileEntityQuarry tileentity) {
		super(new ContainerQuarry(tileentity));
		this.tileentity = tileentity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tilename = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString("Quarry", (this.xSize / 2 - this.fontRenderer.getStringWidth(tilename) / 2) + 3, 6, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.clearColor(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
