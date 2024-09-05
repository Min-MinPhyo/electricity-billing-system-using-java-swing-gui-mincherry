package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame {

    BillDetails(String meter) {
        setTitle("Bill Details");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.WHITE);

        // Create a panel for header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(0, 123, 255)); // Bootstrap Blue

        JLabel titleLabel = new JLabel("Bill Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Create and set up the table
        JTable table = new JTable();
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setGridColor(Color.GRAY);
        table.setSelectionBackground(new Color(173, 216, 230)); // Alice Blue
        table.setSelectionForeground(Color.BLACK);

        try {
            Conn c = new Conn();
            String query = "select * from bill where meter_no = '" + meter + "'";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add table to JScrollPane
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(sp, BorderLayout.CENTER);

        // Add footer with a button to close the window
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(Color.WHITE);

        JButton closeButton = new JButton("Close");
        closeButton.setBackground(new Color(255, 69, 0)); // Red-Orange
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.addActionListener(e -> setVisible(false));
        footerPanel.add(closeButton);

        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BillDetails("")); // Ensure the GUI updates on the Event Dispatch Thread
    }
}
