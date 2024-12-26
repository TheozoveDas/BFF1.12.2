/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.audio.ISound;
/*    */ import net.minecraft.client.audio.PositionedSoundRecord;
/*    */ import net.minecraft.client.audio.SoundHandler;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiCustomPressable
/*    */   extends GuiButton
/*    */ {
/* 17 */   public int isOver = 2;
/* 18 */   private ResourceLocation iconTexture = null;
/*    */   
/*    */   public boolean drawBackground = true;
/*    */   public boolean drawShadow = true;
/* 22 */   public Color menuTheme = new Color(47, 47, 47);
/*    */   
/*    */   public boolean centeredText = true;
/*    */   public boolean soundPlayed = true;
/*    */   
/*    */   public GuiCustomPressable(int id, int x, int y, int width, int height, String displayString) {
/* 28 */     super(id, x, y, width, height, displayString);
/*    */   }
/*    */   
/*    */   public GuiCustomPressable(int id, int x, int y, int width, int height, String displayString, Color menuTheme) {
/* 32 */     super(id, x, y, width, height, displayString);
/* 33 */     this.menuTheme = menuTheme;
/*    */   }
/*    */   
/*    */   public GuiCustomPressable(int id, int x, int y, int width, int height, ResourceLocation iconTexture) {
/* 37 */     super(id, x, y, width, height, "");
/* 38 */     this.iconTexture = iconTexture;
/*    */   }
/*    */   
/*    */   public void drawButton(Minecraft minecraft, int mouseX, int mouseY) {
/* 42 */     FontRenderer fontRenderer = (Minecraft.getMinecraft()).fontRenderer;
/* 43 */     if (this.visible) {
/* 44 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 45 */       this.field_146123_n = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
/* 46 */       this.isOver = getHoverState(this.field_146123_n);
/* 47 */       if (this.drawBackground) {
/* 48 */         if (this.drawShadow) {
/* 49 */           GuiUtils.drawRectWithShadow(this.xPosition, this.yPosition, this.width, this.height, this.menuTheme);
/* 50 */           GuiUtils.drawImage(this.xPosition, this.yPosition, new ResourceLocation("bff:gui/button.png"), this.width, this.height);
/*    */         } else {
/* 52 */           GuiUtils.drawRect(this.xPosition, this.yPosition, this.width, this.height, this.menuTheme);
/*    */         } 
/*    */       }
/* 55 */       mouseDragged(minecraft, mouseX, mouseY);
/* 56 */       String displayText = this.displayString;
/* 57 */       if (this.isOver == 2) {
/* 58 */         if (!this.soundPlayed) {
/* 59 */           Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 2.5F));
/* 60 */           this.soundPlayed = true;
/*    */         } 
/*    */       } else {
/* 63 */         this.soundPlayed = false;
/*    */       } 
/* 65 */       if (this.iconTexture != null) {
/* 66 */         if (this.isOver == 2) {
/* 67 */           Color color = new Color(128, 128, 128);
/* 68 */           GL11.glColor3f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*    */         } 
/* 70 */         GuiUtils.drawImage((this.xPosition + this.width / 2 - 8), (this.yPosition + this.height - 8 - 10), this.iconTexture, 16.0D, 16.0D);
/* 71 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*    */         return;
/*    */       } 
/* 74 */       if (!this.enabled) {
/* 75 */         displayText = TextFormatting.GRAY + displayText;
/*    */       }
/* 77 */       if (!this.centeredText) {
/* 78 */         fontRenderer.drawStringWithShadow(displayText, this.xPosition + 2, this.yPosition + (this.height - 8) / 2, (this.isOver == 2) ? 8421504 : 16777215);
/*    */         return;
/*    */       } 
/* 81 */       fontRenderer.drawStringWithShadow(displayText, this.xPosition + this.width / 2 - fontRenderer.getStringWidth(displayText) / 2, this.yPosition + (this.height - 8) / 2, (this.isOver == 2) ? 8421504 : 16777215);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void func_146113_a(SoundHandler soundHandler) {
/* 86 */     soundHandler.playSound((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiCustomPressable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */