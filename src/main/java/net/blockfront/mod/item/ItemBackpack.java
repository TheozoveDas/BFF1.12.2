/*    */ package net.blockfront.mod.item;
/*    */ 
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.blockfront.mod.BlockFrontTabs;
/*    */ import net.blockfront.mod.backpack.BackpackSize;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBackpack
/*    */   extends Item
/*    */ {
/*    */   public final BackpackSize size;
/*    */   
/*    */   public ItemBackpack(BackpackSize size) {
/* 19 */     this.size = size;
/* 20 */     setMaxDamage(1);
/*    */     
/* 22 */     setMaxStackSize(1);
/*    */     
/* 24 */     setCreativeTab(BlockFrontTabs.tabBlockFrontItems);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxItemUseDuration(ItemStack stack) {
/* 30 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
/* 35 */     if (!world.isRemote)
/*    */     {
/* 37 */       if (!player.isSneaking()) {
/* 38 */         player.openGui(BlockFront.instance, 0, world, 0, 0, 0);
/*    */       }
/*    */     }
/*    */ 
/*    */     
/* 43 */     return itemstack;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\item\ItemBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */