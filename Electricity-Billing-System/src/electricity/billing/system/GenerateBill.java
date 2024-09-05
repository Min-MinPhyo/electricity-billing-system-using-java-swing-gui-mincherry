package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener {

    String meter;
    JButton generateBillButton;
    Choice monthChoice;
    JTextArea billArea;

    GenerateBill(String meter) {
        this.meter = meter;

        // Frame Setup
        setTitle("Generate Bill");
        setSize(600, 800);
        setLocation(550, 30);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for Header and Controls
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(3, 1));
        headerPanel.setBackground(new Color(230, 247, 255)); // Softer Light Cyan Background

        // Header Label
        JLabel heading = new JLabel("Generate Bill", JLabel.CENTER);
        heading.setFont(new Font("Tahoma", Font.BOLD, 26));
        heading.setForeground(new Color(0, 102, 204)); // Deep Blue Color
        headerPanel.add(heading);

        // Meter Number Display
        JLabel meterLabel = new JLabel("Meter Number: " + meter, JLabel.CENTER);
        meterLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        meterLabel.setForeground(new Color(64, 64, 64)); // Dark Gray for readability
        headerPanel.add(meterLabel);

        // Month Choice Dropdown
        monthChoice = new Choice();
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            monthChoice.add(month);
        }

        JPanel monthPanel = new JPanel();
        monthPanel.setBackground(new Color(230, 247, 255));
        monthPanel.add(new JLabel("Select Month:"));
        monthPanel.add(monthChoice);
        headerPanel.add(monthPanel);

        // Adding the header panel to the frame
        add(headerPanel, BorderLayout.NORTH);

        // Bill Area
        billArea = new JTextArea();
        billArea.setEditable(false);
        billArea.setFont(new Font("Serif", Font.PLAIN, 16));
        billArea.setMargin(new Insets(10, 10, 10, 10));
        billArea.setBackground(new Color(245, 245, 245)); // Light Gray for a clean look

        JScrollPane billScrollPane = new JScrollPane(billArea);
        add(billScrollPane, BorderLayout.CENTER);

        // Generate Bill Button
        generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBackground(new Color(0, 102, 204)); // Deep Blue Background
        generateBillButton.setForeground(Color.WHITE); // White text for contrast
        generateBillButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        generateBillButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generateBillButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 247, 255)); // Matching background
        buttonPanel.add(generateBillButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            String month = monthChoice.getSelectedItem();
            billArea.setText("\n\t\tElectricity Bill\n");
            billArea.append("\n\tReliance Power Limited\n");
            billArea.append("\tELECTRICITY BILL GENERATED FOR THE MONTH\n");
            billArea.append("\tOF " + month + ", 2022\n\n");

            // Fetch customer details
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");
            if (rs.next()) {
                billArea.append("Customer Name: " + rs.getString("name") + "\n");
                billArea.append("Meter Number: " + rs.getString("meter_no") + "\n");
                billArea.append("Address: " + rs.getString("address") + "\n");
                billArea.append("City: " + rs.getString("city") + "\n");
                billArea.append("State: " + rs.getString("state") + "\n");
                billArea.append("Email: " + rs.getString("email") + "\n");
                billArea.append("Phone: " + rs.getString("phone") + "\n");
                billArea.append("----------------------------------------------------\n");
            }

            // Fetch meter details
            rs = c.s.executeQuery("select * from meter_info where meter_no = '" + meter + "'");
            if (rs.next()) {
                billArea.append("Meter Location: " + rs.getString("meter_location") + "\n");
                billArea.append("Meter Type: " + rs.getString("meter_type") + "\n");
                billArea.append("Phase Code: " + rs.getString("phase_code") + "\n");
                billArea.append("Bill Type: " + rs.getString("bill_type") + "\n");
                billArea.append("Days: " + rs.getString("days") + "\n");
                billArea.append("----------------------------------------------------\n");
            }

            // Fetch tax details
            rs = c.s.executeQuery("select * from tax");
            if (rs.next()) {
                billArea.append("Cost Per Unit: " + rs.getString("cost_per_unit") + "\n");
                billArea.append("Meter Rent: " + rs.getString("meter_rent") + "\n");
                billArea.append("Service Charge: " + rs.getString("service_charge") + "\n");
                billArea.append("Service Tax: " + rs.getString("service_tax") + "\n");
                billArea.append("Swacch Bharat Cess: " + rs.getString("swacch_bharat_cess") + "\n");
                billArea.append("Fixed Tax: " + rs.getString("fixed_tax") + "\n");
                billArea.append("----------------------------------------------------\n");
            }

            // Fetch bill details
            rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' and month='" + month + "'");
            if (rs.next()) {
                billArea.append("Current Month: " + rs.getString("month") + "\n");
                billArea.append("Units Consumed: " + rs.getString("units") + "\n");
                billArea.append("Total Charges: " + rs.getString("totalbill") + "\n");
                billArea.append("----------------------------------------------------\n");
                billArea.append("Total Payable: " + rs.getString("totalbill") + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
