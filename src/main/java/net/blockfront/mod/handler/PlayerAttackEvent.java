/*    */ package net.blockfront.mod.handler;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerAttackEvent
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onLivingAttack(LivingAttackEvent event) {
/* 21 */     if (event.entity instanceof net.minecraft.entity.player.EntityPlayerMP && event.source.getEntity() instanceof net.minecraft.entity.player.EntityPlayerMP) {
/*    */       
/* 23 */       Vec3 hitVec = getHitVec(event.source.getEntity(), event.entity);
/*    */       
/* 25 */       if (hitVec != null) {
/* 26 */         ((WorldServer)event.entity.worldObj).func_147487_a("blockcrack_152_1", hitVec.xCoord, hitVec.yCoord, hitVec.zCoord, 10, 0.0D, 0.0D, 0.0D, 0.0D);
/* 27 */         event.entity.playSound("bff:mob.impact", 1.0F, 1.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private static Vec3 getHitVec(Entity source, Entity target) {
/* 33 */     float dist = 100.0F;
/* 34 */     Vec3 pos = getPosition(source);
/* 35 */     Vec3 dir = getLook(source);
/* 36 */     Vec3 end = pos.addVector(dir.xCoord * dist, dir.yCoord * dist, dir.zCoord * dist);
/* 37 */     float collisionBorderSize = target.getCollisionBorderSize();
/* 38 */     MovingObjectPosition mop = target.boundingBox.expand(collisionBorderSize, collisionBorderSize, collisionBorderSize).calculateIntercept(pos, end);
/* 39 */     return (mop == null) ? null : mop.hitVec;
/*    */   }
/*    */   
/*    */   private static Vec3 getPosition(Entity entity) {
/* 43 */     return Vec3.createVectorHelper(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static Vec3 getLook(Entity entity) {
/* 52 */     float f1 = MathHelper.cos((float)(-entity.rotationYaw * 0.017453292D - Math.PI));
/* 53 */     float f2 = MathHelper.sin((float)(-entity.rotationYaw * 0.017453292D - Math.PI));
/* 54 */     float f3 = -MathHelper.cos(-entity.rotationPitch * 0.017453292F);
/* 55 */     float f4 = MathHelper.sin(-entity.rotationPitch * 0.017453292F);
/* 56 */     return Vec3.createVectorHelper((f2 * f3), f4, (f1 * f3));
/*    */   }
/*    */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\handler\PlayerAttackEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */