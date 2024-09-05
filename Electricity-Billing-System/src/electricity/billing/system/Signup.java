package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {

    JButton create, back;
    Choice accountType;
    JTextField meter, username, name, password;

    Signup() {
        setTitle("Signup - Electricity Billing System");
        setBounds(450, 150, 750, 450);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(
                new LineBorder(new Color(100, 149, 237), 2), 
                "Create Account", 
                TitledBorder.LEADING, 
                TitledBorder.TOP, 
                new Font("Segoe UI", Font.BOLD, 16), 
                new Color(70, 130, 180)
        ));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        add(panel, BorderLayout.CENTER);

        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100, 20, 200, 30);
        heading.setForeground(new Color(105, 105, 105));
        heading.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(heading);

        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        accountType.setBounds(320, 25, 200, 25);
        panel.add(accountType);

        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100, 80, 200, 30);
        lblmeter.setForeground(new Color(105, 105, 105));
        lblmeter.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblmeter.setVisible(false);
        panel.add(lblmeter);

        meter = new JTextField();
        meter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        meter.setBounds(320, 85, 200, 25);
        meter.setVisible(false);
        panel.add(meter);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(100, 130, 200, 30);
        lblusername.setForeground(new Color(105, 105, 105));
        lblusername.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(lblusername);

        username = new JTextField();
        username.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        username.setBounds(320, 135, 200, 25);
        panel.add(username);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 180, 200, 30);
        lblname.setForeground(new Color(105, 105, 105));
        lblname.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(lblname);

        name = new JTextField();
        name.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        name.setBounds(320, 185, 200, 25);
        panel.add(name);

        meter.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter.getText() + "'");
                    if (rs.next()) {
                        name.setText(rs.getString("name"));
                    } else {
                        name.setText("");
                        JOptionPane.showMessageDialog(null, "Meter Number not found");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100, 230, 200, 30);
        lblpassword.setForeground(new Color(105, 105, 105));
        lblpassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(lblpassword);

        password = new JTextField();
        password.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        password.setBounds(320, 235, 200, 25);
        panel.add(password);

        accountType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ae) {
                String user = accountType.getSelectedItem();
                if (user.equals("Customer")) {
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                } else {
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });

        create = new JButton("Create");
        create.setBackground(new Color(70, 130, 180));
        create.setForeground(Color.WHITE);
        create.setFont(new Font("Segoe UI", Font.BOLD, 14));
        create.setBounds(160, 300, 120, 35);
        create.addActionListener(this);
        panel.add(create);

        back = new JButton("Back");
        back.setBackground(new Color(70, 130, 180));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.setBounds(320, 300, 120, 35);
        back.addActionListener(this);
        panel.add(back);

        // Load and scale image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signup1.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550, 70, 150, 150);
        panel.add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            String atype = accountType.getSelectedItem();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            String smeter = meter.getText();

            if (susername.isEmpty() || sname.isEmpty() || spassword.isEmpty() || (atype.equals("Customer") && smeter.isEmpty())) {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
                return;
            }

            try {
                Conn c = new Conn();
                String query;

                if (atype.equals("Admin")) {
                    query = "insert into login (meter_no, username, name, password, user) values('', '" + susername + "', '" + sname + "', '" + spassword + "', '" + atype + "')";
                } else {
                    query = "update login set username = '" + susername + "', password = '" + spassword + "', user = '" + atype + "' where meter_no = '" + smeter + "'";
                }
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Account Created Successfully");

                setVisible(false);
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Signup::new);
    }
}
