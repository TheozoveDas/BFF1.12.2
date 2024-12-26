/*     */ package net.blockfront.mod.client;
/*     */ 
/*     */ import net.blockfront.mod.backpack.BackpackSize;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryBackpack
/*     */   implements IInventory
/*     */ {
/*     */   public final BackpackSize size;
/*     */   private ItemStack[] inventory;
/*     */   public final ItemStack invItem;
/*     */   private final IInventory inv;
/*     */   private final int slot;
/*     */   
/*     */   public InventoryBackpack(IInventory inv, int slot, BackpackSize size) {
/*  25 */     this.size = size;
/*  26 */     ItemStack stack = inv.getStackInSlot(slot);
/*  27 */     this.invItem = stack;
/*     */     
/*  29 */     this.inv = inv;
/*  30 */     this.slot = slot;
/*  31 */     this.inventory = new ItemStack[size.invSize];
/*     */     
/*  33 */     if (!stack.hasTagCompound()) {
/*  34 */       stack.setTagCompound(new NBTTagCompound());
/*     */     }
/*     */     
/*  37 */     readFromNBT(stack.getTagCompound());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  42 */     return this.inventory.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slot) {
/*  47 */     return this.inventory[slot];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomInventoryName() {
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int slot, int amount) {
/*  57 */     ItemStack stack = getStackInSlot(slot);
/*     */     
/*  59 */     if (stack != null) {
/*  60 */       if (stack.getMaxStackSize() > amount) {
/*  61 */         stack = stack.splitStack(amount);
/*  62 */         markDirty();
/*     */       } else {
/*  64 */         setInventorySlotContents(slot, null);
/*     */       } 
/*     */     }
/*  67 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int slot) {
/*  72 */     ItemStack stack = getStackInSlot(slot);
/*  73 */     if (stack != null) {
/*  74 */       setInventorySlotContents(slot, null);
/*     */     }
/*  76 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int slot, ItemStack itemstack) {
/*  81 */     this.inventory[slot] = itemstack;
/*     */     
/*  83 */     if (itemstack != null && itemstack.getMaxStackSize() > 
/*  84 */       getInventoryStackLimit()) {
/*  85 */       itemstack.getMaxStackSize();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryName() {
/*  91 */     return "Container inventory";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/*  96 */     return 64;
/*     */   }
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 101 */     for (int i = 0; i < getSizeInventory(); i++) {
/*     */       
/* 103 */       if (getStackInSlot(i) != null && (getStackInSlot(i)).getMaxStackSize() == 0) {
/* 104 */         this.inventory[i] = null;
/*     */       }
/*     */     } 
/*     */     
/* 108 */     writeToNBT(this.invItem.getTagCompound());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer player) {
/* 113 */     return (this.inv.getStackInSlot(this.slot) == this.invItem);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void openInventory() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeInventory() {}
/*     */ 
/*     */   
/*     */   public boolean isItemValidForSlot(int p_94041_1_, ItemStack itemstack) {
/* 126 */     return true;
/*     */   }
/*     */   
/*     */   public void readFromNBT(NBTTagCompound tagcompound) {
/* 130 */     NBTTagList nbttaglist = tagcompound.getTagList("InvItems", 10);
/*     */ 
/*     */     
/* 133 */     this.inventory = new ItemStack[getSizeInventory()];
/*     */     
/* 135 */     for (int i = 0; i < nbttaglist.tagCount(); i++) {
/* 136 */       NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
/* 137 */       byte b0 = nbttagcompound1.getByte("Slot");
/*     */       
/* 139 */       if (b0 >= 0 && b0 < this.inventory.length) {
/* 140 */         this.inventory[b0] = 
/* 141 */           ItemStack.loadItemStackFromNBT(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeToNBT(NBTTagCompound tagcompound) {
/* 147 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 149 */     for (int i = 0; i < this.inventory.length; i++) {
/* 150 */       if (this.inventory[i] != null) {
/* 151 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 152 */         nbttagcompound1.setByte("Slot", (byte)i);
/* 153 */         this.inventory[i].writeToNBT(nbttagcompound1);
/* 154 */         nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/*     */     
/* 158 */     tagcompound.setTag("InvItems", (NBTBase)nbttaglist);
/*     */   }
/*     */
@Override
public String getName() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public boolean hasCustomName() {
	// TODO Auto-generated method stub
	return false;
}
@Override
public ITextComponent getDisplayName() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
}
@Override
public ItemStack removeStackFromSlot(int index) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public boolean isUsableByPlayer(EntityPlayer player) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public void openInventory(EntityPlayer player) {
	// TODO Auto-generated method stub
	
}
@Override
public void closeInventory(EntityPlayer player) {
	// TODO Auto-generated method stub
	
}
@Override
public int getField(int id) {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public void setField(int id, int value) {
	// TODO Auto-generated method stub
	
}
@Override
public int getFieldCount() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public void clear() {
	// TODO Auto-generated method stub
	
} }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\InventoryBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */