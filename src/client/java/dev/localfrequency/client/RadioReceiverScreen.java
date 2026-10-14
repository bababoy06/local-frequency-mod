package dev.localfrequency.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class RadioReceiverScreen extends Screen {
    private static final int WOOD_DARK = 0xFF241A12;
    private static final int WOOD = 0xFF49301D;
    private static final int WOOD_LIGHT = 0xFF76502E;
    private static final int METAL = 0xFF77756E;
    private static final int METAL_DARK = 0xFF383936;
    private static final int PANEL = 0xFF171512;
    private static final int PANEL_INNER = 0xFF29251E;
    private static final int AMBER = 0xFFFFB52E;
    private static final int AMBER_BRIGHT = 0xFFFFD15A;
    private static final int MUTED = 0xFFB9B09B;
    private static final int GREEN = 0xFF65D56F;

    public RadioReceiverScreen() {
        super(Component.literal("Radio Signal Menu"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        // The first milestone is a display-only receiver mockup. Interactive
        // channel selection and signal discovery will be added in later steps.
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics, mouseX, mouseY, delta);

        int panelWidth = Math.min(430, width - 24);
        int panelHeight = Math.min(430, height - 18);
        int left = (width - panelWidth) / 2;
        int top = (height - panelHeight) / 2;
        int right = left + panelWidth;
        int bottom = top + panelHeight;

        // Layered wood-and-metal casing.
        graphics.fill(left - 5, top - 5, right + 5, bottom + 5, 0xFF080706);
        graphics.fill(left, top, right, bottom, METAL_DARK);
        graphics.fill(left + 4, top + 4, right - 4, bottom - 4, WOOD_DARK);
        graphics.fill(left + 9, top + 9, right - 9, bottom - 9, WOOD);
        graphics.fill(left + 13, top + 13, right - 13, bottom - 13, WOOD_DARK);
        graphics.fill(left + 18, top + 18, right - 18, bottom - 18, PANEL_INNER);

        drawCorner(graphics, left, top);
        drawCorner(graphics, right - 14, top);
        drawCorner(graphics, left, bottom - 14);
        drawCorner(graphics, right - 14, bottom - 14);

        // Title plaque.
        int titleLeft = left + 56;
        int titleRight = right - 56;
        graphics.fill(titleLeft - 3, top - 7, titleRight + 3, top + 29, 0xFF080706);
        graphics.fill(titleLeft, top - 4, titleRight, top + 26, WOOD_LIGHT);
        graphics.fill(titleLeft + 3, top, titleRight - 3, top + 22, PANEL);
        drawCenteredClipped(graphics, "RADIO SIGNAL MENU", width / 2, top + 5, titleRight - titleLeft - 12, AMBER_BRIGHT);

        int contentLeft = left + 27;
        int contentRight = right - 27;
        int contentWidth = contentRight - contentLeft;
        int y = top + 43;

        // Signal meter.
        drawCentered(graphics, "SIGNAL STRENGTH", width / 2, y, 0xFFFFFFFF);
        int meterTop = y + 17;
        int meterLeft = left + 102;
        int meterRight = right - 102;
        graphics.fill(meterLeft - 7, meterTop - 5, meterRight + 7, meterTop + 34, METAL_DARK);
        graphics.fill(meterLeft - 3, meterTop - 1, meterRight + 3, meterTop + 30, PANEL);
        int segments = 10;
        int gap = 4;
        int segmentWidth = Math.max(5, (meterRight - meterLeft - (segments - 1) * gap) / segments);
        int activeSegments = 8;
        for (int i = 0; i < segments; i++) {
            int sx = meterLeft + i * (segmentWidth + gap);
            int color = i < activeSegments ? (i >= 6 ? AMBER_BRIGHT : AMBER) : 0xFF111316;
            graphics.fill(sx, meterTop + 3, sx + segmentWidth, meterTop + 26, color);
            if (i < activeSegments) {
                graphics.fill(sx + 2, meterTop + 5, sx + Math.max(3, segmentWidth - 2), meterTop + 11, 0x55FFFFFF);
            }
        }

        int infoTop = meterTop + 40;
        drawCentered(graphics, "ACTIVE SIGNAL: [ 8/10 ]", width / 2, infoTop, 0xFFFFFFFF);
        drawCenteredClipped(graphics, "STATUS: STANDBY - SCANNING", width / 2, infoTop + 18, contentWidth, MUTED);

        // Nearby signal list.
        int listTop = infoTop + 44;
        int rowHeight = 34;
        String[][] signals = {
                {"[LOCAL]", "NEARBY USERS", "READY"},
                {"[TOWER]", "NO TOWER TUNED", "--"},
                {"[PING]", "NO ACTIVE PINGS", "--"},
                {"[LINK]", "RELAY NETWORK", "OFFLINE"}
        };
        for (int i = 0; i < signals.length; i++) {
            int rowTop = listTop + i * (rowHeight + 3);
            graphics.fill(contentLeft, rowTop, contentRight, rowTop + rowHeight, 0xFF080706);
            graphics.fill(contentLeft + 3, rowTop + 3, contentRight - 3, rowTop + rowHeight - 3, i == 0 ? 0xFF39301E : PANEL);
            graphics.fill(contentLeft + 3, rowTop + 3, contentLeft + 6, rowTop + rowHeight - 3, i == 0 ? AMBER : METAL_DARK);
            graphics.drawString(font, signals[i][0], contentLeft + 12, rowTop + 6, AMBER_BRIGHT, false);
            graphics.drawString(font, signals[i][1], contentLeft + 12, rowTop + 18, 0xFFFFFFFF, false);
            int statusWidth = font.width(signals[i][2]);
            graphics.drawString(font, signals[i][2], contentRight - statusWidth - 10, rowTop + 11, i == 0 ? GREEN : MUTED, false);
        }

        // Signal details panel.
        int detailTop = listTop + signals.length * (rowHeight + 3) + 5;
        int detailBottom = bottom - 44;
        graphics.fill(contentLeft + 8, detailTop, contentRight - 8, detailBottom, 0xFF080706);
        graphics.fill(contentLeft + 12, detailTop + 4, contentRight - 12, detailBottom - 4, PANEL_INNER);
        drawCentered(graphics, "SIGNAL DETAILS", width / 2, detailTop + 8, 0xFFFFFFFF);
        graphics.drawString(font, "MODE: LOCAL", contentLeft + 20, detailTop + 26, AMBER_BRIGHT, false);
        graphics.drawString(font, "RANGE: NEARBY", contentLeft + 20, detailTop + 41, 0xFFFFFFFF, false);
        graphics.drawString(font, "TOWER: NONE", contentLeft + 20, detailTop + 56, 0xFFFFFFFF, false);
        graphics.drawString(font, "NETWORK: STANDBY", contentLeft + 20, detailTop + 71, MUTED, false);

        // Footer plaque.
        int footerTop = bottom - 29;
        graphics.fill(left + 48, footerTop - 3, right - 48, bottom + 2, 0xFF080706);
        graphics.fill(left + 52, footerTop, right - 52, bottom - 3, WOOD);
        drawCenteredClipped(graphics, "R: RADIO  |  ESC: EXIT", width / 2, footerTop + 7, panelWidth - 112, AMBER_BRIGHT);

        super.render(graphics, mouseX, mouseY, delta);
    }

    private void drawCorner(GuiGraphics graphics, int x, int y) {
        graphics.fill(x, y, x + 14, y + 14, METAL_DARK);
        graphics.fill(x + 2, y + 2, x + 12, y + 12, METAL);
        graphics.fill(x + 4, y + 4, x + 6, y + 6, 0xFFB7B5AD);
        graphics.fill(x + 9, y + 9, x + 11, y + 11, 0xFF4A4B48);
    }

    private void drawCentered(GuiGraphics graphics, String text, int centerX, int y, int color) {
        graphics.drawCenteredString(font, text, centerX, y, color);
    }

    private void drawCenteredClipped(GuiGraphics graphics, String text, int centerX, int y, int maxWidth, int color) {
        String shown = font.plainSubstrByWidth(text, Math.max(0, maxWidth));
        graphics.drawCenteredString(font, shown, centerX, y, color);
    }
}
