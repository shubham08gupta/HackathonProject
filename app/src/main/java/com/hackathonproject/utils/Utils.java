package com.hackathonproject.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.hackathonproject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public final class Utils {

    public static final String SIMPLE_DATE_FORMAT = "dd-MMM-yyyy";
    private static final String TAG = Utils.class.getSimpleName();
    private static ProgressDialog pDialog = null;

    public static SharedPreferences getSharedPrefs(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void showLog(String TAG, String message) {

        Log.e(TAG, "" + message);
    }

    public static void showLog(String message) {

        Log.e(TAG, "" + message);
    }

    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showDialog(Context context, String msg) {

        if (pDialog == null) {
            pDialog = new ProgressDialog(context);
            pDialog.setProgressDrawable(ContextCompat.getDrawable(context, R.color.colorAccent));
            pDialog.setCancelable(false);
        }
        pDialog.setMessage(msg);
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    public static void hideDialog() {
        if (pDialog != null) {
            if (pDialog.isShowing())
                pDialog.dismiss();
            pDialog = null;
        }
    }


    // i.e current day starting time[12:00 AM][or 00:00:00 in UTC]
    public static long getAbsoluteTime(long offset) {
        long absoluteTime = (offset - getOffsetTime(offset));
        return absoluteTime;
    }

    private static long getOffsetTime(long time) {
        long offset = time % (24 * 60 * 60 * 1000);
        return offset;
    }

    public static long stringDateToLongDate(String date) {
        SimpleDateFormat f = new SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.getDefault());
        long milliseconds = 0;
        try {
            Date d = f.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milliseconds;
    }

    public static long getLongTimeFromStringTime(int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                hourOfDay, minute, 0);
        return calendar.getTimeInMillis();
    }

    public static String formatTime(int hours, int mins) {
        String timeSet;
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";
        String minutes;
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        return String.valueOf(hours) + ':' +
                minutes + " " + timeSet;

    }


}
