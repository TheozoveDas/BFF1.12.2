/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.blockfront.mod.client.character.CharacterPlayerFactory;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ public class GuiBoxCharacter
/*    */   extends GuiBox {
/*    */   public GuiBoxCharacter(int posX, int posY, int width, int height, GuiCustomMainMenu parentGUI) {
/* 13 */     super(posX, posY, width, height, parentGUI);
/* 14 */     init();
/*    */   }
/*    */   private static boolean erroredOut = false;
/*    */   private void init() {
/*    */     try {
/* 19 */       if (BlockFront.instance.characterCreator == null) {
/* 20 */         BlockFront.instance.characterCreator = new CharacterPlayerFactory(Minecraft.getMinecraft().getSession().func_148256_e());
/* 21 */         RenderManager.instance.cacheActiveRenderInfo(BlockFront.instance.characterCreator.getWorld(), (Minecraft.getMinecraft()).renderEngine, (Minecraft.getMinecraft()).fontRenderer, (EntityLivingBase)BlockFront.instance.characterCreator.getPlayer(), (Entity)BlockFront.instance.characterCreator.getPlayer(), (Minecraft.getMinecraft()).gameSettings, 0.0F);
/*    */       } 
/* 23 */     } catch (Throwable e) {
/* 24 */       e.printStackTrace();
/* 25 */       erroredOut = true;
/* 26 */       BlockFront.instance.characterCreator.reset();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 31 */     int px = 0;
/* 32 */     int py = 7;
/* 33 */     if (BlockFront.instance.characterCreator.getPlayer() != null && !erroredOut) {
/* 34 */       BlockFront.instance.characterCreator.addPlayerRotation();
/* 35 */       GuiUtils.renderPlayerModel(Minecraft.getMinecraft(), BlockFront.instance.characterCreator.getPlayer(), this.posX + this.width + 2 + px, this.posY + this.height + 260 + py, 220.0F, BlockFront.instance.characterCreator.getPlayerRotation());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiBoxCharacter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */