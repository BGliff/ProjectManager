package com.example.projectmanager.dao;

import com.example.projectmanager.Task;
import com.example.projectmanager.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkerDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public WorkerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void setInfo(Worker worker){
        jdbcTemplate.update("INSERT INTO worker (name) VALUES (?)", worker.getName());
    }
    public Worker getInfo(int workerId){
        return jdbcTemplate.query("SELECT * FROM worker WHERE id = ?", new BeanPropertyRowMapper<>(Worker.class), workerId).stream().findAny().orElse(null);
    }
    public List<Worker> getAllInfo(){
        return jdbcTemplate.query("SELECT * FROM worker", new BeanPropertyRowMapper<>(Worker.class));
    }
    public void updateInfo(Worker worker){
        jdbcTemplate.update("UPDATE worker SET name = ? WHERE id = ?", worker.getName(), worker.getId());
    }
    public void deleteInfo(int workerId){
        jdbcTemplate.update("DELETE FROM worker WHERE id = ?", workerId);
    }
}
