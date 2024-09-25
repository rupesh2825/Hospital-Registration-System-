package Hospital;

import java.util.Scanner;

public class Menu {

    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Hospital Registration System");
            System.out.println("1. Register Patient");
            System.out.println("2. Display Patient Appointment");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    PatientRegistration.registerPatient();
                    break;
                case 2:
                    DisplayAppointment.main(new String[]{});
                    break;
                case 3:
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }

            System.out.println();  // Add a blank line between operations for better readability
        } while (choice != 3);

        scanner.close();
    }

    public static void main(String[] args) {
        displayMenu();
    }
}
