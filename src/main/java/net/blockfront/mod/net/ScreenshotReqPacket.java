/*    */ package net.blockfront.mod.net;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
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
/*    */ public class ScreenshotReqPacket
/*    */   implements IMessage
/*    */ {
/*    */   public void fromBytes(ByteBuf buf) {}
/*    */   
/*    */   public void toBytes(ByteBuf buf) {}
/*    */   
/* 23 */   public static int doingScreenshot = 0;
/*    */   
/*    */   public static final class Handler
/*    */     implements IMessageHandler<ScreenshotReqPacket, IMessage>
/*    */   {
/*    */     public IMessage onMessage(ScreenshotReqPacket message, MessageContext ctx) {
/* 29 */       (Minecraft.getMinecraft()).gameSettings.hideGUI = false;
/* 30 */       ScreenshotReqPacket.doingScreenshot = 2;
/* 31 */       return null;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\net\ScreenshotReqPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */