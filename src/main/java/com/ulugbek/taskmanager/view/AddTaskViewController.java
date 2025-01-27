package com.ulugbek.taskmanager.view;

import com.ulugbek.taskmanager.controller.TaskController;
import com.ulugbek.taskmanager.model.Task;
import com.ulugbek.taskmanager.model.datatypes.TaskStatus;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.time.LocalDate;
import java.util.Date;

import static com.ulugbek.taskmanager.util.DateUtil.retrieveUserInputDate;
import static com.ulugbek.taskmanager.util.StatusUtil.toStatusEnum;
import static com.ulugbek.taskmanager.util.alertClass.showAlert;

public class AddTaskViewController {
    private TaskController controller = new TaskController();
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private Button advancedButton;
    @FXML
    private VBox advancedUrgencyVBox;
    @FXML
    private TextField taskNameTextField;
    @FXML
    private DatePicker dueDateDatePicker;
    @FXML
    private Spinner hourSpinner;
    @FXML
    private Spinner minuteSpinner;
    @FXML
    private Spinner urgencySpinner;
    @FXML
    private Button okButton;

    private ObservableList<Task> taskList;

    public void setTaskList(ObservableList taskList) {
        this.taskList = taskList;
    }
    @FXML
    public void initialize() {
        statusComboBox.getItems().addAll("Pending", "In Progress", "Completed");
        statusComboBox.setValue("Pending");
        dueDateDatePicker.setValue(LocalDate.now());
        advancedUrgencyVBox.setVisible(false);
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        urgencySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, 1));
    }

    @FXML
    public void setAdvancedVisibility() {
        boolean visibility = advancedUrgencyVBox.isVisible();
        advancedUrgencyVBox.setVisible(!visibility);
    }

    @FXML
    public void handleAddTaskButton(ActionEvent event) {
        //if required fields empty, receive alert
        if(taskNameTextField.getText()==null || taskNameTextField.getText().isEmpty()) {
            showAlert("Error", "Required Field is Empty", "Please fill out all required (*) fields");
        } else {
            //add task
            LocalDate selectedDate = dueDateDatePicker.getValue();
            int hours = (int) hourSpinner.getValue();
            int minutes = (int) minuteSpinner.getValue();
            Date dueDate = retrieveUserInputDate(selectedDate, hours, minutes);
            String taskName = taskNameTextField.getText();
            TaskStatus taskStatus = toStatusEnum(statusComboBox.getValue());

            Task tempTask = new Task(taskName, taskStatus, dueDate);

            controller.addTask(tempTask);
            taskList.add(tempTask);

            showAlert("Success", "Task Added", "The task is added successfully!");
            //close the fxml after adding
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        }
    }


}
