package com.example.demo.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;

public class DateUtils {

    public static String parseDate(Timestamp time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(time);
    }
}
