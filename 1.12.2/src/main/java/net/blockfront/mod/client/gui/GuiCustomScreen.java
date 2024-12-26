/*    */ package net.blockfront.mod.client.gui;
import java.io.IOException;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import org.lwjgl.input.Mouse;
/*    */ 
/*    */ public class GuiCustomScreen
/*    */   extends GuiScreen
/*    */ {
/* 12 */   private ArrayList<GuiBox> guiContainers = new ArrayList<>();
/*    */   
/*    */   public void initGui() {
/* 15 */     this.guiContainers.clear();
/*    */   }
/*    */   
/*    */   public GuiButton addButton(GuiButton button) {
/* 19 */     this.buttonList.add(button);
/* 20 */     return button;
/*    */   }
/*    */   
/*    */   protected void actionPerformed(GuiButton button) {
/* 24 */     addContainerAction(button);
/*    */   }
/*    */   
/*    */   public void updateScreen() {
/* 28 */     addContainerUpdate();
/*    */   }
/*    */   
/*    */   public void onGuiClosed() {
/* 32 */     this.guiContainers.forEach(GuiBox::onClose);
/*    */   }
/*    */   
/*    */   public void addContainer(GuiBox container) {
/* 36 */     container.initGui();
/* 37 */     this.guiContainers.add(container);
/*    */   }
/*    */   
/*    */   public void addContainerUpdate() {
/* 41 */     this.guiContainers.forEach(GuiBox::updateScreen);
/*    */   }
/*    */   
/*    */   public void addContainerAction(GuiButton guiButton) {
/* 45 */     for (GuiBox gui : this.guiContainers) {
/* 46 */       gui.actionPerformed(guiButton);
/*    */     }
/*    */   }
/*    */   
/*    */   public void addContainerDrawing(int mouseX, int mouseY, float partialTicks) {
/* 51 */     for (GuiBox gui : this.guiContainers) {
/* 52 */       gui.drawScreen(mouseX, mouseY, partialTicks);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean doesGuiPauseGame() {
/* 57 */     return true;
/*    */   }
/*    */   
/*    */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 61 */     try {
	super.mouseClicked(mouseX, mouseY, mouseButton);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
/* 62 */     for (GuiBox gui : this.guiContainers) {
/* 63 */       gui.mouseClicked(mouseX, mouseY, mouseButton);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void mouseMovedOrUp(int mouseX, int mouseY, int state) {
/* 69 */     super.mouseMovedOrUp(mouseX, mouseY, state);
/*    */     
/* 71 */     if (state >= 0) {
/* 72 */       for (GuiBox container : this.guiContainers) {
/* 73 */         container.mouseReleased(mouseX, mouseY);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void handleMouseInput() {
/* 80 */     try {
	super.handleMouseInput();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
/* 81 */     int dWheel = Mouse.getEventDWheel();
/* 82 */     if (dWheel != 0) {
/* 83 */       int mouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
/* 84 */       int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
/* 85 */       for (GuiBox container : this.guiContainers) {
/* 86 */         container.handleScroll(mouseX, mouseY, dWheel);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 92 */     super.drawScreen(mouseX, mouseY, partialTicks);
/* 93 */     addContainerDrawing(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146283_a(List textLines, int x, int y) {
/* 98 */     super.func_146283_a(textLines, x, y);
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiCustomScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */