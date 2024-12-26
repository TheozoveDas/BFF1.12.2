/*     */ package net.blockfront.mod.client.render;
/*     */ 
/*     */ import net.blockfront.mod.client.model.ModelCorpseSkeleton;
/*     */ import net.blockfront.mod.entity.EntityLootableBody;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderBiped;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.ForgeHooksClient;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderLootableBody extends RenderBiped {
/*  35 */   private static final ResourceLocation skeletonTexture = new ResourceLocation("textures/entity/skeleton/skeleton.png");
/*     */   
/*  37 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */ 
/*     */   
/*     */   public ModelBiped modelArmorChestplate;
/*     */   
/*     */   public ModelBiped modelArmor;
/*     */ 
/*     */   
/*     */   public RenderLootableBody(RenderManager rm) {
/*  46 */     super((ModelBiped)new ModelCorpseSkeleton(), 0.5F);
/*     */ 
/*     */     
/*  49 */     this.modelArmorChestplate = new ModelBiped(1.0F) { public void setLivingAnimations(EntityLivingBase e, float f1, float f2, float f3) {}
/*     */         
/*     */         public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity e) {} }
/*     */       ;
/*  53 */     this.modelArmor = new ModelBiped(0.5F)
/*     */       {
/*     */         public void setLivingAnimations(EntityLivingBase e, float f1, float f2, float f3) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity e) {}
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityLiving p_doRender_1_, double p_doRender_2_, double p_doRender_4_, double p_doRender_6_, float p_doRender_8_, float p_doRender_9_) {
/*  76 */     copyAngles(((ModelBiped)this.mainModel).bipedLeftArm, this.modelArmor.bipedLeftArm);
/*  77 */     copyAngles(((ModelBiped)this.mainModel).bipedRightArm, this.modelArmor.bipedRightArm);
/*  78 */     copyAngles(((ModelBiped)this.mainModel).bipedLeftLeg, this.modelArmor.bipedLeftLeg);
/*  79 */     copyAngles(((ModelBiped)this.mainModel).bipedRightLeg, this.modelArmor.bipedRightLeg);
/*  80 */     copyAngles(((ModelBiped)this.mainModel).bipedLeftArm, this.modelArmorChestplate.bipedLeftArm);
/*  81 */     copyAngles(((ModelBiped)this.mainModel).bipedRightArm, this.modelArmorChestplate.bipedRightArm);
/*  82 */     copyAngles(((ModelBiped)this.mainModel).bipedLeftLeg, this.modelArmorChestplate.bipedLeftLeg);
/*  83 */     copyAngles(((ModelBiped)this.mainModel).bipedRightLeg, this.modelArmorChestplate.bipedRightLeg);
/*     */     
/*  85 */     double d3 = p_doRender_4_ - p_doRender_1_.yOffset;
/*  86 */     doPlayerRender((EntityLootableBody)p_doRender_1_, p_doRender_2_, d3, p_doRender_6_, p_doRender_8_, p_doRender_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   private void copyAngles(ModelRenderer src, ModelRenderer dest) {
/*  91 */     dest.rotateAngleX = src.rotateAngleX;
/*  92 */     dest.rotateAngleY = src.rotateAngleY;
/*  93 */     dest.rotateAngleZ = src.rotateAngleZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doLivingRender(EntityLivingBase p_doRender_1_, double p_doRender_2_, double p_doRender_4_, double p_doRender_6_, float p_doRender_8_, float p_doRender_9_) {
/*  98 */     if (MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Pre(p_doRender_1_, (RendererLivingEntity)this, p_doRender_2_, p_doRender_4_, p_doRender_6_))) {
/*     */       return;
/*     */     }
/* 101 */     GL11.glPushMatrix();
/* 102 */     GL11.glDisable(2884);
/*     */     
/* 104 */     this.mainModel.onGround = renderSwingProgress(p_doRender_1_, p_doRender_9_);
/* 105 */     if (this.renderPassModel != null) {
/* 106 */       this.renderPassModel.onGround = this.mainModel.onGround;
/*     */     }
/* 108 */     this.mainModel.isRiding = p_doRender_1_.isRiding();
/* 109 */     if (this.renderPassModel != null) {
/* 110 */       this.renderPassModel.isRiding = this.mainModel.isRiding;
/*     */     }
/* 112 */     this.mainModel.isChild = p_doRender_1_.isChild();
/* 113 */     if (this.renderPassModel != null) {
/* 114 */       this.renderPassModel.isChild = this.mainModel.isChild;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 119 */       float f2 = interpolateRotation(p_doRender_1_.prevRenderYawOffset, p_doRender_1_.renderYawOffset, p_doRender_9_);
/* 120 */       f2 = interpolateRotation(p_doRender_1_.prevRotationYawHead, p_doRender_1_.rotationYawHead, p_doRender_9_);
/* 121 */       if (p_doRender_1_.isRiding() && p_doRender_1_.ridingEntity instanceof EntityLivingBase) {
/* 122 */         EntityLivingBase entitylivingbase1 = (EntityLivingBase)p_doRender_1_.ridingEntity;
/* 123 */         f2 = interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, p_doRender_9_);
/* 124 */         float f = MathHelper.wrapAngleTo180_float(f2 - f2);
/* 125 */         if (f < -85.0F) {
/* 126 */           f = -85.0F;
/*     */         }
/* 128 */         if (f >= 85.0F) {
/* 129 */           f = 85.0F;
/*     */         }
/* 131 */         f2 -= f;
/* 132 */         if (f * f > 2500.0F) {
/* 133 */           f2 += f * 0.2F;
/*     */         }
/*     */       } 
/* 136 */       float f4 = p_doRender_1_.prevRotationPitch + (p_doRender_1_.rotationPitch - p_doRender_1_.prevRotationPitch) * p_doRender_9_;
/* 137 */       renderLivingAt(p_doRender_1_, p_doRender_2_, p_doRender_4_, p_doRender_6_);
/* 138 */       float f3 = handleRotationFloat(p_doRender_1_, p_doRender_9_);
/* 139 */       rotateCorpse(p_doRender_1_, f3, f2, p_doRender_9_);
/* 140 */       float f5 = 0.0625F;
/* 141 */       GL11.glEnable(32826);
/* 142 */       GL11.glScalef(-1.0F, -1.0F, 1.0F);
/* 143 */       preRenderCallback(p_doRender_1_, p_doRender_9_);
/* 144 */       GL11.glTranslatef(0.0F, -1.5078125F, 0.0F);
/* 145 */       float f6 = p_doRender_1_.prevLimbSwingAmount + (p_doRender_1_.limbSwingAmount - p_doRender_1_.prevLimbSwingAmount) * p_doRender_9_;
/* 146 */       float f7 = p_doRender_1_.limbSwing - p_doRender_1_.limbSwingAmount * (1.0F - p_doRender_9_);
/* 147 */       if (p_doRender_1_.isChild()) {
/* 148 */         f7 *= 3.0F;
/*     */       }
/* 150 */       if (f6 > 1.0F) {
/* 151 */         f6 = 1.0F;
/*     */       }
/* 153 */       GL11.glEnable(3008);
/*     */ 
/*     */       
/* 156 */       GL11.glColor3f(0.8F, 0.8F, 0.8F);
/* 157 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 158 */       GL11.glTranslatef(0.0F, -0.5F, -1.375F);
/*     */       
/* 160 */       this.mainModel.setLivingAnimations(p_doRender_1_, f7, f6, p_doRender_9_);
/* 161 */       renderModel(p_doRender_1_, f7, f6, f3, f2 - f2, f4, 0.0625F);
/* 162 */       for (int i = 0; i < 4; i++) {
/* 163 */         int k = shouldRenderPass(p_doRender_1_, i, p_doRender_9_);
/* 164 */         if (k > 0) {
/* 165 */           this.renderPassModel.setLivingAnimations(p_doRender_1_, f7, f6, p_doRender_9_);
/* 166 */           this.renderPassModel.render((Entity)p_doRender_1_, f7, f6, f3, f2 - f2, f4, 0.0625F);
/* 167 */           if ((k & 0xF0) == 16) {
/* 168 */             func_82408_c(p_doRender_1_, i, p_doRender_9_);
/* 169 */             this.renderPassModel.render((Entity)p_doRender_1_, f7, f6, f3, f2 - f2, f4, 0.0625F);
/*     */           } 
/* 171 */           if ((k & 0xF) == 15) {
/* 172 */             float f8 = p_doRender_1_.ticksExisted + p_doRender_9_;
/* 173 */             bindTexture(RES_ITEM_GLINT);
/* 174 */             GL11.glEnable(3042);
/* 175 */             float f9 = 0.5F;
/* 176 */             GL11.glColor4f(0.5F, 0.5F, 0.5F, 1.0F);
/* 177 */             GL11.glDepthFunc(514);
/* 178 */             GL11.glDepthMask(false);
/* 179 */             for (int m = 0; m < 2; m++) {
/* 180 */               GL11.glDisable(2896);
/* 181 */               float f10 = 0.76F;
/* 182 */               GL11.glColor4f(0.38F, 0.19F, 0.608F, 1.0F);
/* 183 */               GL11.glBlendFunc(768, 1);
/* 184 */               GL11.glMatrixMode(5890);
/* 185 */               GL11.glLoadIdentity();
/* 186 */               float f11 = f8 * (0.001F + m * 0.003F) * 20.0F;
/* 187 */               float f12 = 0.33333334F;
/* 188 */               GL11.glScalef(0.33333334F, 0.33333334F, 0.33333334F);
/* 189 */               GL11.glRotatef(30.0F - m * 60.0F, 0.0F, 0.0F, 1.0F);
/* 190 */               GL11.glTranslatef(0.0F, f11, 0.0F);
/* 191 */               GL11.glMatrixMode(5888);
/* 192 */               this.renderPassModel.render((Entity)p_doRender_1_, f7, f6, f3, f2 - f2, f4, 0.0625F);
/*     */             } 
/* 194 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 195 */             GL11.glMatrixMode(5890);
/* 196 */             GL11.glDepthMask(true);
/* 197 */             GL11.glLoadIdentity();
/* 198 */             GL11.glMatrixMode(5888);
/* 199 */             GL11.glEnable(2896);
/* 200 */             GL11.glDisable(3042);
/* 201 */             GL11.glDepthFunc(515);
/*     */           } 
/* 203 */           GL11.glDisable(3042);
/* 204 */           GL11.glEnable(3008);
/*     */         } 
/*     */       } 
/* 207 */       GL11.glDepthMask(true);
/* 208 */       renderEquippedItems(p_doRender_1_, p_doRender_9_);
/* 209 */       float f13 = p_doRender_1_.getBrightness(p_doRender_9_);
/* 210 */       int j = getColorMultiplier(p_doRender_1_, f13, p_doRender_9_);
/* 211 */       OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 212 */       GL11.glDisable(3553);
/* 213 */       OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 214 */       if ((j >> 24 & 0xFF) > 0 || p_doRender_1_.hurtTime > 0 || p_doRender_1_.deathTime > 0) {
/* 215 */         GL11.glDisable(3553);
/* 216 */         GL11.glDisable(3008);
/* 217 */         GL11.glEnable(3042);
/* 218 */         GL11.glBlendFunc(770, 771);
/* 219 */         GL11.glDepthFunc(514);
/* 220 */         if (p_doRender_1_.hurtTime > 0 || p_doRender_1_.deathTime > 0) {
/* 221 */           GL11.glColor4f(f13, 0.0F, 0.0F, 0.4F);
/* 222 */           this.mainModel.render((Entity)p_doRender_1_, f7, f6, f3, f2 - f2, f4, 0.0625F);
/* 223 */           for (int l = 0; l < 4; l++) {
/* 224 */             if (inheritRenderPass(p_doRender_1_, l, p_doRender_9_) >= 0) {
/* 225 */               GL11.glColor4f(f13, 0.0F, 0.0F, 0.4F);
/* 226 */               this.renderPassModel.render((Entity)p_doRender_1_, f7, f6, f3, f2 - f2, f4, 0.0625F);
/*     */             } 
/*     */           } 
/*     */         } 
/* 230 */         if ((j >> 24 & 0xFF) > 0) {
/* 231 */           float f8 = (j >> 16 & 0xFF) / 255.0F;
/* 232 */           float f9 = (j >> 8 & 0xFF) / 255.0F;
/* 233 */           float f14 = (j & 0xFF) / 255.0F;
/* 234 */           float f10 = (j >> 24 & 0xFF) / 255.0F;
/* 235 */           GL11.glColor4f(f8, f9, f14, f10);
/* 236 */           this.mainModel.render((Entity)p_doRender_1_, f7, f6, f3, f2 - f2, f4, 0.0625F);
/* 237 */           for (int i2 = 0; i2 < 4; i2++) {
/* 238 */             if (inheritRenderPass(p_doRender_1_, i2, p_doRender_9_) >= 0) {
/* 239 */               GL11.glColor4f(f8, f9, f14, f10);
/* 240 */               this.renderPassModel.render((Entity)p_doRender_1_, f7, f6, f3, f2 - f2, f4, 0.0625F);
/*     */             } 
/*     */           } 
/*     */         } 
/* 244 */         GL11.glDepthFunc(515);
/* 245 */         GL11.glDisable(3042);
/* 246 */         GL11.glEnable(3008);
/* 247 */         GL11.glEnable(3553);
/*     */       } 
/* 249 */       GL11.glDisable(32826);
/*     */     }
/* 251 */     catch (Exception exception) {
/* 252 */       logger.error("Couldn't render entity", exception);
/*     */     } 
/* 254 */     OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 255 */     GL11.glEnable(3553);
/* 256 */     OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 257 */     GL11.glEnable(2884);
/*     */ 
/*     */     
/* 260 */     GL11.glPopMatrix();
/* 261 */     passSpecialRender(p_doRender_1_, p_doRender_2_, p_doRender_4_, p_doRender_6_);
/* 262 */     MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post(p_doRender_1_, (RendererLivingEntity)this, p_doRender_2_, p_doRender_4_, p_doRender_6_));
/*     */   }
/*     */   
/*     */   protected void renderModel(EntityLivingBase p_renderModel_1_, float p_renderModel_2_, float p_renderModel_3_, float p_renderModel_4_, float p_renderModel_5_, float p_renderModel_6_, float p_renderModel_7_) {
/* 266 */     bindTexture(getEntityTexture(p_renderModel_1_));
/* 267 */     if (!p_renderModel_1_.isInvisible()) {
/* 268 */       this.mainModel.render((Entity)p_renderModel_1_, p_renderModel_2_, p_renderModel_3_, p_renderModel_4_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_);
/*     */     }
/* 270 */     else if (!p_renderModel_1_.isInvisibleToPlayer((EntityPlayer)(Minecraft.getMinecraft()).thePlayer)) {
/* 271 */       GL11.glPushMatrix();
/* 272 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
/* 273 */       GL11.glDepthMask(false);
/* 274 */       GL11.glEnable(3042);
/* 275 */       GL11.glBlendFunc(770, 771);
/* 276 */       GL11.glAlphaFunc(516, 0.003921569F);
/* 277 */       this.mainModel.render((Entity)p_renderModel_1_, p_renderModel_2_, p_renderModel_3_, p_renderModel_4_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_);
/* 278 */       GL11.glDisable(3042);
/* 279 */       GL11.glAlphaFunc(516, 0.1F);
/* 280 */       GL11.glPopMatrix();
/* 281 */       GL11.glDepthMask(true);
/*     */     } else {
/*     */       
/* 284 */       this.mainModel.setRotationAngles(p_renderModel_2_, p_renderModel_3_, p_renderModel_4_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_, (Entity)p_renderModel_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPlayerRender(EntityLootableBody body, double p_doRender_2_, double p_doRender_4_, double p_doRender_6_, float p_doRender_8_, float p_doRender_9_) {
/* 291 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 292 */     ItemStack itemstack = body.getHeldItem();
/* 293 */     ModelBiped modelArmorChestplate = this.modelArmorChestplate;
/* 294 */     ModelBiped modelArmor = this.modelArmor;
/* 295 */     ModelBiped modelBipedMain = this.modelBipedMain;
/*     */     
/* 297 */     boolean heldItemRight = ((itemstack != null)), b = heldItemRight;
/* 298 */     modelBipedMain.heldItemRight = b ? 1 : 0;
/* 299 */     modelArmor.heldItemRight = b ? 1 : 0;
/* 300 */     modelArmorChestplate.heldItemRight = heldItemRight ? 1 : 0;
/* 301 */     if (itemstack != null) {
/* 302 */       EnumAction enumaction = itemstack.getItemUseAction();
/* 303 */       if (enumaction == EnumAction.block) {
/* 304 */         ModelBiped modelArmorChestplate2 = this.modelArmorChestplate;
/* 305 */         ModelBiped modelArmor2 = this.modelArmor;
/* 306 */         ModelBiped modelBipedMain2 = this.modelBipedMain;
/* 307 */         int heldItemRight2 = 3;
/* 308 */         modelBipedMain2.heldItemRight = 3;
/* 309 */         modelArmor2.heldItemRight = 3;
/* 310 */         modelArmorChestplate2.heldItemRight = 3;
/*     */       }
/* 312 */       else if (enumaction == EnumAction.bow) {
/* 313 */         ModelBiped modelArmorChestplate3 = this.modelArmorChestplate;
/* 314 */         ModelBiped modelArmor3 = this.modelArmor;
/* 315 */         ModelBiped modelBipedMain3 = this.modelBipedMain;
/* 316 */         boolean aimedBow = true;
/* 317 */         modelBipedMain3.aimedBow = true;
/* 318 */         modelArmor3.aimedBow = true;
/* 319 */         modelArmorChestplate3.aimedBow = true;
/*     */       } 
/*     */     } 
/* 322 */     ModelBiped modelArmorChestplate4 = this.modelArmorChestplate;
/* 323 */     ModelBiped modelArmor4 = this.modelArmor;
/* 324 */     ModelBiped modelBipedMain4 = this.modelBipedMain;
/* 325 */     boolean sneaking = body.isSneaking();
/* 326 */     modelBipedMain4.isSneak = sneaking;
/* 327 */     modelArmor4.isSneak = sneaking;
/* 328 */     modelArmorChestplate4.isSneak = sneaking;
/* 329 */     double d3 = p_doRender_4_ - body.yOffset;
/*     */     
/* 331 */     doLivingRender((EntityLivingBase)body, p_doRender_2_, d3, p_doRender_6_, p_doRender_8_, p_doRender_9_);
/* 332 */     ModelBiped modelArmorChestplate5 = this.modelArmorChestplate;
/* 333 */     ModelBiped modelArmor5 = this.modelArmor;
/* 334 */     ModelBiped modelBipedMain5 = this.modelBipedMain;
/* 335 */     boolean aimedBow2 = false;
/* 336 */     modelBipedMain5.aimedBow = false;
/* 337 */     modelArmor5.aimedBow = false;
/* 338 */     modelArmorChestplate5.aimedBow = false;
/* 339 */     ModelBiped modelArmorChestplate6 = this.modelArmorChestplate;
/* 340 */     ModelBiped modelArmor6 = this.modelArmor;
/* 341 */     ModelBiped modelBipedMain6 = this.modelBipedMain;
/* 342 */     boolean isSneak = false;
/* 343 */     modelBipedMain6.isSneak = false;
/* 344 */     modelArmor6.isSneak = false;
/* 345 */     modelArmorChestplate6.isSneak = false;
/* 346 */     ModelBiped modelArmorChestplate7 = this.modelArmorChestplate;
/* 347 */     ModelBiped modelArmor7 = this.modelArmor;
/* 348 */     ModelBiped modelBipedMain7 = this.modelBipedMain;
/* 349 */     boolean heldItemRight3 = false;
/* 350 */     modelBipedMain7.heldItemRight = 0;
/* 351 */     modelArmor7.heldItemRight = 0;
/* 352 */     modelArmorChestplate7.heldItemRight = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int shouldRenderPass(EntityLivingBase p_shouldRenderPass_1_, int p_shouldRenderPass_2_, float p_shouldRenderPass_3_) {
/* 358 */     ItemStack itemstack = ((EntityLootableBody)p_shouldRenderPass_1_).armorItemInSlot(3 - p_shouldRenderPass_2_);
/* 359 */     if (itemstack != null) {
/* 360 */       Item item = itemstack.getItem();
/* 361 */       if (item instanceof ItemArmor) {
/* 362 */         ItemArmor itemarmor = (ItemArmor)item;
/* 363 */         bindTexture(RenderBiped.getArmorResource((Entity)p_shouldRenderPass_1_, itemstack, p_shouldRenderPass_2_, null));
/* 364 */         ModelBiped modelbiped = (p_shouldRenderPass_2_ == 2) ? this.modelArmor : this.modelArmorChestplate;
/* 365 */         modelbiped.bipedHead.showModel = (p_shouldRenderPass_2_ == 0);
/* 366 */         modelbiped.bipedHeadwear.showModel = (p_shouldRenderPass_2_ == 0);
/* 367 */         modelbiped.bipedBody.showModel = (p_shouldRenderPass_2_ == 1 || p_shouldRenderPass_2_ == 2);
/* 368 */         modelbiped.bipedRightArm.showModel = (p_shouldRenderPass_2_ == 1);
/* 369 */         modelbiped.bipedLeftArm.showModel = (p_shouldRenderPass_2_ == 1);
/* 370 */         modelbiped.bipedRightLeg.showModel = (p_shouldRenderPass_2_ == 2 || p_shouldRenderPass_2_ == 3);
/* 371 */         modelbiped.bipedLeftLeg.showModel = (p_shouldRenderPass_2_ == 2 || p_shouldRenderPass_2_ == 3);
/* 372 */         modelbiped = ForgeHooksClient.getArmorModel(p_shouldRenderPass_1_, itemstack, p_shouldRenderPass_2_, modelbiped);
/* 373 */         setRenderPassModel((ModelBase)modelbiped);
/* 374 */         modelbiped.onGround = this.mainModel.onGround;
/* 375 */         modelbiped.isRiding = this.mainModel.isRiding;
/* 376 */         modelbiped.isChild = this.mainModel.isChild;
/* 377 */         int j = itemarmor.getColor(itemstack);
/* 378 */         if (j != -1) {
/* 379 */           float f1 = (j >> 16 & 0xFF) / 255.0F;
/* 380 */           float f2 = (j >> 8 & 0xFF) / 255.0F;
/* 381 */           float f3 = (j & 0xFF) / 255.0F;
/* 382 */           GL11.glColor3f(f1, f2, f3);
/* 383 */           if (itemstack.isItemEnchanted()) {
/* 384 */             return 31;
/*     */           }
/* 386 */           return 16;
/*     */         } 
/*     */         
/* 389 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 390 */         if (itemstack.isItemEnchanted()) {
/* 391 */           return 15;
/*     */         }
/* 393 */         return 1;
/*     */       } 
/*     */     } 
/*     */     
/* 397 */     return -1;
/*     */   }
/*     */   private float interpolateRotation(float p_interpolateRotation_1_, float p_interpolateRotation_2_, float p_interpolateRotation_3_) {
/*     */     float f3;
/* 401 */     for (f3 = p_interpolateRotation_2_ - p_interpolateRotation_1_; f3 < -180.0F; f3 += 360.0F);
/* 402 */     while (f3 >= 180.0F) {
/* 403 */       f3 -= 360.0F;
/*     */     }
/* 405 */     return p_interpolateRotation_1_ + p_interpolateRotation_3_ * f3;
/*     */   }
/*     */ 
/*     */   
/*     */   private ResourceLocation getTexture(EntityLootableBody e) {
/* 410 */     return skeletonTexture;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityLiving e) {
/* 415 */     return getTexture((EntityLootableBody)e);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityLivingBase e) {
/* 420 */     return getTexture((EntityLootableBody)e);
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\render\RenderLootableBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */