/*    */ package net.blockfront.mod.client;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotBackpack
/*    */   extends Slot
/*    */ {
/*    */   SlotBackpack(IInventory iinventory, int i, int j, int k) {
/* 14 */     super(iinventory, i, j, k);
/*    */   }
/*    */   
/*    */   public int getSlotStackLimit() {
/* 18 */     return 64;
/*    */   }
/*    */   
/*    */   public boolean isItemValid(ItemStack itemstack) {
/* 22 */     return (itemstack != null && !(itemstack.getItem() instanceof net.blockfront.mod.item.ItemBackpack));
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\SlotBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */