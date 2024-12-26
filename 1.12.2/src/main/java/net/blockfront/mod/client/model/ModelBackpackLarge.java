/*     */ package net.blockfront.mod.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ 
/*     */ public class ModelBackpackLarge
/*     */   extends ModelBase
/*     */ {
/*  11 */   int x = 0;
/*     */   ModelRenderer Part_0;
/*     */   ModelRenderer Part_1;
/*     */   ModelRenderer Part_2;
/*     */   ModelRenderer Part_3;
/*     */   ModelRenderer Part_4;
/*     */   ModelRenderer Part_5;
/*     */   ModelRenderer Part_6;
/*     */   ModelRenderer Part_7;
/*     */   ModelRenderer Part_8;
/*     */   ModelRenderer Part_9;
/*     */   ModelRenderer Part_10;
/*     */   ModelRenderer Part_11;
/*     */   ModelRenderer Part_12;
/*     */   ModelRenderer Part_13;
/*     */   ModelRenderer Part_14;
/*     */   ModelRenderer Part_15;
/*     */   ModelRenderer Part_16;
/*     */   ModelRenderer Part_17;
/*     */   
/*     */   public ModelBackpackLarge() {
/*  32 */     this.textureWidth = 64;
/*  33 */     this.textureHeight = 64;
/*     */     
/*  35 */     this.Part_0 = new ModelRenderer(this, 1, 1);
/*  36 */     this.Part_0.addBox(-6.5F, -3.0F, 2.5F, 8, 16, 5);
/*  37 */     this.Part_0.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  38 */     this.Part_0.setTextureSize(64, 64);
/*  39 */     this.Part_0.mirror = true;
/*  40 */     setRotation(this.Part_0, 0.0F, 0.0F, -0.43633232F);
/*  41 */     this.Part_1 = new ModelRenderer(this, 33, 1);
/*  42 */     this.Part_1.addBox(-4.0F, -3.0F, -2.5F, 2, 1, 5);
/*  43 */     this.Part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  44 */     this.Part_1.setTextureSize(64, 64);
/*  45 */     this.Part_1.mirror = true;
/*  46 */     setRotation(this.Part_1, 0.0F, 0.0F, -0.55850536F);
/*  47 */     this.Part_2 = new ModelRenderer(this, 49, 1);
/*  48 */     this.Part_2.addBox(-4.0F, -2.0F, -2.5F, 2, 13, 1);
/*  49 */     this.Part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  50 */     this.Part_2.setTextureSize(64, 64);
/*  51 */     this.Part_2.mirror = true;
/*  52 */     setRotation(this.Part_2, 0.0F, 0.0F, -0.55850536F);
/*  53 */     this.Part_3 = new ModelRenderer(this, 33, 9);
/*  54 */     this.Part_3.addBox(-4.0F, 11.0F, -2.5F, 2, 1, 5);
/*  55 */     this.Part_3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  56 */     this.Part_3.setTextureSize(64, 64);
/*  57 */     this.Part_3.mirror = true;
/*  58 */     setRotation(this.Part_3, 0.0F, 0.0F, -0.55850536F);
/*  59 */     this.Part_4 = new ModelRenderer(this, 33, 17);
/*  60 */     this.Part_4.addBox(-4.5F, -1.0F, -3.5F, 3, 3, 2);
/*  61 */     this.Part_4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  62 */     this.Part_4.setTextureSize(64, 64);
/*  63 */     this.Part_4.mirror = true;
/*  64 */     setRotation(this.Part_4, 0.0F, 0.13962634F, -0.6981317F);
/*  65 */     this.Part_5 = new ModelRenderer(this, 49, 17);
/*  66 */     this.Part_5.addBox(-4.5F, 5.0F, -3.5F, 3, 3, 2);
/*  67 */     this.Part_5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  68 */     this.Part_5.setTextureSize(64, 64);
/*  69 */     this.Part_5.mirror = true;
/*  70 */     setRotation(this.Part_5, 0.0F, -0.08726646F, -0.5235988F);
/*  71 */     this.Part_6 = new ModelRenderer(this, 1, 25);
/*  72 */     this.Part_6.addBox(-6.0F, -3.5F, 3.0F, 7, 1, 4);
/*  73 */     this.Part_6.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  74 */     this.Part_6.setTextureSize(64, 64);
/*  75 */     this.Part_6.mirror = true;
/*  76 */     setRotation(this.Part_6, 0.0F, 0.0F, -0.43633232F);
/*  77 */     this.Part_7 = new ModelRenderer(this, 25, 25);
/*  78 */     this.Part_7.addBox(-6.0F, -2.5F, 7.0F, 7, 14, 1);
/*  79 */     this.Part_7.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  80 */     this.Part_7.setTextureSize(64, 64);
/*  81 */     this.Part_7.mirror = true;
/*  82 */     setRotation(this.Part_7, 0.0F, 0.01745329F, -0.4537856F);
/*  83 */     this.Part_8 = new ModelRenderer(this, 57, 1);
/*  84 */     this.Part_8.addBox(-5.5F, -2.5F, 7.2F, 1, 13, 1);
/*  85 */     this.Part_8.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  86 */     this.Part_8.setTextureSize(64, 64);
/*  87 */     this.Part_8.mirror = true;
/*  88 */     setRotation(this.Part_8, 0.0F, 0.01745329F, -0.55850536F);
/*  89 */     this.Part_9 = new ModelRenderer(this, 49, 25);
/*  90 */     this.Part_9.addBox(-2.0F, -1.5F, 7.0F, 2, 3, 2);
/*  91 */     this.Part_9.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  92 */     this.Part_9.setTextureSize(64, 64);
/*  93 */     this.Part_9.mirror = true;
/*  94 */     setRotation(this.Part_9, 0.0F, 0.01745329F, -0.36651915F);
/*  95 */     this.Part_10 = new ModelRenderer(this, 1, 33);
/*  96 */     this.Part_10.addBox(-2.0F, 2.5F, 7.0F, 2, 3, 2);
/*  97 */     this.Part_10.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  98 */     this.Part_10.setTextureSize(64, 64);
/*  99 */     this.Part_10.mirror = true;
/* 100 */     setRotation(this.Part_10, 0.0F, 0.01745329F, -0.5061455F);
/* 101 */     this.Part_11 = new ModelRenderer(this, 49, 33);
/* 102 */     this.Part_11.addBox(-1.0F, 7.5F, 7.0F, 2, 3, 2);
/* 103 */     this.Part_11.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 104 */     this.Part_11.setTextureSize(64, 64);
/* 105 */     this.Part_11.mirror = true;
/* 106 */     setRotation(this.Part_11, 0.0F, 0.01745329F, -0.2617994F);
/* 107 */     this.Part_12 = new ModelRenderer(this, 25, 1);
/* 108 */     this.Part_12.addBox(-1.5F, 2.7F, 7.2F, 1, 1, 2);
/* 109 */     this.Part_12.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 110 */     this.Part_12.setTextureSize(64, 64);
/* 111 */     this.Part_12.mirror = true;
/* 112 */     setRotation(this.Part_12, 0.0F, 0.01745329F, -0.5061455F);
/* 113 */     this.Part_13 = new ModelRenderer(this, 17, 33);
/* 114 */     this.Part_13.addBox(-0.5F, 7.7F, 7.2F, 1, 1, 2);
/* 115 */     this.Part_13.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 116 */     this.Part_13.setTextureSize(64, 64);
/* 117 */     this.Part_13.mirror = true;
/* 118 */     setRotation(this.Part_13, 0.0F, 0.01745329F, -0.2617994F);
/* 119 */     this.Part_14 = new ModelRenderer(this, 1, 41);
/* 120 */     this.Part_14.addBox(-1.5F, -1.3F, 7.2F, 1, 1, 2);
/* 121 */     this.Part_14.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 122 */     this.Part_14.setTextureSize(64, 64);
/* 123 */     this.Part_14.mirror = true;
/* 124 */     setRotation(this.Part_14, 0.0F, 0.01745329F, -0.36651915F);
/* 125 */     this.Part_15 = new ModelRenderer(this, 9, 41);
/* 126 */     this.Part_15.addBox(-3.5F, 5.2F, -3.7F, 1, 1, 2);
/* 127 */     this.Part_15.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 128 */     this.Part_15.setTextureSize(64, 64);
/* 129 */     this.Part_15.mirror = true;
/* 130 */     setRotation(this.Part_15, 0.0F, -0.08726646F, -0.5235988F);
/* 131 */     this.Part_16 = new ModelRenderer(this, 17, 41);
/* 132 */     this.Part_16.addBox(-3.5F, -0.8F, -3.7F, 1, 1, 2);
/* 133 */     this.Part_16.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 134 */     this.Part_16.setTextureSize(64, 64);
/* 135 */     this.Part_16.mirror = true;
/* 136 */     setRotation(this.Part_16, 0.0F, 0.13962634F, -0.6981317F);
/* 137 */     this.Part_17 = new ModelRenderer(this, 25, 41);
/* 138 */     this.Part_17.addBox(-6.0F, 12.5F, 3.0F, 7, 1, 4);
/* 139 */     this.Part_17.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 140 */     this.Part_17.setTextureSize(64, 64);
/* 141 */     this.Part_17.mirror = true;
/* 142 */     setRotation(this.Part_17, 0.0F, 0.0F, -0.43633232F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 147 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 148 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 149 */     this.Part_0.render(f5);
/* 150 */     this.Part_1.render(f5);
/* 151 */     this.Part_2.render(f5);
/* 152 */     this.Part_3.render(f5);
/* 153 */     this.Part_4.render(f5);
/* 154 */     this.Part_5.render(f5);
/* 155 */     this.Part_6.render(f5);
/* 156 */     this.Part_7.render(f5);
/* 157 */     this.Part_8.render(f5);
/* 158 */     this.Part_9.render(f5);
/* 159 */     this.Part_10.render(f5);
/* 160 */     this.Part_11.render(f5);
/* 161 */     this.Part_12.render(f5);
/* 162 */     this.Part_13.render(f5);
/* 163 */     this.Part_14.render(f5);
/* 164 */     this.Part_15.render(f5);
/* 165 */     this.Part_16.render(f5);
/* 166 */     this.Part_17.render(f5);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 171 */     model.rotateAngleX = x;
/* 172 */     model.rotateAngleY = y;
/* 173 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {}
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\model\ModelBackpackLarge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */