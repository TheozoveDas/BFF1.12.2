/*    */ package net.blockfront.mod.client.ping;
/*    */ 
/*    */ import java.util.List;
import net.minecraft.util.text.ITextComponent;
/*    */ 
/*    */ 
/*    */ public class MinecraftPingReply
/*    */ {
/*    */   private ITextComponent description;
/*    */   private Players players;
/*    */   private Version version;
/*    */   private String favicon;
/*    */   
/*    */   public ITextComponent getDescription() {
/* 15 */     return this.description;
/*    */   }
/*    */   
/*    */   public Players getPlayers() {
/* 19 */     return this.players;
/*    */   }
/*    */   
/*    */   public Version getVersion() {
/* 23 */     return this.version;
/*    */   }
/*    */   
/*    */   public String getFavicon() {
/* 27 */     return this.favicon;
/*    */   }
/*    */   
/*    */   public static class Players
/*    */   {
/*    */     private int max;
/*    */     
/*    */     public int getMax() {
/* 35 */       return this.max;
/*    */     } private int online; private List<MinecraftPingReply.Player> sample;
/*    */     public int getOnline() {
/* 38 */       return this.online;
/*    */     }
/*    */     public List<MinecraftPingReply.Player> getSample() {
/* 41 */       return this.sample;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Player { private String name;
/*    */     private String id;
/*    */     
/*    */     public String getName() {
/* 49 */       return this.name;
/*    */     }
/*    */     public String getId() {
/* 52 */       return this.id;
/*    */     } }
/*    */   
/*    */   public static class Version {
/*    */     private String name;
/*    */     private int protocol;
/*    */     
/*    */     public String getName() {
/* 60 */       return this.name;
/*    */     }
/*    */     public int getProtocol() {
/* 63 */       return this.protocol;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\ping\MinecraftPingReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */