package com.ulugbek.taskmanager.model;

import com.ulugbek.taskmanager.model.datatypes.TaskStatus;

import java.util.Date;

public class Task {
    private String name;
    private TaskStatus status;
    private Date dueDate;
    private int urgency; //implement sort by urgency
    public Task(String name, TaskStatus status, Date dueDate) {
        this.name = name;
        this.status = status;
        this.dueDate = dueDate;
        this.urgency = 1; //urgency default 1
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
