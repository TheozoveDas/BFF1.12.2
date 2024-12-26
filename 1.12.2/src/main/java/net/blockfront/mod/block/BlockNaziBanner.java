/*    */ package net.blockfront.mod.block;
/*    */ 
/*    */ import net.blockfront.mod.te.EntityNaziBanner;
/*    */ import net.minecraft.block.BlockContainer;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockNaziBanner
/*    */   extends BlockContainer
/*    */ {
/*    */   public BlockNaziBanner() {
/* 23 */     super(Material.IRON);
/* 24 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F);
/*    */   }
private void setBlockBounds(float f, float g, float h, float i, float j, float k) {
	// TODO Auto-generated method stub
	
}
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName() {
/* 30 */     return "tile.BlockNaziBanner";
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   protected String getTextureName() {
/* 36 */     return "bff:nazibanner";
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
/* 41 */     int l = MathHelper.floor((par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
/*    */     
/* 43 */     if (l == 0)
/*    */     {
/* 45 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
/*    */     }
/*    */     
/* 48 */     if (l == 1)
/*    */     {
/* 50 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
/*    */     }
/*    */     
/* 53 */     if (l == 2)
/*    */     {
/* 55 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
/*    */     }
/*    */     
/* 58 */     if (l == 3)
/*    */     {
/* 60 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int wx, int wy, int wz) {
/* 67 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createNewTileEntity(World world, int var1) {
/* 73 */     return (TileEntity)new EntityNaziBanner();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderType() {
/* 79 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isOpaqueCube() {
/* 85 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean renderAsNormalBlock() {
/* 90 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\block\BlockNaziBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */