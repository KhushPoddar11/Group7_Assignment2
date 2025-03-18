package Assignment2;

class Flight {
    int flightNumber;
    int landingTime;
    Flight left, right;

    public Flight(int flightNumber, int landingTime) {
        this.flightNumber = flightNumber;
        this.landingTime = landingTime;
        left = right = null;
    }
}

class RealLifeATC {
    private Flight root;

    public RealLifeATC() {
        root = null;
    }

    // Insert a flight into BST
    public void scheduleFlight(int flightNumber, int landingTime) {
        root = insertFlight(root, flightNumber, landingTime);
        System.out.println("Scheduled Flight " + flightNumber + " at " + landingTime + " minutes.");
    }

    private Flight insertFlight(Flight root, int flightNumber, int landingTime) {
        if (root == null) return new Flight(flightNumber, landingTime);
        if (landingTime < root.landingTime)
            root.left = insertFlight(root.left, flightNumber, landingTime);
        else
            root.right = insertFlight(root.right, flightNumber, landingTime);
        return root;
    }

    public void displaySchedule() {
        System.out.println("\nATC Landing Schedule:");
        inorderTraversal(root);
    }

    private void inorderTraversal(Flight root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println("Flight " + root.flightNumber + " landing at " + root.landingTime + " minutes");
            inorderTraversal(root.right);
        }
    }

    public boolean isFlightScheduled(int landingTime) {
        return searchFlight(root, landingTime);
    }

    private boolean searchFlight(Flight root, int landingTime) {
        if (root == null) return false;
        if (root.landingTime == landingTime) return true;
        return landingTime < root.landingTime ? searchFlight(root.left, landingTime) : searchFlight(root.right, landingTime);
    }

    public void cancelFlight(int flightNumber, int landingTime) {
        root = deleteFlight(root, flightNumber, landingTime);
        System.out.println("Flight " + flightNumber + " has been canceled.");
    }

    private Flight deleteFlight(Flight root, int flightNumber, int landingTime) {
        if (root == null) return null;
        if (landingTime < root.landingTime)
            root.left = deleteFlight(root.left, flightNumber, landingTime);
        else if (landingTime > root.landingTime)
            root.right = deleteFlight(root.right, flightNumber, landingTime);
        else { // Node to be deleted found
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            Flight successor = getMinValueNode(root.right);
            root.flightNumber = successor.flightNumber;
            root.landingTime = successor.landingTime;
            root.right = deleteFlight(root.right, successor.flightNumber, successor.landingTime);
        }
        return root;
    }

    private Flight getMinValueNode(Flight root) {
        while (root.left != null) root = root.left;
        return root;
    }

    public void emergencyLanding(int flightNumber) {
        root = insertFlight(root, flightNumber, Integer.MIN_VALUE);
        System.out.println("EMERGENCY: Flight " + flightNumber + " needs immediate landing!");
    }

    public void rescheduleFlight(int flightNumber, int oldLandingTime, int newLandingTime) {
        root = deleteFlight(root, flightNumber, oldLandingTime);
        root = insertFlight(root, flightNumber, newLandingTime);
        System.out.println("Flight " + flightNumber + " rescheduled to " + newLandingTime + " minutes.");
    }
}

public class ATC {
    public static void main(String[] args) {
        RealLifeATC atc = new RealLifeATC();

        atc.scheduleFlight(101, 500);
        atc.scheduleFlight(102, 450);
        atc.scheduleFlight(103, 530);
        atc.scheduleFlight(104, 400);
        atc.scheduleFlight(105, 600);

        System.out.println("\nInitial ATC Landing Schedule:");
        atc.displaySchedule();

        System.out.println("\nIs there a flight landing at 530 minutes? " + atc.isFlightScheduled(530));
        System.out.println("Is there a flight landing at 700 minutes? " + atc.isFlightScheduled(700));

        System.out.println("\nEmergency landing for Flight 999...");
        atc.emergencyLanding(999);
        atc.displaySchedule();

        System.out.println("\nRescheduling Flight 103 from 530 to 550...");
        atc.rescheduleFlight(103, 530, 550);
        atc.displaySchedule();

        System.out.println("\nCancelling Flight 102 (Landing at 450)...");
        atc.cancelFlight(102, 450);
        atc.displaySchedule();
    }
}
