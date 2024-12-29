package com.ulugbek.taskmanager.test;

import com.ulugbek.taskmanager.controller.TaskController;
import com.ulugbek.taskmanager.model.datatypes.TaskStatus;

import java.util.Date;

public class TaskControllerTest {
    public static void main(String[] args) {
        TaskController taskController = new TaskController();

        // Create and add tasks
        taskController.addTask("Task 1", TaskStatus.PENDING, new Date());
        taskController.addTask("Task 2", TaskStatus.COMPLETED, new Date());

        // Fetch and display tasks
        taskController.getAllTasks().forEach(task -> {
            System.out.println("Task Name: " + task.getName());
            System.out.println("Task Status: " + task.getStatus());
            System.out.println("Due Date: " + task.getFormattedDueDate());
        });
    }
}
