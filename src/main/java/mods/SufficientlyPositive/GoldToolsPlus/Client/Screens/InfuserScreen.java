package mods.SufficientlyPositive.GoldToolsPlus.Client.Screens;

import com.mojang.blaze3d.systems.RenderSystem;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class InfuserScreen extends HandledScreen<ScreenHandler> {

    //texture needs to be 256x256 with a 176x166 image for inventory
    private static final Identifier TEXTURE = new Identifier(GoldToolsPlus.MOD_ID, "textures/gui/container/infuser.png");

    public InfuserScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    // when created backgroundWidth = 176, backgroundHeight = 166
    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Centre the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

}
