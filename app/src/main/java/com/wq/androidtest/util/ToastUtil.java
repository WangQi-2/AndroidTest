package com.wq.androidtest.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by wangqi on 15/9/14.
 */
public class ToastUtil {

    private static Context mContext;
    private static final int SHOW_TOAST = 0;

    private static Handler toastHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_TOAST:
                    showToast(mContext, (String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    public static void showToast(Context context, int res) {
        Toast.makeText(context, res, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void showToastInThread(Context context, int res) {
        String text = context.getResources().getString(res);
        showToastInThread(context, text);
    }

    public static void showToastInThread(Context context, String str) {
        mContext = context;
        Message message = toastHandler.obtainMessage();
        message.what = SHOW_TOAST;
        message.obj = str;
        toastHandler.sendMessage(message);

        //TODO andbase 的写法
//        Message msg = baseHandler.obtainMessage(SHOW_TOAST);
//        Bundle bundle = new Bundle();
//        bundle.putString("TEXT", text);
//        msg.setData(bundle);




    }
}
