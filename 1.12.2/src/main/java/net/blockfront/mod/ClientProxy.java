/*     */ package net.blockfront.mod;
/*     */ import com.google.common.base.Verify;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.io.Files;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.invoke.MethodHandle;
/*     */ import java.lang.invoke.MethodHandles;
/*     */ import java.lang.invoke.MethodType;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.CompletionException;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.blockfront.mod.backpack.BackpackSlotInventory;
/*     */ import net.blockfront.mod.client.IngameStats;
/*     */ import net.blockfront.mod.client.StatPacket;
/*     */ import net.blockfront.mod.client.model.ModelBackpackMedium;
/*     */ import net.blockfront.mod.client.model.ModelBackpackSmall;
/*     */ import net.blockfront.mod.client.render.RenderAmericanRadio;
/*     */ import net.blockfront.mod.client.render.RenderBackpackLargeItem;
/*     */ import net.blockfront.mod.client.render.RenderBackpackMediumItem;
/*     */ import net.blockfront.mod.client.render.RenderBackpackSmallItem;
/*     */ import net.blockfront.mod.client.render.RenderJapaneseRadio;
/*     */ import net.blockfront.mod.client.render.RenderLootableBody;
/*     */ import net.blockfront.mod.client.render.RenderNaziBanner;
/*     */ import net.blockfront.mod.client.render.RenderNaziRadio;
/*     */ import net.blockfront.mod.client.render.RenderSovietBanner;
/*     */ import net.blockfront.mod.client.render.RenderSovietRadio;
import net.blockfront.mod.entity.EntityLootableBody;
/*     */ import net.blockfront.mod.net.AuthMessage;
/*     */ import net.blockfront.mod.net.MessageBackpackSlot;
/*     */ import net.blockfront.mod.net.ScreenshotDisplayPacket;
/*     */ import net.blockfront.mod.net.ScreenshotReqPacket;
/*     */ import net.blockfront.mod.net.ScreenshotRespPacket;
/*     */ import net.blockfront.mod.te.EntityAmericanRadio;
/*     */ import net.blockfront.mod.te.EntityJapaneseBanner;
/*     */ import net.blockfront.mod.te.EntityNaziBanner;
/*     */ import net.blockfront.mod.te.EntityNaziRadio;
/*     */ import net.blockfront.mod.te.EntitySovietBanner;
/*     */ import net.blockfront.mod.util.Scheduler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.gui.inventory.GuiContainerCreative;
/*     */ import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL30;
/*     */ 
/*     */ @SuppressWarnings({ "rawtypes", "deprecation" })
public class ClientProxy implements BlockFrontProxy {
/*     */   static final Class<?> creativeSlotClass;
/*     */   
/*     */   static {
/*  81 */     Class<?> cl = null;
/*  82 */     for (Class<?> innerClass : GuiContainerCreative.class.getDeclaredClasses()) {
/*  83 */       if (Slot.class.isAssignableFrom(innerClass)) {
/*  84 */         cl = innerClass;
/*     */         break;
/*     */       } 
/*     */     } 
/*  88 */     creativeSlotClass = (Class)Verify.verifyNotNull(cl);
/*     */     try {
/*  90 */       Field f = creativeSlotClass.getDeclaredField("field_148332_b");
/*  91 */       f.setAccessible(true);
/*  92 */       creativeSlotGetParentSlot = MethodHandles.publicLookup().unreflectGetter(f).asType(MethodType.methodType(Slot.class, Object.class));
/*  93 */     } catch (NoSuchFieldException|IllegalAccessException e) {
/*  94 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   static final MethodHandle creativeSlotGetParentSlot;
/*  98 */   static final ResourceLocation BlockFrontIcons = new ResourceLocation("bff", "textures/icons.png"); static final MethodHandle canRenderName; static final MethodHandle guiTopGetter; static final MethodHandle guiLeftGetter; public static File configDir;
/*     */   public static net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper authChannel;
/*     */   public static net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper mainChannel;
/*     */   public static volatile ResourceLocation bannerImage;
/*     */   
/*     */   static {
/*     */     try {
/* 105 */       Method m = ReflectionHelper.findMethod(RendererLivingEntity.class, null, new String[] { "canRenderName", "func_110813_b" }, new Class[] { EntityLivingBase.class });
/* 106 */       canRenderName = MethodHandles.publicLookup().unreflect(m);
/*     */       
/* 108 */       Field f = ReflectionHelper.findField(GuiContainer.class, new String[] { "guiTop", "field_147009_r" });
/* 109 */       guiTopGetter = MethodHandles.publicLookup().unreflectGetter(f);
/*     */       
/* 111 */       f = ReflectionHelper.findField(GuiContainer.class, new String[] { "guiLeft", "field_147003_i" });
/* 112 */       guiLeftGetter = MethodHandles.publicLookup().unreflectGetter(f);
/* 113 */     } catch (IllegalAccessException e) {
/* 114 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean useRemoteServersInDebug = true;
/*     */   
/*     */   private static IntBuffer intBuf;
/*     */   
/*     */   private static Map<Item, ModelBase> backpackModels;
/*     */   private static Map<Item, ResourceLocation> backpackTextures;
/*     */   
/*     */   @SuppressWarnings("unchecked")
public void preInit(FMLPreInitializationEvent event) {
/* 127 */     configDir = event.getModConfigurationDirectory();
/*     */ 
/*     */     
/* 130 */     authChannel = NetworkRegistry.INSTANCE.newSimpleChannel("DecimationAuth");
/* 131 */     authChannel.registerMessage(AuthMessage.Handler.class, AuthMessage.class, 0, Side.CLIENT);
/*     */     
/* 133 */     RenderManager rm = RenderManager.instance;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     mainChannel = NetworkRegistry.INSTANCE.newSimpleChannel("decimation");
/* 139 */     mainChannel.registerMessage(StatPacket.Handler.class, StatPacket.class, 0, Side.CLIENT);
/* 140 */     mainChannel.registerMessage(ScreenshotReqPacket.Handler.class, ScreenshotReqPacket.class, 1, Side.CLIENT);
/* 141 */     mainChannel.registerMessage(ScreenshotRespPacket.Handler.class, ScreenshotRespPacket.class, 2, Side.SERVER);
/* 142 */     mainChannel.registerMessage(ScreenshotDisplayPacket.Handler.class, ScreenshotDisplayPacket.class, 3, Side.CLIENT);
/* 143 */     BlockFrontKeys.init();
/* 144 */     MinecraftForgeClient.registerItemRenderer(BlockFrontItems.itemBackpackSmall, (ItemRenderer)new RenderBackpackSmallItem());
/* 145 */     MinecraftForgeClient.registerItemRenderer(BlockFrontItems.itemBackpackMedium, (ItemRenderer)new RenderBackpackMediumItem());
/* 146 */     MinecraftForgeClient.registerItemRenderer(BlockFrontItems.itemBackpackLarge, (ItemRenderer)new RenderBackpackLargeItem());
/* 147 */     RenderingRegistry.registerEntityRenderingHandler(EntityLootableBody.class, (Render)new RenderLootableBody(rm));
/*     */     
/* 149 */     ClientRegistry.bindTileEntitySpecialRenderer(EntityNaziRadio.class, (TileEntitySpecialRenderer)new RenderNaziRadio());
/* 150 */     ClientRegistry.bindTileEntitySpecialRenderer(EntityNaziBanner.class, (TileEntitySpecialRenderer)new RenderNaziBanner());
/* 151 */     ClientRegistry.bindTileEntitySpecialRenderer(EntityAmericanBanner.class, (TileEntitySpecialRenderer)new RenderAmericanBanner());
/* 152 */     ClientRegistry.bindTileEntitySpecialRenderer(EntityAmericanRadio.class, (TileEntitySpecialRenderer)new RenderAmericanRadio());
/* 153 */     ClientRegistry.bindTileEntitySpecialRenderer(EntityJapaneseBanner.class, (TileEntitySpecialRenderer)new RenderJapaneseBanner());
/* 154 */     ClientRegistry.bindTileEntitySpecialRenderer(EntityJapaneseBanner2.class, (TileEntitySpecialRenderer)new RenderJapaneseBanner2());
/* 155 */     ClientRegistry.bindTileEntitySpecialRenderer(EntityJapaneseRadio.class, (TileEntitySpecialRenderer)new RenderJapaneseRadio());
/* 156 */     ClientRegistry.bindTileEntitySpecialRenderer(EntitySovietBanner.class, (TileEntitySpecialRenderer)new RenderSovietBanner());
/* 157 */     ClientRegistry.bindTileEntitySpecialRenderer(EntitySovietRadio.class, (TileEntitySpecialRenderer)new RenderSovietRadio());
/* 158 */     MinecraftForge.EVENT_BUS.register(this);
/*     */     
/* 160 */     IngameStats.init(event.getModConfigurationDirectory());
/* 161 */     Runtime.getRuntime().addShutdownHook(new Thread()
/*     */         {
/*     */           public void run() {
/* 164 */             IngameStats.save();
/*     */           }
/*     */         });
/*     */     
/* 168 */     loadBanner("http://212.48.95.227/blockfrontmc.com/assets/factions/menu_news.png");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleBackpackSlotMessage(MessageBackpackSlot message) {
/* 177 */     Entity entity = (Minecraft.getMinecraft()).world.getEntityByID(message.entityId);
/* 178 */     if (entity instanceof EntityPlayer) {
/* 179 */       BackpackSlotInventory inv = BackpackSlotInventory.get((EntityPlayer)entity);
/* 180 */       inv.setInventorySlotContents(0, message.stack);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void loadBanner(String url) {
/* 185 */     CompletableFuture.<String>completedFuture(url)
/* 186 */       .thenApplyAsync(ClientProxy::downloadBanner, BlockFront.executor)
/* 187 */       .whenCompleteAsync((img, x) -> {
/*     */         
/* 189 */         }(Executor)Scheduler.client());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(FMLInitializationEvent event) {
/* 198 */     backpackModels = (Map<Item, ModelBase>)ImmutableMap.of(BlockFrontItems.itemBackpackSmall, new ModelBackpackSmall(), BlockFrontItems.itemBackpackMedium, new ModelBackpackMedium(), BlockFrontItems.itemBackpackLarge, new ModelBackpackLarge());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 204 */     backpackTextures = (Map<Item, ResourceLocation>)ImmutableMap.of(BlockFrontItems.itemBackpackSmall, RenderBackpackSmallItem.texture, BlockFrontItems.itemBackpackMedium, RenderBackpackMediumItem.texture, BlockFrontItems.itemBackpackLarge, RenderBackpackLargeItem.texture);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void renderPlayer(RenderPlayerEvent.Post event) {
/* 215 */     BackpackSlotInventory inv = BackpackSlotInventory.get(event.getEntityPlayer());
/* 216 */     ItemStack backpack = inv.getStackInSlot(0);
/*     */     ModelBase model;
/* 218 */     if (backpack != null && (model = backpackModels.get(backpack.getItem())) != null) {
/* 219 */       GL11.glPushMatrix();
/*     */       
/* 221 */       double x = event.getEntityPlayer().lastTickPosX + (event.getEntityPlayer().posX - event.getEntityPlayer().lastTickPosX) * event.getPartialRenderTick();
/* 222 */       double y = event.getEntityPlayer().lastTickPosY + (event.getEntityPlayer().posY - event.getEntityPlayer().lastTickPosY) * event.getPartialRenderTick();
/* 223 */       double z = event.getEntityPlayer().lastTickPosZ + (event.getEntityPlayer().posZ - event.getEntityPlayer().lastTickPosZ) * event.getPartialRenderTick();
/*     */ 
/*     */       
/* 226 */       float rotAngle = 180.0F - interpolateRotation(event.getEntityPlayer().prevRenderYawOffset, event.getEntityPlayer().renderYawOffset, event.getPartialRenderTick());
/* 227 */       double offset = (event.getEntityPlayer() instanceof net.minecraft.client.entity.EntityPlayerSP) ? 1.62D : 0.0D;
/* 228 */       if (event.getEntityPlayer().isSneaking() && !(event.getEntityPlayer() instanceof net.minecraft.client.entity.EntityPlayerSP)) {
/* 229 */         offset += 0.125D;
/*     */       }
/*     */       
/* 232 */       GL11.glTranslated(0.0D, 1.41D - offset, 0.0D);
/* 233 */       GL11.glTranslated(x - RenderManager.renderPosX, y - RenderManager.renderPosY, z - RenderManager.renderPosZ);
/*     */       
/* 235 */       GL11.glRotated(rotAngle, 0.0D, 1.0D, 0.0D);
/* 236 */       GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
/*     */       
/* 238 */       if (event.getEntityPlayer().isSneaking()) {
/* 239 */         GL11.glRotated(22.5D, 1.0D, 0.0D, 0.0D);
/*     */       }
/*     */       
/* 242 */       Minecraft.getMinecraft().getTextureManager().bindTexture(backpackTextures.get(backpack.getItem()));
/*     */       
/* 244 */       model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
/* 245 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static float interpolateRotation(float a, float b, float p_77034_3_) {
/*     */     float f3;
/* 252 */     for (f3 = b - a; f3 < -180.0F; f3 += 360.0F);
/*     */ 
/*     */ 
/*     */     
/* 256 */     while (f3 >= 180.0F) {
/* 257 */       f3 -= 360.0F;
/*     */     }
/*     */     
/* 260 */     return a + p_77034_3_ * f3;
/*     */   }
/*     */   
/*     */   public static BufferedImage downloadBanner(String url) {
/*     */     try {
/* 265 */       return ImageIO.read(new URL(url));
/* 266 */     } catch (IOException e) {
/* 267 */       throw new CompletionException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   static BufferedImage getScreenshot() {
/* 272 */     Minecraft mc = Minecraft.getMinecraft();
/* 273 */     int width = mc.displayWidth;
/* 274 */     int height = mc.displayHeight;
/*     */     
/* 276 */     GL11.glPixelStorei(3333, 1);
/* 277 */     GL11.glPixelStorei(3317, 1);
/*     */     
/* 279 */     int intCount = width * height;
/* 280 */     if (intBuf == null || intBuf.capacity() < intCount) {
/* 281 */       intBuf = BufferUtils.createIntBuffer(intCount);
/*     */     }
/* 283 */     intBuf.clear();
/*     */ 
/*     */     
/* 286 */     GL11.glReadPixels(0, 0, width, height, 32993, 33639, intBuf);
/*     */     
/* 288 */     GL30.glBindFramebuffer(36160, 0);
/*     */     
/* 290 */     BufferedImage img = new BufferedImage(width, height, 1);
/* 291 */     int[] imgData = ((DataBufferInt)img.getWritableTile(0, 0).getDataBuffer()).getData();
/*     */ 
/*     */     
/* 294 */     for (int row = height; row > 0;) {
/* 295 */       intBuf.get(imgData, --row * width, width);
/*     */     }
/*     */     
/* 298 */     return img;
/*     */   }
/*     */   
/*     */   static void saveAccessToken(UUID accessToken) {
/*     */     try {
/* 303 */       Files.write(accessToken.toString(), tokenFile(), StandardCharsets.UTF_8);
/* 304 */     } catch (IOException e) {
/* 305 */       BlockFront.log.error("Failed to write accessToken", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static File tokenFile() {
/* 312 */     return new File(configDir, "BlockFront.accesstoken.dat");
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */