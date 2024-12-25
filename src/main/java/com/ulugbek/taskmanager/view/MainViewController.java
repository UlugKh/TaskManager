package com.ulugbek.taskmanager.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainViewController {
    public Scene scene;
    public Stage stage;

    @FXML
    private TableView<?> taskTable;

    @FXML
    private Label statusLabel;
}
