/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class GuiScrollerSlot
/*    */ {
/*    */   int index;
/*    */   public GuiScroller scroller;
/*    */   public int posX;
/*    */   public int posY;
/* 17 */   protected final List<GuiButton> buttons = new ArrayList<>();
/*    */   
/*    */   final void init(GuiScroller scroller, int index, int posX, int posY) {
/* 20 */     this.scroller = scroller;
/* 21 */     this.index = index;
/* 22 */     this.posX = posX;
/* 23 */     this.posY = posY;
/*    */     
/* 25 */     init();
/*    */   }
/*    */   
/*    */   protected void init() {
/* 29 */     this.buttons.clear();
/*    */   }
/*    */   
/*    */   public GuiButton addButton(GuiButton button) {
/* 33 */     this.buttons.add(button);
/* 34 */     return button;
/*    */   }
/*    */   
/*    */   public abstract boolean canSelect();
/*    */   
/*    */   protected void actionPerformed(GuiButton button) {}
/*    */   
/*    */   protected void clicked(int mouseX, int mouseY) {
/* 42 */     Buttons.click(this.buttons, mouseX, mouseY, this::actionPerformed);
/*    */   }
/*    */   
/*    */   public final boolean isSelected() {
/* 46 */     return this.scroller.isSelected(this);
/*    */   }
/*    */   
/*    */   public boolean isHovered(int mouseX, int mouseY) {
/* 50 */     return GuiUtils.isInBox(this.posX, this.posY, this.scroller.width, height(), mouseX, mouseY);
/*    */   }
/*    */   
/*    */   protected void onClose() {}
/*    */   
/*    */   public void preRenderCallback(int mouseX, int mouseY) {}
/*    */   
/*    */   public void doRender(int mouseX, int mouseY) {
/* 58 */     drawBackground(mouseX, mouseY);
/* 59 */     Buttons.draw(this.buttons, mouseX, mouseY);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderNoClip(int x, int y, int mouseX, int mouseY) {}
/*    */   
/*    */   protected void drawBackground(int mouseX, int mouseY) {
/* 66 */     if (isDisabled()) {
/* 67 */       GuiUtils.drawImage((this.posX + 2), (this.posY + 2), new ResourceLocation("bff:gui/serverslotdisable.png"), 329.0D, 32.0D);
/*    */     }
/* 69 */     else if (isSelected()) {
/* 70 */       GuiUtils.drawImage((this.posX + 2), (this.posY + 2), new ResourceLocation("bff:gui/serverslotselect.png"), 329.0D, 32.0D);
/* 71 */     } else if (isHovered(mouseX, mouseY)) {
/* 72 */       GuiUtils.drawImage((this.posX + 2), (this.posY + 2), new ResourceLocation("bff:gui/serverslothover.png"), 329.0D, 32.0D);
/*    */     } else {
/* 74 */       GuiUtils.drawImage((this.posX + 2), (this.posY + 2), new ResourceLocation("bff:gui/serverslot.png"), 329.0D, 32.0D);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected int paddingLeft() {
/* 80 */     return 2;
/*    */   }
/*    */   
/*    */   protected int paddingRight() {
/* 84 */     return 20;
/*    */   }
/*    */   
/*    */   protected int paddingTop() {
/* 88 */     return 2;
/*    */   }
/*    */   
/*    */   protected int paddingBottom() {
/* 92 */     return 2;
/*    */   }
/*    */   
/*    */   protected boolean isDisabled() {
/* 96 */     return false;
/*    */   }
/*    */   
/*    */   protected abstract int height();
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiScrollerSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */