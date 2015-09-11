package com.wq.androidtest.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangqi on 15/9/9.
 * app
 */
public class AppUtil {

    //TODO 添加静默卸载
    //TODO 添加系统权限判断
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

}
