/*    */ package net.blockfront.mod.client.render;
/*    */ 
/*    */ import net.blockfront.mod.client.model.ModelBanner;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderNaziBanner
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   private final ModelBanner model;
/* 25 */   private final ResourceLocation textureLocation = new ResourceLocation("bff", "textures/entity/prop/nazibanner.png");
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderNaziBanner() {
/* 30 */     this.model = new ModelBanner();
/*    */   }
/*    */ 
/*    */   
/*    */   private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
/* 35 */     int meta = world.getBlockMetadata(x, y, z);
/* 36 */     GL11.glPushMatrix();
/* 37 */     GL11.glRotatef((meta * -90), 0.0F, 0.0F, 1.0F);
/* 38 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
/* 45 */     GL11.glPushMatrix();
/*    */     
/* 47 */     GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/*    */     
/* 49 */     bindTexture(this.textureLocation);
/*    */     
/* 51 */     GL11.glPushMatrix();
/* 52 */     GL11.glRotatef(180.0F, 0.1F, 0.0F, 0.0F);
/*    */ 
/*    */ 
/*    */     
/* 56 */     int rotation = 0;
/* 57 */     GL11.glRotatef(rotation, 0.0F, -0.0F, 0.0F);
/* 58 */     switch (te.getBlockMetadata() % 4) {
/*    */       case 0:
/* 60 */         rotation = 90;
/*    */         break;
/*    */       case 1:
/* 63 */         rotation = 180;
/*    */         break;
/*    */       case 2:
/* 66 */         rotation = 270;
/*    */         break;
/*    */       case 3:
/* 69 */         rotation = 0;
/*    */         break;
/*    */     } 
/* 72 */     GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*    */ 
/*    */     
/* 75 */     this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/* 77 */     GL11.glPopMatrix();
/* 78 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void adjustLightFixture(World world, int i, int j, int k, Block block) {
/* 84 */     Tessellator tess = Tessellator.instance;
/* 85 */     float brightness = block.getLightOpacity((IBlockAccess)world, i, j, k);
/* 86 */     int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
/* 87 */     int modulousModifier = skyLight % 65536;
/* 88 */     int divModifier = skyLight / 65536;
/* 89 */     tess.setColorOpaque_F(brightness, brightness, brightness);
/* 90 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, modulousModifier, divModifier);
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\render\RenderNaziBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */