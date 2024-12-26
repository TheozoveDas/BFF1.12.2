/*     */ package net.blockfront.mod.client.tmt;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*     */ import org.lwjgl.opengl.ARBBufferObject;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GLContext;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TmtTessellator extends Tessellator {
/*  19 */   private static int nativeBufferSize = 2097152;
/*  20 */   private static int trivertsInBuffer = nativeBufferSize / 48 * 6;
/*     */   public static boolean renderingWorldRenderer = false;
/*     */   public boolean defaultTexture = false;
/*  23 */   private int rawBufferSize = 0;
/*  24 */   public int textureID = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean convertQuadsToTriangles = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean tryVBO = false;
/*     */ 
/*     */ 
/*     */   
/*  36 */   private static ByteBuffer byteBuffer = GLAllocation.createDirectByteBuffer(nativeBufferSize * 4);
/*     */ 
/*     */   
/*  39 */   private static IntBuffer intBuffer = byteBuffer.asIntBuffer();
/*     */ 
/*     */   
/*  42 */   private static FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
/*     */ 
/*     */   
/*  45 */   private static ShortBuffer shortBuffer = byteBuffer.asShortBuffer();
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] rawBuffer;
/*     */ 
/*     */ 
/*     */   
/*  53 */   private int vertexCount = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private double textureU;
/*     */ 
/*     */ 
/*     */   
/*     */   private double textureV;
/*     */ 
/*     */   
/*     */   private double textureW;
/*     */ 
/*     */   
/*     */   private int brightness;
/*     */ 
/*     */   
/*     */   private int color;
/*     */ 
/*     */   
/*     */   private boolean hasColor = false;
/*     */ 
/*     */   
/*     */   private boolean hasTexture = false;
/*     */ 
/*     */   
/*     */   private boolean hasBrightness = false;
/*     */ 
/*     */   
/*     */   private boolean hasNormals = false;
/*     */ 
/*     */   
/*  85 */   private int rawBufferIndex = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   private int addedVertices = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isColorDisabled = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public int drawMode;
/*     */ 
/*     */ 
/*     */   
/*     */   public double xOffset;
/*     */ 
/*     */ 
/*     */   
/*     */   public double yOffset;
/*     */ 
/*     */ 
/*     */   
/*     */   public double zOffset;
/*     */ 
/*     */ 
/*     */   
/*     */   private int normal;
/*     */ 
/*     */   
/* 118 */   public static TmtTessellator instance = new TmtTessellator(2097152);
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDrawing = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean useVBO = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private static IntBuffer vertexBuffers;
/*     */ 
/*     */   
/* 133 */   private int vboIndex = 0;
/*     */ 
/*     */   
/* 136 */   private static int vboCount = 10;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int bufferSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 151 */     instance.defaultTexture = true;
/* 152 */     useVBO = (tryVBO && (GLContext.getCapabilities()).GL_ARB_vertex_buffer_object);
/*     */     
/* 154 */     if (useVBO) {
/*     */       
/* 156 */       vertexBuffers = GLAllocation.createDirectIntBuffer(vboCount);
/* 157 */       ARBBufferObject.glGenBuffersARB(vertexBuffers);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int draw() {
/* 167 */     if (!this.isDrawing)
/*     */     {
/* 169 */       throw new IllegalStateException("Not tesselating!");
/*     */     }
/*     */ 
/*     */     
/* 173 */     this.isDrawing = false;
/*     */     
/* 175 */     int offs = 0;
/* 176 */     while (offs < this.vertexCount) {
/*     */       
/* 178 */       int vtc = 0;
/* 179 */       if (this.drawMode == 7 && convertQuadsToTriangles) {
/*     */         
/* 181 */         vtc = Math.min(this.vertexCount - offs, trivertsInBuffer);
/*     */       }
/*     */       else {
/*     */         
/* 185 */         vtc = Math.min(this.vertexCount - offs, nativeBufferSize >> 5);
/*     */       } 
/* 187 */       intBuffer.clear();
/* 188 */       intBuffer.put(this.rawBuffer, offs * 10, vtc * 10);
/* 189 */       byteBuffer.position(0);
/* 190 */       byteBuffer.limit(vtc * 40);
/* 191 */       offs += vtc;
/*     */       
/* 193 */       if (useVBO) {
/*     */         
/* 195 */         this.vboIndex = (this.vboIndex + 1) % vboCount;
/* 196 */         ARBBufferObject.glBindBufferARB(34962, vertexBuffers.get(this.vboIndex));
/* 197 */         ARBBufferObject.glBufferDataARB(34962, byteBuffer, 35040);
/*     */       } 
/*     */       
/* 200 */       if (this.hasTexture) {
/*     */         
/* 202 */         if (useVBO) {
/*     */           
/* 204 */           GL11.glTexCoordPointer(4, 5126, 40, 12L);
/*     */         }
/*     */         else {
/*     */           
/* 208 */           floatBuffer.position(3);
/* 209 */           GL11.glTexCoordPointer(4, 40, floatBuffer);
/*     */         } 
/*     */         
/* 212 */         GL11.glEnableClientState(32888);
/*     */       } 
/*     */       
/* 215 */       if (this.hasBrightness) {
/*     */         
/* 217 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
/*     */         
/* 219 */         if (useVBO) {
/*     */           
/* 221 */           GL11.glTexCoordPointer(2, 5122, 40, 36L);
/*     */         }
/*     */         else {
/*     */           
/* 225 */           shortBuffer.position(18);
/* 226 */           GL11.glTexCoordPointer(2, 40, shortBuffer);
/*     */         } 
/*     */         
/* 229 */         GL11.glEnableClientState(32888);
/* 230 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */       } 
/*     */       
/* 233 */       if (this.hasColor) {
/*     */         
/* 235 */         if (useVBO) {
/*     */           
/* 237 */           GL11.glColorPointer(4, 5121, 40, 28L);
/*     */         }
/*     */         else {
/*     */           
/* 241 */           byteBuffer.position(28);
/* 242 */           GL11.glColorPointer(4, true, 40, byteBuffer);
/*     */         } 
/*     */         
/* 245 */         GL11.glEnableClientState(32886);
/*     */       } 
/*     */       
/* 248 */       if (this.hasNormals) {
/*     */         
/* 250 */         if (useVBO) {
/*     */           
/* 252 */           GL11.glNormalPointer(5121, 40, 32L);
/*     */         }
/*     */         else {
/*     */           
/* 256 */           byteBuffer.position(32);
/* 257 */           GL11.glNormalPointer(40, byteBuffer);
/*     */         } 
/*     */         
/* 260 */         GL11.glEnableClientState(32885);
/*     */       } 
/*     */       
/* 263 */       if (useVBO) {
/*     */         
/* 265 */         GL11.glVertexPointer(3, 5126, 40, 0L);
/*     */       }
/*     */       else {
/*     */         
/* 269 */         floatBuffer.position(0);
/* 270 */         GL11.glVertexPointer(3, 40, floatBuffer);
/*     */       } 
/*     */       
/* 273 */       GL11.glEnableClientState(32884);
/*     */       
/* 275 */       if (this.drawMode == 7 && convertQuadsToTriangles) {
/*     */         
/* 277 */         GL11.glDrawArrays(4, 0, vtc);
/*     */       }
/*     */       else {
/*     */         
/* 281 */         GL11.glDrawArrays(this.drawMode, 0, vtc);
/*     */       } 
/*     */       
/* 284 */       GL11.glDisableClientState(32884);
/*     */       
/* 286 */       if (this.hasTexture)
/*     */       {
/* 288 */         GL11.glDisableClientState(32888);
/*     */       }
/*     */       
/* 291 */       if (this.hasBrightness) {
/*     */         
/* 293 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 294 */         GL11.glDisableClientState(32888);
/* 295 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */       } 
/*     */       
/* 298 */       if (this.hasColor)
/*     */       {
/* 300 */         GL11.glDisableClientState(32886);
/*     */       }
/*     */       
/* 303 */       if (this.hasNormals)
/*     */       {
/* 305 */         GL11.glDisableClientState(32885);
/*     */       }
/*     */     } 
/*     */     
/* 309 */     if (this.rawBufferSize > 131072 && this.rawBufferIndex < this.rawBufferSize << 3) {
/*     */       
/* 311 */       this.rawBufferSize = 0;
/* 312 */       this.rawBuffer = null;
/*     */     } 
/*     */     
/* 315 */     int var1 = this.rawBufferIndex * 4;
/* 316 */     reset();
/* 317 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void reset() {
/* 326 */     this.vertexCount = 0;
/* 327 */     byteBuffer.clear();
/* 328 */     this.rawBufferIndex = 0;
/* 329 */     this.addedVertices = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDrawingQuads() {
/* 338 */     startDrawing(7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDrawing(int par1) {
/* 347 */     if (this.isDrawing)
/*     */     {
/* 349 */       throw new IllegalStateException("Already tesselating!");
/*     */     }
/*     */ 
/*     */     
/* 353 */     this.isDrawing = true;
/* 354 */     reset();
/* 355 */     this.drawMode = par1;
/* 356 */     this.hasNormals = false;
/* 357 */     this.hasColor = false;
/* 358 */     this.hasTexture = false;
/* 359 */     this.hasBrightness = false;
/* 360 */     this.isColorDisabled = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextureUV(double par1, double par3) {
/* 370 */     this.hasTexture = true;
/* 371 */     this.textureU = par1;
/* 372 */     this.textureV = par3;
/* 373 */     this.textureW = 1.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextureUVW(double par1, double par3, double par4) {
/* 381 */     this.hasTexture = true;
/* 382 */     this.textureU = par1;
/* 383 */     this.textureV = par3;
/* 384 */     this.textureW = par4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBrightness(int par1) {
/* 390 */     this.hasBrightness = true;
/* 391 */     this.brightness = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorOpaque_F(float par1, float par2, float par3) {
/* 400 */     setColorOpaque((int)(par1 * 255.0F), (int)(par2 * 255.0F), (int)(par3 * 255.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorRGBA_F(float par1, float par2, float par3, float par4) {
/* 409 */     setColorRGBA((int)(par1 * 255.0F), (int)(par2 * 255.0F), (int)(par3 * 255.0F), (int)(par4 * 255.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorOpaque(int par1, int par2, int par3) {
/* 418 */     setColorRGBA(par1, par2, par3, 255);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorRGBA(int par1, int par2, int par3, int par4) {
/* 427 */     if (!this.isColorDisabled) {
/*     */       
/* 429 */       if (par1 > 255)
/*     */       {
/* 431 */         par1 = 255;
/*     */       }
/*     */       
/* 434 */       if (par2 > 255)
/*     */       {
/* 436 */         par2 = 255;
/*     */       }
/*     */       
/* 439 */       if (par3 > 255)
/*     */       {
/* 441 */         par3 = 255;
/*     */       }
/*     */       
/* 444 */       if (par4 > 255)
/*     */       {
/* 446 */         par4 = 255;
/*     */       }
/*     */       
/* 449 */       if (par1 < 0)
/*     */       {
/* 451 */         par1 = 0;
/*     */       }
/*     */       
/* 454 */       if (par2 < 0)
/*     */       {
/* 456 */         par2 = 0;
/*     */       }
/*     */       
/* 459 */       if (par3 < 0)
/*     */       {
/* 461 */         par3 = 0;
/*     */       }
/*     */       
/* 464 */       if (par4 < 0)
/*     */       {
/* 466 */         par4 = 0;
/*     */       }
/*     */       
/* 469 */       this.hasColor = true;
/*     */       
/* 471 */       if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
/*     */         
/* 473 */         this.color = par4 << 24 | par3 << 16 | par2 << 8 | par1;
/*     */       }
/*     */       else {
/*     */         
/* 477 */         this.color = par1 << 24 | par2 << 16 | par3 << 8 | par4;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVertexWithUV(double par1, double par3, double par5, double par7, double par9) {
/* 488 */     setTextureUV(par7, par9);
/* 489 */     addVertex(par1, par3, par5);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addVertexWithUVW(double par1, double par3, double par5, double par7, double par9, double par10) {
/* 494 */     setTextureUVW(par7, par9, par10);
/* 495 */     addVertex(par1, par3, par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVertex(double par1, double par3, double par5) {
/* 505 */     if (this.rawBufferIndex >= this.rawBufferSize - 40)
/*     */     {
/* 507 */       if (this.rawBufferSize == 0) {
/*     */         
/* 509 */         this.rawBufferSize = 65536;
/* 510 */         this.rawBuffer = new int[this.rawBufferSize];
/*     */       }
/*     */       else {
/*     */         
/* 514 */         this.rawBufferSize *= 2;
/* 515 */         this.rawBuffer = Arrays.copyOf(this.rawBuffer, this.rawBufferSize);
/*     */       } 
/*     */     }
/* 518 */     this.addedVertices++;
/*     */     
/* 520 */     if (this.drawMode == 7 && convertQuadsToTriangles && this.addedVertices % 4 == 0)
/*     */     {
/* 522 */       for (int var7 = 0; var7 < 2; var7++) {
/*     */         
/* 524 */         int var8 = 10 * (3 - var7);
/*     */         
/* 526 */         if (this.hasTexture) {
/*     */           
/* 528 */           this.rawBuffer[this.rawBufferIndex + 3] = this.rawBuffer[this.rawBufferIndex - var8 + 3];
/* 529 */           this.rawBuffer[this.rawBufferIndex + 4] = this.rawBuffer[this.rawBufferIndex - var8 + 4];
/* 530 */           this.rawBuffer[this.rawBufferIndex + 5] = this.rawBuffer[this.rawBufferIndex - var8 + 5];
/* 531 */           this.rawBuffer[this.rawBufferIndex + 6] = this.rawBuffer[this.rawBufferIndex - var8 + 6];
/*     */         } 
/*     */         
/* 534 */         if (this.hasBrightness)
/*     */         {
/* 536 */           this.rawBuffer[this.rawBufferIndex + 9] = this.rawBuffer[this.rawBufferIndex - var8 + 9];
/*     */         }
/*     */         
/* 539 */         if (this.hasColor)
/*     */         {
/* 541 */           this.rawBuffer[this.rawBufferIndex + 7] = this.rawBuffer[this.rawBufferIndex - var8 + 7];
/*     */         }
/*     */         
/* 544 */         this.rawBuffer[this.rawBufferIndex] = this.rawBuffer[this.rawBufferIndex - var8];
/* 545 */         this.rawBuffer[this.rawBufferIndex + 1] = this.rawBuffer[this.rawBufferIndex - var8 + 1];
/* 546 */         this.rawBuffer[this.rawBufferIndex + 2] = this.rawBuffer[this.rawBufferIndex - var8 + 2];
/* 547 */         this.vertexCount++;
/* 548 */         this.rawBufferIndex += 10;
/*     */       } 
/*     */     }
/*     */     
/* 552 */     if (this.hasTexture) {
/*     */       
/* 554 */       this.rawBuffer[this.rawBufferIndex + 3] = Float.floatToRawIntBits((float)this.textureU);
/* 555 */       this.rawBuffer[this.rawBufferIndex + 4] = Float.floatToRawIntBits((float)this.textureV);
/* 556 */       this.rawBuffer[this.rawBufferIndex + 5] = Float.floatToRawIntBits(0.0F);
/* 557 */       this.rawBuffer[this.rawBufferIndex + 6] = Float.floatToRawIntBits((float)this.textureW);
/*     */     } 
/*     */     
/* 560 */     if (this.hasBrightness)
/*     */     {
/* 562 */       this.rawBuffer[this.rawBufferIndex + 9] = this.brightness;
/*     */     }
/*     */     
/* 565 */     if (this.hasColor)
/*     */     {
/* 567 */       this.rawBuffer[this.rawBufferIndex + 7] = this.color;
/*     */     }
/*     */     
/* 570 */     if (this.hasNormals)
/*     */     {
/* 572 */       this.rawBuffer[this.rawBufferIndex + 8] = this.normal;
/*     */     }
/*     */     
/* 575 */     this.rawBuffer[this.rawBufferIndex] = Float.floatToRawIntBits((float)(par1 + this.xOffset));
/* 576 */     this.rawBuffer[this.rawBufferIndex + 1] = Float.floatToRawIntBits((float)(par3 + this.yOffset));
/* 577 */     this.rawBuffer[this.rawBufferIndex + 2] = Float.floatToRawIntBits((float)(par5 + this.zOffset));
/* 578 */     this.rawBufferIndex += 10;
/* 579 */     this.vertexCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorOpaque_I(int par1) {
/* 588 */     int j = par1 >> 16 & 0xFF;
/* 589 */     int k = par1 >> 8 & 0xFF;
/* 590 */     int l = par1 & 0xFF;
/* 591 */     setColorOpaque(j, k, l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorRGBA_I(int par1, int par2) {
/* 600 */     int k = par1 >> 16 & 0xFF;
/* 601 */     int l = par1 >> 8 & 0xFF;
/* 602 */     int i1 = par1 & 0xFF;
/* 603 */     setColorRGBA(k, l, i1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disableColor() {
/* 612 */     this.isColorDisabled = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNormal(float par1, float par2, float par3) {
/* 621 */     this.hasNormals = true;
/* 622 */     byte b0 = (byte)(int)(par1 * 127.0F);
/* 623 */     byte b1 = (byte)(int)(par2 * 127.0F);
/* 624 */     byte b2 = (byte)(int)(par3 * 127.0F);
/* 625 */     this.normal = b0 & 0xFF | (b1 & 0xFF) << 8 | (b2 & 0xFF) << 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTranslation(double par1, double par3, double par5) {
/* 634 */     this.xOffset = par1;
/* 635 */     this.yOffset = par3;
/* 636 */     this.zOffset = par5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTranslation(float par1, float par2, float par3) {
/* 645 */     this.xOffset += par1;
/* 646 */     this.yOffset += par2;
/* 647 */     this.zOffset += par3;
/*     */   }
/*     */   
/*     */   private TmtTessellator(int par1) {}
/*     */   
/*     */   public TmtTessellator() {}
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\TmtTessellator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */