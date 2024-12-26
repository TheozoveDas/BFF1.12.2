/*     */ package net.blockfront.mod.entity;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.Deque;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.blockfront.mod.BlockFront;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.StringUtils;
import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ public class EntityLootableBody
/*     */   extends EntityLiving implements IInventory {
/*     */   public static final int INVENTORY_SIZE = 54;
/*  35 */   public static int additionalItemDamage = 10;
/*  36 */   public static float corpseHP = 5.0F;
/*     */   
/*     */   public static boolean hurtByFire = true;
/*     */   
/*     */   public static boolean hurtByBlast = true;
/*     */   
/*     */   public static boolean hurtByFall = true;
/*     */   public static boolean hurtByCactus = true;
/*     */   public static boolean hurtByWeapons = true;
/*     */   public static boolean hurtByBlockSuffocation = true;
/*     */   public static boolean hurtByAll = true;
/*     */   public static boolean hurtByOther = true;
/*     */   public static boolean invulnerable = false;
/*     */   static final byte VACUUM_TIMELIMIT = 20;
/*     */   static final int VACUUM_RADIUS = 3;
/*     */   static final int WATCHER_ID_OWNER = 28;
/*  52 */   private static final DamageSource selfDestruct = new DamageSource(EntityLootableBody.class.getSimpleName());
/*     */ 
/*     */   
/*  55 */   protected final ItemStack[] equipment = new ItemStack[54];
/*  56 */   protected final Deque<ItemStack> auxInventory = new LinkedList<>();
/*  57 */   private byte vacuumTime = 0;
/*  58 */   private GameProfile owner = null;
/*  59 */   private int shovelHits = 0;
/*     */   
/*     */   private static final int shovelHitLimit = 3;
/*  62 */   private long deathTimestamp = Long.MAX_VALUE;
/*     */   
/*     */   private boolean deadMode = false;
/*     */   
/*     */   public EntityLootableBody(World w) {
/*  67 */     super(w);
/*  68 */     setSize(0.85F, 0.75F);
/*  69 */     this.isImmuneToFire = (!hurtByFire || invulnerable);
/*  70 */     this.vacuumTime = 0;
/*  71 */     getDataWatcher().addObject(28, "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  77 */     super.applyEntityAttributes();
/*  78 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(corpseHP);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  83 */     super.entityInit();
/*     */   }
/*     */   
/*     */   public float getRotation() {
/*  87 */     return this.rotationYaw;
/*     */   }
/*     */   
/*     */   public void setRotation(float newRot) {
/*  91 */     this.rotationYaw = newRot;
/*  92 */     this.renderYawOffset = this.rotationYaw;
/*  93 */     this.prevRenderYawOffset = this.rotationYaw;
/*  94 */     this.prevRotationYaw = this.rotationYaw;
/*  95 */     this.newRotationYaw = this.rotationYaw;
/*  96 */     if (this.worldObj.isRemote) {
/*  97 */       setRotationYawHead(this.rotationYaw);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateEntityActionState() {}
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 107 */     return false;
/*     */   }
/*     */   
/*     */   public void setOwner(GameProfile gp) {
/*     */     try {
/* 112 */       this.owner = gp;
/* 113 */       updatePlayerProfile();
/* 114 */       if (gp.getName() != null) {
/* 115 */         getDataWatcher().updateObject(28, gp.getName());
/*     */       } else {
/* 117 */         getDataWatcher().updateObject(28, "");
/*     */       } 
/* 119 */     } catch (Exception e) {
/* 120 */       System.err.println("Error: " + e);
/* 121 */       getDataWatcher().updateObject(28, "");
/*     */     } 
/*     */   }
/*     */   
/*     */   public GameProfile getOwner() {
/* 126 */     if (this.owner == null) {
/* 127 */       this.owner = getGameProfileFromName(getDataWatcher().getWatchableObjectString(28));
/* 128 */     } else if (getDataWatcher().getWatchableObjectString(28).isEmpty()) {
/* 129 */       this.owner = null;
/*     */     } 
/* 131 */     return this.owner;
/*     */   }
/*     */   
/*     */   private void updatePlayerProfile() {
/* 135 */     if (this.owner == null || StringUtils.isNullOrEmpty(this.owner.getName())) {
/*     */       return;
/*     */     }
/* 138 */     if (this.owner.isComplete() && this.owner.getProperties().containsKey("textures")) {
/*     */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   private GameProfile getGameProfileFromName(String name) {
/* 144 */     if (name == null || name.isEmpty()) {
/* 145 */       return null;
/*     */     }
/* 147 */     GameProfile gp = new GameProfile((UUID)null, name);
/* 148 */     return gp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEntityUpdate() {
/* 153 */     super.onEntityUpdate();
/* 154 */     if (BlockFront.allowCorpseDecay && !this.worldObj.isRemote && this.worldObj.getWorldTime() % 20L == 0L) {
/*     */       
/* 156 */       if (BlockFront.decayOnlyWhenEmpty) {
/* 157 */         for (int i = 0; i < this.equipment.length; i++) {
/* 158 */           if (this.equipment[i] != null) {
/* 159 */             this.deathTimestamp = this.worldObj.getTotalWorldTime();
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/* 164 */       if (this.worldObj.getTotalWorldTime() - this.deathTimestamp > BlockFront.corpseDecayTime) {
/* 165 */         dropEquipment(true, 0);
/* 166 */         kill();
/*     */       } 
/*     */     } 
/* 169 */     if (this.vacuumTime < 20) {
/* 170 */       if (!this.worldObj.isRemote && notFull()) {
/* 171 */         double x1 = this.posX - 3.0D;
/* 172 */         double y1 = this.posY - 3.0D;
/* 173 */         double z1 = this.posZ - 3.0D;
/* 174 */         double x2 = this.posX + 3.0D;
/* 175 */         double y2 = this.posY + 3.0D;
/* 176 */         double z2 = this.posZ + 3.0D;
/* 177 */         List<Entity> ae = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(x1, y1, z1, x2, y2, z2));
/* 178 */         if (!ae.isEmpty()) {
/* 179 */           for (int n = ae.size() - 1; n >= 0; n--) {
/* 180 */             Entity e = ae.get(n);
/* 181 */             ItemStack leftover = vacuumItem(((EntityItem)e).getEntityItem());
/* 182 */             this.worldObj.removeEntity(e);
/* 183 */             if (leftover != null) {
/* 184 */               entityDropItem(leftover, 0.0F);
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/* 189 */       this.vacuumTime = (byte)(this.vacuumTime + 1);
/*     */     } 
/* 191 */     shiftInventory();
/* 192 */     if (this.deadMode) {
/* 193 */       dropEquipment(true, 0);
/* 194 */       this.worldObj.removeEntity((Entity)this);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void shiftInventory() {
/* 199 */     if (!this.auxInventory.isEmpty()) {
/* 200 */       if (this.equipment[this.equipment.length - 1] == null)
/*     */       {
/* 202 */         this.equipment[this.equipment.length - 1] = this.auxInventory.pop();
/*     */       }
/* 204 */       for (int dstSlot = 5; dstSlot < this.equipment.length; dstSlot++) {
/* 205 */         if (this.equipment[dstSlot] == null) {
/*     */           
/* 207 */           int srcSlot = dstSlot + 1;
/* 208 */           while (srcSlot < this.equipment.length && this.equipment[srcSlot] == null) {
/* 209 */             srcSlot++;
/*     */           }
/* 211 */           if (srcSlot == this.equipment.length) {
/*     */             return;
/*     */           }
/*     */           
/* 215 */           this.equipment[dstSlot] = this.equipment[srcSlot];
/* 216 */           this.equipment[srcSlot] = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound root) {
/* 224 */     super.writeEntityToNBT(root);
/* 225 */     NBTTagList nbttaglist = new NBTTagList();
/* 226 */     for (int i = 0; i < this.equipment.length; i++) {
/* 227 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 228 */       if (this.equipment[i] != null) {
/* 229 */         this.equipment[i].writeToNBT(nbttagcompound1);
/*     */       }
/* 231 */       nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*     */     } 
/* 233 */     root.setTag("Equipment", (NBTBase)nbttaglist);
/* 234 */     if (!this.auxInventory.isEmpty()) {
/* 235 */       NBTTagList nbtauxtaglist = new NBTTagList();
/* 236 */       Iterator<ItemStack> iter = this.auxInventory.iterator();
/* 237 */       while (iter.hasNext()) {
/* 238 */         ItemStack itemStack = iter.next();
/* 239 */         if (itemStack == null)
/* 240 */           continue;  NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 241 */         itemStack.writeToNBT(nbttagcompound1);
/* 242 */         nbtauxtaglist.appendTag((NBTBase)nbttagcompound1);
/*     */       } 
/* 244 */       root.setTag("Aux", (NBTBase)nbtauxtaglist);
/*     */     } 
/* 246 */     if (this.vacuumTime < 20) root.setByte("Vac", this.vacuumTime); 
/* 247 */     if (this.owner != null) {
/* 248 */       NBTTagCompound nbtTagCompound = new NBTTagCompound();
/* 249 */       NBTUtil.func_152460_a(nbtTagCompound, this.owner);
/* 250 */       root.setTag("Owner", (NBTBase)nbtTagCompound);
/*     */     } 
/* 252 */     root.setLong("DeathTime", this.deathTimestamp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound root) {
/* 258 */     if (root.hasKey("Equipment", 9)) {
/* 259 */       NBTTagList nbttaglist = root.getTagList("Equipment", 10);
/* 260 */       for (int i = 0; i < this.equipment.length && i < nbttaglist.tagCount(); i++) {
/* 261 */         this.equipment[i] = ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i));
/*     */       }
/*     */     } 
/* 264 */     root.removeTag("Equipment");
/* 265 */     if (root.hasKey("Aux")) {
/* 266 */       NBTTagList nbttaglist = root.getTagList("Aux", 10);
/* 267 */       for (int i = 0; i < this.equipment.length && i < nbttaglist.tagCount(); i++) {
/* 268 */         this.auxInventory.addLast(ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i)));
/*     */       }
/*     */     } 
/* 271 */     super.readEntityFromNBT(root);
/* 272 */     if (root.hasKey("Vac")) {
/* 273 */       this.vacuumTime = root.getByte("Vac");
/*     */     } else {
/* 275 */       this.vacuumTime = 20;
/*     */     } 
/* 277 */     if (root.hasKey("DeathTime")) {
/* 278 */       this.deathTimestamp = root.getLong("DeathTime");
/*     */     }
/* 280 */     if (root.hasKey("Yaw")) {
/* 281 */       this.rotationYaw = root.getFloat("Yaw");
/*     */     }
/* 283 */     if (root.hasKey("Owner")) {
/* 284 */       setOwner(NBTUtil.func_152459_a(root.getCompoundTag("Owner")));
/*     */     }
/* 286 */     if (root.hasKey("Name")) {
/* 287 */       setOwner(new GameProfile(null, root.getString("Name")));
/*     */     }
/* 289 */     else if (root.hasKey("ExtraType", 8)) {
/* 290 */       String string = root.getString("ExtraType");
/* 291 */       if (!StringUtils.isNullOrEmpty(string)) {
/* 292 */         setOwner(new GameProfile((UUID)null, string));
/*     */       }
/*     */     } 
/* 295 */     setRotation(this.rotationYaw);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource src, float amount) {
/* 300 */     if (isEntityInvulnerable()) {
/* 301 */       return false;
/*     */     }
/* 303 */     if (this.worldObj.isRemote) {
/* 304 */       return false;
/*     */     }
/* 306 */     this.entityAge = 0;
/* 307 */     if (getHealth() <= 0.0F) {
/* 308 */       return false;
/*     */     }
/* 310 */     if (src.isFireDamage() && isPotionActive(Potion.fireResistance)) {
/* 311 */       return false;
/*     */     }
/* 313 */     boolean flag = true;
/* 314 */     if (this.hurtResistantTime > this.maxHurtResistantTime / 2.0F) {
/* 315 */       if (amount <= this.lastDamage) {
/* 316 */         return false;
/*     */       }
/* 318 */       damageEntity(src, amount - this.lastDamage);
/* 319 */       this.lastDamage = amount;
/* 320 */       flag = false;
/*     */     } else {
/* 322 */       this.lastDamage = amount;
/* 323 */       this.prevHealth = getHealth();
/* 324 */       this.hurtResistantTime = this.maxHurtResistantTime;
/* 325 */       damageEntity(src, amount);
/* 326 */       int n = 10;
/* 327 */       this.maxHurtTime = 10;
/* 328 */       this.hurtTime = 10;
/*     */     } 
/* 330 */     Entity entity = src.getEntity();
/* 331 */     if (entity != null) {
/* 332 */       if (entity instanceof EntityPlayer) {
/* 333 */         this.recentlyHit = 100;
/* 334 */         this.attackingPlayer = (EntityPlayer)entity;
/*     */       }
/* 336 */       else if (entity instanceof EntityTameable) {
/* 337 */         EntityTameable entitywolf = (EntityTameable)entity;
/* 338 */         if (entitywolf.isTamed()) {
/* 339 */           this.recentlyHit = 100;
/* 340 */           this.attackingPlayer = null;
/*     */         } 
/*     */       } 
/*     */     }
/* 344 */     if (flag) {
/* 345 */       this.worldObj.setEntityState((Entity)this, (byte)2);
/* 346 */       if (src != DamageSource.drown) {
/* 347 */         setBeenAttacked();
/*     */       }
/* 349 */       if (entity != null) {
/*     */         double d1;
/*     */         double d2;
/* 352 */         for (d1 = entity.posX - this.posX, d2 = entity.posZ - this.posZ; d1 * d1 + d2 * d2 < 1.0E-4D; ) { d1 = (Math.random() - Math.random()) * 0.01D; d2 = (Math.random() - Math.random()) * 0.01D; }
/* 353 */          knockBack(entity, amount, d1, d2);
/*     */       } 
/*     */     } 
/* 356 */     if (getHealth() <= 0.0F) {
/* 357 */       String s = getDeathSound();
/* 358 */       if (flag && s != null) {
/* 359 */         playSound(s, getSoundVolume(), getSoundPitch());
/*     */       }
/* 361 */       onDeath(src);
/*     */     } else {
/*     */       
/* 364 */       String s = getHurtSound();
/* 365 */       if (flag && s != null) {
/* 366 */         playSound(s, getSoundVolume(), getSoundPitch());
/*     */       }
/*     */     } 
/* 369 */     return true;
/*     */   }
/*     */   
/*     */   protected void damageEntity(DamageSource src, float amount) {
/* 373 */     if (src == selfDestruct) {
/* 374 */       super.damageEntity(src, amount);
/*     */       return;
/*     */     } 
/* 377 */     if (src.getEntity() != null && src.getEntity() instanceof EntityPlayer && ((EntityPlayer)src.getEntity()).getHeldItem() != null) {
/* 378 */       ItemStack itemStack = ((EntityPlayer)src.getEntity()).getHeldItem();
/* 379 */       Item item = itemStack.getItem();
/* 380 */       if (item instanceof net.minecraft.item.ItemSpade || item.getHarvestLevel(itemStack, "shovel") >= 0) {
/* 381 */         this.shovelHits++;
/* 382 */         super.damageEntity(src, amount);
/* 383 */         if (this.shovelHits >= 3) {
/* 384 */           kill();
/*     */         }
/*     */       } 
/*     */     } 
/* 388 */     if (this.posY < -1.0D)
/*     */     {
/* 390 */       kill();
/*     */     }
/*     */     
/* 393 */     if (src.equals(DamageSource.inWall)) {
/* 394 */       jumpOutOfWall();
/*     */     }
/*     */     
/* 397 */     if (invulnerable)
/* 398 */       return;  this; if (hurtByAll) super.damageEntity(src, amount); 
/* 399 */     if (src.getEntity() != null && src.getEntity() instanceof net.minecraft.entity.EntityLivingBase) { this; if (hurtByWeapons) super.damageEntity(src, amount);  }
/* 400 */      if (src.isFireDamage()) { this; if (hurtByFire) super.damageEntity(src, amount);  }
/* 401 */      if (src.isExplosion()) { this; if (hurtByBlast) super.damageEntity(src, amount);  }
/* 402 */      if (src == DamageSource.fall) { this; if (hurtByFall) super.damageEntity(src, amount);  }
/* 403 */      if (src == DamageSource.cactus) { this; if (hurtByCactus) super.damageEntity(src, amount);  }
/* 404 */      if (src == DamageSource.inWall) { this; if (hurtByBlockSuffocation) super.damageEntity(src, amount);  }
/* 405 */      this; if (hurtByOther) super.damageEntity(src, amount);
/*     */     
/* 407 */     if (getHealth() <= 0.0F) {
/* 408 */       this.deadMode = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void jumpOutOfWall() {
/* 414 */     double root2 = 1.414213562D;
/* 415 */     double[] currentCoord = { (int)this.posX, (int)this.posY, (int)this.posZ };
/*     */     
/* 417 */     double[] vector = new double[3];
/* 418 */     vector[0] = this.posX - currentCoord[0] + 0.5D;
/* 419 */     vector[1] = 0.0D;
/* 420 */     vector[2] = this.posZ - currentCoord[2] + 0.5D;
/* 421 */     double normalizer = 1.0D / Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2]);
/* 422 */     vector[0] = vector[0] * normalizer;
/* 423 */     vector[1] = vector[1] * normalizer;
/* 424 */     vector[2] = vector[2] * normalizer;
/* 425 */     Block block = this.worldObj.getBlock((int)(this.posX + vector[0]), (int)(this.posY + vector[1]), (int)(this.posZ + vector[2]));
/* 426 */     if (!block.getMaterial().blocksMovement()) {
/* 427 */       setPosition((int)(this.posX + vector[0]) + 0.5D, (int)(this.posY + vector[1]), (int)(this.posZ + vector[2]) + 0.5D);
/*     */       
/*     */       return;
/*     */     } 
/* 431 */     int[] n = new int[3];
/* 432 */     n[0] = (int)currentCoord[0];
/* 433 */     n[1] = (int)currentCoord[1];
/* 434 */     n[2] = (int)currentCoord[2];
/* 435 */     n[1] = n[1] + 1;
/* 436 */     if (!this.worldObj.getBlock(n[0], n[1], n[2]).getMaterial().blocksMovement()) {
/* 437 */       setPosition(n[0] + 0.5D, n[1] + 0.015625D, n[2] + 0.5D);
/*     */       return;
/*     */     } 
/* 440 */     n[0] = (int)currentCoord[0];
/* 441 */     n[1] = (int)currentCoord[1];
/* 442 */     n[2] = (int)currentCoord[2];
/* 443 */     n[0] = n[0] + 1;
/* 444 */     if (!this.worldObj.getBlock(n[0], n[1], n[2]).getMaterial().blocksMovement()) {
/* 445 */       setPosition(n[0] + 0.5D, n[1] + 0.015625D, n[2] + 0.5D);
/*     */       return;
/*     */     } 
/* 448 */     n[0] = (int)currentCoord[0];
/* 449 */     n[1] = (int)currentCoord[1];
/* 450 */     n[2] = (int)currentCoord[2];
/* 451 */     n[0] = n[0] - 1;
/* 452 */     if (!this.worldObj.getBlock(n[0], n[1], n[2]).getMaterial().blocksMovement()) {
/* 453 */       setPosition(n[0] + 0.5D, n[1] + 0.015625D, n[2] + 0.5D);
/*     */       return;
/*     */     } 
/* 456 */     n[0] = (int)currentCoord[0];
/* 457 */     n[1] = (int)currentCoord[1];
/* 458 */     n[2] = (int)currentCoord[2];
/* 459 */     n[2] = n[2] + 1;
/* 460 */     if (!this.worldObj.getBlock(n[0], n[1], n[2]).getMaterial().blocksMovement()) {
/* 461 */       setPosition(n[0] + 0.5D, n[1] + 0.015625D, n[2] + 0.5D);
/*     */       return;
/*     */     } 
/* 464 */     n[0] = (int)currentCoord[0];
/* 465 */     n[1] = (int)currentCoord[1];
/* 466 */     n[2] = (int)currentCoord[2];
/* 467 */     n[2] = n[2] - 1;
/* 468 */     if (!this.worldObj.getBlock(n[0], n[1], n[2]).getMaterial().blocksMovement()) {
/* 469 */       setPosition(n[0] + 0.5D, n[1] + 0.015625D, n[2] + 0.5D);
/*     */       return;
/*     */     } 
/* 472 */     n[0] = (int)currentCoord[0];
/* 473 */     n[1] = (int)currentCoord[1];
/* 474 */     n[2] = (int)currentCoord[2];
/* 475 */     n[1] = n[1] - 1;
/* 476 */     if (!this.worldObj.getBlock(n[0], n[1], n[2]).getMaterial().blocksMovement()) {
/* 477 */       setPosition(n[0] + 0.5D, n[1] + 0.015625D, n[2] + 0.5D);
/*     */       
/*     */       return;
/*     */     } 
/* 481 */     vector[0] = this.worldObj.rand.nextDouble();
/* 482 */     vector[1] = this.worldObj.rand.nextDouble();
/* 483 */     vector[2] = this.worldObj.rand.nextDouble();
/* 484 */     normalizer = root2 / Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2]);
/* 485 */     setPosition(this.posX + vector[0], this.posY + vector[1], this.posZ + vector[2]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void kill() {
/* 490 */     this.deadMode = true;
/* 491 */     attackEntityFrom(selfDestruct, getMaxHealth());
/* 492 */     markDirty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateAITasks() {}
/*     */ 
/*     */   
/*     */   public ItemStack getHeldItem() {
/* 501 */     return this.equipment[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getEquipmentInSlot(int slot) {
/* 506 */     return this.equipment[slot % this.equipment.length];
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ItemStack getCurrentArmor(int armorSlot) {
/* 511 */     return getEquipmentInSlot(armorSlot + 1);
/*     */   }
/*     */   
/*     */   public ItemStack armorItemInSlot(int i) {
/* 515 */     return getCurrentArmor(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentItemOrArmor(int slot, ItemStack item) {
/* 520 */     this.equipment[slot] = item;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ItemStack[] getLastActiveItems() {
/* 525 */     return this.equipment;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropEquipment(boolean doDrop, int dropProbability) {
/* 530 */     if (!doDrop)
/* 531 */       return;  for (int j = this.equipment.length - 1; j >= 0; j--) {
/* 532 */       ItemStack itemstack = this.equipment[j];
/* 533 */       if (itemstack != null) {
/* 534 */         entityDropItem(itemstack, 0.0F);
/* 535 */         this.equipment[j] = null;
/*     */       } 
/*     */     } 
/* 538 */     if (!this.auxInventory.isEmpty()) {
/* 539 */       Iterator<ItemStack> buffer = this.auxInventory.iterator();
/* 540 */       while (buffer.hasNext()) {
/* 541 */         ItemStack itemstack = buffer.next();
/* 542 */         if (itemstack == null)
/* 543 */           continue;  entityDropItem(itemstack, 0.0F);
/*     */       } 
/* 545 */       this.auxInventory.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean notFull() {
/* 550 */     for (int i = 5; i < this.equipment.length; i++) {
/* 551 */       if (this.equipment[i] == null) {
/* 552 */         return true;
/*     */       }
/*     */     } 
/* 555 */     return (this.auxInventory.size() < BlockFront.corpseAuxilleryInventorySize);
/*     */   }
/*     */   
/*     */   public ItemStack vacuumItem(ItemStack item) {
/* 559 */     if (item == null) return null; 
/* 560 */     int nextIndex = 5;
/* 561 */     while (nextIndex < this.equipment.length) {
/* 562 */       if (this.equipment[nextIndex] != null) {
/* 563 */         if (canStack(item, this.equipment[nextIndex])) {
/* 564 */           ItemStack remainder = stackItemStacks(this.equipment[nextIndex], item);
/* 565 */           if (remainder != null) {
/* 566 */             item = remainder;
/*     */           } else {
/* 568 */             return null;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 572 */         this.equipment[nextIndex] = applyItemDamage(item);
/* 573 */         return null;
/*     */       } 
/* 575 */       nextIndex++;
/*     */     } 
/* 577 */     if (this.auxInventory.size() < BlockFront.corpseAuxilleryInventorySize) {
/* 578 */       Iterator<ItemStack> buffer = this.auxInventory.iterator();
/* 579 */       while (buffer.hasNext()) {
/* 580 */         ItemStack bufferItem = buffer.next();
/* 581 */         if (canStack(bufferItem, item)) {
/* 582 */           item = stackItemStacks(bufferItem, item);
/* 583 */           if (item == null) {
/* 584 */             return null;
/*     */           }
/*     */         } 
/*     */       } 
/* 588 */       this.auxInventory.addLast(applyItemDamage(item));
/* 589 */       return null;
/*     */     } 
/* 591 */     return item.copy();
/*     */   }
/*     */   
/*     */   ItemStack stackItemStacks(ItemStack dest, ItemStack src) {
/* 595 */     if (src.stackSize == 0) return null; 
/* 596 */     if (canStack(dest, src)) {
/* 597 */       int maxStackSize = Math.min(dest.getMaxStackSize(), getInventoryStackLimit());
/* 598 */       if (src.stackSize + dest.stackSize < maxStackSize) {
/* 599 */         dest.stackSize += src.stackSize;
/* 600 */         return null;
/* 601 */       }  if (dest.stackSize < maxStackSize) {
/* 602 */         int difference = maxStackSize - dest.stackSize;
/* 603 */         dest.stackSize += difference;
/* 604 */         src.stackSize -= difference;
/* 605 */         if (src.stackSize == 0) return null; 
/* 606 */         return src;
/*     */       } 
/* 608 */       return src;
/*     */     } 
/*     */     
/* 611 */     return src;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canStack(ItemStack a, ItemStack b) {
/* 616 */     if (a == null || b == null) return false; 
/* 617 */     if (a.getItem() == b.getItem() && a.isStackable()) {
/* 618 */       if (a.isItemDamaged() != b.isItemDamaged()) return false; 
/* 619 */       if (!a.isStackable()) return false; 
/* 620 */       if (a.stackSize + b.stackSize > Math.min(a.getMaxStackSize(), getInventoryStackLimit())) return false; 
/* 621 */       if (!a.hasTagCompound() && !b.hasTagCompound())
/* 622 */         return true; 
/* 623 */       if (a.hasTagCompound() && b.hasTagCompound()) {
/* 624 */         return ItemStack.areItemStackTagsEqual(a, b);
/*     */       }
/*     */     } 
/* 627 */     return false;
/*     */   }
/*     */   
/*     */   public static ItemStack applyItemDamage(ItemStack itemstack) {
/* 631 */     if (additionalItemDamage == 0) return itemstack; 
/* 632 */     if (itemstack == null || 
/* 633 */       !itemstack.isItemStackDamageable() || 
/* 634 */       !itemstack.getItem().isDamageable() || itemstack
/* 635 */       .isStackable() || itemstack
/* 636 */       .getItem().getHasSubtypes() || itemstack
/* 637 */       .getItem().getMaxDamage(itemstack) > 0);
/*     */     
/* 639 */     return itemstack;
/*     */   }
/*     */   
/*     */   public void setDeathTime(long timestamp) {
/* 643 */     this.deathTimestamp = timestamp;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 648 */     return 1200;
/*     */   }
/*     */ 
/*     */   
/*     */   public void playLivingSound() {}
/*     */   
/*     */   public boolean canBreatheUnderwater() {
/* 655 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getExperiencePoints(EntityPlayer p_getExperiencePoints_1_) {
/* 660 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void handleHealthUpdate(byte p_handleHealthUpdate_1_) {}
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 669 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 674 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeSteered() {
/* 679 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEquipmentDropChance(int p_setEquipmentDropChance_1_, float p_setEquipmentDropChance_2_) {}
/*     */ 
/*     */   
/*     */   public boolean canPickUpLoot() {
/* 688 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanPickUpLoot(boolean p_setCanPickUpLoot_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean interact(EntityPlayer player) {
/* 698 */     player.displayGUIChest(this);
/* 699 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean allowLeashing() {
/* 704 */     return false;
/*     */   }
/*     */   
/*     */   protected void playSound(String soundID) {
/* 708 */     if (!this.worldObj.isRemote)
/*     */     {
/* 710 */       this.worldObj.playSoundAtEntity((Entity)this, soundID, 0.5F, 0.4F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 716 */     for (int i = 0; i < this.equipment.length; i++) {
/* 717 */       this.equipment[i] = null;
/*     */     }
/* 719 */     this.auxInventory.clear();
/*     */   }
/*     */   public void clearInventory() {
/* 722 */     clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeInventory() {
/* 728 */     playSound("mob.horse.leather");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/* 734 */     if (this.equipment[index] == null) {
/* 735 */       return null;
/*     */     }
/* 737 */     if ((this.equipment[index]).stackSize <= count) {
/* 738 */       ItemStack itemStack = this.equipment[index];
/* 739 */       this.equipment[index] = null;
/* 740 */       markDirty();
/* 741 */       return itemStack;
/*     */     } 
/* 743 */     ItemStack splitStack = this.equipment[index].splitStack(count);
/* 744 */     if ((this.equipment[index]).stackSize == 0) {
/* 745 */       this.equipment[index] = null;
/*     */     }
/* 747 */     markDirty();
/* 748 */     return splitStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 753 */     return 64;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/* 758 */     return this.equipment.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int index) {
/* 763 */     return this.equipment[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/* 768 */     if (this.equipment[index] != null) {
/* 769 */       ItemStack itemStack = this.equipment[index];
/* 770 */       this.equipment[index] = null;
/* 771 */       return itemStack;
/*     */     } 
/* 773 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isItemValidForSlot(int index, ItemStack stack) {
/* 778 */     if (index < 5 && index > 0 && 
/* 779 */       getArmorPosition(stack) != index) {
/* 780 */       return false;
/*     */     }
/*     */     
/* 783 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer player) {
/* 788 */     if (this.deadMode) return false; 
/* 789 */     return (player.getDistanceSq(this.posX, this.posY, this.posZ) <= 16.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {}
/*     */ 
/*     */   
/*     */   public void openInventory() {
/* 798 */     playSound("mob.horse.armor");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int slot, ItemStack item) {
/* 803 */     this.equipment[slot] = item;
/* 804 */     if (item != null && item.stackSize > getInventoryStackLimit()) {
/* 805 */       item.stackSize = getInventoryStackLimit();
/*     */     }
/* 807 */     markDirty();
/*     */   }
/*     */   
/*     */   public String getInventoryName() {
/* 811 */     return "Rotting Corpse";
/*     */   }
/*     */   
/*     */   public boolean hasCustomInventoryName() {
/* 815 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\entity\EntityLootableBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */