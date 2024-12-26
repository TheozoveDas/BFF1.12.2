/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GuiBoxGraphic
/*    */   extends GuiBox
/*    */ {
/*    */   public GuiBoxGraphic(int posX, int posY, int width, int height, GuiCustomScreen parentGUI) {
/*  9 */     super(posX, posY, width, height, parentGUI);
/*    */   }
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 13 */     GuiUtils.drawImage((this.posX + 5 + mouseX / 25), (this.posY + 2 + mouseY / 30), new ResourceLocation("bff:gui/graphic.png"), this.width, this.height);
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiBoxGraphic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */