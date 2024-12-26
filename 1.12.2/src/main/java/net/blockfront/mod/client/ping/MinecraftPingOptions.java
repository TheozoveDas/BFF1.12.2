/*    */ package net.blockfront.mod.client.ping;
/*    */ 
/*    */ public class MinecraftPingOptions
/*    */ {
/*    */   private String hostname;
/*  6 */   private int port = 25565;
/*  7 */   private int timeout = 2000;
/*  8 */   private String charset = "UTF-8";
/*    */   
/*    */   public MinecraftPingOptions setHostname(String hostname) {
/* 11 */     this.hostname = hostname;
/* 12 */     return this;
/*    */   }
/*    */   
/*    */   public MinecraftPingOptions setPort(int port) {
/* 16 */     this.port = port;
/* 17 */     return this;
/*    */   }
/*    */   
/*    */   public MinecraftPingOptions setTimeout(int timeout) {
/* 21 */     this.timeout = timeout;
/* 22 */     return this;
/*    */   }
/*    */   
/*    */   public MinecraftPingOptions setCharset(String charset) {
/* 26 */     this.charset = charset;
/* 27 */     return this;
/*    */   }
/*    */   
/*    */   public String getHostname() {
/* 31 */     return this.hostname;
/*    */   }
/*    */   
/*    */   public int getPort() {
/* 35 */     return this.port;
/*    */   }
/*    */   
/*    */   public int getTimeout() {
/* 39 */     return this.timeout;
/*    */   }
/*    */   
/*    */   public String getCharset() {
/* 43 */     return this.charset;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\ping\MinecraftPingOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */