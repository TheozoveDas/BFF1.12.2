/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiPressableNoBorderRed
/*    */   extends GuiButton
/*    */ {
/*    */   private final int activeColor;
/*    */   
/*    */   public GuiPressableNoBorderRed(int id, int x, int y, int width, int height, String text, int activeColor) {
/* 22 */     super(id, x, y, width, height, text);
/* 23 */     this.activeColor = activeColor;
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawButton(Minecraft mc, int mouseX, int mouseY) {
/* 28 */     if (this.visible) {
/* 29 */       FontRenderer fr = mc.fontRenderer;
/* 30 */       mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
/* 31 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 32 */       this.field_146123_n = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
/*    */ 
/*    */       
/* 35 */       if (this.enabled) {
/* 36 */         int color = this.field_146123_n ? darkenColor(this.activeColor, 20) : this.activeColor;
/*    */       } else {
/* 38 */         int color = 16777215;
/*    */       } 
/*    */       
/* 41 */       GuiUtils.drawRectWithShadow(this.xPosition, this.yPosition, this.width, this.height, Color.BLACK);
/* 42 */       GuiUtils.drawImage(this.xPosition, this.yPosition, new ResourceLocation("bff", "gui/buttonclans.png"), this.width, this.height);
/*    */       
/* 44 */       mouseDragged(mc, mouseX, mouseY);
/*    */       
/* 46 */       int textColor = 14737632;
/*    */       
/* 48 */       if (this.packedFGColour != 0) {
/* 49 */         textColor = this.packedFGColour;
/* 50 */       } else if (!this.enabled) {
/* 51 */         textColor = 10526880;
/* 52 */       } else if (this.field_146123_n) {
/* 53 */         textColor = 16777215;
/*    */       } 
/*    */       
/* 56 */       drawCenteredString(fr, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, textColor);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static int darkenColor(int rgb, int amnt) {
/* 61 */     int r = rgb >>> 16 & 0xFF;
/* 62 */     int g = rgb >>> 8 & 0xFF;
/* 63 */     int b = rgb & 0xFF;
/*    */     
/* 65 */     return clampChannel(r - amnt) << 16 | clampChannel(g - amnt) << 8 | clampChannel(b - amnt);
/*    */   }
/*    */   
/*    */   private static int clampChannel(int c) {
/* 69 */     return MathHelper.clamp(c, 0, 255);
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiPressableNoBorderRed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */