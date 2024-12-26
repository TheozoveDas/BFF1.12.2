/*    */ package net.blockfront.mod.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelBanner
/*    */   extends ModelBase
/*    */ {
/* 12 */   int x = 0;
/*    */   
/*    */   ModelRenderer Part_0;
/*    */   ModelRenderer Part_1;
/*    */   
/*    */   public ModelBanner() {
/* 18 */     this.textureWidth = 64;
/* 19 */     this.textureHeight = 64;
/*    */     
/* 21 */     this.Part_0 = new ModelRenderer(this, 1, 1);
/* 22 */     this.Part_0.addBox(0.0F, 0.0F, 0.0F, 16, 2, 2);
/* 23 */     this.Part_0.setRotationPoint(-8.0F, -48.0F, 6.0F);
/* 24 */     this.Part_0.setTextureSize(64, 64);
/* 25 */     this.Part_0.mirror = true;
/* 26 */     setRotation(this.Part_0, 0.0F, 0.0F, 0.0F);
/* 27 */     this.Part_1 = new ModelRenderer(this, 1, 9);
/* 28 */     this.Part_1.addBox(0.0F, 0.0F, 0.0F, 16, 44, 0);
/* 29 */     this.Part_1.setRotationPoint(-8.0F, -47.0F, 6.5F);
/* 30 */     this.Part_1.setTextureSize(64, 64);
/* 31 */     this.Part_1.mirror = true;
/* 32 */     setRotation(this.Part_1, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 38 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 39 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 40 */     this.Part_0.render(f5);
/* 41 */     this.Part_1.render(f5);
/*    */   }
/*    */ 
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 46 */     model.rotateAngleX = x;
/* 47 */     model.rotateAngleY = y;
/* 48 */     model.rotateAngleZ = z;
/*    */   }
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {}
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\model\ModelBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */