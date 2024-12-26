/*    */ package net.blockfront.mod.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.client.model.ModelZombie;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ 
/*    */ @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*    */ public class ModelCorpseSkeleton
/*    */   extends ModelZombie
/*    */ {
/*    */   public ModelCorpseSkeleton() {
/* 17 */     this(0.0F, false);
/*    */   }
/*    */   
/*    */   public ModelCorpseSkeleton(float f, boolean b) {
/* 21 */     super();
/* 22 */     if (!b) {
/* 23 */       (this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16)).addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, f);
/* 24 */       this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/* 25 */       this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 16);
/* 26 */       this.bipedLeftArm.mirror = true;
/* 27 */       this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, f);
/* 28 */       this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/* 29 */       (this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16)).addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, f);
/* 30 */       this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/* 31 */       this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
/* 32 */       this.bipedLeftLeg.mirror = true;
/* 33 */       this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, f);
/* 34 */       this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase e, float f1, float f2, float f3) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity e) {
/* 48 */     super.setRotationAngles(0.0F, 0.0F, f3, f4, f5, f6, e);
/* 49 */     this.bipedRightLeg.rotateAngleZ = 0.2F;
/* 50 */     this.bipedLeftLeg.rotateAngleZ = -0.2F;
/* 51 */     this.bipedRightArm.rotateAngleZ = 0.3F;
/* 52 */     this.bipedLeftArm.rotateAngleZ = -0.3F;
/* 53 */     this.bipedRightArm.rotateAngleY = 1.25F;
/* 54 */     this.bipedLeftArm.rotateAngleY = -0.5F;
/* 55 */     this.bipedRightArm.rotateAngleX = 0.0F;
/* 56 */     this.bipedLeftArm.rotateAngleX = 0.0F;
/* 57 */     this.bipedHead.rotateAngleX = 0.0F;
/* 58 */     this.bipedHead.rotateAngleY = 0.0F;
/* 59 */     this.bipedHead.rotateAngleZ = 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\model\ModelCorpseSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */