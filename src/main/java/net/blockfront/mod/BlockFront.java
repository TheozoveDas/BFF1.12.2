/*     */ package net.blockfront.mod;
import com.google.common.base.Throwables;
/*     */ 
/*     */ import com.google.common.io.Resources;
/*     */ import com.google.gson.Gson;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.ByteBufOutputStream;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.blockfront.mod.backpack.BackpackInPlayerInvSlot;
/*     */ import net.blockfront.mod.backpack.BackpackSlotInventory;
/*     */ import net.blockfront.mod.client.IngameStats;
/*     */ import net.blockfront.mod.client.character.CharacterPlayerFactory;
/*     */ import net.blockfront.mod.entity.EntityLootableBody;
/*     */ import net.blockfront.mod.handler.PlayerAttackEvent;
/*     */ import net.blockfront.mod.handler.PlayerDeathEventHandler;
/*     */ import net.blockfront.mod.net.MessageBackpackSlot;
/*     */ import net.blockfront.mod.net.MessageKey;
/*     */ import net.blockfront.mod.net.ScreenshotReqPacket;
/*     */ import net.blockfront.mod.net.ScreenshotRespPacket;
/*     */ import net.blockfront.mod.te.EntityAmericanBanner;
/*     */ import net.blockfront.mod.te.EntityAmericanRadio;
/*     */ import net.blockfront.mod.te.EntityJapaneseBanner;
/*     */ import net.blockfront.mod.te.EntityJapaneseBanner2;
/*     */ import net.blockfront.mod.te.EntityJapaneseRadio;
/*     */ import net.blockfront.mod.te.EntityNaziBanner;
/*     */ import net.blockfront.mod.te.EntityNaziRadio;
/*     */ import net.blockfront.mod.util.Scheduler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.gui.inventory.GuiContainerCreative;
/*     */ import net.minecraft.command.CommandHandler;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.boss.EntityDragon;
/*     */ import net.minecraft.entity.monster.EntityBlaze;
/*     */ import net.minecraft.entity.monster.EntityCaveSpider;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.monster.EntityPigZombie;
/*     */ import net.minecraft.entity.monster.EntitySilverfish;
/*     */ import net.minecraft.entity.monster.EntitySkeleton;
/*     */ import net.minecraft.entity.monster.EntitySlime;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.client.event.GuiScreenEvent;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ @SuppressWarnings("deprecation")
@Mod(modid = "bff", version = "1.0")
/*     */ public class BlockFront
/*     */ {
/*     */   public static final String MOD_ID = "bff";
/* 108 */   public static final SimpleNetworkWrapper mainNetworkChannel = NetworkRegistry.INSTANCE.newSimpleChannel("ctx");
/*     */ 
/*     */   
/*     */   public boolean isBanned = false;
/*     */   
/*     */   @Instance("bff")
/*     */   public static BlockFront instance;
/*     */   
/*     */   @SidedProxy(clientSide = "net.blockfront.mod.ClientProxy", serverSide = "net.blockfront.mod.ServerProxy")
/*     */   public static BlockFrontProxy proxy;
/*     */   
/* 119 */   public static List<String> spawnPoints = new ArrayList<>();
/* 120 */   public static List<String> dropPoints = new ArrayList<>();
/*     */   
/*     */   public static boolean addBonesToCorpse = false;
/*     */   
/* 124 */   public static int corpseAuxilleryInventorySize = 54;
/*     */   public static boolean allowCorpseDecay = true;
/*     */   public static boolean decayOnlyWhenEmpty = false;
/* 127 */   public static long corpseDecayTime = 72000L;
/*     */   
/*     */   public static int eioSoulboundID;
/*     */   
/*     */   public static ExecutorService executor;
/*     */   
/*     */   public static final int GUI_BACKPACK = 0;
/*     */   public static final int GUI_BACKPACK_KEY = 1;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public CharacterPlayerFactory characterCreator;
/* 137 */   public static final Gson GSON = new Gson();
/*     */   
/*     */   public static Logger log;
/*     */   
/*     */   @SuppressWarnings("deprecation")
@EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/* 143 */     log = event.getModLog();
/* 144 */     executor = Executors.newCachedThreadPool();
/*     */     
/* 146 */     MinecraftForge.EVENT_BUS.register(this);
/* 147 */     MinecraftForge.EVENT_BUS.register(new PlayerDeathEventHandler());
/* 148 */     MinecraftForge.EVENT_BUS.register(new PlayerAttackEvent());
/* 149 */     FMLCommonHandler.instance().bus().register(this);
/* 150 */     FMLCommonHandler.instance().bus().register(new PlayerDeathEventHandler());
/* 151 */     FMLCommonHandler.instance().bus().register(new PlayerAttackEvent());
/* 152 */     BlockFrontBlocks.init();
/* 153 */     BlockFrontItems.init();
/*     */     
/* 155 */     mainNetworkChannel.registerMessage(MessageKey.Handler.class, MessageKey.class, 0, Side.SERVER);
/* 156 */     mainNetworkChannel.registerMessage(MessageBackpackSlot.Handler.class, MessageBackpackSlot.class, 2, Side.CLIENT);
/*     */     
/* 158 */     GameRegistry.registerTileEntity(EntityNaziRadio.class, "NaziRadio");
/* 159 */     GameRegistry.registerTileEntity(EntityNaziBanner.class, "NaziBanner");
/* 160 */     GameRegistry.registerTileEntity(EntityAmericanBanner.class, "AmericanBanner");
/* 161 */     GameRegistry.registerTileEntity(EntityAmericanRadio.class, "AmericanRadio");
/* 162 */     GameRegistry.registerTileEntity(EntityJapaneseBanner.class, "JapaneseBanner");
/* 163 */     GameRegistry.registerTileEntity(EntityJapaneseBanner2.class, "JapaneseBanner2");
/* 164 */     GameRegistry.registerTileEntity(EntityJapaneseRadio.class, "JapaneseRadio");
/*     */     
/* 166 */     proxy.preInit(event);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void registerCommand(FMLServerStartedEvent e) {
/* 175 */     CommandHandler handler = (CommandHandler)FMLCommonHandler.instance().getSidedDelegate().getServer().getCommandManager();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void init(FMLInitializationEvent event) {
/* 182 */     EntityRegistry.registerModEntity(null, EntityLootableBody.class, "Corpse", 0, this, 80, 3, true);
/*     */ 
/*     */ 
/*     */     
/* 186 */     BiomeGenBase[] allBiomes = (BiomeGenBase[])Stream.<BiomeGenBase>of(BiomeGenBase.getBiomeGenArray()).filter(Objects::nonNull).toArray(x$0 -> new BiomeGenBase[x$0]);
/*     */     
/* 188 */     EntityRegistry.removeSpawn(EntityZombie.class, EnumCreatureType.MONSTER, allBiomes);
/* 189 */     EntityRegistry.removeSpawn(EntityCreeper.class, EnumCreatureType.MONSTER, allBiomes);
/* 190 */     EntityRegistry.removeSpawn(EntitySkeleton.class, EnumCreatureType.MONSTER, allBiomes);
/* 191 */     EntityRegistry.removeSpawn(EntitySlime.class, EnumCreatureType.MONSTER, allBiomes);
/* 192 */     EntityRegistry.removeSpawn(EntityEnderman.class, EnumCreatureType.MONSTER, allBiomes);
/* 193 */     EntityRegistry.removeSpawn(EntityDragon.class, EnumCreatureType.MONSTER, allBiomes);
/* 194 */     EntityRegistry.removeSpawn(EntitySilverfish.class, EnumCreatureType.MONSTER, allBiomes);
/* 195 */     EntityRegistry.removeSpawn(EntitySpider.class, EnumCreatureType.MONSTER, allBiomes);
/* 196 */     EntityRegistry.removeSpawn(EntityCaveSpider.class, EnumCreatureType.MONSTER, allBiomes);
/* 197 */     EntityRegistry.removeSpawn(EntityBlaze.class, EnumCreatureType.MONSTER, allBiomes);
/* 198 */     EntityRegistry.removeSpawn(EntityPigZombie.class, EnumCreatureType.MONSTER, allBiomes);
/*     */     
/* 200 */     NetworkRegistry.INSTANCE.registerGuiHandler(this, new BlockFrontGuiHandler());
/*     */     
/* 202 */     proxy.init(event);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEntityConstructServer(EntityEvent.EntityConstructing event) {
/* 208 */     if (event.getEntity() instanceof EntityPlayer) {
/* 209 */       BackpackSlotInventory.register((EntityPlayer)event.getEntity());
/*     */     }
/*     */   }
/*     */   
/* 213 */   private static final Method addSlot = ObfuscationReflectionHelper.findMethod(Container.class, null, new String[] { "addSlotToContainer", "addSlotToContainer" }, new Class[] { Slot.class });
/*     */   private static final int SLOT_X = 83;
/*     */   private static final int SLOT_Y = 64;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEntityJoinWorld(EntityJoinWorldEvent event) throws InvocationTargetException, IllegalAccessException {
/* 219 */     if (event.getEntity() instanceof EntityPlayer) {
/* 220 */       Container inv = ((EntityPlayer)event.getEntity()).inventoryContainer;
/* 221 */       addSlot.invoke(inv, new Object[] { new BackpackInPlayerInvSlot((IInventory)BackpackSlotInventory.get((EntityPlayer)event.getEntity()), 0, 83, 64) });
/*     */     } 
/*     */   } private static final String NATURAL_REGENERATION = "naturalRegeneration"; @SideOnly(Side.CLIENT)
/*     */   private static boolean shownTitle;
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void guiOpen(GuiScreenEvent.InitGuiEvent.Post event) throws Throwable {
/* 228 */     if (event.getGui() instanceof net.minecraft.client.gui.inventory.GuiInventory) {
/* 229 */       event.getButtonList().add(new HackyButtonForSlotDrawingPleaseKillMe((GuiContainer)event.getGui()));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void drawScreen(GuiScreenEvent.DrawScreenEvent.Pre event) throws Throwable {
/* 236 */     if (event.getGui() instanceof GuiContainerCreative)
/* 237 */       for (Object slot : ((GuiContainerCreative)event.getGui()).inventorySlots.inventorySlots) {
/* 238 */         if (ClientProxy.creativeSlotClass.isInstance(slot)) {
/* 239 */           Object wrappedSlot = ClientProxy.creativeSlotGetParentSlot.invokeExact(slot);
/* 240 */           if (wrappedSlot instanceof BackpackInPlayerInvSlot) {
/* 241 */             ((Slot)slot).xPos = ((Slot)slot).yPos = Integer.MIN_VALUE;
/*     */           }
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private static final class HackyButtonForSlotDrawingPleaseKillMe
/*     */     extends GuiButton
/*     */   {
/*     */     private final GuiContainer gui;
/*     */     
/*     */     HackyButtonForSlotDrawingPleaseKillMe(GuiContainer gui) {
/* 254 */       super(-2147483648, -2147483648, -2147483648, "");
/* 255 */       this.gui = gui;
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawButton(Minecraft mc, int mouseX, int mouseY) {
/*     */       try {
/* 261 */         Minecraft.getMinecraft().getTextureManager().bindTexture(ClientProxy.BlockFrontIcons);
/*     */         
/* 263 */         Object guiLeft = ClientProxy.guiLeftGetter.invokeExact(this.gui);
/* 264 */         Object guiTop = ClientProxy.guiTopGetter.invokeExact(this.gui);
/*     */         
/* 266 */         int x = guiLeft + 83 - 1;
/* 267 */         int y = guiTop + 64 - 1;
/*     */         
/* 269 */         GL11.glPushMatrix();
/*     */ 
/*     */         
/* 272 */         GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 273 */         Gui.drawModalRectWithCustomSizedTexture(x, y, 54.0F, 0.0F, 18, 18, 512.0F, 512.0F);
/* 274 */         GL11.glPopMatrix();
/* 275 */       } catch (Throwable x) {
/* 276 */         throw Throwables.propagate(x);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
/* 282 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void startTracking(PlayerEvent.StartTracking event) {
/* 290 */     if (event.getTarget() instanceof EntityPlayer) {
/* 291 */       BackpackSlotInventory inv = BackpackSlotInventory.get((EntityPlayer)event.getTarget());
/* 292 */       MessageBackpackSlot msg = new MessageBackpackSlot(event.getTarget().getEntityId(), inv.getStackInSlot(0));
/* 293 */       mainNetworkChannel.sendTo((IMessage)msg, (EntityPlayerMP)event.getEntityPlayer());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void playerTick(TickEvent.PlayerTickEvent event) {
/* 303 */     if (!event.player.world.isRemote) {
/* 304 */       syncBackpackSlot(event.player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void syncBackpackSlot(EntityPlayer player) {
/* 310 */     BackpackSlotInventory inv = BackpackSlotInventory.get(player);
/* 311 */     if (!ItemStack.areItemStacksEqual(inv.previousStack, inv.stack)) {
/* 312 */       inv.previousStack = (inv.stack == null) ? null : inv.stack.copy();
/* 313 */       MessageBackpackSlot msg = new MessageBackpackSlot(player.getEntityId(), inv.stack);
/* 314 */       for (EntityPlayer tracking : ((WorldServer)player.world).getEntityTracker().getTrackingPlayers((Entity)player)) {
/* 315 */         mainNetworkChannel.sendTo((IMessage)msg, (EntityPlayerMP)tracking);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean checkBanned() {
/* 326 */     String playerID = Minecraft.getMinecraft().getSession().getPlayerID();
/* 327 */     try (BufferedReader r = Resources.asCharSource(new URL("http://212.48.95.227/blockfrontmc.com/assets/bans.txt"), StandardCharsets.UTF_8).openBufferedStream()) {
/*     */ 
/*     */       
/* 330 */       Set<UUID> ids = (Set<UUID>)r.lines().map(UUID::fromString).collect(Collectors.toSet());
/* 331 */       if (ids.contains(playerID)) {
/* 332 */         return true;
/*     */       }
/* 334 */       return false;
/*     */     
/*     */     }
/* 337 */     catch (MalformedURLException e) {
/* 338 */       e.printStackTrace();
/* 339 */     } catch (IOException e) {
/* 340 */       e.printStackTrace();
/*     */     } 
/* 342 */     return false;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void clientTick(TickEvent.ClientTickEvent event) {
/* 348 */     if (event.phase == TickEvent.Phase.START) {
/* 349 */       Scheduler.client().tick();
/*     */       
/* 351 */       if ((Minecraft.getMinecraft()).player != null && (Minecraft.getMinecraft()).player.getHealth() > 0.0F) {
/* 352 */         (IngameStats.current()).ticksSurvived++;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderTick(TickEvent.RenderTickEvent event) {
/* 363 */     if (event.phase == TickEvent.Phase.END) {
/* 364 */       GL11.glPushMatrix();
/* 365 */       GL11.glPopMatrix();
/*     */ 
/*     */       
/* 368 */       if (ScreenshotReqPacket.doingScreenshot == 2) {
/* 369 */         ScreenshotReqPacket.doingScreenshot = 1;
/* 370 */       } else if (ScreenshotReqPacket.doingScreenshot == 1) {
/*     */         try {
/* 372 */           BufferedImage screenshot = ClientProxy.getScreenshot();
/* 373 */           ByteBuf buf = Unpooled.buffer();
/* 374 */           ImageIO.write(screenshot, "PNG", (OutputStream)new ByteBufOutputStream(buf));
/* 375 */           int numParts = MathHelper.ceil(buf.readableBytes() / 32763.0F);
/* 376 */           for (int i = 0; i < numParts; i++) {
/* 377 */             int bufIdx = i * 32763;
/* 378 */             int thisLen = Math.min(buf.readableBytes() - bufIdx, 32763);
/* 379 */             ByteBuf sliced = buf.slice(bufIdx, thisLen);
/*     */             
/* 381 */             ClientProxy.mainChannel.sendToServer((IMessage)new ScreenshotRespPacket(numParts, i, sliced));
/*     */           } 
/* 383 */         } catch (IOException e) {
/* 384 */           log.error("Failed to take screenshot...", e);
/*     */         } finally {
/* 386 */           ScreenshotReqPacket.doingScreenshot = 0;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderGameOverlay(RenderGameOverlayEvent.Post event) {
/* 397 */     if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
/*     */       
/* 399 */       Minecraft mc = Minecraft.getMinecraft();
/*     */       
/* 401 */       if (mc.player.getHealth() <= 15.0F && mc.player.getHealth() > 10.0F) {
/* 402 */         int width = (Minecraft.getMinecraft()).displayWidth;
/* 403 */         int height = (Minecraft.getMinecraft()).displayHeight;
/* 404 */         Gui.drawRect(0, 0, width, height, 548601856);
/* 405 */         double d = Math.random();
/* 406 */         double Speed = Math.floor(Math.random() * 0.07999999821186066D) + 0.029999999329447746D;
/* 407 */         if (d < 0.25D) {
/* 408 */           (Minecraft.getMinecraft()).player.rotationYaw = (float)((Minecraft.getMinecraft()).player.rotationYaw + Speed);
/* 409 */         } else if (d < 0.5D) {
/* 410 */           (Minecraft.getMinecraft()).player.rotationPitch = (float)((Minecraft.getMinecraft()).player.rotationPitch + Speed);
/* 411 */         } else if (d < 0.75D) {
/* 412 */           (Minecraft.getMinecraft()).player.rotationYaw = (float)((Minecraft.getMinecraft()).player.rotationYaw - Speed);
/*     */         } else {
/* 414 */           (Minecraft.getMinecraft()).player.rotationPitch = (float)((Minecraft.getMinecraft()).player.rotationPitch - Speed);
/*     */         }
/*     */       
/* 417 */       } else if (mc.player.getHealth() <= 10.0F && mc.player.getHealth() > 5.0F) {
/* 418 */         int width = (Minecraft.getMinecraft()).displayWidth;
/* 419 */         int height = (Minecraft.getMinecraft()).displayHeight;
/* 420 */         Gui.drawRect(0, 0, width, height, 1085472768);
/* 421 */         double d = Math.random();
/* 422 */         double Speed = Math.floor(Math.random() * 0.11999999731779099D) + 0.05999999865889549D;
/* 423 */         if (d < 0.25D) {
/* 424 */           (Minecraft.getMinecraft()).player.rotationYaw = (float)((Minecraft.getMinecraft()).player.rotationYaw + Speed);
/* 425 */         } else if (d < 0.5D) {
/* 426 */           (Minecraft.getMinecraft()).player.rotationPitch = (float)((Minecraft.getMinecraft()).player.rotationPitch + Speed);
/* 427 */         } else if (d < 0.75D) {
/* 428 */           (Minecraft.getMinecraft()).player.rotationYaw = (float)((Minecraft.getMinecraft()).player.rotationYaw - Speed);
/*     */         } else {
/* 430 */           (Minecraft.getMinecraft()).player.rotationPitch = (float)((Minecraft.getMinecraft()).player.rotationPitch - Speed);
/*     */         }
/*     */       
/* 433 */       } else if (mc.player.getHealth() <= 5.0F && mc.player.getHealth() >= 0.0F) {
/* 434 */         int width = (Minecraft.getMinecraft()).displayWidth;
/* 435 */         int height = (Minecraft.getMinecraft()).displayHeight;
/* 436 */         Gui.drawRect(0, 0, width, height, 1655898112);
/* 437 */         double d = Math.random();
/* 438 */         double Speed = Math.floor(Math.random() * 0.3799999952316284D) + 0.23000000417232513D;
/* 439 */         if (d < 0.25D) {
/* 440 */           (Minecraft.getMinecraft()).player.rotationYaw = (float)((Minecraft.getMinecraft()).player.rotationYaw + Speed);
/* 441 */         } else if (d < 0.5D) {
/* 442 */           (Minecraft.getMinecraft()).player.rotationPitch = (float)((Minecraft.getMinecraft()).player.rotationPitch + Speed);
/* 443 */         } else if (d < 0.75D) {
/* 444 */           (Minecraft.getMinecraft()).player.rotationYaw = (float)((Minecraft.getMinecraft()).player.rotationYaw - Speed);
/*     */         } else {
/* 446 */           (Minecraft.getMinecraft()).player.rotationPitch = (float)((Minecraft.getMinecraft()).player.rotationPitch - Speed);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void worldLoad(WorldEvent.Load event) {
/* 457 */     if (event.getWorld().isRemote) {
/* 458 */       IngameStats.awaitLoad();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean or(boolean... bools) {
/* 464 */     for (int i = 0; i < bools.length; i++) {
/* 465 */       if (bools[i] == true) return true; 
/*     */     } 
/* 467 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean and(boolean... bools) {
/* 471 */     for (int i = 0; i < bools.length; i++) {
/* 472 */       if (!bools[i]) return false; 
/*     */     } 
/* 474 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\BlockFront.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */