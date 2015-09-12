package com.wq.androidtest.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by wangqi on 15/9/7.
 * TODO what if wifi is closed
 */
public class NetWordUtil {

    public static String getLocalIp(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        return intIp2StringIp(ipAddress);
    }

    public static String intIp2StringIp(int intIp) {
        StringBuffer sb = new StringBuffer();
        sb.append(intIp & 0xff).append(".");
        sb.append(intIp >> 8 & 0xff).append(".");
        sb.append(intIp >> 16 & 0xff).append(".");
        sb.append(intIp >> 24 & 0xff);
        return sb.toString();
    }

    /**
     * gprs
     * TODO what is gprs ??
     *
     * @return
     */
    public static String getLocalIp2() {
        try {
            //TODO networkinterface ???
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                //TODO inetaddress ???
                for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }

            }
        } catch (SocketException ex) {
            Logger.e(ex.toString());
        }
        return null;
    }
}
