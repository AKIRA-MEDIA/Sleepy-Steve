package media.akira.sleepysteve.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;

public class SleepFade {

    // ---- Settings ----
    public static final float FADE_TICKS = 100.0f;     // falling asleep (5 s)
    public static final float WAKE_TICKS = 100.0f;     // whole wake-up, until blur is gone (5 s)
    public static final float WAKE_CLEAR_AT = 0.6f;    // darkness gone 60% of the way through

    // ---- Memory (kept between ticks) ----
    private static boolean wasSleeping = false;
    private static int wakeTicks = -1;                 // -1 means "not waking up"
    private static float wakeStrength = 0.0f;          // how dark it was when we woke
    private static float lastSleepDarkness = 0.0f;

    // Called 20 times a second from SleepySteveClient.
    public static void tick() {
        boolean sleeping = isSleeping();

        if (sleeping) {
            wakeTicks = -1;
            lastSleepDarkness = getSleepDarkness(0.0f);
        } else if (wasSleeping) {
            // Sleeping last tick, not now: we just woke up.
            wakeTicks = 0;
            wakeStrength = lastSleepDarkness;
        } else if (wakeTicks >= 0) {
            wakeTicks++;
            if (wakeTicks >= WAKE_TICKS) {
                wakeTicks = -1;
            }
        }

        wasSleeping = sleeping;
    }

    public static boolean isSleeping() {
        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && player.isSleeping();
    }

    public static boolean isWaking() {
        return wakeTicks >= 0;
    }

    // Sleeping or still waking up.
    public static boolean isActive() {
        return isSleeping() || isWaking();
    }

    // How black the screen should be (0 to 1).
    public static float getFadeAmount(float partialTick) {
        if (isSleeping()) {
            return getSleepDarkness(partialTick);
        }
        if (isWaking()) {
            return getWakeDarkness(partialTick);
        }
        return 0.0f;
    }

    // How blurry the world should be (0 to 1).
    public static float getBlurAmount(float partialTick) {
        if (isSleeping()) {
            return getSleepDarkness(partialTick);
        }
        if (isWaking()) {
            return (1.0f - wakeProgress(partialTick)) * wakeStrength;
        }
        return 0.0f;
    }

    // ---- Private helpers ----

    private static float wakeProgress(float partialTick) {
        return Mth.clamp((wakeTicks + partialTick) / WAKE_TICKS, 0.0f, 1.0f);
    }

    private static float getSleepDarkness(float partialTick) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            return 0.0f;
        }

        float timer = player.getSleepTimer() + partialTick;
        float progress = Mth.clamp(timer / FADE_TICKS, 0.0f, 1.0f);

        float darkness = progress * progress;
        darkness = Math.max(darkness, blink(progress, 0.25f, 0.08f));
        darkness = Math.max(darkness, blink(progress, 0.50f, 0.12f));
        darkness = Math.max(darkness, blink(progress, 0.72f, 0.18f));
        return darkness;
    }

    private static float getWakeDarkness(float partialTick) {
        float progress = wakeProgress(partialTick);

        // Eyes opening: fully dark → clear by WAKE_CLEAR_AT.
        float open = Mth.clamp(progress / WAKE_CLEAR_AT, 0.0f, 1.0f);
        float darkness = (1.0f - open) * (1.0f - open);

        // Blinks continue until the blur ends, quicker and lighter each time.
        darkness = Math.max(darkness, blink(progress, 0.55f, 0.14f) * 0.9f);
        darkness = Math.max(darkness, blink(progress, 0.75f, 0.10f) * 0.7f);
        darkness = Math.max(darkness, blink(progress, 0.92f, 0.08f) * 0.5f);

        return darkness * wakeStrength;
    }

    private static float blink(float progress, float centre, float width) {
        float distance = Math.abs(progress - centre) / (width / 2.0f);
        if (distance >= 1.0f) {
            return 0.0f;
        }
        return 1.0f - distance;
    }
}