/*     */ package net.blockfront.mod.client.clan;
/*     */ 
/*     */ import com.google.common.base.CharMatcher;
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import com.google.common.io.CharStreams;
/*     */ import com.google.common.io.Resources;
/*     */ import com.google.common.util.concurrent.UncheckedExecutionException;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.ForkJoinPool;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.blockfront.mod.ClientProxy;
/*     */ import net.minecraft.launchwrapper.Launch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UsernameCache
/*     */ {
/*     */   public static String getBlocking(UUID uuid) throws IOException {
/*     */     try {
/*  35 */       return (String)cache.get(uuid);
/*  36 */     } catch (UncheckedExecutionException|ExecutionException e) {
/*  37 */       throw new IOException("Failed contacting Mojang API", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static CompletableFuture<String> get(UUID uuid) {
/*  42 */     String name = (String)cache.getIfPresent(uuid);
/*  43 */     if (name != null) {
/*  44 */       return CompletableFuture.completedFuture(name);
/*     */     }
/*  46 */     CompletableFuture<String> future = new CompletableFuture<>();
/*  47 */     ForkJoinPool.commonPool().execute(() -> {
/*     */           try {
/*     */             future.complete(cache.get(uuid));
/*  50 */           } catch (ExecutionException|UncheckedExecutionException x) {
/*     */             future.completeExceptionally(x.getCause());
/*  52 */           } catch (Throwable t) {
/*     */             future.completeExceptionally(t);
/*     */           } 
/*     */         });
/*  56 */     return future;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private static LoadingCache<UUID, String> cache = CacheBuilder.newBuilder()
/*  65 */     .maximumSize(200L)
/*  66 */     .build(new Loader());
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class Loader
/*     */     extends CacheLoader<UUID, String>
/*     */   {
/*     */     
/*  75 */     private static final CharMatcher DASH_MATCHER = CharMatcher.is('-');
/*     */ 
/*     */     
/*     */     public String load(@Nonnull UUID uuid) throws IOException {
/*  79 */       if (((Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment")).booleanValue() && !ClientProxy.useRemoteServersInDebug) {
/*  80 */         if (uuid.equals(UUID.fromString("1cd8fd0a-60bd-42c2-b57f-b354d0df50aa"))) {
/*  81 */           return "Player1";
/*     */         }
/*  83 */         return "Player2";
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  88 */       String uuidString = DASH_MATCHER.removeFrom(uuid.toString());
/*  89 */       try (BufferedReader reader = Resources.asCharSource(new URL(String.format("https://api.mojang.com/user/profiles/%s/names", new Object[] { uuidString })), StandardCharsets.UTF_8).openBufferedStream()) {
/*  90 */         String s = CharStreams.toString(reader);
/*     */         
/*  92 */         System.out.println(s);
try (/*     */         
/*  94 */JsonReader json = new JsonReader(new StringReader(s))) {
	/*  95 */         json.beginArray();
	/*     */         
	/*  97 */         String name = null;
	/*  98 */         long when = 0L;
	/*     */         
	/* 100 */         while (json.hasNext()) {
	/* 101 */           String nameObj = null;
	/* 102 */           long timeObj = 0L;
	/* 103 */           json.beginObject();
	/* 104 */           while (json.hasNext()) {
	/* 105 */             String key = json.nextName();
	/* 106 */             switch (key) {
	/*     */               case "name":
	/* 108 */                 nameObj = json.nextString();
	/*     */                 continue;
	/*     */               case "changedToAt":
	/* 111 */                 timeObj = json.nextLong();
	/*     */                 continue;
	/*     */             } 
	/* 114 */             json.skipValue();
	/*     */           } 
	/*     */ 
	/*     */           
	/* 118 */           json.endObject();
	/*     */           
	/* 120 */           if (nameObj != null && timeObj >= when) {
	/* 121 */             name = nameObj;
	/*     */           }
	/*     */         } 
	/*     */         
	/* 125 */         json.endArray();
	/*     */         
	/* 127 */         if (name == null) {
	/* 128 */           throw new IOException("Failed connecting to the Mojang API");
	/*     */         }
	/*     */         
	/* 131 */         return name;
	/*     */
}       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\clan\UsernameCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */