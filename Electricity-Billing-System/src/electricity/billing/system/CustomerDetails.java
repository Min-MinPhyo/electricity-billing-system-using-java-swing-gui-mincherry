package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener {

    JTable table;
    JButton printButton;

    CustomerDetails() {
        super("Customer Details");

        setSize(1200, 700);
        setLocation(200, 100);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Table for displaying customer details
        table = new JTable();
        loadCustomerData();
        
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Customer Details"));
        add(tableScrollPane, BorderLayout.CENTER);

        // Print button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(173, 216, 230)); // Light Cyan Background

        printButton = new JButton("Print");
        printButton.setBackground(new Color(70, 130, 180)); // Steel Blue Color
        printButton.setForeground(Color.WHITE);
        printButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        printButton.addActionListener(this);
        bottomPanel.add(printButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadCustomerData() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == printButton) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new CustomerDetails();
    }
}
