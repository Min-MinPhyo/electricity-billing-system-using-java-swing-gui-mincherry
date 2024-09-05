package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener {

    JTextField tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton update, cancel;
    String meter;
    JLabel name;

    UpdateInformation(String meter) {
        
        this.meter = meter;
        setBounds(300, 150, 900, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        

        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(50, 10, 800, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(new Color(34, 139, 34));
        add(heading);

        JPanel formPanel = new JPanel();
        
        formPanel.setLayout(null);
        formPanel.setBounds(20, 80, 500,400);
        
        
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(34, 139, 34)), "Customer Details", 0, 0, new Font("Tahoma", Font.BOLD, 16), new Color(34, 139, 34)));
        formPanel.setBackground(Color.WHITE);
        add(formPanel);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(30, 30, 150, 25);
        lblname.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        formPanel.add(lblname);

        name = new JLabel("");
        name.setBounds(200, 30, 250, 25);
        name.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        formPanel.add(name);

        JLabel lblmeternumber = new JLabel("Meter Number:");
        lblmeternumber.setBounds(30, 70, 150, 25);
        lblmeternumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        formPanel.add(lblmeternumber);

        JLabel meternumber = new JLabel("");
        meternumber.setBounds(200, 70, 250, 25);
        meternumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        formPanel.add(meternumber);

        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(30, 110, 150, 25);
        lbladdress.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        formPanel.add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 110, 250, 25);
        tfaddress.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(tfaddress);

        JLabel lblcity = new JLabel("City:");
        lblcity.setBounds(30, 150, 150, 25);
        lblcity.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        formPanel.add(lblcity);

        tfcity = new JTextField();
        tfcity.setBounds(200, 150, 250, 25);
        tfcity.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(tfcity);

        JLabel lblstate = new JLabel("State:");
        lblstate.setBounds(30, 190, 150, 25);
        lblstate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        formPanel.add(lblstate);

        tfstate = new JTextField();
        tfstate.setBounds(200, 190, 250, 25);
        tfstate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(tfstate);

        JLabel lblemail = new JLabel("Email:");
        lblemail.setBounds(30, 230, 150, 25);
        lblemail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        formPanel.add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 230, 250, 25);
        tfemail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(tfemail);

        JLabel lblphone = new JLabel("Phone:");
        lblphone.setBounds(30, 270, 150, 25);
        lblphone.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        formPanel.add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(200, 270, 250, 25);
        tfphone.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(tfphone);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");
            while (rs.next()) {
                name.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfemail.setText(rs.getString("email"));
                tfphone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        update = new JButton("Update");
        update.setBackground(new Color(34, 139, 34));
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        update.setBounds(50, 400, 150, 35);
        add(update);
        update.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(220, 20, 60));
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cancel.setBounds(250, 400, 150, 35);
        add(cancel);
        cancel.addActionListener(this);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/updateuser.png"));
        Image i2 = i1.getImage().getScaledInstance(200,150, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550, 100, 400, 300);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();

            try {
                Conn c = new Conn();
                c.s.executeUpdate("update customer set address = '" + address + "', city = '" + city + "', state = '" + state + "', email = '" + email + "', phone = '" + phone + "' where meter_no = '" + meter + "'");

                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateInformation("");
    }
}
