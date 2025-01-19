package com.ulugbek.taskmanager.view;

import com.ulugbek.taskmanager.controller.TaskController;
import com.ulugbek.taskmanager.model.Task;
import com.ulugbek.taskmanager.model.datatypes.TaskStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import static com.ulugbek.taskmanager.util.DatabaseConnection.getConnection;
import static com.ulugbek.taskmanager.util.DateUtil.toDate;
import static com.ulugbek.taskmanager.util.StatusUtil.toStatusEnum;

public class MainViewController {
    TaskController taskController;
    public Scene scene;
    public Stage stage;

    @FXML
    private Button addTaskButton;
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

        //display database values in the taskList
        try(Connection conn = getConnection();
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
                TaskStatus status = toStatusEnum(tempStatus);
                Date dueDate = toDate(tempDueDate);

                taskList.add(new Task(name, status, dueDate, ID));
            }

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        taskTable.setItems(taskList);
    }

    @FXML
    public void handleAddTask() throws IOException {
        //show the add fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("/com/ulugbek/taskmanager/AddTask.fxml")));
        Scene scene = new Scene(loader.load());
        Stage addStage = new Stage();
        addStage.setTitle("Add Task");
        addStage.setScene(scene);
        addStage.show();


    }
}
