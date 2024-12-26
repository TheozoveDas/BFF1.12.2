/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.blockfront.mod.ClientProxy;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ 
/*    */ public class GuiBoxGraphicNews
/*    */   extends GuiBox
/*    */ {
/*    */   public GuiBoxGraphicNews(int posX, int posY, int width, int height, GuiCustomScreen parentGUI) {
/* 12 */     super(posX, posY, width, height, parentGUI);
/*    */   }
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 16 */     GuiUtils.drawRectWithShadow(this.posX, this.posY, this.width, this.height, new Color(47, 47, 47));
/* 17 */     drawBannerNews(this.posX, this.posY, this.width, this.height);
/*    */   }
/*    */   
/*    */   public static void drawBannerNews(int x, int y, double width, double height) {
/* 21 */     if (ClientProxy.bannerImage == null) {
/* 22 */       GuiUtils.renderTextThemed("Loading...", x, y);
/*    */     } else {
/* 24 */       Minecraft.getMinecraft().getTextureManager().bindTexture(ClientProxy.bannerImage);
/* 25 */       Tessellator tess = Tessellator.getInstance();
/* 26 */       tess.startDrawingQuads();
/* 27 */       tess.addVertexWithUV(x, y + height, 0.0D, 0.0D, 1.0D);
/* 28 */       tess.addVertexWithUV(x + width, y + height, 0.0D, 1.0D, 1.0D);
/* 29 */       tess.addVertexWithUV(x + width, y, 0.0D, 1.0D, 0.0D);
/* 30 */       tess.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
/* 31 */       tess.draw();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiBoxGraphicNews.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */