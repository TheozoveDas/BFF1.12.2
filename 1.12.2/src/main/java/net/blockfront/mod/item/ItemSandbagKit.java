/*    */ package net.blockfront.mod.item;
/*    */ import net.blockfront.mod.BlockFrontBlocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemSandbagKit extends Item {
/*    */   public ItemSandbagKit() {
/* 15 */     setMaxStackSize(1);
/* 16 */     setMaxDamage(10);
/* 17 */     isDamageable();
/*    */   }
private void isDamageable() {
	// TODO Auto-generated method stub
	
}
private void setMaxDamage(int i) {
	// TODO Auto-generated method stub
	
}
private void setMaxStackSize(int i) {
	// TODO Auto-generated method stub
	
}
/*    */   
/*    */   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
/* 21 */     if (par7 != 1) {
/* 22 */       return false;
/*    */     }
/*    */     
/* 25 */     if (world.getBlock(x, y + 1, z) == Blocks.air) {
/* 26 */       stack.damageItem(1, (EntityLivingBase)player);
/* 27 */       world.setBlock(x, y + 1, z, BlockFrontBlocks.blockSandbagStack);
/* 28 */       world.notifyBlockOfNeighborChange(x, y + 1, z, BlockFrontBlocks.blockSandbagStack);
/* 29 */       world.playSoundAtEntity((Entity)player, "bff:item.sandbagkit.placebag", 1.0F, 1.0F);
/* 30 */       return true;
/* 31 */     }  if (world.getBlock(x, y + 1, z) == Blocks.tallgrass) {
/* 32 */       stack.damageItem(1, (EntityLivingBase)player);
/* 33 */       world.setBlock(x, y + 1, z, BlockFrontBlocks.blockSandbagStackBeige);
/* 34 */       world.notifyBlockOfNeighborChange(x, y + 1, z, BlockFrontBlocks.blockSandbagStack);
/* 35 */       world.playSoundAtEntity((Entity)player, "bff:item.sandbagkit.placebag", 1.0F, 1.0F);
/* 36 */       return true;
/*    */     } 
/* 38 */     player.sendMessage((ITextComponent)new ITextComponent(TextFormatting.RED + "You cannot place Sandbags there!"));
/* 39 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\item\ItemSandbagKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */