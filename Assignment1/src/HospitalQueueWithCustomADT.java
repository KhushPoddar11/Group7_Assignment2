import java.util.Scanner;

public class HospitalQueueWithCustomADT {

    static class Patient {
        private static int idCounter = 1;
        private int patientId;
        private String name;
        private String condition;
        private boolean isEmergency;

        public Patient(String name, String condition, boolean isEmergency) {
            this.patientId = idCounter++;
            this.name = name;
            this.condition = condition;
            this.isEmergency = isEmergency;
        }

        public int getPatientId() {
            return patientId;
        }

        public String getName() {
            return name;
        }

        public String getCondition() {
            return condition;
        }

        public boolean isEmergency() {
            return isEmergency;
        }

        @Override
        public String toString() {
            return "Patient ID: " + patientId + ", Name: " + name + ", Condition: " + condition + (isEmergency ? " [EMERGENCY]" : "");
        }
    }

    public static void main(String[] args) {
        QueueADT.Queue regularQueue = new QueueADT.Queue();
        QueueADT.Queue emergencyQueue = new QueueADT.Queue();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n=== Hospital Patient Management System ===");
            System.out.println("1. Register New Patient");
            System.out.println("2. View Next Patient");
            System.out.println("3. Attend Next Patient");
            System.out.println("4. View All Waiting Patients");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter patient name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter condition: ");
                    String condition = scanner.nextLine();
                    System.out.print("Is this an emergency case? (yes/no): ");
                    boolean isEmergency = scanner.nextLine().equalsIgnoreCase("yes");

                    Patient newPatient = new Patient(name, condition, isEmergency);
                    if (isEmergency) {
                        emergencyQueue.enqueue(newPatient.getPatientId());
                    } else {
                        regularQueue.enqueue(newPatient.getPatientId());
                    }
                    System.out.println("Patient registered successfully! Patient ID: " + newPatient.getPatientId());
                    break;

                case 2:
                    if (!emergencyQueue.isEmpty()) {
                        System.out.println("Next patient (Emergency): " + emergencyQueue.peek());
                    } else if (!regularQueue.isEmpty()) {
                        System.out.println("Next patient (Regular): " + regularQueue.peek());
                    } else {
                        System.out.println("No patients are waiting.");
                    }
                    break;

                case 3:
                    if (!emergencyQueue.isEmpty()) {
                        System.out.println("Attending emergency patient ID: " + emergencyQueue.dequeue());
                    } else if (!regularQueue.isEmpty()) {
                        System.out.println("Attending regular patient ID: " + regularQueue.dequeue());
                    } else {
                        System.out.println("No patients to attend.");
                    }
                    break;

                case 4:
                    System.out.println("\nEmergency Queue:");
                    emergencyQueue.display();
                    System.out.println("\nRegular Queue:");
                    regularQueue.display();
                    break;

                case 5:
                    System.out.println("Exiting the system. Stay healthy!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
