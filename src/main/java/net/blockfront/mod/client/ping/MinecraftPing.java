/*    */ package net.blockfront.mod.client.ping;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;

import net.minecraft.util.text.ITextComponent;

/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.net.InetSocketAddress;
/*    */ import java.net.Socket;
/*    */ 
/*    */ 
/*    */ public class MinecraftPing
/*    */ {
/*    */   public MinecraftPingReply getPing(String hostname) throws IOException {
/* 17 */     return getPing((new MinecraftPingOptions()).setHostname(hostname));
/*    */   }
/*    */   
/*    */   public MinecraftPingReply getPing(String hostname, int port) throws IOException {
/* 21 */     return getPing((new MinecraftPingOptions()).setHostname(hostname).setPort(port));
/*    */   }
/*    */   
/*    */   public MinecraftPingReply getPing(MinecraftPingOptions options) throws IOException {
/* 25 */     MinecraftPingUtil.validate(options.getHostname(), "Hostname cannot be null.");
/* 26 */     MinecraftPingUtil.validate(Integer.valueOf(options.getPort()), "Port cannot be null.");
/*    */     
/* 28 */     Socket socket = new Socket();
/* 29 */     socket.connect(new InetSocketAddress(options.getHostname(), options.getPort()), options.getTimeout());
/*    */     
/* 31 */     DataInputStream in = new DataInputStream(socket.getInputStream());
/* 32 */     DataOutputStream out = new DataOutputStream(socket.getOutputStream());
/*    */ 
/*    */ 
/*    */     
/* 36 */     ByteArrayOutputStream handshake_bytes = new ByteArrayOutputStream();
/* 37 */     DataOutputStream handshake = new DataOutputStream(handshake_bytes);
/*    */     
/* 39 */     handshake.writeByte(MinecraftPingUtil.PACKET_HANDSHAKE);
/* 40 */     MinecraftPingUtil.writeVarInt(handshake, MinecraftPingUtil.PROTOCOL_VERSION);
/* 41 */     MinecraftPingUtil.writeVarInt(handshake, options.getHostname().length());
/* 42 */     handshake.writeBytes(options.getHostname());
/* 43 */     handshake.writeShort(options.getPort());
/* 44 */     MinecraftPingUtil.writeVarInt(handshake, MinecraftPingUtil.STATUS_HANDSHAKE);
/*    */     
/* 46 */     MinecraftPingUtil.writeVarInt(out, handshake_bytes.size());
/* 47 */     out.write(handshake_bytes.toByteArray());
/*    */ 
/*    */ 
/*    */     
/* 51 */     out.writeByte(1);
/* 52 */     out.writeByte(MinecraftPingUtil.PACKET_STATUSREQUEST);
/*    */ 
/*    */ 
/*    */     
/* 56 */     MinecraftPingUtil.readVarInt(in);
/* 57 */     int id = MinecraftPingUtil.readVarInt(in);
/*    */     
/* 59 */     MinecraftPingUtil.io((id == -1), "Server prematurely ended stream.");
/* 60 */     MinecraftPingUtil.io((id != MinecraftPingUtil.PACKET_STATUSREQUEST), "Server returned invalid packet.");
/*    */     
/* 62 */     int length = MinecraftPingUtil.readVarInt(in);
/* 63 */     MinecraftPingUtil.io((length == -1), "Server prematurely ended stream.");
/* 64 */     MinecraftPingUtil.io((length == 0), "Server returned unexpected value.");
/*    */     
/* 66 */     byte[] data = new byte[length];
/* 67 */     in.readFully(data);
/* 68 */     String json = new String(data, options.getCharset());
/*    */ 
/*    */ 
/*    */     
/* 72 */     out.writeByte(9);
/* 73 */     out.writeByte(MinecraftPingUtil.PACKET_PING);
/* 74 */     out.writeLong(System.currentTimeMillis());
/*    */ 
/*    */ 
/*    */     
/* 78 */     MinecraftPingUtil.readVarInt(in);
/* 79 */     id = MinecraftPingUtil.readVarInt(in);
/* 80 */     MinecraftPingUtil.io((id == -1), "Server prematurely ended stream.");
/* 81 */     MinecraftPingUtil.io((id != MinecraftPingUtil.PACKET_PING), "Server returned invalid packet.");
/*    */ 
/*    */ 
/*    */     
/* 85 */     handshake.close();
/* 86 */     handshake_bytes.close();
/* 87 */     out.close();
/* 88 */     in.close();
/* 89 */     socket.close();
/*    */     
/* 91 */     return (MinecraftPingReply)GSON.fromJson(json, MinecraftPingReply.class);
/*    */   }
/*    */   
/* 94 */   private static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(ITextComponent.class, new ITextComponent.Serializer()).create();
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\ping\MinecraftPing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */