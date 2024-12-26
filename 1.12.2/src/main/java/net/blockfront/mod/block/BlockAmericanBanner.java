/*    */ package net.blockfront.mod.block;
/*    */ 
/*    */ import net.blockfront.mod.te.EntityAmericanBanner;
/*    */ import net.minecraft.block.BlockContainer;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockAmericanBanner
/*    */   extends BlockContainer
/*    */ {
/*    */   public BlockAmericanBanner() {
/* 24 */     super(Material.IRON);
/* 25 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F);
/*    */   }
private void setBlockBounds(float f, float g, float h, float i, float j, float k) {
	// TODO Auto-generated method stub
	
}
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName() {
/* 31 */     return "tile.BlockAmericanBanner";
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   protected String getTextureName() {
/* 37 */     return "bff:americanbanner";
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
/* 42 */     int l = MathHelper.floor((par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
/*    */     
/* 44 */     if (l == 0)
/*    */     {
/* 46 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
/*    */     }
/*    */     
/* 49 */     if (l == 1)
/*    */     {
/* 51 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
/*    */     }
/*    */     
/* 54 */     if (l == 2)
/*    */     {
/* 56 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
/*    */     }
/*    */     
/* 59 */     if (l == 3)
/*    */     {
/* 61 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public net.minecraft.util.math.AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int wx, int wy, int wz) {
/* 68 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createNewTileEntity(World world, int var1) {
/* 74 */     return (TileEntity)new EntityAmericanBanner();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderType() {
/* 80 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isOpaqueCube() {
/* 86 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean renderAsNormalBlock() {
/* 91 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\block\BlockAmericanBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */