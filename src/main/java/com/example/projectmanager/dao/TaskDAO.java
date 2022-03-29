package com.example.projectmanager.dao;

import com.example.projectmanager.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public TaskDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void setInfo(Task task){
        jdbcTemplate.update("INSERT INTO task (taskdescription, taskcosts, workerid, deadline) VALUES (?,?,?,?)",
                task.getTaskDescription(), task.getTaskCosts(), task.getWorkerId(), task.getDeadline());
    }
    public Task getInfo(int taskId){
        return jdbcTemplate.query("SELECT * FROM task WHERE taskid = ?", new BeanPropertyRowMapper<>(Task.class), taskId).stream().findAny().orElse(null);
    }
    public List<Task> getAllInfo() {
        return jdbcTemplate.query("SELECT * FROM task", new BeanPropertyRowMapper<>(Task.class));
    }
    public void updateInfo(Task task){
        jdbcTemplate.update("UPDATE task SET taskdescription = ?, taskcosts = ?, workerid = ?, deadline = ? WHERE taskid = ?",
                task.getTaskDescription(),task.getTaskCosts(),task.getWorkerId(), task.getDeadline(), task.getTaskId());
    }
    public void deleteInfo(int taskId){
        jdbcTemplate.update("DELETE FROM task WHERE taskid = ?", taskId);
    }
}
