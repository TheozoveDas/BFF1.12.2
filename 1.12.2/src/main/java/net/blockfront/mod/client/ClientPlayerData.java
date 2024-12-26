/*    */ package net.blockfront.mod.client;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.blockfront.mod.client.clan.ClanDisplayResponse;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class ClientPlayerData
/*    */   implements IExtendedEntityProperties
/*    */ {
/*    */   private static final String IDENTIFIER = "BlockFront.client_player";
/*    */   private static final int NEW = 0;
/*    */   private static final int CHECKING = 1;
/*    */   private static final int CHECKED = 2;
/*    */   private static final int FAILED = 3;
/* 20 */   private static final long RECHECK_TIMEOUT = TimeUnit.SECONDS.toMillis(30L);
/*    */   
/* 22 */   private int state = 0;
/*    */   private long recheckTime;
/*    */   
/*    */   public static ClientPlayerData get(Entity player) {
/* 26 */     return ((ClientPlayerData)player.getExtendedProperties("BlockFront.client_player")).check(player);
/*    */   }
/*    */   public String clanName; public String clanTag;
/*    */   private ClientPlayerData check(Entity player) {
/* 30 */     if (this.state == 0 || (this.state == 3 && System.currentTimeMillis() - this.recheckTime > 0L)) {
/* 31 */       this.state = 1;
/*    */     }
/* 33 */     return this;
/*    */   }
/*    */   
/*    */   private void inject(ClanDisplayResponse response, Throwable x) {
/* 37 */     if (x == null) {
/* 38 */       this.clanId = response.result.id;
/* 39 */       this.clanName = response.result.name;
/* 40 */       this.clanTag = response.result.tag;
/* 41 */       this.state = 2;
/*    */       
/* 43 */       BlockFront.log.info("got clan data " + response);
/*    */     } else {
/* 45 */       this.state = 3;
/* 46 */       this.recheckTime = System.currentTimeMillis() + RECHECK_TIMEOUT;
/*    */       
/* 48 */       BlockFront.log.error("Failed to check clan", x);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void register(Entity entity) {
/* 53 */     entity.registerExtendedProperties("BlockFront.client_player", new ClientPlayerData());
/*    */   }
/*    */ 
/*    */   
/* 57 */   public int clanId = -1;
/*    */   
/*    */   public void saveNBTData(NBTTagCompound compound) {}
/*    */   
/*    */   public void loadNBTData(NBTTagCompound compound) {}
/*    */   
/*    */   public void init(Entity entity, World world) {}
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\ClientPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */