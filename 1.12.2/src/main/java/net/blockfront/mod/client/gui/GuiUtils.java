/*     */ package net.blockfront.mod.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.blockfront.mod.client.character.CharacterPlayerRender;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class GuiUtils
/*     */ {
/*  21 */   public static int menuTheme = 15987699;
/*     */   
/*     */   private static CharacterPlayerRender characterRenderer;
/*     */   
/*     */   public static void renderCenteredTextThemed(String text, int posX, int posY) {
/*  26 */     (Minecraft.getMinecraft()).fontRenderer.drawStringWithShadow(text, posX - (Minecraft.getMinecraft()).fontRenderer.getStringWidth(text) / 2, posY, menuTheme);
/*     */   }
/*     */   
/*     */   public static void renderTextThemed(String text, int posX, int posY) {
/*  30 */     (Minecraft.getMinecraft()).fontRenderer.drawStringWithShadow(text, posX, posY, menuTheme);
/*     */   }
/*     */   
/*     */   public static void drawImage(double x, double y, ResourceLocation image, double width, double height) {
/*  34 */     (Minecraft.getMinecraft()).renderEngine.bindTexture(image);
/*  35 */     Tessellator tess = Tessellator.getInstance();
/*  36 */     tess.startDrawingQuads();
/*  37 */     tess.addVertexWithUV(x, y + height, 0.0D, 0.0D, 1.0D);
/*  38 */     tess.addVertexWithUV(x + width, y + height, 0.0D, 1.0D, 1.0D);
/*  39 */     tess.addVertexWithUV(x + width, y, 0.0D, 1.0D, 0.0D);
/*  40 */     tess.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
/*  41 */     tess.draw();
/*     */   }
/*     */   
/*     */   public static void renderPlayerModel(Minecraft minecraft, EntityPlayer player, int posX, int posY, float scale, float rotation) {
/*  45 */     RenderManager.instance.field_147941_i = (Entity)player;
/*  46 */     RenderManager.instance.renderEngine = minecraft.getTextureManager();
/*     */     
/*  48 */     if (characterRenderer == null) {
/*  49 */       characterRenderer = new CharacterPlayerRender();
/*  50 */       characterRenderer.setRenderManager(RenderManager.instance);
/*     */       
/*     */       return;
/*     */     } 
/*  54 */     GL11.glEnable(2903);
/*  55 */     GL11.glPushMatrix();
/*  56 */     GL11.glTranslatef(posX, posY, 50.0F);
/*  57 */     GL11.glScalef(-scale, scale, scale);
/*  58 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*     */     
/*  60 */     GL11.glEnable(2884);
/*  61 */     GL11.glEnable(3008);
/*  62 */     GL11.glEnable(2929);
/*     */     
/*  64 */     float f2 = player.renderYawOffset;
/*  65 */     float f3 = player.rotationYaw;
/*  66 */     float f4 = player.rotationPitch;
/*  67 */     GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
/*  68 */     RenderHelper.enableStandardItemLighting();
/*  69 */     GL11.glRotatef(-110.0F + player.renderYawOffset + (Mouse.getX() / 20 + 90), 0.0F, 1.0F, 0.0F);
/*  70 */     player.renderYawOffset = 0.0F;
/*  71 */     player.rotationYaw = 0.0F;
/*  72 */     player.rotationPitch = 50.0F - (Mouse.getY() / 8);
/*  73 */     player.rotationYawHead = player.renderYawOffset - (Mouse.getX() / 8 + 240);
/*  74 */     GL11.glTranslatef(0.0F, player.yOffset, 0.0F);
/*  75 */     RenderManager.instance.playerViewX = 180.0F;
/*  76 */     characterRenderer.doRender((AbstractClientPlayer)player, 0.0D, 0.0D, 0.0D, 0.0F, 0.625F);
/*     */     
/*  78 */     player.renderYawOffset = f2;
/*  79 */     player.rotationYaw = f3;
/*  80 */     player.rotationPitch = f4;
/*  81 */     GL11.glPopMatrix();
/*  82 */     RenderHelper.disableStandardItemLighting();
/*  83 */     GL11.glDisable(32826);
/*  84 */     OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/*  85 */     GL11.glDisable(3553);
/*  86 */     OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */   }
/*     */   
/*     */   public static void renderPlayerHead(String playerName, int xPos, int yPos) {
/*  90 */     ResourceLocation resourceLocation = AbstractClientPlayer.locationStevePng;
/*  91 */     if (playerName.length() > 0) {
/*  92 */       resourceLocation = AbstractClientPlayer.getLocationSkin(playerName);
/*  93 */       AbstractClientPlayer.getDownloadImageSkin(resourceLocation, playerName);
/*     */     } 
/*  95 */     GL11.glPushMatrix();
/*  96 */     drawRect(xPos - 1, yPos - 1, 20, 21, new Color(0, 0, 0, 127));
/*  97 */     (Minecraft.getMinecraft()).renderEngine.bindTexture(resourceLocation);
/*  98 */     GL11.glTranslated(xPos, yPos, 30.0D);
/*  99 */     GL11.glScaled(0.75D, 0.39D, 0.0D);
/* 100 */     double scale = 0.75D;
/* 101 */     GL11.glScaled(scale, scale, scale);
/* 102 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 103 */     RenderHelper.disableStandardItemLighting();
/* 104 */     (Minecraft.getMinecraft()).ingameGUI.drawTexturedModalRect(0, 0, 32, 64, 32, 64);
/* 105 */     (Minecraft.getMinecraft()).ingameGUI.drawTexturedModalRect(0, 0, 160, 64, 32, 64);
/* 106 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static void drawRect(int xStart, int yStart, int xEnd, int yEnd, Color color) {
/* 110 */     float r = color.getRed() / 255.0F;
/* 111 */     float g = color.getGreen() / 255.0F;
/* 112 */     float b = color.getBlue() / 255.0F;
/* 113 */     float a = color.getAlpha() / 255.0F;
/* 114 */     Tessellator tessellator = Tessellator.instance;
/* 115 */     GL11.glPushMatrix();
/* 116 */     GL11.glEnable(3042);
/* 117 */     GL11.glBlendFunc(770, 771);
/* 118 */     GL11.glDisable(3553);
/* 119 */     GL11.glColor4f(r, g, b, a);
/* 120 */     tessellator.startDrawingQuads();
/* 121 */     tessellator.addVertex(xStart, (yStart + yEnd), 0.0D);
/* 122 */     tessellator.addVertex((xStart + xEnd), (yStart + yEnd), 0.0D);
/* 123 */     tessellator.addVertex((xStart + xEnd), yStart, 0.0D);
/* 124 */     tessellator.addVertex(xStart, yStart, 0.0D);
/* 125 */     tessellator.draw();
/* 126 */     GL11.glEnable(3553);
/* 127 */     GL11.glDisable(3042);
/* 128 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 129 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static void drawRectWithShadow(int x, int y, int width, int height, Color color) {
/* 133 */     drawRect(x - 1, y - 1, width + 2, height + 2, new Color(0, 0, 0, 51));
/* 134 */     drawRect(x, y, width, height, color);
/*     */   }
/*     */   
/*     */   public static boolean isInBox(int x, int y, int width, int height, int checkX, int checkY) {
/* 138 */     return (checkX >= x && checkY >= y && checkX <= x + width && checkY <= y + height);
/*     */   }
/*     */   
/*     */   public static void drawTexturedQuadFit(int x, int y, int width, int height, float zLevel) {
/* 142 */     Tessellator t = Tessellator.instance;
/* 143 */     t.startDrawingQuads();
/* 144 */     t.addVertexWithUV(x, (y + height), zLevel, 0.0D, 1.0D);
/* 145 */     t.addVertexWithUV((x + width), (y + height), zLevel, 1.0D, 1.0D);
/* 146 */     t.addVertexWithUV((x + width), y, zLevel, 1.0D, 0.0D);
/* 147 */     t.addVertexWithUV(x, y, zLevel, 0.0D, 0.0D);
/* 148 */     t.draw();
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */