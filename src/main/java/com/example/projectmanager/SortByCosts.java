package com.example.projectmanager;

import java.util.Comparator;

public class SortByCosts implements Comparator<Task> {
    public int compare(Task a, Task b){
            return b.getTaskCosts() - a.getTaskCosts();
        }
}
