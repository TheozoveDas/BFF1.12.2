/*      */ package net.blockfront.mod.client.tmt;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import net.minecraft.client.model.ModelBase;
/*      */ import net.minecraft.client.model.ModelRenderer;
/*      */ import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ public class ModelRendererTurbo extends ModelRenderer {
/*      */   private PositionTextureVertex[] vertices;
/*      */   private TexturedPolygon[] faces;
/*      */   private int textureOffsetX;
/*      */   private int textureOffsetY;
/*      */   private boolean compiled;
/*      */   private int displayList;
/*      */   private int[] displayListArray;
/*      */   private Map<String, TransformGroup> transformGroup;
/*      */   private Map<String, TextureGroup> textureGroup;
/*      */   private TransformGroup currentGroup;
/*      */   private TextureGroup currentTextureGroup;
/*      */   public boolean mirror;
/*      */   public boolean flip;
/*      */   public boolean showModel;
/*      */   
/*      */   public ModelRendererTurbo(ModelBase modelbase, String s) {
/*   30 */     super(modelbase, s);
/*   31 */     this.flip = false;
/*   32 */     this.compiled = false;
/*   33 */     this.displayList = 0;
/*   34 */     this.mirror = false;
/*   35 */     this.showModel = true;
/*   36 */     this.field_1402_i = false;
/*   37 */     this.vertices = new PositionTextureVertex[0];
/*   38 */     this.faces = new TexturedPolygon[0];
/*   39 */     this.forcedRecompile = false;
/*   40 */     this.transformGroup = new HashMap<>();
/*   41 */     this.transformGroup.put("0", new TransformGroupBone(new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D));
/*   42 */     this.textureGroup = new HashMap<>();
/*   43 */     this.textureGroup.put("0", new TextureGroup());
/*   44 */     this.currentTextureGroup = this.textureGroup.get("0");
/*   45 */     this.boxName = s;
/*      */     
/*   47 */     this.defaultTexture = "";
/*      */     
/*   49 */     this.useLegacyCompiler = false;
/*      */   }
/*      */   public boolean field_1402_i; public boolean forcedRecompile; public boolean useLegacyCompiler; public List cubeList; public List childModels; public final String boxName; private String defaultTexture; public static final int MR_FRONT = 0; public static final int MR_BACK = 1; public static final int MR_LEFT = 2; public static final int MR_RIGHT = 3; public static final int MR_TOP = 4; public static final int MR_BOTTOM = 5; private static final float pi = 3.1415927F;
/*      */   
/*      */   public ModelRendererTurbo(ModelBase modelbase) {
/*   54 */     this(modelbase, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ModelRendererTurbo(ModelBase modelbase, int textureX, int textureY) {
/*   66 */     this(modelbase, textureX, textureY, 64, 32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ModelRendererTurbo(ModelBase modelbase, int textureX, int textureY, int textureU, int textureV) {
/*   81 */     this(modelbase);
/*   82 */     this.textureOffsetX = textureX;
/*   83 */     this.textureOffsetY = textureY;
/*   84 */     this.textureWidth = textureU;
/*   85 */     this.textureHeight = textureV;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPolygon(PositionTextureVertex[] verts) {
/*   94 */     copyTo(verts, new TexturedPolygon[] { new TexturedPolygon(verts) });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPolygon(PositionTextureVertex[] verts, int[][] uv) {
/*      */     try {
/*  106 */       for (int i = 0; i < verts.length; i++)
/*      */       {
/*  108 */         verts[i] = verts[i].setTexturePosition(uv[i][0] / this.textureWidth, uv[i][1] / this.textureHeight);
/*      */       }
/*      */     }
/*      */     finally {
/*      */       
/*  113 */       addPolygon(verts);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPolygon(PositionTextureVertex[] verts, int u1, int v1, int u2, int v2) {
/*  127 */     copyTo(verts, new TexturedPolygon[] { addPolygonReturn(verts, u1, v1, u2, v2) });
/*      */   }
/*      */ 
/*      */   
/*      */   private TexturedPolygon addPolygonReturn(PositionTextureVertex[] verts, int u1, int v1, int u2, int v2, float q1, float q2, float q3, float q4) {
/*  132 */     if (verts.length < 3)
/*  133 */       return null; 
/*  134 */     float uOffs = 1.0F / this.textureWidth * 10.0F;
/*  135 */     float vOffs = 1.0F / this.textureHeight * 10.0F;
/*  136 */     if (verts.length < 4) {
/*      */       
/*  138 */       float xMin = -1.0F;
/*  139 */       float yMin = -1.0F;
/*  140 */       float xMax = 0.0F;
/*  141 */       float yMax = 0.0F;
/*      */       
/*  143 */       for (PositionTextureVertex vert : verts) {
/*  144 */         float xPos = vert.texturePositionX;
/*  145 */         float yPos = vert.texturePositionY;
/*  146 */         xMax = Math.max(xMax, xPos);
/*  147 */         xMin = (xMin < -1.0F) ? xPos : Math.min(xMin, xPos);
/*  148 */         yMax = Math.max(yMax, yPos);
/*  149 */         yMin = (yMin < -1.0F) ? yPos : Math.min(yMin, yPos);
/*      */       } 
/*  151 */       float uMin = u1 / this.textureWidth + uOffs;
/*  152 */       float vMin = v1 / this.textureHeight + vOffs;
/*  153 */       float uSize = (u2 - u1) / this.textureWidth - uOffs * 2.0F;
/*  154 */       float vSize = (v2 - v1) / this.textureHeight - vOffs * 2.0F;
/*      */       
/*  156 */       float xSize = xMax - xMin;
/*  157 */       float ySize = yMax - yMin;
/*  158 */       for (int i = 0; i < verts.length; i++)
/*      */       {
/*  160 */         float xPos = (verts[i]).texturePositionX;
/*  161 */         float yPos = (verts[i]).texturePositionY;
/*  162 */         xPos = (xPos - xMin) / xSize;
/*  163 */         yPos = (yPos - yMin) / ySize;
/*  164 */         verts[i] = verts[i].setTexturePosition(uMin + xPos * uSize, vMin + yPos * vSize);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  169 */       verts[0] = verts[0].setTexturePosition((u2 / this.textureWidth - uOffs) * q1, (v1 / this.textureHeight + vOffs) * q1, q1);
/*  170 */       verts[1] = verts[1].setTexturePosition((u1 / this.textureWidth + uOffs) * q2, (v1 / this.textureHeight + vOffs) * q2, q2);
/*  171 */       verts[2] = verts[2].setTexturePosition((u1 / this.textureWidth + uOffs) * q3, (v2 / this.textureHeight - vOffs) * q3, q3);
/*  172 */       verts[3] = verts[3].setTexturePosition((u2 / this.textureWidth - uOffs) * q4, (v2 / this.textureHeight - vOffs) * q4, q4);
/*      */     } 
/*  174 */     return new TexturedPolygon(verts);
/*      */   }
/*      */ 
/*      */   
/*      */   private TexturedPolygon addPolygonReturn(PositionTextureVertex[] verts, int u1, int v1, int u2, int v2) {
/*  179 */     if (verts.length < 3)
/*  180 */       return null; 
/*  181 */     float uOffs = 1.0F / this.textureWidth * 10.0F;
/*  182 */     float vOffs = 1.0F / this.textureHeight * 10.0F;
/*  183 */     if (verts.length < 4) {
/*      */       
/*  185 */       float xMin = -1.0F;
/*  186 */       float yMin = -1.0F;
/*  187 */       float xMax = 0.0F;
/*  188 */       float yMax = 0.0F;
/*      */       
/*  190 */       for (PositionTextureVertex vert : verts) {
/*  191 */         float xPos = vert.texturePositionX;
/*  192 */         float yPos = vert.texturePositionY;
/*  193 */         xMax = Math.max(xMax, xPos);
/*  194 */         xMin = (xMin < -1.0F) ? xPos : Math.min(xMin, xPos);
/*  195 */         yMax = Math.max(yMax, yPos);
/*  196 */         yMin = (yMin < -1.0F) ? yPos : Math.min(yMin, yPos);
/*      */       } 
/*  198 */       float uMin = u1 / this.textureWidth + uOffs;
/*  199 */       float vMin = v1 / this.textureHeight + vOffs;
/*  200 */       float uSize = (u2 - u1) / this.textureWidth - uOffs * 2.0F;
/*  201 */       float vSize = (v2 - v1) / this.textureHeight - vOffs * 2.0F;
/*      */       
/*  203 */       float xSize = xMax - xMin;
/*  204 */       float ySize = yMax - yMin;
/*  205 */       for (int i = 0; i < verts.length; i++)
/*      */       {
/*  207 */         float xPos = (verts[i]).texturePositionX;
/*  208 */         float yPos = (verts[i]).texturePositionY;
/*  209 */         xPos = (xPos - xMin) / xSize;
/*  210 */         yPos = (yPos - yMin) / ySize;
/*  211 */         verts[i] = verts[i].setTexturePosition(uMin + xPos * uSize, vMin + yPos * vSize);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  216 */       verts[0] = verts[0].setTexturePosition(u2 / this.textureWidth - uOffs, v1 / this.textureHeight + vOffs);
/*  217 */       verts[1] = verts[1].setTexturePosition(u1 / this.textureWidth + uOffs, v1 / this.textureHeight + vOffs);
/*  218 */       verts[2] = verts[2].setTexturePosition(u1 / this.textureWidth + uOffs, v2 / this.textureHeight - vOffs);
/*  219 */       verts[3] = verts[3].setTexturePosition(u2 / this.textureWidth - uOffs, v2 / this.textureHeight - vOffs);
/*      */     } 
/*  221 */     return new TexturedPolygon(verts);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addRectShape(float[] v, float[] v1, float[] v2, float[] v3, float[] v4, float[] v5, float[] v6, float[] v7, int w, int h, int d) {
/*  241 */     float[] var1 = { 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F };
/*  242 */     addRectShape(v, v1, v2, v3, v4, v5, v6, v7, w, h, d, var1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addRectShape(float[] v, float[] v1, float[] v2, float[] v3, float[] v4, float[] v5, float[] v6, float[] v7, int w, int h, int d, float[] qParam) {
/*  263 */     PositionTextureVertex[] verts = new PositionTextureVertex[8];
/*  264 */     TexturedPolygon[] poly = new TexturedPolygon[6];
/*  265 */     PositionTextureVertex positionTexturevertex = new PositionTextureVertex(v[0], v[1], v[2], 0.0F, 0.0F);
/*  266 */     PositionTextureVertex positionTexturevertex1 = new PositionTextureVertex(v1[0], v1[1], v1[2], 0.0F, 8.0F);
/*  267 */     PositionTextureVertex positionTexturevertex2 = new PositionTextureVertex(v2[0], v2[1], v2[2], 8.0F, 8.0F);
/*  268 */     PositionTextureVertex positionTexturevertex3 = new PositionTextureVertex(v3[0], v3[1], v3[2], 8.0F, 0.0F);
/*  269 */     PositionTextureVertex positionTexturevertex4 = new PositionTextureVertex(v4[0], v4[1], v4[2], 0.0F, 0.0F);
/*  270 */     PositionTextureVertex positionTexturevertex5 = new PositionTextureVertex(v5[0], v5[1], v5[2], 0.0F, 8.0F);
/*  271 */     PositionTextureVertex positionTexturevertex6 = new PositionTextureVertex(v6[0], v6[1], v6[2], 8.0F, 8.0F);
/*  272 */     PositionTextureVertex positionTexturevertex7 = new PositionTextureVertex(v7[0], v7[1], v7[2], 8.0F, 0.0F);
/*  273 */     verts[0] = positionTexturevertex;
/*  274 */     verts[1] = positionTexturevertex1;
/*  275 */     verts[2] = positionTexturevertex2;
/*  276 */     verts[3] = positionTexturevertex3;
/*  277 */     verts[4] = positionTexturevertex4;
/*  278 */     verts[5] = positionTexturevertex5;
/*  279 */     verts[6] = positionTexturevertex6;
/*  280 */     verts[7] = positionTexturevertex7;
/*  281 */     poly[0] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex5, positionTexturevertex1, positionTexturevertex2, positionTexturevertex6 }, this.textureOffsetX + d + w, this.textureOffsetY + d, this.textureOffsetX + d + w + d, this.textureOffsetY + d + h, 1.0F, qParam[7], qParam[10] * qParam[7], qParam[10]);
/*      */ 
/*      */ 
/*      */     
/*  285 */     poly[1] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex, positionTexturevertex4, positionTexturevertex7, positionTexturevertex3 }, this.textureOffsetX, this.textureOffsetY + d, this.textureOffsetX + d, this.textureOffsetY + d + h, qParam[9] * qParam[6], qParam[9], 1.0F, qParam[6]);
/*      */ 
/*      */ 
/*      */     
/*  289 */     poly[2] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex5, positionTexturevertex4, positionTexturevertex, positionTexturevertex1 }, this.textureOffsetX + d, this.textureOffsetY, this.textureOffsetX + d + w, this.textureOffsetY + d, 1.0F, qParam[8], qParam[1] * qParam[8], qParam[1]);
/*      */ 
/*      */ 
/*      */     
/*  293 */     poly[3] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex2, positionTexturevertex3, positionTexturevertex7, positionTexturevertex6 }, this.textureOffsetX + d + w, this.textureOffsetY, this.textureOffsetX + d + w + w, this.textureOffsetY + d, qParam[3], qParam[3] * qParam[11], qParam[11], 1.0F);
/*      */ 
/*      */ 
/*      */     
/*  297 */     poly[4] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex1, positionTexturevertex, positionTexturevertex3, positionTexturevertex2 }, this.textureOffsetX + d, this.textureOffsetY + d, this.textureOffsetX + d + w, this.textureOffsetY + d + h, qParam[0], qParam[0] * qParam[4], qParam[4], 1.0F);
/*      */ 
/*      */ 
/*      */     
/*  301 */     poly[5] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex4, positionTexturevertex5, positionTexturevertex6, positionTexturevertex7 }, this.textureOffsetX + d + w + d, this.textureOffsetY + d, this.textureOffsetX + d + w + d + w, this.textureOffsetY + d + h, qParam[2] * qParam[5], qParam[2], 1.0F, qParam[5]);
/*      */ 
/*      */ 
/*      */     
/*  305 */     if ((this.mirror ^ this.flip) != 0)
/*      */     {
/*  307 */       for (TexturedPolygon aPoly : poly) {
/*  308 */         aPoly.flipFace();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*  313 */     copyTo(verts, poly);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ModelRendererTurbo addBox(float x, float y, float z, int w, int h, int d) {
/*  328 */     addBox(x, y, z, w, h, d, 0.0F);
/*  329 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addBox(float x, float y, float z, int w, int h, int d, float expansion) {
/*  345 */     addBox(x, y, z, w, h, d, expansion, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addBox(float x, float y, float z, int w, int h, int d, float expansion, float scale) {
/*  361 */     float scaleX = w * scale;
/*  362 */     float scaleY = h * scale;
/*  363 */     float scaleZ = d * scale;
/*      */     
/*  365 */     float x1 = x + scaleX;
/*  366 */     float y1 = y + scaleY;
/*  367 */     float z1 = z + scaleZ;
/*      */     
/*  369 */     float expX = expansion + scaleX - w;
/*  370 */     float expY = expansion + scaleY - h;
/*  371 */     float expZ = expansion + scaleZ - d;
/*      */     
/*  373 */     x -= expX;
/*  374 */     y -= expY;
/*  375 */     z -= expZ;
/*  376 */     x1 += expansion;
/*  377 */     y1 += expansion;
/*  378 */     z1 += expansion;
/*  379 */     if (this.mirror) {
/*      */       
/*  381 */       float xTemp = x1;
/*  382 */       x1 = x;
/*  383 */       x = xTemp;
/*      */     } 
/*      */     
/*  386 */     float[] v = { x, y, z };
/*  387 */     float[] v1 = { x1, y, z };
/*  388 */     float[] v2 = { x1, y1, z };
/*  389 */     float[] v3 = { x, y1, z };
/*  390 */     float[] v4 = { x, y, z1 };
/*  391 */     float[] v5 = { x1, y, z1 };
/*  392 */     float[] v6 = { x1, y1, z1 };
/*  393 */     float[] v7 = { x, y1, z1 };
/*  394 */     addRectShape(v, v1, v2, v3, v4, v5, v6, v7, w, h, d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addTrapezoid(float x, float y, float z, int w, int h, int d, float scale, float bottomScale, int dir) {
/*  414 */     float f4 = x + w;
/*  415 */     float f5 = y + h;
/*  416 */     float f6 = z + d;
/*  417 */     x -= scale;
/*  418 */     y -= scale;
/*  419 */     z -= scale;
/*  420 */     f4 += scale;
/*  421 */     f5 += scale;
/*  422 */     f6 += scale;
/*      */     
/*  424 */     int m = this.mirror ? -1 : 1;
/*  425 */     if (this.mirror) {
/*      */       
/*  427 */       float f7 = f4;
/*  428 */       f4 = x;
/*  429 */       x = f7;
/*      */     } 
/*      */     
/*  432 */     float[] v = { x, y, z };
/*  433 */     float[] v1 = { f4, y, z };
/*  434 */     float[] v2 = { f4, f5, z };
/*  435 */     float[] v3 = { x, f5, z };
/*  436 */     float[] v4 = { x, y, f6 };
/*  437 */     float[] v5 = { f4, y, f6 };
/*  438 */     float[] v6 = { f4, f5, f6 };
/*  439 */     float[] v7 = { x, f5, f6 };
/*      */     
/*  441 */     switch (dir) {
/*      */       
/*      */       case 3:
/*  444 */         v[1] = v[1] - bottomScale;
/*  445 */         v[2] = v[2] - bottomScale;
/*  446 */         v3[1] = v3[1] + bottomScale;
/*  447 */         v3[2] = v3[2] - bottomScale;
/*  448 */         v4[1] = v4[1] - bottomScale;
/*  449 */         v4[2] = v4[2] + bottomScale;
/*  450 */         v7[1] = v7[1] + bottomScale;
/*  451 */         v7[2] = v7[2] + bottomScale;
/*      */         break;
/*      */       case 2:
/*  454 */         v1[1] = v1[1] - bottomScale;
/*  455 */         v1[2] = v1[2] - bottomScale;
/*  456 */         v2[1] = v2[1] + bottomScale;
/*  457 */         v2[2] = v2[2] - bottomScale;
/*  458 */         v5[1] = v5[1] - bottomScale;
/*  459 */         v5[2] = v5[2] + bottomScale;
/*  460 */         v6[1] = v6[1] + bottomScale;
/*  461 */         v6[2] = v6[2] + bottomScale;
/*      */         break;
/*      */       case 0:
/*  464 */         v[0] = v[0] - m * bottomScale;
/*  465 */         v[1] = v[1] - bottomScale;
/*  466 */         v1[0] = v1[0] + m * bottomScale;
/*  467 */         v1[1] = v1[1] - bottomScale;
/*  468 */         v2[0] = v2[0] + m * bottomScale;
/*  469 */         v2[1] = v2[1] + bottomScale;
/*  470 */         v3[0] = v3[0] - m * bottomScale;
/*  471 */         v3[1] = v3[1] + bottomScale;
/*      */         break;
/*      */       case 1:
/*  474 */         v4[0] = v4[0] - m * bottomScale;
/*  475 */         v4[1] = v4[1] - bottomScale;
/*  476 */         v5[0] = v5[0] + m * bottomScale;
/*  477 */         v5[1] = v5[1] - bottomScale;
/*  478 */         v6[0] = v6[0] + m * bottomScale;
/*  479 */         v6[1] = v6[1] + bottomScale;
/*  480 */         v7[0] = v7[0] - m * bottomScale;
/*  481 */         v7[1] = v7[1] + bottomScale;
/*      */         break;
/*      */       case 4:
/*  484 */         v[0] = v[0] - m * bottomScale;
/*  485 */         v[2] = v[2] - bottomScale;
/*  486 */         v1[0] = v1[0] + m * bottomScale;
/*  487 */         v1[2] = v1[2] - bottomScale;
/*  488 */         v4[0] = v4[0] - m * bottomScale;
/*  489 */         v4[2] = v4[2] + bottomScale;
/*  490 */         v5[0] = v5[0] + m * bottomScale;
/*  491 */         v5[2] = v5[2] + bottomScale;
/*      */         break;
/*      */       case 5:
/*  494 */         v2[0] = v2[0] + m * bottomScale;
/*  495 */         v2[2] = v2[2] - bottomScale;
/*  496 */         v3[0] = v3[0] - m * bottomScale;
/*  497 */         v3[2] = v3[2] - bottomScale;
/*  498 */         v6[0] = v6[0] + m * bottomScale;
/*  499 */         v6[2] = v6[2] + bottomScale;
/*  500 */         v7[0] = v7[0] - m * bottomScale;
/*  501 */         v7[2] = v7[2] + bottomScale;
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  519 */     float[] qValues = { Math.abs((v[0] - v1[0]) / (v3[0] - v2[0])), Math.abs((v[0] - v1[0]) / (v4[0] - v5[0])), Math.abs((v4[0] - v5[0]) / (v7[0] - v6[0])), Math.abs((v3[0] - v2[0]) / (v7[0] - v6[0])), Math.abs((v[1] - v3[1]) / (v1[1] - v2[1])), Math.abs((v4[1] - v7[1]) / (v5[1] - v6[1])), Math.abs((v[1] - v3[1]) / (v4[1] - v7[1])), Math.abs((v1[1] - v2[1]) / (v5[1] - v6[1])), Math.abs((v[2] - v4[2]) / (v1[2] - v5[2])), Math.abs((v[2] - v4[2]) / (v3[2] - v7[2])), Math.abs((v1[2] - v5[2]) / (v2[2] - v6[2])), Math.abs((v3[2] - v7[2]) / (v2[2] - v6[2])) };
/*      */ 
/*      */     
/*  522 */     addRectShape(v, v1, v2, v3, v4, v5, v6, v7, w, h, d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFlexBox(float x, float y, float z, int w, int h, int d, float scale, float bScale1, float bScale2, float bScale3, float bScale4, int dir) {
/*  545 */     float f4 = x + w;
/*  546 */     float f5 = y + h;
/*  547 */     float f6 = z + d;
/*  548 */     x -= scale;
/*  549 */     y -= scale;
/*  550 */     z -= scale;
/*  551 */     f4 += scale;
/*  552 */     f5 += scale;
/*  553 */     f6 += scale;
/*      */     
/*  555 */     int m = this.mirror ? -1 : 1;
/*  556 */     if (this.mirror) {
/*      */       
/*  558 */       float f7 = f4;
/*  559 */       f4 = x;
/*  560 */       x = f7;
/*      */     } 
/*      */     
/*  563 */     float[] v = { x, y, z };
/*  564 */     float[] v1 = { f4, y, z };
/*  565 */     float[] v2 = { f4, f5, z };
/*  566 */     float[] v3 = { x, f5, z };
/*  567 */     float[] v4 = { x, y, f6 };
/*  568 */     float[] v5 = { f4, y, f6 };
/*  569 */     float[] v6 = { f4, f5, f6 };
/*  570 */     float[] v7 = { x, f5, f6 };
/*      */     
/*  572 */     switch (dir) {
/*      */       
/*      */       case 3:
/*  575 */         v[1] = v[1] - bScale1;
/*  576 */         v[2] = v[2] - bScale3;
/*  577 */         v3[1] = v3[1] + bScale2;
/*  578 */         v3[2] = v3[2] - bScale3;
/*  579 */         v4[1] = v4[1] - bScale1;
/*  580 */         v4[2] = v4[2] + bScale4;
/*  581 */         v7[1] = v7[1] + bScale2;
/*  582 */         v7[2] = v7[2] + bScale4;
/*      */         break;
/*      */       case 2:
/*  585 */         v1[1] = v1[1] - bScale1;
/*  586 */         v1[2] = v1[2] - bScale3;
/*  587 */         v2[1] = v2[1] + bScale2;
/*  588 */         v2[2] = v2[2] - bScale3;
/*  589 */         v5[1] = v5[1] - bScale1;
/*  590 */         v5[2] = v5[2] + bScale4;
/*  591 */         v6[1] = v6[1] + bScale2;
/*  592 */         v6[2] = v6[2] + bScale4;
/*      */         break;
/*      */       case 0:
/*  595 */         v[0] = v[0] - m * bScale4;
/*  596 */         v[1] = v[1] - bScale1;
/*  597 */         v1[0] = v1[0] + m * bScale3;
/*  598 */         v1[1] = v1[1] - bScale1;
/*  599 */         v2[0] = v2[0] + m * bScale3;
/*  600 */         v2[1] = v2[1] + bScale2;
/*  601 */         v3[0] = v3[0] - m * bScale4;
/*  602 */         v3[1] = v3[1] + bScale2;
/*      */         break;
/*      */       case 1:
/*  605 */         v4[0] = v4[0] - m * bScale4;
/*  606 */         v4[1] = v4[1] - bScale1;
/*  607 */         v5[0] = v5[0] + m * bScale3;
/*  608 */         v5[1] = v5[1] - bScale1;
/*  609 */         v6[0] = v6[0] + m * bScale3;
/*  610 */         v6[1] = v6[1] + bScale2;
/*  611 */         v7[0] = v7[0] - m * bScale4;
/*  612 */         v7[1] = v7[1] + bScale2;
/*      */         break;
/*      */       case 4:
/*  615 */         v[0] = v[0] - m * bScale1;
/*  616 */         v[2] = v[2] - bScale3;
/*  617 */         v1[0] = v1[0] + m * bScale2;
/*  618 */         v1[2] = v1[2] - bScale3;
/*  619 */         v4[0] = v4[0] - m * bScale1;
/*  620 */         v4[2] = v4[2] + bScale4;
/*  621 */         v5[0] = v5[0] + m * bScale2;
/*  622 */         v5[2] = v5[2] + bScale4;
/*      */         break;
/*      */       case 5:
/*  625 */         v2[0] = v2[0] + m * bScale2;
/*  626 */         v2[2] = v2[2] - bScale3;
/*  627 */         v3[0] = v3[0] - m * bScale1;
/*  628 */         v3[2] = v3[2] - bScale3;
/*  629 */         v6[0] = v6[0] + m * bScale2;
/*  630 */         v6[2] = v6[2] + bScale4;
/*  631 */         v7[0] = v7[0] - m * bScale1;
/*  632 */         v7[2] = v7[2] + bScale4;
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  650 */     float[] qValues = { Math.abs((v[0] - v1[0]) / (v3[0] - v2[0])), Math.abs((v[0] - v1[0]) / (v4[0] - v5[0])), Math.abs((v4[0] - v5[0]) / (v7[0] - v6[0])), Math.abs((v3[0] - v2[0]) / (v7[0] - v6[0])), Math.abs((v[1] - v3[1]) / (v1[1] - v2[1])), Math.abs((v4[1] - v7[1]) / (v5[1] - v6[1])), Math.abs((v[1] - v3[1]) / (v4[1] - v7[1])), Math.abs((v1[1] - v2[1]) / (v5[1] - v6[1])), Math.abs((v[2] - v4[2]) / (v1[2] - v5[2])), Math.abs((v[2] - v4[2]) / (v3[2] - v7[2])), Math.abs((v1[2] - v5[2]) / (v2[2] - v6[2])), Math.abs((v3[2] - v7[2]) / (v2[2] - v6[2])) };
/*      */ 
/*      */     
/*  653 */     addRectShape(v, v1, v2, v3, v4, v5, v6, v7, w, h, d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFlexTrapezoid(float x, float y, float z, int w, int h, int d, float scale, float bScale1, float bScale2, float bScale3, float bScale4, float fScale1, float fScale2, int dir) {
/*  678 */     float f4 = x + w;
/*  679 */     float f5 = y + h;
/*  680 */     float f6 = z + d;
/*  681 */     x -= scale;
/*  682 */     y -= scale;
/*  683 */     z -= scale;
/*  684 */     f4 += scale;
/*  685 */     f5 += scale;
/*  686 */     f6 += scale;
/*      */     
/*  688 */     int m = this.mirror ? -1 : 1;
/*  689 */     if (this.mirror) {
/*      */       
/*  691 */       float f7 = f4;
/*  692 */       f4 = x;
/*  693 */       x = f7;
/*      */     } 
/*      */     
/*  696 */     float[] v = { x, y, z };
/*  697 */     float[] v1 = { f4, y, z };
/*  698 */     float[] v2 = { f4, f5, z };
/*  699 */     float[] v3 = { x, f5, z };
/*  700 */     float[] v4 = { x, y, f6 };
/*  701 */     float[] v5 = { f4, y, f6 };
/*  702 */     float[] v6 = { f4, f5, f6 };
/*  703 */     float[] v7 = { x, f5, f6 };
/*      */ 
/*      */     
/*  706 */     switch (dir) {
/*      */       
/*      */       case 3:
/*  709 */         v[2] = v[2] - fScale1;
/*  710 */         v1[2] = v1[2] - fScale1;
/*  711 */         v4[2] = v4[2] + fScale2;
/*  712 */         v5[2] = v5[2] + fScale2;
/*      */         
/*  714 */         v[1] = v[1] - bScale1;
/*  715 */         v[2] = v[2] - bScale3;
/*  716 */         v3[1] = v3[1] + bScale2;
/*  717 */         v3[2] = v3[2] - bScale3;
/*  718 */         v4[1] = v4[1] - bScale1;
/*  719 */         v4[2] = v4[2] + bScale4;
/*  720 */         v7[1] = v7[1] + bScale2;
/*  721 */         v7[2] = v7[2] + bScale4;
/*      */         break;
/*      */       case 2:
/*  724 */         v[2] = v[2] - fScale1;
/*  725 */         v1[2] = v1[2] - fScale1;
/*  726 */         v4[2] = v4[2] + fScale2;
/*  727 */         v5[2] = v5[2] + fScale2;
/*      */         
/*  729 */         v1[1] = v1[1] - bScale1;
/*  730 */         v1[2] = v1[2] - bScale3;
/*  731 */         v2[1] = v2[1] + bScale2;
/*  732 */         v2[2] = v2[2] - bScale3;
/*  733 */         v5[1] = v5[1] - bScale1;
/*  734 */         v5[2] = v5[2] + bScale4;
/*  735 */         v6[1] = v6[1] + bScale2;
/*  736 */         v6[2] = v6[2] + bScale4;
/*      */         break;
/*      */       case 0:
/*  739 */         v1[1] = v1[1] - fScale1;
/*  740 */         v5[1] = v5[1] - fScale1;
/*  741 */         v2[1] = v2[1] + fScale2;
/*  742 */         v6[1] = v6[1] + fScale2;
/*      */         
/*  744 */         v[0] = v[0] - m * bScale4;
/*  745 */         v[1] = v[1] - bScale1;
/*  746 */         v1[0] = v1[0] + m * bScale3;
/*  747 */         v1[1] = v1[1] - bScale1;
/*  748 */         v2[0] = v2[0] + m * bScale3;
/*  749 */         v2[1] = v2[1] + bScale2;
/*  750 */         v3[0] = v3[0] - m * bScale4;
/*  751 */         v3[1] = v3[1] + bScale2;
/*      */         break;
/*      */       case 1:
/*  754 */         v1[1] = v1[1] - fScale1;
/*  755 */         v5[1] = v5[1] - fScale1;
/*  756 */         v2[1] = v2[1] + fScale2;
/*  757 */         v6[1] = v6[1] + fScale2;
/*      */         
/*  759 */         v4[0] = v4[0] - m * bScale4;
/*  760 */         v4[1] = v4[1] - bScale1;
/*  761 */         v5[0] = v5[0] + m * bScale3;
/*  762 */         v5[1] = v5[1] - bScale1;
/*  763 */         v6[0] = v6[0] + m * bScale3;
/*  764 */         v6[1] = v6[1] + bScale2;
/*  765 */         v7[0] = v7[0] - m * bScale4;
/*  766 */         v7[1] = v7[1] + bScale2;
/*      */         break;
/*      */       case 4:
/*  769 */         v1[2] = v1[2] - fScale1;
/*  770 */         v2[2] = v2[2] - fScale1;
/*  771 */         v5[2] = v5[2] + fScale2;
/*  772 */         v6[2] = v6[2] + fScale2;
/*      */         
/*  774 */         v[0] = v[0] - m * bScale1;
/*  775 */         v[2] = v[2] - bScale3;
/*  776 */         v1[0] = v1[0] + m * bScale2;
/*  777 */         v1[2] = v1[2] - bScale3;
/*  778 */         v4[0] = v4[0] - m * bScale1;
/*  779 */         v4[2] = v4[2] + bScale4;
/*  780 */         v5[0] = v5[0] + m * bScale2;
/*  781 */         v5[2] = v5[2] + bScale4;
/*      */         break;
/*      */       case 5:
/*  784 */         v1[2] = v1[2] - fScale1;
/*  785 */         v2[2] = v2[2] - fScale1;
/*  786 */         v5[2] = v5[2] + fScale2;
/*  787 */         v6[2] = v6[2] + fScale2;
/*      */         
/*  789 */         v2[0] = v2[0] + m * bScale2;
/*  790 */         v2[2] = v2[2] - bScale3;
/*  791 */         v3[0] = v3[0] - m * bScale1;
/*  792 */         v3[2] = v3[2] - bScale3;
/*  793 */         v6[0] = v6[0] + m * bScale2;
/*  794 */         v6[2] = v6[2] + bScale4;
/*  795 */         v7[0] = v7[0] - m * bScale1;
/*  796 */         v7[2] = v7[2] + bScale4;
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  814 */     float[] qValues = { Math.abs((v[0] - v1[0]) / (v3[0] - v2[0])), Math.abs((v[0] - v1[0]) / (v4[0] - v5[0])), Math.abs((v4[0] - v5[0]) / (v7[0] - v6[0])), Math.abs((v3[0] - v2[0]) / (v7[0] - v6[0])), Math.abs((v[1] - v3[1]) / (v1[1] - v2[1])), Math.abs((v4[1] - v7[1]) / (v5[1] - v6[1])), Math.abs((v[1] - v3[1]) / (v4[1] - v7[1])), Math.abs((v1[1] - v2[1]) / (v5[1] - v6[1])), Math.abs((v[2] - v4[2]) / (v1[2] - v5[2])), Math.abs((v[2] - v4[2]) / (v3[2] - v7[2])), Math.abs((v1[2] - v5[2]) / (v2[2] - v6[2])), Math.abs((v3[2] - v7[2]) / (v2[2] - v6[2])) };
/*      */ 
/*      */     
/*  817 */     addRectShape(v, v1, v2, v3, v4, v5, v6, v7, w, h, d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addBox(float x, float y, float z, float w, float h, float d) {
/*  831 */     int rw = MathHelper.ceiling_float_int(w);
/*  832 */     int rh = MathHelper.ceiling_float_int(h);
/*  833 */     int rd = MathHelper.ceiling_float_int(d);
/*  834 */     w -= rw;
/*  835 */     h -= rh;
/*  836 */     d -= rd;
/*  837 */     addShapeBox(x, y, z, rw, rh, rd, 0.0F, 0.0F, 0.0F, 0.0F, w, 0.0F, 0.0F, w, 0.0F, d, 0.0F, 0.0F, d, 0.0F, h, 0.0F, w, h, 0.0F, w, h, d, 0.0F, h, d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addShapeBox(float x, float y, float z, int w, int h, int d, float scale, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, float x5, float y5, float z5, float x6, float y6, float z6, float x7, float y7, float z7) {
/*  864 */     float f4 = x + w;
/*  865 */     float f5 = y + h;
/*  866 */     float f6 = z + d;
/*  867 */     x -= scale;
/*  868 */     y -= scale;
/*  869 */     z -= scale;
/*  870 */     f4 += scale;
/*  871 */     f5 += scale;
/*  872 */     f6 += scale;
/*      */     
/*  874 */     int m = this.mirror ? -1 : 1;
/*  875 */     if (this.mirror) {
/*      */       
/*  877 */       float f7 = f4;
/*  878 */       f4 = x;
/*  879 */       x = f7;
/*      */     } 
/*      */     
/*  882 */     float[] v = { x - x0, y - y0, z - z0 };
/*  883 */     float[] v1 = { f4 + x1, y - y1, z - z1 };
/*  884 */     float[] v2 = { f4 + x5, f5 + y5, z - z5 };
/*  885 */     float[] v3 = { x - x4, f5 + y4, z - z4 };
/*  886 */     float[] v4 = { x - x3, y - y3, f6 + z3 };
/*  887 */     float[] v5 = { f4 + x2, y - y2, f6 + z2 };
/*  888 */     float[] v6 = { f4 + x6, f5 + y6, f6 + z6 };
/*  889 */     float[] v7 = { x - x7, f5 + y7, f6 + z7 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  905 */     float[] qValues = { Math.abs((v[0] - v1[0]) / (v3[0] - v2[0])), Math.abs((v[0] - v1[0]) / (v4[0] - v5[0])), Math.abs((v4[0] - v5[0]) / (v7[0] - v6[0])), Math.abs((v3[0] - v2[0]) / (v7[0] - v6[0])), Math.abs((v[1] - v3[1]) / (v1[1] - v2[1])), Math.abs((v4[1] - v7[1]) / (v5[1] - v6[1])), Math.abs((v[1] - v3[1]) / (v4[1] - v7[1])), Math.abs((v1[1] - v2[1]) / (v5[1] - v6[1])), Math.abs((v[2] - v4[2]) / (v1[2] - v5[2])), Math.abs((v[2] - v4[2]) / (v3[2] - v7[2])), Math.abs((v1[2] - v5[2]) / (v2[2] - v6[2])), Math.abs((v3[2] - v7[2]) / (v2[2] - v6[2])) };
/*      */ 
/*      */     
/*  908 */     addRectShape(v, v1, v2, v3, v4, v5, v6, v7, w, h, d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addShape3D(float x, float y, float z, Coord2D[] coordinates, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction) {
/*  927 */     addShape3D(x, y, z, coordinates, depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, direction, (float[])null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addShape3D(float x, float y, float z, Coord2D[] coordinates, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction, float[] faceLengths) {
/*  947 */     addShape3D(x, y, z, new Shape2D(coordinates), depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, direction, faceLengths);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addShape3D(float x, float y, float z, ArrayList<Coord2D> coordinates, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction) {
/*  965 */     addShape3D(x, y, z, coordinates, depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, direction, (float[])null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addShape3D(float x, float y, float z, ArrayList<Coord2D> coordinates, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction, float[] faceLengths) {
/*  985 */     addShape3D(x, y, z, new Shape2D(coordinates), depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, direction, faceLengths);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addShape3D(float x, float y, float z, Shape2D shape, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction) {
/* 1003 */     addShape3D(x, y, z, shape, depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, direction, (float[])null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addShape3D(float x, float y, float z, Shape2D shape, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, int direction, float[] faceLengths) {
/* 1023 */     float rotX = 0.0F;
/* 1024 */     float rotY = 0.0F;
/* 1025 */     float rotZ = 0.0F;
/* 1026 */     switch (direction) {
/*      */       
/*      */       case 2:
/* 1029 */         rotY = 1.5707964F;
/*      */         break;
/*      */       case 3:
/* 1032 */         rotY = -1.5707964F;
/*      */         break;
/*      */       case 4:
/* 1035 */         rotX = 1.5707964F;
/*      */         break;
/*      */       case 5:
/* 1038 */         rotX = -1.5707964F;
/*      */         break;
/*      */       case 0:
/* 1041 */         rotY = 3.1415927F;
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 1046 */     addShape3D(x, y, z, shape, depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, rotX, rotY, rotZ, faceLengths);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addShape3D(float x, float y, float z, Shape2D shape, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, float rotX, float rotY, float rotZ) {
/* 1066 */     addShape3D(x, y, z, shape, depth, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, rotX, rotY, rotZ, (float[])null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addShape3D(float x, float y, float z, Shape2D shape, float depth, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, float rotX, float rotY, float rotZ, float[] faceLengths) {
/* 1071 */     Shape3D shape3D = shape.extrude(x, y, z, rotX, rotY, rotZ, depth, this.textureOffsetX, this.textureOffsetY, this.textureWidth, this.textureHeight, shapeTextureWidth, shapeTextureHeight, sideTextureWidth, sideTextureHeight, faceLengths);
/*      */     
/* 1073 */     if (this.flip)
/*      */     {
/* 1075 */       for (int idx = 0; idx < shape3D.faces.length; idx++)
/*      */       {
/* 1077 */         shape3D.faces[idx].flipFace();
/*      */       }
/*      */     }
/*      */     
/* 1081 */     copyTo((PositionTextureVertex[])shape3D.vertices, shape3D.faces);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPixel(float x, float y, float z, float width, float height, float length) {
/* 1098 */     addPixel(x, y, z, new float[] { width, height, length }, this.textureOffsetX, this.textureOffsetY);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPixel(float x, float y, float z, float[] scale, int w, int h) {
/* 1115 */     PositionTextureVertex[] verts = new PositionTextureVertex[8];
/* 1116 */     TexturedPolygon[] poly = new TexturedPolygon[6];
/*      */     
/* 1118 */     float x1 = x + scale[0];
/* 1119 */     float y1 = y + scale[1];
/* 1120 */     float z1 = z + scale[2];
/*      */     
/* 1122 */     float[] f = { x, y, z };
/* 1123 */     float[] f1 = { x1, y, z };
/* 1124 */     float[] f2 = { x1, y1, z };
/* 1125 */     float[] f3 = { x, y1, z };
/* 1126 */     float[] f4 = { x, y, z1 };
/* 1127 */     float[] f5 = { x1, y, z1 };
/* 1128 */     float[] f6 = { x1, y1, z1 };
/* 1129 */     float[] f7 = { x, y1, z1 };
/* 1130 */     PositionTextureVertex positionTexturevertex = new PositionTextureVertex(f[0], f[1], f[2], 0.0F, 0.0F);
/* 1131 */     PositionTextureVertex positionTexturevertex1 = new PositionTextureVertex(f1[0], f1[1], f1[2], 0.0F, 8.0F);
/* 1132 */     PositionTextureVertex positionTexturevertex2 = new PositionTextureVertex(f2[0], f2[1], f2[2], 8.0F, 8.0F);
/* 1133 */     PositionTextureVertex positionTexturevertex3 = new PositionTextureVertex(f3[0], f3[1], f3[2], 8.0F, 0.0F);
/* 1134 */     PositionTextureVertex positionTexturevertex4 = new PositionTextureVertex(f4[0], f4[1], f4[2], 0.0F, 0.0F);
/* 1135 */     PositionTextureVertex positionTexturevertex5 = new PositionTextureVertex(f5[0], f5[1], f5[2], 0.0F, 8.0F);
/* 1136 */     PositionTextureVertex positionTexturevertex6 = new PositionTextureVertex(f6[0], f6[1], f6[2], 8.0F, 8.0F);
/* 1137 */     PositionTextureVertex positionTexturevertex7 = new PositionTextureVertex(f7[0], f7[1], f7[2], 8.0F, 0.0F);
/*      */     
/* 1139 */     verts[0] = positionTexturevertex;
/* 1140 */     verts[1] = positionTexturevertex1;
/* 1141 */     verts[2] = positionTexturevertex2;
/* 1142 */     verts[3] = positionTexturevertex3;
/* 1143 */     verts[4] = positionTexturevertex4;
/* 1144 */     verts[5] = positionTexturevertex5;
/* 1145 */     verts[6] = positionTexturevertex6;
/* 1146 */     verts[7] = positionTexturevertex7;
/*      */     
/* 1148 */     poly[0] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex5, positionTexturevertex1, positionTexturevertex2, positionTexturevertex6 }, w, h, w + 1, h + 1);
/*      */ 
/*      */     
/* 1151 */     poly[1] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex, positionTexturevertex4, positionTexturevertex7, positionTexturevertex3 }, w, h, w + 1, h + 1);
/*      */ 
/*      */     
/* 1154 */     poly[2] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex5, positionTexturevertex4, positionTexturevertex, positionTexturevertex1 }, w, h, w + 1, h + 1);
/*      */ 
/*      */     
/* 1157 */     poly[3] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex2, positionTexturevertex3, positionTexturevertex7, positionTexturevertex6 }, w, h, w + 1, h + 1);
/*      */ 
/*      */     
/* 1160 */     poly[4] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex1, positionTexturevertex, positionTexturevertex3, positionTexturevertex2 }, w, h, w + 1, h + 1);
/*      */ 
/*      */     
/* 1163 */     poly[5] = addPolygonReturn(new PositionTextureVertex[] { positionTexturevertex4, positionTexturevertex5, positionTexturevertex6, positionTexturevertex7 }, w, h, w + 1, h + 1);
/*      */ 
/*      */ 
/*      */     
/* 1167 */     copyTo(verts, poly);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSprite(float x, float y, float z, int w, int h, float expansion) {
/* 1184 */     addSprite(x, y, z, w, h, 1, false, false, false, false, false, expansion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSprite(float x, float y, float z, int w, int h, boolean rotX, boolean rotY, boolean rotZ, boolean mirrorX, boolean mirrorY, float expansion) {
/* 1206 */     addSprite(x, y, z, w, h, 1, rotX, rotY, rotZ, mirrorX, mirrorY, expansion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSprite(float x, float y, float z, int w, int h, int d, boolean rotX, boolean rotY, boolean rotZ, boolean mirrorX, boolean mirrorY, float expansion) {
/* 1229 */     addSprite(x, y, z, w, h, d, 1.0F, rotX, rotY, rotZ, mirrorX, mirrorY, expansion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSprite(float x, float y, float z, int w, int h, int d, float pixelScale, boolean rotX, boolean rotY, boolean rotZ, boolean mirrorX, boolean mirrorY, float expansion) {
/* 1253 */     String[] mask = new String[h];
/* 1254 */     char[] str = new char[w];
/* 1255 */     Arrays.fill(str, '1');
/* 1256 */     Arrays.fill((Object[])mask, new String(str));
/*      */     
/* 1258 */     addSprite(x, y, z, mask, d, pixelScale, rotX, rotY, rotZ, mirrorX, mirrorY, expansion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSprite(float x, float y, float z, String[] mask, int d, float pixelScale, boolean rotX, boolean rotY, boolean rotZ, boolean mirrorX, boolean mirrorY, float expansion) {
/* 1285 */     int w = mask[0].length();
/* 1286 */     int h = mask.length;
/*      */     
/* 1288 */     float x1 = x - expansion;
/* 1289 */     float y1 = y - expansion;
/* 1290 */     float z1 = z - expansion;
/*      */     
/* 1292 */     int wDir = 0;
/* 1293 */     int hDir = 0;
/* 1294 */     int dDir = 0;
/*      */     
/* 1296 */     float wScale = 1.0F + expansion / w * pixelScale;
/* 1297 */     float hScale = 1.0F + expansion / h * pixelScale;
/*      */     
/* 1299 */     if (!rotX) {
/*      */       
/* 1301 */       if (!rotY)
/*      */       {
/* 1303 */         if (!rotZ)
/*      */         {
/* 1305 */           wDir = 0;
/* 1306 */           hDir = 1;
/* 1307 */           dDir = 2;
/*      */         }
/*      */         else
/*      */         {
/* 1311 */           wDir = 1;
/* 1312 */           hDir = 0;
/* 1313 */           dDir = 2;
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1318 */       else if (!rotZ)
/*      */       {
/* 1320 */         wDir = 2;
/* 1321 */         hDir = 1;
/* 1322 */         dDir = 0;
/*      */       }
/*      */       else
/*      */       {
/* 1326 */         wDir = 2;
/* 1327 */         hDir = 0;
/* 1328 */         dDir = 1;
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1334 */     else if (!rotY) {
/*      */       
/* 1336 */       if (!rotZ)
/*      */       {
/* 1338 */         wDir = 0;
/* 1339 */         hDir = 2;
/* 1340 */         dDir = 1;
/*      */       }
/*      */       else
/*      */       {
/* 1344 */         wDir = 1;
/* 1345 */         hDir = 2;
/* 1346 */         dDir = 0;
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1351 */     else if (!rotZ) {
/*      */       
/* 1353 */       wDir = 2;
/* 1354 */       hDir = 0;
/* 1355 */       dDir = 1;
/*      */     }
/*      */     else {
/*      */       
/* 1359 */       wDir = 2;
/* 1360 */       hDir = 1;
/* 1361 */       dDir = 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1366 */     int texStartX = this.textureOffsetX + (mirrorX ? (w - 1) : 0);
/* 1367 */     int texStartY = this.textureOffsetY + (mirrorY ? (h - 1) : 0);
/* 1368 */     int texDirX = mirrorX ? -1 : 1;
/* 1369 */     int texDirY = mirrorY ? -1 : 1;
/*      */     
/* 1371 */     float wVoxSize = getPixelSize(wScale, hScale, d * pixelScale + expansion * 2.0F, 0, 1, wDir, 1, 1);
/* 1372 */     float hVoxSize = getPixelSize(wScale, hScale, d * pixelScale + expansion * 2.0F, 0, 1, hDir, 1, 1);
/* 1373 */     float dVoxSize = getPixelSize(wScale, hScale, d * pixelScale + expansion * 2.0F, 0, 1, dDir, 1, 1);
/*      */     
/* 1375 */     for (int i = 0; i < w; i++) {
/*      */       
/* 1377 */       for (int j = 0; j < h; j++) {
/*      */         
/* 1379 */         if (mask[j].charAt(i) == '1')
/*      */         {
/* 1381 */           addPixel(x1 + getPixelSize(wScale, hScale, 0.0F, wDir, hDir, 0, i, j), y1 + 
/* 1382 */               getPixelSize(wScale, hScale, 0.0F, wDir, hDir, 1, i, j), z1 + 
/* 1383 */               getPixelSize(wScale, hScale, 0.0F, wDir, hDir, 2, i, j), new float[] { wVoxSize, hVoxSize, dVoxSize }, texStartX + texDirX * i, texStartY + texDirY * j);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private float getPixelSize(float wScale, float hScale, float dScale, int wDir, int hDir, int checkDir, int texPosX, int texPosY) {
/* 1392 */     return (wDir == checkDir) ? (wScale * texPosX) : ((hDir == checkDir) ? (hScale * texPosY) : dScale);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSphere(float x, float y, float z, float r, int segs, int rings, int textureW, int textureH) {
/* 1408 */     if (segs < 3)
/* 1409 */       segs = 3; 
/* 1410 */     rings++;
/* 1411 */     PositionTextureVertex[] tempVerts = new PositionTextureVertex[segs * (rings - 1) + 2];
/* 1412 */     TexturedPolygon[] poly = new TexturedPolygon[segs * rings];
/*      */     
/* 1414 */     tempVerts[0] = new PositionTextureVertex(x, y - r, z, 0.0F, 0.0F);
/* 1415 */     tempVerts[tempVerts.length - 1] = new PositionTextureVertex(x, y + r, z, 0.0F, 0.0F);
/*      */     
/* 1417 */     float uOffs = 1.0F / this.textureWidth * 10.0F;
/* 1418 */     float vOffs = 1.0F / this.textureHeight * 10.0F;
/* 1419 */     float texW = textureW / this.textureWidth - 2.0F * uOffs;
/* 1420 */     float texH = textureH / this.textureHeight - 2.0F * vOffs;
/* 1421 */     float segW = texW / segs;
/* 1422 */     float segH = texH / rings;
/* 1423 */     float startU = this.textureOffsetX / this.textureWidth;
/* 1424 */     float startV = this.textureOffsetY / this.textureHeight;
/*      */     
/* 1426 */     int currentFace = 0;
/*      */     
/* 1428 */     for (int j = 1; j < rings; j++) {
/*      */       PositionTextureVertex[] verts;
/* 1430 */       for (int k = 0; k < segs; k++) {
/*      */         
/* 1432 */         float yWidth = MathHelper.cos(-1.5707964F + 3.1415927F / rings * j);
/* 1433 */         float yHeight = MathHelper.sin(-1.5707964F + 3.1415927F / rings * j);
/* 1434 */         float xSize = MathHelper.sin(3.1415927F / segs * k * 2.0F + 3.1415927F) * yWidth;
/* 1435 */         float zSize = -MathHelper.cos(3.1415927F / segs * k * 2.0F + 3.1415927F) * yWidth;
/* 1436 */         int curVert = 1 + k + segs * (j - 1);
/* 1437 */         tempVerts[curVert] = new PositionTextureVertex(x + xSize * r, y + yHeight * r, z + zSize * r, 0.0F, 0.0F);
/* 1438 */         if (k > 0) {
/*      */           PositionTextureVertex[] arrayOfPositionTextureVertex;
/*      */           
/* 1441 */           if (j == 1) {
/*      */             
/* 1443 */             arrayOfPositionTextureVertex = new PositionTextureVertex[4];
/* 1444 */             arrayOfPositionTextureVertex[0] = tempVerts[curVert].setTexturePosition(startU + segW * k, startV + segH * j);
/* 1445 */             arrayOfPositionTextureVertex[1] = tempVerts[curVert - 1].setTexturePosition(startU + segW * (k - 1), startV + segH * j);
/* 1446 */             arrayOfPositionTextureVertex[2] = tempVerts[0].setTexturePosition(startU + segW * (k - 1), startV);
/* 1447 */             arrayOfPositionTextureVertex[3] = tempVerts[0].setTexturePosition(startU + segW + segW * k, startV);
/*      */           }
/*      */           else {
/*      */             
/* 1451 */             arrayOfPositionTextureVertex = new PositionTextureVertex[4];
/* 1452 */             arrayOfPositionTextureVertex[0] = tempVerts[curVert].setTexturePosition(startU + segW * k, startV + segH * j);
/* 1453 */             arrayOfPositionTextureVertex[1] = tempVerts[curVert - 1].setTexturePosition(startU + segW * (k - 1), startV + segH * j);
/* 1454 */             arrayOfPositionTextureVertex[2] = tempVerts[curVert - 1 - segs].setTexturePosition(startU + segW * (k - 1), startV + segH * (j - 1));
/* 1455 */             arrayOfPositionTextureVertex[3] = tempVerts[curVert - segs].setTexturePosition(startU + segW * k, startV + segH * (j - 1));
/*      */           } 
/* 1457 */           poly[currentFace] = new TexturedPolygon(arrayOfPositionTextureVertex);
/* 1458 */           currentFace++;
/*      */         } 
/*      */       } 
/*      */       
/* 1462 */       if (j == 1) {
/*      */         
/* 1464 */         verts = new PositionTextureVertex[4];
/* 1465 */         verts[0] = tempVerts[1].setTexturePosition(startU + segW * segs, startV + segH * j);
/* 1466 */         verts[1] = tempVerts[segs].setTexturePosition(startU + segW * (segs - 1), startV + segH * j);
/* 1467 */         verts[2] = tempVerts[0].setTexturePosition(startU + segW * (segs - 1), startV);
/* 1468 */         verts[3] = tempVerts[0].setTexturePosition(startU + segW * segs, startV);
/*      */       }
/*      */       else {
/*      */         
/* 1472 */         verts = new PositionTextureVertex[4];
/* 1473 */         verts[0] = tempVerts[1 + segs * (j - 1)].setTexturePosition(startU + texW, startV + segH * j);
/* 1474 */         verts[1] = tempVerts[segs * (j - 1) + segs].setTexturePosition(startU + texW - segW, startV + segH * j);
/* 1475 */         verts[2] = tempVerts[segs * (j - 1)].setTexturePosition(startU + texW - segW, startV + segH * (j - 1));
/* 1476 */         verts[3] = tempVerts[1 + segs * (j - 1) - segs].setTexturePosition(startU + texW, startV + segH * (j - 1));
/*      */       } 
/* 1478 */       poly[currentFace] = new TexturedPolygon(verts);
/* 1479 */       currentFace++;
/*      */     } 
/* 1481 */     for (int i = 0; i < segs; i++) {
/*      */       
/* 1483 */       PositionTextureVertex[] verts = new PositionTextureVertex[3];
/* 1484 */       int curVert = tempVerts.length - segs + 1;
/* 1485 */       verts[0] = tempVerts[tempVerts.length - 1].setTexturePosition(startU + segW * (i + 0.5F), startV + texH);
/* 1486 */       verts[1] = tempVerts[curVert + i].setTexturePosition(startU + segW * i, startV + texH - segH);
/* 1487 */       verts[2] = tempVerts[curVert + (i + 1) % segs].setTexturePosition(startU + segW * (i + 1), startV + texH - segH);
/* 1488 */       poly[currentFace] = new TexturedPolygon(verts);
/* 1489 */       currentFace++;
/*      */     } 
/*      */     
/* 1492 */     copyTo(tempVerts, poly);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCone(float x, float y, float z, float radius, float length, int segments) {
/* 1506 */     addCone(x, y, z, radius, length, segments, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCone(float x, float y, float z, float radius, float length, int segments, float baseScale) {
/* 1524 */     addCone(x, y, z, radius, length, segments, baseScale, 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCone(float x, float y, float z, float radius, float length, int segments, float baseScale, int baseDirection) {
/* 1546 */     addCone(x, y, z, radius, length, segments, baseScale, baseDirection, (int)Math.floor((radius * 2.0F)), (int)Math.floor((radius * 2.0F)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCone(float x, float y, float z, float radius, float length, int segments, float baseScale, int baseDirection, int textureCircleDiameterW, int textureCircleDiameterH) {
/* 1572 */     addCylinder(x, y, z, radius, length, segments, baseScale, 0.0F, baseDirection, textureCircleDiameterW, textureCircleDiameterH, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCylinder(float x, float y, float z, float radius, float length, int segments) {
/* 1586 */     addCylinder(x, y, z, radius, length, segments, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCylinder(float x, float y, float z, float radius, float length, int segments, float baseScale, float topScale) {
/* 1606 */     addCylinder(x, y, z, radius, length, segments, baseScale, topScale, 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCylinder(float x, float y, float z, float radius, float length, int segments, float baseScale, float topScale, int baseDirection) {
/* 1630 */     addCylinder(x, y, z, radius, length, segments, baseScale, topScale, baseDirection, (int)Math.floor((radius * 2.0F)), (int)Math.floor((radius * 2.0F)), (int)Math.floor(length));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCylinder(float x, float y, float z, float radius, float length, int segments, float baseScale, float topScale, int baseDirection, int textureCircleDiameterW, int textureCircleDiameterH, int textureH) {
/* 1660 */     boolean dirTop = (baseDirection == 4 || baseDirection == 5);
/* 1661 */     boolean dirSide = (baseDirection == 3 || baseDirection == 2);
/* 1662 */     boolean dirFront = (baseDirection == 0 || baseDirection == 1);
/* 1663 */     boolean dirMirror = (baseDirection == 2 || baseDirection == 5 || baseDirection == 1);
/*      */     
/* 1665 */     boolean coneBase = (baseScale == 0.0F);
/* 1666 */     boolean coneTop = (topScale == 0.0F);
/*      */     
/* 1668 */     if (coneBase && coneTop) {
/*      */       
/* 1670 */       baseScale = 1.0F;
/* 1671 */       coneBase = false;
/*      */     } 
/*      */     
/* 1674 */     PositionTextureVertex[] tempVerts = new PositionTextureVertex[segments * ((coneBase || coneTop) ? 1 : 2) + 2];
/* 1675 */     TexturedPolygon[] poly = new TexturedPolygon[segments * ((coneBase || coneTop) ? 2 : 3)];
/*      */     
/* 1677 */     float xLength = dirSide ? length : 0.0F;
/* 1678 */     float yLength = dirTop ? length : 0.0F;
/* 1679 */     float zLength = dirFront ? length : 0.0F;
/*      */     
/* 1681 */     float xStart = dirMirror ? (x + xLength) : x;
/* 1682 */     float yStart = dirMirror ? (y + yLength) : y;
/* 1683 */     float zStart = dirMirror ? (z + zLength) : z;
/* 1684 */     float xEnd = !dirMirror ? (x + xLength) : x;
/* 1685 */     float yEnd = !dirMirror ? (y + yLength) : y;
/* 1686 */     float zEnd = !dirMirror ? (z + zLength) : z;
/*      */     
/* 1688 */     tempVerts[0] = new PositionTextureVertex(xStart, yStart, zStart, 0.0F, 0.0F);
/* 1689 */     tempVerts[tempVerts.length - 1] = new PositionTextureVertex(xEnd, yEnd, zEnd, 0.0F, 0.0F);
/*      */     
/* 1691 */     float xCur = xStart;
/* 1692 */     float yCur = yStart;
/* 1693 */     float zCur = zStart;
/* 1694 */     float sCur = coneBase ? topScale : baseScale;
/* 1695 */     for (int repeat = 0; repeat < ((coneBase || coneTop) ? 1 : 2); repeat++) {
/*      */       
/* 1697 */       for (int i = 0; i < segments; i++) {
/*      */         
/* 1699 */         float xSize = ((this.mirror ^ dirMirror) ? -1 : true) * MathHelper.sin(3.1415927F / segments * i * 2.0F + 3.1415927F) * radius * sCur;
/* 1700 */         float zSize = -MathHelper.cos(3.1415927F / segments * i * 2.0F + 3.1415927F) * radius * sCur;
/*      */         
/* 1702 */         float xPlace = xCur + (!dirSide ? xSize : 0.0F);
/* 1703 */         float yPlace = yCur + (!dirTop ? zSize : 0.0F);
/* 1704 */         float zPlace = zCur + (dirSide ? xSize : (dirTop ? zSize : 0.0F));
/*      */         
/* 1706 */         tempVerts[1 + i + repeat * segments] = new PositionTextureVertex(xPlace, yPlace, zPlace, 0.0F, 0.0F);
/*      */       } 
/* 1708 */       xCur = xEnd;
/* 1709 */       yCur = yEnd;
/* 1710 */       zCur = zEnd;
/* 1711 */       sCur = topScale;
/*      */     } 
/*      */     
/* 1714 */     float uScale = 1.0F / this.textureWidth;
/* 1715 */     float vScale = 1.0F / this.textureHeight;
/* 1716 */     float uOffset = uScale / 20.0F;
/* 1717 */     float vOffset = vScale / 20.0F;
/* 1718 */     float uCircle = textureCircleDiameterW * uScale;
/* 1719 */     float vCircle = textureCircleDiameterH * vScale;
/* 1720 */     float uWidth = (uCircle * 2.0F - uOffset * 2.0F) / segments;
/* 1721 */     float vHeight = textureH * vScale - uOffset * 2.0F;
/* 1722 */     float uStart = this.textureOffsetX * uScale;
/* 1723 */     float vStart = this.textureOffsetY * vScale;
/*      */ 
/*      */     
/* 1726 */     for (int index = 0; index < segments; index++) {
/*      */       
/* 1728 */       int index2 = (index + 1) % segments;
/* 1729 */       float uSize = MathHelper.sin(3.1415927F / segments * index * 2.0F + (!dirTop ? 0.0F : 3.1415927F)) * (0.5F * uCircle - 2.0F * uOffset);
/* 1730 */       float vSize = MathHelper.cos(3.1415927F / segments * index * 2.0F + (!dirTop ? 0.0F : 3.1415927F)) * (0.5F * vCircle - 2.0F * vOffset);
/* 1731 */       float uSize1 = MathHelper.sin(3.1415927F / segments * index2 * 2.0F + (!dirTop ? 0.0F : 3.1415927F)) * (0.5F * uCircle - 2.0F * uOffset);
/* 1732 */       float vSize1 = MathHelper.cos(3.1415927F / segments * index2 * 2.0F + (!dirTop ? 0.0F : 3.1415927F)) * (0.5F * vCircle - 2.0F * vOffset);
/* 1733 */       PositionTextureVertex[] vert = new PositionTextureVertex[3];
/*      */       
/* 1735 */       vert[0] = tempVerts[0].setTexturePosition(uStart + 0.5F * uCircle, vStart + 0.5F * vCircle);
/* 1736 */       vert[1] = tempVerts[1 + index2].setTexturePosition(uStart + 0.5F * uCircle + uSize1, vStart + 0.5F * vCircle + vSize1);
/* 1737 */       vert[2] = tempVerts[1 + index].setTexturePosition(uStart + 0.5F * uCircle + uSize, vStart + 0.5F * vCircle + vSize);
/*      */       
/* 1739 */       poly[index] = new TexturedPolygon(vert);
/* 1740 */       if ((this.mirror ^ this.flip) != 0) {
/* 1741 */         poly[index].flipFace();
/*      */       }
/* 1743 */       if (!coneBase && !coneTop) {
/*      */         
/* 1745 */         vert = new PositionTextureVertex[4];
/*      */         
/* 1747 */         vert[0] = tempVerts[1 + index].setTexturePosition(uStart + uOffset + uWidth * index, vStart + vOffset + vCircle);
/* 1748 */         vert[1] = tempVerts[1 + index2].setTexturePosition(uStart + uOffset + uWidth * (index + 1), vStart + vOffset + vCircle);
/* 1749 */         vert[2] = tempVerts[1 + segments + index2].setTexturePosition(uStart + uOffset + uWidth * (index + 1), vStart + vOffset + vCircle + vHeight);
/* 1750 */         vert[3] = tempVerts[1 + segments + index].setTexturePosition(uStart + uOffset + uWidth * index, vStart + vOffset + vCircle + vHeight);
/* 1751 */         poly[index + segments] = new TexturedPolygon(vert);
/* 1752 */         if ((this.mirror ^ this.flip) != 0) {
/* 1753 */           poly[index + segments].flipFace();
/*      */         }
/*      */       } 
/* 1756 */       vert = new PositionTextureVertex[3];
/*      */       
/* 1758 */       vert[0] = tempVerts[tempVerts.length - 1].setTexturePosition(uStart + 1.5F * uCircle, vStart + 0.5F * vCircle);
/* 1759 */       vert[1] = tempVerts[tempVerts.length - 2 - index].setTexturePosition(uStart + 1.5F * uCircle + uSize1, vStart + 0.5F * vCircle + vSize1);
/* 1760 */       vert[2] = tempVerts[tempVerts.length - 1 + segments + (segments - index) % segments].setTexturePosition(uStart + 1.5F * uCircle + uSize, vStart + 0.5F * vCircle + vSize);
/*      */       
/* 1762 */       poly[poly.length - segments + index] = new TexturedPolygon(vert);
/* 1763 */       if ((this.mirror ^ this.flip) != 0)
/* 1764 */         poly[poly.length - segments + index].flipFace(); 
/*      */     } 
/* 1766 */     copyTo(tempVerts, poly);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addObj(String file) {
/* 1776 */     addModel(file, ModelPool.OBJ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addModel(String file, Class modelFormat) {
/* 1787 */     ModelPoolEntry entry = ModelPool.addFile(file, modelFormat, this.transformGroup, this.textureGroup);
/* 1788 */     if (entry == null)
/*      */       return; 
/* 1790 */     PositionTextureVertex[] verts = Arrays.<PositionTextureVertex>copyOf((PositionTextureVertex[])entry.vertices, entry.vertices.length);
/* 1791 */     TexturedPolygon[] poly = Arrays.<TexturedPolygon>copyOf(entry.faces, entry.faces.length);
/* 1792 */     if (this.flip)
/*      */     {
/* 1794 */       for (TexturedPolygon face : this.faces) {
/* 1795 */         face.flipFace();
/*      */       }
/*      */     }
/*      */     
/* 1799 */     copyTo(verts, poly, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ModelRendererTurbo setTextureOffset(int x, int y) {
/* 1810 */     this.textureOffsetX = x;
/* 1811 */     this.textureOffsetY = y;
/* 1812 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPosition(float x, float y, float z) {
/* 1824 */     this.rotationPointX = x;
/* 1825 */     this.rotationPointY = y;
/* 1826 */     this.rotationPointZ = z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void doMirror(boolean x, boolean y, boolean z) {
/* 1837 */     for (TexturedPolygon face : this.faces) {
/* 1838 */       PositionTextureVertex[] verts = face.vertexPositions;
/* 1839 */       for (int j = 0; j < verts.length; j++) {
/* 1840 */         (verts[j]).vector3D.xCoord *= (x ? -1 : true);
/* 1841 */         (verts[j]).vector3D.yCoord *= (y ? -1 : true);
/* 1842 */         (verts[j]).vector3D.zCoord *= (z ? -1 : true);
/*      */       } 
/* 1844 */       if (x ^ y ^ z) {
/* 1845 */         face.flipFace();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMirrored(boolean isMirrored) {
/* 1856 */     this.mirror = isMirrored;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFlipped(boolean isFlipped) {
/* 1867 */     this.flip = isFlipped;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/* 1877 */     this.vertices = new PositionTextureVertex[0];
/* 1878 */     this.faces = new TexturedPolygon[0];
/* 1879 */     this.transformGroup.clear();
/* 1880 */     this.transformGroup.put("0", new TransformGroupBone(new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D));
/* 1881 */     this.currentGroup = this.transformGroup.get("0");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void copyTo(PositionTextureVertex[] verts, TexturedPolygon[] poly) {
/* 1893 */     copyTo(verts, poly, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public void copyTo(PositionTextureVertex[] verts, TexturedPolygon[] poly, boolean copyGroup) {
/* 1898 */     this.vertices = Arrays.<PositionTextureVertex>copyOf(this.vertices, this.vertices.length + verts.length);
/* 1899 */     this.faces = Arrays.<TexturedPolygon>copyOf(this.faces, this.faces.length + poly.length);
/*      */     int idx;
/* 1901 */     for (idx = 0; idx < verts.length; idx++) {
/*      */       
/* 1903 */       this.vertices[this.vertices.length - verts.length + idx] = verts[idx];
/* 1904 */       if (copyGroup && verts[idx] instanceof PositionTransformVertex) {
/* 1905 */         ((PositionTransformVertex)verts[idx]).addGroup(this.currentGroup);
/*      */       }
/*      */     } 
/* 1908 */     for (idx = 0; idx < poly.length; idx++) {
/*      */       
/* 1910 */       this.faces[this.faces.length - poly.length + idx] = poly[idx];
/* 1911 */       if (copyGroup) {
/* 1912 */         this.currentTextureGroup.addPoly(poly[idx]);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void copyTo(PositionTextureVertex[] verts, TexturedQuad[] quad) {
/* 1924 */     TexturedPolygon[] poly = new TexturedPolygon[quad.length];
/* 1925 */     for (int idx = 0; idx < quad.length; idx++)
/*      */     {
/* 1927 */       poly[idx] = new TexturedPolygon((PositionTextureVertex[])(quad[idx]).vertexPositions);
/*      */     }
/*      */     
/* 1930 */     copyTo(verts, poly);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGroup(String groupName) {
/* 1941 */     setGroup(groupName, new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGroup(String groupName, Bone bone, double weight) {
/* 1954 */     if (!this.transformGroup.containsKey(groupName))
/* 1955 */       this.transformGroup.put(groupName, new TransformGroupBone(bone, weight)); 
/* 1956 */     this.currentGroup = this.transformGroup.get(groupName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TransformGroup getGroup() {
/* 1965 */     return this.currentGroup;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TransformGroup getGroup(String groupName) {
/* 1974 */     if (!this.transformGroup.containsKey(groupName))
/* 1975 */       return null; 
/* 1976 */     return this.transformGroup.get(groupName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureGroup(String groupName) {
/* 1990 */     if (!this.textureGroup.containsKey(groupName))
/*      */     {
/* 1992 */       this.textureGroup.put(groupName, new TextureGroup());
/*      */     }
/* 1994 */     this.currentTextureGroup = this.textureGroup.get(groupName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TextureGroup getTextureGroup() {
/* 2003 */     return this.currentTextureGroup;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TextureGroup getTextureGroup(String groupName) {
/* 2013 */     if (!this.textureGroup.containsKey(groupName))
/* 2014 */       return null; 
/* 2015 */     return this.textureGroup.get(groupName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGroupTexture(String s) {
/* 2024 */     this.currentTextureGroup.texture = s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDefaultTexture(String s) {
/* 2036 */     this.defaultTexture = s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(float worldScale) {
/* 2046 */     render(worldScale, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(float worldScale, boolean oldRotateOrder) {
/* 2057 */     if (this.field_1402_i) {
/*      */       return;
/*      */     }
/*      */     
/* 2061 */     if (!this.showModel) {
/*      */       return;
/*      */     }
/*      */     
/* 2065 */     if (!this.compiled || this.forcedRecompile)
/*      */     {
/* 2067 */       compileDisplayList(worldScale);
/*      */     }
/* 2069 */     if (this.rotateAngleX != 0.0F || this.rotateAngleY != 0.0F || this.rotateAngleZ != 0.0F) {
/*      */       
/* 2071 */       GL11.glPushMatrix();
/* 2072 */       GL11.glTranslatef(this.rotationPointX * worldScale, this.rotationPointY * worldScale, this.rotationPointZ * worldScale);
/* 2073 */       if (!oldRotateOrder && this.rotateAngleY != 0.0F)
/*      */       {
/* 2075 */         GL11.glRotatef(this.rotateAngleY * 57.29578F, 0.0F, 1.0F, 0.0F);
/*      */       }
/* 2077 */       if (this.rotateAngleZ != 0.0F)
/*      */       {
/* 2079 */         GL11.glRotatef((oldRotateOrder ? -1 : true) * this.rotateAngleZ * 57.29578F, 0.0F, 0.0F, 1.0F);
/*      */       }
/* 2081 */       if (oldRotateOrder && this.rotateAngleY != 0.0F)
/*      */       {
/* 2083 */         GL11.glRotatef(-this.rotateAngleY * 57.29578F, 0.0F, 1.0F, 0.0F);
/*      */       }
/* 2085 */       if (this.rotateAngleX != 0.0F)
/*      */       {
/* 2087 */         GL11.glRotatef(this.rotateAngleX * 57.29578F, 1.0F, 0.0F, 0.0F);
/*      */       }
/*      */       
/* 2090 */       callDisplayList();
/* 2091 */       if (this.childModels != null)
/*      */       {
/* 2093 */         for (Object childModel : this.childModels) {
/* 2094 */           ((ModelRenderer)childModel).render(worldScale);
/*      */         }
/*      */       }
/*      */       
/* 2098 */       GL11.glPopMatrix();
/*      */     }
/* 2100 */     else if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
/*      */       
/* 2102 */       GL11.glTranslatef(this.rotationPointX * worldScale, this.rotationPointY * worldScale, this.rotationPointZ * worldScale);
/* 2103 */       callDisplayList();
/* 2104 */       if (this.childModels != null)
/*      */       {
/* 2106 */         for (Object childModel : this.childModels) {
/* 2107 */           ((ModelRenderer)childModel).render(worldScale);
/*      */         }
/*      */       }
/*      */       
/* 2111 */       GL11.glTranslatef(-this.rotationPointX * worldScale, -this.rotationPointY * worldScale, -this.rotationPointZ * worldScale);
/*      */     } else {
/*      */       
/* 2114 */       callDisplayList();
/* 2115 */       if (this.childModels != null)
/*      */       {
/* 2117 */         for (Object childModel : this.childModels) {
/* 2118 */           ((ModelRenderer)childModel).render(worldScale);
/*      */         }
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderWithRotation(float f) {
/* 2128 */     if (this.field_1402_i) {
/*      */       return;
/*      */     }
/*      */     
/* 2132 */     if (!this.showModel) {
/*      */       return;
/*      */     }
/*      */     
/* 2136 */     if (!this.compiled)
/*      */     {
/* 2138 */       compileDisplayList(f);
/*      */     }
/* 2140 */     GL11.glPushMatrix();
/* 2141 */     GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
/* 2142 */     if (this.rotateAngleY != 0.0F)
/*      */     {
/* 2144 */       GL11.glRotatef(this.rotateAngleY * 57.29578F, 0.0F, 1.0F, 0.0F);
/*      */     }
/* 2146 */     if (this.rotateAngleX != 0.0F)
/*      */     {
/* 2148 */       GL11.glRotatef(this.rotateAngleX * 57.29578F, 1.0F, 0.0F, 0.0F);
/*      */     }
/* 2150 */     if (this.rotateAngleZ != 0.0F)
/*      */     {
/* 2152 */       GL11.glRotatef(this.rotateAngleZ * 57.29578F, 0.0F, 0.0F, 1.0F);
/*      */     }
/* 2154 */     callDisplayList();
/* 2155 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void postRender(float f) {
/* 2161 */     if (this.field_1402_i) {
/*      */       return;
/*      */     }
/*      */     
/* 2165 */     if (!this.showModel) {
/*      */       return;
/*      */     }
/*      */     
/* 2169 */     if (!this.compiled || this.forcedRecompile)
/*      */     {
/* 2171 */       compileDisplayList(f);
/*      */     }
/* 2173 */     if (this.rotateAngleX != 0.0F || this.rotateAngleY != 0.0F || this.rotateAngleZ != 0.0F) {
/*      */       
/* 2175 */       GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
/* 2176 */       if (this.rotateAngleZ != 0.0F)
/*      */       {
/* 2178 */         GL11.glRotatef(this.rotateAngleZ * 57.29578F, 0.0F, 0.0F, 1.0F);
/*      */       }
/* 2180 */       if (this.rotateAngleY != 0.0F)
/*      */       {
/* 2182 */         GL11.glRotatef(this.rotateAngleY * 57.29578F, 0.0F, 1.0F, 0.0F);
/*      */       }
/* 2184 */       if (this.rotateAngleX != 0.0F)
/*      */       {
/* 2186 */         GL11.glRotatef(this.rotateAngleX * 57.29578F, 1.0F, 0.0F, 0.0F);
/*      */       }
/*      */     }
/* 2189 */     else if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
/*      */       
/* 2191 */       GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void callDisplayList() {
/* 2197 */     if (this.useLegacyCompiler) {
/* 2198 */       GL11.glCallList(this.displayList);
/*      */     } else {
/*      */       
/* 2201 */       TextureManager renderEngine = RenderManager.instance.renderEngine;
/*      */       
/* 2203 */       Collection<TextureGroup> textures = this.textureGroup.values();
/*      */       
/* 2205 */       Iterator<TextureGroup> itr = textures.iterator();
/* 2206 */       for (int i = 0; itr.hasNext(); i++) {
/*      */         
/* 2208 */         TextureGroup curTexGroup = itr.next();
/* 2209 */         curTexGroup.loadTexture();
/* 2210 */         GL11.glCallList(this.displayListArray[i]);
/* 2211 */         if (!this.defaultTexture.equals("")) {
/* 2212 */           renderEngine.bindTexture(new ResourceLocation("", this.defaultTexture));
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compileDisplayList(float worldScale) {
/* 2219 */     if (this.useLegacyCompiler) {
/* 2220 */       compileLegacyDisplayList(worldScale);
/*      */     } else {
/*      */       
/* 2223 */       Collection<TextureGroup> textures = this.textureGroup.values();
/*      */       
/* 2225 */       Iterator<TextureGroup> itr = textures.iterator();
/* 2226 */       this.displayListArray = new int[this.textureGroup.size()];
/* 2227 */       for (int i = 0; itr.hasNext(); i++) {
/*      */         
/* 2229 */         this.displayListArray[i] = GLAllocation.generateDisplayLists(1);
/* 2230 */         GL11.glNewList(this.displayListArray[i], 4864);
/* 2231 */         TmtTessellator tessellator = TmtTessellator.instance;
/*      */         
/* 2233 */         TextureGroup usedGroup = itr.next();
/* 2234 */         for (int j = 0; j < usedGroup.poly.size(); j++)
/*      */         {
/* 2236 */           ((TexturedPolygon)usedGroup.poly.get(j)).draw(tessellator, worldScale);
/*      */         }
/*      */         
/* 2239 */         GL11.glEndList();
/*      */       } 
/*      */     } 
/*      */     
/* 2243 */     this.compiled = true;
/*      */   }
/*      */ 
/*      */   
/*      */   private void compileLegacyDisplayList(float worldScale) {
/* 2248 */     this.displayList = GLAllocation.generateDisplayLists(1);
/* 2249 */     GL11.glNewList(this.displayList, 4864);
/* 2250 */     TmtTessellator tessellator = TmtTessellator.instance;
/* 2251 */     for (TexturedPolygon face : this.faces) {
/* 2252 */       face.draw(tessellator, worldScale);
/*      */     }
/*      */     
/* 2255 */     GL11.glEndList();
/*      */   }
/*      */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\ModelRendererTurbo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */