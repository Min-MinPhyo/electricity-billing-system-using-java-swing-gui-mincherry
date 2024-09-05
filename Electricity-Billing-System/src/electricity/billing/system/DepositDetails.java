package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener {

    Choice meterNumberChoice, monthChoice;
    JTable table;
    JButton searchButton, printButton;

    DepositDetails() {
        super("Pay Bill Details");

        setSize(800, 700);
        setLocation(300, 100);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Top panel for search controls
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2, 10, 10));
        topPanel.setBackground(new Color(173, 216, 230)); // Light Cyan Background

        // Meter Number Search
        JLabel meterNumberLabel = new JLabel("Search By Meter Number");
        meterNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        meterNumberLabel.setForeground(new Color(70, 130, 180)); // Steel Blue Color
        topPanel.add(meterNumberLabel);

        meterNumberChoice = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                meterNumberChoice.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        topPanel.add(meterNumberChoice);

        // Month Search
        JLabel monthLabel = new JLabel("Search By Month");
        monthLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        monthLabel.setForeground(new Color(70, 130, 180)); // Steel Blue Color
        topPanel.add(monthLabel);

        monthChoice = new Choice();
        monthChoice.add("January");
        monthChoice.add("February");
        monthChoice.add("March");
        monthChoice.add("April");
        monthChoice.add("May");
        monthChoice.add("June");
        monthChoice.add("July");
        monthChoice.add("August");
        monthChoice.add("September");
        monthChoice.add("October");
        monthChoice.add("November");
        monthChoice.add("December");
        topPanel.add(monthChoice);

        add(topPanel, BorderLayout.NORTH);

        // Table for displaying results
        table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Deposit Details"));
        add(tableScrollPane, BorderLayout.CENTER);

        // Bottom panel for buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(173, 216, 230)); // Light Cyan Background

        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(70, 130, 180)); // Steel Blue Color
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        searchButton.addActionListener(this);
        bottomPanel.add(searchButton);

        printButton = new JButton("Print");
        printButton.setBackground(new Color(70, 130, 180)); // Steel Blue Color
        printButton.setForeground(Color.WHITE);
        printButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        printButton.addActionListener(this);
        bottomPanel.add(printButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Load initial data
        loadInitialData();

        setVisible(true);
    }

    private void loadInitialData() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String query = "select * from bill where meter_no = '" + meterNumberChoice.getSelectedItem() + "' and month = '" + monthChoice.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == printButton) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DepositDetails();
    }
}
