package com.ulugbek.taskmanager.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class alertClass {
    public static void showAlert(String title, String header, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
