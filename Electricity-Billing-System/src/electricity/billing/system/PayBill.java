package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener {

    Choice cmonth;
    JButton pay, back;
    String meter;
    JLabel meternumber, labelname, labelunits, labeltotalbill, labelstatus;

    PayBill(String meter) {
        this.meter = meter;

        // Frame properties
        setLayout(null);
        setBounds(300, 150, 900, 600);
        setTitle("Electricity Billing System");

        // Heading label
        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);

        // Meter Number
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(35, 80, 200, 20);
        add(lblmeternumber);

        meternumber = new JLabel("");
        meternumber.setBounds(300, 80, 200, 20);
        add(meternumber);

        // Name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 200, 20);
        add(lblname);

        labelname = new JLabel("");
        labelname.setBounds(300, 140, 200, 20);
        add(labelname);

        // Month
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 200, 20);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(300, 200, 200, 20);
        String[] months = {"January", "February", "March", "April", "May", "June", 
                           "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            cmonth.add(month);
        }
        add(cmonth);

        // Units
        JLabel lblunits = new JLabel("Units");
        lblunits.setBounds(35, 260, 200, 20);
        add(lblunits);

        labelunits = new JLabel("");
        labelunits.setBounds(300, 260, 200, 20);
        add(labelunits);

        // Total Bill
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 200, 20);
        add(lbltotalbill);

        labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(300, 320, 200, 20);
        add(labeltotalbill);

        // Status
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        add(lblstatus);

        labelstatus = new JLabel("");
        labelstatus.setBounds(300, 380, 200, 20);
        labelstatus.setForeground(Color.RED);
        add(labelstatus);

        // Fetch initial customer details and bill details for January
        loadCustomerDetails();
        loadBillDetails("January");

        // Add item listener to month choice
        cmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                loadBillDetails(cmonth.getSelectedItem());
            }
        });

        // Pay button
        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100, 460, 100, 25);
        pay.addActionListener(this);
        add(pay);

        // Back button
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230, 460, 100, 25);
        back.addActionListener(this);
        add(back);

        // Background color
        getContentPane().setBackground(Color.WHITE);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 120, 600, 300);
        add(image);

        setVisible(true);
    }

    private void loadCustomerDetails() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");
            if (rs.next()) {
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBillDetails(String month) {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' AND month = '" + month + "'");
            if (rs.next()) {
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            } else {
                labelunits.setText("");
                labeltotalbill.setText("");
                labelstatus.setText("No Data");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            try {
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '" + meter + "' AND month='" + cmonth.getSelectedItem() + "'");
                JOptionPane.showMessageDialog(null, "Payment Successful");
                loadBillDetails(cmonth.getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
