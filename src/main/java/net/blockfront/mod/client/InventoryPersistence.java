/*    */ package net.blockfront.mod.client;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.blockfront.mod.client.character.CharacterPlayerFactory;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.CompressedStreamTools;
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import org.apache.commons.io.FileUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryPersistence
/*    */ {
/*    */   
/*    */   public static void injectPersistedInventory(File directory, CharacterPlayerFactory playerFactory) {
/*    */     NBTTagCompound invNbt;
/* 23 */     File file = new File(directory, "BlockFront.invPersist.nbt");
/*    */     
/*    */     try {
/* 26 */       invNbt = CompressedStreamTools.read(file);
/* 27 */     } catch (IOException e) {
/*    */       try {
/* 29 */         FileUtils.forceDelete(file);
/* 30 */       } catch (IOException iOException) {}
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     if (invNbt != null) {
/* 35 */       (playerFactory.getPlayer()).inventory.readFromNBT(invNbt.getTagList("inv", 10));
/*    */     }
/*    */   }
/*    */   
/*    */   public static void saveInventory(File directory, EntityPlayer player, CharacterPlayerFactory factory) {
/* 40 */     NBTTagCompound nbt = new NBTTagCompound();
/* 41 */     NBTTagList nbtList = player.inventory.writeToNBT(new NBTTagList());
/* 42 */     nbt.setTag("inv", (NBTBase)nbtList);
/*    */     
/* 44 */     File file = new File(directory, "BlockFront.invPersist.nbt");
/*    */     try {
/* 46 */       if (file.exists()) {
/*    */         try {
/* 48 */           FileUtils.forceDelete(file);
/* 49 */         } catch (IOException iOException) {}
/*    */       }
/* 51 */       CompressedStreamTools.write(nbt, file);
/* 52 */     } catch (IOException x) {
/* 53 */       BlockFront.log.error("Failed to persist inventory", x);
/*    */     } 
/*    */     
/* 56 */     if (factory != null)
/* 57 */       (factory.getPlayer()).inventory.readFromNBT(nbtList); 
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\InventoryPersistence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */