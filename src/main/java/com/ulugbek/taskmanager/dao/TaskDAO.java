package com.ulugbek.taskmanager.dao;

import com.ulugbek.taskmanager.model.Task;
import com.ulugbek.taskmanager.model.datatypes.TaskStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ulugbek.taskmanager.util.DatabaseConnection.getConnection;
import static com.ulugbek.taskmanager.util.DateUtil.formatDate;
import static com.ulugbek.taskmanager.util.DateUtil.toDate;
import static com.ulugbek.taskmanager.util.StatusUtil.toStatusEnum;

public class TaskDAO {
    //data access object to interact with data in database
    private static final String INSERT_TASK = "INSERT INTO Tasks (ID, Name, Urgency, [Due Date], Status) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_TASKS = "SELECT * FROM Tasks";
    private static final String DELETE_TASK = "DELETE FROM Tasks WHERE ID = ?";
    private static final String UPDATE_TASK = "UPDATE Tasks SET Name = ?, Urgency = ?, [Due Date] = ?, Status = ? WHERE ID = ?";

    public void addTask(Task task) throws SQLException {
        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(INSERT_TASK)) {
            stm.setString(1, task.getTaskID());
            stm.setString(2, task.getName());
            stm.setInt(3, task.getUrgency());
            stm.setString(4, task.getFormattedDueDate());
            stm.setString(5, task.getStatus());
            stm.executeUpdate();
        }
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(SELECT_ALL_TASKS))
        {
            while(rs.next()) {
                String name = rs.getString("Name");
                String id = rs.getString("ID");
                String tempduedate = rs.getString("Due Date");
                String tempstatus = rs.getString("Status");

                Date duedate = toDate(tempduedate);
                TaskStatus status = toStatusEnum(tempstatus);

                Task tempTask = new Task(name, status, duedate, id);
                tempTask.setUrgency(rs.getInt("Urgency"));

                tasks.add(tempTask);
            }
        }
        return tasks;
    }

    public void deleteTask(String TaskID) throws SQLException {
        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(DELETE_TASK))
        {
            stm.setString(1, TaskID);
            stm.executeUpdate();
        }
    }

    public void updateTask(Task task) throws SQLException{
        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(UPDATE_TASK))
        {
            stm.setString(1, task.getName());
            stm.setInt(2, task.getUrgency());
            stm.setString(3, task.getFormattedDueDate());
            stm.setString(4, task.getStatus());
            stm.setString(5, task.getTaskID());
            stm.executeUpdate();
        }
    }
}
