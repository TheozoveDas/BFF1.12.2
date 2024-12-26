/*    */ package net.blockfront.mod.block;
/*    */ 
/*    */ import net.minecraft.block.BlockFalling;
/*    */ import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class BlockSandbagStack
/*    */   extends BlockFalling
/*    */ {
/*    */   private String textureName;
public BlockSandbagStack(Material material) {
/* 12 */     super(material);
/* 13 */     setResistance(15.0F);
/* 14 */     setHardness(0.4F);
/* 15 */     setHarvestLevel("shovel", 4);
/* 16 */     this; setStepSound(soundTypeGravel);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName() {
/* 22 */     return "tile.BlockSandbagStack";
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   protected String getTextureName() {
/* 28 */     return this.textureName = "bff:blocksandbagstack";
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\block\BlockSandbagStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */