package com.scheduler;

import com.scheduler.queues.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SchedulerTest {

    @Test
    public void testHeapPriorityQueueOrdering() {
        System.out.println("\n--- Core Test: Heap Priority Queue Ordering ---");
        Scheduler scheduler = new Scheduler(new HeapPriorityQueue());

        scheduler.addTask("Low Priority", 5);
        scheduler.addTask("High Priority", 1);
        scheduler.addTask("Medium Priority", 3);

        System.out.println("Added: Low(5), High(1), Medium(3)");
        System.out.println("Expecting: High → Medium → Low");

        assertEquals("High Priority", scheduler.getNextTask().getName());
        assertEquals("Medium Priority", scheduler.getNextTask().getName());
        assertEquals("Low Priority", scheduler.getNextTask().getName());
    }

    @Test
    public void testSortedSequenceOrdering() {
        System.out.println("\n--- Core Test: Sorted Sequence Priority Queue ---");
        Scheduler scheduler = new Scheduler(new SortedSeqPriorityQueue());

        scheduler.addTask("A", 2);
        scheduler.addTask("B", 1);
        scheduler.addTask("C", 3);

        System.out.println("Added: A(2), B(1), C(3)");
        System.out.println("Expecting: B → A → C");

        assertEquals("B", scheduler.getNextTask().getName());
        assertEquals("A", scheduler.getNextTask().getName());
        assertEquals("C", scheduler.getNextTask().getName());
    }

    @Test
    public void testUnsortedSequenceOrdering() {
        System.out.println("\n--- Core Test: Unsorted Sequence Priority Queue ---");
        Scheduler scheduler = new Scheduler(new UnsortedSeqPriorityQueue());

        scheduler.addTask("Z", 9);
        scheduler.addTask("X", 2);
        scheduler.addTask("Y", 1);

        System.out.println("Added: Z(9), X(2), Y(1)");
        System.out.println("Expecting: Y → X → Z");

        assertEquals("Y", scheduler.getNextTask().getName());
        assertEquals("X", scheduler.getNextTask().getName());
        assertEquals("Z", scheduler.getNextTask().getName());
    }

    @Test
    public void testEmptyQueue() {
        System.out.println("\n--- Core Test: Empty Queue Check ---");
        Scheduler scheduler = new Scheduler(new HeapPriorityQueue());

        System.out.println("Checking if scheduler is empty...");
        assertFalse(scheduler.hasTasks());
    }

    @Test
    public void testPeekMinBehavior() {
        System.out.println("\n--- Core Test: Peek Minimum Behavior ---");
        HeapPriorityQueue queue = new HeapPriorityQueue();

        queue.insert(new Task("Ping", 2), 2);
        queue.insert(new Task("Pong", 1), 1);

        System.out.println("Inserted: Ping(2), Pong(1)");
        System.out.println("Peeking... expecting: Pong");

        assertEquals("Pong", queue.peekMin().getName());
    }
}
