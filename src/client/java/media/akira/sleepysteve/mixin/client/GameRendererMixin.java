package media.akira.sleepysteve.mixin.client;   // ← must match "package" in the JSON

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import media.akira.sleepysteve.client.SleepFade;
import media.akira.sleepysteve.client.SleepScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @ModifyExpressionValue(
            method = "extractOptions",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;getMenuBackgroundBlurriness()I")
    )
    private int sleepysteve$scaleBlur(int original) {
        Screen screen = Minecraft.getInstance().gui.screen();
        if (SleepFade.isActive() && (screen == null || screen instanceof SleepScreen)) {
            return Math.round(original * SleepFade.getBlurAmount(0.0f));
        }
        return original;
    }
}