/*    */ package net.blockfront.mod;
/*    */ 
/*    */ import net.blockfront.mod.block.BlockAmericanBanner;
/*    */ import net.blockfront.mod.block.BlockAmericanRadio;
/*    */ import net.blockfront.mod.block.BlockBarbedWire;
/*    */ import net.blockfront.mod.block.BlockJapaneseBanner;
/*    */ import net.blockfront.mod.block.BlockJapaneseBanner2;
/*    */ import net.blockfront.mod.block.BlockJapaneseRadio;
/*    */ import net.blockfront.mod.block.BlockNaziBanner;
/*    */ import net.blockfront.mod.block.BlockNaziRadio;
/*    */ import net.blockfront.mod.block.BlockSandbagStack;
/*    */ import net.blockfront.mod.block.BlockSandbagStackBeige;
/*    */ import net.blockfront.mod.block.BlockSovietRadio;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ 
/*    */ public class BlockFrontBlocks {
/*    */   public static Block blockSandbagStack;
/*    */   public static Block blockSandbagStackBeige;
/*    */   public static Block blockBarbedWire;
/*    */   public static Block blockNaziRadio;
/*    */   public static Block blockNaziBanner;
/*    */   
/*    */   public static void init() {
/* 26 */     blockSandbagStack = (new BlockSandbagStack(Material.SAND)).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 27 */     GameRegistry.registerBlock(blockSandbagStack, blockSandbagStack.getUnlocalizedName().substring(5));
/* 28 */     blockSandbagStackBeige = (new BlockSandbagStackBeige(Material.SAND)).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 29 */     GameRegistry.registerBlock(blockSandbagStackBeige, blockSandbagStackBeige.getUnlocalizedName().substring(5));
/* 30 */     blockBarbedWire = (new BlockBarbedWire(Material.WEB)).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 31 */     GameRegistry.registerBlock(blockBarbedWire, blockBarbedWire.getUnlocalizedName().substring(5));
/* 32 */     blockNaziRadio = (new BlockNaziRadio()).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 33 */     GameRegistry.registerBlock(blockNaziRadio, blockNaziRadio.getUnlocalizedName().substring(5));
/* 34 */     blockNaziBanner = (new BlockNaziBanner()).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 35 */     GameRegistry.registerBlock(blockNaziBanner, blockNaziBanner.getUnlocalizedName().substring(5));
/* 36 */     blockAmericanBanner = (new BlockAmericanBanner()).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 37 */     GameRegistry.registerBlock(blockAmericanBanner, blockAmericanBanner.getUnlocalizedName().substring(5));
/* 38 */     blockAmericanRadio = (new BlockAmericanRadio()).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 39 */     GameRegistry.registerBlock(blockAmericanRadio, blockAmericanRadio.getUnlocalizedName().substring(5));
/* 40 */     blockJapaneseBanner = (new BlockJapaneseBanner()).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 41 */     GameRegistry.registerBlock(blockJapaneseBanner, blockJapaneseBanner.getUnlocalizedName().substring(5));
/* 42 */     blockJapaneseBanner2 = (new BlockJapaneseBanner2()).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 43 */     GameRegistry.registerBlock(blockJapaneseBanner2, blockJapaneseBanner2.getUnlocalizedName().substring(5));
/* 44 */     blockJapaneseRadio = (new BlockJapaneseRadio()).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 45 */     GameRegistry.registerBlock(blockJapaneseRadio, blockJapaneseRadio.getUnlocalizedName().substring(5));
/* 46 */     blockSovietRadio = (new BlockSovietRadio()).setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 47 */     GameRegistry.registerBlock(blockSovietRadio, blockSovietRadio.getUnlocalizedName().substring(5));
/*    */   }
/*    */   
/*    */   public static Block blockAmericanBanner;
/*    */   public static Block blockAmericanRadio;
/*    */   public static Block blockJapaneseBanner;
/*    */   public static Block blockJapaneseBanner2;
/*    */   public static Block blockJapaneseRadio;
/*    */   public static Block blockSovietRadio;
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\BlockFrontBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */