/*     */ package net.blockfront.mod.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.blockfront.mod.te.EntityNaziRadio;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockNaziRadio
/*     */   extends BlockContainer
/*     */ {
/*     */   public BlockNaziRadio() {
/*  26 */     super(Material.IRON);
/*  27 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
/*     */   }
private void setBlockBounds(float f, float g, float h, float i, float j, float k) {
	// TODO Auto-generated method stub
	
}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName() {
/*  33 */     return "tile.BlockNaziRadio";
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected String getTextureName() {
/*  39 */     return "bff:naziradio";
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
/*  45 */     if (random.nextInt(20) == 0)
/*     */     {
/*  47 */       world.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "bff:block.naziradio.chatter", 0.2F, 1.0F, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
/*  52 */     if (world.isRemote) {
/*  53 */       return true;
/*     */     }
/*  55 */     world.playSoundEffect(x, y, z, "bff:block.radio.static", 1.0F, 1.0F);
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
/*  62 */     int l = MathHelper.floor((par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
/*     */     
/*  64 */     if (l == 0)
/*     */     {
/*  66 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
/*     */     }
/*     */     
/*  69 */     if (l == 1)
/*     */     {
/*  71 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
/*     */     }
/*     */     
/*  74 */     if (l == 2)
/*     */     {
/*  76 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
/*     */     }
/*     */     
/*  79 */     if (l == 3)
/*     */     {
/*  81 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int wx, int wy, int wz) {
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World world, int var1) {
/*  94 */     return (TileEntity)new EntityNaziRadio();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 100 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderAsNormalBlock() {
/* 111 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\block\BlockNaziRadio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */