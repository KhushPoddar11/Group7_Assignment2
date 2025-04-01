package com.scheduler.queues;

public interface CustomPriorityQueue<T> {
    void insert(T element, int priority);
    T removeMin();
    T peekMin();
    boolean isEmpty();
}
