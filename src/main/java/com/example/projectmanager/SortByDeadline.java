package com.example.projectmanager;

import java.util.Comparator;

public class SortByDeadline implements Comparator<Task> {
    public int compare(Task a, Task b) {
        return a.getDeadline().compareTo(b.getDeadline());
    }
}
