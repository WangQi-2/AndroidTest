package com.wq.androidtest.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;

import com.wq.androidtest.util.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangqi on 15/9/20.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = CrashHandler.class.getSimpleName();
    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            dumpExceptionToSDCard(ex);
            uploadExceptionToServer(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ex.printStackTrace();
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(thread, ex);
        } else {
            android.os.Process.killProcess(Process.myPid());
        }
    }


    // TODO: 15/10/08
    private void uploadExceptionToServer(Throwable ex) {

    }

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/AndroidTest/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    boolean DEBUG = false;

    private void dumpExceptionToSDCard(Throwable ex) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Logger.e(TAG, "sdcard unmounted,skip dump exception");
            return;
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            //app
            pw.print("App version: ");
            pw.print(pi.versionName);
            pw.print("-");
            pw.println(pi.versionCode);
            //system
            pw.print("OS Verion: ");
            pw.print(Build.VERSION.RELEASE);
            pw.print("-");
            pw.println(Build.VERSION.SDK_INT);
            //device
            pw.print("Vender: ");
            pw.println(Build.MANUFACTURER);
            pw.print("Model: ");
            pw.println(Build.MODEL);
            pw.print("CPU ABI: ");
            pw.println(Build.CPU_ABI);
        } catch (Exception e) {
            Logger.e(TAG, "failed to dump crash " + e.getMessage());
        }
    }
}
