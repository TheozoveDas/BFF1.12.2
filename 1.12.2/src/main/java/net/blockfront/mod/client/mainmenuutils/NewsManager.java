/*    */ package net.blockfront.mod.client.mainmenuutils;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.CompletionException;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public class NewsManager
/*    */ {
/*    */   private static final URL NEWS_URL;
/*    */   
/*    */   static {
/*    */     try {
/* 21 */       NEWS_URL = new URL("http://212.48.95.227/blockfrontmc.com/assets/factions/menu_news.txt");
/* 22 */     } catch (MalformedURLException e) {
/* 23 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static CompletableFuture<List<String>> fetchNews() {
/* 28 */     return CompletableFuture.supplyAsync(NewsManager::downloadNews);
/*    */   }
/*    */   
/*    */   private static ImmutableList<String> downloadNews() {
/* 32 */     try(BufferedReader reader = new BufferedReader(new InputStreamReader(NEWS_URL.openStream())); 
/* 33 */         Stream<String> lines = reader.lines()) {
/* 34 */       return ImmutableList.copyOf(lines.iterator());
/* 35 */     } catch (IOException e) {
/* 36 */       throw new CompletionException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\mainmenuutils\NewsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */