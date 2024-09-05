package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class NewCustomer extends JFrame implements ActionListener{

    JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton next, cancel;
    JLabel lblmeter;

    NewCustomer() {
        setTitle("New Customer Registration");
        setSize(750, 500);
        setLocation(400, 200);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(224, 255, 255)); // Light Cyan Background
        add(mainPanel);

        // Heading
        JLabel heading = new JLabel("New Customer Registration");
        heading.setBounds(200, 20, 350, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 26));
        heading.setForeground(new Color(70, 130, 180)); // Steel Blue Color
        mainPanel.add(heading);

        // Customer Name
        JLabel lblname = new JLabel("Customer Name:");
        lblname.setBounds(100, 80, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(260, 80, 300, 25);
        tfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(tfname);

        // Meter Number
        JLabel lblmeterno = new JLabel("Meter Number:");
        lblmeterno.setBounds(100, 120, 150, 25);
        lblmeterno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblmeterno);

        lblmeter = new JLabel("");
        lblmeter.setBounds(260, 120, 300, 25);
        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblmeter.setForeground(Color.RED);
        mainPanel.add(lblmeter);

        Random ran = new Random();
        long number = Math.abs(ran.nextLong() % 1000000);
        lblmeter.setText("" + number);

        // Address
        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(100, 160, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(260, 160, 300, 25);
        tfaddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(tfaddress);

        // City
        JLabel lblcity = new JLabel("City:");
        lblcity.setBounds(100, 200, 150, 25);
        lblcity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblcity);

        tfcity = new JTextField();
        tfcity.setBounds(260, 200, 300, 25);
        tfcity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(tfcity);

        // State
        JLabel lblstate = new JLabel("State:");
        lblstate.setBounds(100, 240, 150, 25);
        lblstate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblstate);

        tfstate = new JTextField();
        tfstate.setBounds(260, 240, 300, 25);
        tfstate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(tfstate);

        // Email
        JLabel lblemail = new JLabel("Email:");
        lblemail.setBounds(100, 280, 150, 25);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(260, 280, 300, 25);
        tfemail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(tfemail);

        // Phone Number
        JLabel lblphone = new JLabel("Phone Number:");
        lblphone.setBounds(100, 320, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(260, 320, 300, 25);
        tfphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(tfphone);

        // Buttons
        next = new JButton("Next");
        next.setBounds(200, 380, 120, 35);
        next.setBackground(new Color(70, 130, 180)); // Steel Blue Color
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Tahoma", Font.BOLD, 16));
        next.addActionListener(this);
        mainPanel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(360, 380, 120, 35);
        cancel.setBackground(new Color(255, 69, 0)); // Red Orange Color
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
        cancel.addActionListener(this);
        mainPanel.add(cancel);

        // Sidebar Image
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
//        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel image = new JLabel(i3);
//        image.setBounds(20, 100, 150, 300);
//        mainPanel.add(image);

        // Setting the background color of the JFrame
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String name = tfname.getText();
            String meter = lblmeter.getText();
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();
            
            String query1 = "insert into customer values('" + name + "', '" + meter + "', '" + address + "', '" + city + "', '" + state + "', '" + email + "', '" + phone + "')";
            String query2 = "insert into login values('" + meter + "', '', '" + name + "', '', '')";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                setVisible(false);
                
                // Open new frame with meter information
                new MeterInfo(meter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
