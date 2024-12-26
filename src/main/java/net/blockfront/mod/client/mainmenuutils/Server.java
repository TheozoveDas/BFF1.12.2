/*    */ package net.blockfront.mod.client.mainmenuutils;
/*    */ 
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import net.blockfront.mod.client.ping.MinecraftPingReply;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Server
/*    */ {
/*    */   private final String name;
/*    */   private final String motd;
/*    */   private final String ip;
/*    */   private final int port;
/*    */   private volatile transient CompletableFuture<MinecraftPingReply> pingStatus;
/*    */   
/*    */   public Server(String name, String motd, String ip, int port) {
/* 20 */     this.name = name;
/* 21 */     this.motd = motd;
/* 22 */     this.ip = ip;
/* 23 */     this.port = port;
/*    */   }
/*    */   
/*    */   public int getPort() {
/* 27 */     return this.port;
/*    */   }
/*    */   
/*    */   public String getIp() {
/* 31 */     return this.ip;
/*    */   }
/*    */   
/*    */   public String getMotd() {
/* 35 */     return this.motd;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 39 */     return this.name;
/*    */   }
/*    */   
/*    */   public CompletableFuture<MinecraftPingReply> getPingData() {
/* 43 */     return getPingData(false);
/*    */   }
/*    */   
/*    */   public CompletableFuture<MinecraftPingReply> getPingData(boolean forceRefresh) {
/* 47 */     if (forceRefresh) {
/* 48 */       synchronized (this) {
/* 49 */         return this.pingStatus = ServersManager.getPing(getIp(), getPort());
/*    */       } 
/*    */     }
/* 52 */     if (this.pingStatus == null) {
/* 53 */       synchronized (this) {
/* 54 */         if (this.pingStatus == null) {
/* 55 */           this.pingStatus = ServersManager.getPing(getIp(), getPort());
/*    */         }
/*    */       } 
/*    */     }
/* 59 */     return this.pingStatus;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\mainmenuutils\Server.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */