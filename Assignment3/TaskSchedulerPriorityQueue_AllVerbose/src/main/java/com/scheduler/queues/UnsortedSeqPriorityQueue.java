package com.scheduler.queues;

import java.util.ArrayList;
import java.util.List;
import com.scheduler.Task;

public class UnsortedSeqPriorityQueue implements CustomPriorityQueue<Task> {
    private List<Task> list;

    public UnsortedSeqPriorityQueue() {
        this.list = new ArrayList<>();
    }

    public void insert(Task task, int priority) {
        list.add(task);
    }

    public Task removeMin() {
        if (list.isEmpty()) return null;
        Task minTask = list.get(0);
        int minIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getPriority() < minTask.getPriority()) {
                minTask = list.get(i);
                minIndex = i;
            }
        }
        return list.remove(minIndex);
    }

    public Task peekMin() {
        if (list.isEmpty()) return null;
        Task minTask = list.get(0);
        for (Task task : list) {
            if (task.getPriority() < minTask.getPriority()) {
                minTask = task;
            }
        }
        return minTask;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
