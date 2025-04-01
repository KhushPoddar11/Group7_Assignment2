package com.scheduler;

import com.scheduler.queues.*;

public class Main {
    public static void main(String[] args) {
        CustomPriorityQueue<Task> heapQueue = new HeapPriorityQueue();
        CustomPriorityQueue<Task> sortedQueue = new SortedSeqPriorityQueue();
        CustomPriorityQueue<Task> unsortedQueue = new UnsortedSeqPriorityQueue();

        System.out.println("=== Heap Priority Queue ===");
        runDemo(new Scheduler(heapQueue));

        System.out.println("\n=== Sorted Sequence Priority Queue ===");
        runDemo(new Scheduler(sortedQueue));

        System.out.println("\n=== Unsorted Sequence Priority Queue ===");
        runDemo(new Scheduler(unsortedQueue));
    }

    private static void runDemo(Scheduler scheduler) {
        scheduler.addTask("Send Email", 3);
        scheduler.addTask("System Update", 1);
        scheduler.addTask("Backup Files", 2);

        while (scheduler.hasTasks()) {
            Task next = scheduler.getNextTask();
            System.out.println("Executing: " + next);
        }
    }
}
