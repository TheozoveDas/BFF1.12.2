/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.text.TextFormatting;
/*    */ 
/*    */ 
/*    */ public class GuiMainMenuNews
/*    */   extends GuiCustomMainMenu
/*    */ {
/*    */   public void initGui() {
/* 12 */     super.initGui();
/* 13 */     int x = this.width / 2 - 173;
/* 14 */     addContainer(new GuiBoxGraphicNews(x, 15, 355, 210, this));
/* 15 */     initOverheadMenu();
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(GuiButton button) {
/* 20 */     super.actionPerformed(button);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateScreen() {
/* 25 */     super.updateScreen();
/*    */   }
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 29 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initOverheadMenu() {
/* 35 */     int x = this.width / 2 - 173;
/* 36 */     Color green = new Color(51, 153, 51);
/* 37 */     this.buttonList.add(new GuiCustomPressablePlaynow(1, x + 271, 226, 60, 10, TextFormatting.BOLD + "Proceed", green));
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiMainMenuNews.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */