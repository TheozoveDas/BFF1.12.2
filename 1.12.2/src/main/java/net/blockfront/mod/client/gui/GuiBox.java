/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiBox
/*    */ {
/*    */   protected int posX;
/*    */   protected int posY;
/*    */   protected int width;
/*    */   protected int height;
/*    */   protected GuiCustomScreen parentGUI;
/* 18 */   protected Color color = new Color(47, 47, 47);
/*    */   
/* 20 */   private final List<GuiButton> buttons = new ArrayList<>();
/*    */   
/*    */   public GuiBox(int posX, int posY, int width, int height, GuiCustomScreen parentGUI) {
/* 23 */     this.posX = posX;
/* 24 */     this.posY = posY;
/* 25 */     this.width = width;
/* 26 */     this.height = height;
/* 27 */     this.parentGUI = parentGUI;
/*    */   }
/*    */   
/*    */   public void initGui() {
/* 31 */     this.buttons.clear();
/*    */   }
/*    */   
/*    */   public void actionPerformed(GuiButton button) {}
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 37 */     if (mouseButton == 0) {
/* 38 */       Buttons.click(this.buttons, mouseX, mouseY, this::actionPerformed);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY) {}
/*    */ 
/*    */   
/*    */   public void handleScroll(int mouseX, int mouseY, int dWheel) {}
/*    */ 
/*    */   
/*    */   public void updateScreen() {}
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 53 */     drawBackgroundPlayerinfo();
/*    */     
/* 55 */     drawBackgroundClanMembers();
/*    */     
/* 57 */     drawBackgroundNone();
/*    */     
/* 59 */     Buttons.draw(this.buttons, mouseX, mouseY);
/*    */   }
/*    */   
/*    */   public void drawBackgroundPlayerinfo() {
/* 63 */     GuiUtils.drawImage(this.posX, this.posY, new ResourceLocation("bff:gui/playerinfo.png"), this.width, this.height);
/*    */   }
/*    */   
/*    */   public void drawBackgroundClanMembers() {
/* 67 */     GuiUtils.drawImage(this.posX, this.posY, new ResourceLocation("bff:gui/container.png"), this.width, this.height);
/*    */   }
/*    */   
/*    */   public void drawBackgroundNone() {
/* 71 */     GuiUtils.drawImage(this.posX, this.posY, new ResourceLocation("bff:gui/container.png"), this.width, this.height);
/*    */   }
/*    */   
/*    */   public void onClose() {}
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */