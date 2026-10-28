# Sleepy Steve

*Heavy eyelids, slow blinks, and a soft fade to black. Sleeping, the way it should feel. A lightweight, client-side mod for a more immersive night’s sleep.*

![Minecraft 26.3](https://img.shields.io/badge/Minecraft-26.3-62B47A)
![Fabric](https://img.shields.io/badge/Loader-Fabric-DBD0B4)
![License: MIT](https://img.shields.io/badge/License-MIT-blue)

In vanilla Minecraft, going to bed means staring at a chat box and a big "Leave Bed" button while the screen dims a little. Sleepy Steve turns it into something that feels like actually falling asleep: your eyes get heavy, you blink, the world goes soft and dark, and you wake up groggy in the morning.

---

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
3. Download the latest `sleepy-steve-x.x.x.jar` from the [Releases page](https://github.com/AKIRA-MEDIA/Sleepy-Steve/releases).
4. Put both jars in your `mods` folder and launch the game.

### Client or server?
Sleepy Steve is currently **client-side**: install it on your own game, and it works on any server, including vanilla ones. Other players don't need it, and it only affects the screen of the player who has it installed.

---

## Known limitations

- **Brief flash of the vanilla bed screen.** For a single frame when you get into bed, you may see the vanilla chat screen before Sleepy Steve replaces it.
- **Night skip timing is vanilla.** The server still skips the night after the standard 5 seconds of sleep, which lines up with the default fade length.

## Planned

- [ ] Server-side support, so the night only skips once every sleeping player's screen has fully faded
- [ ] Config options for fade speed, blink timing and which HUD elements to hide
- [ ] Skip the wake-up effect with Escape

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
