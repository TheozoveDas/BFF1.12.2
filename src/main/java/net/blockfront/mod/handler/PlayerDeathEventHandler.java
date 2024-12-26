/*    */ package net.blockfront.mod.handler;
/*    */ 
/*    */ import java.util.Map;
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.blockfront.mod.entity.EntityLootableBody;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerDeathEventHandler
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void playerDeathEvent(PlayerDropsEvent event) {
/* 23 */     EntityPlayer player = (EntityPlayer)event.entity;
/* 24 */     World w = event.entity.worldObj;
/*    */     
/* 26 */     if (player.worldObj.isRemote || !event.isCanceled());
/*    */ 
/*    */ 
/*    */     
/* 30 */     float rotation = player.getRotationYawHead();
/* 31 */     EntityLootableBody corpse = new EntityLootableBody(w);
/* 32 */     corpse.setPositionAndRotation(player.posX, player.posY, player.posZ, rotation, 0.0F);
/* 33 */     corpse.setDeathTime(w.getTotalWorldTime());
/*    */     
/* 35 */     if (!w.getGameRules().getGameRuleBooleanValue("keepInventory")) {
/*    */       
/* 37 */       corpse.setCurrentItemOrArmor(0, EntityLootableBody.applyItemDamage(withdrawHeldItem(player))); int i;
/* 38 */       for (i = 0; i < 4; i++) {
/* 39 */         Map<Integer, Integer> enchantments = (player.inventory.armorInventory[i] != null) ? EnchantmentHelper.getEnchantments(player.inventory.armorInventory[i]) : null;
/* 40 */         if (enchantments != null && enchantments.containsKey(Integer.valueOf(BlockFront.eioSoulboundID))) {
/* 41 */           System.out.println("Armor item: This item should not be in the corpse");
/*    */         } else {
/*    */           
/* 44 */           corpse.setCurrentItemOrArmor(i + 1, EntityLootableBody.applyItemDamage(player.getCurrentArmor(i)));
/* 45 */           player.inventory.armorInventory[i] = null;
/*    */         } 
/*    */       } 
/* 48 */       for (i = 0; i < player.inventory.mainInventory.length; i++) {
/* 49 */         Map<Integer, Integer> enchantments = (player.inventory.mainInventory[i] != null) ? EnchantmentHelper.getEnchantments(player.inventory.mainInventory[i]) : null;
/* 50 */         if (enchantments != null && enchantments.containsKey(Integer.valueOf(BlockFront.eioSoulboundID))) {
/* 51 */           System.out.println("Inventory item: This item should not be in the corpse");
/*    */         } else {
/*    */           
/* 54 */           corpse.vacuumItem(player.inventory.mainInventory[i]);
/* 55 */           player.inventory.mainInventory[i] = null;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 60 */     w.spawnEntityInWorld((Entity)corpse);
/* 61 */     corpse.setRotation(rotation);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static ItemStack withdrawHeldItem(EntityPlayer player) {
/* 67 */     ItemStack item = player.getHeldItem();
/* 68 */     if (item == null) return null; 
/* 69 */     Map<Integer, Integer> enchantments = EnchantmentHelper.getEnchantments(item);
/* 70 */     if (enchantments != null && enchantments.containsKey(Integer.valueOf(BlockFront.eioSoulboundID))) {
/* 71 */       System.out.println(item.getDisplayName() + ": This item should not be in the corpse");
/* 72 */       return null;
/*    */     } 
/*    */     
/* 75 */     for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/* 76 */       if (player.inventory.getStackInSlot(i) != null && 
/* 77 */         item == player.inventory.getStackInSlot(i)) {
/* 78 */         player.inventory.setInventorySlotContents(i, null);
/*    */         break;
/*    */       } 
/*    */     } 
/* 82 */     return item;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\handler\PlayerDeathEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */