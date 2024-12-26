/*     */ package net.blockfront.mod.client.gui;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.ByteBufInputStream;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.handler.codec.base64.Base64;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Executor;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.blockfront.mod.BlockFront;
/*     */ import net.blockfront.mod.client.mainmenuutils.Server;
/*     */ import net.blockfront.mod.client.ping.MinecraftPingReply;
/*     */ import net.blockfront.mod.util.Scheduler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.multiplayer.ServerData;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;

/*     */ import org.apache.commons.lang3.Validate;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiServerSlot
/*     */   extends GuiScrollerSlot {
/*  32 */   private static final ResourceLocation UNREACHABLE_ICON = new ResourceLocation("bff", "gui/unreachable.png");
/*     */   
/*     */   private static final int JOIN_BTN = 123;
/*     */   
/*     */   private static final int JOIN_BTN_PAD = 20;
/*     */   
/*     */   private final Server server;
/*     */   private MinecraftPingReply serverData;
/*     */   private String errorText;
/*     */   private DynamicTexture texture;
/*     */   private ResourceLocation textureLocation;
/*     */   private GuiButton joinBtn;
/*  44 */   private final String joinBtnText = TextFormatting.BOLD + "Join"; private int joinBtnX;
/*     */   private int joinBtnY;
/*     */   
/*     */   public GuiServerSlot(Server server, boolean forceRefresh) {
/*  48 */     this.server = server;
/*  49 */     server.getPingData(forceRefresh).whenCompleteAsync(this::injectServerData, (Executor)Scheduler.client());
/*     */   }
/*     */   private int joinBtnWidth; private int joinBtnHeight;
/*     */   private void injectServerData(MinecraftPingReply reply, Throwable err) {
/*  53 */     this.errorText = null;
/*  54 */     this.serverData = null;
/*  55 */     disposeTexture();
/*     */     
/*  57 */     if (err != null) {
/*  58 */       this.errorText = "Offline";
/*     */     } else {
/*  60 */       this.serverData = reply;
/*     */       
/*  62 */       String favicon = this.serverData.getFavicon();
/*     */       
/*  64 */       if (favicon.startsWith("data:image/png;base64,")) {
/*  65 */         favicon = favicon.substring("data:image/png;base64,".length());
/*     */       } else {
/*  67 */         BlockFront.log.error("Invalid server icon (unknown format)");
/*     */       } 
/*     */       
/*  70 */       ByteBuf bytebuf = Unpooled.copiedBuffer(favicon, StandardCharsets.UTF_8);
/*  71 */       ByteBuf bytebuf1 = Base64.decode(bytebuf);
/*  72 */       BufferedImage bufferedimage = null;
/*     */       try {
/*  74 */         bufferedimage = ImageIO.read((InputStream)new ByteBufInputStream(bytebuf1));
/*  75 */         Validate.validState((bufferedimage.getWidth() == 64), "Must be 64 pixels wide", new Object[0]);
/*  76 */         Validate.validState((bufferedimage.getHeight() == 64), "Must be 64 pixels high", new Object[0]);
/*  77 */       } catch (Exception exception) {
/*  78 */         BlockFront.log.error("Invalid icon for server " + this.serverData.getDescription() + " (" + this.server.getName() + ")", exception);
/*     */       } finally {
/*  80 */         bytebuf.release();
/*  81 */         bytebuf1.release();
/*     */       } 
/*     */       
/*  84 */       if (bufferedimage != null) {
/*  85 */         this.textureLocation = new ResourceLocation("servers/" + this.server.getIp() + "/icon");
/*     */         
/*  87 */         this.texture = new DynamicTexture(bufferedimage.getWidth(), bufferedimage.getHeight());
/*  88 */         Minecraft.getMinecraft().getTextureManager().loadTexture(this.textureLocation, (ITextureObject)this.texture);
/*     */         
/*  90 */         bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), this.texture.getTextureData(), 0, bufferedimage.getWidth());
/*  91 */         this.texture.updateDynamicTexture();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void disposeTexture() {
/*  97 */     if (this.texture != null) {
/*  98 */       Minecraft.getMinecraft().getTextureManager().deleteTexture(this.textureLocation);
/*     */     }
/* 100 */     this.texture = null;
/* 101 */     this.textureLocation = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void init() {
/* 106 */     super.init();
/* 107 */     FontRenderer fr = (Minecraft.getMinecraft()).fontRenderer;
/* 108 */     int textWidth = fr.getStringWidth(this.joinBtnText);
/* 109 */     this.joinBtnX = this.posX + this.scroller.width - 50 - 40 - textWidth;
/* 110 */     this.joinBtnY = this.posY + 4;
/* 111 */     this.joinBtnWidth = textWidth + 40;
/* 112 */     this.joinBtnHeight = 28;
/*     */     
/* 114 */     this.joinBtn = addButton(new GuiPressableNoBorder(123, this.joinBtnX, this.joinBtnY, this.joinBtnWidth, this.joinBtnHeight, "Join", 3381555));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSelect() {
/* 120 */     return (!isPinging() && !isDown());
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRender(int mouseX, int mouseY) {
/* 125 */     super.doRender(mouseX, mouseY);
/*     */     
/* 127 */     GuiUtils.renderTextThemed(this.server.getMotd(), this.posX + 64 + 10, this.posY + 8);
/*     */     
/* 129 */     GL11.glColor3d(1.0D, 1.0D, 1.0D);
/* 130 */     ResourceLocation image = isDown() ? UNREACHABLE_ICON : this.textureLocation;
/* 131 */     if (image != null) {
/* 132 */       GuiUtils.drawImage((this.posX + 25), (this.posY + 2), image, 32.0D, 32.0D);
/*     */     }
/*     */     
/* 135 */     this.joinBtn.visible = false;
/*     */     
/* 137 */     if (isPinging()) {
/* 138 */       GuiUtils.renderTextThemed("Pinging...", this.posX + 64 + 10, this.posY + 21);
/* 139 */     } else if (isDown()) {
/* 140 */       GuiUtils.renderTextThemed("Not Reachable", this.posX + 64 + 10, this.posY + 21);
/*     */     } else {
/* 142 */       if (isSelected()) {
/* 143 */         this.joinBtn.visible = true;
/*     */       }
/* 145 */       String s = TextFormatting.GREEN + "" + this.serverData.getPlayers().getOnline() + TextFormatting.GRAY + "/" + TextFormatting.GRAY + this.serverData.getPlayers().getMax();
/* 146 */       GuiUtils.renderTextThemed(s, this.posX + 64 + 10, this.posY + 21);
/*     */     } 
/* 148 */     GuiUtils.drawImage((this.posX + 2), (this.posY + 2), new ResourceLocation("bff:gui/serverslotoverlap.png"), 329.0D, 32.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) {
/* 153 */     super.actionPerformed(button);
/*     */     
/* 155 */     switch (button.id) {
/*     */       case 123:
/* 157 */         FMLClientHandler.instance().setupServerList();
/* 158 */         FMLClientHandler.instance().connectToServer((Minecraft.getMinecraft()).currentScreen, new ServerData(this.server.getName(), this.server.getIp() + ":" + this.server.getPort(), false));
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isPinging() {
/* 164 */     return (this.serverData == null && this.errorText == null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isDisabled() {
/* 169 */     return isDown();
/*     */   }
/*     */   
/*     */   private boolean isDown() {
/* 173 */     return (this.errorText != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderNoClip(int x, int y, int mouseX, int mouseY) {
/* 178 */     if (isSelected() && this.joinBtn.func_146115_a()) {
/* 179 */       this.scroller.parentGUI.func_146283_a((List)ImmutableList.of(TextFormatting.GREEN + "Click to Join!"), mouseX, mouseY);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onClose() {
/* 185 */     disposeTexture();
/*     */   }
/*     */   
/*     */   private boolean isJoinButtonHovered(int mouseX, int mouseY) {
/* 189 */     return (GuiUtils.isInBox(this.scroller.posX, this.scroller.posY + 4, this.scroller.width, this.scroller.height, mouseX, mouseY) && 
/* 190 */       GuiUtils.isInBox(this.posX + this.joinBtnX, this.posY + this.joinBtnY, this.joinBtnWidth, this.joinBtnHeight, mouseX, mouseY));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int height() {
/* 195 */     return 36;
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiServerSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */