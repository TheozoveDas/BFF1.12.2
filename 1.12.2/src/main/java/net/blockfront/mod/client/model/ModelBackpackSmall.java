/*     */ package net.blockfront.mod.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ 
/*     */ public class ModelBackpackSmall
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
/*     */   ModelRenderer Part_18;
/*     */   ModelRenderer Part_19;
/*     */   
/*     */   public ModelBackpackSmall() {
/*  34 */     this.textureWidth = 64;
/*  35 */     this.textureHeight = 64;
/*     */     
/*  37 */     this.Part_0 = new ModelRenderer(this, 1, 1);
/*  38 */     this.Part_0.addBox(-4.0F, 1.0F, 1.5F, 8, 7, 3);
/*  39 */     this.Part_0.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  40 */     this.Part_0.setTextureSize(64, 64);
/*  41 */     this.Part_0.mirror = true;
/*  42 */     setRotation(this.Part_0, 0.0F, 0.0F, 0.0F);
/*  43 */     this.Part_1 = new ModelRenderer(this, 25, 1);
/*  44 */     this.Part_1.addBox(-4.01F, 1.7F, 1.2F, 8, 1, 4);
/*  45 */     this.Part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  46 */     this.Part_1.setTextureSize(64, 64);
/*  47 */     this.Part_1.mirror = true;
/*  48 */     setRotation(this.Part_1, 0.0F, 0.0F, 0.0F);
/*  49 */     this.Part_2 = new ModelRenderer(this, 49, 1);
/*  50 */     this.Part_2.addBox(2.0F, 2.5F, 4.3F, 1, 2, 1);
/*  51 */     this.Part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  52 */     this.Part_2.setTextureSize(64, 64);
/*  53 */     this.Part_2.mirror = true;
/*  54 */     setRotation(this.Part_2, 0.0F, 0.0F, 0.0F);
/*  55 */     this.Part_3 = new ModelRenderer(this, 57, 1);
/*  56 */     this.Part_3.addBox(-3.0F, 2.5F, 4.3F, 1, 2, 1);
/*  57 */     this.Part_3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  58 */     this.Part_3.setTextureSize(64, 64);
/*  59 */     this.Part_3.mirror = true;
/*  60 */     setRotation(this.Part_3, 0.0F, 0.0F, 0.0F);
/*  61 */     this.Part_4 = new ModelRenderer(this, 25, 9);
/*  62 */     this.Part_4.addBox(-0.5F, 3.8F, 4.3F, 1, 1, 1);
/*  63 */     this.Part_4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  64 */     this.Part_4.setTextureSize(64, 64);
/*  65 */     this.Part_4.mirror = true;
/*  66 */     setRotation(this.Part_4, 0.0F, 0.0F, 0.0F);
/*  67 */     this.Part_5 = new ModelRenderer(this, 33, 9);
/*  68 */     this.Part_5.addBox(-4.2F, -1.0F, -2.02F, 1, 1, 4);
/*  69 */     this.Part_5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  70 */     this.Part_5.setTextureSize(64, 64);
/*  71 */     this.Part_5.mirror = true;
/*  72 */     setRotation(this.Part_5, 0.0F, 0.0F, 0.0F);
/*  73 */     this.Part_6 = new ModelRenderer(this, 49, 9);
/*  74 */     this.Part_6.addBox(-4.2F, -0.5F, -3.02F, 1, 7, 1);
/*  75 */     this.Part_6.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  76 */     this.Part_6.setTextureSize(64, 64);
/*  77 */     this.Part_6.mirror = true;
/*  78 */     setRotation(this.Part_6, 0.0F, 0.0F, 0.0F);
/*  79 */     this.Part_7 = new ModelRenderer(this, 57, 9);
/*  80 */     this.Part_7.addBox(-4.2F, -0.5F, 1.98F, 1, 7, 1);
/*  81 */     this.Part_7.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  82 */     this.Part_7.setTextureSize(64, 64);
/*  83 */     this.Part_7.mirror = true;
/*  84 */     setRotation(this.Part_7, 0.0F, 0.0F, 0.0F);
/*  85 */     this.Part_8 = new ModelRenderer(this, 1, 17);
/*  86 */     this.Part_8.addBox(-4.2F, 6.0F, -2.02F, 1, 1, 4);
/*  87 */     this.Part_8.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  88 */     this.Part_8.setTextureSize(64, 64);
/*  89 */     this.Part_8.mirror = true;
/*  90 */     setRotation(this.Part_8, 0.0F, 0.0F, 0.0F);
/*  91 */     this.Part_9 = new ModelRenderer(this, 17, 17);
/*  92 */     this.Part_9.addBox(3.2F, -0.5F, -3.02F, 1, 7, 1);
/*  93 */     this.Part_9.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  94 */     this.Part_9.setTextureSize(64, 64);
/*  95 */     this.Part_9.mirror = true;
/*  96 */     setRotation(this.Part_9, 0.0F, 0.0F, 0.0F);
/*  97 */     this.Part_10 = new ModelRenderer(this, 25, 17);
/*  98 */     this.Part_10.addBox(3.2F, 6.0F, -2.02F, 1, 1, 4);
/*  99 */     this.Part_10.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 100 */     this.Part_10.setTextureSize(64, 64);
/* 101 */     this.Part_10.mirror = true;
/* 102 */     setRotation(this.Part_10, 0.0F, 0.0F, 0.0F);
/* 103 */     this.Part_11 = new ModelRenderer(this, 41, 17);
/* 104 */     this.Part_11.addBox(3.2F, -1.0F, -2.02F, 1, 1, 4);
/* 105 */     this.Part_11.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 106 */     this.Part_11.setTextureSize(64, 64);
/* 107 */     this.Part_11.mirror = true;
/* 108 */     setRotation(this.Part_11, 0.0F, 0.0F, 0.0F);
/* 109 */     this.Part_12 = new ModelRenderer(this, 1, 25);
/* 110 */     this.Part_12.addBox(3.2F, -0.5F, 1.98F, 1, 7, 1);
/* 111 */     this.Part_12.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 112 */     this.Part_12.setTextureSize(64, 64);
/* 113 */     this.Part_12.mirror = true;
/* 114 */     setRotation(this.Part_12, 0.0F, 0.0F, 0.0F);
/* 115 */     this.Part_13 = new ModelRenderer(this, 25, 25);
/* 116 */     this.Part_13.addBox(-4.01F, 2.7F, 1.2F, 8, 4, 1);
/* 117 */     this.Part_13.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 118 */     this.Part_13.setTextureSize(64, 64);
/* 119 */     this.Part_13.mirror = true;
/* 120 */     setRotation(this.Part_13, 0.0F, 0.0F, 0.0F);
/* 121 */     this.Part_14 = new ModelRenderer(this, 9, 25);
/* 122 */     this.Part_14.addBox(-1.0F, 0.5F, 3.2F, 1, 3, 1);
/* 123 */     this.Part_14.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 124 */     this.Part_14.setTextureSize(64, 64);
/* 125 */     this.Part_14.mirror = true;
/* 126 */     setRotation(this.Part_14, 0.0F, 0.0F, 0.9075712F);
/* 127 */     this.Part_15 = new ModelRenderer(this, 49, 25);
/* 128 */     this.Part_15.addBox(0.0F, 0.5F, 3.2F, 1, 3, 1);
/* 129 */     this.Part_15.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 130 */     this.Part_15.setTextureSize(64, 64);
/* 131 */     this.Part_15.mirror = true;
/* 132 */     setRotation(this.Part_15, 0.0F, 0.0F, -0.9075712F);
/* 133 */     this.Part_16 = new ModelRenderer(this, 41, 9);
/* 134 */     this.Part_16.addBox(-1.0F, -0.5F, 3.2F, 2, 1, 1);
/* 135 */     this.Part_16.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 136 */     this.Part_16.setTextureSize(64, 64);
/* 137 */     this.Part_16.mirror = true;
/* 138 */     setRotation(this.Part_16, 0.0F, 0.0F, 0.0F);
/* 139 */     this.Part_17 = new ModelRenderer(this, 9, 33);
/* 140 */     this.Part_17.addBox(-4.0F, -2.47F, 3.88F, 8, 1, 1);
/* 141 */     this.Part_17.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 142 */     this.Part_17.setTextureSize(64, 64);
/* 143 */     this.Part_17.mirror = true;
/* 144 */     setRotation(this.Part_17, -0.7853982F, 0.0F, 0.0F);
/* 145 */     this.Part_18 = new ModelRenderer(this, 33, 33);
/* 146 */     this.Part_18.addBox(-4.02F, -0.47F, 5.88F, 8, 1, 1);
/* 147 */     this.Part_18.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 148 */     this.Part_18.setTextureSize(64, 64);
/* 149 */     this.Part_18.mirror = true;
/* 150 */     setRotation(this.Part_18, -0.7853982F, 0.0F, 0.0F);
/* 151 */     this.Part_19 = new ModelRenderer(this, 1, 41);
/* 152 */     this.Part_19.addBox(-4.01F, 2.7F, 3.2F, 8, 2, 2);
/* 153 */     this.Part_19.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 154 */     this.Part_19.setTextureSize(64, 64);
/* 155 */     this.Part_19.mirror = true;
/* 156 */     setRotation(this.Part_19, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 162 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 163 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 164 */     this.Part_0.render(f5);
/* 165 */     this.Part_1.render(f5);
/* 166 */     this.Part_2.render(f5);
/* 167 */     this.Part_3.render(f5);
/* 168 */     this.Part_4.render(f5);
/* 169 */     this.Part_5.render(f5);
/* 170 */     this.Part_6.render(f5);
/* 171 */     this.Part_7.render(f5);
/* 172 */     this.Part_8.render(f5);
/* 173 */     this.Part_9.render(f5);
/* 174 */     this.Part_10.render(f5);
/* 175 */     this.Part_11.render(f5);
/* 176 */     this.Part_12.render(f5);
/* 177 */     this.Part_13.render(f5);
/* 178 */     this.Part_14.render(f5);
/* 179 */     this.Part_15.render(f5);
/* 180 */     this.Part_16.render(f5);
/* 181 */     this.Part_17.render(f5);
/* 182 */     this.Part_18.render(f5);
/* 183 */     this.Part_19.render(f5);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 188 */     model.rotateAngleX = x;
/* 189 */     model.rotateAngleY = y;
/* 190 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {}
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\model\ModelBackpackSmall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */