package mv.logistics.invoicemaker;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.provider.MediaStore;
import android.webkit.WebView;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/** Printing (Save as PDF, A4) and saving files to Downloads, which a plain WebView cannot do. */
@CapacitorPlugin(name = "NativeTools")
public class NativeToolsPlugin extends Plugin {

    @PluginMethod
    public void print(final PluginCall call) {
        final String name = call.getString("name", "Document");
        getActivity().runOnUiThread(() -> {
            try {
                WebView webView = getBridge().getWebView();
                PrintManager pm = (PrintManager) getActivity().getSystemService(Context.PRINT_SERVICE);
                PrintAttributes attrs = new PrintAttributes.Builder()
                        .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                        .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                        .build();
                pm.print(name, webView.createPrintDocumentAdapter(name), attrs);
                call.resolve();
            } catch (Exception e) {
                call.reject(e.getMessage());
            }
        });
    }

    @PluginMethod
    public void saveFile(PluginCall call) {
        String name = call.getString("name", "file.txt");
        String data = call.getString("data", "");
        String mime = call.getString("mime", "text/plain");
        try {
            byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            if (Build.VERSION.SDK_INT >= 29) {
                ContentValues v = new ContentValues();
                v.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
                v.put(MediaStore.MediaColumns.MIME_TYPE, mime);
                v.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);
                Uri uri = getContext().getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, v);
                if (uri == null) throw new Exception("Could not create file");
                try (OutputStream os = getContext().getContentResolver().openOutputStream(uri)) {
                    os.write(bytes);
                }
            } else {
                File dir = getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                File f = new File(dir, name);
                try (FileOutputStream os = new FileOutputStream(f)) {
                    os.write(bytes);
                }
            }
            JSObject r = new JSObject();
            r.put("path", "Download/" + name);
            call.resolve(r);
        } catch (Exception e) {
            call.reject(e.getMessage());
        }
    }
}
