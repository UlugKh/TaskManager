package com.ulugbek.taskmanager.view;

import com.ulugbek.taskmanager.controller.TaskController;
import com.ulugbek.taskmanager.model.Task;
import com.ulugbek.taskmanager.model.datatypes.TaskStatus;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;

import static com.ulugbek.taskmanager.util.DateUtil.*;
import static com.ulugbek.taskmanager.util.StatusUtil.fromStatusEnum;
import static com.ulugbek.taskmanager.util.StatusUtil.toStatusEnum;
import static com.ulugbek.taskmanager.util.alertClass.showAlert;

public class UpdateTaskViewController {
    private TaskController controller = new TaskController();
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private Button searchButton;
    @FXML
    private VBox advancedUrgencyVBox;
    @FXML
    private TextField taskNameTextField;
    @FXML
    private TextField taskIDTextField;
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

    private String currentTaskID = null;

    private ObservableList<Task> taskList;

    public void setTaskList(ObservableList taskList) {
        this.taskList = taskList;
    }

    public String getCurrentTaskID() {
        return currentTaskID;
    }

    @FXML
    public void initialize(){
        statusComboBox.getItems().addAll("Pending", "In Progress", "Completed");
        dueDateDatePicker.setValue(LocalDate.now());
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        urgencySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, 1));
        okButton.setVisible(false); //cant update unless ID specified
    }

    @FXML
    public void handleSearchButton(ActionEvent event){
        if (taskIDTextField.getText().isEmpty() || taskIDTextField.getText() == null) {
            showAlert("Error", "Required Field is Empty", "Please fill out all required (*) fields");
        } else {
            String taskID = taskIDTextField.getText().toUpperCase();
            //set the chosen task info into the input data to make it easier to fill out or change
            if (controller.exists(taskID)) {
                okButton.setVisible(true); //ok button appears if ID exists
                this.currentTaskID = taskID; //store the ID in a global variable to know which task to change (stores only if exists)
                Task taskFound = controller.retrieveTask(taskID);
                taskNameTextField.setPromptText(taskFound.getName()); //prompt text as an exception to make for easier edits
                statusComboBox.setValue(taskFound.getStatus());
                Date taskDate = taskFound.getDueDate();
                dueDateDatePicker.setValue(toLocalDate(taskDate));
                hourSpinner.getValueFactory().setValue(getHour(taskDate));
                minuteSpinner.getValueFactory().setValue(getMinutes(taskDate));
                urgencySpinner.getValueFactory().setValue(taskFound.getUrgency());
                //continue doing this for all fields
            } else {
                showAlert("Error", "No task with given ID", "Please enter an existing ID");
            }
        }
    }

    @FXML
    public void handleUpdateOkButton(ActionEvent event) {
        if (taskNameTextField.getText().isEmpty() || taskNameTextField.getText() == null) {
            showAlert("Error", "Required Field is Empty", "Please fill out all required (*) fields");
        } else {
            String tempID = getCurrentTaskID();
            if (tempID == null) { //only null if no existing ID has been confirmed
                System.out.print("tempID is null");
                showAlert("Error", "ID is empty or incorrect", "Please enter a valid ID");
            } else {
                String tempName = taskNameTextField.getText();
                Date tempDueDate = retrieveUserInputDate(dueDateDatePicker.getValue(), (Integer) hourSpinner.getValue(), (Integer) minuteSpinner.getValue());
                TaskStatus tempStatus = toStatusEnum(statusComboBox.getValue());
                Integer tempUrgency = (Integer) urgencySpinner.getValue();
                Task tempTask = new Task(tempName, tempStatus, tempDueDate, tempID);
                tempTask.setUrgency(tempUrgency);

                controller.updateTask(tempTask);
                for(Task task: taskList) { //update the observable list
                    if (task.getTaskID().equals(tempID)) {
                        task.setName(tempName);
                        task.setUrgency(tempUrgency);
                        task.setStatus(tempStatus);
                        task.setDueDate(tempDueDate);
                    }
                }

                showAlert("Success", "Task Updated", "The task is updated successfully!");
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            }
        }
    }


}
