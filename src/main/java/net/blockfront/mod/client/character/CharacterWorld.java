/*      */ package net.blockfront.mod.client.character;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.io.File;
import java.util.Calendar;
/*      */ import java.util.Collection;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.command.IEntitySelector;
/*      */ import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.entity.EnumCreatureType;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.pathfinding.PathEntity;
import net.minecraft.scoreboard.Scoreboard;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IProgressUpdate;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.Vec3;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
/*      */ import net.minecraft.world.ChunkCoordIntPair;
/*      */ import net.minecraft.world.ChunkPosition;
import net.minecraft.world.DimensionType;
/*      */ import net.minecraft.world.EnumSkyBlock;
/*      */ import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
/*      */ import net.minecraft.world.IWorldAccess;
import net.minecraft.world.MinecraftException;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.WorldSavedData;
/*      */ import net.minecraft.world.WorldSettings;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import net.minecraft.world.chunk.Chunk;
/*      */ import net.minecraft.world.chunk.IChunkProvider;
/*      */ import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.TemplateManager;
/*      */ import net.minecraft.world.storage.IPlayerFileData;
/*      */ import net.minecraft.world.storage.ISaveHandler;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import net.minecraftforge.client.IRenderHandler;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ 
/*      */ public class CharacterWorld<MovingObjectPosition, IWorldAccess> extends World {
/*      */   public CharacterWorld() {
/*   45 */     super(new FakeSaveHandler(), "", new FakeWorldProvider(), new WorldSettings(new WorldInfo(new NBTTagCompound())), null);
/*   46 */     this.difficultySetting = EnumDifficulty.HARD;
/*      */   }
/*      */ 
/*      */   
/*      */   public BiomeGenBase getBiomeGenForCoords(int par1, int par2) {
/*   51 */     return BiomeGenBase.plains;
/*      */   }
/*      */ 
/*      */   
/*      */   public BiomeGenBase getBiomeGenForCoordsBody(int par1, int par2) {
/*   56 */     return BiomeGenBase.plains;
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldChunkManager getWorldChunkManager() {
/*   61 */     return super.getWorldChunkManager();
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   protected void finishSetup() {
/*   67 */     super.finishSetup();
/*      */   }
/*      */ 
/*      */   
/*      */   public void initialize(WorldSettings par1WorldSettings) {
/*   72 */     super.initialize(par1WorldSettings);
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public void setSpawnLocation() {
/*   78 */     super.setInitialSpawnLocation();
/*      */   }
/*      */ 
/*      */   
/*      */   public Block getTopBlock(int x, int z) {
/*   83 */     return (Block)Blocks.GRASS;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAirBlock(int x, int y, int z) {
/*   88 */     return (y > 63);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean doChunksNearChunkExist(int par1, int par2, int par3, int par4) {
/*   93 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean checkChunksExist(int par1, int par2, int par3, int par4, int par5, int par6) {
/*   98 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Chunk getChunkFromBlockCoords(int x, int z) {
/*  103 */     return super.getChunkFromChunkCoords(x, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setBlock(int x, int y, int z, Block block, int meta, int notify) {
/*  108 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBlockMetadata(int x, int y, int z) {
/*  113 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setBlockMetadataWithNotify(int x, int y, int z, int par4, int par5) {
/*  118 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setBlockToAir(int x, int y, int z) {
/*  123 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setBlock(int x, int y, int z, Block p_147449_4_) {
/*  128 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void markBlockForUpdate(int p_147471_1_, int p_147471_2_, int p_147471_3_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void notifyBlockChange(int p_147444_1_, int p_147444_2_, int p_147444_3_, Block p_147444_4_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void markBlocksDirtyVertical(int par1, int par2, int par3, int par4) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void markBlockRangeForRenderUpdate(int p_147458_1_, int p_147458_2_, int p_147458_3_, int p_147458_4_, int p_147458_5_, int p_147458_6_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void notifyBlocksOfNeighborChange(int p_147459_1_, int p_147459_2_, int p_147459_3_, Block p_147459_4_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void notifyBlocksOfNeighborChange(int p_147441_1_, int p_147441_2_, int p_147441_3_, Block p_147441_4_, int p_147441_5_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void notifyBlockOfNeighborChange(int p_147460_1_, int p_147460_2_, int p_147460_3_, Block p_147460_4_) {}
/*      */ 
/*      */   
/*      */   public boolean isBlockTickScheduledThisTick(int p_147477_1_, int p_147477_2_, int p_147477_3_, Block p_147477_4_) {
/*  161 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBlockSeeTheSky(int x, int y, int z) {
/*  166 */     return (y > 62);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFullBlockLightValue(int x, int y, int z) {
/*  171 */     return 14;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBlockLightValue(int x, int y, int z) {
/*  176 */     return 14;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBlockLightValue_do(int x, int y, int z, boolean par4) {
/*  181 */     return 14;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHeightValue(int x, int z) {
/*  186 */     return 63;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getChunkHeightMapMinimum(int x, int z) {
/*  191 */     return 63;
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public int getSkyBlockTypeBrightness(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4) {
/*  197 */     return 14;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSavedLightValue(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4) {
/*  202 */     return 14;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLightValue(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4, int par5) {}
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public int getLightBrightnessForSkyBlocks(int par1, int par2, int par3, int par4) {
/*  212 */     return 14;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getLightBrightness(int par1, int par2, int par3) {
/*  217 */     return super.getLightBrightness(par1, par2, par3);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDaytime() {
/*  222 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public MovingObjectPosition rayTraceBlocks(Vec2f par1Vec3, Vec3d par2Vec3) {
/*  227 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public MovingObjectPosition rayTraceBlocks(Vec2f par1Vec3, Vec3d par2Vec3, boolean par3) {
/*  232 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSoundAtEntity(Entity par1Entity, String par2Str, float par3, float par4) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSoundToNearExcept(EntityPlayer par1EntityPlayer, String par2Str, float par3, float par4) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSoundEffect(double par1, double par3, double par5, String par7Str, float par8, float par9) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSound(double par1, double par3, double par5, String par7Str, float par8, float par9, boolean par10) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void playRecord(String par1Str, int par2, int par3, int par4) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnParticle(String par1Str, double par2, double par4, double par6, double par8, double par10, double par12) {}
/*      */ 
/*      */   
/*      */   public boolean addWeatherEffect(Entity par1Entity) {
/*  261 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean spawnEntityInWorld(Entity par1Entity) {
/*  266 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityAdded(Entity par1Entity) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityRemoved(Entity par1Entity) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeEntity(Entity par1Entity) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void removePlayerEntityDangerously(Entity par1Entity) {}
/*      */ 
/*      */   
/*      */   public void addWorldAccess(IWorldAccess par1iWorldAccess) {
/*      */   }
/*      */ 
/*      */   
/*      */   public List getCollidingBoundingBoxes(Entity par1Entity, net.minecraft.util.math.AxisAlignedBB par2AxisAlignedBB) {
/*  292 */     return super.getCollisionBoxes(par1Entity, par2AxisAlignedBB);
/*      */   }
/*      */ 
/*      */   
/*      */   public List func_147461_a(net.minecraft.util.math.AxisAlignedBB p_147461_1_) {
/*  297 */     return superpar1Entity; par2AxisAlignedBB(p_147461_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int calculateSkylightSubtracted(float par1) {
/*  302 */     return 6;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeWorldAccess(IWorldAccess par1iWorldAccess) {}
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public float getSunBrightness(float par1) {
/*  312 */     return super.getSunBrightness(par1);
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public Vec3 getSkyColor(Entity par1Entity, float par2) {
/*  318 */     return super.getSkyColor(par1Entity, par2);
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public Vec3 getSkyColorBody(Entity par1Entity, float par2) {
/*  324 */     return super.getSkyColorBody(par1Entity, par2);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getCelestialAngle(float par1) {
/*  329 */     return super.getCelestialAngle(par1);
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public int getMoonPhase() {
/*  335 */     return super.getMoonPhase();
/*      */   }
/*      */ 
/*      */   
/*      */   public float getCurrentMoonPhaseFactor() {
/*  340 */     return super.getCurrentMoonPhaseFactor();
/*      */   }
/*      */ 
/*      */   
/*      */   public float getCelestialAngleRadians(float par1) {
/*  345 */     return super.getCelestialAngleRadians(par1);
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public Vec3 getCloudColour(float par1) {
/*  351 */     return super.getCloudColour(par1);
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public Vec3 drawCloudsBody(float par1) {
/*  357 */     return super.drawCloudsBody(par1);
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public Vec3 getFogColor(float par1) {
/*  363 */     return super.getFogColor(par1);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPrecipitationHeight(int par1, int par2) {
/*  368 */     return super.getPrecipitationHeight(par1, par2);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTopSolidOrLiquidBlock(int par1, int par2) {
/*  373 */     return 63;
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public float getStarBrightness(float par1) {
/*  379 */     return super.getStarBrightness(par1);
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public float getStarBrightnessBody(float par1) {
/*  385 */     return super.getStarBrightnessBody(par1);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void scheduleBlockUpdate(int p_147464_1_, int p_147464_2_, int p_147464_3_, Block p_147464_4_, int p_147464_5_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void scheduleBlockUpdateWithPriority(int p_147454_1_, int p_147454_2_, int p_147454_3_, Block p_147454_4_, int p_147454_5_, int p_147454_6_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_147446_b(int p_147446_1_, int p_147446_2_, int p_147446_3_, Block p_147446_4_, int p_147446_5_, int p_147446_6_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateEntities() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_147448_a(Collection p_147448_1_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateEntity(Entity par1Entity) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateEntityWithOptionalForce(Entity par1Entity, boolean par2) {}
/*      */ 
/*      */   
/*      */   public boolean checkNoEntityCollision(AxisAlignedBB par1AxisAlignedBB) {
/*  418 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean checkNoEntityCollision(AxisAlignedBB par1AxisAlignedBB, Entity par2Entity) {
/*  423 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean checkBlockCollision(AxisAlignedBB par1AxisAlignedBB) {
/*  428 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAnyLiquid(AxisAlignedBB par1AxisAlignedBB) {
/*  433 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_147470_e(AxisAlignedBB p_147470_1_) {
/*  438 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean handleMaterialAcceleration(AxisAlignedBB par1AxisAlignedBB, Material par2Material, Entity par3Entity) {
/*  443 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isMaterialInBB(AxisAlignedBB par1AxisAlignedBB, Material par2Material) {
/*  448 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAABBInMaterial(AxisAlignedBB par1AxisAlignedBB, Material par2Material) {
/*  453 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Explosion createExplosion(Entity par1Entity, double par2, double par4, double par6, float par8, boolean par9) {
/*  458 */     return super.createExplosion(par1Entity, par2, par4, par6, par8, par9);
/*      */   }
/*      */ 
/*      */   
/*      */   public Explosion newExplosion(Entity par1Entity, double par2, double par4, double par6, float par8, boolean par9, boolean par10) {
/*  463 */     return super.newExplosion(par1Entity, par2, par4, par6, par8, par9, par10);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getBlockDensity(Vec3 par1Vec3, AxisAlignedBB par2AxisAlignedBB) {
/*  468 */     return super.getBlockDensity(par1Vec3, par2AxisAlignedBB);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean extinguishFire(EntityPlayer par1EntityPlayer, int par2, int par3, int par4, int par5) {
/*  473 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public String getDebugLoadedEntities() {
/*  479 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public String getProviderName() {
/*  485 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public TileEntity getTileEntity(int x, int y, int z) {
/*  490 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTileEntity(int x, int y, int z, TileEntity p_147455_4_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeTileEntity(int x, int y, int z) {}
/*      */ 
/*      */   
/*      */   public boolean isBlockNormalCubeDefault(int p_147445_1_, int p_147445_2_, int p_147445_3_, boolean p_147445_4_) {
/*  503 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void calculateInitialSkylight() {
/*  508 */     super.calculateInitialSkylight();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAllowedSpawnTypes(boolean par1, boolean par2) {
/*  513 */     super.setAllowedSpawnTypes(par1, par2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void tick() {}
/*      */ 
/*      */   
/*      */   public void calculateInitialWeatherBody() {
/*  522 */     super.calculateInitialWeatherBody();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateWeather() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateWeatherBody() {}
/*      */ 
/*      */   
/*      */   protected void setActivePlayerChunksAndCheckLight() {
/*  535 */     super.setActivePlayerChunksAndCheckLight();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int func_152379_p() {
/*  540 */     return 4;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_147467_a(int p_147467_1_, int p_147467_2_, Chunk p_147467_3_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_147456_g() {}
/*      */ 
/*      */   
/*      */   public boolean isBlockFreezable(int par1, int par2, int par3) {
/*  553 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockFreezableNaturally(int par1, int par2, int par3) {
/*  558 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBlockFreeze(int par1, int par2, int par3, boolean par4) {
/*  563 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBlockFreezeBody(int par1, int par2, int par3, boolean par4) {
/*  568 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canSnowAtBody(int p_147478_1_, int p_147478_2_, int p_147478_3_, boolean p_147478_4_) {
/*  573 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean updateLightByType(EnumSkyBlock p_147463_1_, int p_147463_2_, int p_147463_3_, int p_147463_4_) {
/*  578 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean tickUpdates(boolean par1) {
/*  583 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public List getPendingBlockUpdates(Chunk par1Chunk, boolean par2) {
/*  588 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public List getEntitiesWithinAABBExcludingEntity(Entity par1Entity, net.minecraft.util.math.AxisAlignedBB par2AxisAlignedBB) {
/*  593 */     return super.getEntitiesWithinAABBExcludingEntity(par1Entity, par2AxisAlignedBB);
/*      */   }
/*      */ 
/*      */   
/*      */   public List getEntitiesWithinAABBExcludingEntity(Entity par1Entity, net.minecraft.util.math.AxisAlignedBB par2AxisAlignedBB, IEntitySelector par3iEntitySelector) {
/*  598 */     return super.getEntitiesWithinAABBExcludingEntity(par1Entity, par2AxisAlignedBB, par3iEntitySelector);
/*      */   }
/*      */ 
/*      */   
/*      */   public List getEntitiesWithinAABB(Class par1Class, net.minecraft.util.math.AxisAlignedBB par2AxisAlignedBB) {
/*  603 */     return super.getEntitiesWithinAABB(par1Class, par2AxisAlignedBB);
/*      */   }
/*      */ 
/*      */   
/*      */   public List selectEntitiesWithinAABB(Class par1Class, net.minecraft.util.math.AxisAlignedBB par2AxisAlignedBB, IEntitySelector par3iEntitySelector) {
/*  608 */     return super.selectEntitiesWithinAABB(par1Class, par2AxisAlignedBB, par3iEntitySelector);
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity findNearestEntityWithinAABB(Class par1Class, AxisAlignedBB par2AxisAlignedBB, Entity par3Entity) {
/*  613 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public List getLoadedEntityList() {
/*  619 */     return super.getLoadedEntityList();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void markTileEntityChunkModified(int p_147476_1_, int p_147476_2_, int p_147476_3_, TileEntity p_147476_4_) {}
/*      */ 
/*      */   
/*      */   public int countEntities(Class par1Class) {
/*  628 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addLoadedEntities(List par1List) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void unloadEntities(List par1List) {}
/*      */ 
/*      */   
/*      */   public boolean canPlaceEntityOnSide(Block p_147472_1_, int p_147472_2_, int p_147472_3_, int p_147472_4_, boolean p_147472_5_, int p_147472_6_, Entity p_147472_7_, ItemStack p_147472_8_) {
/*  641 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public PathEntity getPathEntityToEntity(Entity par1Entity, Entity par2Entity, float par3, boolean par4, boolean par5, boolean par6, boolean par7) {
/*  646 */     return super.getPathEntityToEntity(par1Entity, par2Entity, par3, par4, par5, par6, par7);
/*      */   }
/*      */ 
/*      */   
/*      */   public PathEntity getEntityPathToXYZ(Entity par1Entity, int par2, int par3, int par4, float par5, boolean par6, boolean par7, boolean par8, boolean par9) {
/*  651 */     return super.getEntityPathToXYZ(par1Entity, par2, par3, par4, par5, par6, par7, par8, par9);
/*      */   }
/*      */ 
/*      */   
/*      */   public int isBlockProvidingPowerTo(int par1, int par2, int par3, int par4) {
/*  656 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBlockPowerInput(int par1, int par2, int par3) {
/*  661 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIndirectPowerOutput(int par1, int par2, int par3, int par4) {
/*  666 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getIndirectPowerLevelTo(int par1, int par2, int par3, int par4) {
/*  671 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockIndirectlyGettingPowered(int par1, int par2, int par3) {
/*  676 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getStrongestIndirectPower(int par1, int par2, int par3) {
/*  681 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayer getClosestPlayerToEntity(Entity par1Entity, double par2) {
/*  686 */     return super.getClosestPlayerToEntity(par1Entity, par2);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayer getClosestPlayer(double par1, double par3, double par5, double par7) {
/*  691 */     return super.getClosestPlayer(par1, par3, par5, par7);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayer getClosestVulnerablePlayerToEntity(Entity par1Entity, double par2) {
/*  696 */     return super.getClosestVulnerablePlayerToEntity(par1Entity, par2);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayer getClosestVulnerablePlayer(double par1, double par3, double par5, double par7) {
/*  701 */     return super.getClosestVulnerablePlayer(par1, par3, par5, par7);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayer getPlayerEntityByName(String par1Str) {
/*  706 */     return super.getPlayerEntityByName(par1Str);
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public void sendQuittingDisconnectingPacket() {
/*  712 */     super.sendQuittingDisconnectingPacket();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void checkSessionLock() throws MinecraftException {}
/*      */ 
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public void func_82738_a(long par1) {}
/*      */ 
/*      */   
/*      */   public long getSeed() {
/*  726 */     return 1L;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTotalWorldTime() {
/*  731 */     return 1L;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getWorldTime() {
/*  736 */     return 1L;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWorldTime(long par1) {}
/*      */ 
/*      */   
/*      */   public ChunkCoordinates getSpawnPoint() {
/*  745 */     return new ChunkCoordinates(0, 64, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSpawnLocation(int par1, int par2, int par3) {
/*  750 */     super.setInitialSpawnLocation();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public void joinEntityInSurroundings(Entity par1Entity) {}
/*      */ 
/*      */   
/*      */   public boolean canMineBlock(EntityPlayer par1EntityPlayer, int par2, int par3, int par4) {
/*  760 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canMineBlockBody(EntityPlayer par1EntityPlayer, int par2, int par3, int par4) {
/*  765 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEntityState(Entity par1Entity, byte par2) {}
/*      */ 
/*      */   
/*      */   public IChunkProvider getChunkProvider() {
/*  774 */     return super.getChunkProvider();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addBlockEvent(int p_147452_1_, int p_147452_2_, int p_147452_3_, Block p_147452_4_, int p_147452_5_, int p_147452_6_) {}
/*      */ 
/*      */   
/*      */   public ISaveHandler getSaveHandler() {
/*  783 */     return super.getSaveHandler();
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldInfo getWorldInfo() {
/*  788 */     return super.getWorldInfo();
/*      */   }
/*      */ 
/*      */   
/*      */   public GameRules getGameRules() {
/*  793 */     return super.getGameRules();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateAllPlayersSleepingFlag() {}
/*      */ 
/*      */   
/*      */   public float getWeightedThunderStrength(float par1) {
/*  802 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public void setThunderStrength(float p_147442_1_) {}
/*      */ 
/*      */   
/*      */   public float getRainStrength(float par1) {
/*  812 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public void setRainStrength(float par1) {}
/*      */ 
/*      */   
/*      */   public boolean isThundering() {
/*  822 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isRaining() {
/*  827 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockHighHumidity(int par1, int par2, int par3) {
/*  832 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItemData(String par1Str, net.minecraft.world.storage.WorldSavedData par2WorldSavedData) {}
/*      */ 
/*      */   
/*      */   public net.minecraft.world.storage.WorldSavedData loadItemData(Class par1Class, String par2Str) {
/*  841 */     return super.loadData(par1Class, par2Str);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getUniqueDataId(String par1Str) {
/*  846 */     return super.getUniqueDataId(par1Str);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void playBroadcastSound(int par1, int par2, int par3, int par4, int par5) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void playAuxSFX(int par1, int par2, int par3, int par4, int par5) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void playAuxSFXAtEntity(EntityPlayer par1EntityPlayer, int par2, int par3, int par4, int par5, int par6) {}
/*      */ 
/*      */   
/*      */   public int getHeight() {
/*  863 */     return 256;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getActualHeight() {
/*  868 */     return 256;
/*      */   }
/*      */ 
/*      */   
/*      */   public Random setRandomSeed(int par1, int par2, int par3) {
/*  873 */     return super.setRandomSeed(par1, par2, par3);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChunkPos findClosestStructure(String p_147440_1_, int p_147440_2_, int p_147440_3_, int p_147440_4_) {
/*  878 */     return super.findClosestStructure(p_147440_1_, p_147440_2_, p_147440_3_, p_147440_4_);
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public boolean extendedLevelsInChunkCache() {
/*  884 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public double getHorizon() {
/*  890 */     return super.getHorizon();
/*      */   }
/*      */ 
/*      */   
/*      */   public CrashReportCategory addWorldInfoToCrashReport(CrashReport par1CrashReport) {
/*  895 */     return super.addWorldInfoToCrashReport(par1CrashReport);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void destroyBlockInWorldPartially(int p_147443_1_, int p_147443_2_, int p_147443_3_, int p_147443_4_, int p_147443_5_) {}
/*      */ 
/*      */   
/*      */   public Calendar getCurrentDate() {
/*  904 */     return super.getCurrentDate();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */   public void makeFireworks(double par1, double par3, double par5, double par7, double par9, double par11, NBTTagCompound par13nbtTagCompound) {}
/*      */ 
/*      */   
/*      */   public Scoreboard getScoreboard() {
/*  914 */     return super.getScoreboard();
/*      */   }
/*      */ 
/*      */   
/*      */   public float func_147473_B(int p_147473_1_, int p_147473_2_, int p_147473_3_) {
/*  919 */     return super.func_147473_B(p_147473_1_, p_147473_2_, p_147473_3_);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_147450_X() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void addTileEntity(TileEntity entity) {}
/*      */ 
/*      */   
/*      */   public boolean isSideSolid(int x, int y, int z, ForgeDirection side) {
/*  932 */     return (y <= 63);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSideSolid(int x, int y, int z, ForgeDirection side, boolean _default) {
/*  937 */     return (y <= 63);
/*      */   }
/*      */ 
/*      */   
/*      */   public ImmutableSetMultimap<ChunkCoordIntPair, ForgeChunkManager.Ticket> getPersistentChunks() {
/*  942 */     return super.getPersistentChunks();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBlockLightOpacity(int x, int y, int z) {
/*  947 */     return super.getBlockLightOpacity(x, y, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public int countEntities(EnumCreatureType type, boolean forSpawnCount) {
/*  952 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean blockExists(int par1, int par2, int par3) {
/*  957 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean chunkExists(int par1, int par2) {
/*  962 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected IChunkProvider createChunkProvider() {
/*  967 */     return new FakeChunkProvider();
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getEntityByID(int i) {
/*  972 */     return EntityList.createEntityByID(i, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public Chunk getChunkFromChunkCoords(int par1, int par2) {
/*  977 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Block getBlock(int x, int y, int z) {
/*  982 */     return (y > 63) ? Blocks.air : (Block)Blocks.grass;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static class FakeWorldProvider
/*      */     extends WorldProvider
/*      */   {
/*      */     protected void generateLightBrightnessTable() {}
/*      */     
/*      */     protected void registerWorldChunkManager() {
/*  992 */       super.registerWorldChunkManager();
/*      */     }
/*      */ 
/*      */     
/*      */     public IChunkGenerator createChunkGenerator() {
/*  997 */       return super.createChunkGenerator();
/*      */     }
/*      */ 
/*      */     
/*      */     public float calculateCelestialAngle(long par1, float par3) {
/* 1002 */       return super.calculateCelestialAngle(par1, par3);
/*      */     }
/*      */ 
/*      */     
/*      */     public int getMoonPhase(long par1) {
/* 1007 */       return super.getMoonPhase(par1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isSurfaceWorld() {
/* 1012 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public float[] calcSunriseSunsetColors(float par1, float par2) {
/* 1018 */       return super.calcSunriseSunsetColors(par1, par2);
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public Vec3 getFogColor(float par1, float par2) {
/* 1024 */       return super.getFogColor(par1, par2);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canRespawnHere() {
/* 1029 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public float getCloudHeight() {
/* 1035 */       return super.getCloudHeight();
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public boolean isSkyColored() {
/* 1041 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public ChunkCoordinates getEntrancePortalLocation() {
/* 1046 */       return super.getEntrancePortalLocation();
/*      */     }
/*      */ 
/*      */     
/*      */     public int getAverageGroundLevel() {
/* 1051 */       return 63;
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public boolean getWorldHasVoidParticles() {
/* 1057 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public double getVoidFogYFactor() {
/* 1063 */       return super.getVoidFogYFactor();
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public boolean doesXZShowFog(int par1, int par2) {
/* 1069 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void setDimension(int dim) {}
/*      */ 
/*      */     
/*      */     public String getSaveFolder() {
/* 1078 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getWelcomeMessage() {
/* 1083 */       return "";
/*      */     }
/*      */ 
/*      */     
/*      */     public String getDepartMessage() {
/* 1088 */       return "";
/*      */     }
/*      */ 
/*      */     
/*      */     public double getMovementFactor() {
/* 1093 */       return super.getMovementFactor();
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public IRenderHandler getSkyRenderer() {
/* 1099 */       return super.getSkyRenderer();
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public void setSkyRenderer(IRenderHandler skyRenderer) {
/* 1105 */       super.setSkyRenderer(skyRenderer);
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public IRenderHandler getCloudRenderer() {
/* 1111 */       return super.getCloudRenderer();
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public void setCloudRenderer(IRenderHandler renderer) {
/* 1117 */       super.setCloudRenderer(renderer);
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public IRenderHandler getWeatherRenderer() {
/* 1123 */       return super.getWeatherRenderer();
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public void setWeatherRenderer(IRenderHandler renderer) {
/* 1129 */       super.setWeatherRenderer(renderer);
/*      */     }
/*      */ 
/*      */     
/*      */     public ChunkCoordinates getRandomizedSpawnPoint() {
/* 1134 */       return new ChunkCoordinates(0, 64, 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean shouldMapSpin(String entity, double x, double y, double z) {
/* 1139 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getRespawnDimension(EntityPlayerMP player) {
/* 1144 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*      */     public BiomeGenBase getBiomeGenForCoords(int x, int z) {
/* 1149 */       return BiomeGenBase.plains;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isDaytime() {
/* 1154 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
/* 1160 */       return super.getSkyColor(cameraEntity, partialTicks);
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public Vec3 drawClouds(float partialTicks) {
/* 1166 */       return super.drawClouds(partialTicks);
/*      */     }
/*      */ 
/*      */     
/*      */     @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
/*      */     public float getStarBrightness(float par1) {
/* 1172 */       return super.getStarBrightness(par1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void setAllowedSpawnTypes(boolean allowHostile, boolean allowPeaceful) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void calculateInitialWeather() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void updateWeather() {}
/*      */ 
/*      */     
/*      */     public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
/* 1189 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canSnowAt(int x, int y, int z, boolean checkLight) {
/* 1194 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void setWorldTime(long time) {}
/*      */ 
/*      */     
/*      */     public long getSeed() {
/* 1203 */       return 1L;
/*      */     }
/*      */ 
/*      */     
/*      */     public long getWorldTime() {
/* 1208 */       return 1L;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setSpawnPoint(int x, int y, int z) {
/* 1213 */       super.setSpawnPoint(x, y, z);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canMineBlock(EntityPlayer player, int x, int y, int z) {
/* 1218 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isBlockHighHumidity(int x, int y, int z) {
/* 1223 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getHeight() {
/* 1228 */       return 256;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getActualHeight() {
/* 1233 */       return 256;
/*      */     }
/*      */ 
/*      */     
/*      */     public double getHorizon() {
/* 1238 */       return super.getHorizon();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void resetRainAndThunder() {}
/*      */ 
/*      */     
/*      */     public boolean canDoLightning(Chunk chunk) {
/* 1247 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canDoRainSnowIce(Chunk chunk) {
/* 1252 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getDimensionName() {
/* 1257 */       return "";
/*      */     }
/*      */ 
/*      */     
/*      */     public ChunkCoordinates getSpawnPoint() {
/* 1262 */       return new ChunkCoordinates(0, 64, 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canCoordinateBeSpawn(int par1, int par2) {
/* 1267 */       return true;
/*      */     }
/*      */
@Override
public DimensionType getDimensionType() {
	// TODO Auto-generated method stub
	return null;
}   }
/*      */   
/*      */   protected static class FakeSaveHandler
/*      */     implements ISaveHandler {
/*      */     public WorldInfo loadWorldInfo() {
/* 1274 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void checkSessionLock() {}
/*      */ 
/*      */     
/*      */     public IChunkLoader getChunkLoader(WorldProvider var1) {
/* 1283 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void saveWorldInfoWithPlayer(WorldInfo var1, NBTTagCompound var2) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void saveWorldInfo(WorldInfo var1) {}
/*      */ 
/*      */     
/*      */     public IPlayerFileData getSaveHandler() {
/* 1296 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void flush() {}
/*      */ 
/*      */     
/*      */     public File getWorldDirectory() {
/* 1305 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public File getMapFileFromName(String var1) {
/* 1310 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getWorldDirectoryName() {
/* 1315 */       return null;
/*      */     }
/*      */
@Override
public IPlayerFileData getPlayerNBTManager() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public TemplateManager getStructureTemplateManager() {
	// TODO Auto-generated method stub
	return null;
}   }
/*      */   
/*      */   public static class FakeChunkProvider
/*      */     implements IChunkProvider {
/*      */     public boolean chunkExists(int var1, int var2) {
/* 1322 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public Chunk provideChunk(int var1, int var2) {
/* 1327 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public Chunk loadChunk(int var1, int var2) {
/* 1332 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void populate(IChunkProvider var1, int var2, int var3) {}
/*      */ 
/*      */     
/*      */     public boolean saveChunks(boolean var1, IProgressUpdate var2) {
/* 1341 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean unloadQueuedChunks() {
/* 1346 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canSave() {
/* 1351 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public String makeString() {
/* 1356 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3, int var4) {
/* 1361 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
/* 1366 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getLoadedChunkCount() {
/* 1371 */       return 0;
/*      */     }
/*      */     
/*      */     public void recreateStructures(int var1, int var2) {}
/*      */     
/*      */     public void saveExtraData() {}
/*      */
@Override
public Chunk getLoadedChunk(int x, int z) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public boolean tick() {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean isChunkGeneratedAt(int x, int z) {
	// TODO Auto-generated method stub
	return false;
}   }
/*      */
@Override
protected boolean isChunkLoaded(int x, int z, boolean allowEmpty) {
	// TODO Auto-generated method stub
	return false;
} }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\character\CharacterWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */