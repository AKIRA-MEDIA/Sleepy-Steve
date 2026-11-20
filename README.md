# Sleepy Steve

<p align="center">
  <em>Get a Better Night’s Sleep in Minecraft.</em>
  <br>
  <em>This is a lightweight, client-side mod that makes the sleeping mechanic more immersive.</em>
</p>
<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-26.3-62B47A" alt="Minecraft 26.3">
  <img src="https://img.shields.io/badge/Loader-Fabric-DBD0B4" alt="Fabric">
  <img src="https://img.shields.io/badge/License-MIT-blue" alt="License: MIT">
</p>
<p align="center">
  <a href="https://modrinth.com/mod/sleepy-steve"><img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg" alt="Available on Modrinth" height="56"></a>
  <a href="https://www.curseforge.com/minecraft/mc-mods/sleepy-steve"><img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/curseforge_vector.svg" alt="Available on CurseForge" height="56"></a>
</p>

I created this mod to make sleeping in Minecraft more realistic. The current sleeping animation is quite dated especially having chat open when you sleep, despite the night skipping within a few seconds. That and well, it does not make much sense in single-player.

---

<p align="center">
  <img src="gifs/sleeping-example-mod-enabled-1.0.3.gif" width="500" alt="Example of Mod">
</p>

## Features

### Falling asleep
- **Slow fade to black.** The screen gradually darkens to completely black.
- **Tired blinking.** Your eyes blink shut a few times, each blink slower than the last, before closing for good.
- **Drifting blur.** The world gets blurrier as you fall asleep, in step with the fade.
- **No distractions.** The hotbar, health, hunger, armour and chat messages are hidden.
- **No chat in bed.** The chat box is removed and can't be opened while you sleep.
- **Compact Leave Bed button.** A small button at the bottom of the screen. Escape also gets you out of bed.

### Waking up
- **Eyes opening.** The darkness lifts as your eyes open, with a few quick, lighter blinks.
- **Groggy blur.** The world stays blurry for a few seconds after you can see, then slowly sharpens.
- **Full control straight away.** You can move, look around and play while the wake-up effect finishes.
- **Natural early exits.** Leave bed early and the effect fades out from however dark it got, without jumping to black.

### Accessibility
The blur respects your **Menu Background Blur** setting (*Options → Accessibility*). Set it to 0 and Sleepy Steve won't blur anything; the fade and blinks still work as normal.

---

## Requirements

| | Version |
|---|---|
| Minecraft | 26.3 |
| Fabric Loader | 0.20.1 or newer |
| [Fabric API](https://modrinth.com/mod/fabric-api) | Any version for 26.3 |

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 26.3.
2. Download [Fabric API](https://modrinth.com/mod/fabric-api) for 26.3.
3. Download the latest `sleepy-steve-x.x.x.jar` from [Modrinth](https://modrinth.com/mod/sleepy-steve), [CurseForge](https://www.curseforge.com/minecraft/mc-mods/sleepy-steve) or the [Releases page](https://github.com/AKIRA-MEDIA/Sleepy-Steve/releases).
4. Put both jars in your `mods` folder and launch the game.

---

### Client or server?
Sleepy Steve is currently **client-side**: install it on your own game, and it works on any server, including vanilla ones. Other players don't need it, and it only affects the screen of the player who has it installed.

---

## Planned

- [ ] Config options for fade speed, blink timing and which HUD elements to hide
- [X] ~~Hide Experience Bar~~
- [ ] Port to other versions and loaders
- [X] ~~Post on CurseForge and Modrinth~~

---

## Building from source

You'll need **JDK 25**.

```sh
git clone https://github.com/AKIRA-MEDIA/Sleepy-Steve.git
cd Sleepy-Steve
./gradlew build
```

On Windows, use `gradlew.bat build` instead.

The built mod is in `build/libs/`. To test it in a development client, run `./gradlew runClient`.

### Project layout

| Path | What's there |
|---|---|
| `src/client/java/.../client/SleepFade.java` | The fade, blink, blur and wake-up timing |
| `src/client/java/.../client/SleepScreen.java` | The replacement bed screen |
| `src/client/java/.../client/SleepySteveClient.java` | Registers the HUD overlay, hidden HUD elements and the screen swap |
| `src/client/java/.../mixin/client/GameRendererMixin.java` | Scales the menu blur with the fade |

---

## Bugs and suggestions

Found a bug or have an idea? Please [open an issue](https://github.com/AKIRA-MEDIA/Sleepy-Steve/issues). For crashes, include your `latest.log` and a list of your other mods.

## License

Released under the [MIT License](LICENSE).
