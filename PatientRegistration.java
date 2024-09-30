package Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class PatientRegistration {

    // Method to register a patient using parameters from the GUI
    public static void registerPatient(String name, String address, String contact, String medicalHistory) {
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
            LocalTime appointmentTime = assignRandomAppointmentTime();  // Assign random appointment time

            String sql = "INSERT INTO appointments (patient_id, doctor, appointment_date, appointment_time) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientId);
            statement.setString(2, doctor);
            statement.setDate(3, java.sql.Date.valueOf(appointmentDate));
            statement.setTime(4, java.sql.Time.valueOf(appointmentTime));
            statement.executeUpdate();
            
            System.out.println("Appointment scheduled successfully for " + appointmentTime);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error scheduling appointment.");
        }
    }

    // A simple method to assign a doctor (can be randomized or based on availability)
    private static String assignDoctor() {
        String[] doctors = {"Dr. Smith", "Dr. Khimraj Parate", "Dr. Rupesh Chavan", "Dr. Shivam Bari", "Dr. Nikhil Joshi"};
        Random random = new Random();
        return doctors[random.nextInt(doctors.length)];  // Randomly assign a doctor
    }

    // Method to assign a random appointment time between 9 AM and 5 PM
    private static LocalTime assignRandomAppointmentTime() {
        Random random = new Random();
        
        // Generate a random hour between 9 AM (9) and 5 PM (17)
        int hour = random.nextInt(9) + 9; // Generates 9-17 (inclusive)
        
        // Generate a random minute (0-59)
        int minute = random.nextInt(60); // Generates 0-59
        
        return LocalTime.of(hour, minute);  // Create LocalTime with random hour and minute
    }
}
