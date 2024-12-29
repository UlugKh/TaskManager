package com.ulugbek.taskmanager.controller;

import com.ulugbek.taskmanager.dao.TaskDAO;
import com.ulugbek.taskmanager.model.Task;
import com.ulugbek.taskmanager.model.datatypes.TaskStatus;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TaskController {
    private final TaskDAO taskDAO;

    public TaskController() {
        this.taskDAO = new TaskDAO();
    }

    public void addTask(String name, TaskStatus status, Date dueDate) {
        Task newTask = new Task(name, status, dueDate);
        try{
            taskDAO.addTask(newTask);
            System.out.print("Task added to database: " + newTask.getName() + "\n");
        } catch (SQLException e) {
            System.out.print("Error adding task " + e.getMessage() + "\n");
        }
    }

    public List<Task> getAllTasks() {
        try{
            return taskDAO.getAllTasks();
        } catch (SQLException e) {
            System.out.print("Error retrieving all tasks " + e.getMessage()+ "\n");
            return null;
        }
    }

    public void deleteTask(String taskID) {
        try{
            taskDAO.deleteTask(taskID);
            System.out.print("Task with ID" + taskID + " successfully deleted" + "\n");
        } catch (SQLException e) {
            System.out.print("Error deleting task with ID" + taskID + ". " + e.getMessage() + "\n");
        }
    }

    public void updateTask(Task task) {
        try{
            taskDAO.updateTask(task);
            System.out.print("Update task " + task.getName() + "\n");
        } catch (SQLException e) {
            System.out.print("Error updating task " + task.getName() + " " + e.getMessage() + "\n");
        }

    }
}
