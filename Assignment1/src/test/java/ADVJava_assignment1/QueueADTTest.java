package ADVJava_assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.time.Instant;

public class QueueADTTest {

    // --------------------- Experiment 1: Performance Tests ---------------------

    @Test
    public void testEnqueueDequeuePerformance() {
        System.out.println("\n--- Performance Test ---");
        testPerformance(100);
        testPerformance(1000);
        testPerformance(10000);
    }

    private void testPerformance(int patientCount) {
        QueueADT.Queue queue = new QueueADT.Queue();

        // Measure Enqueue Time
        Instant startEnqueue = Instant.now();
        for (int i = 0; i < patientCount; i++) {
            queue.enqueue(i);
        }
        Instant endEnqueue = Instant.now();
        long enqueueTime = Duration.between(startEnqueue, endEnqueue).toMillis();
        System.out.println("Enqueue Time for " + patientCount + " patients: " + enqueueTime + " ms");

        // Measure Dequeue Time
        Instant startDequeue = Instant.now();
        for (int i = 0; i < patientCount; i++) {
            queue.dequeue();
        }
        Instant endDequeue = Instant.now();
        long dequeueTime = Duration.between(startDequeue, endDequeue).toMillis();
        System.out.println("Dequeue Time for " + patientCount + " patients: " + dequeueTime + " ms");

        assertTrue(queue.isEmpty(), "Queue should be empty after all dequeues");
    }

    // --------------------- Experiment 2: Priority Test ---------------------

    @Test
    public void testEmergencyVsRegularProcessing() {
        System.out.println("\n--- Priority Test (Emergency vs Regular) ---");

        QueueADT.Queue regularQueue = new QueueADT.Queue();
        QueueADT.Queue emergencyQueue = new QueueADT.Queue();

        // Enqueue regular patients
        regularQueue.enqueue(101);
        regularQueue.enqueue(102);

        // Enqueue emergency patients
        emergencyQueue.enqueue(201);
        emergencyQueue.enqueue(202);

        // Emergency patients should be dequeued first
        assertEquals(201, emergencyQueue.dequeue(), "First dequeued should be emergency patient 201");
        assertEquals(202, emergencyQueue.dequeue(), "Second dequeued should be emergency patient 202");

        // Regular patients should be processed next
        assertEquals(101, regularQueue.dequeue(), "Next should be regular patient 101");
        assertEquals(102, regularQueue.dequeue(), "Last should be regular patient 102");

        // Ensure both queues are empty
        assertTrue(emergencyQueue.isEmpty(), "Emergency queue should be empty");
        assertTrue(regularQueue.isEmpty(), "Regular queue should be empty");
    }

    // --------------------- General Tests ---------------------

    @Test
    public void testBasicQueueOperations() {
        QueueADT.Queue queue = new QueueADT.Queue();
        System.out.println("\n--- Basic Queue Operations Test ---");

        // Enqueue elements
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // Peek
        assertEquals(10, queue.peek(), "Peek should return the first element (10)");

        // Dequeue
        assertEquals(10, queue.dequeue(), "Dequeue should return 10");
        assertEquals(20, queue.dequeue(), "Next dequeue should return 20");

        // Size check
        assertEquals(1, queue.size(), "Size should be 1 after two dequeues");

        // Final dequeue
        assertEquals(30, queue.dequeue(), "Final dequeue should return 30");
        assertTrue(queue.isEmpty(), "Queue should be empty now");
    }

    // --------------------- Edge Cases ---------------------

    @Test
    public void testEmptyQueueDequeue() {
        QueueADT.Queue queue = new QueueADT.Queue();
        System.out.println("\n--- Edge Case: Dequeue from Empty Queue ---");

        assertEquals(-1, queue.dequeue(), "Dequeue from empty queue should return -1");
        assertEquals(-1, queue.peek(), "Peek from empty queue should return -1");
    }

    @Test
    public void testLargeQueue() {
        QueueADT.Queue queue = new QueueADT.Queue();
        System.out.println("\n--- Edge Case: Large Queue ---");

        int largeSize = 100000;
        for (int i = 0; i < largeSize; i++) {
            queue.enqueue(i);
        }
        assertEquals(largeSize, queue.size(), "Queue size should be 100,000");

        for (int i = 0; i < largeSize; i++) {
            assertEquals(i, queue.dequeue(), "Dequeued value should match the enqueued value");
        }

        assertTrue(queue.isEmpty(), "Queue should be empty after processing all elements");
    }

    @Test
    public void testNullHandling() {
        QueueADT.Queue queue = new QueueADT.Queue();
        System.out.println("\n--- Edge Case: Null Handling ---");

        queue.enqueue(0);
        assertEquals(0, queue.peek(), "Queue should accept 0 as a valid value");

        queue.dequeue();
        assertTrue(queue.isEmpty(), "Queue should be empty after dequeuing 0");
    }

    @Test
    public void testQueueSize() {
        QueueADT.Queue queue = new QueueADT.Queue();
        System.out.println("\n--- Edge Case: Queue Size ---");

        assertEquals(0, queue.size(), "Queue size should be 0 initially");

        queue.enqueue(100);
        queue.enqueue(200);
        assertEquals(2, queue.size(), "Queue size should be 2 after enqueue");

        queue.dequeue();
        assertEquals(1, queue.size(), "Queue size should be 1 after one dequeue");

        queue.dequeue();
        assertEquals(0, queue.size(), "Queue size should be 0 after all dequeues");
    }
}
