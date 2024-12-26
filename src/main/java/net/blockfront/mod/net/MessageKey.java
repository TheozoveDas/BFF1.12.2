/*    */ package net.blockfront.mod.net;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MessageKey
/*    */   implements IMessage
/*    */ {
/*    */   private Type type;
/*    */   
/*    */   public MessageKey() {}
/*    */   
/*    */   public MessageKey(Type type) {
/* 33 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 38 */     this.type = Type.values()[buf.readUnsignedByte()];
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 43 */     buf.writeByte(this.type.ordinal());
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<MessageKey, IMessage>
/*    */   {
/*    */     public IMessage onMessage1(MessageKey message, MessageContext ctx) {
/* 50 */       EntityPlayerMP player = (ctx.getServerHandler()).playerEntity;
/* 51 */       switch (message.type) {
/*    */         case BACKPACK:
/* 53 */           player.openGui(BlockFront.instance, 1, player.worldObj, 0, 0, 0);
/*    */           break;
/*    */       } 
/* 56 */       return null;
/*    */     }
/*    */

@Override
public IMessage onMessage(MessageKey message, MessageContext ctx) {
	// TODO Auto-generated method stub
	return null;
}   }
/*    */   
/*    */   public enum Type
/*    */   {
/* 62 */     BACKPACK;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\net\MessageKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */