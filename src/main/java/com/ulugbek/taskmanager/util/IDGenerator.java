package com.ulugbek.taskmanager.util;

import java.util.Random;

public class IDGenerator {
    public static String generateCustomID() {
        Random random = new Random();

        // Generate a 6-digit integer
        int number = 100000 + random.nextInt(900000);

        // Generate two random uppercase letters
        char letter1 = (char) ('A' + random.nextInt(26));
        char letter2 = (char) ('A' + random.nextInt(26));

        return "" + letter1 + letter2 + number;
    }
}