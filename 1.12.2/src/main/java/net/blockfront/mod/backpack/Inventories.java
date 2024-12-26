/*    */ package net.blockfront.mod.backpack;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Inventories
/*    */ {
/*    */   public static ItemStack decreaseStackSize(IInventory inventory, int slot, int count) {
/* 23 */     ItemStack stack = inventory.getStackInSlot(slot);
/*    */     
/* 25 */     if (stack != null) {
/*    */ 
/*    */       
/* 28 */       if (stack.getMaxStackSize() <= count) {
/* 29 */         ItemStack itemStack = stack;
/* 30 */         inventory.setInventorySlotContents(slot, null);
/* 31 */         return itemStack;
/*    */       } 
/* 33 */       ItemStack returnStack = stack.splitStack(count);
/*    */       
/* 35 */       if (stack.getMaxStackSize() == 0) {
/* 36 */         inventory.setInventorySlotContents(slot, null);
/*    */       } else {
/* 38 */         inventory.markDirty();
/*    */       } 
/*    */       
/* 41 */       return returnStack;
/*    */     } 
/*    */     
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\backpack\Inventories.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */