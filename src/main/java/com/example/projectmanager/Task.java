package com.example.projectmanager;

import java.sql.Date;


public class Task {
    private int taskId;
    private String taskDescription;
    private int taskCosts;
    private int workerId;
    private Date deadline;
    private double priority;
    private int daysCount = 0;

    public int getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(int daysCount) {
        this.daysCount = daysCount;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public Task(int taskId, String taskDescription, int taskCosts, int workerId) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.taskCosts = taskCosts;
        this.workerId = workerId;
    }
    public Task(int taskId, String taskDescription, int taskCosts) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.taskCosts = taskCosts;
        this.workerId = 1;
    }

    public Task(String taskDescription, int taskCosts, Date deadline) {
        this.taskDescription = taskDescription;
        this.taskCosts = taskCosts;
        this.workerId = 1;
        this.deadline = deadline;
    }

    public Task() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getTaskCosts() {
        return taskCosts;
    }

    public void setTaskCosts(int taskCosts) {
        this.taskCosts = taskCosts;
    }

    @Override
    public String toString() {
        return "Task{" + taskDescription + '}';
    }
//    public int getDeadlineDay() {
//        String[] parts = deadline.split("-");
//        return Integer.parseInt(parts[2]);
//    }
//    public int getDeadlineMonth() {
//        String[] parts = deadline.split("-");
//        return Integer.parseInt(parts[1]);
//    }
//    public int getDeadlineYear() {
//        String[] parts = deadline.split("-");
//        return Integer.parseInt(parts[0]);
//    }
//    public int getDay() {
//        return 365*getDeadlineYear()+30*getDeadlineMonth()+getDeadlineDay();
//    }
}

