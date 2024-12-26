/*    */ package net.blockfront.mod.client.tmt;
/*    */ 
/*    */ public class PositionTextureVertex extends PositionTextureVertex {
/*  7 */   public float texturePositionW = 1.0F;
/*    */ 
/*    */   
/*    */   public PositionTextureVertex(float par1, float par2, float par3, float par4, float par5) {
/* 11 */     this(par1, par2, par3, par4, par5, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public PositionTextureVertex(float par1, float par2, float par3, float par4, float par5, float par6) {
/* 16 */     this(Vec3.createVectorHelper(par1, par2, par3), par4, par5);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PositionTextureVertex setTexturePosition(float par1, float par2) {
/* 22 */     return new PositionTextureVertex(this, par1, par2, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public PositionTextureVertex setTexturePosition(float par1, float par2, float q) {
/* 27 */     return new PositionTextureVertex(this, par1, par2, q);
/*    */   }
/*    */ 
/*    */   
/*    */   public PositionTextureVertex(PositionTextureVertex par1PositionTextureVertex, float par2, float par3) {
/* 32 */     this(par1PositionTextureVertex, par2, par3, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public PositionTextureVertex(PositionTextureVertex par1PositionTextureVertex, float par2, float par3, float q) {
/* 37 */     super(par1PositionTextureVertex, par2, par3);
/* 38 */     this.texturePositionW = q;
/*    */   }
/*    */ 
/*    */   
/*    */   public PositionTextureVertex(Vec3 par1Vec3, float par2, float par3) {
/* 43 */     this(par1Vec3, par2, par3, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public PositionTextureVertex(Vec3 par1Vec3, float par2, float par3, float par4) {
/* 48 */     super(par1Vec3, par2, par3);
/* 49 */     this.texturePositionW = par4;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\PositionTextureVertex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */