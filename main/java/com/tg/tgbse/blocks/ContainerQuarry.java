package com.tg.tgbse.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerQuarry extends Container {
	private final TileEntityQuarry tileentity;
	//private int cookProgress, timeToCook, fuelLeft, outputRemaining;
	
	public ContainerQuarry(TileEntityQuarry tileentity) 
	{
		this.tileentity = tileentity;
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		if (handler != null) {

		}
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
		//  IContainerListener listener = (IContainerListener)this.listeners.get(i);
		// 	if(this.fuelLeft != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
		// 	if(this.cookProgress != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
		// 	if(this.timeToCook != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
		// 	if(this.outputRemaining != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
		}

		// this.fuelLeft = this.tileentity.getField(0);
		// this.cookProgress = this.tileentity.getField(1);
		// this.timeToCook = this.tileentity.getField(2);
		// this.outputRemaining = this.tileentity.getField(3);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.tileentity.isUsableByPlayer(playerIn);
	}
}