package Hospital;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayAppointment {

    public static void displayAppointmentByPatientID(int patientId) {
        try (Connection connection = DatabaseConnection.getConnection()) {  // Establish connection
            String sql = "SELECT patients.name, appointments.doctor, appointments.appointment_date, appointments.appointment_time "
                    + "FROM patients "
                    + "JOIN appointments ON patients.id = appointments.patient_id "
                    + "WHERE patients.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);  // Prepare SQL query
            statement.setInt(1, patientId);  // Set the patient ID in the query
            ResultSet resultSet = statement.executeQuery();  // Execute query

            if (resultSet.next()) {  // Check if a result is found
                String name = resultSet.getString("name");
                String doctor = resultSet.getString("doctor");
                String date = resultSet.getString("appointment_date");
                String time = resultSet.getString("appointment_time");

                // Create a message to display the appointment details
                String message = "Appointment Details:\n" +
                                 "Patient Name: " + name + "\n" +
                                 "Doctor: " + doctor + "\n" +
                                 "Appointment Date: " + date + "\n" +
                                 "Appointment Time: " + time;

                // Show the details in a dialog
                JOptionPane.showMessageDialog(null, message);
            } else {
                // No appointment found
                JOptionPane.showMessageDialog(null, "No appointment found for patient ID: " + patientId);
            }

        } catch (SQLException ex) {
            // Handle SQL exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving appointment.");
        }
    }

    public static void displayAppointmentByPatientName(String patientName) {
        try (Connection connection = DatabaseConnection.getConnection()) {  // Establish connection
            String sql = "SELECT patients.id, appointments.doctor, appointments.appointment_date, appointments.appointment_time "
                    + "FROM patients "
                    + "JOIN appointments ON patients.id = appointments.patient_id "
                    + "WHERE patients.name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);  // Prepare SQL query
            statement.setString(1, patientName);  // Set the patient name in the query
            ResultSet resultSet = statement.executeQuery();  // Execute query

            if (resultSet.next()) {  // Check if a result is found
                int patientId = resultSet.getInt("id");
                String doctor = resultSet.getString("doctor");
                String date = resultSet.getString("appointment_date");
                String time = resultSet.getString("appointment_time");

                // Create a message to display the appointment details
                String message = "Appointment Details:\n" +
                                 "Patient ID: " + patientId + "\n" +
                                 "Doctor: " + doctor + "\n" +
                                 "Appointment Date: " + date + "\n" +
                                 "Appointment Time: " + time;

                // Show the details in a dialog
                JOptionPane.showMessageDialog(null, message);
            } else {
                // No appointment found
                JOptionPane.showMessageDialog(null, "No appointment found for patient name: " + patientName);
            }

        } catch (SQLException ex) {
            // Handle SQL exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving appointment.");
        }
    }
}
