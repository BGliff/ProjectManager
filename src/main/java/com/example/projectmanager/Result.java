package com.example.projectmanager;

import java.sql.Date;

public class Result {
    private String name;
    private String taskDescription;
    private int taskCosts;
    private Date deadline;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Result() {
    }

    public Result(String name, String taskDescription, int taskCosts) {
        this.name = name;
        this.taskDescription = taskDescription;
        this.taskCosts = taskCosts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
