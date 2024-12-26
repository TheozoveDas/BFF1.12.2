/*     */ package net.blockfront.mod.util;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.primitives.Ints;
/*     */ import com.google.common.util.concurrent.AbstractListeningExecutorService;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.ParametersAreNonnullByDefault;
/*     */ import net.blockfront.mod.BlockFront;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ParametersAreNonnullByDefault
/*     */ public final class Scheduler
/*     */   extends AbstractListeningExecutorService
/*     */ {
/*     */   public static Scheduler server() {
/*  43 */     return server;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Scheduler client() {
/*  52 */     return client;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Scheduler forSide(Side side) {
/*  62 */     return (side == Side.CLIENT) ? client : server;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void schedule(Runnable r, long tickDelay) {
/*  72 */     Preconditions.checkArgument((tickDelay >= 0L));
/*  73 */     execute(new WaitingTask(r, tickDelay));
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(Runnable task) {
/*  78 */     execute(new WrappedRunnable(task));
/*     */   }
/*     */   
/*     */   public void execute(Task task) {
/*  82 */     this.inputQueue.offer(task);
/*     */   }
/*     */   
/*     */   static {
/*  86 */     if (FMLCommonHandler.instance().getSide().isClient()) {
/*  87 */       client = new Scheduler();
/*     */     } else {
/*  89 */       client = null;
/*     */     } 
/*  91 */   } private static final Scheduler server = new Scheduler();
/*     */   
/*     */   private static final Scheduler client;
/*     */   
/*  95 */   private final ConcurrentLinkedQueue<Task> inputQueue = new ConcurrentLinkedQueue<>();
/*     */ 
/*     */   
/*  98 */   private Task[] activeTasks = new Task[5];
/*  99 */   private int size = 0;
/*     */   
/*     */   public void tick() {
/* 102 */     Task[] activeTasks = this.activeTasks;
/* 103 */     int size = this.size;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     int idx = 0, free = -1;
/* 110 */     while (idx < size) {
/* 111 */       Task t = activeTasks[idx];
/* 112 */       if (!checkedExecute(t)) {
/*     */         
/* 114 */         activeTasks[idx] = null;
/* 115 */         if (free == -1)
/*     */         {
/* 117 */           free = idx;
/*     */         }
/* 119 */       } else if (free != -1) {
/*     */ 
/*     */         
/* 122 */         activeTasks[free++] = t;
/* 123 */         activeTasks[idx] = null;
/*     */       } 
/* 125 */       idx++;
/*     */     } 
/*     */     
/* 128 */     if (free != -1) {
/* 129 */       this.size = free;
/*     */     }
/*     */ 
/*     */     
/*     */     Task task;
/*     */     
/* 135 */     while ((task = this.inputQueue.poll()) != null) {
/*     */ 
/*     */       
/* 138 */       if (checkedExecute(task)) {
/* 139 */         if (size == activeTasks.length) {
/*     */           
/* 141 */           Task[] newArr = new Task[size << 1];
/* 142 */           System.arraycopy(activeTasks, 0, newArr, 0, size);
/* 143 */           activeTasks = this.activeTasks = newArr;
/*     */         } 
/* 145 */         activeTasks[size] = task;
/* 146 */         this.size++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean checkedExecute(Task task) {
/*     */     try {
/* 154 */       return task.execute();
/* 155 */     } catch (Throwable x) {
/* 156 */       BlockFront.log.error(String.format("Exception thrown during execution of %s", new Object[] { task }));
/* 157 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Task
/*     */   {
/*     */     boolean execute();
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class WaitingTask
/*     */     implements Task
/*     */   {
/*     */     private final Runnable r;
/*     */     
/*     */     private long ticks;
/*     */ 
/*     */     
/*     */     WaitingTask(Runnable r, long ticks) {
/* 178 */       this.r = r;
/* 179 */       this.ticks = ticks;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean execute() {
/* 184 */       if (--this.ticks == 0L) {
/* 185 */         this.r.run();
/* 186 */         return false;
/*     */       } 
/* 188 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 194 */       return String.format("Scheduled task (task=%s, remainingTicks=%s)", new Object[] { this.r, Long.valueOf(this.ticks) });
/*     */     }
/*     */   }
/*     */   
/*     */   private static class WrappedRunnable implements Task {
/*     */     private final Runnable task;
/*     */     
/*     */     public WrappedRunnable(Runnable task) {
/* 202 */       this.task = task;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean execute() {
/* 207 */       this.task.run();
/* 208 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 213 */       return this.task.toString();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean isShutdown() {
/* 224 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean isTerminated() {
/* 234 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void shutdown() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   @Deprecated
/*     */   public List<Runnable> shutdownNow() {
/* 253 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
/* 267 */     long millis = unit.toMillis(timeout);
/* 268 */     long milliNanos = TimeUnit.MILLISECONDS.toNanos(millis);
/* 269 */     int additionalNanos = Ints.saturatedCast(unit.toNanos(timeout) - milliNanos);
/* 270 */     Thread.sleep(millis, additionalNanos);
/* 271 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mo\\util\Scheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */