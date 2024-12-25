package com.ulugbek.taskmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskManagerApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ulugbek/taskmanager/Main.fxml"));

        // Set the scene
        Scene scene = new Scene(loader.load());

        // Set the stage
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //launch
    public static void main(String[] args) {
        launch(args);
    }
}

