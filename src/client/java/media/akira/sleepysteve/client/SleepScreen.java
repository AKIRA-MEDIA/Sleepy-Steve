package media.akira.sleepysteve.client;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;

public class SleepScreen extends Screen {

    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 16;

    public SleepScreen() {
        super(Component.translatable("multiplayer.stopSleeping"));
    }

    @Override
    protected void init() {
        int x = (this.width - BUTTON_WIDTH) / 2;
        int y = this.height - BUTTON_HEIGHT - 10;

        this.addRenderableWidget(
                Button.builder(Component.translatable("multiplayer.stopSleeping"), button -> this.wakeUp())
                        .bounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT)
                        .build()
        );
    }

    private void wakeUp() {
        LocalPlayer player = this.minecraft.player;
        if (player != null) {
            player.connection.send(new ServerboundPlayerCommandPacket(
                    player, ServerboundPlayerCommandPacket.Action.STOP_SLEEPING));
        }
    }

    @Override
    public void onClose() {
        if (SleepFade.isSleeping()) {
            this.wakeUp();
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}