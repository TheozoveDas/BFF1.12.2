/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*    */ import net.minecraft.client.renderer.texture.ITextureObject;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiScreenshotView
/*    */   extends GuiScreen
/*    */ {
/*    */   private final BufferedImage image;
/*    */   private final ResourceLocation textureLocation;
/*    */   
/*    */   public GuiScreenshotView(BufferedImage image) {
/* 21 */     this.image = image;
/*    */     
/* 23 */     this.textureLocation = new ResourceLocation("bff", "screenshot");
/*    */     
/* 25 */     DynamicTexture texture = new DynamicTexture(image.getWidth(), image.getHeight());
/* 26 */     Minecraft.getMinecraft().getTextureManager().loadTexture(this.textureLocation, (ITextureObject)texture);
/*    */     
/* 28 */     image.getRGB(0, 0, image.getWidth(), image.getHeight(), texture.getTextureData(), 0, image.getWidth());
/* 29 */     texture.updateDynamicTexture();
/*    */   }
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*    */     int scaledWidth, scaledHeight;
/* 34 */     drawDefaultBackground();
/* 35 */     super.drawScreen(mouseX, mouseY, partialTicks);
/* 36 */     Minecraft.getMinecraft().getTextureManager().bindTexture(this.textureLocation);
/* 37 */     float widthScale = (this.width - 20.0F) / this.image.getWidth();
/* 38 */     float heightScale = (this.height - 20.0F) / this.image.getHeight();
/*    */     
/* 40 */     float scaledHeightForWidth = this.image.getHeight() * widthScale;
/*    */     
/* 42 */     if (scaledHeightForWidth > (this.height - 20)) {
/* 43 */       scaledHeight = (int)(this.image.getHeight() * heightScale);
/* 44 */       scaledWidth = (int)(this.image.getWidth() * heightScale);
/*    */     } else {
/* 46 */       scaledHeight = (int)(this.image.getHeight() * widthScale);
/* 47 */       scaledWidth = (int)(this.image.getWidth() * widthScale);
/*    */     } 
/*    */     
/* 50 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 51 */     GuiUtils.drawTexturedQuadFit(10, 10, scaledWidth, scaledHeight, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onGuiClosed() {
/* 56 */     super.onGuiClosed();
/* 57 */     Minecraft.getMinecraft().getTextureManager().deleteTexture(this.textureLocation);
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiScreenshotView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */