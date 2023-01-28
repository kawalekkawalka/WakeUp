package com.example.wakeup.ui.main.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalTimeConverter {
    @TypeConverter
    public static LocalTime fromTimestamp(String value) {
        return value == null ? null : LocalTime.parse(value);
    }

    @TypeConverter
    public static String dateToTimestamp(LocalTime localTime) {
        return localTime == null ? null : localTime.toString();
    }
}
