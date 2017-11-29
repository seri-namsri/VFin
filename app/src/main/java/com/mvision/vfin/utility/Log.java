package com.mvision.vfin.utility;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

import android.annotation.SuppressLint;

import com.mvision.vfin.R;

import static android.util.Log.DEBUG;
import static android.util.Log.ERROR;
import static android.util.Log.INFO;
import static android.util.Log.WARN;
/**
 * Created by ps13 on 26/2/2560.
 */
@SuppressLint("LogTagMismatch")
public class Log {
    //true for show log
    //false for hide log
    private static boolean flag_logs = true;
    //private static boolean flag_logs = false;
    public static void logexception(Exception e) {
        if (flag_logs) {
            e.printStackTrace();
        }
    }
    public static void e(String Tag, String msg) {
        if (flag_logs) {
            android.util.Log.e(Tag, msg);
        }
    }
    public static void d(String Tag, String msg) {
        if (flag_logs) {
            android.util.Log.d(Tag, msg);
        }
    }
    public static void v(String Tag, String msg) {
        if (flag_logs) {
            android.util.Log.v(Tag, msg);
        }
    }
    public static void i(String Tag, String msg) {
        if (flag_logs) {
            android.util.Log.i(Tag, msg);
        }
    }
    public static void w(String Tag, String msg) {
        if (flag_logs) {
            android.util.Log.w(Tag, msg);
        }
    }
    public static void wtf(String Tag, String msg) {
        if (flag_logs) {
            android.util.Log.wtf(Tag, msg);
        }
    }
    //for linphone logs
    public static String TAG = "Linphone";
    private static final boolean useIsLoggable = false;
    private static boolean isLogEnabled = true;
    public Log(String tag, boolean enable) {
        TAG = tag;
        if (flag_logs) {
            isLogEnabled = enable;
        } else {
            isLogEnabled = flag_logs;
        }
    }
    @SuppressWarnings(value = "all")
    private static boolean isLoggable(int level) {
        return isLogEnabled && (!useIsLoggable || android.util.Log.isLoggable(TAG, level));
    }
    public static void i(Object... objects) {
        if (isLoggable(INFO)) {
            android.util.Log.i(TAG, toString(objects));
        }
    }

    public static void i(Throwable t, Object... objects) {
        if (isLoggable(INFO)) {
            android.util.Log.i(TAG, toString(objects), t);
        }
    }
    public static void d(Object... objects) {
        if (isLoggable(DEBUG)) {
            android.util.Log.d(TAG, toString(objects));
        }
    }
    public static void d(Throwable t, Object... objects) {
        if (isLoggable(DEBUG)) {
            android.util.Log.d(TAG, toString(objects), t);
        }
    }
    public static void w(Object... objects) {
        if (isLoggable(WARN)) {
            android.util.Log.w(TAG, toString(objects));
        }
    }
    public static void w(Throwable t, Object... objects) {
        if (isLoggable(WARN)) {
            android.util.Log.w(TAG, toString(objects), t);
        }
    }
    public static void e(Object... objects) {
        if (isLoggable(ERROR)) {
            android.util.Log.e(TAG, toString(objects));
        }
    }
    public static void e(Throwable t, Object... objects) {
        if (isLoggable(ERROR)) {
            android.util.Log.e(TAG, toString(objects), t);
        }
    }
    /**
     * @throws RuntimeException always throw after logging the error message.
     */
    public static void f(Object... objects) {
        if (isLoggable(ERROR)) {
            android.util.Log.e(TAG, toString(objects));
            throw new RuntimeException(R.string.fatal_error + toString(objects));
        }
    }
    /**
     * @throws RuntimeException always throw after logging the error message.
     */
    public static void f(Throwable t, Object... objects) {
        if (isLoggable(ERROR)) {
            android.util.Log.e(TAG, toString(objects), t);
            throw new RuntimeException(R.string.fatal_error + toString(objects), t);
        }
    }
    private static String toString(Object... objects) {
        StringBuilder sb = new StringBuilder();
        for (Object o : objects) {
            sb.append(o);
        }
        return sb.toString();
    }
}