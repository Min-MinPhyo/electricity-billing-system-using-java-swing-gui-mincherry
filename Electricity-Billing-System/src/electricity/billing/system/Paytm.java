package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener {

    String meter;
    JButton back;

    Paytm(String meter) {
        this.meter = meter;

        // Set up the frame
        setTitle("Pay Bill via Paytm");
        setSize(800, 600);
        setLocation(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add a title label
        JLabel titleLabel = new JLabel("Pay Your Bill via Paytm", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBackground(new Color(70, 130, 180)); // Steel Blue
        titleLabel.setOpaque(true);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setPreferredSize(new Dimension(100, 50));
        add(titleLabel, BorderLayout.NORTH);

        // Add the JEditorPane for displaying the Paytm webpage
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try {
            j.setPage("https://paytm.com/online-payments");
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load the payment page. Please check your internet connection.<html>");
        }

        JScrollPane pane = new JScrollPane(j);
        add(pane, BorderLayout.CENTER);

        // Create a panel for the back button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);

        back = new JButton("Back");
        back.setBackground(new Color(220, 20, 60)); // Crimson
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setPreferredSize(new Dimension(100, 40));
        back.addActionListener(this);
        buttonPanel.add(back);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new PayBill(meter);
        }
    }

    public static void main(String[] args) {
        new Paytm("");
    }
}
