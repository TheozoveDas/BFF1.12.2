/*     */ package net.blockfront.mod.inv;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
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
/*     */ public abstract class ShiftClickTarget
/*     */ {
/*     */   public static ShiftClickTarget range(int from, int to) {
/*  22 */     Preconditions.checkArgument((from >= 0 && to >= 0), "to and from must be >= 0");
/*  23 */     if (to == from)
/*  24 */       return of(to); 
/*  25 */     if (to > from) {
/*  26 */       return new RevRange(from, to);
/*     */     }
/*  28 */     return new Range(from, to);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShiftClickTarget none() {
/*  38 */     return None.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShiftClickTarget standard() {
/*  47 */     return Standard.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShiftClickTarget of(int slot) {
/*  57 */     return new One(slot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ShiftClickTarget of(int... slots) {
/*  67 */     int len = slots.length;
/*  68 */     if (len == 0)
/*  69 */       return none(); 
/*  70 */     if (len == 1) {
/*  71 */       return of(slots[0]);
/*     */     }
/*  73 */     return new ForArray(slots);
/*     */   }
/*     */ 
/*     */   
/*     */   abstract boolean hasNext();
/*     */ 
/*     */   
/*     */   abstract int next();
/*     */ 
/*     */   
/*     */   abstract void reset();
/*     */   
/*     */   boolean isStandard() {
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   boolean isNone() {
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   private static final class Range
/*     */     extends ShiftClickTarget {
/*     */     private final int from;
/*     */     private final int to;
/*     */     private int next;
/*     */     
/*     */     Range(int from, int to) {
/* 100 */       this.from = from;
/* 101 */       this.to = to;
/*     */       
/* 103 */       this.next = from;
/*     */     }
/*     */ 
/*     */     
/*     */     boolean hasNext() {
/* 108 */       return (this.next <= this.to);
/*     */     }
/*     */ 
/*     */     
/*     */     int next() {
/* 113 */       return this.next++;
/*     */     }
/*     */ 
/*     */     
/*     */     void reset() {
/* 118 */       this.next = this.from;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class RevRange
/*     */     extends ShiftClickTarget {
/*     */     private final int from;
/*     */     private final int to;
/*     */     private int next;
/*     */     
/*     */     RevRange(int from, int to) {
/* 129 */       this.from = from;
/* 130 */       this.to = to;
/*     */       
/* 132 */       this.next = to;
/*     */     }
/*     */ 
/*     */     
/*     */     boolean hasNext() {
/* 137 */       return (this.next >= this.from);
/*     */     }
/*     */ 
/*     */     
/*     */     int next() {
/* 142 */       return this.next--;
/*     */     }
/*     */ 
/*     */     
/*     */     void reset() {
/* 147 */       this.next = this.to;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class ForArray
/*     */     extends ShiftClickTarget
/*     */   {
/*     */     private final int[] slots;
/*     */     private int curr;
/*     */     
/*     */     ForArray(int[] slots) {
/* 158 */       this.slots = slots;
/*     */     }
/*     */ 
/*     */     
/*     */     boolean hasNext() {
/* 163 */       return (this.curr != this.slots.length);
/*     */     }
/*     */ 
/*     */     
/*     */     int next() {
/* 168 */       return this.slots[this.curr++];
/*     */     }
/*     */ 
/*     */     
/*     */     void reset() {
/* 173 */       this.curr = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class One
/*     */     extends ShiftClickTarget {
/*     */     private int slot;
/*     */     
/*     */     One(int slot) {
/* 182 */       this.slot = slot;
/*     */     }
/*     */ 
/*     */     
/*     */     boolean hasNext() {
/* 187 */       return ((this.slot & Integer.MIN_VALUE) == 0);
/*     */     }
/*     */ 
/*     */     
/*     */     int next() {
/* 192 */       int ret = this.slot;
/* 193 */       this.slot = ret | Integer.MIN_VALUE;
/* 194 */       return ret;
/*     */     }
/*     */ 
/*     */     
/*     */     void reset() {
/* 199 */       this.slot &= Integer.MAX_VALUE;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class None
/*     */     extends ShiftClickTarget {
/* 205 */     static final None INSTANCE = new None();
/*     */ 
/*     */     
/*     */     boolean hasNext() {
/* 209 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     int next() {
/* 214 */       throw new AssertionError();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     void reset() {}
/*     */ 
/*     */     
/*     */     boolean isNone() {
/* 223 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Standard
/*     */     extends ShiftClickTarget {
/* 229 */     static final Standard INSTANCE = new Standard();
/*     */ 
/*     */     
/*     */     boolean hasNext() {
/* 233 */       throw new AssertionError();
/*     */     }
/*     */ 
/*     */     
/*     */     int next() {
/* 238 */       throw new AssertionError();
/*     */     }
/*     */ 
/*     */     
/*     */     void reset() {
/* 243 */       throw new AssertionError();
/*     */     }
/*     */ 
/*     */     
/*     */     boolean isStandard() {
/* 248 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\inv\ShiftClickTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */