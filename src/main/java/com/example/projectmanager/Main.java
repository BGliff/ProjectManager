package com.example.projectmanager;

import com.example.projectmanager.config.SpringConfig;
import com.example.projectmanager.dao.ResultDAO;
import com.example.projectmanager.dao.TaskDAO;
import com.example.projectmanager.dao.WorkerDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.*;

public class Main {
    static void addTask(TaskDAO taskDAO){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i=0;i<n;i++){
            System.out.print("Task description: ");
            String taskDescription = scanner.next();
            System.out.print("Task costs: ");
            int taskCosts = scanner.nextInt();
            String stringDate = scanner.next();
            Date date = Date.valueOf(stringDate);
            Task task = new Task(taskDescription, taskCosts, date);
            taskDAO.setInfo(task);
        }

    }
    static void addWorker(WorkerDAO workerDAO){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i=0;i<n;i++){
            System.out.print("Worker name: ");
            String name = scanner.next();
            Worker worker = new Worker(name);
            workerDAO.setInfo(worker);
        }
    }
    static void organiser(WorkerDAO workerDAO, TaskDAO taskDAO){
        List<Worker> workerList = (ArrayList) workerDAO.getAllInfo();
        List<Task> taskList = (ArrayList) taskDAO.getAllInfo();
        Collections.sort(taskList, new SortByCosts());
        for (Task task : taskList) {
            Collections.sort(workerList, new SortByLoad());
            task.setWorkerId(workerList.get(0).getId());
            workerList.get(0).setLoad(workerList.get(0).getLoad()+task.getTaskCosts());
            taskDAO.updateInfo(task);
        }
        for (Worker worker : workerList){
            System.out.println(worker.getName() + ' ' + worker.getLoad());
        }
    }
    static void doResult(ResultDAO resultDAO, WorkerDAO workerDAO, TaskDAO taskDAO){
        resultDAO.deleteAllInfo();
        List<Task> taskList = taskDAO.getAllInfo();
        for (Task task : taskList){
            Result result = new Result();
            result.setTaskDescription(task.getTaskDescription());
            result.setTaskCosts(task.getTaskCosts());
            result.setDeadline(task.getDeadline());
            result.setName(workerDAO.getInfo(task.getWorkerId()).getName());
            resultDAO.setInfo(result);
        }
        java.util.Date date = new java.util.Date();
        Date sqlDate = new Date(date.getTime());
        List<Task> schedule = workerDAO.getInfo(1).createSchedule(sqlDate, resultDAO);
        for (int i = 1; i <= schedule.size(); i++) {
            System.out.print(i);
            System.out.print(' ');
            System.out.println(schedule.get(i - 1));
        }
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TaskDAO taskDAO = context.getBean(TaskDAO.class);
        WorkerDAO workerDAO = context.getBean(WorkerDAO.class);
        ResultDAO resultDAO = context.getBean(ResultDAO.class);
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning){
            System.out.println("0 - close application, 1 - add new workers, 2 - add new tasks, 3 - distribution, 4 - update result table");
            int command = scanner.nextInt();
            switch (command){
                case (0):
                    isRunning = false;
                    break;
                case (1):
                    addWorker(workerDAO);
                    break;
                case (2):
                    addTask(taskDAO);
                    break;
                case (3):
                    organiser(workerDAO, taskDAO);
                    break;
                case (4):
                    doResult(resultDAO, workerDAO, taskDAO);
                    break;
                case (5):
                    System.out.println(new Date(new java.util.Date().getTime()).getTime());
            }
        }
    }
}
