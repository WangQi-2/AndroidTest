package com.wq.androidtest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;

import com.wq.androidlibrary.util.ToastUtil;

/**
 * Created by wangqi on 15/10/20.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
            ApplicationInfo app = new ApplicationInfo();
            Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(app.packageName);
            context.startActivity(LaunchIntent);
        }

        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            ToastUtil.showToast(context, "x504,install : " + packageName);
            System.out.println("x504,install:" + packageName + "包名的程序");
        }
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String packageName = intent.getDataString();
            ToastUtil.showToast(context, "x504,uninstall : " + packageName);
            System.out.println("x504,uninstall:" + packageName + "包名的程序");
        }
        ToastUtil.showToast(context, "x504 : " + intent.getAction() + " " + intent.getDataString());
        System.out.println("x504:" + intent.getAction() + " :" + intent.getDataString() + "包名的程序");

    }
}
