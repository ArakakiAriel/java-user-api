package com.example.apiuser.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static String parseDate(Timestamp time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(time);
    }

    public static Timestamp getTimestampNow(){

        //Date object
        Date date= new Date();
        //getTime() returns current time in milliseconds
        long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class
        Timestamp now = new Timestamp(time);

        return now;
    }

    public static String getStringTimestampNow(){
        Timestamp now = getTimestampNow();
        return parseDate(now);
    }
}
