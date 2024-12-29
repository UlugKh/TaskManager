package com.ulugbek.taskmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String sql = "jdbc:sqlite:D:\\IntelijIDEA\\TaskManager\\src\\main\\resources\\com\\ulugbek\\taskmanager\\tasksDatabase.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(sql);
    }
}
