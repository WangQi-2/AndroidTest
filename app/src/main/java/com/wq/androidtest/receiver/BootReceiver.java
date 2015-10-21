package com.wq.androidtest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by wangqi on 15/10/20.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

//        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
//            ApplicationInfo app = new ApplicationInfo();
//            Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(app.packageName);
//            context.startActivity(LaunchIntent);
//        }
//        ToastUtil.showToast(context, "x504 : " + intent.getAction() + " " + intent.getDataString());
//        System.out.println("x504:" + intent.getAction() + " :" + intent.getDataString() + "包名的程序");

    }
}
