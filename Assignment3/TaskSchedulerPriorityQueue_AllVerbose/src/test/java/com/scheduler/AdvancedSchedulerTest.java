package com.scheduler;

import com.scheduler.queues.*;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdvancedSchedulerTest {

    // --------------------- Performance Tests ---------------------

    @Test
    public void testPerformanceAllQueues() {
        System.out.println("\n--- Performance Test: HeapPriorityQueue ---");
        testPerformance(new HeapPriorityQueue(), 1000);
        testPerformance(new HeapPriorityQueue(), 10000);
    
        System.out.println("\n--- Performance Test: SortedSeqPriorityQueue ---");
        testPerformance(new SortedSeqPriorityQueue(), 1000);
        testPerformance(new SortedSeqPriorityQueue(), 10000);
    
        System.out.println("\n--- Performance Test: UnsortedSeqPriorityQueue ---");
        testPerformance(new UnsortedSeqPriorityQueue(), 1000);
        testPerformance(new UnsortedSeqPriorityQueue(), 10000);
    }
    

    private void testPerformance(CustomPriorityQueue<Task> queue, int count) {
        Instant startEnqueue = Instant.now();
        for (int i = 0; i < count; i++) {
            queue.insert(new Task("Task" + i, (int)(Math.random() * 100)), i);
        }
        Instant endEnqueue = Instant.now();
        System.out.println("Enqueue " + count + ": " + Duration.between(startEnqueue, endEnqueue).toMillis() + " ms");

        Instant startDequeue = Instant.now();
        while (!queue.isEmpty()) {
            queue.removeMin();
        }
        Instant endDequeue = Instant.now();
        System.out.println("Dequeue " + count + ": " + Duration.between(startDequeue, endDequeue).toMillis() + " ms");

        assertTrue(queue.isEmpty(), "Queue should be empty after dequeueing all tasks.");
    }

    // --------------------- Priority Tests ---------------------

    @Test
    public void testEmergencyVsRoutineTasks() {
        System.out.println("\n--- Priority Test: Emergency vs Routine ---");

        CustomPriorityQueue<Task> queue = new HeapPriorityQueue();
        queue.insert(new Task("Routine Check", 5), 5);
        queue.insert(new Task("Emergency Surgery", 1), 1);
        queue.insert(new Task("Medication", 3), 3);

        System.out.println("Inserted: Routine Check (5), Emergency Surgery (1), Medication (3)");
        System.out.println("Expected order: Emergency Surgery → Medication → Routine Check");

        assertEquals("Emergency Surgery", queue.removeMin().getName());
        assertEquals("Medication", queue.removeMin().getName());
        assertEquals("Routine Check", queue.removeMin().getName());
    }

    // --------------------- General Behavior Tests ---------------------

    @Test
    public void testPeekAndRemove() {
        System.out.println("\n--- General Behavior Test: Peek and Remove ---");

        CustomPriorityQueue<Task> queue = new SortedSeqPriorityQueue();
        queue.insert(new Task("A", 2), 2);
        queue.insert(new Task("B", 1), 1);

        System.out.println("Inserted: A (2), B (1)");
        System.out.println("Peek should return B, then remove B, then peek A");

        assertEquals("B", queue.peekMin().getName());
        assertEquals("B", queue.removeMin().getName());
        assertEquals("A", queue.peekMin().getName());
    }

    @Test
    public void testMixedInsertions() {
        System.out.println("\n--- General Behavior Test: Mixed Insertions ---");

        CustomPriorityQueue<Task> queue = new UnsortedSeqPriorityQueue();
        queue.insert(new Task("Low", 5), 5);
        queue.insert(new Task("High", 1), 1);
        queue.insert(new Task("Medium", 3), 3);

        System.out.println("Inserted tasks with priorities: Low(5), High(1), Medium(3)");
        System.out.println("Expected removal order: High → Medium → Low");

        assertEquals("High", queue.removeMin().getName());
        assertEquals("Medium", queue.removeMin().getName());
        assertEquals("Low", queue.removeMin().getName());
    }

    // --------------------- Edge Cases ---------------------

    @Test
    public void testEmptyQueueOperations() {
        System.out.println("\n--- Edge Case: Operations on Empty Queue ---");

        CustomPriorityQueue<Task> queue = new HeapPriorityQueue();

        System.out.println("Peeking and removing from an empty queue...");
        assertNull(queue.removeMin(), "Removing from empty queue should return null");
        assertNull(queue.peekMin(), "Peeking into empty queue should return null");
    }

    @Test
    public void testLargeQueueHandling() {
        System.out.println("\n--- Edge Case: Large Queue Handling ---");

        CustomPriorityQueue<Task> queue = new SortedSeqPriorityQueue();
        int size = 5000;

        System.out.println("Inserting " + size + " tasks...");

        for (int i = 0; i < size; i++) {
            queue.insert(new Task("T" + i, i), i);
        }

        System.out.println("Removing and checking task order...");

        for (int i = 0; i < size; i++) {
            Task task = queue.removeMin();
            if (!task.getName().equals("T" + i)) {
                System.out.println("Mismatch at index " + i + ": got " + task.getName());
            }
            assertEquals("T" + i, task.getName());
        }

        assertTrue(queue.isEmpty(), "Queue should be empty after all removals");
        System.out.println("Large queue handled successfully.");
    }

    @Test
    public void testSamePriorityTasks() {
        System.out.println("\n--- Edge Case: Same Priority Tasks ---");

        CustomPriorityQueue<Task> queue = new HeapPriorityQueue();
        queue.insert(new Task("T1", 1), 1);
        queue.insert(new Task("T2", 1), 1);
        queue.insert(new Task("T3", 1), 1);

        System.out.println("Inserted T1, T2, T3 all with priority 1");

        List<String> results = new ArrayList<>();
        results.add(queue.removeMin().getName());
        results.add(queue.removeMin().getName());
        results.add(queue.removeMin().getName());

        System.out.println("Dequeued order: " + results);

        assertTrue(results.containsAll(List.of("T1", "T2", "T3")), "All same-priority tasks should be dequeued.");
        assertTrue(queue.isEmpty(), "Queue should be empty after removing all tasks.");
    }
}
