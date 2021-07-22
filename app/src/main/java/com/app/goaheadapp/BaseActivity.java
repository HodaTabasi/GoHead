package com.app.goaheadapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.app.goaheadapp.Utils.Connectivity;
import com.app.goaheadapp.databinding.DialogNoInternetBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static android.content.Context.LOCATION_SERVICE;

public class BaseActivity {
    public static String getLocalTimeString(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        SimpleDateFormat LocalTimeFormatter = new SimpleDateFormat("hh:mm a", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateString = formatter.format(new Date(milliseconds));
        String strLocalTime = "";
        try {
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = formatter.parse(dateString);
            LocalTimeFormatter.setTimeZone(TimeZone.getDefault());
            strLocalTime = LocalTimeFormatter.format(date);
        } catch (ParseException e) {
            return strLocalTime;
        }
        return strLocalTime;
    }

    public static String getLocalTimeString(String milliseconds) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat outputtime = new SimpleDateFormat("hh:mm a", Locale.getDefault());

        Date d = null;
        Date dtime = null;
        try {
            d = input.parse(milliseconds);
            dtime = input.parse(milliseconds);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatted = output.format(d);
        String formattedtime = outputtime.format(dtime);
        Log.i("ffffffff", "" + formatted + "\n" + formattedtime);
        Log.d("ffffffff", "onCreate: " + milliseconds);

        return formattedtime;
    }

    public static String getLocalDateString(String milliseconds) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat outputtime = new SimpleDateFormat("hh:mm a", Locale.getDefault());

        Date d = null;
        Date dtime = null;
        try {
            d = input.parse(milliseconds);
            dtime = input.parse(milliseconds);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatted = output.format(d);
        String formattedtime = outputtime.format(dtime);
//        Log.i("ffffffff", "" + formatted + "\n" + formattedtime);
//        Log.d("ffffffff", "onCreate: " + milliseconds);

        return formatted;
    }

    public static String getTime(String milliseconds) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String s = String.valueOf(Clock.systemUTC().instant());
            if (s.equals(milliseconds)) {
                return getLocalTimeString(milliseconds);
            } else {
                return getLocalDateString(milliseconds);
            }

        }
        return getLocalDateString(milliseconds);
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public static void toolToStatsBarTrabsarint(Activity context) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(context, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }

        if (Build.VERSION.SDK_INT >= 19) {
            context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(context, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            context.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        // In Activity's onCreate() for instance
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = context.getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public static boolean isConnected(Context context) {
        if (Connectivity.isConnectedWifi(context) || Connectivity.isConnectedWifi(context)) {
            return true;
        } else {
            showDialog(context);
            return false;
        }
    }

    public static void showDialog(Context context) {
        DialogNoInternetBinding dialogOrderResivedBinding;
        Dialog dialog1 = new Dialog(context);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogOrderResivedBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_no_internet, null, false);
        dialog1.setContentView(dialogOrderResivedBinding.getRoot());

        dialogOrderResivedBinding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        dialog1.show();
        Window window = dialog1.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public static void checkGPS(Activity activity) {
        final LocationManager manager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

        }
    }

    public static int[] getDeviceWidthAndHeight(Context context) {

        int widthHeght[] = new int[2];
        int measuredWidth = 0;
        int measuredHeight = 0;

        WindowManager w = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

            Point size = new Point();
            w.getDefaultDisplay().getSize(size);
            measuredWidth = size.x;
            measuredHeight = size.y;
            widthHeght[0] = measuredWidth;
            widthHeght[1] = measuredHeight;

        } else {
            Display d = w.getDefaultDisplay();
            measuredWidth = d.getWidth();
            measuredHeight = d.getHeight();
            widthHeght[0] = measuredWidth;
            widthHeght[1] = measuredHeight;

        }
        return widthHeght;
    }
}
