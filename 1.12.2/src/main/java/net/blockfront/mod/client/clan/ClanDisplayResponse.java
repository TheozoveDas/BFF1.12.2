/*    */ package net.blockfront.mod.client.clan;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClanDisplayResponse
/*    */   extends ApiResponse
/*    */ {
/*    */   public ClanDisplay result;
/*    */   
/*    */   public String toString() {
/* 12 */     StringBuilder sb = new StringBuilder("ClanDisplayResponse{");
/* 13 */     sb.append("result=").append(this.result);
/* 14 */     sb.append('}');
/* 15 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public static class ClanDisplay
/*    */   {
/*    */     public int id;
/*    */     public String name;
/*    */     public String tag;
/*    */     
/*    */     public String toString() {
/* 25 */       StringBuilder sb = new StringBuilder("ClanDisplay{");
/* 26 */       sb.append("id=").append(this.id);
/* 27 */       sb.append(", name='").append(this.name).append('\'');
/* 28 */       sb.append(", tag='").append(this.tag).append('\'');
/* 29 */       sb.append('}');
/* 30 */       return sb.toString();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\clan\ClanDisplayResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */