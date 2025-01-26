package com.ulugbek.taskmanager.util;

import com.ulugbek.taskmanager.model.datatypes.TaskStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusUtil {
    public static TaskStatus toStatusEnum(String stringStatus) {
        try {
            if(stringStatus.equalsIgnoreCase("INPROGRESS") || stringStatus.equalsIgnoreCase("IN PROGRESS")) {
                return TaskStatus.IN_PROGRESS;
            } else {
                return TaskStatus.valueOf(stringStatus.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            System.out.print("There was an error with in conversion");
            return TaskStatus.PENDING; //default is PENDING if error
        }
    }
    public static String fromStatusEnum(TaskStatus enumStatus) {
        switch(enumStatus){
            case PENDING -> {
                return "Pending";
            }
            case IN_PROGRESS -> {
                return "In Progress";
            }
            case COMPLETED -> {
                return "Completed";
            }
        }
        System.out.print("Conversion from status to string failed");
        return "";
    }
}
