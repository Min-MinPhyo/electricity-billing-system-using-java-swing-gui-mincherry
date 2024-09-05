package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    Thread t;

    Splash() {
        // Setting up the window without layout managers for custom positioning
        setUndecorated(true);
        setLayout(null);
        
        // Adding the splash image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 730, 550);
        add(image);
        
        // Adding a gradient background
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(34, 193, 195);
                Color color2 = new Color(253, 187, 45);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        gradientPanel.setBounds(0, 0, 730, 550);
        add(gradientPanel);
        
        // Ensuring the image is on top of the gradient background
        gradientPanel.setLayout(null);
        gradientPanel.add(image);
        
        // Adding a title on the splash screen
        JLabel title = new JLabel("Welcome to Electricity Billing System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 20, 730, 40);
        gradientPanel.add(title);

        setVisible(true);

        // Splash screen size and position animation
        int x = 1;
        for (int i = 2; i < 600; i += 4, x += 1) {
            setSize(i + x, i);
            setLocationRelativeTo(null);
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Start the thread for splash timing
        t = new Thread(this);
        t.start();

        setVisible(true);
    }

    public void run() {
        try {
            Thread.sleep(7000);
            setVisible(false);

            // Show the login frame after the splash
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
