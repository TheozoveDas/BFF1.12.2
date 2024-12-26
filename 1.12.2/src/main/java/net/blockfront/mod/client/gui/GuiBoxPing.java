/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ 
/*    */ public class GuiBoxPing
/*    */   extends GuiBox
/*    */ {
/*    */   public GuiBoxPing(int posX, int posY, int width, int height, GuiCustomScreen parentGUI) {
/*  8 */     super(posX, posY, width, height, parentGUI);
/*    */   }
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 12 */     drawBackgroundClanMembers();
/* 13 */     GuiUtils.renderCenteredTextThemed(GuiMainMenuHome.pingState.pingText, this.posX + this.width / 2, this.posY + 2);
/*    */     
/* 15 */     if (mouseX >= this.posX && mouseX <= this.posX + this.width && mouseY >= this.posY && mouseY <= this.posY + this.height && 
/* 16 */       GuiMainMenuHome.pingState != null && !GuiMainMenuHome.pingState.serverTexts.isEmpty())
/* 17 */       this.parentGUI.func_146283_a(GuiMainMenuHome.pingState.serverTexts, mouseX, mouseY); 
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiBoxPing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */