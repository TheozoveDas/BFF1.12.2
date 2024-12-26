/*    */ package net.blockfront.mod;
/*    */ 
/*    */ import net.blockfront.mod.net.MessageBackpackSlot;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface BlockFrontProxy
/*    */ {
/*    */   default void preInit(net.minecraftforge.fml.common.event.FMLPreInitializationEvent event) {}
/*    */   
/*    */   default void init(net.minecraftforge.fml.common.event.FMLInitializationEvent event) {}
/*    */   
/*    */   default void postInit(net.minecraftforge.fml.common.event.FMLPostInitializationEvent event) {}
/*    */   
/*    */   default void handleBackpackSlotMessage(MessageBackpackSlot message) {
/* 22 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\BlockFrontProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */