package com.ulugbek.taskmanager.view;

import com.ulugbek.taskmanager.model.Task;
import com.ulugbek.taskmanager.model.datatypes.TaskStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainViewController {
    public Scene scene;
    public Stage stage;

    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    @FXML
    private TableColumn<Task, String> taskIDColumn;
    @FXML
    private TableColumn<Task, String> taskStatusColumn;
    @FXML
    private TableColumn<Task, String> taskDueDateColumn;

    @FXML
    private Label statusLabel;

    public void initialize() {
        //link each column in the table to the respective Task property
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        taskIDColumn.setCellValueFactory(new PropertyValueFactory<>("taskID"));
        taskStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        taskDueDateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDueDate"));

        ObservableList<Task> taskList = FXCollections.observableArrayList();
        String sql = "SELECT ID, Name, [Due Date], Status FROM Tasks";

        //connection with database
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\IntelijIDEA\\TaskManager\\src\\main\\resources\\com\\ulugbek\\taskmanager\\tasksDatabase.db");
            Statement stm = conn.createStatement();
            ResultSet resSet = stm.executeQuery(sql);)
        {
            while (resSet.next()) {
                //iterate and take each property
                String name = resSet.getString("Name");
                String ID = resSet.getString("ID");
                String tempDueDate = resSet.getString("Due Date");
                String tempStatus = resSet.getString("Status");

                // convert into respective types for Task object creation
                TaskStatus status = null;
                try {
                    if(tempStatus.equalsIgnoreCase("INPROGRESS")) {
                        status = TaskStatus.IN_PROGRESS;
                    } else {
                        status = TaskStatus.valueOf(tempStatus.toUpperCase());
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid status value for task ID " + ID + ": " + tempStatus);
                    status = TaskStatus.PENDING; // default is PENDING
                }

                Date dueDate = null;
                if (tempDueDate != null && !tempDueDate.isEmpty()) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dueDate = dateFormat.parse(tempDueDate);
                    } catch (Exception e) {
                        System.out.println("Invalid date format for task ID " + ID + ": " + tempDueDate);
                    }
                }

                assert dueDate != null;
                taskList.add(new Task(name, status, dueDate, ID));

            }

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        taskTable.setItems(taskList);
    }
}
