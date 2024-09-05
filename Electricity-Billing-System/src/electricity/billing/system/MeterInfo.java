package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener {

    JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton submit, cancel;
    JLabel lblmeter;
    Choice meterlocation, metertype, phasecode, billtype;
    String meternumber;

    MeterInfo(String meternumber) {
        this.meternumber = meternumber;

        setTitle("Meter Information");
        setSize(750, 550);
        setLocation(400, 200);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(224, 255, 255)); // Light Cyan Background
        add(mainPanel);

        // Heading
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(200, 20, 350, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 26));
        heading.setForeground(new Color(70, 130, 180)); // Steel Blue Color
        mainPanel.add(heading);

        // Meter Number
        JLabel lblname = new JLabel("Meter Number:");
        lblname.setBounds(100, 80, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblname);

        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(260, 80, 300, 25);
        lblmeternumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblmeternumber.setForeground(Color.RED);
        mainPanel.add(lblmeternumber);

        // Meter Location
        JLabel lblmeterno = new JLabel("Meter Location:");
        lblmeterno.setBounds(100, 120, 150, 25);
        lblmeterno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblmeterno);

        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(260, 120, 300, 25);
        meterlocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(meterlocation);

        // Meter Type
        JLabel lbladdress = new JLabel("Meter Type:");
        lbladdress.setBounds(100, 160, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lbladdress);

        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(260, 160, 300, 25);
        metertype.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(metertype);

        // Phase Code
        JLabel lblcity = new JLabel("Phase Code:");
        lblcity.setBounds(100, 200, 150, 25);
        lblcity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblcity);

        phasecode = new Choice();
        phasecode.add("11");
        phasecode.add("22");
        phasecode.add("33");
        phasecode.add("44");
        phasecode.add("55");
        phasecode.add("66");
        phasecode.add("77");
        phasecode.add("88");
        phasecode.add("99");
        phasecode.setBounds(260, 200, 300, 25);
        phasecode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(phasecode);

        // Bill Type
        JLabel lblstate = new JLabel("Bill Type:");
        lblstate.setBounds(100, 240, 150, 25);
        lblstate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblstate);

        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(260, 240, 300, 25);
        billtype.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(billtype);

        // Days
        JLabel lblemail = new JLabel("Days:");
        lblemail.setBounds(100, 280, 150, 25);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblemail);

        JLabel lblemails = new JLabel("30 Days");
        lblemails.setBounds(260, 280, 300, 25);
        lblemails.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblemails.setForeground(Color.BLUE);
        mainPanel.add(lblemails);

        // Note
        JLabel lblphone = new JLabel("Note:");
        lblphone.setBounds(100, 320, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mainPanel.add(lblphone);

        JLabel lblphones = new JLabel("By default, the bill is calculated for 30 days only.");
        lblphones.setBounds(260, 320, 400, 25);
        lblphones.setFont(new Font("Tahoma", Font.ITALIC, 16));
        lblphones.setForeground(Color.GRAY);
        mainPanel.add(lblphones);

        // Submit Button
        submit = new JButton("Submit");
        submit.setBounds(200, 400, 120, 35);
        submit.setBackground(new Color(70, 130, 180)); // Steel Blue Color
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Tahoma", Font.BOLD, 16));
        submit.addActionListener(this);
        mainPanel.add(submit);

        // Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(360, 400, 120, 35);
        cancel.setBackground(new Color(255, 69, 0)); // Red Orange Color
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
        cancel.addActionListener(this);
        mainPanel.add(cancel);

        // Sidebar Image
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
//        Image i2 = i1.getImage().getScaledInstance(100,150, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel image = new JLabel(i3);
//        image.setBounds(20, 100, 150, 300);
//        mainPanel.add(image);

        // Setting the background color of the JFrame
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";

            String query = "insert into meter_info values('" + meter + "', '" + location + "', '" + type + "', '" + code + "', '" + typebill + "', '" + days + "')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MeterInfo("");
    }
}
