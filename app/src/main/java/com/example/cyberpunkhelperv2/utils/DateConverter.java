package com.example.cyberpunkhelperv2.utils;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String getDayMonth(Date date) {
        SimpleDateFormat dayFormat =  new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        String day = dayFormat.format(date);
        String monthString = monthFormat.format(date);
        return day + "." + monthString;
    }
}
