/*     */ package net.blockfront.mod.client.tmt;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class Shape2D
/*     */ {
/*     */   public ArrayList<Coord2D> coords;
/*     */   
/*     */   public Shape2D() {
/*  13 */     this.coords = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public Shape2D(Coord2D[] coordArray) {
/*  18 */     this.coords = new ArrayList<>();
/*     */     
/*  20 */     Collections.addAll(this.coords, coordArray);
/*     */   }
/*     */ 
/*     */   
/*     */   public Shape2D(ArrayList<Coord2D> coordList) {
/*  25 */     this.coords = coordList;
/*     */   }
/*     */ 
/*     */   
/*     */   public Coord2D[] getCoordArray() {
/*  30 */     return (Coord2D[])this.coords.toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public Shape3D extrude(float x, float y, float z, float rotX, float rotY, float rotZ, float depth, int u, int v, float textureWidth, float textureHeight, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, float[] faceLengths) {
/*  35 */     PositionTransformVertex[] verts = new PositionTransformVertex[this.coords.size() * 2];
/*  36 */     PositionTransformVertex[] vertsTop = new PositionTransformVertex[this.coords.size()];
/*  37 */     PositionTransformVertex[] vertsBottom = new PositionTransformVertex[this.coords.size()];
/*  38 */     TexturedPolygon[] poly = new TexturedPolygon[this.coords.size() + 2];
/*     */     
/*  40 */     Vec3 extrudeVector = Vec3.createVectorHelper(0.0D, 0.0D, depth);
/*     */     
/*  42 */     setVectorRotations(extrudeVector, rotX, rotY, rotZ);
/*     */     
/*  44 */     if (faceLengths != null && faceLengths.length < this.coords.size()) {
/*  45 */       faceLengths = null;
/*     */     }
/*  47 */     float totalLength = 0.0F;
/*     */     
/*  49 */     for (int idx = 0; idx < this.coords.size(); idx++) {
/*     */       
/*  51 */       Coord2D curCoord = this.coords.get(idx);
/*  52 */       Coord2D nextCoord = this.coords.get((idx + 1) % this.coords.size());
/*  53 */       float texU1 = (curCoord.uCoord + u) / textureWidth;
/*  54 */       float texU2 = (shapeTextureWidth * 2 - curCoord.uCoord + u) / textureWidth;
/*  55 */       float texV = (curCoord.vCoord + v) / textureHeight;
/*     */       
/*  57 */       Vec3 vecCoord = Vec3.createVectorHelper(curCoord.xCoord, curCoord.yCoord, 0.0D);
/*     */       
/*  59 */       setVectorRotations(vecCoord, rotX, rotY, rotZ);
/*     */       
/*  61 */       verts[idx] = new PositionTransformVertex(x + (float)vecCoord.xCoord, y + (float)vecCoord.yCoord, z + (float)vecCoord.zCoord, texU1, texV);
/*     */ 
/*     */ 
/*     */       
/*  65 */       verts[idx + this.coords.size()] = new PositionTransformVertex(x + (float)vecCoord.xCoord - (float)extrudeVector.xCoord, y + (float)vecCoord.yCoord - (float)extrudeVector.yCoord, z + (float)vecCoord.zCoord - (float)extrudeVector.zCoord, texU2, texV);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  70 */       vertsTop[idx] = new PositionTransformVertex(verts[idx]);
/*  71 */       vertsBottom[this.coords.size() - idx - 1] = new PositionTransformVertex(verts[idx + this.coords.size()]);
/*     */       
/*  73 */       if (faceLengths != null) {
/*  74 */         totalLength += faceLengths[idx];
/*     */       } else {
/*  76 */         totalLength = (float)(totalLength + Math.sqrt(Math.pow(curCoord.xCoord - nextCoord.xCoord, 2.0D) + Math.pow(curCoord.yCoord - nextCoord.yCoord, 2.0D)));
/*     */       } 
/*     */     } 
/*  79 */     poly[this.coords.size()] = new TexturedPolygon((PositionTextureVertex[])vertsTop);
/*  80 */     poly[this.coords.size() + 1] = new TexturedPolygon((PositionTextureVertex[])vertsBottom);
/*     */     
/*  82 */     float currentLengthPosition = totalLength;
/*     */     
/*  84 */     for (int i = 0; i < this.coords.size(); i++) {
/*     */       
/*  86 */       Coord2D curCoord = this.coords.get(i);
/*  87 */       Coord2D nextCoord = this.coords.get((i + 1) % this.coords.size());
/*  88 */       float currentLength = (float)Math.sqrt(Math.pow(curCoord.xCoord - nextCoord.xCoord, 2.0D) + Math.pow(curCoord.yCoord - nextCoord.yCoord, 2.0D));
/*  89 */       if (faceLengths != null)
/*  90 */         currentLength = faceLengths[faceLengths.length - i - 1]; 
/*  91 */       float ratioPosition = currentLengthPosition / totalLength;
/*  92 */       float ratioLength = (currentLengthPosition - currentLength) / totalLength;
/*     */       
/*  94 */       float texU1 = (ratioLength * sideTextureWidth + u) / textureWidth;
/*  95 */       float texU2 = (ratioPosition * sideTextureWidth + u) / textureWidth;
/*  96 */       float texV1 = (v + shapeTextureHeight) / textureHeight;
/*  97 */       float texV2 = (v + shapeTextureHeight + sideTextureHeight) / textureHeight;
/*     */       
/*  99 */       PositionTransformVertex[] polySide = new PositionTransformVertex[4];
/*     */       
/* 101 */       polySide[0] = new PositionTransformVertex(verts[i], texU2, texV1);
/* 102 */       polySide[1] = new PositionTransformVertex(verts[this.coords.size() + i], texU2, texV2);
/* 103 */       polySide[2] = new PositionTransformVertex(verts[this.coords.size() + (i + 1) % this.coords.size()], texU1, texV2);
/* 104 */       polySide[3] = new PositionTransformVertex(verts[(i + 1) % this.coords.size()], texU1, texV1);
/*     */       
/* 106 */       poly[i] = new TexturedPolygon((PositionTextureVertex[])polySide);
/* 107 */       currentLengthPosition -= currentLength;
/*     */     } 
/*     */     
/* 110 */     return new Shape3D(verts, poly);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setVectorRotations(Vec3 vector, float xRot, float yRot, float zRot) {
/* 115 */     float x = xRot;
/* 116 */     float y = yRot;
/* 117 */     float z = zRot;
/* 118 */     float xC = MathHelper.cos(x);
/* 119 */     float xS = MathHelper.sin(x);
/* 120 */     float yC = MathHelper.cos(y);
/* 121 */     float yS = MathHelper.sin(y);
/* 122 */     float zC = MathHelper.cos(z);
/* 123 */     float zS = MathHelper.sin(z);
/*     */     
/* 125 */     double xVec = vector.xCoord;
/* 126 */     double yVec = vector.yCoord;
/* 127 */     double zVec = vector.zCoord;
/*     */ 
/*     */     
/* 130 */     double xy = xC * yVec - xS * zVec;
/* 131 */     double xz = xC * zVec + xS * yVec;
/*     */     
/* 133 */     double yz = yC * xz - yS * xVec;
/* 134 */     double yx = yC * xVec + yS * xz;
/*     */     
/* 136 */     double zx = zC * yx - zS * xy;
/* 137 */     double zy = zC * xy + zS * yx;
/*     */     
/* 139 */     xVec = zx;
/* 140 */     yVec = zy;
/* 141 */     zVec = yz;
/*     */     
/* 143 */     vector.xCoord = xVec;
/* 144 */     vector.yCoord = yVec;
/* 145 */     vector.zCoord = zVec;
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\Shape2D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */