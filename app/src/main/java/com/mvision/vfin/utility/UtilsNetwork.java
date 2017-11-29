package com.mvision.vfin.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by BoyDroid on 10/28/2015.
 * .
 */
public class UtilsNetwork {
    protected final static String TAG = UtilsNetwork.class.getSimpleName();

    public static boolean isOnline(Context context) {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }

    public static ModelCellSite getSellSite(Context context) {
        String unknown = "unknown";
        ModelCellSite cellSite = new ModelCellSite();
        TelephonyManager phone = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (phone.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
            GsmCellLocation cellLocation = (GsmCellLocation) phone.getCellLocation();
            if ((cellLocation != null) && (phone.getNetworkOperator() != null) && (phone.getNetworkOperator().length() > 3)) {
                cellSite.setCellID(String.valueOf(cellLocation.getCid()));
                cellSite.setLac(String.valueOf(cellLocation.getLac()));
                cellSite.setMcc(phone.getNetworkOperator().substring(0, 3));
                cellSite.setMnc(phone.getNetworkOperator().substring(3));
                cellSite.setCountry(phone.getSimCountryIso());
                if (!phone.getSimOperator().equals("")) {
                    cellSite.setOperator(getBrandOperator(Integer.parseInt(phone.getSimOperator())));
                } else {
                    cellSite.setOperator("Unknown");
                }
            } else {
                cellSite.setCellID(unknown);
                cellSite.setLac(unknown);
                cellSite.setMcc(unknown);
                cellSite.setMnc(unknown);
                cellSite.setCountry(unknown);
                cellSite.setOperator(unknown);
            }
        }
        return cellSite;
    }

    public static String getPhoneType(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return getType(telephonyManager.getPhoneType());
    }

    private static String getType(int phoneType) {
        switch (phoneType) {
            case TelephonyManager.PHONE_TYPE_NONE:
                return "none";
            case TelephonyManager.PHONE_TYPE_GSM:
                return "gsm";
            case TelephonyManager.PHONE_TYPE_CDMA:
                return "cdma";
            case TelephonyManager.PHONE_TYPE_SIP:
                return "sip";
            default:
                return "Unknown";
        }
    }

    public static String getBrandOperator(int operator) {
        switch (operator) {
            case 52000:
                return "CAT";
            case 52001:
                return "AIS";
            case 52002:
                return "CAT CDMA";
            case 52003:
                return "AIS 3G";
            case 52004:
                return "TRUE-H 4G LTE";
            case 52005:
                return "DTAC TRI-NET";
            case 52015:
                return "TOT 3G";
            case 52018:
                return "DTAC";
            case 52023:
                return "ASI GSM 1800";
            case 52025:
                return "WE PCT";
            case 52099:
                return "TRUEMOVE";
            default:
                return "Unknown";
        }
    }

    public static String getNetworkClass(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected())
            return "-";
        if (info.getType() == ConnectivityManager.TYPE_WIFI)
            return "WIFI";
        if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                    return "2G";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                    return "4G";
                default:
                    return "Unknown";
            }
        }
        return "Unknown";
    }

//    public static String getIpAddress(Context context) {
//        String ipAddress = context.getString(R.string.msg_unknown);
//        if (getNetworkClass(context).equals("WIFI")) {
////            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
////            ipAddress = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
//            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//            int ipAddr = wifiManager.getConnectionInfo().getIpAddress();
//
//            //Convert little-endian to big-endian if needed
//            if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
//                ipAddr = Integer.reverseBytes(ipAddr);
//            }
//
//            byte[] ipByte = BigInteger.valueOf(ipAddr).toByteArray();
//            try {
//                ipAddress = InetAddress.getByAddress(ipByte).getHostAddress();
//            } catch (UnknownHostException e) {
//                UtilsLog.error(TAG, "Unable to get host address.", true);
//                ipAddress = context.getString(R.string.msg_unknown);
//            }
//        } else {
//            try {
//                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
//                    NetworkInterface networkInterface = en.nextElement();
//                    for (Enumeration<InetAddress> enumIpAddr = networkInterface.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
//                        InetAddress inetAddress = enumIpAddr.nextElement();
//                        if (!inetAddress.isLoopbackAddress()) {
//                            ipAddress = inetAddress.getHostAddress();
//                        }
//                    }
//                }
//            } catch (SocketException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return ipAddress;
//    }

    /**
     * Get IP address from first non-localhost interface
     * getIPAddress(false) || getIPAddress(true)
     *
     * @param useIPv4 true=return ipv4, false=return ipv6
     * @return address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {

            return "";
        }
        return "";
    }

    /**
     * Returns MAC address of the given interface name.
     * getMACAddress("wlan0") || getMACAddress("eth0")
     *
     * @param interfaceName eth0, wlan0 or NULL=use first interface
     * @return mac address or empty string
     */
    public static String getMACAddress(String interfaceName) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
                }
                byte[] mac = intf.getHardwareAddress();
                if (mac == null) return "";
                StringBuilder buf = new StringBuilder();
                for (int idx = 0; idx < mac.length; idx++)
                    buf.append(String.format("%02X:", mac[idx]));
                if (buf.length() > 0) buf.deleteCharAt(buf.length() - 1);
                return buf.toString();
            }
        } catch (Exception ex) {
            return "";
        }
        return "";
    }

    public static String getSimOperatorName(Context context) {
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        String simOperatorName = telephonyManager.getSimOperatorName();
        if(TextUtils.isEmpty(simOperatorName.trim())){
            simOperatorName = getSellSite(context).getOperator();
        }
        return simOperatorName;
    }

    public static String getSSID(Context context) {
        String ssid = "Unknown";
        if (getNetworkClass(context).equals("WIFI")) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            @SuppressLint("MissingPermission") WifiInfo info = wifiManager.getConnectionInfo();
            ssid = info.getSSID();
        }
        return ssid;
    }

    public static boolean isLocation(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true;
        } else {
            return false;
        }
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = UtilsNetwork.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }


    public static boolean isConnectedWifi(Context context) {
        NetworkInfo info = UtilsNetwork.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }


    public static boolean isConnectedMobile(Context context) {
        NetworkInfo info = UtilsNetwork.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * Check if there is fast connectivity
     *
     * @param context
     * @return
     */
    public static boolean isConnectedFast(Context context) {
        NetworkInfo info = UtilsNetwork.getNetworkInfo(context);
        return (info != null && info.isConnected() && UtilsNetwork.isConnectionFast(info.getType(), info.getSubtype()));
    }

    /**
     * Check if the connection is fast
     *
     * @param type
     * @param subType
     * @return
     */
    public static boolean isConnectionFast(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI) {
            return true;
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return true; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return false; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return true; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return true; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return true; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return true; // ~ 400-7000 kbps
        /*
         * Above API level 7, make sure to set android:targetSdkVersion
         * to appropriate level to use these
         */
                case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                    return true; // ~ 1-2 Mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                    return true; // ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                    return true; // ~ 10-20 Mbps
                case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                    return false; // ~25 kbps
                case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                    return true; // ~ 10+ Mbps
                // Unknown
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
}
