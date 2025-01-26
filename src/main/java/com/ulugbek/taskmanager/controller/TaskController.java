package com.ulugbek.taskmanager.controller;

import com.ulugbek.taskmanager.dao.TaskDAO;
import com.ulugbek.taskmanager.model.Task;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class TaskController {
    private static TaskDAO taskDAO;

    public TaskController() {
        this.taskDAO = new TaskDAO();
    }

    public void addTask(Task newTask) {
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

    //returns boolean to know if ID deleted
    public void deleteTask(String taskID) {
        try{
           taskDAO.deleteTask(taskID);
            if (taskDAO.exists(taskID)) {
                System.out.print("Task with ID" + taskID + " successfully deleted" + "\n");
            } else {
                System.out.print("Task with ID " + taskID + " was not deleted" + "\n");
            }
        } catch (SQLException e) {
            System.out.print("SQL error deleting task with ID" + taskID + ". " + e.getMessage() + "\n");
        }
    }

    public boolean exists(String taskID) {
        try{
            return taskDAO.exists(taskID);
        } catch (SQLException e) {
            System.out.print("SQL error checking existance of task with ID" + taskID + ". " + e.getMessage() + "\n");
            return false;
        }
    }

    public void updateTask(Task task) {
        try{
            taskDAO.updateTask(task);
            System.out.print("Updated task " + task.getName() + "\n");
        } catch (SQLException e) {
            System.out.print("Error updating task " + task.getName() + " " + e.getMessage() + "\n");
        }

    }

    public Task selectTask(String taskID) {
        try{
            return taskDAO.selectTask(taskID);
        } catch (SQLException e) {
            System.out.print("Error selecting task with ID " + taskID+ " " + e.getMessage() + "\n");
            return null;
        }
    }
}
