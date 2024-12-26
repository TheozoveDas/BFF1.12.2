/*     */ package net.blockfront.mod.client.gui;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import net.blockfront.mod.client.mainmenuutils.Server;
/*     */ import net.blockfront.mod.client.mainmenuutils.ServersManager;
/*     */ import net.blockfront.mod.client.ping.MinecraftPingReply;
/*     */ import net.blockfront.mod.util.Scheduler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiMultiplayer;
/*     */ import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;
/*     */ 
/*     */ 
/*     */ public class GuiServersList
/*     */   extends GuiCustomMainMenu
/*     */ {
/*     */   static GuiMainMenuHome.PingState pingState;
/*  23 */   private static final String PINGING_TEXT = TextFormatting.DARK_RED + "Pinging Servers..." + TextFormatting.RESET;
/*  24 */   private static final String GETTING_LIST_TEXT = TextFormatting.DARK_RED + "Searching Servers..." + TextFormatting.RESET;
/*  28 */   private final GuiScroller serverScroller = new GuiScroller(0, 73, 351, 150, this); private final List<Server> servers; private boolean doRefresh;
/*     */   
/*     */   public GuiServersList(List<Server> servers, boolean doRefresh) {
/*  31 */     this.servers = servers;
/*  32 */     this.doRefresh = doRefresh;
/*     */   }
/*     */ 
/*     */   
/*     */   private void initPing() {
/*  37 */     GuiMainMenuHome.PingState state = new GuiMainMenuHome.PingState();
/*  38 */     pingState = state;
/*     */     
/*  40 */     state.pingText = GETTING_LIST_TEXT;
/*     */     
/*  42 */     ServersManager.getServerList()
/*  43 */       .whenCompleteAsync((servers, x) -> handlePlayerCountList(state, servers, x));
/*     */   }
/*     */   
/*     */   private void handlePlayerCountList(GuiMainMenuHome.PingState state, List<Server> list, Throwable err) {
/*  47 */     if (pingState == state) {
/*  48 */       if (err == null) {
/*  49 */         state.pingText = PINGING_TEXT;
/*  50 */         state.servers = list;
/*  51 */         state.playerCount = -1;
/*  52 */         state.serverTexts = (List<String>)list.stream().map(s -> getServerStatusText(s, "Pinging...")).collect(Collectors.toCollection(java.util.ArrayList::new));
/*     */         
/*  54 */         for (int i = 0; i < list.size(); i++) {
/*  55 */           Server server = list.get(i);
/*  56 */           int thisI = i;
/*  57 */           server.getPingData(true)
/*  58 */             .whenCompleteAsync((reply, x) -> handlePingComplete(state, thisI, reply, x), (Executor)Scheduler.client());
/*     */         } 
/*     */       } else {
/*  61 */         err.printStackTrace();
/*  62 */         state.pingText = "Couldn't get server list";
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void handlePingComplete(GuiMainMenuHome.PingState state, int serverIndex, MinecraftPingReply reply, Throwable x) {
/*  68 */     if (pingState == state) {
/*  69 */       state.completedPings++;
/*  70 */       Server server = state.servers.get(serverIndex);
/*  71 */       if (x == null) {
/*  72 */         int onlinePlayers = reply.getPlayers().getOnline();
/*  73 */         state.playerCount = Math.max(0, state.playerCount) + onlinePlayers;
/*  74 */         state.pingText = TextFormatting.WHITE + "Survivors Online: " + state.playerCount + TextFormatting.RESET;
/*     */         
/*  76 */         state.serverTexts.set(serverIndex, getServerStatusText(server, String.format("%s / %s", new Object[] { Integer.valueOf(onlinePlayers), Integer.valueOf(reply.getPlayers().getMax()) })));
/*     */       } else {
/*  78 */         state.serverTexts.set(serverIndex, getServerStatusText(server, "Not reachable"));
/*     */       } 
/*     */       
/*  81 */       if (state.completedPings == state.servers.size() && state.playerCount == -1) {
/*  82 */         state.pingText = "Not reachable";
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String getServerStatusText(Server server, String status) {
/*  88 */     return server.getName() + ": " + status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  93 */     super.initGui();
/*  94 */     initPing();
/*  95 */     int x = this.width / 2 - 173;
/*  96 */     this.serverScroller.posX = x + 1;
/*     */     
/*  98 */     addContainer(this.serverScroller);
/*  99 */     boolean doRefresh = this.doRefresh;
/* 100 */     this.serverScroller.setSlotList(this.servers.stream().map(server -> new GuiServerSlot(server, doRefresh)).iterator());
/* 101 */     this.doRefresh = false;
/*     */     
/* 103 */     String singleplayer = "Single";
/* 104 */     String multiplayer = "Community";
/* 105 */     String back = "Back";
/* 106 */     int h = 15;
/* 107 */     this.buttonList.add(new GuiCustomPressable(10, x + 10, 224, this.fontRenderer.getStringWidth(singleplayer) + 10, h, singleplayer));
/* 108 */     this.buttonList.add(new GuiCustomPressable(12, x + 56, 224, this.fontRenderer.getStringWidth(multiplayer) + 10, h, multiplayer));
/* 109 */     this.buttonList.add(new GuiCustomPressableClans(13, x + 300, 224, this.fontRenderer.getStringWidth(back) + 10, h, back));
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(GuiButton button) {
/* 114 */     super.actionPerformed(button);
/* 115 */     switch (button.id) {
/*     */       case 10:
/* 117 */         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiSelectWorld(this));
/*     */         break;
/*     */       case 12:
/* 120 */         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiMultiplayer(this));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 127 */     int x = this.width / 2 - 50;
/* 128 */     super.drawScreen(mouseX, mouseY, partialTicks);
/* 129 */     this.serverScroller.drawScreen(mouseX, mouseY, partialTicks);
/* 130 */     GuiUtils.renderTextThemed("Official Servers:", x, 63);
/*     */   }
/*     */   
/*     */   public void initOverheadMenu() {
/* 134 */     int x = this.width / 2 - 173;
/* 135 */     int y = 1;
/* 136 */     int buttonWidth = 85;
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiServersList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */