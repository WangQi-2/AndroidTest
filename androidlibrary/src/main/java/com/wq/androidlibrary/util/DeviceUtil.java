package com.wq.androidlibrary.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Created by wangqi on 15/9/16.
 */
public class DeviceUtil {
    public static String getDeviceId(Context context) {
        String ret = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return ret;
    }

    public static String getAndroidId(Context context) {
        String ret = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
        return ret;
    }

    public static String getDeviceSerial() {
        return Build.SERIAL;
    }

    public static String getMacAddress(Context context) {
        return NetWordUtil.getWifiMacAddress(context);
    }
}
