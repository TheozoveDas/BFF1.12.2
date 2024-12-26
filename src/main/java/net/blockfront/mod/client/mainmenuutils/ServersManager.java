/*    */ package net.blockfront.mod.client.mainmenuutils;
/*    */ 
/*    */ import com.google.common.io.Resources;
/*    */ import com.google.common.reflect.TypeToken;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.CompletionException;
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.blockfront.mod.client.ping.MinecraftPing;
/*    */ import net.blockfront.mod.client.ping.MinecraftPingReply;
/*    */ 
/*    */ public class ServersManager
/*    */ {
/*    */   private static final URL SERVERS_JSON_URL;
/*    */   
/*    */   static {
/*    */     try {
/* 24 */       SERVERS_JSON_URL = new URL("http://212.48.95.227/blockfrontmc.com/assets/factions/servers.json");
/* 25 */     } catch (MalformedURLException e) {
/* 26 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/* 30 */   private static MinecraftPing serverPinger = new MinecraftPing();
/*    */   
/*    */   public static CompletableFuture<MinecraftPingReply> getPing(String ip, int port) {
/* 33 */     return CompletableFuture.supplyAsync(() -> {
/*    */           try {
/*    */             return serverPinger.getPing(ip, port);
/* 36 */           } catch (IOException e) {
/*    */             throw new CompletionException(String.format("failed to ping %s:%s", new Object[] { ip, Integer.valueOf(port) }), e);
/*    */           } 
/*    */         }BlockFront.executor);
/*    */   }
/*    */   
/* 42 */   private static final Type LIST_OF_SERVERS = (new TypeToken<List<Server>>() {  }).getType();
/*    */   
/*    */   public static CompletableFuture<List<Server>> getServerList() {
/* 45 */     if (serversList.isCompletedExceptionally()) {
/* 46 */       serversList = refreshServersList();
/*    */     }
/* 48 */     return serversList;
/*    */   }
/*    */   
/* 51 */   private static CompletableFuture<List<Server>> serversList = refreshServersList();
/*    */   
/*    */   private static CompletableFuture<List<Server>> refreshServersList() {
/* 54 */     return CompletableFuture.supplyAsync(ServersManager::getServerListDirect, BlockFront.executor);
/*    */   }
/*    */   
/*    */   private static List<Server> getServerListDirect() {
/* 58 */     try (BufferedReader reader = Resources.asCharSource(SERVERS_JSON_URL, StandardCharsets.UTF_8).openBufferedStream()) {
/* 59 */       return (List)BlockFront.GSON.fromJson(reader, LIST_OF_SERVERS);
/* 60 */     } catch (IOException e) {
/* 61 */       throw new CompletionException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\mainmenuutils\ServersManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */