/*     */ package net.blockfront.mod.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Iterators;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.MathHelper;

/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class GuiScroller
/*     */   extends GuiBox
/*     */ {
/*     */   public static final int SCROLLBAR_HEIGHT = 20;
/*     */   public static final int SCROLLBAR_WIDTH = 6;
/*     */   public static final int SCROLLBAR_Y_PAD = 2;
/*     */   ArrayList<GuiScrollerSlot> slots;
/*     */   private int scrollbarY;
/*     */   private int scrollbarMinY;
/*     */   private int scrollbarMaxY;
/*     */   private int scrollbarX;
/*  26 */   private int smoothScrollTargetY = -1;
/*     */   
/*     */   private int selectedSlot;
/*     */   private int totalHeight;
/*     */   private boolean scrollerClicked;
/*     */   private int scrollerClickMouseOffset;
/*     */   
/*     */   public GuiScroller(int posX, int posY, int width, int height, GuiCustomScreen parentGUI) {
/*  34 */     super(posX, posY, width, height, parentGUI);
/*  35 */     this.slots = new ArrayList<>();
/*  36 */     this.scrollerClicked = false;
/*  37 */     this.selectedSlot = -1;
/*     */   }
/*     */   
/*     */   public void initGui() {
/*  41 */     super.initGui();
/*     */     
/*  43 */     this.scrollbarMinY = this.posY + 4;
/*  44 */     this.scrollbarMaxY = this.scrollbarMinY;
/*  45 */     this.scrollbarY = this.scrollbarMinY;
/*  46 */     this.scrollbarX = this.posX + this.width - 12;
/*     */     
/*  48 */     onSlotHeightChanged();
/*     */   }
/*     */   
/*     */   public void onSlotHeightChanged() {
/*  52 */     int currentHeight = 0;
/*  53 */     for (int i = 0, n = this.slots.size(); i < n; i++) {
/*  54 */       GuiScrollerSlot slot = this.slots.get(i);
/*     */       
/*  56 */       slot.init(this, i, this.posX, this.posY + currentHeight);
/*  57 */       currentHeight += slot.height();
/*     */     } 
/*  59 */     this.totalHeight = currentHeight;
/*     */     
/*  61 */     if (this.totalHeight > this.height) {
/*  62 */       this.scrollbarMaxY = this.posY + this.height - 4 - 20;
/*     */     } else {
/*  64 */       this.scrollbarMaxY = this.scrollbarMinY;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void scrollTo(int y) {
/*  69 */     int maxTranslate = this.totalHeight - this.height;
/*  70 */     float translate = Math.min(maxTranslate, y - this.posY);
/*     */     
/*  72 */     this.smoothScrollTargetY = clampToScrollbar(this.scrollbarMinY + (int)(translate / maxTranslate * (this.scrollbarMaxY - this.scrollbarMinY)));
/*     */   }
/*     */   
/*     */   public void scrollIntoView(GuiScrollerSlot slot) {
/*  76 */     scrollTo(slot.posY);
/*     */   }
/*     */   
/*     */   private void updateScrollPosition(int newPos) {
/*  80 */     this.scrollbarY = clampToScrollbar(newPos);
/*     */   }
/*     */   
/*     */   private int clampToScrollbar(int newPos) {
/*  84 */     return MathHelper.clamp(newPos, this.scrollbarMinY, this.scrollbarMaxY);
/*     */   }
/*     */   
/*     */   private int getActualScrollbarPos() {
/*  88 */     return (this.smoothScrollTargetY == -1) ? this.scrollbarY : this.smoothScrollTargetY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleScroll(int mouseX, int mouseY, int dWheel) {
/*  93 */     super.handleScroll(mouseX, mouseY, dWheel);
/*  94 */     if (!this.scrollerClicked && !this.slots.isEmpty() && GuiUtils.isInBox(this.posX, this.posY, this.width, this.height, mouseX, mouseY)) {
/*  95 */       this.smoothScrollTargetY = clampToScrollbar((int)(getActualScrollbarPos() - dWheel / this.totalHeight * 30.0F));
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 100 */     int translate = getSlotYTranslation();
/* 101 */     for (int i = 0, n = this.slots.size(); i < n; i++) {
/* 102 */       GuiScrollerSlot slot = this.slots.get(i);
/* 103 */       if (GuiUtils.isInBox(slot.posX, slot.posY - translate, this.width, slot.height(), mouseX, mouseY)) {
/* 104 */         if (slot.canSelect()) {
/* 105 */           if (this.selectedSlot != i) {
/* 106 */             this.selectedSlot = i;
/*     */           } else {
/* 108 */             slot.clicked(mouseX, mouseY + translate);
/*     */           } 
/*     */         } else {
/* 111 */           slot.clicked(mouseX, mouseY + translate);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 116 */     if (GuiUtils.isInBox(this.scrollbarX, this.scrollbarY, 6, 20, mouseX, mouseY)) {
/* 117 */       this.smoothScrollTargetY = -1;
/* 118 */       this.scrollerClickMouseOffset = mouseY - this.scrollbarY;
/* 119 */       this.scrollerClicked = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY) {
/* 125 */     super.mouseReleased(mouseX, mouseY);
/* 126 */     this.scrollerClicked = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 131 */     if (this.scrollerClicked) {
/* 132 */       updateScrollPosition(mouseY - this.scrollerClickMouseOffset);
/* 133 */     } else if (this.smoothScrollTargetY != -1) {
/* 134 */       double amnt = (this.smoothScrollTargetY - this.scrollbarY) / 3.0D;
/* 135 */       updateScrollPosition((int)(this.scrollbarY + ((amnt <= 0.0D) ? Math.floor(amnt) : Math.ceil(amnt))));
/* 136 */       if (this.scrollbarY == this.smoothScrollTargetY) {
/* 137 */         this.smoothScrollTargetY = -1;
/*     */       }
/*     */     } 
/*     */     
/* 141 */     for (GuiScrollerSlot slot : this.slots) {
/* 142 */       slot.preRenderCallback(mouseX, mouseY);
/*     */     }
/*     */     
/* 145 */     drawBackgroundClanMembers();
/*     */     
/* 147 */     renderScrollbar();
/*     */     
/* 149 */     ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
/* 150 */     int factor = sr.getScaleFactor();
/*     */     
/* 152 */     GL11.glPushMatrix();
/*     */     
/* 154 */     int translation = getSlotYTranslation();
/* 155 */     GL11.glTranslatef(0.0F, -translation, 0.0F);
/*     */     
/* 157 */     GL11.glEnable(3089);
/* 158 */     GL11.glScissor(this.posX * factor, (this.parentGUI.height - this.posY + this.height - 1) * factor, this.width * factor, (this.height - 3) * factor);
/*     */     
/* 160 */     for (GuiScrollerSlot slot : this.slots) {
/* 161 */       slot.doRender(mouseX, mouseY + translation);
/*     */     }
/*     */     
/* 164 */     GL11.glDisable(3089);
/*     */     
/* 166 */     for (GuiScrollerSlot slot : this.slots) {
/* 167 */       slot.renderNoClip(slot.posX, slot.posY, mouseX, mouseY);
/*     */     }
/*     */     
/* 170 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private int getSlotYTranslation() {
/* 174 */     int maxTranslate = this.totalHeight - this.height;
/* 175 */     return (int)((this.scrollbarY - this.scrollbarMinY) / (this.scrollbarMaxY - this.scrollbarMinY) * maxTranslate);
/*     */   }
/*     */   
/*     */   private void renderScrollbar() {
/* 179 */     if (this.totalHeight > this.height) {
/*     */ 
/*     */       
/* 182 */       GuiUtils.drawRect(this.scrollbarX - 2, this.posY + 2, 10, this.height - 4, new Color(34, 34, 34));
/*     */ 
/*     */       
/* 185 */       GuiUtils.drawRect(this.scrollbarX, this.scrollbarY, 6, 20, new Color(255, 255, 255));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setTextList(List<String> list) {
/* 190 */     setSlotList(list.stream().map(GuiScrollerTextSlot::new).iterator());
/*     */   }
/*     */   
/*     */   boolean isSelected(GuiScrollerSlot slot) {
/* 194 */     return (slot.index == this.selectedSlot);
/*     */   }
/*     */   
/*     */   public void setSlotList(Iterator<? extends GuiScrollerSlot> newSlots) {
/* 198 */     this.slots.clear();
/* 199 */     Iterators.addAll(this.slots, newSlots);
/* 200 */     onSlotHeightChanged();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClose() {
/* 205 */     this.slots.forEach(GuiScrollerSlot::onClose);
/*     */   }
/*     */ }


/* Location:              C:\Users\theoz\Desktop\BlockFront-deobf.jar!\net\blockfront\mod\client\gui\GuiScroller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */