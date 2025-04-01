package com.scheduler;

import com.scheduler.queues.CustomPriorityQueue;

public class Scheduler {
    private CustomPriorityQueue<Task> queue;

    public Scheduler(CustomPriorityQueue<Task> queue) {
        this.queue = queue;
    }

    public void addTask(String name, int priority) {
        Task task = new Task(name, priority);
        queue.insert(task, priority);
    }

    public Task getNextTask() {
        return queue.removeMin();
    }

    public boolean hasTasks() {
        return !queue.isEmpty();
    }
}
