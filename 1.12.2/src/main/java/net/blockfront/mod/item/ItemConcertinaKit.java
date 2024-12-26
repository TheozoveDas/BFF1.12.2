/*    */ package net.blockfront.mod.item;
/*    */ import net.blockfront.mod.BlockFrontBlocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemConcertinaKit extends Item {
/*    */   public ItemConcertinaKit() {
/* 16 */     setMaxStackSize(1);
/* 17 */     setMaxDamage(10);
/* 18 */     isDamageable();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
/* 23 */     if (par7 != 1) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     if (world.getBlock(x, y + 1, z) == Blocks.air) {
/* 28 */       stack.damageItem(1, (EntityLivingBase)player);
/* 29 */       world.setBlock(x, y + 1, z, BlockFrontBlocks.blockBarbedWire);
/* 30 */       world.notifyBlockOfNeighborChange(x, y + 1, z, BlockFrontBlocks.blockBarbedWire);
/* 31 */       world.playSoundAtEntity((Entity)player, "bff:item.concertinakit.placewire", 1.0F, 1.0F);
/* 32 */       return true;
/* 33 */     }  if (world.getBlock(x, y + 1, z) == Blocks.tallgrass) {
/* 34 */       stack.damageItem(1, (EntityLivingBase)player);
/* 35 */       world.setBlock(x, y + 1, z, BlockFrontBlocks.blockBarbedWire);
/* 36 */       world.notifyBlockOfNeighborChange(x, y + 1, z, BlockFrontBlocks.blockBarbedWire);
/* 37 */       world.playSoundAtEntity((Entity)player, "bff:item.concertinakit.placewire", 1.0F, 1.0F);
/* 38 */       return true;
/*    */     } 
/* 40 */     player.sendMessage((ITextComponent)new ITextComponent(TextFormatting.RED + "You cannot place Barbed Wire there!"));
/* 41 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\item\ItemConcertinaKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */