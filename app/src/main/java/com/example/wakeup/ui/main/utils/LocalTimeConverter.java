package com.example.wakeup.ui.main.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalTimeConverter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @TypeConverter
    public static LocalTime fromTimestamp(String value) {
        return value == null ? null : LocalTime.parse(value, FORMATTER);
    }

    @TypeConverter
    public static String dateToTimestamp(LocalTime localTime) {
        return localTime == null ? null : localTime.format(FORMATTER);
    }
}
