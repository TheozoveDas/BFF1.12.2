/*    */ package net.blockfront.mod.client.clan;
/*    */ 
/*    */ import com.google.common.base.Objects;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ApiError
/*    */ {
/*    */   public String text;
/*    */   public String type;
/*    */   public Map<String, String> data;
/*    */   
/*    */   public String toString() {
/* 18 */     return Objects.toStringHelper(this)
/* 19 */       .add("text", this.text)
/* 20 */       .add("type", this.type)
/* 21 */       .add("data", this.data)
/* 22 */       .toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\clan\ApiError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */