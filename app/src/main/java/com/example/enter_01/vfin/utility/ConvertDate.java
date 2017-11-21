package com.example.enter_01.vfin.utility;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by thana on 5/10/2017.
 */

public class ConvertDate {

    private static ConvertDate instance = null;

    public static ConvertDate getInstance() {
        if (instance == null)
            instance = new ConvertDate();
        return instance;
    }

    public String TimestampToFormatDate(String timeLong, String formatDate) {
        try {
            SimpleDateFormat objFormatter = new SimpleDateFormat(formatDate, new Locale("th", "TH"));
            Calendar objCalendar = Calendar.getInstance();
            TimeZone tz = objCalendar.getTimeZone();
            try {

                objCalendar.setTimeInMillis(Long.valueOf(timeLong)*1000);
            }catch (NumberFormatException e){

                objCalendar.setTimeInMillis((long) (Double.valueOf(timeLong)*1000));
            }
            int year = objCalendar.get(Calendar.YEAR) + 543;
            objFormatter.setTimeZone(tz);
            String result = objFormatter.format(objCalendar.getTime());
            objCalendar.clear();
            return result + "" + year;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

    public String  TimestampToFormatDateAndTimeTH(String timeLong, String formatDate) {
        try {
            SimpleDateFormat objFormatter = new SimpleDateFormat(formatDate, new Locale("th", "TH"));
            Calendar objCalendar = Calendar.getInstance();
            TimeZone tz = objCalendar.getTimeZone();
            try {

                objCalendar.setTimeInMillis(Long.valueOf(timeLong)*1000);
            }catch (NumberFormatException e){

                objCalendar.setTimeInMillis((long) (Double.valueOf(timeLong)*1000));
            }
            int year = objCalendar.get(Calendar.YEAR) + 543;
            objCalendar.set(Calendar.YEAR, year);
            objFormatter.setTimeZone(tz);
            String result = objFormatter.format(objCalendar.getTime());
            objCalendar.clear();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }


    public String TimestampToFormatDate2(String timeLong, String formatDate) {
        try {
            SimpleDateFormat objFormatter = new SimpleDateFormat(formatDate, new Locale("th", "TH"));
            Calendar objCalendar = Calendar.getInstance();
            TimeZone tz = objCalendar.getTimeZone();
            try {

                objCalendar.setTimeInMillis(Long.valueOf(timeLong)*1000);
            }catch (NumberFormatException e){

                objCalendar.setTimeInMillis((long) (Double.valueOf(timeLong)*1000));
            }
            objFormatter.setTimeZone(tz);
            String result = objFormatter.format(objCalendar.getTime());
            objCalendar.clear();
            return result ;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

    public String TimestampToFormatTime(String timeLong) {
        try {
            DateFormat objFormatter = new SimpleDateFormat("HH.mm");
            // objFormatter.setTimeZone(TimeZone.getTimeZone(timeLong));
            Calendar objCalendar = Calendar.getInstance(TimeZone.getTimeZone(timeLong));
            try {

                objCalendar.setTimeInMillis(Long.valueOf(timeLong)*1000);
            }catch (NumberFormatException e){

                objCalendar.setTimeInMillis((long) (Double.valueOf(timeLong)*1000));
            }

            String result = objFormatter.format(objCalendar.getTime());
            objCalendar.clear();
            Log.e("TimestampToFormatTime", "Time > " + result);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return "";

        }




    }

    public Boolean CalculateTimeRegisterPretest(String dateTimeLong) {
        DateFormat objFormatter = new SimpleDateFormat(dateTimeLong, new Locale("th", "TH"));
        objFormatter.setTimeZone(TimeZone.getTimeZone(dateTimeLong));
        Calendar objCalendar = Calendar.getInstance(TimeZone.getTimeZone(dateTimeLong));
        int year = objCalendar.get(Calendar.YEAR) + 543;
        String result = objFormatter.format(objCalendar.getTime());
        objCalendar.clear();
        Log.e("TimestampToFormatDate", "Date > " + result +" || " + year);
        return true;
    }

    public String getDateTimeNow() {
        Calendar objCalendar = Calendar.getInstance();
        SimpleDateFormat  objFormatter = new SimpleDateFormat("dd MMMM ", new Locale("th", "TH"));
        int year = objCalendar.get(Calendar.YEAR) + 543;
        String result = objFormatter.format(objCalendar.getTime());
        Log.e("TimestampToFormatDate", "Date > " + result +" || " + year);
        return result + "" +year;
    }

    @SuppressLint("DefaultLocale")
    public String getTimeSecToHour(int sec) {
        int min = sec / 60;
        int hour = min / 60;
        sec = sec % 60;
        min = min % 60;
        return String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec);
    }

    public String getTimeLess(int sec) {
        int min = sec / 60;
        int hour = min / 60;
        sec = sec % 60;
        min = min % 60;
        return String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec);
    }

    public String getTimeLessPretest(int sec) {
        int min = sec / 60;
        int hour = min / 60;
        sec = sec % 60;
        min = min % 60;
        return String.format("%02d", min) + ":" + String.format("%02d", sec);
    }
}
