package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    String atype, meter;

    Project(String atype, String meter) {
        this.atype = atype;
        this.meter = meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Electricity Billing System");

        // Setting up a background image with transparency overlay for a modern look
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icon/elect2.jpg"));
        Image backgroundImg = backgroundIcon.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImg);
        JLabel background = new JLabel(scaledBackgroundIcon);
        background.setLayout(new BorderLayout());
        add(background);

        // Creating a transparent panel to overlay on the background for menus
        JPanel overlayPanel = new JPanel(new GridLayout(1, 1));
        overlayPanel.setOpaque(false);
        background.add(overlayPanel, BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();
        overlayPanel.add(menuBar);

        // Customizing the look of the menus
        Font menuFont = new Font("SansSerif", Font.BOLD, 14);

        JMenu masterMenu = createMenu("Master", Color.BLUE, menuFont);
        JMenu infoMenu = createMenu("Information", Color.RED, menuFont);
        JMenu userMenu = createMenu("User", Color.BLUE, menuFont);
        JMenu reportMenu = createMenu("Report", Color.RED, menuFont);
        JMenu utilityMenu = createMenu("Utility", Color.BLUE, menuFont);
        JMenu exitMenu = createMenu("Exit", Color.RED, menuFont);

        if (atype.equals("Admin")) {
            menuBar.add(masterMenu);
        } else {
            menuBar.add(infoMenu);
            menuBar.add(userMenu);
            menuBar.add(reportMenu);
        }

        menuBar.add(utilityMenu);
        menuBar.add(exitMenu);

        // Adding menu items with improved design
        addMenuItem(masterMenu, "New Customer", "icon/icon1.png", KeyEvent.VK_D, 'D', menuFont);
        addMenuItem(masterMenu, "Customer Details", "icon/icon2.png", KeyEvent.VK_M, 'M', menuFont);
        addMenuItem(masterMenu, "Pay Bill Details", "icon/icon3.png", KeyEvent.VK_N, 'N', menuFont);
        addMenuItem(masterMenu, "Calculate Bill", "icon/icon5.png", KeyEvent.VK_B, 'B', menuFont);

        addMenuItem(infoMenu, "Update Information", "icon/icon4.png", KeyEvent.VK_P, 'P', menuFont);
        addMenuItem(infoMenu, "View Information", "icon/icon6.png", KeyEvent.VK_L, 'L', menuFont);

        addMenuItem(userMenu, "Pay Bill", "icon/icon4.png", KeyEvent.VK_R, 'R', menuFont);
        addMenuItem(userMenu, "Bill Details", "icon/icon6.png", KeyEvent.VK_B, 'B', menuFont);

        addMenuItem(reportMenu, "Generate Bill", "icon/icon7.png", KeyEvent.VK_G, 'G', menuFont);

        addMenuItem(utilityMenu, "Notepad", "icon/icon12.png", KeyEvent.VK_N, 'N', menuFont);
        addMenuItem(utilityMenu, "Calculator", "icon/icon9.png", KeyEvent.VK_C, 'C', menuFont);

        addMenuItem(exitMenu, "Exit", "icon/icon11.png", KeyEvent.VK_W, 'W', menuFont);

        setVisible(true);
    }

    // Method to create a JMenu with a specific design
    private JMenu createMenu(String title, Color color, Font font) {
        JMenu menu = new JMenu(title);
        menu.setForeground(color);
        menu.setFont(font);
        return menu;
    }

    // Method to add a JMenuItem with specific properties
    private void addMenuItem(JMenu menu, String title, String iconPath, int keyEvent, char mnemonic, Font font) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(font);
        menuItem.setBackground(Color.WHITE);
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        menuItem.setIcon(new ImageIcon(img));
        menuItem.setMnemonic(mnemonic);
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(keyEvent, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
    }

    // Handling the actions performed by the menu items
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        switch (msg) {
            case "New Customer":
                new NewCustomer();
                break;
            case "Customer Details":
                new CustomerDetails();
                break;
            case "Pay Bill Details":
                new DepositDetails();
                break;
            case "Calculate Bill":
                new CalculateBill();
                break;
            case "View Information":
                new ViewInformation(meter);
                break;
            case "Update Information":
                new UpdateInformation(meter);
                break;
            case "Bill Details":
                new BillDetails(meter);
                break;
            case "Notepad":
                runExternalApplication("notepad.exe");
                break;
            case "Calculator":
                runExternalApplication("calc.exe");
                break;
            case "Exit":
                setVisible(false);
                new Login();
                break;
            case "Pay Bill":
                new PayBill(meter);
                break;
            case "Generate Bill":
                new GenerateBill(meter);
                break;
        }
    }

    // Method to run an external application
    private void runExternalApplication(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Project("", "");
    }
}
