package com.mvision.vfin.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.mvision.vfin.BuildConfig;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class Utility {

	private static SharedPreferences sharedPreferences;

	public static boolean isInternetAvailable() {
		try {
			InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name

			if (ipAddr.equals("")) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			return false;
		}

	}



	public static String getIMEI1(Context context) {
		try {
			TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(context);
			return telephonyInfo.getImsiSIM1();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			return capitalize(model);
		}
		return capitalize(manufacturer) + " " + model;
	}
	private static String capitalize(String str) {
		if (TextUtils.isEmpty(str)) {
			return str;
		}
		char[] arr = str.toCharArray();
		boolean capitalizeNext = true;
		String phrase = "";
		for (char c : arr) {
			if (capitalizeNext && Character.isLetter(c)) {
				phrase += Character.toUpperCase(c);
				capitalizeNext = false;
				continue;
			} else if (Character.isWhitespace(c)) {
				capitalizeNext = true;
			}
			phrase += c;
		}
		return phrase;
	}

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

	public static String getIMEI2(Context context) {
		try {
			TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(context);
			if (telephonyInfo.isDualSIM()) {
				return telephonyInfo.getImsiSIM2();
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}



	public static String SetDate(String date){
		String Array_month_th [] = {"ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค."};
		try {
			String[] parts = date.split("-");
			int i = Integer.parseInt(parts[1]);
			String month  = Array_month_th[i-1];
			int year = Integer.parseInt(parts[0])+  543;
			try {
				if (parts[2].substring(2).isEmpty()){
					return  parts[2].substring(0,2) + " "+month + " "+year;
				}
				return  parts[2].substring(0,2) + " "+month + " "+year+ " "+parts[2].substring(2)+" น.";
			}catch (Exception e){
				return  parts[2].substring(0,2) + " "+month + " "+year;
			}
		}catch (NullPointerException e){
			return  "";
		}catch (ArrayIndexOutOfBoundsException e){
			return  date;
		}
	}


	public static String keyMemberId(String memberId){
		return  md5(memberId+"|Se590264410eOf14a336aeB50f0801T4a5cI4D");
	}

	public static String SetDate2(String date){
		String Array_month_th [] = {"ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค."};
		try {
			String[] parts = date.split("-");
			int i = Integer.parseInt(parts[1]);
			String month  = Array_month_th[i-1];
			int year = Integer.parseInt(parts[0])+  543;
			try {
				if (parts[2].substring(2).isEmpty()){
					return  parts[2].substring(0,2) + "\n"+month;
				}
				return  parts[2].substring(0,2) + "\n"+month +parts[2].substring(2)+" น.";
			}catch (Exception e){
				return  parts[2].substring(0,2) + "\n"+month;
			}
		}catch (NullPointerException e){
			return  "";
		}catch (ArrayIndexOutOfBoundsException e){
			return  date;
		}
	}




	public final static boolean isValidEmail(CharSequence target) {
		return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
	}

	public final static boolean isValidPhone(CharSequence target) {
		return !TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches();
	}

	public static boolean wifiManage(Context context) {
		boolean check = false ;
		ConnectivityManager connManager = (ConnectivityManager)context. getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mWifi.isConnected()) {
		    // Do whatever
			check = true ;
		}
		return check;
			
		/*ConnectivityManager cn=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo nf=cn.getActiveNetworkInfo();
	    if(nf != null && nf.isConnected()==true )
	    {
	       
	    	check = true ;
	    }
		return check;*/
	    }

    public static void openBrowser(String url) {
		try {
			final String HTTPS = "https://";
			final String HTTP = "http://";

			if (!url.startsWith(HTTP) && !url.startsWith(HTTPS)) {
				url = HTTP + url;
			}

			Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setData(Uri.parse(url));
			Contextor.getInstance().getContext().startActivity(intent);
		}catch (Exception e){

		}


    }

    public static void getDailog(final Context mContext ,String result,String tital, final String even)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle(tital);
        builder.setMessage(result);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        if (even.equalsIgnoreCase("Back")) {
                            Activity back = (Activity) mContext;
                            back.onBackPressed();
                        }
                    }
                });

        AlertDialog dialog = builder.show();
        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }

	public static boolean checkInternet(Context context) {
		boolean check = false ;


		ConnectivityManager cn=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo nf=cn.getActiveNetworkInfo();
	    if(nf != null && nf.isConnected()==true )
	    {

	    	check = true ;
	    }
		return check;
	    }

	public static String md5(String str) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.reset();
			digest.update(str.getBytes());
			byte[] a = digest.digest();
			int len = a.length;
			StringBuilder sb = new StringBuilder(len << 1);
			for (int i = 0; i < len; i++) {
				sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
				sb.append(Character.forDigit(a[i] & 0x0f, 16));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getOperaTerName(Context context) {
		TelephonyManager telephonyManager = ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE));
		String opeName = telephonyManager.getNetworkOperatorName();
		return opeName ;
	}

	@SuppressLint("HardwareIds")
	public static String getDeviceID(Context context) {
		return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
	}

	public static void ShowMsg(Context mContext,String msg) {

        try {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e ){
            e.printStackTrace();
        }

	}


	public static void ShowMsgSnack(Context context,View view, String msg) {
		try {
			Snackbar.make(view,msg, Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();
		}catch (Exception e){
			ShowMsg(context,msg);
		}


	}

	public static void savePreferences(Context mContext, String key, String value) {
        try {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

	}

	public static String loadSavedPreferences(Context mContext, String key) {
		String str = null;
		try {
			sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
			 str = sharedPreferences.getString(key, "");
		}catch (Exception e){
			e.printStackTrace();
		}

		return str;
	}

	public static void removeSavedPreferences(Context mContext, String key) {
        try {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            sharedPreferences.edit().remove(key).commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }


	}


	public static void CopyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024;
		try {
			byte[] bytes = new byte[buffer_size];
			for (;;) {
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {
		}
	}


	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK)
				>= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	public static String getreferenceID(Context context)
	{     String device = Utility.getDeviceID(context);

	      String digi = difi();
	   String referentID = device+digi ;
	   //Log.d("asasasdededed", referentID);
	   //Log.d("digi", digi);
		return referentID;

	}

	private static String difi()
	{
		Random r = new Random();
		List<Integer> codes = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
		{
		    int x = r.nextInt(9999);
		    while (codes.contains(x))
		        x = r.nextInt(9999);
		    codes.add(x);
		}
		String str = String.format("%04d", codes.get(0));

		return str;
	}

	public static void daialohShow(String result, final Context mContext)
	{
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(mContext);

		// Setting Dialog Title

		alertDialog2.setTitle("Messaeg");

		// Setting Dialog Message
		alertDialog2.setMessage(result);
		alertDialog2.setNegativeButton("OK",
		        new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		                // Write your code here to execute after dialog

		                dialog.cancel();
		            }
		        });

		// Showing Alert Dialog
		alertDialog2.show();
	}

	public static String getCurrentDate(){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateString = fmt.format(date);
		Log.d("dateString", dateString);
		return dateString;
	}
	public static String getCurrentDateAndTime(){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateString = fmt.format(date);
		Log.d("dateString", dateString);
		return dateString;
	}



	public static class TelephonyManagers {
		private static TelephonyManagers instance;

		private Context context;
		private TelephonyManager tm;

		private TelephonyManagers(Context context) {
			if (context instanceof Application) {
				this.context = context;
			} else {
				this.context = context.getApplicationContext();
			}
			tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		}

		public static TelephonyManagers getInstance(Context context) {
			if (instance == null) {
				instance = new TelephonyManagers(context);
			}
			return instance;
		}

		public String getSimOperator() {
			if (tm.getSimOperator() != null) {
				return tm.getSimOperator();
			}
			return "";
		}

		public int getDataActivity() {
			return tm.getDataActivity();
		}

		public int getDataState() {
			return tm.getDataState();
		}

		@SuppressLint("MissingPermission")
		public String getDeviceSoftwareVersion() {
			if (tm.getDeviceSoftwareVersion() != null) {
				return tm.getDeviceSoftwareVersion();
			}
			return "";
		}

		@SuppressLint("MissingPermission")
		public String getGroupLevel1() {
			if (Build.VERSION.SDK_INT >= 18) {
				if (tm.getGroupIdLevel1() != null) {
					return tm.getGroupIdLevel1();
				}
				return "";
			} else {
				return "";
			}
		}
		@SuppressLint("MissingPermission")
		public String getLine1Number() {
			if (tm.getLine1Number() != null) {
				return tm.getLine1Number();
			}
			return "";
		}

		public String getMmsUAProfUrl() {
			if (Build.VERSION.SDK_INT >= 19) {
				if (tm.getMmsUAProfUrl() != null) {
					return tm.getMmsUAProfUrl();
				}
				return "";
			} else {
				return "";
			}
		}

		public String getMmsUserAgent() {
			if (Build.VERSION.SDK_INT >= 19) {
				if (tm.getMmsUserAgent() != null) {
					return tm.getMmsUserAgent();
				}
				return "";
			} else {
				return "";
			}
		}

		public String getNetworkCountryIso() {
			if (tm.getNetworkCountryIso() != null) {
				return tm.getNetworkCountryIso();
			}
			return "";
		}

		public int getNetworkTypeCode() {
			return tm.getNetworkType();
		}

		public int getPhoneTypeCode() {
			return tm.getPhoneType();
		}

		public String getSimCountryIso() {
			if (tm.getSimCountryIso() != null) {
				return tm.getSimCountryIso();
			}
			return "";
		}

		@SuppressLint("MissingPermission")
		public String getSimSerialNumber() {
			if (tm.getSimSerialNumber() != null) {
				return tm.getSimSerialNumber();
			}
			return "";
		}

		public int getSimState() {
			return tm.getSimState();
		}
		@SuppressLint("MissingPermission")
		public String getSubscribedId() {
			if (tm.getSubscriberId() != null) {
				return tm.getSubscriberId();
			}
			return "";
		}
		@SuppressLint("MissingPermission")
		public String getVoiceMailAlphaTag() {
			if (tm.getVoiceMailAlphaTag() != null) {
				return tm.getVoiceMailAlphaTag();
			}
			return "";
		}
		@SuppressLint("MissingPermission")
		public String getVoiceMailNumber() {
			if (tm.getVoiceMailNumber() != null) {
				return tm.getVoiceMailNumber();
			}
			return "";
		}

		public boolean getNetworkRoaming() {
			return tm.isNetworkRoaming();
		}

		public boolean getSmsCapable() {
			if (Build.VERSION.SDK_INT >= 21) {
				return tm.isSmsCapable();
			}
			return false;
		}

		public boolean getVoiceCapable() {
			if (Build.VERSION.SDK_INT >= 22) {
				return tm.isVoiceCapable();
			}
			return false;
		}

	}

	/*Device*/
	public static class Device {
	//	protected final static String TAG = Utilities.TAG + "." + Device.class.getSimpleName();

		public static String getDeviceName() {
			String manufacturer = Build.MANUFACTURER;
			String model = Build.MODEL;
			if (model.startsWith(manufacturer)) {
				return capitalize(model);
			}
			return capitalize(manufacturer) + " " + model;
		}

		private static String capitalize(String str) {
			if (TextUtils.isEmpty(str)) {
				return str;
			}
			char[] arr = str.toCharArray();
			boolean capitalizeNext = true;
			String phrase = "";
			for (char c : arr) {
				if (capitalizeNext && Character.isLetter(c)) {
					phrase += Character.toUpperCase(c);
					capitalizeNext = false;
					continue;
				} else if (Character.isWhitespace(c)) {
					capitalizeNext = true;
				}
				phrase += c;
			}
			return phrase;
		}

		public static String getDeviceId(Context context) {
			return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
		}

		public static String getIMEIActive(Context context) {
			TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(context);
			if (telephonyInfo.isSIM1Ready()) {
				return telephonyInfo.getImsiSIM1();
			} else if (telephonyInfo.isSIM2Ready()) {
				return telephonyInfo.getImsiSIM2();
			} else {
				return "";
			}
		}

		public static String getIMEI1(Context context) {
			try {
				TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(context);
				return telephonyInfo.getImsiSIM1();
			} catch (Exception e) {
			//	UtilsLog.error(TAG, e.getMessage());
				return "";
			}
		}

		public static String getIMEI2(Context context) {
			try {
				TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(context);
				if (telephonyInfo.isDualSIM()) {
					return telephonyInfo.getImsiSIM2();
				} else {
					return "";
				}
			} catch (Exception e) {
			//	UtilsLog.error(TAG, e.getMessage());
				return "";
			}
		}

		public static boolean isNewVersion(String vName, int vCode) {
			String versionName = BuildConfig.VERSION_NAME;
			int versionCode = BuildConfig.VERSION_CODE;

			if (!versionName.equals(vName) || (versionName.equals(vName) && versionCode < vCode)) {
				return true;
			} else {
				return false;
			}
		}

		public static boolean isSimState(Context context) {
			TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			int simState = manager.getSimState();
			switch (simState) {
				case TelephonyManager.SIM_STATE_ABSENT:
					return false;
				case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
					return false;
				case TelephonyManager.SIM_STATE_PIN_REQUIRED:
					return false;
				case TelephonyManager.SIM_STATE_PUK_REQUIRED:
					return false;
				case TelephonyManager.SIM_STATE_READY:
					return true;
				case TelephonyManager.NETWORK_TYPE_UNKNOWN:;
					return false;
				default:
					return false;
			}
		}


		public static int getSoftwareVersion() {
			return android.os.Build.VERSION.SDK_INT;
		}

		public static void hideSoftKeyboard(Context context) {
			Activity activity = ((Activity) context);
			if (activity.getCurrentFocus() != null) {
				InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
			}
		}

		public static void showSoftKeyboard(Context context, View view) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			view.requestFocus();
			imm.showSoftInput(view, 0);
		}

		public static String getShowVersion() {
			String versionName = BuildConfig.VERSION_NAME;
			int versionCode = BuildConfig.VERSION_CODE;

			String version = "Version " + versionName + "(" + versionCode + ")";
			if (BuildConfig.BUILD_TYPE.equals("debug")) {
				version = "Version " + versionName + "(" + versionCode + ") " + BuildConfig.BUILD_TYPE + "-" + String.valueOf(BuildConfig.FLAVOR);
			}

			return version;
		}

		public static String getBuildVariant() {
			String buildType = BuildConfig.BUILD_TYPE;
			String flavor = BuildConfig.FLAVOR;
			return flavor + "-" + buildType;
		}
	}

}
