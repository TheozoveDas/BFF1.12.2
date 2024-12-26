/*    */ package net.blockfront.mod.client.tmt;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;

import net.minecraftforge.fml.common.Loader;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelPool
/*    */ {
/*    */   public static ModelPoolEntry addFile(String file, Class<ModelPoolEntry> modelClass, Map<String, TransformGroup> group, Map<String, TextureGroup> textureGroup) {
/* 13 */     ModelPoolEntry entry = null;
/* 14 */     if (modelMap.containsKey(file)) {
/*    */       
/* 16 */       entry = modelMap.get(file);
/* 17 */       entry.applyGroups(group, textureGroup);
/* 18 */       return entry;
/*    */     } 
/*    */     
/*    */     try {
/* 22 */       entry = modelClass.newInstance();
/*    */     }
/* 24 */     catch (Exception e) {
/*    */       
/* 26 */       System.out.println("A new " + entry.getClass().getName() + " could not be initialized.");
/* 27 */       System.out.println(e.getMessage());
/* 28 */       return null;
/*    */     } 
/* 30 */     File modelFile = null;
/* 31 */     for (int i = 0; i < resourceDir.length && (modelFile == null || !modelFile.exists()); i++) {
/*    */       
/* 33 */       String absPath = (new File(Loader.instance().getConfigDir().getParent(), resourceDir[i])).getAbsolutePath();
/* 34 */       if (!absPath.endsWith("/") || !absPath.endsWith("\\"))
/* 35 */         absPath = absPath + "/"; 
/* 36 */       modelFile = entry.checkValidPath(absPath + file);
/*    */     } 
/* 38 */     if (modelFile == null || !modelFile.exists()) {
/*    */       
/* 40 */       System.out.println("The model with the name " + file + " does not exist.");
/* 41 */       return null;
/*    */     } 
/* 43 */     entry.groups = new HashMap<>();
/* 44 */     entry.textures = new HashMap<>();
/* 45 */     entry.name = file;
/* 46 */     entry.setGroup("0");
/* 47 */     entry.setTextureGroup("0");
/* 48 */     entry.getModel(modelFile);
/* 49 */     entry.applyGroups(group, textureGroup);
/* 50 */     modelMap.put(file, entry);
/* 51 */     return entry;
/*    */   }
/*    */   
/* 54 */   private static Map<String, ModelPoolEntry> modelMap = new HashMap<>();
/* 55 */   private static String[] resourceDir = new String[] { "/resources/models/", "/resources/mod/models/" };
/*    */ 
/*    */ 
/*    */   
/* 59 */   public static final Class OBJ = ModelPoolObjEntry.class;
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\ModelPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */