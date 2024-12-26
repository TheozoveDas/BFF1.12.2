/*     */ package net.blockfront.mod.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Desktop;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import net.blockfront.mod.BlockFront;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiMultiplayer;
/*     */ import net.minecraft.client.gui.GuiOptions;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
/*     */ 
/*     */ public class GuiCustomMainMenu
/*     */   extends GuiCustomScreen
/*     */ {
/*  21 */   public ResourceLocation background = new ResourceLocation("bff:gui/background.png");
/*  22 */   public ResourceLocation iconHome = new ResourceLocation("bff:gui/home.png");
/*  23 */   public ResourceLocation iconSettings = new ResourceLocation("bff:gui/settings.png");
/*  24 */   public ResourceLocation iconPower = new ResourceLocation("bff:gui/power.png");
/*     */   
/*     */   public void initGui() {
/*  27 */     super.initGui();
/*     */     
/*  29 */     initOverheadMenu();
/*  30 */     int x = this.width / 2 - 173;
/*  31 */     addContainer(new GuiBoxCharacterInfo(x + 241, 73, 110, 45, this));
/*  32 */     addContainer(new GuiBoxGraphic(x + 44, 15, 247, 45, this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final URI HOMEPAGE;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final URI STATS;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final URI TEAMSPEAK;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(GuiButton button) {
/*  53 */     super.actionPerformed(button);
/*  54 */     addOverheadMenuActionPerformed(button);
/*     */   }
/*     */   
/*     */   public void updateScreen() {
/*  58 */     super.updateScreen();
/*     */   }
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  62 */     drawBackground(mouseX, mouseY, partialTicks);
/*  63 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   String username = Minecraft.getMinecraft().getSession().getUsername();
/*     */   
/*     */   static {
/*     */     try {
/*  74 */       HOMEPAGE = new URI("http://blockfrontmc.buycraft.net/");
/*  75 */       STATS = new URI("http://www.blockfrontmc.com/stats");
/*  76 */       TEAMSPEAK = new URI("ts3server://ts.blockfrontmc.com?port=9987&nickname" + Minecraft.getMinecraft().getSession().getUsername());
/*  77 */     } catch (URISyntaxException e) {
/*  78 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addOverheadMenuActionPerformed(GuiButton button) {
/*  83 */     switch (button.id) {
/*     */       case 2:
/*  85 */         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiMultiplayer(this));
/*     */         break;
/*     */       case 3:
/*  88 */         openURL(HOMEPAGE);
/*     */         break;
/*     */       case 5:
/*  91 */         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiOptions(this, this.mc.gameSettings));
/*     */         break;
/*     */       case 6:
/*  94 */         Minecraft.getMinecraft().shutdown();
/*     */         break;
/*     */       case 7:
/*  97 */         openURL(STATS);
/*     */         break;
/*     */       case 8:
/* 100 */         openURL(TEAMSPEAK);
/*     */         break;
/*     */       case 9:
/* 103 */         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiSelectWorld(this));
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void openURL(URI uri) {
/*     */     try {
/* 110 */       Desktop.getDesktop().browse(uri);
/* 111 */     } catch (IOException e) {
/* 112 */       BlockFront.log.warn("Failed to open link");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean drawRightHalfBG = false;
/*     */   
/*     */   public void drawBackground(int mouseX, int mouseY, float partialTicks) {
/* 119 */     GuiUtils.drawImage((-20 + mouseX / 40), (-20 + mouseY / 40), this.background, this.width * 1.1D, this.height * 1.1D);
/*     */     
/* 121 */     if (this.drawRightHalfBG) {
/* 122 */       int x = this.width / 2 - 173;
/* 123 */       GuiUtils.drawRectWithShadow(x + 1, 73, 237, 151, new Color(47, 47, 47));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initOverheadMenu() {
/* 128 */     int x = this.width / 2 - 173;
/* 129 */     Color green = new Color(51, 153, 51);
/* 130 */     Color orange = new Color(255, 153, 51);
/* 131 */     this.buttonList.add(new GuiCustomPressableHome(1, x + 241, 119, 30, 20, ""));
/* 132 */     this.buttonList.add(new GuiCustomPressablePlaynow(9, x + 271, 119, 80, 20, TextFormatting.BOLD + "Singleplayer", green));
/* 133 */     this.buttonList.add(new GuiCustomPressable(5, x + 241, 140, 110, 20, TextFormatting.BOLD + "Multiplayer"));
/* 134 */     this.buttonList.add(new GuiCustomPressable(5, x + 241, 161, 110, 10, TextFormatting.BOLD + "Settings"));
/* 135 */     this.buttonList.add(new GuiCustomPressable(8, x + 241, 172, 110, 10, TextFormatting.BOLD + "Teamspeak"));
/* 136 */     this.buttonList.add(new GuiCustomPressable(6, x + 241, 183, 110, 20, TextFormatting.BOLD + "Exit"));
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiCustomMainMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */