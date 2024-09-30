package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientRegistrationGUI {

    public static void showPatientRegistrationGUI(JFrame mainMenuFrame) {
        JFrame frame = new JFrame("Patient Registration");
        frame.setSize(400, 400);
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(new Color(255, 248, 220));

        // Font settings
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JLabel nameLabel = new JLabel("Patient Name:");
        nameLabel.setBounds(50, 50, 150, 30);
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.DARK_GRAY);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(200, 50, 150, 30);
        nameField.setFont(textFieldFont);
        frame.add(nameField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 100, 150, 30);
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(Color.DARK_GRAY);
        frame.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(200, 100, 150, 30);
        addressField.setFont(textFieldFont);
        frame.add(addressField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(50, 150, 150, 30);
        contactLabel.setFont(labelFont);
        contactLabel.setForeground(Color.DARK_GRAY);
        frame.add(contactLabel);

        JTextField contactField = new JTextField();
        contactField.setBounds(200, 150, 150, 30);
        contactField.setFont(textFieldFont);
        frame.add(contactField);

        JLabel historyLabel = new JLabel("Medical History:");
        historyLabel.setBounds(50, 200, 150, 30);
        historyLabel.setFont(labelFont);
        historyLabel.setForeground(Color.DARK_GRAY);
        frame.add(historyLabel);

        JTextField historyField = new JTextField();
        historyField.setBounds(200, 200, 150, 30);
        historyField.setFont(textFieldFont);
        frame.add(historyField);

        JButton submitButton = new JButton("Register");
        submitButton.setBounds(150, 250, 100, 40);
        submitButton.setFont(buttonFont);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(34, 139, 34));
        frame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String contact = contactField.getText();
                String medicalHistory = historyField.getText();

                // Call your registration method here
                PatientRegistration.registerPatient(name, address, contact, medicalHistory);  
                JOptionPane.showMessageDialog(frame, "Patient Registered Successfully!");

                // Close the registration form and bring back the main menu
                frame.dispose();
                mainMenuFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
        mainMenuFrame.setVisible(false);  // Hide the main menu while registration is open
    }
}
