/*   */ package net.blockfront.mod.client.tmt;
/*   */ 
/*   */ public class Shape3D {
/*   */   public PositionTransformVertex[] vertices;
/*   */   
/*   */   public Shape3D(PositionTransformVertex[] verts, TexturedPolygon[] poly) {
/* 7 */     this.vertices = verts;
/* 8 */     this.faces = poly;
/*   */   }
/*   */   
/*   */   public TexturedPolygon[] faces;
/*   */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\Shape3D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */