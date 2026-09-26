# MOFA Quotes

Quotations and invoices for MOFA Company Pvt Ltd, as a mobile app. You can run it three ways.

## 1. Open on your phone (fastest)

**https://quantscript007.github.io/mofa-quotes/**

Open the link in Chrome on Android, then go to menu ⋮ → **Install app** (or **Add to Home screen**).
The app gets its own icon, runs full-screen and works offline. Data is saved on the phone.

## 2. Install the Android APK

Every push to `main` builds a fresh APK automatically (see the **Actions** tab).

1. Open **Releases → latest** on this repo from your phone.
2. Download **MOFA-Quotes.apk** and open it.
3. If Android asks, allow "Install unknown apps" for Chrome or your file manager.

## 3. Run on a computer

```bash
npx serve www        # then open http://localhost:3000
```

## What's in the repo

| Path | What it is |
|---|---|
| `www/index.html` | The whole app in one self-contained file (React is bundled, so it works offline) |
| `www/manifest.json`, `www/sw.js` | Makes it installable and lets it work offline |
| `www/assets/` | Logo and app icons |
| `capacitor.config.json`, `package.json` | Capacitor wrapper that turns the web app into an Android APK |
| `resources/` | Source icon and splash image for the APK |
| `.github/workflows/pages.yml` | Publishes `www/` to GitHub Pages |
| `.github/workflows/android.yml` | Builds the APK and attaches it to the `latest` release |

## Features

- Quotes and invoices, saved on the device
- Several companies/letterheads, each with its own number prefix
- Price list: tap a container rate to add a line item
- Discounts and totals (no GST)
- Share by WhatsApp or email; **Save PDF** opens the print dialog → "Save as PDF" (A4)
- Business → Backup to file exports a JSON backup
