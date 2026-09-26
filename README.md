# Logistic Invoice Maker

Make quotations and invoices for your logistics company on your phone. Anyone can set it up for their own business: company name, address, logo and staff users.

## Get the app

**On your phone (web app):** open **https://quantscript007.github.io/logistics-Invoice-Maker/** in Chrome, then ⋮ → **Install app**.
It works offline, and your data stays on your phone.

**Android APK:** open **Releases → latest** on this repo from your phone and download **Logistic-Invoice-Maker.apk**.
If Android asks, allow "Install unknown apps" once. Later builds install over the top and keep your data.

## First-time setup

1. **Business → Switch or edit company → Edit details**: enter your company name, address, phone and quote-number prefix.
2. **Upload logo**: pick your logo from the phone's gallery or files. It's resized automatically and appears on every PDF.
3. **Business → Users → + Add user**: add each staff member (name, role, phone, email) and their **bank details**
   (bank, account name, account number, currency, SWIFT/IBAN). Tap a user to make them active.
   The active user is printed as "Prepared by", and their bank details are added to their invoices automatically.
4. Tap **+** to make your first quote.

## Features

- Quotes and invoices, saved on the device; **Edit** any saved quote or invoice (number kept, change logged); convert an accepted quote into an invoice
- Several companies/letterheads, each with your own uploaded logo, address and number prefix
- Staff users with "Prepared by" and their own bank details on invoices
- **Ledger**: full history of quotes, invoices and payments for each user, with invoiced / received / outstanding totals, running balance and CSV export
- Backup and restore (move your history to a new phone)
- Container-rate price list: tap a rate to add a line item
- Discounts and totals (no GST)
- **Save PDF** prints a full-page **A4** document (choose "Save as PDF" in the print dialog)
- Business → Backup to file exports a JSON backup

## Repo layout

| Path | What it is |
|---|---|
| `www/` | The app: `index.html` (self-contained, works offline), manifest, service worker, icons |
| `capacitor.config.json`, `package.json` | Capacitor wrapper that turns the web app into an Android APK |
| `resources/` | Source icon and splash for the APK |
| `android-src/` | Native Android code for printing to PDF (A4) and saving files to Downloads |
| `android-signing/debug.keystore` | Fixed signing key so each new APK installs as an update |
| `.github/workflows/` | Publishes the web app to GitHub Pages and builds the APK on every push |

Run locally: `npx serve www`, then open http://localhost:3000
