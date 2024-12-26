/*    */ package net.blockfront.mod.client.clan;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MultiClanDataResponse
/*    */   extends ApiResponse
/*    */ {
/*    */   public List<ClanData> result;
/*    */   
/*    */   public Optional<ClanData> getOneClan() {
/* 14 */     return (this.result.size() != 1) ? Optional.<ClanData>empty() : Optional.<ClanData>of(this.result.get(0));
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\clan\MultiClanDataResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */