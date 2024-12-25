package com.ulugbek.taskmanager.model;

import com.ulugbek.taskmanager.model.datatypes.TaskStatus;

import java.util.Date;
import java.util.UUID;

public class Task {
    private String taskID;
    private String name;
    private TaskStatus status;
    private Date dueDate;
    private int urgency; //implement sort by urgency

    public Task(String taskID, String name, TaskStatus status, Date dueDate) {
        this.taskID = generateID();
        this.name = name;
        this.status = status;
        this.dueDate = dueDate;
        this.urgency = 1; //urgency default 1
    }

    private String generateID() {
        return UUID.randomUUID().toString(); // Generate a random unique ID
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }
}
