# Simple Youtube on Background

Simple app that allows Youtube videos to be played in the background and without ads!

I made this because I wanted music to keep playing while the screen was locked (and also because ads are annoying and it's impossible to block them in Chrome).

## How does it works?

It's just a activity with a custom webview that doesn't report visibility status and as such, can't tell if the app is in background or foreground or even if the screen is locked.

## How do you block the ads?

We check every request the webpage makes and if the URL contains a string that might be related to ads, we don't let it load.

## How can I use this on my phone?

Download the APK file from [here](app-release.apk) (or the "app-release.apk" file), transfer to your device and install it.

## Does everything work?

Login probably doesn't work, I also didn't test it, and I don't use it. So I am not going to bother.
