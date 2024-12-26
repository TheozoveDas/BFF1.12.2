/*    */ package net.blockfront.mod.client.character;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.stats.StatBase;
/*    */ import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class CharacterPlayer
/*    */   extends AbstractClientPlayer
/*    */ {
/*    */   public CharacterPlayer(World world, GameProfile profile) {
/* 16 */     super(world, profile);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addChatMessage(ITextComponent message) {}
/*    */ 
/*    */   
/*    */   public boolean canCommandSenderUseCommand(int i, String s) {
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChunkCoordinates getPlayerCoordinates() {
/* 30 */     return new ChunkCoordinates(0, 0, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addChatComponentMessage(ITextComponent chatMessageComponent) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void addStat(StatBase par1StatBase, int par2) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void openGui(Object mod, int modGuiId, World world, int x, int y, int z) {}
/*    */ 
/*    */   
/*    */   public boolean canAttackPlayer(EntityPlayer player) {
/* 47 */     return false;
/*    */   }
/*    */   
/*    */   public void onDeath(DamageSource source) {}
/*    */   
/*    */   public void onUpdate() {}
/*    */   
/*    */   public void travelToDimension(int dim) {}
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\character\CharacterPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */