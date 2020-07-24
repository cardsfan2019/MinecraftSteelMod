package com.tg.tgbse.blocks;

import com.tg.tgbse.BetterSteelProduction;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class GuiSteelFurnace extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(BetterSteelProduction.MODID + ":textures/gui/container/steel_furnace.png");
	private final InventoryPlayer player;
	private final TileEntitySteelFurnace tileentity;
	public GuiSteelFurnace(InventoryPlayer player, TileEntitySteelFurnace tileentity) {
		super(new ContainerSteelFurnace(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tilename = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString("Steel Furnace", (this.xSize / 2 - this.fontRenderer.getStringWidth(tilename) / 2) + 3, 6, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 6, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.clearColor(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		if(TileEntitySteelFurnace.isBurning(tileentity)) {
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 57, this.guiTop + 37 + 12 - k, 176, 12 - k, 14, k + 1);
			int l = this.getCookProgressScaled(24);
			this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 35, 176, 14, l + 1, 16);
		}
	}
	
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	private int getBurnLeftScaled(int pixels) {
		int i = this.tileentity.getField(0);
		if(i == 0) {
			i = 200;
		}
		return i * pixels / TileEntitySteelFurnace.getItemBurnTime(Item.getItemFromBlock(Blocks.COAL_BLOCK));
	}
	
	private int getCookProgressScaled(int pixels) {
		int i = this.tileentity.getField(1);
		int j = this.tileentity.getField(2);

		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
