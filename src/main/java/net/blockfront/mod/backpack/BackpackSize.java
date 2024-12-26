/*    */ package net.blockfront.mod.backpack;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum BackpackSize
/*    */ {
/* 11 */   SMALL(8, "small"),
/* 12 */   MEDIUM(12, "medium"),
/* 13 */   LARGE(16, "large");
/*    */   
/*    */   public final int invSize;
/*    */   public final ResourceLocation background;
/*    */   
/*    */   BackpackSize(int invSize, String background) {
/* 19 */     this.invSize = invSize;
/* 20 */     this.background = new ResourceLocation("bff", "gui/backpack_" + background + ".png");
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\backpack\BackpackSize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */