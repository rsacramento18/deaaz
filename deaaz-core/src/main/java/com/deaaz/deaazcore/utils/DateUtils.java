package com.deaaz.deaazcore.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static ZoneId zutc = ZoneId.of("UTC");

    public static DateTimeFormatter dateDocument = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static DateTimeFormatter dateShort = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    public static DateTimeFormatter dateTimeSec = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static DateTimeFormatter dateTimeShortMilli = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");

    public static DateTimeFormatter dateTimeLongMilli = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static LocalDateTime DateToLocalDateTime(Date date) {
        if (date != null) {
            LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), zutc);
            return ldt;
        }
        return null;
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            Date out = Date.from(localDateTime.atZone(zutc).toInstant());
            return out;
        }
        return null;
    }

    public static Date stringToDate(String dateString) {

        Date date = new Date();

        try {
            LocalDate date2 = LocalDate.parse(dateString, dateShort);
            ZonedDateTime date3 = date2.atStartOfDay(ZoneId.systemDefault());
            Instant date4 = date3.toInstant();
            date = Date.from(date4);

        } catch (DateTimeParseException exc) {
            return null;
        }

        return date;

    }

    public static LocalDateTime stringToLocalDateTime(String dateTimeFormat) {

        LocalDateTime formatDateTime;

        try {
            formatDateTime = LocalDateTime.parse(dateTimeFormat, dateTimeSec);
        } catch (DateTimeParseException e) {
            try {
                formatDateTime = LocalDateTime.parse(dateTimeFormat, dateTimeShortMilli);
            } catch (DateTimeParseException ex) {
                try {
                    formatDateTime = LocalDateTime.parse(dateTimeFormat, dateShort);
                } catch (DateTimeParseException exp) {
                    formatDateTime = LocalDateTime.parse(dateTimeFormat, dateTimeLongMilli);
                }
            }
        }

        return formatDateTime;
    }

    public static String localDatetimeToString(LocalDateTime date) {

        String formatDateTime = date.format(dateShort);

        return formatDateTime;
    }

    public static String nowToDate() {
        return LocalDateTime.now().format(dateDocument);
    }

}
