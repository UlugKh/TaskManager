package com.ulugbek.taskmanager.view;

import com.ulugbek.taskmanager.controller.TaskController;
import com.ulugbek.taskmanager.model.Task;
import com.ulugbek.taskmanager.model.datatypes.TaskStatus;
import com.ulugbek.taskmanager.util.alertClass;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

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

    @FXML
    public void initialize() {
        statusComboBox.getItems().addAll("Pending", "In Progress", "Completed");
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
    public void handleAddTaskButton() {
        //if required fields empty, receive alert
        if(taskNameTextField.getText()==null) {
            showAlert("Error", "Required Field is Empty", "Please fill out all required (*) fields");
        } else {
            //add task
            LocalDate selectedDate = dueDateDatePicker.getValue();
            int hours = (int) hourSpinner.getValue();
            int minutes = (int) minuteSpinner.getValue();
            Date dueDate = retrieveUserInputDate(selectedDate, hours, minutes);
            String taskName = taskNameTextField.getText();
            TaskStatus taskStatus = toStatusEnum(statusComboBox.getValue());

            controller.addTask(new Task(taskName, taskStatus, dueDate));

            showAlert("Success", "Task Added", "The task is added successfully!");
        }
    }


}
