package Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class PatientRegistration {

    public static void registerPatient() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter patient name:");
        String name = scanner.nextLine();

        System.out.println("Enter address:");
        String address = scanner.nextLine();

        System.out.println("Enter contact:");
        String contact = scanner.nextLine();

        System.out.println("Enter medical history:");
        String medicalHistory = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Register the patient
            String sql = "INSERT INTO patients (name, address, contact, medical_history) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, contact);
            statement.setString(4, medicalHistory);
            statement.executeUpdate();

            // Get the patient ID (auto-generated primary key)
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int patientId = generatedKeys.getInt(1);

                // Display patient ID
                System.out.println("Patient registered successfully with Patient ID: " + patientId);

                // Automatically schedule an appointment
                scheduleAppointmentAutomatically(patientId);
            }

            System.out.println("Appointment scheduled successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error registering patient or scheduling appointment.");
        }
    }

    // Automatically schedule an appointment after registering the patient
    private static void scheduleAppointmentAutomatically(int patientId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String doctor = assignDoctor();  // Automatically assign a doctor
            LocalDate appointmentDate = LocalDate.now().plusDays(1);  // Appointment date: 1 day after registration
            LocalTime appointmentTime = LocalTime.of(10, 30);  // Default appointment time: 10:30 AM

            String sql = "INSERT INTO appointments (patient_id, doctor, appointment_date, appointment_time) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientId);
            statement.setString(2, doctor);
            statement.setDate(3, java.sql.Date.valueOf(appointmentDate));
            statement.setTime(4, java.sql.Time.valueOf(appointmentTime));
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error scheduling appointment.");
        }
    }

    // A simple method to assign a doctor (can be randomized or based on availability)
    private static String assignDoctor() {
        String[] doctors = {"Dr. Smith", "Dr. Chavan", "Dr. Bhari", "Dr. Joshi", " Dr. Khimraj Parate"};
        Random random = new Random();
        return doctors[random.nextInt(doctors.length)];  // Randomly assign a doctor
    }
}
