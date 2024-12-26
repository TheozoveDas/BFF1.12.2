/*    */ package net.blockfront.mod.net;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MessageBackpackSlot
/*    */   implements IMessage
/*    */ {
/*    */   public int entityId;
/*    */   public ItemStack stack;
/*    */   
/*    */   public MessageBackpackSlot() {}
/*    */   
/*    */   public MessageBackpackSlot(int entityId, ItemStack stack) {
/* 23 */     this.entityId = entityId;
/* 24 */     this.stack = stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 29 */     buf.writeInt(this.entityId);
/* 30 */     ByteBufUtils.writeItemStack(buf, this.stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 35 */     this.entityId = buf.readInt();
/* 36 */     this.stack = ByteBufUtils.readItemStack(buf);
/*    */   }
/*    */   
/*    */   public static final class Handler
/*    */     implements IMessageHandler<MessageBackpackSlot, IMessage>
/*    */   {
/*    */     public IMessage onMessage1(MessageBackpackSlot message, MessageContext ctx) {
/* 43 */       BlockFront.proxy.handleBackpackSlotMessage(message);
/* 44 */       return null;
/*    */     }
/*    */

@Override
public IMessage onMessage(MessageBackpackSlot message, MessageContext ctx) {
	// TODO Auto-generated method stub
	return null;
}   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\net\MessageBackpackSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */