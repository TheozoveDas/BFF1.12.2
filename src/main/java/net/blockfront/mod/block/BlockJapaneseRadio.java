/*     */ package net.blockfront.mod.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.blockfront.mod.te.EntityJapaneseRadio;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockJapaneseRadio
/*     */   extends BlockContainer
/*     */ {
/*     */   public BlockJapaneseRadio() {
/*  27 */     super(Material.IRON);
/*  28 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
/*     */   }
private void setBlockBounds(float f, float g, float h, float i, float j, float k) {
	// TODO Auto-generated method stub
	
}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName() {
/*  34 */     return "tile.BlockJapaneseRadio";
/*     */   }
/*     */ 
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(Side.CLIENT)
/*     */   protected String getTextureName() {
/*  40 */     return "bff:japaneseradio";
/*     */   }
/*     */ 
/*     */   
/*     */   @net.minecraftforge.fml.relauncher.SideOnly(Side.CLIENT)
/*     */   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
/*  46 */     if (random.nextInt(20) == 0)
/*     */     {
/*  48 */       world.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "bff:block.japaneseradio.chatter", 0.2F, 1.0F, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
/*  53 */     if (world.isRemote) {
/*  54 */       return true;
/*     */     }
/*  56 */     world.playSoundEffect(x, y, z, "bff:block.radio.static", 1.0F, 1.0F);
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
/*  63 */     int l = net.minecraft.util.math.MathHelper.floor((par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3;
/*     */     
/*  65 */     if (l == 0)
/*     */     {
/*  67 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
/*     */     }
/*     */     
/*  70 */     if (l == 1)
/*     */     {
/*  72 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
/*     */     }
/*     */     
/*  75 */     if (l == 2)
/*     */     {
/*  77 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
/*     */     }
/*     */     
/*  80 */     if (l == 3)
/*     */     {
/*  82 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public net.minecraft.util.math.AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int wx, int wy, int wz) {
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World world, int var1) {
/*  95 */     return (TileEntity)new EntityJapaneseRadio();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 101 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderAsNormalBlock() {
/* 112 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\block\BlockJapaneseRadio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */