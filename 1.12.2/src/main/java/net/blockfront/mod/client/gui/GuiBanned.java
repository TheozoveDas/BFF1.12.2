/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Desktop;
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import net.blockfront.mod.BlockFront;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiBanned
/*    */   extends GuiCustomScreen {
/* 17 */   public ResourceLocation background = new ResourceLocation("bff:gui/background_banned.png");
/* 18 */   public ResourceLocation iconHome = new ResourceLocation("bff:gui/home.png");
/* 19 */   public ResourceLocation iconSettings = new ResourceLocation("bff:gui/settings.png");
/* 20 */   public ResourceLocation iconPower = new ResourceLocation("bff:gui/power.png"); 
/*    */   
/*    */   public void initGui() {
/* 23 */     super.initGui();
/*    */     
/* 25 */     initOverheadMenu();
/*    */   }
/*    */   
/*    */   public void actionPerformed(GuiButton button) {
/* 30 */     super.actionPerformed(button);
/* 31 */     addOverheadMenuActionPerformed(button);
/*    */   }
/*    */   
/*    */   public void updateScreen() {
/* 35 */     super.updateScreen();
/*    */   }
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 39 */     drawBackground(mouseX, mouseY, partialTicks);
/* 40 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   String username = Minecraft.getMinecraft().getSession().getUsername();
/*    */   
/*    */   static {
/*    */     try {
new URI("http://blockfrontmc.buycraft.net/");
new URI("http://www.blockfrontmc.com/stats");
new URI("ts3server://ts.blockfrontmc.com?port=9987&nickname" + Minecraft.getMinecraft().getSession().getUsername());     } catch (URISyntaxException e) {
/* 55 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void addOverheadMenuActionPerformed(GuiButton button) {
/* 60 */     switch (button.id) {
/*    */       case 1:
/* 62 */         System.out.println("This user is Banned, Program Terminated");
/* 63 */         Minecraft.getMinecraft().shutdown();
/*    */         break;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean drawRightHalfBG = false;
/*    */   
/*    */   public void drawBackground(int mouseX, int mouseY, float partialTicks) {
/* 79 */     int screenX = this.width / 2;
/* 80 */     int screenY = this.height / 2;
/*    */     
/* 82 */     GuiUtils.drawImage((-20 + mouseX / 40), (-20 + mouseY / 40), this.background, this.width * 1.1D, this.height * 1.1D);
/*    */     
/* 84 */     if (this.drawRightHalfBG) {
/* 85 */       int x = this.width / 2 - 173;
/* 86 */       GuiUtils.drawRectWithShadow(x + 1, 73, 237, 151, new Color(47, 47, 47));
/*    */     } 
/* 88 */     String username = Minecraft.getMinecraft().getSession().getUsername();
/* 89 */     GuiUtils.renderPlayerHead(username, screenX - 10, screenY - 40);
/* 90 */     GuiUtils.renderTextThemed("You are Global-Banned from playing this Modpack!", screenX - 120, screenY - 60);
/* 91 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 92 */     GuiUtils.renderTextThemed("ScottehBoeh Ban Manager v1.0", screenX - 75, this.height - 10);
/*    */   }
/*    */   
/*    */   public void initOverheadMenu() {
/* 96 */     int x = this.width / 2;
/* 97 */     int y = this.height / 2;
/* 98 */     this.buttonList.add(new GuiCustomPressable(1, x - 55, y - 10, 110, 20, TextFormatting.BOLD + "Exit Game"));
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiBanned.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */