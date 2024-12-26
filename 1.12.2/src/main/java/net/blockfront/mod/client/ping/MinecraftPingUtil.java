/*    */ package net.blockfront.mod.client.ping;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class MinecraftPingUtil
/*    */ {
/*  9 */   public static byte PACKET_HANDSHAKE = 0; public static byte PACKET_STATUSREQUEST = 0; public static byte PACKET_PING = 1;
/* 10 */   public static int PROTOCOL_VERSION = 4;
/* 11 */   public static int STATUS_HANDSHAKE = 1;
/*    */   
/*    */   public static void validate(Object o, String m) {
/* 14 */     if (o == null) {
/* 15 */       throw new RuntimeException(m);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void io(boolean b, String m) throws IOException {
/* 20 */     if (b) {
/* 21 */       throw new IOException(m);
/*    */     }
/*    */   }
/*    */   
/*    */   public static int readVarInt(DataInputStream in) throws IOException {
/* 26 */     int k, i = 0;
/* 27 */     int j = 0;
/*    */     do {
/* 29 */       k = in.readByte();
/*    */       
/* 31 */       i |= (k & 0x7F) << j++ * 7;
/*    */       
/* 33 */       if (j > 5) {
/* 34 */         throw new RuntimeException("VarInt too big");
/*    */       }
/* 36 */     } while ((k & 0x80) == 128);
/*    */ 
/*    */ 
/*    */     
/* 40 */     return i;
/*    */   }
/*    */   
/*    */   public static void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
/*    */     while (true) {
/* 45 */       if ((paramInt & 0xFFFFFF80) == 0) {
/* 46 */         out.writeByte(paramInt);
/*    */         
/*    */         return;
/*    */       } 
/* 50 */       out.writeByte(paramInt & 0x7F | 0x80);
/* 51 */       paramInt >>>= 7;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\ping\MinecraftPingUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */