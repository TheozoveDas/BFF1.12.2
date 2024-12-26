/*     */ package net.blockfront.mod.client;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.UnmodifiableIterator;

import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import net.blockfront.mod.BlockFront;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import org.apache.logging.log4j.Level;
/*     */ 
/*     */ public class IngameStats {
/*     */   public int playersKilled;
/*     */   public int zombiesKilled;
/*     */   
/*     */   public IngameStats(int playersKilled, int zombiesKilled, int ticksSurvived) {
/*  25 */     this.playersKilled = playersKilled;
/*  26 */     this.zombiesKilled = zombiesKilled;
/*  27 */     this.ticksSurvived = ticksSurvived;
/*     */   }
/*     */   public int ticksSurvived; public static final String IDENTIFIER_SINGLEPLAYER = "\000SINGLEPLAYER\000"; private static File file;
/*     */   public IngameStats(IngameStats other) {
/*  31 */     this.playersKilled = other.playersKilled;
/*  32 */     this.zombiesKilled = other.zombiesKilled;
/*  33 */     this.ticksSurvived = other.ticksSurvived;
/*     */   }
/*     */   
/*     */   private IngameStats() {}
/*     */   
/*     */   public void clear() {
/*  39 */     this.playersKilled = this.zombiesKilled = this.ticksSurvived = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init(File dir) {
/*  47 */     file = new File(dir, "stats.dat");
/*  48 */     load();
/*     */   }
/*     */   
/*  51 */   private static final Map<String, IngameStats> stats = new HashMap<>();
/*     */   
/*     */   public static IngameStats current() {
/*     */     String key;
/*  55 */     if (Minecraft.getMinecraft().isIntegratedServerRunning()) {
/*  56 */       key = "\000SINGLEPLAYER\000";
/*     */     } else {
/*  58 */       key = (Minecraft.getMinecraft().getCurrentServerData()).serverIP;
/*     */     } 
/*     */     
/*  61 */     IngameStats s = stats.get(key);
/*  62 */     if (s == null) {
/*  63 */       s = new IngameStats();
/*  64 */       stats.put(key, s);
/*     */     } 
/*  66 */     return s;
/*     */   }
/*     */   
/*     */   public static void awaitLoad() {
/*     */     try {
/*  71 */       loading.await();
/*  72 */     } catch (InterruptedException e) {
/*  73 */       Thread.interrupted();
/*     */     } 
/*     */   }
/*     */   
/*  77 */   private static final CountDownLatch loading = new CountDownLatch(1);
/*     */   
/*     */   private static void load() {
/*  80 */     CompletableFuture.supplyAsync(IngameStats::loadDirect, BlockFront.executor)
/*  81 */       .thenAcceptAsync(map -> {
/*     */           map.forEach(());
/*     */           loading.countDown();
/*  84 */         }(Executor)Scheduler.client());
/*     */   }
/*     */   
/*     */   private IngameStats merge(IngameStats other) {
/*  88 */     return new IngameStats(this.playersKilled + other.playersKilled, this.zombiesKilled + other.zombiesKilled, this.ticksSurvived + other.ticksSurvived);
/*     */   }
/*     */   
/*     */   public static void save() {
/*  92 */     ImmutableMap<String, IngameStats> frozen = ImmutableMap.copyOf(Maps.transformValues(stats, IngameStats::new));
/*  93 */     BlockFront.executor.execute(() -> {
/*     */           try {
/*     */             loading.await();
/*  96 */           } catch (InterruptedException e) {
/*     */             Thread.interrupted();
/*     */             return;
/*     */           } 
/*     */           synchronized (fileLock) {
/*     */             try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
/*     */               UnmodifiableIterator<Map.Entry<String, IngameStats>> unmodifiableIterator = frozen.entrySet().iterator();
/*     */               while (unmodifiableIterator.hasNext()) {
/*     */                 Map.Entry<String, IngameStats> entry = unmodifiableIterator.next();
/*     */                 IngameStats value = entry.getValue();
/*     */                 out.writeUTF(entry.getKey());
/*     */                 out.writeInt(value.playersKilled);
/*     */                 out.writeInt(value.zombiesKilled);
/*     */                 out.writeInt(value.ticksSurvived);
/*     */               } 
/*     */               out.writeUTF("");
/* 112 */             } catch (IOException e) {
/*     */               BlockFront.log.log(Level.ERROR, "Could not write stats file!", e);
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/* 119 */   private static final Object fileLock = new Object();
/*     */   
/*     */   private static Map<String, IngameStats> loadDirect() {
/* 122 */     synchronized (fileLock) {
/* 123 */       try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
/* 124 */         Map<String, IngameStats> b = new HashMap<>();
/*     */         while (true) {
/* 126 */           String id = in.readUTF();
/* 127 */           if (id.equals("")) {
/* 128 */             return b;
/*     */           }
/*     */           
/* 131 */           int pk = in.readInt();
/* 132 */           int zk = in.readInt();
/* 133 */           int days = in.readInt();
/* 134 */           b.putIfAbsent(id, new IngameStats(pk, zk, days));
/*     */         } 
/*     */       } 
/*     */ catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}     } 
/*     */
return null;   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\IngameStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */