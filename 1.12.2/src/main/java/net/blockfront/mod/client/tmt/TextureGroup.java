/*    */ package net.blockfront.mod.client.tmt;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextureGroup
/*    */ {
/* 13 */   public ArrayList<TexturedPolygon> poly = new ArrayList<>();
/* 14 */   public String texture = "";
/*    */ 
/*    */ 
/*    */   
/*    */   public void addPoly(TexturedPolygon polygon) {
/* 19 */     this.poly.add(polygon);
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadTexture() {
/* 24 */     loadTexture(-1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadTexture(int defaultTexture) {
/* 29 */     if (!this.texture.equals("")) {
/*    */       
/* 31 */       TextureManager renderengine = RenderManager.instance.renderEngine;
/* 32 */       renderengine.bindTexture(new ResourceLocation("", this.texture));
/*    */     }
/* 34 */     else if (defaultTexture > -1) {
/*    */       
/* 36 */       RenderManager.instance.renderEngine.bindTexture(new ResourceLocation("", ""));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\TextureGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */