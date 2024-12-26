/*    */ package net.blockfront.mod.handler;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.blockfront.mod.net.MessageKey;
/*    */ import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFrontKeys
/*    */ {
/*    */   public static KeyBinding keyBackpack;
/* 22 */   private static final Map<KeyBinding, MessageKey.Type> typeMap = new HashMap<>();
/*    */   
/*    */   public static void init() {
/* 25 */     reg(keyBackpack = new KeyBinding("key.BlockFront.backpack.desc", 48, "key.BlockFront.category"), MessageKey.Type.BACKPACK);
/*    */     
/* 27 */     FMLCommonHandler.instance().bus().register(new BlockFrontKeys());
/*    */   }
/*    */   
/*    */   private static void reg(KeyBinding kb, MessageKey.Type type) {
/* 31 */     ClientRegistry.registerKeyBinding(kb);
/* 32 */     typeMap.put(kb, type);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void clientTick(TickEvent.ClientTickEvent event) {
/* 37 */     if (event.phase == TickEvent.Phase.END)
/* 38 */       typeMap.forEach((keyBinding, type) -> {
/*    */             if (keyBinding.isPressed())
/*    */               BlockFront.mainNetworkChannel.sendToServer((IMessage)new MessageKey(type)); 
/*    */           }); 
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\handler\BlockFrontKeys.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */