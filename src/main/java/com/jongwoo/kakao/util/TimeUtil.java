package com.jongwoo.kakao.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {
	
	public static Timestamp getCurrentSeoulTime() {
        TimeZone time;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = TimeZone.getTimeZone("Asia/Seoul");
        df.setTimeZone(time);
        return Timestamp.valueOf(df.format(new Date()));
	}
	
	public static long getDaysBetween(Timestamp start, Timestamp end) {
        long milliseconds = end.getTime() - start.getTime();
        long seconds = milliseconds / 1000;
        long hours = (seconds / 3600);
        long days = hours / 24;
        return days;
    }

    public static long getMinutesBetween(Timestamp start, Timestamp end) {
        long milliseconds = end.getTime() - start.getTime();
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        return minutes;
    }
    
    public static Timestamp getSpecificTime(int year, int month, int day, int hour, int minute, int secont){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ofDateTime = LocalDateTime.of(year, month, day, hour, minute, secont);
        Date d = Date.from(ofDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return Timestamp.valueOf(df.format(d));

     }

}
