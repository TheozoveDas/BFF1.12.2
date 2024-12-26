/*    */ package net.blockfront.mod;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFrontTabs
/*    */ {
/* 12 */   public static CreativeTabs tabBlockFrontBlocks = new CreativeTabs("tabBlockFrontBlocks")
/*    */     {
/*    */       public Item getTabIconItem() {
/* 15 */         return Item.getItemFromBlock(BlockFrontBlocks.blockSandbagStack);
/*    */       }
/*    */     };
/* 18 */   public static CreativeTabs tabBlockFrontItems = new CreativeTabs("tabBlockFrontItems")
/*    */     {
/*    */       public Item getTabIconItem() {
/* 21 */         return BlockFrontItems.itemBackpackMedium;
/*    */       }
/*    */     };
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\BlockFrontTabs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */