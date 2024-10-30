package com.verma.android.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Base64;
import android.view.Gravity;
import android.widget.Toast;
import com.verma.android.template.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class Utils {
    private static final String TAG = "Utils";
    private static Utils instance;
    public static boolean  IS_DEBUG = BuildConfig.DEBUG;

    private Utils() {
        //Do Nothing
    }

    public static Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }

    public static String timeFormat(long timeMillis, String pattern) {
        return timeFormat(timeMillis, pattern, "");
    }

    public static String timeFormat(long timeMillis, String pattern, String defaultValue) {
        if (timeMillis <= 0) {
            return defaultValue;
        }
        Date curDateTime = new Date(timeMillis);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        return formatter.format(curDateTime);
    }

    public static void openWebLink(Context context, String url) {
        // missing 'http://' will cause crashed
        if (!url.toLowerCase().matches("^\\w+://.*")) {
            url = "http://" + url;
        }
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    public void copyToClipboard(Context pContext, String copyText) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                    pContext.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(copyText);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                    pContext.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Your TEXT", copyText);
            clipboard.setPrimaryClip(clip);
        }
        Toast toast = Toast.makeText(pContext.getApplicationContext(), "Your Text is copied", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 50, 50);
        toast.show();
    }

    public String readFile(Context pContext, int aboutMe) {
        String jsonString = "";
        InputStream is = pContext.getResources().openRawResource(aboutMe);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            jsonString = writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonString;

    }

    public void printHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Timber.i("printHashKey() Hash Key: %s", hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Timber.e("printHashKey()%s", e.toString());
        } catch (Exception e) {
            Timber.e(e, "printHashKey()");
        }
    }

    public int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
