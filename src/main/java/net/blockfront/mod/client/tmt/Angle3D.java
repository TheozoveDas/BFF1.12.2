/*    */ package net.blockfront.mod.client.tmt;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Angle3D
/*    */ {
/*    */   public float angleX;
/*    */   public float angleY;
/*    */   public float angleZ;
/*    */   
/*    */   public Angle3D(float x, float y, float z) {
/* 19 */     this.angleX = x;
/* 20 */     this.angleY = y;
/* 21 */     this.angleZ = z;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addAngles(float x, float y, float z) {
/* 32 */     this.angleX += x;
/* 33 */     this.angleY += y;
/* 34 */     this.angleZ += z;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addAngles(Angle3D angles) {
/* 43 */     this.angleX += angles.angleX;
/* 44 */     this.angleY += angles.angleY;
/* 45 */     this.angleZ += angles.angleZ;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void multiplyAngles(float x, float y, float z) {
/* 56 */     this.angleX *= x;
/* 57 */     this.angleY *= y;
/* 58 */     this.angleZ *= z;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void multiplyAngles(Angle3D angles) {
/* 67 */     this.angleX *= angles.angleX;
/* 68 */     this.angleY *= angles.angleY;
/* 69 */     this.angleZ *= angles.angleZ;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Angle3D getCenter(Angle3D angles1, Angle3D angles2) {
/* 80 */     Angle3D angles = new Angle3D(0.0F, 0.0F, 0.0F);
/* 81 */     angles.addAngles(angles1);
/* 82 */     angles.addAngles(angles2);
/* 83 */     angles.multiplyAngles(0.5F, 0.5F, 0.5F);
/* 84 */     return angles;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Angle3D copy() {
/* 93 */     return new Angle3D(this.angleX, this.angleY, this.angleZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\Angle3D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */