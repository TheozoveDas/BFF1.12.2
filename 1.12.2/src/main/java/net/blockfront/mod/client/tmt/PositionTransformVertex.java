/*    */ package net.blockfront.mod.client.tmt;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class PositionTransformVertex extends PositionTextureVertex {
/*    */   public Vec3 neutralVector;
/*    */   public ArrayList<TransformGroup> transformGroups;
/*    */   
/*    */   public PositionTransformVertex(float x, float y, float z, float u, float v) {
/* 11 */     this(Vec3.createVectorHelper(x, y, z), u, v);
/*    */   }
/*    */   
/*    */   public PositionTransformVertex(PositionTextureVertex vertex, float u, float v)
/*    */   {
/* 16 */     super(vertex, u, v);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 72 */     this.transformGroups = new ArrayList<>(); if (vertex instanceof PositionTransformVertex) { this.neutralVector = ((PositionTransformVertex)vertex).neutralVector; } else { this.neutralVector = Vec3.createVectorHelper(vertex.vector3D.xCoord, vertex.vector3D.yCoord, vertex.vector3D.zCoord); }  } public PositionTransformVertex(Vec3 vector, float u, float v) { super(vector, u, v); this.transformGroups = new ArrayList<>();
/*    */     this.neutralVector = Vec3.createVectorHelper(vector.xCoord, vector.yCoord, vector.zCoord); }
/*    */ 
/*    */   
/*    */   public PositionTransformVertex(PositionTextureVertex vertex) {
/*    */     this(vertex, vertex.texturePositionX, vertex.texturePositionY);
/*    */   }
/*    */   
/*    */   public void setTransformation() {
/*    */     if (this.transformGroups.size() == 0) {
/*    */       this.vector3D.xCoord = this.neutralVector.xCoord;
/*    */       this.vector3D.yCoord = this.neutralVector.yCoord;
/*    */       this.vector3D.zCoord = this.neutralVector.zCoord;
/*    */       return;
/*    */     } 
/*    */     double weight = 0.0D;
/*    */     for (TransformGroup transformGroup : this.transformGroups)
/*    */       weight += transformGroup.getWeight(); 
/*    */     this.vector3D.xCoord = 0.0D;
/*    */     this.vector3D.yCoord = 0.0D;
/*    */     this.vector3D.zCoord = 0.0D;
/*    */     for (TransformGroup group : this.transformGroups) {
/*    */       double cWeight = group.getWeight() / weight;
/*    */       Vec3 vector = group.doTransformation(this);
/*    */       this.vector3D.xCoord += cWeight * vector.xCoord;
/*    */       this.vector3D.yCoord += cWeight * vector.yCoord;
/*    */       this.vector3D.zCoord += cWeight * vector.zCoord;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void addGroup(TransformGroup group) {
/*    */     this.transformGroups.add(group);
/*    */   }
/*    */   
/*    */   public void removeGroup(TransformGroup group) {
/*    */     this.transformGroups.remove(group);
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\PositionTransformVertex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */