package com.ulugbek.taskmanager.view;

import com.ulugbek.taskmanager.controller.TaskController;
import com.ulugbek.taskmanager.model.Task;
import com.ulugbek.taskmanager.model.datatypes.TaskStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Button deleteTaskButton;
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

    private ObservableList<Task> taskList;

    public void initialize() {
        //link each column in the table to the respective Task property
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        taskIDColumn.setCellValueFactory(new PropertyValueFactory<>("taskID"));
        taskStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        taskDueDateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDueDate"));

        //needed to show the dynamic changes to the database in the UI
        ObservableList<Task> taskList = FXCollections.observableArrayList();
        this.taskList = taskList;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ulugbek/taskmanager/AddTask.fxml"));
        Parent root = loader.load();
        //pass the observable
        AddTaskViewController addTaskViewController = loader.getController();
        addTaskViewController.setTaskList(taskList);
        //show
        Scene scene = new Scene(root);
        Stage addStage = new Stage();
        addStage.setTitle("Add Task");
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    public void handleDeleteTask() throws IOException{
        //show the delete fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ulugbek/taskmanager/DeleteTask.fxml"));
        Parent root = loader.load();
        //pass the observable
        DeleteTaskViewController deleteTaskViewController = loader.getController();
        deleteTaskViewController.setTaskList(taskList);
        //show
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Delete Task");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleUpdateTask() throws IOException{
        //show the update fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ulugbek/taskmanager/UpdateTask.fxml"));
        Parent root = loader.load();
        //pass the observable
        UpdateTaskViewController updateTaskViewController = loader.getController();
        updateTaskViewController.setTaskList(taskList);
        //show
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Update Task");
        stage.setScene(scene);
        stage.show();
    }
}
