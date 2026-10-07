# Pixel Lock

Press **volume up** to lock your Android phone instantly. Also adds a launcher icon and a home-screen widget that lock the screen with one tap.

It uses an Android Accessibility Service for two things only: seeing the volume-up key press, and calling the system "lock screen" action. It does **not** read, store or send anything from your screen, and the app has no internet permission.

## Compatibility

- Android 9 (API 28) and newer.
- Tested on: Pixel 7, Android 17. Other devices are untested, so reports are welcome.
- Some manufacturers (Samsung, Xiaomi, OnePlus, ...) stop background services aggressively. If it stops working after a while, exempt the app from battery optimization.

## Install

1. Download the latest APK from [Releases](../../releases) and install it (allow installs from your browser/file manager if asked).
2. Go to **Settings → Accessibility → Installed apps → Lock** and turn it on.
   - On Android 13+ the toggle may be greyed out for sideloaded apps. Open **Settings → Apps → Lock → ⋮ → Allow restricted settings**, then try again.
3. Press volume up with the screen on.

## Limitations

- Volume up no longer changes the volume (volume down still does). Use the on-screen slider to raise it.
- Works only while the screen is on. Android generally doesn't deliver key events to accessibility services when the screen is off.
- A locked phone uses your normal lock method, so biometrics keep working.

## Build

Open in Android Studio, or:

```bash
./gradlew :app:installDebug
```

## License

[MIT](LICENSE)
