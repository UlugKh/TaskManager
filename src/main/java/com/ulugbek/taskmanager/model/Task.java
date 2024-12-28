package com.ulugbek.taskmanager.model;

import com.ulugbek.taskmanager.model.datatypes.TaskStatus;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Task {
    //using properties since working with a database and a tableview in fxml
    private final SimpleStringProperty taskID;
    private final SimpleStringProperty name;
    private final SimpleStringProperty status;
    private final SimpleObjectProperty<Date> dueDate;
    private final SimpleIntegerProperty urgency; //implement sort by urgency

    public Task(String name, TaskStatus status, Date dueDate) {
        this.taskID = new SimpleStringProperty(generateID());
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status.toString());
        this.dueDate = new SimpleObjectProperty<>(dueDate);
        this.urgency = new SimpleIntegerProperty(1); //urgency default 1
    }

    public Task(String name, TaskStatus status, Date dueDate, String taskID) {
        this.taskID = new SimpleStringProperty(taskID);
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status.toString());
        this.dueDate = new SimpleObjectProperty<>(dueDate);
        this.urgency = new SimpleIntegerProperty(1); //urgency default 1
    }

    //when making an add method, validate the date! SQLite has a specific way to store it (yyyy-mm-dd)

    private String generateID() {
        return UUID.randomUUID().toString(); // Generate a random unique ID
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getTaskID() {
        return taskID.get();
    }

    public void setTaskID(String id) {
        this.taskID.set(id);
    }

    public SimpleStringProperty taskIDProperty() {
        return taskID;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(TaskStatus status) {
        this.status.set(status.toString());
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public Date getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(Date dueDate) {
        this.dueDate.set(dueDate);
    }

    public String getFormattedDueDate() {
        if (dueDate.get() == null) {
            return "No Due Date";  // Return some default message if null
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(dueDate.get());
    }


    public SimpleObjectProperty<Date> dueDateProperty() {
        return dueDate;
    }

    public int getUrgency() {
        return urgency.get();
    }

    public void setUrgency(Integer urgency) {
        this.urgency.set(urgency);
    }

    public SimpleIntegerProperty urgencyProperty() {
        return urgency;
    }
}
