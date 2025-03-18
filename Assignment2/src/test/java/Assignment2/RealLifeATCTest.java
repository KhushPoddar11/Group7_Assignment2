package Assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RealLifeATCTest {

    // Test for scheduling a flight
    @Test
    public void testScheduleFlight() {
        RealLifeATC atc = new RealLifeATC();
        atc.scheduleFlight(101, 500);
        assertTrue(atc.isFlightScheduled(500));
    }

    // Test for emergency landing
    @Test
    public void testEmergencyLanding() {
        RealLifeATC atc = new RealLifeATC();
        atc.scheduleFlight(102, 450);
        atc.emergencyLanding(999);
        // Check that emergency landing has inserted the flight with special landing time
        assertTrue(atc.isFlightScheduled(Integer.MIN_VALUE));
    }

    // Test for rescheduling a flight
    @Test
    public void testRescheduleFlight() {
        RealLifeATC atc = new RealLifeATC();
        atc.scheduleFlight(103, 530);
        atc.rescheduleFlight(103, 530, 550);
        assertTrue(atc.isFlightScheduled(550));
        assertFalse(atc.isFlightScheduled(530));
    }

    // Test for canceling a flight
    @Test
    public void testCancelFlight() {
        RealLifeATC atc = new RealLifeATC();
        atc.scheduleFlight(104, 400);
        atc.cancelFlight(104, 400);
        assertFalse(atc.isFlightScheduled(400));
    }

    // Test for emergency flight priority
    @Test
    public void testEmergencyFlightPriority() {
        RealLifeATC atc = new RealLifeATC();
        // Schedule regular flights
        atc.scheduleFlight(101, 500);
        atc.scheduleFlight(102, 600);
        atc.scheduleFlight(103, 700);
        // Schedule emergency flights
        atc.emergencyLanding(201); // Emergency flight
        atc.emergencyLanding(202); // Emergency flight

        // Display the schedule and verify emergency flights appear first
        atc.displaySchedule();
        assertTrue(atc.isFlightScheduled(Integer.MIN_VALUE));  // Emergency flight
    }

    // Test for scheduling a flight with a duplicate landing time
    @Test
    public void testDuplicateLandingTime() {
        RealLifeATC atc = new RealLifeATC();
        atc.scheduleFlight(101, 500);
        atc.scheduleFlight(102, 500); // Same landing time
        assertTrue(atc.isFlightScheduled(500));
    }

    // Test for rescheduling a flight to an existing landing time
    @Test
    public void testRescheduleToExistingTime() {
        RealLifeATC atc = new RealLifeATC();
        atc.scheduleFlight(103, 500);
        atc.scheduleFlight(104, 600);
        atc.rescheduleFlight(103, 500, 600);
        assertTrue(atc.isFlightScheduled(600));
    }

    // Test for canceling a non-existing flight
    @Test
    public void testCancelNonExistingFlight() {
        RealLifeATC atc = new RealLifeATC();
        atc.scheduleFlight(101, 500);
        atc.cancelFlight(102, 600); // Non-existing flight
        assertTrue(atc.isFlightScheduled(500)); // Flight 101 should still exist
    }

    // Test for scheduling a flight with the maximum integer landing time
    @Test
    public void testMaxIntegerLandingTime() {
        RealLifeATC atc = new RealLifeATC();
        atc.scheduleFlight(105, Integer.MAX_VALUE); // Max landing time
        assertTrue(atc.isFlightScheduled(Integer.MAX_VALUE));
    }

    // Performance test for scheduling and canceling flights at different scales
    @Test
    public void testPerformance() {
        RealLifeATC atc = new RealLifeATC();

        // Performance Test for 100 flights
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            atc.scheduleFlight(i, 100 + i);
        }
        long endTime = System.currentTimeMillis();
        long scheduleTimeFor100 = endTime - startTime;

        // Performance Test for 1000 flights
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            atc.scheduleFlight(i, 100 + i);
        }
        endTime = System.currentTimeMillis();
        long scheduleTimeFor1000 = endTime - startTime;

        // Performance Test for 10000 flights
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= 10000; i++) {
            atc.scheduleFlight(i, 100 + i);
        }
        endTime = System.currentTimeMillis();
        long scheduleTimeFor10000 = endTime - startTime;

        // Performance Test for 100 flights cancellation
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            atc.cancelFlight(i, 100 + i);
        }
        endTime = System.currentTimeMillis();
        long cancelTimeFor100 = endTime - startTime;

        // Performance Test for 1000 flights cancellation
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            atc.cancelFlight(i, 100 + i);
        }
        endTime = System.currentTimeMillis();
        long cancelTimeFor1000 = endTime - startTime;

        // Performance Test for 10000 flights cancellation
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= 10000; i++) {
            atc.cancelFlight(i, 100 + i);
        }
        endTime = System.currentTimeMillis();
        long cancelTimeFor10000 = endTime - startTime;

        // Print results
        System.out.println("Performance Test");
        System.out.println("Scheduling Time for 100 flights: " + scheduleTimeFor100 + " ms");
        System.out.println("Cancellation Time for 100 flights: " + cancelTimeFor100 + " ms");
        System.out.println("Scheduling Time for 1000 flights: " + scheduleTimeFor1000 + " ms");
        System.out.println("Cancellation Time for 1000 flights: " + cancelTimeFor1000 + " ms");
        System.out.println("Scheduling Time for 10000 flights: " + scheduleTimeFor10000 + " ms");
        System.out.println("Cancellation Time for 10000 flights: " + cancelTimeFor10000 + " ms");
    }
}
