package com.app.goaheadapp.Utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.app.goaheadapp.R;

public class AppShareMethods {
    public static void newFacebookIntent(Activity mActivity, PackageManager pm, String fbUrl) {
        Uri uri;
        if (!TextUtils.isEmpty(fbUrl)) {
            try {
                ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
                if (applicationInfo.enabled) {

                    uri = Uri.parse("fb://facewebmodal/f?href=" + fbUrl);
                    mActivity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            } catch (PackageManager.NameNotFoundException ignored) {
                if (!fbUrl.startsWith("https://") && !fbUrl.startsWith("http://")) {
                    fbUrl = "http://" + fbUrl;
                }
                mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fbUrl)));
            }
        }
    }

    public static void newInstagramIntent(Activity mActivity, PackageManager pm, String fbUrl) {
        Uri uri;
        if (!TextUtils.isEmpty(fbUrl)) {
            try {
                ApplicationInfo applicationInfo = pm.getApplicationInfo("com.instagram.android", 0);
                if (applicationInfo.enabled) {
                    uri = Uri.parse("https://www.instagram.com/_u/" + fbUrl);
                    mActivity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            } catch (PackageManager.NameNotFoundException ignored) {
                if (!fbUrl.startsWith("https://") && !fbUrl.startsWith("http://")) {
                    fbUrl = "http://" + fbUrl;
                }
                mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fbUrl)));
            }
        }
    }

    public static void newTwitterIntent(Activity mActivity, PackageManager pm, String fbUrl) {
        Uri uri;
        if (!TextUtils.isEmpty(fbUrl)) {
            try {
                ApplicationInfo applicationInfo = pm.getApplicationInfo("com.twitter.android", 0);
                if (applicationInfo.enabled) {
                    uri = Uri.parse("twitter://user?user_id=USERID" + fbUrl);
                    mActivity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            } catch (PackageManager.NameNotFoundException ignored) {
                if (!fbUrl.startsWith("https://") && !fbUrl.startsWith("http://")) {
                    fbUrl = "http://" + fbUrl;
                }
                mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fbUrl)));
            }
        }
    }

    public static void copyAppLink(Activity mActivity, String text) {
        ClipboardManager clipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(null, text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(mActivity, " " + mActivity.getResources().getString(R.string.coped), Toast.LENGTH_SHORT).show();
    }
}
