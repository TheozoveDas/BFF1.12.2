/*    */ package net.blockfront.mod;
/*    */ 
/*    */ import net.blockfront.mod.backpack.BackpackSize;
/*    */ import net.blockfront.mod.item.ItemBackpack;
/*    */ import net.blockfront.mod.item.ItemConcertinaKit;
/*    */ import net.blockfront.mod.item.ItemSandbagKit;
/*    */ import net.blockfront.mod.item.ItemSandbagKitBeige;
/*    */ import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFrontItems
/*    */ {
/*    */   public static Item itemConcertinaKit;
/*    */   public static Item itemSandbagKit;
/*    */   public static Item itemSandbagKitBeige;
/*    */   public static Item itemBackpackSmall;
/*    */   public static Item itemBackpackMedium;
/*    */   public static Item itemBackpackLarge;
/*    */   
/*    */   public static void init() {
/* 23 */     itemConcertinaKit = (new ItemConcertinaKit()).setUnlocalizedName("ItemConcertinaKit").setTextureName("bff:itemconcertinakit").setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 24 */     GameRegistry.registerItem(itemConcertinaKit, itemConcertinaKit.getUnlocalizedName().substring(5));
/* 25 */     itemSandbagKitBeige = (new ItemSandbagKitBeige()).setUnlocalizedName("ItemSandbagKitBeige").setTextureName("bff:itemsandbagkitbeige").setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 26 */     GameRegistry.registerItem(itemSandbagKitBeige, itemSandbagKitBeige.getUnlocalizedName().substring(5));
/* 27 */     itemSandbagKit = (new ItemSandbagKit()).setUnlocalizedName("ItemSandbagKit").setTextureName("bff:itemsandbagkit").setCreativeTab(BlockFrontTabs.tabBlockFrontBlocks);
/* 28 */     GameRegistry.registerItem(itemSandbagKit, itemSandbagKit.getUnlocalizedName().substring(5));
/*    */     
/* 30 */     itemBackpackSmall = (new ItemBackpack(BackpackSize.SMALL)).setUnlocalizedName("ItemBackpackSmall").setTextureName("bff:itembackpacksmall").setCreativeTab(BlockFrontTabs.tabBlockFrontItems);
/* 31 */     GameRegistry.registerItem(itemBackpackSmall, itemBackpackSmall.getUnlocalizedName().substring(5));
/* 32 */     itemBackpackMedium = (new ItemBackpack(BackpackSize.MEDIUM)).setUnlocalizedName("ItemBackpackMedium").setTextureName("bff:itembackpackmedium").setCreativeTab(BlockFrontTabs.tabBlockFrontItems);
/* 33 */     GameRegistry.registerItem(itemBackpackMedium, itemBackpackMedium.getUnlocalizedName().substring(5));
/* 34 */     itemBackpackLarge = (new ItemBackpack(BackpackSize.LARGE)).setUnlocalizedName("ItemBackpackLarge").setTextureName("bff:itembackpacklarge").setCreativeTab(BlockFrontTabs.tabBlockFrontItems);
/* 35 */     GameRegistry.registerItem(itemBackpackLarge, itemBackpackLarge.getUnlocalizedName().substring(5));
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\BlockFrontItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */