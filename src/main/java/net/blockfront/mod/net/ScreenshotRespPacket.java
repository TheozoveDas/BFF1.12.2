/*    */ package net.blockfront.mod.net;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ 
/*    */ public class ScreenshotRespPacket
/*    */   implements IMessage
/*    */ {
/*    */   public static final int MAX_PAYLOAD_SIZE = 32763;
/*    */   private int totalParts;
/*    */   private int partIndex;
/*    */   private ByteBuf bytes;
/*    */   
/*    */   public ScreenshotRespPacket(int totalParts, int partIndex, ByteBuf bytes) {
/* 18 */     this.totalParts = totalParts;
/* 19 */     this.partIndex = partIndex;
/* 20 */     this.bytes = bytes;
/*    */   }
/*    */ 
/*    */   
/*    */   public ScreenshotRespPacket() {}
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 28 */     this.totalParts = buf.readUnsignedByte();
/* 29 */     this.partIndex = buf.readUnsignedByte();
/* 30 */     this.bytes = buf.readBytes(buf.readableBytes());
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 35 */     buf.writeByte(this.totalParts);
/* 36 */     buf.writeByte(this.partIndex);
/* 37 */     buf.writeBytes(this.bytes);
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<ScreenshotRespPacket, IMessage>
/*    */   {
/*    */     public IMessage onMessage(ScreenshotRespPacket msg, MessageContext ctx) {
/* 44 */       throw new IllegalStateException();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\net\ScreenshotRespPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */