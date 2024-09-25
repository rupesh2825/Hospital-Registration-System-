package Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DisplayAppointment {

    public static void displayAppointmentByPatientID(int patientId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT patients.name, appointments.doctor, appointments.appointment_date, appointments.appointment_time "
                    + "FROM patients "
                    + "JOIN appointments ON patients.id = appointments.patient_id "
                    + "WHERE patients.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String doctor = resultSet.getString("doctor");
                String date = resultSet.getString("appointment_date");
                String time = resultSet.getString("appointment_time");

                System.out.println("Appointment Details:");
                System.out.println("Patient Name: " + name);
                System.out.println("Doctor: " + doctor);
                System.out.println("Appointment Date: " + date);
                System.out.println("Appointment Time: " + time);
            } else {
                System.out.println("No appointment found for patient ID: " + patientId);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error retrieving appointment.");
        }
    }

    public static void displayAppointmentByPatientName(String patientName) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT patients.id, appointments.doctor, appointments.appointment_date, appointments.appointment_time "
                    + "FROM patients "
                    + "JOIN appointments ON patients.id = appointments.patient_id "
                    + "WHERE patients.name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patientName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int patientId = resultSet.getInt("id");
                String doctor = resultSet.getString("doctor");
                String date = resultSet.getString("appointment_date");
                String time = resultSet.getString("appointment_time");

                System.out.println("Appointment Details:");
                System.out.println("Patient ID: " + patientId);
                System.out.println("Doctor: " + doctor);
                System.out.println("Appointment Date: " + date);
                System.out.println("Appointment Time: " + time);
            } else {
                System.out.println("No appointment found for patient name: " + patientName);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error retrieving appointment.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search Appointment by:");
        System.out.println("1. Patient ID");
        System.out.println("2. Patient Name");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character after the integer input

        if (choice == 1) {
            System.out.print("Enter Patient ID: ");
            int patientId = scanner.nextInt();
            displayAppointmentByPatientID(patientId);
        } else if (choice == 2) {
            System.out.print("Enter Patient Name: ");
            String patientName = scanner.nextLine();
            displayAppointmentByPatientName(patientName);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
