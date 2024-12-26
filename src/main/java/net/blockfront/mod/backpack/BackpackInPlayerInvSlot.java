/*    */ package net.blockfront.mod.backpack;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BackpackInPlayerInvSlot
/*    */   extends Slot
/*    */ {
/*    */   public BackpackInPlayerInvSlot(IInventory inv, int index, int x, int y) {
/* 13 */     super(inv, index, x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isItemValid(ItemStack stack) {
/* 18 */     return this.inventory.isItemValidForSlot(getSlotIndex(), stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\backpack\BackpackInPlayerInvSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */