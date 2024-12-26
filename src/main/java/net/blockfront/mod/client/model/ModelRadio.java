/*     */ package net.blockfront.mod.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelRadio
/*     */   extends ModelBase
/*     */ {
/*  12 */   int x = 0;
/*     */   
/*     */   ModelRenderer Import_Box0;
/*     */   ModelRenderer Import_Box1;
/*     */   ModelRenderer Import_Box2;
/*     */   ModelRenderer Import_Box3;
/*     */   ModelRenderer Import_Box4;
/*     */   ModelRenderer Import_Box5;
/*     */   ModelRenderer Import_Box6;
/*     */   ModelRenderer Import_Box7;
/*     */   ModelRenderer Import_Box8;
/*     */   
/*     */   public ModelRadio() {
/*  25 */     this.textureWidth = 32;
/*  26 */     this.textureHeight = 32;
/*     */     
/*  28 */     this.textureWidth = 256;
/*  29 */     this.textureHeight = 128;
/*     */     
/*  31 */     this.Import_Box0 = new ModelRenderer(this, 57, 0);
/*  32 */     this.Import_Box0.addBox(0.0F, 0.0F, 0.0F, 10, 1, 18);
/*  33 */     this.Import_Box0.setRotationPoint(-5.0F, -1.0F, -9.0F);
/*  34 */     this.Import_Box0.setTextureSize(256, 128);
/*  35 */     this.Import_Box0.mirror = true;
/*  36 */     setRotation(this.Import_Box0, 0.0F, 0.0F, 0.0F);
/*  37 */     this.Import_Box1 = new ModelRenderer(this, 0, 0);
/*  38 */     this.Import_Box1.addBox(0.0F, 0.0F, 0.0F, 10, 1, 18);
/*  39 */     this.Import_Box1.setRotationPoint(-5.0F, -10.0F, -9.0F);
/*  40 */     this.Import_Box1.setTextureSize(256, 128);
/*  41 */     this.Import_Box1.mirror = true;
/*  42 */     setRotation(this.Import_Box1, 0.0F, 0.0F, 0.0F);
/*  43 */     this.Import_Box2 = new ModelRenderer(this, 91, 20);
/*  44 */     this.Import_Box2.addBox(0.0F, 0.0F, 0.0F, 10, 8, 1);
/*  45 */     this.Import_Box2.setRotationPoint(-5.0F, -9.0F, -10.0F);
/*  46 */     this.Import_Box2.setTextureSize(256, 128);
/*  47 */     this.Import_Box2.mirror = true;
/*  48 */     setRotation(this.Import_Box2, 0.0F, 0.0F, 0.0F);
/*  49 */     this.Import_Box3 = new ModelRenderer(this, 68, 20);
/*  50 */     this.Import_Box3.addBox(0.0F, 0.0F, 0.0F, 10, 8, 1);
/*  51 */     this.Import_Box3.setRotationPoint(-5.0F, -9.0F, 9.0F);
/*  52 */     this.Import_Box3.setTextureSize(256, 128);
/*  53 */     this.Import_Box3.mirror = true;
/*  54 */     setRotation(this.Import_Box3, 0.0F, 0.0F, 0.0F);
/*  55 */     this.Import_Box4 = new ModelRenderer(this, 115, 0);
/*  56 */     this.Import_Box4.addBox(0.0F, 0.0F, 0.0F, 8, 8, 18);
/*  57 */     this.Import_Box4.setRotationPoint(-4.0F, -9.0F, -9.0F);
/*  58 */     this.Import_Box4.setTextureSize(256, 128);
/*  59 */     this.Import_Box4.mirror = true;
/*  60 */     setRotation(this.Import_Box4, 0.0F, 0.0F, 0.0F);
/*  61 */     this.Import_Box5 = new ModelRenderer(this, 1, 20);
/*  62 */     this.Import_Box5.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
/*  63 */     this.Import_Box5.setRotationPoint(-5.0F, -7.0F, -8.0F);
/*  64 */     this.Import_Box5.setTextureSize(256, 128);
/*  65 */     this.Import_Box5.mirror = true;
/*  66 */     setRotation(this.Import_Box5, 0.0F, 0.0F, 0.0F);
/*  67 */     this.Import_Box6 = new ModelRenderer(this, 7, 20);
/*  68 */     this.Import_Box6.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
/*  69 */     this.Import_Box6.setRotationPoint(-5.0F, -7.0F, -4.0F);
/*  70 */     this.Import_Box6.setTextureSize(256, 128);
/*  71 */     this.Import_Box6.mirror = true;
/*  72 */     setRotation(this.Import_Box6, 0.0F, 0.0F, 0.0F);
/*  73 */     this.Import_Box7 = new ModelRenderer(this, 13, 20);
/*  74 */     this.Import_Box7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
/*  75 */     this.Import_Box7.setRotationPoint(-5.0F, -8.0F, -7.0F);
/*  76 */     this.Import_Box7.setTextureSize(256, 128);
/*  77 */     this.Import_Box7.mirror = true;
/*  78 */     setRotation(this.Import_Box7, 0.0F, 0.0F, 0.0F);
/*  79 */     this.Import_Box8 = new ModelRenderer(this, 23, 20);
/*  80 */     this.Import_Box8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
/*  81 */     this.Import_Box8.setRotationPoint(-5.0F, -4.0F, -7.0F);
/*  82 */     this.Import_Box8.setTextureSize(256, 128);
/*  83 */     this.Import_Box8.mirror = true;
/*  84 */     setRotation(this.Import_Box8, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  90 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*  91 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*  92 */     this.Import_Box0.render(f5);
/*  93 */     this.Import_Box1.render(f5);
/*  94 */     this.Import_Box2.render(f5);
/*  95 */     this.Import_Box3.render(f5);
/*  96 */     this.Import_Box4.render(f5);
/*  97 */     this.Import_Box5.render(f5);
/*  98 */     this.Import_Box6.render(f5);
/*  99 */     this.Import_Box7.render(f5);
/* 100 */     this.Import_Box8.render(f5);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 105 */     model.rotateAngleX = x;
/* 106 */     model.rotateAngleY = y;
/* 107 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {}
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\model\ModelRadio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */