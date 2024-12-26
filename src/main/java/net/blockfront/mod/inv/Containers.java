/*     */ package net.blockfront.mod.inv;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.oredict.OreDictionary;
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
/*     */ public final class Containers
/*     */ {
/*     */   public static ItemStack handleShiftClick(Container container, EntityPlayer player, int slotIndex) {
/*  37 */     List<Slot> slots = container.inventorySlots;
/*  38 */     Slot sourceSlot = slots.get(slotIndex);
/*  39 */     ItemStack inputStack = sourceSlot.getStack();
/*  40 */     if (inputStack == null) {
/*  41 */       return null;
/*     */     }
/*     */     
/*  44 */     boolean sourceIsPlayer = (sourceSlot.inventory == player.inventory);
/*     */     
/*  46 */     ItemStack copy = inputStack.copy();
/*     */     
/*  48 */     if (sourceIsPlayer) {
/*  49 */       if (container instanceof SpecialShiftClick) {
/*  50 */         ShiftClickTarget target = ((SpecialShiftClick)container).getShiftClickTarget(inputStack, player);
/*  51 */         if (!target.isStandard()) {
/*  52 */           if (target.isNone() || !mergeToTarget(player.inventory, sourceSlot, slots, target)) {
/*  53 */             return null;
/*     */           }
/*  55 */           return copy;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  60 */       if (!mergeStack(player.inventory, false, sourceSlot, slots, false)) {
/*  61 */         return null;
/*     */       }
/*  63 */       return copy;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  68 */     boolean isMachineOutput = !sourceSlot.isItemValid(inputStack);
/*  69 */     if (!mergeStack(player.inventory, true, sourceSlot, slots, !isMachineOutput)) {
/*  70 */       return null;
/*     */     }
/*  72 */     return copy;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean stacksEqual(ItemStack a, ItemStack b) {
/*  78 */     return (OreDictionary.itemMatches(b, a, true) && ItemStack.areItemStackTagsEqual(a, b));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean mergeToTarget(InventoryPlayer playerInv, Slot sourceSlot, List<Slot> slots, ShiftClickTarget target) {
/*  84 */     ItemStack sourceStack = sourceSlot.getStack();
/*  85 */     int originalSize = sourceStack.getMaxStackSize();
/*     */ 
/*     */     
/*  88 */     target.reset();
/*  89 */     while (sourceStack.getMaxStackSize() > 0 && target.hasNext()) {
/*  90 */       Slot targetSlot = slots.get(target.next());
/*  91 */       if (targetSlot.inventory != playerInv) {
/*  92 */         ItemStack targetStack = targetSlot.getStack();
/*  93 */         if (stacksEqual(targetStack, sourceStack)) {
/*  94 */           int targetMax = Math.min(targetSlot.getSlotStackLimit(), targetStack.getMaxStackSize());
/*  95 */           int toTransfer = Math.min(sourceStack.getMaxStackSize(), targetMax - targetStack.getMaxStackSize());
/*  96 */           if (toTransfer > 0) {
/*  97 */             targetStack.stackSize += toTransfer;
/*  98 */             sourceStack.stackSize -= toTransfer;
/*  99 */             targetSlot.onSlotChanged();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 104 */     if (sourceStack.getMaxStackSize() == 0) {
/* 105 */       sourceSlot.putStack(null);
/* 106 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 110 */     target.reset();
/* 111 */     while (target.hasNext()) {
/* 112 */       Slot targetSlot = slots.get(target.next());
/* 113 */       if (targetSlot.inventory != playerInv && !targetSlot.getHasStack() && targetSlot.isItemValid(sourceStack)) {
/* 114 */         targetSlot.putStack(sourceStack);
/* 115 */         sourceSlot.putStack(null);
/* 116 */         return true;
/*     */       } 
/*     */     } 
/* 119 */     if (originalSize != sourceStack.getMaxStackSize()) {
/* 120 */       sourceSlot.onSlotChanged();
/* 121 */       return true;
/*     */     } 
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean mergeStack(InventoryPlayer playerInv, boolean mergeIntoPlayer, Slot sourceSlot, List<Slot> slots, boolean reverse) {
/* 129 */     ItemStack sourceStack = sourceSlot.getStack();
/*     */     
/* 131 */     int originalSize = sourceStack.getMaxStackSize();
/*     */     
/* 133 */     int len = slots.size();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     if (sourceStack.isStackable()) {
/* 139 */       int i = reverse ? (len - 1) : 0;
/*     */       
/* 141 */       while (sourceStack.getMaxStackSize() > 0 && (reverse ? (i >= 0) : (i < len))) {
/* 142 */         Slot targetSlot = slots.get(i);
/* 143 */         if (((targetSlot.inventory == playerInv)) == mergeIntoPlayer) {
/* 144 */           ItemStack target = targetSlot.getStack();
/* 145 */           if (stacksEqual(sourceStack, target)) {
/* 146 */             int targetMax = Math.min(targetSlot.getSlotStackLimit(), target.getMaxStackSize());
/* 147 */             int toTransfer = Math.min(sourceStack.getMaxStackSize(), targetMax - target.getMaxStackSize());
/* 148 */             if (toTransfer > 0) {
/* 149 */               target.stackSize += toTransfer;
/* 150 */               sourceStack.stackSize -= toTransfer;
/* 151 */               targetSlot.onSlotChanged();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 156 */         if (reverse) {
/* 157 */           i--; continue;
/*     */         } 
/* 159 */         i++;
/*     */       } 
/*     */       
/* 162 */       if (sourceStack.getMaxStackSize() == 0) {
/* 163 */         sourceSlot.putStack(null);
/* 164 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 169 */     int idx = reverse ? (len - 1) : 0;
/* 170 */     while (reverse ? (idx >= 0) : (idx < len)) {
/* 171 */       Slot targetSlot = slots.get(idx);
/* 172 */       if (((targetSlot.inventory == playerInv)) == mergeIntoPlayer && 
/* 173 */         !targetSlot.getHasStack() && targetSlot.isItemValid(sourceStack)) {
/* 174 */         targetSlot.putStack(sourceStack);
/* 175 */         sourceSlot.putStack(null);
/* 176 */         return true;
/*     */       } 
/*     */       
/* 179 */       if (reverse) {
/* 180 */         idx--; continue;
/*     */       } 
/* 182 */       idx++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 187 */     if (sourceStack.getMaxStackSize() != originalSize) {
/* 188 */       sourceSlot.onSlotChanged();
/* 189 */       return true;
/*     */     } 
/* 191 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\inv\Containers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */