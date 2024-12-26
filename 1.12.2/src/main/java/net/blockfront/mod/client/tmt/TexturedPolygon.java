/*     */ package net.blockfront.mod.client.tmt;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class TexturedPolygon
/*     */ {
/*     */   public PositionTextureVertex[] vertexPositions;
/*     */   public int nVertices;
/*     */   
/*     */   public TexturedPolygon(PositionTextureVertex[] apositionTexturevertex) {
/*  12 */     this.invertNormal = false;
/*  13 */     this.vertexPositions = apositionTexturevertex;
/*  14 */     this.nVertices = apositionTexturevertex.length;
/*  15 */     this.iNormals = new ArrayList<>();
/*  16 */     this.normals = new float[0];
/*     */   }
/*     */   private boolean invertNormal; private float[] normals; private ArrayList<Vec3> iNormals;
/*     */   
/*     */   public TexturedPolygon(PositionTextureVertex[] apositionTexturevertex, int par2, int par3, int par4, int par5, float par6, float par7) {
/*  21 */     this(apositionTexturevertex);
/*  22 */     float var8 = 0.0F / par6;
/*  23 */     float var9 = 0.0F / par7;
/*  24 */     apositionTexturevertex[0] = apositionTexturevertex[0].setTexturePosition(par4 / par6 - var8, par3 / par7 + var9);
/*  25 */     apositionTexturevertex[1] = apositionTexturevertex[1].setTexturePosition(par2 / par6 + var8, par3 / par7 + var9);
/*  26 */     apositionTexturevertex[2] = apositionTexturevertex[2].setTexturePosition(par2 / par6 + var8, par5 / par7 - var9);
/*  27 */     apositionTexturevertex[3] = apositionTexturevertex[3].setTexturePosition(par4 / par6 - var8, par5 / par7 - var9);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvertNormal(boolean isSet) {
/*  32 */     this.invertNormal = isSet;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNormals(float x, float y, float z) {
/*  37 */     this.normals = new float[] { x, y, z };
/*     */   }
/*     */ 
/*     */   
/*     */   public void flipFace() {
/*  42 */     PositionTextureVertex[] var1 = new PositionTextureVertex[this.vertexPositions.length];
/*     */     
/*  44 */     for (int var2 = 0; var2 < this.vertexPositions.length; var2++)
/*     */     {
/*  46 */       var1[var2] = this.vertexPositions[this.vertexPositions.length - var2 - 1];
/*     */     }
/*     */     
/*  49 */     this.vertexPositions = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNormals(ArrayList<Vec3> vec) {
/*  54 */     this.iNormals = vec;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(TmtTessellator tessellator, float f) {
/*  60 */     if (this.nVertices == 3) {
/*  61 */       tessellator.startDrawing(4);
/*  62 */     } else if (this.nVertices == 4) {
/*  63 */       tessellator.startDrawingQuads();
/*     */     } else {
/*  65 */       tessellator.startDrawing(9);
/*     */     } 
/*  67 */     if (this.iNormals.size() == 0)
/*     */     {
/*  69 */       if (this.normals.length == 3) {
/*     */         
/*  71 */         if (this.invertNormal) {
/*     */           
/*  73 */           tessellator.setNormal(-this.normals[0], -this.normals[1], -this.normals[2]);
/*     */         } else {
/*     */           
/*  76 */           tessellator.setNormal(this.normals[0], this.normals[1], this.normals[2]);
/*     */         }
/*     */       
/*  79 */       } else if (this.vertexPositions.length >= 3) {
/*     */         
/*  81 */         Vec3 Vec3 = (this.vertexPositions[1]).vector3D.subtract((this.vertexPositions[0]).vector3D);
/*  82 */         Vec3 Vec31 = (this.vertexPositions[1]).vector3D.subtract((this.vertexPositions[2]).vector3D);
/*  83 */         Vec3 Vec32 = Vec31.crossProduct(Vec3).normalize();
/*     */         
/*  85 */         if (this.invertNormal) {
/*     */           
/*  87 */           tessellator.setNormal(-((float)Vec32.xCoord), -((float)Vec32.yCoord), -((float)Vec32.zCoord));
/*     */         } else {
/*     */           
/*  90 */           tessellator.setNormal((float)Vec32.xCoord, (float)Vec32.yCoord, (float)Vec32.zCoord);
/*     */         } 
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  98 */     for (int i = 0; i < this.nVertices; i++) {
/*     */       
/* 100 */       PositionTextureVertex positionTexturevertex = this.vertexPositions[i];
/* 101 */       if (positionTexturevertex instanceof PositionTransformVertex)
/* 102 */         ((PositionTransformVertex)positionTexturevertex).setTransformation(); 
/* 103 */       if (i < this.iNormals.size())
/*     */       {
/* 105 */         if (this.invertNormal) {
/*     */           
/* 107 */           tessellator.setNormal(-((float)((Vec3)this.iNormals.get(i)).xCoord), -((float)((Vec3)this.iNormals.get(i)).yCoord), -((float)((Vec3)this.iNormals.get(i)).zCoord));
/*     */         }
/*     */         else {
/*     */           
/* 111 */           tessellator.setNormal((float)((Vec3)this.iNormals.get(i)).xCoord, (float)((Vec3)this.iNormals.get(i)).yCoord, (float)((Vec3)this.iNormals.get(i)).zCoord);
/*     */         } 
/*     */       }
/* 114 */       tessellator.addVertexWithUVW(((float)positionTexturevertex.vector3D.xCoord * f), ((float)positionTexturevertex.vector3D.yCoord * f), ((float)positionTexturevertex.vector3D.zCoord * f), positionTexturevertex.texturePositionX, positionTexturevertex.texturePositionY, positionTexturevertex.texturePositionW);
/*     */     } 
/*     */     
/* 117 */     tessellator.draw();
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\TexturedPolygon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */