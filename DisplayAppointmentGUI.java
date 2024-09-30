package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayAppointmentGUI {

    public static void showAppointmentGUI(JFrame mainMenuFrame) {
        JFrame frame = new JFrame("Display Appointment");
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(new Color(240, 255, 255));

        // Font settings
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JLabel searchLabel = new JLabel("Search by:");
        searchLabel.setBounds(50, 50, 100, 30);
        searchLabel.setFont(labelFont);
        searchLabel.setForeground(Color.DARK_GRAY);
        frame.add(searchLabel);

        String[] searchOptions = {"Patient ID", "Patient Name"};
        JComboBox<String> searchComboBox = new JComboBox<>(searchOptions);
        searchComboBox.setBounds(150, 50, 150, 30);
        frame.add(searchComboBox);

        JTextField searchField = new JTextField();
        searchField.setBounds(50, 100, 250, 30);
        searchField.setFont(textFieldFont);
        frame.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(150, 150, 100, 40);
        searchButton.setFont(buttonFont);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(70, 130, 180));
        frame.add(searchButton);

        // Action listener for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchOption = (String) searchComboBox.getSelectedItem();
                String searchValue = searchField.getText().trim();

                if (searchOption.equals("Patient ID")) {
                    try {
                        int patientId = Integer.parseInt(searchValue);
                        DisplayAppointment.displayAppointmentByPatientID(patientId); // Correct line
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid Patient ID.");
                    }
                } else if (searchOption.equals("Patient Name")) {
                    if (searchValue.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter a Patient Name.");
                    } else {
                        DisplayAppointment.displayAppointmentByPatientName(searchValue); // This line is correct
                    }
                }
            }
        });

        // Make the frame visible and hide the main menu
        frame.setVisible(true);
        mainMenuFrame.setVisible(false);  // Hide the main menu while appointment is displayed

        // Action to close the appointment window and return to the main menu
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();  // Close the appointment frame
                mainMenuFrame.setVisible(true);  // Show the main menu
            }
        });
    }
}
