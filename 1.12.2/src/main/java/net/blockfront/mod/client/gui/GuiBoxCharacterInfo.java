/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiBoxCharacterInfo
/*    */   extends GuiBox {
/*    */   public GuiBoxCharacterInfo(int posX, int posY, int width, int height, GuiCustomScreen parentGUI) {
/*  9 */     super(posX, posY, width, height, parentGUI);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 14 */     super.initGui();
/*    */   }
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 18 */     drawBackgroundPlayerinfo();
/* 19 */     String username = Minecraft.getMinecraft().getSession().getUsername();
/* 20 */     GuiUtils.renderPlayerHead(username, this.posX + 80, this.posY + 4);
/* 21 */     GuiUtils.renderTextThemed(substringName(username), this.posX + 10, this.posY + 4);
/* 22 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */   public String substringName(String name) {
/* 26 */     if (name.length() > 11) {
/* 27 */       name = name.substring(0, 11) + "...";
/*    */     }
/* 29 */     return name;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiBoxCharacterInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */