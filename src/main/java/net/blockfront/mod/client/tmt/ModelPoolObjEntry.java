/*     */ package net.blockfront.mod.client.tmt;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.util.ArrayList;

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
/*     */ public class ModelPoolObjEntry
/*     */   extends ModelPoolEntry
/*     */ {
/*     */   public void getModel(File file) {
/*     */     try {
/*  23 */       BufferedReader in = new BufferedReader(new FileReader(file));
/*     */ 
/*     */ 
/*     */       
/*  27 */       ArrayList<PositionTransformVertex> verts = new ArrayList<>();
/*  28 */       ArrayList<float[]> uvs = (ArrayList)new ArrayList<>();
/*  29 */       ArrayList<float[]> normals = (ArrayList)new ArrayList<>();
/*  30 */       ArrayList<TexturedPolygon> face = new ArrayList<>();
/*     */       String s;
/*  32 */       while ((s = in.readLine()) != null) {
/*     */         
/*  34 */         if (s.contains("#"))
/*     */         {
/*  36 */           s = s.substring(0, s.indexOf("#"));
/*     */         }
/*     */         
/*  39 */         s = s.trim();
/*     */         
/*  41 */         if (s.equals("")) {
/*     */           continue;
/*     */         }
/*  44 */         if (s.startsWith("g ")) {
/*     */           
/*  46 */           setTextureGroup(s.substring(s.indexOf(" ") + 1).trim());
/*     */           continue;
/*     */         } 
/*  49 */         if (s.startsWith("v ")) {
/*     */           
/*  51 */           s = s.substring(s.indexOf(" ") + 1).trim();
/*  52 */           float[] v = new float[3];
/*  53 */           for (int j = 0; j < 3; j++) {
/*     */             
/*  55 */             int ind = s.indexOf(" ");
/*  56 */             if (ind > -1) {
/*  57 */               v[j] = Float.parseFloat(s.substring(0, ind));
/*     */             } else {
/*  59 */               v[j] = Float.parseFloat(s.substring(0));
/*  60 */             }  s = s.substring(s.indexOf(" ") + 1).trim();
/*     */           } 
/*     */           
/*  63 */           float flt = v[2];
/*  64 */           v[2] = -v[1];
/*  65 */           v[1] = flt;
/*     */           
/*  67 */           verts.add(new PositionTransformVertex(v[0], v[1], v[2], 0.0F, 0.0F));
/*     */           continue;
/*     */         } 
/*  70 */         if (s.startsWith("vt ")) {
/*     */           
/*  72 */           s = s.substring(s.indexOf(" ") + 1).trim();
/*  73 */           float[] v = new float[2];
/*  74 */           for (int j = 0; j < 2; j++) {
/*     */             
/*  76 */             int ind = s.indexOf(" ");
/*  77 */             if (ind > -1) {
/*  78 */               v[j] = Float.parseFloat(s.substring(0, ind));
/*     */             } else {
/*  80 */               v[j] = Float.parseFloat(s.substring(0));
/*  81 */             }  s = s.substring(s.indexOf(" ") + 1).trim();
/*     */           } 
/*     */           
/*  84 */           uvs.add(new float[] { v[0], 1.0F - v[1] });
/*     */           continue;
/*     */         } 
/*  87 */         if (s.startsWith("vn ")) {
/*     */           
/*  89 */           s = s.substring(s.indexOf(" ") + 1).trim();
/*  90 */           float[] v = new float[3];
/*  91 */           for (int j = 0; j < 3; j++) {
/*     */             
/*  93 */             int ind = s.indexOf(" ");
/*  94 */             if (ind > -1) {
/*  95 */               v[j] = Float.parseFloat(s.substring(0, ind));
/*     */             } else {
/*  97 */               v[j] = Float.parseFloat(s.substring(0));
/*  98 */             }  s = s.substring(s.indexOf(" ") + 1).trim();
/*     */           } 
/*     */           
/* 101 */           float flt = v[2];
/* 102 */           v[2] = v[1];
/* 103 */           v[1] = flt;
/*     */           
/* 105 */           normals.add(new float[] { v[0], v[1], v[2] });
/*     */           continue;
/*     */         } 
/* 108 */         if (s.startsWith("f ")) {
/*     */           
/* 110 */           s = s.substring(s.indexOf(" ") + 1).trim();
/* 111 */           ArrayList<PositionTextureVertex> v = new ArrayList<>();
/*     */           
/* 113 */           int finalPhase = 0;
/* 114 */           float[] normal = { 0.0F, 0.0F, 0.0F };
/* 115 */           ArrayList<Vec3> iNormal = new ArrayList<>();
/*     */           
/*     */           do {
/*     */             int vInt;
/*     */             
/*     */             float[] curUV, curNormals;
/* 121 */             int ind = s.indexOf(" ");
/* 122 */             String s1 = s;
/* 123 */             if (ind > -1)
/* 124 */               s1 = s.substring(0, ind); 
/* 125 */             if (s1.contains("/")) {
/*     */               
/* 127 */               String[] f = s1.split("/");
/* 128 */               vInt = Integer.parseInt(f[0]) - 1;
/* 129 */               if (f[1].equals(""))
/* 130 */                 f[1] = f[0]; 
/* 131 */               int vtInt = Integer.parseInt(f[1]) - 1;
/* 132 */               if (uvs.size() > vtInt) {
/* 133 */                 curUV = uvs.get(vtInt);
/*     */               } else {
/* 135 */                 curUV = new float[] { 0.0F, 0.0F };
/* 136 */               }  int vnInt = 0;
/* 137 */               if (f.length == 3) {
/*     */                 
/* 139 */                 if (f[2].equals(""))
/* 140 */                   f[2] = f[0]; 
/* 141 */                 vnInt = Integer.parseInt(f[2]) - 1;
/*     */               } else {
/*     */                 
/* 144 */                 vnInt = Integer.parseInt(f[0]) - 1;
/* 145 */               }  if (normals.size() > vnInt) {
/* 146 */                 curNormals = normals.get(vnInt);
/*     */               } else {
/* 148 */                 curNormals = new float[] { 0.0F, 0.0F, 0.0F };
/*     */               } 
/*     */             } else {
/*     */               
/* 152 */               vInt = Integer.parseInt(s1) - 1;
/* 153 */               if (uvs.size() > vInt) {
/* 154 */                 curUV = uvs.get(vInt);
/*     */               } else {
/* 156 */                 curUV = new float[] { 0.0F, 0.0F };
/* 157 */               }  if (normals.size() > vInt) {
/* 158 */                 curNormals = normals.get(vInt);
/*     */               } else {
/* 160 */                 curNormals = new float[] { 0.0F, 0.0F, 0.0F };
/*     */               } 
/*     */             } 
/* 163 */             iNormal.add(Vec3.createVectorHelper(curNormals[0], curNormals[1], curNormals[2]));
/*     */             
/* 165 */             normal[0] = normal[0] + curNormals[0];
/* 166 */             normal[1] = normal[1] + curNormals[1];
/* 167 */             normal[2] = normal[2] + curNormals[2];
/*     */             
/* 169 */             if (vInt < verts.size()) {
/*     */               
/* 171 */               v.add(((PositionTransformVertex)verts.get(vInt)).setTexturePosition(curUV[0], curUV[1]));
/* 172 */               if (verts.get(vInt) instanceof PositionTransformVertex)
/*     */               {
/* 174 */                 ((PositionTransformVertex)verts.get(vInt)).addGroup(this.group);
/*     */               }
/*     */             } 
/* 177 */             if (ind > -1)
/* 178 */             { s = s.substring(s.indexOf(" ") + 1).trim(); }
/*     */             else
/* 180 */             { finalPhase++; } 
/* 181 */           } while (finalPhase < 1);
/*     */           
/* 183 */           float d = MathHelper.sqrt_double((normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]));
/*     */           
/* 185 */           normal[0] = normal[0] / d;
/* 186 */           normal[1] = normal[1] / d;
/* 187 */           normal[2] = normal[2] / d;
/*     */           
/* 189 */           PositionTextureVertex[] vToArr = new PositionTextureVertex[v.size()];
/*     */           
/* 191 */           for (int j = 0; j < v.size(); j++)
/*     */           {
/* 193 */             vToArr[j] = v.get(j);
/*     */           }
/*     */           
/* 196 */           TexturedPolygon poly = new TexturedPolygon(vToArr);
/* 197 */           poly.setNormals(normal[0], normal[1], normal[2]);
/* 198 */           poly.setNormals(iNormal);
/*     */           
/* 200 */           face.add(poly);
/* 201 */           this.texture.addPoly(poly);
/*     */         } 
/*     */       } 
/*     */       
/* 205 */       this.vertices = new PositionTransformVertex[verts.size()]; int i;
/* 206 */       for (i = 0; i < verts.size(); i++)
/*     */       {
/* 208 */         this.vertices[i] = verts.get(i);
/*     */       }
/* 210 */       this.faces = new TexturedPolygon[face.size()];
/* 211 */       for (i = 0; i < face.size(); i++)
/*     */       {
/* 213 */         this.faces[i] = face.get(i);
/*     */       }
/* 215 */       in.close();
/*     */     }
/* 217 */     catch (Throwable throwable) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\tmt\ModelPoolObjEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */