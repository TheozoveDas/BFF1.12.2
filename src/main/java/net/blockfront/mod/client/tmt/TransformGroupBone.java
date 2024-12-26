/*     */ package net.blockfront.mod.client.tmt;

import net.minecraft.util.math.MathHelper;

/*     */ 
/*     */ 
/*     */ public class TransformGroupBone
/*     */   extends TransformGroup
/*     */ {
/*     */   protected Angle3D baseAngles;
/*     */   protected Vec3 baseVector;
/*     */   protected Bone attachedBone;
/*     */   protected double weight;
/*     */   
/*     */   public TransformGroupBone(Bone bone, double wght) {
/*  15 */     this.baseVector = bone.getPosition();
/*  16 */     this.baseAngles = bone.getAbsoluteAngle();
/*  17 */     this.attachedBone = bone;
/*  18 */     this.weight = wght;
/*     */   }
/*     */ 
/*     */   
/*     */   public Angle3D getBaseAngles() {
/*  23 */     return this.baseAngles.copy();
/*     */   }
/*     */ 
/*     */   
/*     */   public Angle3D getTransformAngle() {
/*  28 */     Angle3D returnAngle = this.attachedBone.getAbsoluteAngle().copy();
/*  29 */     returnAngle.angleX -= this.baseAngles.angleX;
/*  30 */     returnAngle.angleY -= this.baseAngles.angleY;
/*  31 */     returnAngle.angleZ -= this.baseAngles.angleZ;
/*  32 */     return returnAngle;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 getBaseVector() {
/*  37 */     return Vec3.createVectorHelper(this.baseVector.xCoord, this.baseVector.yCoord, this.baseVector.zCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 getTransformVector() {
/*  42 */     return this.baseVector.subtract(this.attachedBone.getPosition());
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 getCurrentVector() {
/*  47 */     return this.attachedBone.getPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getWeight() {
/*  53 */     return this.weight;
/*     */   }
/*     */ 
/*     */   
/*     */   public void attachBone(Bone bone) {
/*  58 */     this.baseVector = bone.getPosition();
/*  59 */     this.baseAngles = bone.getAbsoluteAngle();
/*  60 */     this.attachedBone = bone;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 doTransformation(PositionTransformVertex vertex) {
/*  66 */     Vec3 vector = Vec3.createVectorHelper(vertex.neutralVector.xCoord, vertex.neutralVector.yCoord, vertex.neutralVector.zCoord);
/*  67 */     vector = getBaseVector().subtract(vector);
/*  68 */     Angle3D angle = getTransformAngle();
/*  69 */     setVectorRotations(vector, angle.angleX, angle.angleY, angle.angleZ);
/*     */     
/*  71 */     return vector;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setVectorRotations(Vec3 vector, float xRot, float yRot, float zRot) {
/*  76 */     float x = xRot;
/*  77 */     float y = yRot;
/*  78 */     float z = zRot;
/*  79 */     float xC = MathHelper.cos(x);
/*  80 */     float xS = MathHelper.sin(x);
/*  81 */     float yC = MathHelper.cos(y);
/*  82 */     float yS = MathHelper.sin(y);
/*  83 */     float zC = MathHelper.cos(z);
/*  84 */     float zS = MathHelper.sin(z);
/*     */     
/*  86 */     double xVec = vector.xCoord;
/*  87 */     double yVec = vector.yCoord;
/*  88 */     double zVec = vector.zCoord;
/*     */ 
/*     */     
/*  91 */     double xy = xC * yVec - xS * zVec;
/*  92 */     double xz = xC * zVec + xS * yVec;
/*     */     
/*  94 */     double yz = yC * xz - yS * xVec;
/*  95 */     double yx = yC * xVec + yS * xz;
/*     */     
/*  97 */     double zx = zC * yx - zS * xy;
/*  98 */     double zy = zC * xy + zS * yx;
/*     */     
/* 100 */     xVec = zx;
/* 101 */     yVec = zy;
/* 102 */     zVec = yz;
/*     */     
/* 104 */     vector.xCoord = xVec;
/* 105 */     vector.yCoord = yVec;
/* 106 */     vector.zCoord = zVec;
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\TransformGroupBone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */