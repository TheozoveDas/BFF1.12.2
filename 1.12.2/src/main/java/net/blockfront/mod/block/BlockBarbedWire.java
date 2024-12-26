/*    */ package net.blockfront.mod.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
/*    */ 
/*    */ public class BlockBarbedWire
/*    */   extends Block
/*    */ {
/*    */   private String textureName;
public BlockBarbedWire(Material material) {
/* 16 */     super(Material.WEB);
/* 17 */     setTickRandomly(true);
/* 18 */     setResistance(12.0F);
/* 19 */     setHardness(0.6F);
/* 20 */     setHarvestLevel("pickaxe", 4);
/* 21 */     this; setStepSound(soundTypeMetal);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName() {
/* 27 */     return "tile.BlockBarbedWire";
/*    */   }
/*    */ 
/*    */   
/*    */   @net.minecraftforge.fml.relauncher.SideOnly(Side.CLIENT)
/*    */   protected String getTextureName() {
/* 33 */     return this.textureName = "bff:blockbarbedwire";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRenderType() {
/* 38 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public net.minecraft.util.math.AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
/* 43 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean renderAsNormalBlock() {
/* 48 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isOpaqueCube() {
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
/* 57 */     entity.setInWeb();
/* 58 */     entity.attackEntityFrom(DamageSource.CACTUS, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\block\BlockBarbedWire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */