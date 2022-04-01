package com.example.projectmanager;

import com.example.projectmanager.dao.ResultDAO;

import java.util.ArrayList;
import java.util.Date;
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
        List<Task> tasksSortedByDeadline = new ArrayList<>(taskList);
        int timeLine = (int)((taskList.get(taskList.size() - 1).getDeadline().getTime() - date.getTime()) / 86400000.0 + 1);
        List<Task> schedule = new ArrayList<>();
        for (int i = 0; i < timeLine; i++)
            schedule.add(new Task());
        int sumCosts = 0;
        for (Task task : taskList){
            sumCosts += task.getTaskCosts();
            schedule.set((int)((task.getDeadline().getTime() - date.getTime()) / 86400000), new Deadline());
        }
        double minTime = (double) timeLine / sumCosts;
        for (Task task : taskList)
            task.setDaysCount((int)(task.getTaskCosts()*minTime));

        int nextDeadlineTaskPointer = 0;
        int i = 0;
        int j = 0;
        while (i < schedule.size()){
            while (j < tasksSortedByDeadline.get(nextDeadlineTaskPointer).getTaskCosts()*minTime && !(schedule.get(i) instanceof Deadline)) {
                schedule.set(i, tasksSortedByDeadline.get(nextDeadlineTaskPointer));
                j++;
                i++;
            }
            taskList.remove(tasksSortedByDeadline.get(nextDeadlineTaskPointer));
            for (Task task : taskList)
                task.setPriority((double)task.getTaskCosts()/((double)(task.getDeadline().getTime()-date.getTime()) / 86400000 - i - 1));
            taskList.sort(new SortByPriority());
            if (taskList.size() < 1)
                break;
            if (schedule.get(i) instanceof Deadline){
                j = 0;
                nextDeadlineTaskPointer++;
                i++;
            }else {
                j = timeLine;
            }
            schedule.set(i, taskList.get(taskList.size()-1));
            taskList.get(taskList.size()-1).setDaysCount(taskList.get(taskList.size()-1).getDaysCount()-1);
            if (taskList.get(taskList.size()-1).getDaysCount() == 0)
                taskList.remove(taskList.size()-1);
            i++;
        }
        return schedule;
    }
}
