package com.ulugbek.taskmanager.view;

import com.ulugbek.taskmanager.controller.TaskController;
import com.ulugbek.taskmanager.model.Task;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.ulugbek.taskmanager.util.alertClass.showAlert;


public class DeleteTaskViewController {
    TaskController taskController = new TaskController();
    @FXML
    private TextField IDTextField;
    @FXML
    private Button okButton;

    private ObservableList<Task> taskList;


    public void setTaskList(ObservableList taskList) {
        this.taskList = taskList;
    }

    @FXML
    public void initialize() {
    }

    @FXML
    private void deleteTaskButton(ActionEvent event) {
        if(IDTextField.getText() == null || IDTextField.getText().isEmpty()) {
            showAlert("Error", "ID is required", "Task ID was not provided");
        } else {
            String taskID = IDTextField.getText().toUpperCase();
            boolean exists = taskController.exists(taskID);
            if (exists) {
                Task taskToRemove = taskList.stream()
                        .filter(task -> task.getTaskID().equals(taskID))
                        .findFirst()
                        .orElse(null);
                taskList.remove(taskToRemove);
                taskController.deleteTask(taskID);
                showAlert("Success", "Task Deleted", "Task with ID " + taskID + " deleted successfully");
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } else {
                showAlert("Error", "Task Not Found", "Task with ID " + taskID + " not found");
            }
        }
    }
}
