/*     */ package net.blockfront.mod.client.render;
/*     */ 
/*     */ import net.blockfront.mod.client.model.ModelBackpackMedium;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderBackpackMediumItem
/*     */   implements IItemRenderer
/*     */ {
/*  23 */   public static final ResourceLocation texture = new ResourceLocation("bff", "textures/model/backpackmedium.png");
/*     */   
/*  25 */   protected ModelBackpackMedium backpackModel = new ModelBackpackMedium();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
/*  31 */     switch (type) {
/*     */       case EQUIPPED:
/*  33 */         return true;
/*  34 */       case EQUIPPED_FIRST_PERSON: return true;
/*  35 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
/*  44 */     return false;
/*     */   }
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
/*     */     boolean isFirstPerson;
/*  49 */     switch (type) {
/*     */ 
/*     */ 
/*     */       
/*     */       case EQUIPPED:
/*  54 */         GL11.glPushMatrix();
/*     */         
/*  56 */         (Minecraft.getMinecraft()).renderEngine.bindTexture(texture);
/*  57 */         GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  58 */         GL11.glRotatef(180.0F, 10.0F, 5.0F, 7.0F);
/*  59 */         GL11.glRotatef(25.0F, 11.0F, 5.0F, 7.0F);
/*  60 */         GL11.glTranslatef(0.4F, 0.9F, 1.3F);
/*  61 */         GL11.glTranslatef(1.0F, 0.5F, 0.2F);
/*     */ 
/*     */         
/*  64 */         GL11.glPopMatrix();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case EQUIPPED_FIRST_PERSON:
/*  70 */         GL11.glPushMatrix();
/*     */         
/*  72 */         (Minecraft.getMinecraft()).renderEngine.bindTexture(texture);
/*  73 */         GL11.glScalef(1.8F, 1.8F, 1.8F);
/*  74 */         GL11.glRotatef(-130.0F, -5.0F, 2.0F, 35.0F);
/*  75 */         GL11.glTranslatef(-0.2F, -0.4F, -1.4F);
/*  76 */         isFirstPerson = false;
/*     */         
/*  78 */         if (data[1] != null && data[1] instanceof EntityPlayer) {
/*     */           
/*  80 */           if ((EntityPlayer)data[1] != (Minecraft.getMinecraft()).renderViewEntity || (Minecraft.getMinecraft()).gameSettings.thirdPersonView != 0 || (((Minecraft.getMinecraft()).currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory || (Minecraft.getMinecraft()).currentScreen instanceof net.minecraft.client.gui.inventory.GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F))
/*     */           {
/*  82 */             GL11.glScalef(1.0F, 1.0F, 1.0F);
/*  83 */             GL11.glRotatef(0.0F, -5.0F, -30.0F, 15.0F);
/*  84 */             GL11.glTranslatef(0.0F, 0.3F, 1.2F);
/*     */           }
/*     */           else
/*     */           {
/*  88 */             isFirstPerson = true;
/*  89 */             GL11.glScalef(0.7F, 0.7F, 0.7F);
/*  90 */             GL11.glRotatef(260.0F, -20.0F, -50.0F, 10.0F);
/*  91 */             GL11.glTranslatef(-1.4F, 0.2F, -0.7F);
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  97 */           GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  98 */           GL11.glRotatef(180.0F, 10.0F, 5.0F, 7.0F);
/*  99 */           GL11.glRotatef(25.0F, 11.0F, 5.0F, 7.0F);
/* 100 */           GL11.glTranslatef(0.4F, 0.9F, 1.3F);
/*     */         } 
/* 102 */         this.backpackModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/*     */         
/* 104 */         GL11.glPopMatrix();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\render\RenderBackpackMediumItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */