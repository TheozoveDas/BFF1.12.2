/*    */ package net.blockfront.mod.client;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StatPacket
/*    */   implements IMessage
/*    */ {
/*    */   private Type type;
/*    */   
/*    */   public StatPacket(Type type) {
/* 17 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public StatPacket() {}
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/* 25 */     this.type = Type.values()[MathHelper.clamp(buf.readUnsignedByte(), 0, 1)];
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 30 */     buf.writeByte(this.type.ordinal());
/*    */   }
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<StatPacket, IMessage>
/*    */   {
/*    */     public IMessage onMessage(StatPacket message, MessageContext ctx) {
/* 37 */       switch (message.type) {
/*    */         case ZOMBIE_KILLED:
/* 39 */           (IngameStats.current()).zombiesKilled++;
/*    */           break;
/*    */         case PLAYER_KILLED:
/* 42 */           (IngameStats.current()).playersKilled++;
/*    */           break;
/*    */       } 
/* 45 */       return null;
/*    */     }
/*    */   }
/*    */   
/*    */   public enum Type
/*    */   {
/* 51 */     ZOMBIE_KILLED,
/* 52 */     PLAYER_KILLED;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\StatPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */