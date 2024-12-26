/*    */ package net.blockfront.mod.net;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.ByteBufInputStream;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import javax.imageio.ImageIO;
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.blockfront.mod.client.gui.GuiScreenshotView;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ public class ScreenshotDisplayPacket
/*    */   implements IMessage
/*    */ {
/*    */   public static final int MAX_PAYLOAD_SIZE = 2097047;
/*    */   int partIndex;
/*    */   int totalParts;
/*    */   ByteBuf data;
/*    */   
/*    */   public ScreenshotDisplayPacket() {}
/*    */   
/*    */   public ScreenshotDisplayPacket(int partIndex, int totalParts, ByteBuf data) {
/* 31 */     this.partIndex = partIndex;
/* 32 */     this.totalParts = totalParts;
/* 33 */     this.data = data;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 38 */     buf
/* 39 */       .writeByte(this.partIndex)
/* 40 */       .writeByte(this.totalParts)
/* 41 */       .writeBytes(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 46 */     this.partIndex = buf.readUnsignedByte();
/* 47 */     this.totalParts = buf.readUnsignedByte();
/* 48 */     this.data = buf.readBytes(buf.readableBytes());
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<ScreenshotDisplayPacket, IMessage>
/*    */   {
/*    */     private ByteBuf[] data;
/*    */     
/*    */     public IMessage onMessage1(ScreenshotDisplayPacket msg, MessageContext ctx) {
/* 57 */       if (this.data == null) {
/* 58 */         this.data = new ByteBuf[msg.totalParts];
/*    */       }
/* 60 */       this.data[msg.partIndex] = msg.data;
/* 61 */       for (ByteBuf b : this.data) {
/* 62 */         if (b == null) {
/* 63 */           return null;
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/*    */       try {
/* 69 */         BufferedImage image = ImageIO.read((InputStream)new ByteBufInputStream(Unpooled.wrappedBuffer(this.data)));
/*    */         
/* 71 */         this.data = null;
/* 72 */         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiScreenshotView(image));
/* 73 */       } catch (IOException e) {
/* 74 */         (Minecraft.getMinecraft()).thePlayer.addChatMessage((ITextComponent)new ITextComponent("error in receiving screenshot, see log"));
/* 75 */         BlockFront.log.error("failed to received screenshot", e);
/*    */       } 
/*    */       
/* 78 */       return null;
/*    */     }
/*    */
@Override
public IMessage onMessage(ScreenshotDisplayPacket message, MessageContext ctx) {
	// TODO Auto-generated method stub
	return null;
}   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\net\ScreenshotDisplayPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */