package media.akira.sleepysteve.client;

import com.mojang.blaze3d.platform.Window;
import media.akira.sleepysteve.SleepySteve;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.InBedChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.Identifier;

public class SleepySteveClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath(SleepySteve.MOD_ID, "sleep_fade"),
                SleepySteveClient::drawSleepFade
        );

        hideWhileSleeping(VanillaHudElements.HOTBAR);
        hideWhileSleeping(VanillaHudElements.HEALTH_BAR);
        hideWhileSleeping(VanillaHudElements.FOOD_BAR);
        hideWhileSleeping(VanillaHudElements.ARMOR_BAR);
        hideWhileSleeping(VanillaHudElements.CHAT);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            SleepFade.tick();

            Screen screen = client.gui.screen();
            if (screen instanceof InBedChatScreen) {
                client.gui.setScreen(new SleepScreen());
            } else if (screen instanceof SleepScreen && !SleepFade.isSleeping()) {
                client.gui.setScreen(null);
            }
        });
    }
    private static void drawSleepFade(GuiGraphicsExtractor graphics, DeltaTracker delta) {
        Minecraft minecraft = Minecraft.getInstance();

        // While waking up there's no screen to ask for blur, so we ask for it ourselves.
        if (SleepFade.isWaking() && minecraft.gui.screen() == null) {
            graphics.blurBeforeThisStratum();
        }

        float fade = SleepFade.getFadeAmount(delta.getGameTimeDeltaPartialTick(false));
        if (fade <= 0.0f) {
            return;
        }

        int alpha = (int) (fade * 255);
        int colour = alpha << 24;

        Window window = minecraft.getWindow();
        graphics.fill(0, 0, window.getGuiScaledWidth(), window.getGuiScaledHeight(), colour);
    }

    private static void hideWhileSleeping(Identifier element) {
        HudElementRegistry.replaceElement(element, original -> (graphics, delta) ->{
            if(!SleepFade.isSleeping()){
                original.extractRenderState(graphics, delta);
            }

        });

    }
}
