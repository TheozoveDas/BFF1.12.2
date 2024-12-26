/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Buttons
/*    */ {
/*    */   public static void draw(List<GuiButton> buttons, int mouseX, int mouseY) {
/* 15 */     Minecraft mc = Minecraft.getMinecraft();
/* 16 */     for (GuiButton button : buttons) {
/* 17 */       button.drawButton(mc, mouseX, mouseY, 0);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void click(List<GuiButton> buttons, int mouseX, int mouseY, Consumer<GuiButton> clickHandler) {
/* 22 */     Minecraft mc = Minecraft.getMinecraft();
/* 23 */     for (GuiButton button : buttons) {
/* 24 */       if (button.mousePressed(mc, mouseX, mouseY)) {
/* 25 */         button.func_146113_a(mc.getSoundHandler());
/* 26 */         clickHandler.accept(button);
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\Buttons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */