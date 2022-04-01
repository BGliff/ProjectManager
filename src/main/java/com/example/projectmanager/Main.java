package com.example.projectmanager;

import com.example.projectmanager.config.SpringConfig;
import com.example.projectmanager.dao.ResultDAO;
import com.example.projectmanager.dao.TaskDAO;
import com.example.projectmanager.dao.WorkerDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.*;

public class Main {
    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private static final TaskDAO taskDAO = context.getBean(TaskDAO.class);
    private static final WorkerDAO workerDAO = context.getBean(WorkerDAO.class);
    private static final ResultDAO resultDAO = context.getBean(ResultDAO.class);

    static void addTask(String taskDescription, int taskCosts, Date date){
        Task task = new Task(taskDescription, taskCosts, date);
        taskDAO.setInfo(task);
    }
    static void addWorker(String name){
        Worker worker = new Worker(name);
        workerDAO.setInfo(worker);
    }
    static void organiser(){
        List<Worker> workerList = workerDAO.getAllInfo();
        List<Task> taskList = taskDAO.getAllInfo();
        taskList.sort(new SortByCosts());
        for (Task task : taskList) {
            workerList.sort(new SortByLoad());
            task.setWorkerId(workerList.get(0).getId());
            workerList.get(0).setLoad(workerList.get(0).getLoad()+task.getTaskCosts());
            taskDAO.updateInfo(task);
        }
    }
    static void doResult(){
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
    }
    static void makeSchedule(int id, java.util.Date date){

        List<Task> schedule = workerDAO.getInfo(id).createSchedule(date, resultDAO);
        for (int i = 0; i < schedule.size(); i++) {
            System.out.print(new Date((long)i*86400000 + date.getTime()));
            System.out.print(' ');
            System.out.println(schedule.get(i));
        }
    }
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning){
            System.out.println("0 - close application, 1 - add new worker, 2 - add new task, 3 - distribution, " +
                    "4 - update result table, 5 - make a schedule for worker");
            int command = scanner.nextInt();
            switch (command){
                case (0):
                    isRunning = false;
                    break;
                case (1):
                    System.out.print("Worker name: ");
                    String name = scanner.next();
                    addWorker(name);
                    break;
                case (2):
                    System.out.print("Task description: ");
                    String taskDescription = scanner.next();
                    System.out.print("Task costs: ");
                    int taskCosts = scanner.nextInt();
                    System.out.print("Enter task's deadline like 'yyyy-mm-dd': ");
                    String stringDate = scanner.next();
                    Date date = Date.valueOf(stringDate);
                    addTask(taskDescription, taskCosts, date);
                    break;
                case (3):
                    organiser();
                    break;
                case (4):
                    doResult();
                    break;
                case (5):
                    System.out.print("Enter worker id: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter today's date like 'yyyy-mm-dd': ");
                    stringDate = scanner.next();
                    date = Date.valueOf(stringDate);
                    makeSchedule(id, date);
                    break;
            }
        }
    }
}
