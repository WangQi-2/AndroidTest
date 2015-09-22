package com.wq.androidlibrary.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;
import java.util.List;

/**
 * Created by wangqi on 15/9/9.
 * app
 */
public class AppUtil {

    //TODO 添加静默卸载
    //TODO andbase里有一个返回cpu核心数,有啥用?

    public static void installApk(Context context, File apkfile) {
        Intent intent = new Intent();
        //TODO what for?
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.adnroid.package-archive");
        //TODO STARTACTIVITY?
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
        //TODO 为什么叫activity_service
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        List<RunningServiceInfo> serviceInfos = activityManager.getRunningServices(Integer.MAX_VALUE);
        for (RunningServiceInfo runningServiceInfo : serviceInfos) {
            if (className.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
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
            //TODO 为什么通过context来停止
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
        //TODO CONTEXT 分类
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
}
