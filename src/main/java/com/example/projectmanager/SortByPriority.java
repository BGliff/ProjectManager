package com.example.projectmanager;

import java.util.Comparator;

public class SortByPriority implements Comparator<Task> {
    public int compare(Task a, Task b) {
        return (int) (a.getPriority() - b.getPriority());
    }
}
