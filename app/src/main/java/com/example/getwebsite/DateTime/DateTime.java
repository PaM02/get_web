package com.example.getwebsite.DateTime;
import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
        import java.util.Date;

public class DateTime {

    public static String getDateEN() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yy");
        String date = "Date:"+format1.format(new Date(System.currentTimeMillis()));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
        String Mydate =date+" à "+format2.format(new Date(System.currentTimeMillis()));
        return Mydate;// 03-10-2012 23:41:31
    }

}