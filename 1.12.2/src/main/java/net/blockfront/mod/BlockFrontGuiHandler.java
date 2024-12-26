/*    */ package net.blockfront.mod;
/*    */ import net.blockfront.mod.backpack.BackpackSlotInventory;
/*    */ import net.blockfront.mod.client.ContainerBackpack;
import net.blockfront.mod.client.InventoryBackpack;
/*    */ import net.blockfront.mod.client.gui.GuiBackpack;
/*    */ import net.blockfront.mod.item.ItemBackpack;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockFrontGuiHandler implements net.minecraftforge.fml.common.network.IGuiHandler {
/*    */   public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
/*    */     BackpackSlotInventory backpackSlotInventory = null;
/*    */     int slot;
/*    */     ItemStack backpackItem;
/*    */     ItemBackpack backpack;
/* 20 */     switch (guiId) {
/*    */ 
/*    */ 
/*    */       
/*    */       case 0:
/*    */       case 1:
/* 26 */         if (guiId == 0) {
/* 28 */           slot = player.inventory.currentItem;
/*    */         } else {
/* 30 */           backpackSlotInventory = BackpackSlotInventory.get(player);
/* 31 */           slot = 0;
/*    */         } 
/* 33 */         backpackItem = backpackSlotInventory.getStackInSlot(slot);
/* 34 */         if (backpackItem == null || !(backpackItem.getItem() instanceof ItemBackpack)) {
/* 35 */           return null;
/*    */         }
/*    */         
/* 38 */         backpack = (ItemBackpack)backpackItem.getItem();
/* 39 */         if (!world.isRemote) {
/* 40 */           world.playSound(player, null, null, null, z, slot);
/*    */         }
/* 42 */         return new ContainerBackpack(player.inventory, new InventoryBackpack((IInventory)backpackSlotInventory, slot, backpack.size));
/*    */     } 
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
/*    */     ContainerBackpack container;
/* 50 */     switch (guiId) {
/*    */       case 0:
/*    */       case 1:
/* 53 */         container = (ContainerBackpack)getServerGuiElement(guiId, player, world, x, y, z);
/* 54 */         return (container == null) ? null : new GuiBackpack(container);
/*    */     } 
/* 56 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\BlockFrontGuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */