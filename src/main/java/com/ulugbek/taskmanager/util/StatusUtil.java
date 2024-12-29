package com.ulugbek.taskmanager.util;

import com.ulugbek.taskmanager.model.datatypes.TaskStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusUtil {
    public static TaskStatus toStatusEnum(String stringStatus) {
        try {
            if(stringStatus.equalsIgnoreCase("INPROGRESS")) {
                return TaskStatus.IN_PROGRESS;
            } else {
                return TaskStatus.valueOf(stringStatus.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
            return TaskStatus.PENDING; //default is PENDING if error
        }
    }
}
