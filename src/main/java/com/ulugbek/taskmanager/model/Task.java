package com.ulugbek.taskmanager.model;

import com.ulugbek.taskmanager.model.datatypes.TaskStatus;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;
import java.util.UUID;

public class Task {
    //using properties since working with a database and a tableview in fxml
    private final SimpleStringProperty taskID;
    private final SimpleStringProperty name;
    private final SimpleStringProperty status;
    private final SimpleStringProperty dueDate;
    private final SimpleIntegerProperty urgency; //implement sort by urgency

    public Task(String name, TaskStatus status, Date dueDate) {
        this.taskID = new SimpleStringProperty(generateID());
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status.toString());
        this.dueDate = new SimpleStringProperty(dueDate.toString());
        this.urgency = new SimpleIntegerProperty(1); //urgency default 1
    }

    public Task(String name, TaskStatus status, Date dueDate, String taskID) {
        this.taskID = new SimpleStringProperty(taskID);
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status.toString());
        this.dueDate = new SimpleStringProperty(dueDate.toString());
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

    public String getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(Date dueDate) {
        this.dueDate.set(dueDate!=null? dueDate.toString() : "No Due Date");
    }

    public SimpleStringProperty dueDateProperty() {
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
