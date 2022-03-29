package com.example.projectmanager;

import java.util.Comparator;

public class SortByLoad implements Comparator<Worker> {
    public int compare(Worker a, Worker b){
        return a.getLoad() - b.getLoad();
    }
}
