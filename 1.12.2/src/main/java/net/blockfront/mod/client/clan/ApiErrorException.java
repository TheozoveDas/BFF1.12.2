/*    */ package net.blockfront.mod.client.clan;
/*    */ 
/*    */ import java.util.concurrent.CompletionException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApiErrorException
/*    */   extends CompletionException
/*    */ {
/*    */   public final int httpStatus;
/*    */   public final ApiError error;
/*    */   
/*    */   public ApiErrorException(int httpStatus, ApiError error) {
/* 14 */     this.httpStatus = httpStatus;
/* 15 */     this.error = error;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\clan\ApiErrorException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */