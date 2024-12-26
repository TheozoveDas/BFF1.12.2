/*     */ package net.blockfront.mod.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ 
/*     */ public class ModelBackpackMedium
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
/*     */   ModelRenderer Part_20;
/*     */   ModelRenderer Part_21;
/*     */   ModelRenderer Part_22;
/*     */   ModelRenderer Part_23;
/*     */   ModelRenderer Part_24;
/*     */   ModelRenderer Part_25;
/*     */   ModelRenderer Part_26;
/*     */   ModelRenderer Part_27;
/*     */   ModelRenderer Part_28;
/*     */   ModelRenderer Part_29;
/*     */   
/*     */   public ModelBackpackMedium() {
/*  44 */     this.textureWidth = 64;
/*  45 */     this.textureHeight = 64;
/*     */     
/*  47 */     this.Part_0 = new ModelRenderer(this, 1, 1);
/*  48 */     this.Part_0.addBox(2.5F, -0.5F, 1.98F, 2, 10, 1);
/*  49 */     this.Part_0.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  50 */     this.Part_0.setTextureSize(64, 64);
/*  51 */     this.Part_0.mirror = true;
/*  52 */     setRotation(this.Part_0, 0.0F, 0.0F, 0.0F);
/*  53 */     this.Part_1 = new ModelRenderer(this, 9, 1);
/*  54 */     this.Part_1.addBox(2.5F, -1.0F, -2.02F, 2, 1, 4);
/*  55 */     this.Part_1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  56 */     this.Part_1.setTextureSize(64, 64);
/*  57 */     this.Part_1.mirror = true;
/*  58 */     setRotation(this.Part_1, 0.0F, 0.0F, 0.0F);
/*  59 */     this.Part_2 = new ModelRenderer(this, 25, 1);
/*  60 */     this.Part_2.addBox(2.5F, -0.5F, -3.02F, 2, 10, 1);
/*  61 */     this.Part_2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  62 */     this.Part_2.setTextureSize(64, 64);
/*  63 */     this.Part_2.mirror = true;
/*  64 */     setRotation(this.Part_2, 0.0F, 0.0F, 0.0F);
/*  65 */     this.Part_3 = new ModelRenderer(this, 33, 1);
/*  66 */     this.Part_3.addBox(2.5F, 9.0F, -2.02F, 2, 1, 4);
/*  67 */     this.Part_3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  68 */     this.Part_3.setTextureSize(64, 64);
/*  69 */     this.Part_3.mirror = true;
/*  70 */     setRotation(this.Part_3, 0.0F, 0.0F, 0.0F);
/*  71 */     this.Part_4 = new ModelRenderer(this, 49, 1);
/*  72 */     this.Part_4.addBox(-4.52F, -0.5F, 1.98F, 2, 10, 1);
/*  73 */     this.Part_4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  74 */     this.Part_4.setTextureSize(64, 64);
/*  75 */     this.Part_4.mirror = true;
/*  76 */     setRotation(this.Part_4, 0.0F, 0.0F, 0.0F);
/*  77 */     this.Part_5 = new ModelRenderer(this, 9, 9);
/*  78 */     this.Part_5.addBox(-4.52F, -1.0F, -2.02F, 2, 1, 4);
/*  79 */     this.Part_5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  80 */     this.Part_5.setTextureSize(64, 64);
/*  81 */     this.Part_5.mirror = true;
/*  82 */     setRotation(this.Part_5, 0.0F, 0.0F, 0.0F);
/*  83 */     this.Part_6 = new ModelRenderer(this, 57, 1);
/*  84 */     this.Part_6.addBox(-4.52F, -0.5F, -3.02F, 2, 10, 1);
/*  85 */     this.Part_6.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  86 */     this.Part_6.setTextureSize(64, 64);
/*  87 */     this.Part_6.mirror = true;
/*  88 */     setRotation(this.Part_6, 0.0F, 0.0F, 0.0F);
/*  89 */     this.Part_7 = new ModelRenderer(this, 33, 9);
/*  90 */     this.Part_7.addBox(-4.52F, 9.0F, -2.02F, 2, 1, 4);
/*  91 */     this.Part_7.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  92 */     this.Part_7.setTextureSize(64, 64);
/*  93 */     this.Part_7.mirror = true;
/*  94 */     setRotation(this.Part_7, 0.0F, 0.0F, 0.0F);
/*  95 */     this.Part_8 = new ModelRenderer(this, 1, 17);
/*  96 */     this.Part_8.addBox(-4.51F, 0.0F, 2.0F, 9, 10, 2);
/*  97 */     this.Part_8.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  98 */     this.Part_8.setTextureSize(64, 64);
/*  99 */     this.Part_8.mirror = true;
/* 100 */     setRotation(this.Part_8, 0.0F, 0.0F, 0.0F);
/* 101 */     this.Part_9 = new ModelRenderer(this, 25, 17);
/* 102 */     this.Part_9.addBox(-3.5F, 4.5F, 4.0F, 7, 5, 2);
/* 103 */     this.Part_9.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 104 */     this.Part_9.setTextureSize(64, 64);
/* 105 */     this.Part_9.mirror = true;
/* 106 */     setRotation(this.Part_9, 0.0F, 0.0F, 0.0F);
/* 107 */     this.Part_10 = new ModelRenderer(this, 25, 25);
/* 108 */     this.Part_10.addBox(-3.5F, 1.85F, 1.05F, 7, 5, 2);
/* 109 */     this.Part_10.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 110 */     this.Part_10.setTextureSize(64, 64);
/* 111 */     this.Part_10.mirror = true;
/* 112 */     setRotation(this.Part_10, 0.5061455F, 0.0F, 0.0F);
/* 113 */     this.Part_11 = new ModelRenderer(this, 1, 33);
/* 114 */     this.Part_11.addBox(-4.0F, 4.51F, 4.0F, 8, 5, 1);
/* 115 */     this.Part_11.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 116 */     this.Part_11.setTextureSize(64, 64);
/* 117 */     this.Part_11.mirror = true;
/* 118 */     setRotation(this.Part_11, 0.0F, 0.0F, 0.0F);
/* 119 */     this.Part_12 = new ModelRenderer(this, 49, 17);
/* 120 */     this.Part_12.addBox(-2.5F, 5.95F, 5.5F, 5, 3, 1);
/* 121 */     this.Part_12.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 122 */     this.Part_12.setTextureSize(64, 64);
/* 123 */     this.Part_12.mirror = true;
/* 124 */     setRotation(this.Part_12, 0.0F, 0.0F, 0.0F);
/* 125 */     this.Part_13 = new ModelRenderer(this, 49, 25);
/* 126 */     this.Part_13.addBox(-2.5F, 5.35F, 1.8F, 5, 3, 1);
/* 127 */     this.Part_13.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 128 */     this.Part_13.setTextureSize(64, 64);
/* 129 */     this.Part_13.mirror = true;
/* 130 */     setRotation(this.Part_13, 0.5061455F, 0.0F, 0.0F);
/* 131 */     this.Part_14 = new ModelRenderer(this, 25, 33);
/* 132 */     this.Part_14.addBox(-1.5F, -0.5F, 2.5F, 1, 3, 1);
/* 133 */     this.Part_14.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 134 */     this.Part_14.setTextureSize(64, 64);
/* 135 */     this.Part_14.mirror = true;
/* 136 */     setRotation(this.Part_14, 0.0F, 0.0F, 0.9075712F);
/* 137 */     this.Part_15 = new ModelRenderer(this, 33, 33);
/* 138 */     this.Part_15.addBox(0.5F, -0.5F, 2.5F, 1, 3, 1);
/* 139 */     this.Part_15.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 140 */     this.Part_15.setTextureSize(64, 64);
/* 141 */     this.Part_15.mirror = true;
/* 142 */     setRotation(this.Part_15, 0.0F, 0.0F, -0.9075712F);
/* 143 */     this.Part_16 = new ModelRenderer(this, 41, 33);
/* 144 */     this.Part_16.addBox(-0.5F, -1.5F, 2.5F, 1, 1, 1);
/* 145 */     this.Part_16.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 146 */     this.Part_16.setTextureSize(64, 64);
/* 147 */     this.Part_16.mirror = true;
/* 148 */     setRotation(this.Part_16, 0.0F, 0.0F, 0.0F);
/* 149 */     this.Part_17 = new ModelRenderer(this, 49, 33);
/* 150 */     this.Part_17.addBox(-1.0F, 6.8F, 5.5F, 2, 3, 2);
/* 151 */     this.Part_17.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 152 */     this.Part_17.setTextureSize(64, 64);
/* 153 */     this.Part_17.mirror = true;
/* 154 */     setRotation(this.Part_17, 0.01745329F, -0.55850536F, -0.03490659F);
/* 155 */     this.Part_18 = new ModelRenderer(this, 1, 41);
/* 156 */     this.Part_18.addBox(-1.0F, 6.55F, 5.5F, 2, 3, 2);
/* 157 */     this.Part_18.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 158 */     this.Part_18.setTextureSize(64, 64);
/* 159 */     this.Part_18.mirror = true;
/* 160 */     setRotation(this.Part_18, 0.05235988F, 0.55850536F, 0.01745329F);
/* 161 */     this.Part_19 = new ModelRenderer(this, 17, 41);
/* 162 */     this.Part_19.addBox(-1.0F, 6.3F, 5.5F, 2, 3, 2);
/* 163 */     this.Part_19.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 164 */     this.Part_19.setTextureSize(64, 64);
/* 165 */     this.Part_19.mirror = true;
/* 166 */     setRotation(this.Part_19, 0.01745329F, 0.17453294F, -0.01745329F);
/* 167 */     this.Part_20 = new ModelRenderer(this, 33, 41);
/* 168 */     this.Part_20.addBox(-1.0F, 6.51F, 5.5F, 2, 3, 2);
/* 169 */     this.Part_20.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 170 */     this.Part_20.setTextureSize(64, 64);
/* 171 */     this.Part_20.mirror = true;
/* 172 */     setRotation(this.Part_20, 0.03490659F, -0.17453294F, 0.01745329F);
/* 173 */     this.Part_21 = new ModelRenderer(this, 49, 41);
/* 174 */     this.Part_21.addBox(-2.5F, 3.4F, 2.25F, 5, 3, 1);
/* 175 */     this.Part_21.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 176 */     this.Part_21.setTextureSize(64, 64);
/* 177 */     this.Part_21.mirror = true;
/* 178 */     setRotation(this.Part_21, 0.5061455F, 0.0F, 0.0F);
/* 179 */     this.Part_22 = new ModelRenderer(this, 1, 49);
/* 180 */     this.Part_22.addBox(-0.5F, 7.0F, 6.57F, 1, 1, 1);
/* 181 */     this.Part_22.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 182 */     this.Part_22.setTextureSize(64, 64);
/* 183 */     this.Part_22.mirror = true;
/* 184 */     setRotation(this.Part_22, 0.05235988F, 0.55850536F, 0.01745329F);
/* 185 */     this.Part_23 = new ModelRenderer(this, 9, 49);
/* 186 */     this.Part_23.addBox(-0.5F, 6.75F, 6.57F, 1, 1, 1);
/* 187 */     this.Part_23.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 188 */     this.Part_23.setTextureSize(64, 64);
/* 189 */     this.Part_23.mirror = true;
/* 190 */     setRotation(this.Part_23, 0.01745329F, 0.17453294F, -0.01745329F);
/* 191 */     this.Part_24 = new ModelRenderer(this, 17, 49);
/* 192 */     this.Part_24.addBox(-0.5F, 6.96F, 6.57F, 1, 1, 1);
/* 193 */     this.Part_24.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 194 */     this.Part_24.setTextureSize(64, 64);
/* 195 */     this.Part_24.mirror = true;
/* 196 */     setRotation(this.Part_24, 0.03490659F, -0.17453294F, 0.01745329F);
/* 197 */     this.Part_25 = new ModelRenderer(this, 25, 49);
/* 198 */     this.Part_25.addBox(-0.5F, 7.25F, 6.57F, 1, 1, 1);
/* 199 */     this.Part_25.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 200 */     this.Part_25.setTextureSize(64, 64);
/* 201 */     this.Part_25.mirror = true;
/* 202 */     setRotation(this.Part_25, 0.01745329F, -0.55850536F, -0.03490659F);
/* 203 */     this.Part_26 = new ModelRenderer(this, 33, 49);
/* 204 */     this.Part_26.addBox(-2.0F, 3.6F, 2.35F, 1, 1, 1);
/* 205 */     this.Part_26.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 206 */     this.Part_26.setTextureSize(64, 64);
/* 207 */     this.Part_26.mirror = true;
/* 208 */     setRotation(this.Part_26, 0.5061455F, 0.0F, 0.0F);
/* 209 */     this.Part_27 = new ModelRenderer(this, 41, 49);
/* 210 */     this.Part_27.addBox(1.0F, 3.6F, 2.35F, 1, 1, 1);
/* 211 */     this.Part_27.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 212 */     this.Part_27.setTextureSize(64, 64);
/* 213 */     this.Part_27.mirror = true;
/* 214 */     setRotation(this.Part_27, 0.5061455F, 0.0F, 0.0F);
/* 215 */     this.Part_28 = new ModelRenderer(this, 49, 49);
/* 216 */     this.Part_28.addBox(-5.0F, 0.5F, -3.52F, 3, 3, 1);
/* 217 */     this.Part_28.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 218 */     this.Part_28.setTextureSize(64, 64);
/* 219 */     this.Part_28.mirror = true;
/* 220 */     setRotation(this.Part_28, 0.05235988F, 0.0F, -0.05235988F);
/* 221 */     this.Part_29 = new ModelRenderer(this, 1, 57);
/* 222 */     this.Part_29.addBox(2.0F, 0.2F, -3.52F, 3, 3, 1);
/* 223 */     this.Part_29.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 224 */     this.Part_29.setTextureSize(64, 64);
/* 225 */     this.Part_29.mirror = true;
/* 226 */     setRotation(this.Part_29, 0.05235988F, 0.0F, 0.01745329F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 232 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 233 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 234 */     this.Part_0.render(f5);
/* 235 */     this.Part_1.render(f5);
/* 236 */     this.Part_2.render(f5);
/* 237 */     this.Part_3.render(f5);
/* 238 */     this.Part_4.render(f5);
/* 239 */     this.Part_5.render(f5);
/* 240 */     this.Part_6.render(f5);
/* 241 */     this.Part_7.render(f5);
/* 242 */     this.Part_8.render(f5);
/* 243 */     this.Part_9.render(f5);
/* 244 */     this.Part_10.render(f5);
/* 245 */     this.Part_11.render(f5);
/* 246 */     this.Part_12.render(f5);
/* 247 */     this.Part_13.render(f5);
/* 248 */     this.Part_14.render(f5);
/* 249 */     this.Part_15.render(f5);
/* 250 */     this.Part_16.render(f5);
/* 251 */     this.Part_17.render(f5);
/* 252 */     this.Part_18.render(f5);
/* 253 */     this.Part_19.render(f5);
/* 254 */     this.Part_20.render(f5);
/* 255 */     this.Part_21.render(f5);
/* 256 */     this.Part_22.render(f5);
/* 257 */     this.Part_23.render(f5);
/* 258 */     this.Part_24.render(f5);
/* 259 */     this.Part_25.render(f5);
/* 260 */     this.Part_26.render(f5);
/* 261 */     this.Part_27.render(f5);
/* 262 */     this.Part_28.render(f5);
/* 263 */     this.Part_29.render(f5);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 268 */     model.rotateAngleX = x;
/* 269 */     model.rotateAngleY = y;
/* 270 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {}
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\model\ModelBackpackMedium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */