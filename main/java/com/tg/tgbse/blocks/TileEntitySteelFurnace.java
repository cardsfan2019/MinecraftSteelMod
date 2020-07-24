package com.tg.tgbse.blocks;

import com.tg.tgbse.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntitySteelFurnace extends TileEntity implements ITickable, ISidedInventory {
	private ItemStackHandler handler = new ItemStackHandler(4);
	private String customName;
	
    private static final int[] SLOTS_TOP = new int[] {2};
    private static final int[] SLOTS_BOTTOM = new int[] {3};
    private static final int[] SLOTS_SIDES = new int[] {0,1};
	
	private int fuelLeft; //keeps track of how much fuel value remains in the furnace
	private int cookProgress; //keeps track of how long the ingredients have been cooking for
	private int timeToCook = 200; //sets the time required for cooking the ingredients
	private boolean cooking;

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		else return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}

	public ItemStackHandler getHandler() {
		return this.handler;
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
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.steel_furnace");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.fuelLeft = compound.getInteger("FuelLeft");
		this.cookProgress = compound.getInteger("CookProgress");
		this.timeToCook = compound.getInteger("TimeToCook");
		this.cooking = compound.getBoolean("Cooking");
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("FuelLeft", (short)this.fuelLeft);
		compound.setInteger("CookProgress", (short)this.cookProgress);
		compound.setInteger("TimeToCook", (short)this.timeToCook);
		compound.setBoolean("Cooking", this.cooking);
		compound.setTag("Inventory", this.handler.serializeNBT());
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean isBurning() 
	{
		return this.fuelLeft > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntitySteelFurnace te) 
	{
		return te.getField(0) > 0;
	}
	
	public void update() 
	{	
		
		ItemStack fuel = this.handler.getStackInSlot(2);
		ItemStack[] inputs = new ItemStack[] {handler.getStackInSlot(0), handler.getStackInSlot(1)};
		ItemStack result = SteelFurnaceRecipes.getInstance().getSteelFurnaceResult((ItemStack)this.handler.getStackInSlot(0), (ItemStack)this.handler.getStackInSlot(1));			
		ItemStack output = handler.getStackInSlot(3);

		if(this.fuelLeft > 0 && output.getCount() < 64) { //if there is fuel remaining, reduce the burn time
			this.fuelLeft--;
			this.cooking = true;
		} else if (output.getCount() == 64) {
			this.cooking = false;
		}
		
		if(this.fuelLeft == 0 && !result.isEmpty()) { //if there is no fuel left and there is fuel in the fuel slot and items to burn, remove one fuel and reset the fuel left
			this.cookProgress = 0;
			if(fuel.getCount() > 0) {
				if(fuel.getItem() == Item.getItemFromBlock(Blocks.COAL_BLOCK)) {
					this.fuelLeft = TileEntitySteelFurnace.getItemBurnTime(fuel);
					fuel.shrink(1);
					this.cooking = true;
					BlockSteelFurnace.setState(true, world, pos);
				}
			}
		}
		
		if (this.fuelLeft == 0 && this.cooking == true) {
			this.cooking = false;
			
			BlockSteelFurnace.setState(false, world, pos);
		}
		
		if (result.getItem() == ModItems.STEEL_INGOT && fuelLeft > 0 && this.cookProgress != this.timeToCook && this.cooking) { //if the ingredients haven't fully cooked, keep raising the cooking amount
			this.cookProgress++;
		}
		
		if(this.cookProgress == this.timeToCook) { //if the ingredients have cooked fully increase the amount of steel in the output	
			inputs[0].shrink(1);
			inputs[1].shrink(1);
			this.handler.setStackInSlot(0, inputs[0]);
			this.handler.setStackInSlot(1, inputs[1]);
			
			this.cookProgress = 0;
			
			if(output.isEmpty()) {
				handler.setStackInSlot(3, result);
			} else if(output.getCount() > 0 && output.getCount() < 64 && output.getItem() == result.getItem()) {
				output.grow(1);
			}		
		}
	}
	
	public static int getItemBurnTime(ItemStack fuel) 
	{
		if(fuel.isEmpty()) return 0;
		else 
		{
			Item item = fuel.getItem();
			if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK)) {
				return 200;
			}
			return 0;
		}
	}
	
	public static int getItemBurnTime(Item fuel) 
	{
		if (fuel == Item.getItemFromBlock(Blocks.COAL_BLOCK)) {
			return 200;
		}
		return 0;
	}
	
	public static boolean isItemIngredient(ItemStack item) {
		if(item.getItem() == Items.COAL || item.getItem() == Items.IRON_INGOT) return true;
		return false;
	}
		
	public static boolean isItemFuel(ItemStack item) {
		if(item.getItem() == Item.getItemFromBlock(Blocks.COAL_BLOCK)) return true;
		return false;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public int getField(int id) 
	{
		switch(id)
		{
		case 0:
			return this.fuelLeft;
		case 1:
			return this.cookProgress;
		case 2:
			return this.timeToCook;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id)
		{
		case 0:
			this.fuelLeft = value;
			break;
		case 1:
			this.cookProgress = value;
			break;
		case 2:
			this.timeToCook = value;
			break;
		}
	}

	@Override
	public int getSizeInventory() {
		return this.handler.getSlots();
	}

	@Override
	public boolean isEmpty() {
        for (int i = 0; i < getFieldCount(); i++) {
        	if(!this.handler.getStackInSlot(i).isEmpty()) {
        		return false;
        	}
        }
        return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.handler.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
	    NonNullList<ItemStack> steelFurnaceItemStacks = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
        for (int i = 0; i < getFieldCount(); i++) {
        	if(!this.handler.getStackInSlot(i).isEmpty()) {
    		    steelFurnaceItemStacks.set(i, this.handler.getStackInSlot(i));
    		}
        }
		return ItemStackHelper.getAndSplit(steelFurnaceItemStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
	    NonNullList<ItemStack> steelFurnaceItemStacks = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
        for (int i = 0; i < getFieldCount(); i++) {
        	if(!this.handler.getStackInSlot(i).isEmpty()) {
    		    steelFurnaceItemStacks.set(i, this.handler.getStackInSlot(i));
    		}
        }
		return ItemStackHelper.getAndRemove(steelFurnaceItemStacks, index);
	}

	@Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.handler.getStackInSlot(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.handler.setStackInSlot(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !flag)
        {
            this.timeToCook = 200;
            this.cookProgress = 0;
            this.markDirty();
        }
    }

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if (index == 3)
        {
            return false;
        }
        else if (index == 0 && stack.getItem() == Items.COAL)
        {
            return true;
        }
        else if (index == 1 && stack.getItem() == Items.IRON_INGOT ) {
        	return true;
        }
        else if (index == 2 && stack.getItem() == Item.getItemFromBlock(Blocks.COAL_BLOCK)) {
            return true;
        }
        return false;
    }

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
    public void clear()
    {
        //this.handler.setStackInSlot(0, new ItemStack(blockType).EMPTY);
        //this.handler.setStackInSlot(1, new ItemStack(blockType).EMPTY);
        //this.handler.setStackInSlot(2, new ItemStack(blockType).EMPTY);
        //this.handler.setStackInSlot(3, new ItemStack(blockType).EMPTY);
    }

	@Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.steel_furnace";
    }

	@Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        if (side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
        {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }

	@Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

	@Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 3)
        {
            Item item = stack.getItem();

            if (item != ModItems.STEEL_INGOT)
            {
                return false;
            }
        }

        return true;
    }
	
    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);
}