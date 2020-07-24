package com.tg.tgbse.blocks;

import com.tg.tgbse.BetterSteelProduction;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityQuarry extends TileEntity implements ITickable {
	private String customName;
	
	private int energyStored;
	private int energyPerAction;
	private int mineRadius;
	private int maxEnergy;
	private EnumFacing facing;
	private boolean enabled;
	private BlockPos miningPos;
	
	public TileEntityQuarry() {
		this(null, null);
	}
	
	public TileEntityQuarry(EnumFacing facing, World world) {
		super();
		this.energyStored = 10000;
		this.energyPerAction = 120;
		this.mineRadius = 5;
		this.maxEnergy = 100000;
		this.facing = facing;	
		this.world = world;
		this.enabled = true;
	}
		
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.quarry");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		this.energyStored = compound.getInteger("EnergyStored");
		this.energyPerAction = compound.getInteger("EnergyPerAction");

		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("EnergyStored", (short)this.energyStored);
		compound.setInteger("EnergyPerAction", (short)this.energyPerAction);

		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean isActive() {
		return this.blocksInRadius() && canMine();
	}
	
	public boolean canMine() {
		return this.energyStored > this.energyPerAction;
	}
	
	public void nextBlock() {
		if(miningPos.getY() > 0) {
			if(miningPos.getZ() > pos.getZ()) {
				miningPos = miningPos.add(0, 0, -1);
			} else {
				miningPos = miningPos.add(0,0, mineRadius);
				
				if(miningPos.getX() > pos.getX()) {
					miningPos = miningPos.add(-1,0,0);
				} else {
					miningPos = miningPos.add(mineRadius,-1,0);
				}
			}
		}
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
	}
	
	public void update() {				
		if(hasWorld() && pos != null) {
			if(miningPos == null) {
				this.miningPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
				miningPos = miningPos.add(new BlockPos(mineRadius, -1,mineRadius));
			}
			
			IBlockState state = world.getBlockState(miningPos);
			nextBlock();
			if(canMine()) {
				world.setBlockToAir(miningPos);
				//this.energyStored -= energyPerAction;
			}
		}
	}

	public boolean blocksInRadius() {
		return true;
	}
	
	public boolean clearedAbove() {
		return false;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
}