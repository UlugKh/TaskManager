package com.ulugbek.taskmanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String formatDate(Date date) {
        return date == null ? null : dateFormat.format(date);    }

    public static Date toDate(String stringDate) {
        if (stringDate != null && !stringDate.isEmpty()) {
            try {
                return dateFormat.parse(stringDate);
            } catch (IllegalArgumentException | ParseException e) {
                System.out.print(e.getMessage());
                System.out.println(" Invalid date format for date: " + stringDate);
                return null;
            }
        } else {
            System.out.println("Date cannot be null or empty.");
            return null;
        }
    }
    public static Date createDate(int year, int month, int day, int hour, int minute) throws ParseException {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat1.parse(year + "-" + month + "-" + day + " " + hour + ":" + minute);
    }
}