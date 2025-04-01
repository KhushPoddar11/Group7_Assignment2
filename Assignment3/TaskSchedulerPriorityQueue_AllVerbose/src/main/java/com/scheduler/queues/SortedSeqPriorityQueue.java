package com.scheduler.queues;

import java.util.LinkedList;
import java.util.List;
import com.scheduler.Task;

public class SortedSeqPriorityQueue implements CustomPriorityQueue<Task> {
    private List<Task> list;

    public SortedSeqPriorityQueue() {
        this.list = new LinkedList<>();
    }

    public void insert(Task task, int priority) {
        int index = 0;
        while (index < list.size() && list.get(index).getPriority() <= priority) {
            index++;
        }
        list.add(index, task);
    }

    public Task removeMin() {
        return list.isEmpty() ? null : list.remove(0);
    }

    public Task peekMin() {
        return list.isEmpty() ? null : list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
