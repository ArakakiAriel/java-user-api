package com.example.apiuser.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static String parseDate(Timestamp time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(time);
    }

    public static Timestamp getTimestampNow(){
        LocalDateTime time = LocalDateTime.now() ;
        Timestamp now = Timestamp.valueOf(time);

        return now;
    }

    public static String getStringTimestampNow(){
        Timestamp now = getTimestampNow();
        return parseDate(now);
    }
}
