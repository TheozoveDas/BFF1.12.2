/*     */ package net.blockfront.mod.client;
/*     */ 
/*     */ import net.blockfront.mod.inv.Containers;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerBackpack
/*     */   extends Container
/*     */ {
/*     */   public final InventoryBackpack inventory;
/*     */   
/*     */   public ContainerBackpack(InventoryPlayer inventoryPlayer, InventoryBackpack inventoryItem) {
/*  25 */     this.inventory = inventoryItem;
inventoryItem.getSizeInventory();
/*     */     
/*     */     int i;
/*     */     
/*  34 */     for (i = 0; i < inventoryItem.getSizeInventory(); i++) {
/*  35 */       addSlotToContainer(new SlotBackpack(this.inventory, i, 80 + 18 * i / 4, 8 + 18 * i % 4));
/*     */     }
/*     */     
/*  38 */     for (i = 0; i < 3; i++) {
/*  39 */       for (int j = 0; j < 9; j++) {
/*  40 */         addSlotToContainer(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  44 */     for (i = 0; i < 9; i++) {
/*  45 */       addSlotToContainer(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer entityplayer) {
/*  51 */     return this.inventory.isUseableByPlayer(entityplayer);
/*     */   }
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer player, int index) {
/*  55 */     return Containers.handleShiftClick(this, player, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack slotClick(int slot, int button, ClickType flag, EntityPlayer player) {
/* 181 */     if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == this.inventory.invItem) {
/* 182 */       return null;
/*     */     }
/*     */     
/* 185 */     return super.slotClick(slot, button, flag, player);
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\ContainerBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */