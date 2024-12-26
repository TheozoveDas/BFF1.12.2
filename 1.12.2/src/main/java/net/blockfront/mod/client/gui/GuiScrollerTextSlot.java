/*    */ package net.blockfront.mod.client.gui;

import net.minecraft.util.text.TextFormatting;

/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiScrollerTextSlot
/*    */   extends GuiScrollerSlot
/*    */ {
/*    */   public static final char COLOR_CHAR = '§';
/*    */   private final String displayText;
/*    */   
/*    */   public GuiScrollerTextSlot(String text) {
/* 14 */     this.displayText = text;
/*    */   }
/*    */   
/*    */   public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
/* 18 */     char[] b = textToTranslate.toCharArray();
/* 19 */     for (int i = 0; i < b.length - 1; i++) {
/* 20 */       if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
/* 21 */         b[i] = '§';
/* 22 */         b[i + 1] = Character.toLowerCase(b[i + 1]);
/*    */       } 
/*    */     } 
/* 25 */     return new String(b);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSelect() {
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRender(int mouseX, int mouseY) {
/* 35 */     GuiUtils.renderTextThemed((isSelected() ? TextFormatting.WHITE : "") + translateAlternateColorCodes('&', this.displayText), this.posX + 2, this.posY + 2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int height() {
/* 40 */     return 11;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiScrollerTextSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */