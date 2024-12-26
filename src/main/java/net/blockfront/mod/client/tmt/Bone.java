/*     */ package net.blockfront.mod.client.tmt;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Bone
/*     */ {
/*     */   protected Angle3D neutralAngles;
/*     */   public Angle3D relativeAngles;
/*     */   protected Angle3D absoluteAngles;
/*     */   private Vec3 positionVector;
/*     */   private float length;
/*     */   private Bone parentNode;
/*     */   protected ArrayList<Bone> childNodes;
/*     */   private ArrayList<ModelRenderer> models;
/*     */   private Map<ModelRenderer, Angle3D> modelBaseRot;
/*     */   private float offsetX;
/*     */   private float offsetY;
/*     */   private float offsetZ;
/*     */   
/*     */   public Bone(float x, float y, float z, float l) {
/* 111 */     this.neutralAngles = new Angle3D(x, y, z);
/* 112 */     this.relativeAngles = new Angle3D(0.0F, 0.0F, 0.0F);
/* 113 */     this.absoluteAngles = new Angle3D(0.0F, 0.0F, 0.0F);
/* 114 */     this.positionVector = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
/* 115 */     this.length = l;
/* 116 */     this.childNodes = new ArrayList<>();
/* 117 */     this.models = new ArrayList<>();
/* 118 */     this.modelBaseRot = new HashMap<>();
/* 119 */     this.parentNode = null;
/* 120 */     this.offsetX = 0.0F;
/* 121 */     this.offsetY = 0.0F;
/* 122 */     this.offsetZ = 0.0F;
/* 123 */     this.positionVector = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bone(float xOrig, float yOrig, float zOrig, float xRot, float yRot, float zRot, float l) {
/* 138 */     this(xRot, yRot, zRot, l);
/* 139 */     this.positionVector = setOffset(xOrig, yOrig, zOrig);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bone(float x, float y, float z, float l, Bone parent) {
/* 153 */     this(x, y, z, l);
/* 154 */     attachBone(parent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detachBone() {
/* 162 */     this.parentNode.childNodes.remove(this);
/* 163 */     this.parentNode = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attachBone(Bone parent) {
/* 173 */     if (this.parentNode != null)
/* 174 */       detachBone(); 
/* 175 */     this.parentNode = parent;
/* 176 */     parent.addChildBone(this);
/* 177 */     this.offsetX = parent.offsetX;
/* 178 */     this.offsetY = parent.offsetY;
/* 179 */     this.offsetZ = parent.offsetZ;
/* 180 */     resetOffset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 setOffset(float x, float y, float z) {
/* 194 */     if (this.parentNode != null) {
/*     */       
/* 196 */       Vec3 vector = this.parentNode.setOffset(x, y, z);
/* 197 */       this.offsetX = (float)vector.xCoord;
/* 198 */       this.offsetY = (float)vector.yCoord;
/* 199 */       this.offsetZ = (float)vector.zCoord;
/* 200 */       return vector;
/*     */     } 
/* 202 */     this.offsetX = x;
/* 203 */     this.offsetY = y;
/* 204 */     this.offsetZ = z;
/* 205 */     resetOffset(true);
/* 206 */     return Vec3.createVectorHelper(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetOffset() {
/* 214 */     resetOffset(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetOffset(boolean doRecursive) {
/* 223 */     if (this.parentNode != null) {
/*     */       
/* 225 */       this.positionVector = Vec3.createVectorHelper(0.0D, 0.0D, this.parentNode.length);
/* 226 */       this.parentNode.setVectorRotations(this.positionVector);
/* 227 */       this.positionVector.xCoord += this.parentNode.positionVector.xCoord;
/* 228 */       this.positionVector.yCoord += this.parentNode.positionVector.yCoord;
/* 229 */       this.positionVector.zCoord += this.parentNode.positionVector.zCoord;
/*     */     } 
/* 231 */     if (doRecursive && !this.childNodes.isEmpty())
/*     */     {
/* 233 */       for (Bone childNode : this.childNodes) {
/* 234 */         childNode.resetOffset(doRecursive);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNeutralRotation(float x, float y, float z) {
/* 248 */     this.neutralAngles.angleX = x;
/* 249 */     this.neutralAngles.angleY = y;
/* 250 */     this.neutralAngles.angleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bone getRootParent() {
/* 259 */     if (this.parentNode == null) {
/* 260 */       return this;
/*     */     }
/* 262 */     return this.parentNode.getRootParent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addModel(ModelRenderer model) {
/* 272 */     addModel(model, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addModel(ModelRenderer model, boolean inherit) {
/* 284 */     addModel(model, 0.0F, 0.0F, 0.0F, inherit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addModel(ModelRenderer model, boolean inherit, boolean isUpright) {
/* 298 */     addModel(model, 0.0F, 0.0F, 0.0F, inherit, isUpright);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addModel(ModelRenderer model, float x, float y, float z) {
/* 310 */     addModel(model, x, y, z, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addModel(ModelRenderer model, float x, float y, float z, boolean inherit) {
/* 324 */     addModel(model, x, y, z, inherit, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addModel(ModelRenderer model, float x, float y, float z, boolean inherit, boolean isUpright) {
/* 340 */     if (inherit) {
/*     */       
/* 342 */       x += this.neutralAngles.angleX + (isUpright ? 1.5707964F : 0.0F);
/* 343 */       y += this.neutralAngles.angleY;
/* 344 */       z += this.neutralAngles.angleZ;
/*     */     } 
/* 346 */     this.models.add(model);
/* 347 */     this.modelBaseRot.put(model, new Angle3D(x, y, z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeModel(ModelRenderer model) {
/* 358 */     this.models.remove(model);
/* 359 */     this.modelBaseRot.remove(model);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Angle3D getAbsoluteAngle() {
/* 370 */     return new Angle3D(this.absoluteAngles.angleX, this.absoluteAngles.angleY, this.absoluteAngles.angleZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getPosition() {
/* 380 */     return Vec3.createVectorHelper(this.positionVector.xCoord, this.positionVector.yCoord, this.positionVector.zCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addChildBone(Bone bone) {
/* 385 */     this.childNodes.add(bone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void prepareDraw() {
/* 394 */     if (this.parentNode != null) {
/* 395 */       this.parentNode.prepareDraw();
/*     */     } else {
/*     */       
/* 398 */       setAbsoluteRotations();
/* 399 */       setVectors();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotations(float x, float y, float z) {
/* 411 */     this.relativeAngles.angleX = x;
/* 412 */     this.relativeAngles.angleY = y;
/* 413 */     this.relativeAngles.angleZ = z;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setAbsoluteRotations() {
/* 418 */     this.absoluteAngles.angleX = this.relativeAngles.angleX;
/* 419 */     this.absoluteAngles.angleY = this.relativeAngles.angleY;
/* 420 */     this.absoluteAngles.angleZ = this.relativeAngles.angleZ;
/* 421 */     for (Bone childNode : this.childNodes) {
/* 422 */       childNode.setAbsoluteRotations(this.absoluteAngles.angleX, this.absoluteAngles.angleY, this.absoluteAngles.angleZ);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setAbsoluteRotations(float x, float y, float z) {
/* 428 */     this.relativeAngles.angleX += x;
/* 429 */     this.relativeAngles.angleY += y;
/* 430 */     this.relativeAngles.angleZ += z;
/* 431 */     for (Bone childNode : this.childNodes) {
/* 432 */       childNode.setAbsoluteRotations(this.absoluteAngles.angleX, this.absoluteAngles.angleY, this.absoluteAngles.angleZ);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setVectorRotations(Vec3 vector) {
/* 439 */     float x = this.neutralAngles.angleX + this.absoluteAngles.angleX;
/* 440 */     float y = this.neutralAngles.angleY + this.absoluteAngles.angleY;
/* 441 */     float z = this.neutralAngles.angleZ + this.absoluteAngles.angleZ;
/* 442 */     setVectorRotations(vector, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setVectorRotations(Vec3 vector, float xRot, float yRot, float zRot) {
/* 447 */     float x = xRot;
/* 448 */     float y = yRot;
/* 449 */     float z = zRot;
/* 450 */     float xC = MathHelper.cos(x);
/* 451 */     float xS = MathHelper.sin(x);
/* 452 */     float yC = MathHelper.cos(y);
/* 453 */     float yS = MathHelper.sin(y);
/* 454 */     float zC = MathHelper.cos(z);
/* 455 */     float zS = MathHelper.sin(z);
/*     */     
/* 457 */     double xVec = vector.xCoord;
/* 458 */     double yVec = vector.yCoord;
/* 459 */     double zVec = vector.zCoord;
/*     */ 
/*     */     
/* 462 */     double xy = xC * yVec - xS * zVec;
/* 463 */     double xz = xC * zVec + xS * yVec;
/*     */     
/* 465 */     double yz = yC * xz - yS * xVec;
/* 466 */     double yx = yC * xVec + yS * xz;
/*     */     
/* 468 */     double zx = zC * yx - zS * xy;
/* 469 */     double zy = zC * xy + zS * yx;
/*     */     
/* 471 */     xVec = zx;
/* 472 */     yVec = zy;
/* 473 */     zVec = yz;
/*     */     
/* 475 */     vector.xCoord = xVec;
/* 476 */     vector.yCoord = yVec;
/* 477 */     vector.zCoord = zVec;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addVector(Vec3 destVec, Vec3 srcVec) {
/* 482 */     destVec.xCoord += srcVec.xCoord;
/* 483 */     destVec.yCoord += srcVec.yCoord;
/* 484 */     destVec.zCoord += srcVec.zCoord;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setVectors() {
/* 489 */     Vec3 tempVec = Vec3.createVectorHelper(0.0D, 0.0D, this.length);
/* 490 */     this.positionVector = Vec3.createVectorHelper(this.offsetX, this.offsetY, this.offsetZ);
/* 491 */     addVector(tempVec, this.positionVector);
/* 492 */     setVectorRotations(tempVec);
/* 493 */     for (Bone childNode : this.childNodes) {
/* 494 */       childNode.setVectors(tempVec);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setVectors(Vec3 vector) {
/* 500 */     this.positionVector = vector;
/* 501 */     Vec3 tempVec = Vec3.createVectorHelper(0.0D, 0.0D, this.length);
/* 502 */     setVectorRotations(tempVec);
/* 503 */     addVector(tempVec, vector);
/* 504 */     for (Bone childNode : this.childNodes) {
/* 505 */       childNode.setVectors(tempVec);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnglesToModels() {
/* 515 */     for (ModelRenderer currentModel : this.models) {
/* 516 */       Angle3D baseAngles = this.modelBaseRot.get(currentModel);
/* 517 */       currentModel.rotateAngleX = baseAngles.angleX + this.absoluteAngles.angleX;
/* 518 */       currentModel.rotateAngleY = baseAngles.angleY + this.absoluteAngles.angleY;
/* 519 */       currentModel.rotateAngleZ = baseAngles.angleZ + this.absoluteAngles.angleZ;
/* 520 */       currentModel.rotationPointX = (float)this.positionVector.xCoord;
/* 521 */       currentModel.rotationPointY = (float)this.positionVector.yCoord;
/* 522 */       currentModel.rotationPointZ = (float)this.positionVector.zCoord;
/*     */     } 
/*     */     
/* 525 */     for (Bone childNode : this.childNodes)
/* 526 */       childNode.setAnglesToModels(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\Bone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */