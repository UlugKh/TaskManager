package com.ulugbek.taskmanager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TaskManagerApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Button
        Button btn = new Button("Click Me");

        // Set an action on the Button click
        btn.setOnAction(e -> System.out.println("Button Clicked!"));

        // Create a layout (StackPane in this case)
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // Create a Scene
        Scene scene = new Scene(root, 300, 250);

        // Set the Scene on the Stage
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
