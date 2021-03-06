package com.wq.androidlibrary.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by wangqi on 15/9/9.
 * app
 */
public class AppUtil {


    public static void installApk(Context context, File apkfile) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.adnroid.package-archive");
        context.startActivity(intent);
    }

    public static void upinstallApk(Context context, String packageName) {
        //注意这里action的另一种设置方法
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageUri = Uri.parse("package:" + packageName);
        intent.setData(packageUri);
        context.startActivity(intent);
    }

    public static boolean isServiceRuning(Context context, String className) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> serviceInfos = activityManager.getRunningServices(Integer.MAX_VALUE);
        for (RunningServiceInfo runningServiceInfo : serviceInfos) {
            if (className.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void getm(Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        System.out.print("a");
    }

   public static List<ActivityManager.RunningAppProcessInfo> getAllApps(Context context){
       ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
       List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
       return runningAppProcesses;
   }

    public static boolean stopRunningService(Context context, String className) {
        boolean ret = false;
        Intent service = null;
        try {
            service = new Intent(context, Class.forName(className));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (service != null) {
            ret = context.stopService(service);
        }
        return ret;
    }

    public static boolean hasInstallPermission(Context ctx) {
        return ctx.getPackageManager().checkPermission("android.permssion.INSTALL_PACKAGES", ctx.getPackageName()) >= 0;
    }

    public static boolean isSystemApp(Context context, ApplicationInfo applicationInfo) {
        int appFlags = applicationInfo.flags;
        if ((appFlags & ApplicationInfo.FLAG_SYSTEM) == 0) {
            return false;
        }
        return true;
    }

    public static void startActivity(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, String packagename, String className) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = new ComponentName(packagename, className);
        intent.setComponent(cn);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(intent);
    }

    public static String getMetaData(Context context, String name) {
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object o = info.metaData.get(name);
            if (o != null) {
                return o.toString();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void silentInstall(String filePath) {
        String[] args = {"pm", "install", "-r", filePath};
        runProcess(args);
    }

    public static void silentUnInstall(String packName) {
        String[] args = {"pm", "uninstall", packName};
        runProcess(args);
    }

    private static void runProcess(String[] args) {
        Process process = null;
        BufferedReader bufferedReaderErr = null;
        BufferedReader bufferedReaderOut = null;
        InputStream errStream = null;
        InputStream outStream = null;
        StringBuilder sbErr = new StringBuilder();
        StringBuilder sbOut = new StringBuilder();

        try {
            // TODO: 15/10/8 process
            process = new ProcessBuilder(args).start();
            process.waitFor();
            errStream = process.getErrorStream();
            bufferedReaderErr = new BufferedReader(new InputStreamReader(errStream));
            String line;
            while ((line = bufferedReaderErr.readLine()) != null) {
                sbErr.append(line);
            }

            outStream = process.getInputStream();
            bufferedReaderOut = new BufferedReader(new InputStreamReader(outStream));
            while ((line = bufferedReaderOut.readLine()) != null) {
                sbOut.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReaderErr != null) {
                    bufferedReaderErr.close();
                }
                if (bufferedReaderOut != null) {
                    bufferedReaderOut.close();
                }
                if (outStream != null) {
                    outStream.close();
                }
                if (errStream != null) {
                    errStream.close();
                }

                process.destroy();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
