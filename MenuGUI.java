package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {

    public static void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Hospital Registration System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(new Color(224, 255, 255));

        // Font settings
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Register Patient Button
        JButton registerButton = new JButton("Register Patient");
        registerButton.setBounds(100, 50, 200, 40);
        registerButton.setFont(buttonFont);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(70, 130, 180));
        frame.add(registerButton);
        
        // Display Appointment Button
        JButton displayButton = new JButton("Display Appointment");
        displayButton.setBounds(100, 100, 200, 40);
        displayButton.setFont(buttonFont);
        displayButton.setForeground(Color.WHITE);
        displayButton.setBackground(new Color(60, 179, 113));
        frame.add(displayButton);

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(100, 150, 200, 40);
        exitButton.setFont(buttonFont);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(220, 20, 60));
        frame.add(exitButton);

        // Add action listener for Register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientRegistrationGUI.showPatientRegistrationGUI(frame);  // Pass main frame
            }
        });

        // Add action listener for Display Appointment button
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayAppointmentGUI.showAppointmentGUI(frame);  // Pass main frame
            }
        });

        // Add action listener for Exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Run GUI in the event-dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
