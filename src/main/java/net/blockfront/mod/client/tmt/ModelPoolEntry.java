/*     */ package net.blockfront.mod.client.tmt;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class ModelPoolEntry {
/*     */   public String name;
/*     */   
/*     */   public File checkValidPath(String path) {
/*  13 */     File file = null;
/*     */     
/*  15 */     for (int index = 0; index < this.fileExtensions.length && (file == null || !file.exists()); index++) {
/*     */       
/*  17 */       String absPath = path;
/*     */       
/*  19 */       if (!path.endsWith("." + this.fileExtensions[index])) {
/*  20 */         absPath = absPath + "." + this.fileExtensions[index];
/*     */       }
/*  22 */       file = new File(absPath);
/*     */     } 
/*  24 */     if (file == null || !file.exists())
/*  25 */       return null; 
/*  26 */     return file;
/*     */   }
/*     */   public PositionTransformVertex[] vertices;
/*     */   public TexturedPolygon[] faces;
/*     */   public Map<String, TransformGroupBone> groups;
/*     */   public Map<String, TextureGroup> textures;
/*     */   protected TransformGroupBone group;
/*     */   protected TextureGroup texture;
/*     */   protected String[] fileExtensions;
/*     */   
/*     */   public abstract void getModel(File paramFile);
/*     */   
/*     */   protected void setGroup(String groupName) {
/*  39 */     setGroup(groupName, new Bone(0.0F, 0.0F, 0.0F, 0.0F), 1.0D);
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
/*     */   protected void setGroup(String groupName, Bone bone, double weight) {
/*  52 */     if (this.groups.size() == 0 || !this.groups.containsKey(groupName))
/*  53 */       this.groups.put(groupName, new TransformGroupBone(bone, weight)); 
/*  54 */     this.group = this.groups.get(groupName);
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
/*     */   protected void setTextureGroup(String groupName) {
/*  68 */     if (this.textures.size() == 0 || !this.textures.containsKey(groupName))
/*     */     {
/*  70 */       this.textures.put(groupName, new TextureGroup());
/*     */     }
/*  72 */     this.texture = this.textures.get(groupName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyGroups(Map<String, TransformGroup> groupsMap, Map<String, TextureGroup> texturesMap) {
/*  77 */     Set<String> groupsCol = this.groups.keySet();
/*  78 */     Collection<String> texturesCol = this.textures.keySet();
/*     */     
/*  80 */     Iterator<String> groupsItr = groupsCol.iterator();
/*  81 */     Iterator<String> texturesItr = texturesCol.iterator();
/*     */     
/*  83 */     while (groupsItr.hasNext()) {
/*     */       
/*  85 */       int nameIdx = 0;
/*  86 */       String groupKey = groupsItr.next();
/*  87 */       String currentGroup = this.name + "_" + nameIdx + ":" + groupKey;
/*  88 */       while (groupsMap.size() > 0 && groupsMap.containsKey(currentGroup)) {
/*     */         
/*  90 */         nameIdx++;
/*  91 */         currentGroup = this.name + "_" + nameIdx + ":" + groupKey;
/*     */       } 
/*  93 */       groupsMap.put(currentGroup, this.groups.get(groupKey));
/*     */     } 
/*     */     
/*  96 */     while (texturesItr.hasNext()) {
/*     */       
/*  98 */       int nameIdx = 0;
/*  99 */       String groupKey = texturesItr.next();
/* 100 */       String currentGroup = this.name + "_" + nameIdx + ":" + groupKey;
/* 101 */       while (groupsMap.size() > 0 && texturesMap.containsKey(currentGroup)) {
/*     */         
/* 103 */         nameIdx++;
/* 104 */         currentGroup = this.name + "_" + nameIdx + ":" + groupKey;
/*     */       } 
/* 106 */       texturesMap.put(currentGroup, this.textures.get(groupKey));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\ModelPoolEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */