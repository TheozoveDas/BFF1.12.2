/*    */ package net.blockfront.mod.client.character;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.UUID;
/*    */ import net.blockfront.mod.ClientProxy;
/*    */ import net.blockfront.mod.client.InventoryPersistence;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class CharacterPlayerFactory
/*    */ {
/*    */   private final UUID uuid;
/*    */   private final String username;
/*    */   private World world;
/*    */   private CharacterPlayer player;
/* 17 */   private float playerRotation = 130.0F;
/*    */   
/*    */   public CharacterPlayerFactory(GameProfile profile) {
/* 20 */     this(profile.getName(), profile.getId());
/*    */   }
/*    */   
/*    */   public CharacterPlayerFactory(String username, UUID uuid) {
/* 24 */     this.username = username;
/* 25 */     this.uuid = uuid;
/*    */   }
/*    */   
/*    */   public void reset() {
/* 29 */     this.world = null;
/* 30 */     this.player = null;
/*    */   }
/*    */   
/*    */   private void init() {
/* 34 */     this.world = new CharacterWorld();
/* 35 */     this.player = new CharacterPlayer(this.world, new GameProfile(this.uuid, this.username));
/* 36 */     InventoryPersistence.injectPersistedInventory(ClientProxy.configDir, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPlayerRotation() {
/* 41 */     return this.playerRotation;
/*    */   }
/*    */   
/*    */   public void addPlayerRotation() {
/* 45 */     this.playerRotation += 0.3F;
/*    */   }
/*    */   
/*    */   public EntityPlayer getPlayer() {
/* 49 */     if (this.player == null) {
/* 50 */       init();
/*    */     }
/* 52 */     return (EntityPlayer)this.player;
/*    */   }
/*    */   
/*    */   public World getWorld() {
/* 56 */     if (this.world == null) {
/* 57 */       init();
/*    */     }
/* 59 */     return this.world;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\character\CharacterPlayerFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */