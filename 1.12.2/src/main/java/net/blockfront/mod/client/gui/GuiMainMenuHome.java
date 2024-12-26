/*     */ package net.blockfront.mod.client.gui;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import net.blockfront.mod.client.mainmenuutils.Server;
/*     */ import net.blockfront.mod.client.mainmenuutils.ServersManager;
/*     */ import net.blockfront.mod.client.ping.MinecraftPingReply;
/*     */ import net.blockfront.mod.util.Scheduler;
/*     */ import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.text.TextFormatting;
/*     */ 
/*     */ public class GuiMainMenuHome
/*     */   extends GuiCustomMainMenu {
/*  17 */   private static final String GETTING_LIST_TEXT = TextFormatting.DARK_RED + "Searching Servers..." + TextFormatting.RESET;
/*  18 */   private static final String PINGING_TEXT = TextFormatting.DARK_RED + "Pinging Servers..." + TextFormatting.RESET;
/*  19 */   public float fade = 1.0F;
/*     */   
/*  21 */   private final GuiScroller newsContainer = new GuiScroller(0, 73, 247, 150, this);
/*     */   
/*     */   static PingState pingState;
/*     */   private boolean init = false;
/*     */   
/*     */   public void initGui() {
/*  27 */     super.initGui();
/*  28 */     initPing();
/*  29 */     int x = this.width / 2 - 173;
/*  30 */     addContainer(new GuiBoxCharacter(x + 1, 73, 100, 150, this));
/*     */     
/*  32 */     if (!this.init)
/*     */     {
/*  34 */       this.init = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private void initPing() {
/*  39 */     PingState state = new PingState();
/*  40 */     pingState = state;
/*     */     
/*  42 */     state.pingText = GETTING_LIST_TEXT;
/*     */     
/*  44 */     ServersManager.getServerList()
/*  45 */       .whenCompleteAsync((servers, x) -> handlePlayerCountList(state, servers, x));
/*     */   }
/*     */   
/*     */   private void handlePlayerCountList(PingState state, List<Server> list, Throwable err) {
/*  49 */     if (pingState == state) {
/*  50 */       if (err == null) {
/*  51 */         state.pingText = PINGING_TEXT;
/*  52 */         state.servers = list;
/*  53 */         state.playerCount = -1;
/*  54 */         state.serverTexts = (List<String>)list.stream().map(s -> getServerStatusText(s, "Pinging...")).collect(Collectors.toCollection(java.util.ArrayList::new));
/*     */         
/*  56 */         for (int i = 0; i < list.size(); i++) {
/*  57 */           Server server = list.get(i);
/*  58 */           int thisI = i;
/*  59 */           server.getPingData(true)
/*  60 */             .whenCompleteAsync((reply, x) -> handlePingComplete(state, thisI, reply, x), (Executor)Scheduler.client());
/*     */         } 
/*     */       } else {
/*  63 */         err.printStackTrace();
/*  64 */         state.pingText = "Couldn't get server list";
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void handlePingComplete(PingState state, int serverIndex, MinecraftPingReply reply, Throwable x) {
/*  70 */     if (pingState == state) {
/*  71 */       state.completedPings++;
/*  72 */       Server server = state.servers.get(serverIndex);
/*  73 */       if (x == null) {
/*  74 */         int onlinePlayers = reply.getPlayers().getOnline();
/*  75 */         state.playerCount = Math.max(0, state.playerCount) + onlinePlayers;
/*  76 */         state.pingText = TextFormatting.WHITE + "Survivors Online: " + state.playerCount + TextFormatting.RESET;
/*     */         
/*  78 */         state.serverTexts.set(serverIndex, getServerStatusText(server, String.format("%s / %s", new Object[] { Integer.valueOf(onlinePlayers), Integer.valueOf(reply.getPlayers().getMax()) })));
/*     */       } else {
/*  80 */         state.serverTexts.set(serverIndex, getServerStatusText(server, "Not reachable"));
/*     */       } 
/*     */       
/*  83 */       if (state.completedPings == state.servers.size() && state.playerCount == -1) {
/*  84 */         state.pingText = "Not reachable";
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String getServerStatusText(Server server, String status) {
/*  90 */     return server.getName() + ": " + status;
/*     */   }
/*     */   
/*     */   static class PingState
/*     */   {
/*     */     int playerCount;
/*     */     String pingText;
/*     */     List<Server> servers;
/*     */     int completedPings;
/*  99 */     List<String> serverTexts = (List<String>)ImmutableList.of();
/*     */   }
/*     */ 
/*     */   
/*     */   private void injectNews(List<String> news, Throwable x) {
/* 104 */     if (news != null) {
/* 105 */       this.newsContainer.setTextList(news);
/*     */     } else {
/* 107 */       this.newsContainer.setTextList((List<String>)ImmutableList.of("Failed to grab news"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(GuiButton button) {
/* 113 */     super.actionPerformed(button);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 118 */     super.updateScreen();
/*     */   }
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 122 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiMainMenuHome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */