package com.example.wakeup.ui.main.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalDateConverter {
    @TypeConverter
    public static LocalDate fromTimestamp(Long value) {
        return value == null ? null : Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @TypeConverter
    public static Long localDateToTimestamp(LocalDate localDate) {
        return localDate == null ? null : localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
