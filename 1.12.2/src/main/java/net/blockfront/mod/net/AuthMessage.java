/*    */ package net.blockfront.mod.net;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuthMessage
/*    */   implements IMessage
/*    */ {
/*    */   public UUID accessToken;
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 19 */     long leastSig = buf.readLong();
/* 20 */     long mostSig = buf.readLong();
/* 21 */     this.accessToken = new UUID(mostSig, leastSig);
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 26 */     throw new IllegalStateException();
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<AuthMessage, IMessage>
/*    */   {
/*    */     public IMessage onMessage1(AuthMessage message, MessageContext ctx) {
/* 34 */       return null;
/*    */     }
/*    */

@Override
public IMessage onMessage(AuthMessage message, MessageContext ctx) {
	// TODO Auto-generated method stub
	return null;
}   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\net\AuthMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */