/*    */ package net.blockfront.mod.client;
/*    */ 
/*    */ import net.minecraft.client.renderer.Tessellator;
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
/*    */ public class Rendering
/*    */ {
/*    */   public static void drawTexturedQuad(int x, int y, int width, int height, int u, int v, int uSize, int vSize, int texWidth, int texHeight, float zLevel) {
/* 27 */     float uFact = 1.0F / texWidth;
/* 28 */     float vFact = 1.0F / texHeight;
/*    */     
/* 30 */     float uEnd = (u + uSize) * uFact;
/* 31 */     float vEnd = (v + vSize) * vFact;
/*    */     
/* 33 */     drawTexturedQuad(x, y, width, height, u * uFact, v * vFact, uEnd, vEnd, zLevel);
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void drawTexturedQuad(int x, int y, int width, int height, float uStart, float vStart, float uEnd, float vEnd, float zLevel) {
/* 50 */     Tessellator t = Tessellator.getInstance();
/* 51 */     t.startDrawingQuads();
/* 52 */     t.addVertexWithUV(x, (y + height), zLevel, uStart, vEnd);
/* 53 */     t.addVertexWithUV((x + width), (y + height), zLevel, uEnd, vEnd);
/* 54 */     t.addVertexWithUV((x + width), y, zLevel, uEnd, vStart);
/* 55 */     t.addVertexWithUV(x, y, zLevel, uStart, vStart);
/* 56 */     t.draw();
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\Rendering.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */