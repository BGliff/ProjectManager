package com.example.projectmanager;

import com.example.projectmanager.dao.ResultDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Worker {
    private int load;
    private int id;
    private String name;

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public Worker(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Worker(String name) {
        this.name = name;
    }

    public Worker() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    public List<Task> createSchedule(Date date, ResultDAO resultDAO) {
        List<Task> taskList = resultDAO.getWorkerTasks(name);
        taskList.sort(new SortByDeadline());
        int timeLine = (int)(taskList.get(taskList.size() - 1).getDeadline().getTime() - date.getTime()) / 86400000 + 1;
        List<Task> schedule = new ArrayList<>();
        for (int i = 0; i < timeLine; i++)
            schedule.add(i, new Task());
        int sumCosts = 0;
        for (Task task : taskList){
            sumCosts += task.getTaskCosts();
            schedule.set((int)(task.getDeadline().getTime() - date.getTime()) / 86400000, new Deadline());
        }
        double minTime = (double) timeLine / sumCosts;
        int i = 0;
        while (i < schedule.size()){

            while (!(schedule.get(i) instanceof Deadline)){

            }
        }
//        for (int n = 0; n < taskList.size(); n++)
//            for (int k = n; k < taskList.size(); k++){
//                for (int j = 0; j < taskList.get(k).getTaskCosts()*minTime; j++){
//                    if (schedule.get(i) instanceof Deadline)
//                        break;
//                    schedule.set(i, taskList.get(k));
//                    i++;
//                }
//                if (schedule.get(i) instanceof Deadline)
//                    schedule.set(i, taskList.get(k));
//                    break;
//            }
        return schedule;
    }
}
