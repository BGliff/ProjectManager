package com.example.projectmanager.dao;

import com.example.projectmanager.Result;
import com.example.projectmanager.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public ResultDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void setInfo(Result result){
        jdbcTemplate.update("INSERT INTO result (name, taskdescription, taskcosts, deadline) VALUES (?,?,?,?)",
                result.getName(), result.getTaskDescription(), result.getTaskCosts(), result.getDeadline());
    }
    public void deleteAllInfo(){
        jdbcTemplate.update("DELETE FROM result");
    }
    public List<Task> getWorkerTasks (String name) {
        return jdbcTemplate.query("SELECT taskdescription, taskcosts, deadline FROM result WHERE name = ?", new BeanPropertyRowMapper<>(Task.class), name);
    }
}
