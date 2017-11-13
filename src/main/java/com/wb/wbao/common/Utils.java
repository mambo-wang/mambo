package com.wb.wbao.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utils {

    private static final DateTimeFormatter FORMATTER_FULL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Long time, DateTimeFormatter formatter) {
        Instant instant = Instant.ofEpochMilli(time);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, Constant.BEIJING_ZONE);
        return localDateTime.format(formatter);
    }

    public static String formatFullDateTime(Long datetime) {
        return formatDate(datetime, FORMATTER_FULL);
    }
}
