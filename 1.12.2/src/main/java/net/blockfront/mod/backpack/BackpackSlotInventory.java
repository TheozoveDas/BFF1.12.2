/*     */ package net.blockfront.mod.backpack;
/*     */ 
/*     */ import net.blockfront.mod.BlockFrontItems;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IExtendedEntityProperties;
/*     */ 
/*     */ public class BackpackSlotInventory
/*     */   implements IExtendedEntityProperties, IInventory {
/*     */   private static final String IDENTIFIER = "BlockFront.backpackprops";
/*     */   public ItemStack previousStack;
/*     */   public ItemStack stack;
/*     */   private static final String NBT_KEY = "BlockFront.bp";
/*     */   
/*     */   public static BackpackSlotInventory get(EntityPlayer player) {
/*  21 */     return (BackpackSlotInventory)player.getExtendedProperties("BlockFront.backpackprops");
/*     */   }
/*     */   
/*     */   public static void register(EntityPlayer player) {
/*  25 */     player.registerExtendedProperties("BlockFront.backpackprops", new BackpackSlotInventory());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  34 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/*  39 */     return this.stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/*  44 */     return Inventories.decreaseStackSize(this, index, count);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/*  49 */     ItemStack s = this.stack;
/*  50 */     this.stack = null;
/*  51 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/*  56 */     this.stack = stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInventoryName() {
/*  61 */     return "BlockFront.backpack";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomInventoryName() {
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/*  71 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer player) {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void openInventory() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeInventory() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValidForSlot(int index, ItemStack stack) {
/*  96 */     return (stack.getItem() == BlockFrontItems.itemBackpackLarge || stack
/*  97 */       .getItem() == BlockFrontItems.itemBackpackMedium || stack
/*  98 */       .getItem() == BlockFrontItems.itemBackpackSmall);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveNBTData(NBTTagCompound nbt) {
/* 105 */     if (this.stack != null) {
/* 106 */       NBTTagCompound stackNbt = this.stack.writeToNBT(new NBTTagCompound());
/* 107 */       nbt.setTag("BlockFront.bp", (NBTBase)stackNbt);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadNBTData(NBTTagCompound nbt) {
/* 113 */     if (nbt.hasKey("BlockFront.bp", 10)) {
/* 114 */       this.stack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("BlockFront.bp"));
/*     */     } else {
/* 116 */       this.stack = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void init(Entity entity, World world) {}
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\backpack\BackpackSlotInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */