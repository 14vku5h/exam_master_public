package com.lkvcodestudio.exammaster;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {

    public static String df = "yyyy-MM-dd HH:mm";
    public static String fs = "yyyy-MM-dd HH:mm:ss";
    public static String displayFormat = "dd MMM yyy hh:mm a";
    public static String dfInput = "yyyy-MM-dd";
    public static String dfOutput = "dd MMM yyyy";


    public static LocalDateTime convertIntsToDateTime(int yyyy, int mm, int dd, int hh, int min) {
        String dateStr = yyyy + "-" + (mm < 10 ? "0" + mm : mm) + "-" + (dd < 10 ? "0" + dd : dd) + " " + (hh < 10 ? "0" + hh : hh) + ":" + (min < 10 ? "0" + min : min);
        LocalDateTime date = null;
        if (dateStr != null) {
            DateTimeFormatter dateTimeFormatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dateTimeFormatter = DateTimeFormatter.ofPattern(df);
                date = LocalDateTime.parse(dateStr, dateTimeFormatter);
            }

        }
        return date;
    }

    public static String convertStrToDateStr(int yyyy, int mm, int dd) {
        String dateStr = yyyy + "-" + (mm < 10 ? "0" + mm : mm) + "-" + (dd < 10 ? "0" + dd : dd);
        LocalDate date = null;
        String str = null;
        if (dateStr != null) {
            DateTimeFormatter dateTimeFormatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dateTimeFormatter = DateTimeFormatter.ofPattern(dfInput);
                date = LocalDate.parse(dateStr, dateTimeFormatter);
                str = date.format(DateTimeFormatter.ofPattern(dfOutput));
            }

        }
        return str;
    }

    public static LocalDateTime convertStrToDateTime(String dateStr, String format) {
        LocalDateTime date = null;
        if (dateStr != null) {
            DateTimeFormatter dateTimeFormatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dateTimeFormatter = DateTimeFormatter.ofPattern(format);
                date = LocalDateTime.parse(dateStr, dateTimeFormatter);
            }

        }
        return date;
    }

    public static String convertDateTimeToStr(LocalDateTime dateTime, String format) {
        String dateStr = null;
        if (dateTime != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
                dateStr = dateTime.format(dateTimeFormatter);
            }
        }
        return dateStr;
    }


    public static String convertToTimeStr(Integer hh, Integer mm) {
        String a = "AM";
        if (hh > 12) {
            hh = hh - 12;
            a = "PM";
        }

        return (hh < 10 ? "0" + hh : hh) + ":" + (mm < 10 ? "0" + mm : mm) + " " + a;
    }

    public static String getCurrentTimeStamp() {

        SimpleDateFormat sdfDate = new SimpleDateFormat(fs);
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static Date convertStrToDate(String addedOn, String fs) {
        DateFormat sdf = new SimpleDateFormat(fs);
        Date today=null;
        try {
             today = sdf.parse(addedOn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return today;
    }
}
