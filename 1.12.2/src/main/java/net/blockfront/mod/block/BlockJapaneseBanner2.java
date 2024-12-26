/*    */ package net.blockfront.mod.block;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.blockfront.mod.te.EntityJapaneseBanner2;
/*    */ import net.minecraft.block.BlockContainer;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockJapaneseBanner2
/*    */   extends BlockContainer
/*    */ {
/*    */   public BlockJapaneseBanner2() {
/* 25 */     super(Material.IRON);
/* 26 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F);
/*    */   }
private void setBlockBounds(float f, float g, float h, float i, float j, float k) {
	// TODO Auto-generated method stub
	
}
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName() {
/* 32 */     return "tile.BlockJapaneseBanner2";
/*    */   }
/*    */ 
/*    */   
/*    */   @net.minecraftforge.fml.relauncher.SideOnly(Side.CLIENT)
/*    */   protected String getTextureName() {
/* 38 */     return "bff:japanesebanner2";
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
/* 43 */     int l = net.minecraft.util.math.MathHelper.floor((par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
/*    */     
/* 45 */     if (l == 0)
/*    */     {
/* 47 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
/*    */     }
/*    */     
/* 50 */     if (l == 1)
/*    */     {
/* 52 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
/*    */     }
/*    */     
/* 55 */     if (l == 2)
/*    */     {
/* 57 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
/*    */     }
/*    */     
/* 60 */     if (l == 3)
/*    */     {
/* 62 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public net.minecraft.util.math.AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int wx, int wy, int wz) {
/* 69 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createNewTileEntity(World world, int var1) {
/* 75 */     return (TileEntity)new EntityJapaneseBanner2();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderType() {
/* 81 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isOpaqueCube() {
/* 87 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean renderAsNormalBlock() {
/* 92 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\block\BlockJapaneseBanner2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */