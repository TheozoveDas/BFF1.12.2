/*    */ package net.blockfront.mod.client.gui;
/*    */ 
/*    */ import net.blockfront.mod.client.ContainerBackpack;
/*    */ import net.blockfront.mod.client.InventoryBackpack;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.RenderHelper;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.inventory.Container;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiBackpack
/*    */   extends GuiContainer
/*    */ {
/*    */   private float xSize_lo;
/*    */   private float ySize_lo;
/*    */   private final InventoryBackpack inventory;
/*    */   
/*    */   public GuiBackpack(ContainerBackpack containerBackpack) {
/* 27 */     super((Container)containerBackpack);
/* 28 */     this.inventory = containerBackpack.inventory;
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int par1, int par2, float par3) {
/* 33 */     super.drawScreen(par1, par2, par3);
/* 34 */     this.xSize_lo = par1;
/* 35 */     this.ySize_lo = par2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/* 40 */     String s = this.inventory.hasCustomInventoryName() ? this.inventory.getInventoryName() : I18n.format(this.inventory.getInventoryName(), new Object[0]);
/* 41 */     GuiUtils.renderTextThemed("Backpack Contents:", this.xSize / 2 - this.fontRendererObj.getStringWidth(s) + 65, -10);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 46 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 47 */     this.mc.getTextureManager().bindTexture(this.inventory.size.background);
/* 48 */     int k = (this.width - this.xSize) / 2;
/* 49 */     int l = (this.height - this.ySize) / 2;
/* 50 */     drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
/*    */     
/* 52 */     drawPlayerModel(k + 51, l + 75, 30, (k + 51) - this.xSize_lo, (l + 75 - 50) - this.ySize_lo, (EntityLivingBase)this.mc.player);
/*    */   }
/*    */   
/*    */   public static void drawPlayerModel(int x, int y, int scale, float yaw, float pitch, EntityLivingBase entity) {
/* 56 */     GL11.glEnable(2903);
/* 57 */     GL11.glPushMatrix();
/* 58 */     GL11.glTranslatef(x, y, 50.0F);
/* 59 */     GL11.glScalef(-scale, scale, scale);
/* 60 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 61 */     float f2 = entity.renderYawOffset;
/* 62 */     float f3 = entity.rotationYaw;
/* 63 */     float f4 = entity.rotationPitch;
/* 64 */     float f5 = entity.prevRotationYawHead;
/* 65 */     float f6 = entity.rotationYawHead;
/* 66 */     GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
/* 67 */     RenderHelper.enableStandardItemLighting();
/* 68 */     GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
/* 69 */     GL11.glRotatef(-((float)Math.atan((pitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
/* 70 */     entity.renderYawOffset = (float)Math.atan((yaw / 40.0F)) * 20.0F;
/* 71 */     entity.rotationYaw = (float)Math.atan((yaw / 40.0F)) * 40.0F;
/* 72 */     entity.rotationPitch = -((float)Math.atan((pitch / 40.0F))) * 20.0F;
/* 73 */     entity.rotationYawHead = entity.rotationYaw;
/* 74 */     entity.prevRotationYawHead = entity.rotationYaw;
/* 75 */     GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
/* 76 */     RenderManager.instance.playerViewY = 180.0F;
/* 77 */     RenderManager.instance.renderEntityWithPosYaw((Entity)entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/* 78 */     entity.renderYawOffset = f2;
/* 79 */     entity.rotationYaw = f3;
/* 80 */     entity.rotationPitch = f4;
/* 81 */     entity.prevRotationYawHead = f5;
/* 82 */     entity.rotationYawHead = f6;
/* 83 */     GL11.glPopMatrix();
/* 84 */     RenderHelper.disableStandardItemLighting();
/* 85 */     GL11.glDisable(32826);
/* 86 */     OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 87 */     GL11.glDisable(3553);
/* 88 */     OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */