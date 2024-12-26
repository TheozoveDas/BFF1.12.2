/*    */ package net.blockfront.mod.client.tmt;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Coord2D
/*    */ {
/*    */   public double xCoord;
/*    */   public double yCoord;
/*    */   public int uCoord;
/*    */   public int vCoord;
/*    */   
/*    */   public Coord2D(double x, double y) {
/* 13 */     this.xCoord = x;
/* 14 */     this.yCoord = y;
/* 15 */     this.uCoord = (int)Math.floor(x);
/* 16 */     this.vCoord = (int)Math.floor(y);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Coord2D(double x, double y, int u, int v) {
/* 22 */     this(x, y);
/* 23 */     this.uCoord = u;
/* 24 */     this.vCoord = v;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\Coord2D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */