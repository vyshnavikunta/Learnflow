import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class EcommercePlatformGUI {

    private static final Map<String, String> userCredentials = new HashMap<>(); // User registration data
    private static final Map<String, Product> productCatalog = EcommercePlatform.initializeProductCatalog();
    private static final Map<String, Integer> shoppingCart = EcommercePlatform.shoppingCart;

    private static JFrame frame;
    private static JTextField usernameField;
    private static JPasswordField passwordField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("E-commerce Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createLoginPanel();

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (authenticateUser(username, password)) {
                    frame.getContentPane().removeAll();
                    createShoppingPanel();
                    frame.revalidate();
                    frame.repaint();
                } else {
                    int choice = JOptionPane.showConfirmDialog(frame, "User not found. Do you want to register?",
                            "User Registration", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        registerUser();
                    }
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private static void registerUser() {
        String newUsername = JOptionPane.showInputDialog(frame, "Enter a new username:", "User Registration",
                JOptionPane.PLAIN_MESSAGE);
        if (newUsername != null) {  // Check if the user clicked cancel
            while (userCredentials.containsKey(newUsername)) {
                newUsername = JOptionPane.showInputDialog(frame, "Username already exists. Please choose a different username:",
                        "User Registration", JOptionPane.WARNING_MESSAGE);
                if (newUsername == null) {
                    break;
                }
            }

            if (newUsername != null) {  // Check if the user clicked cancel
                String newPassword = JOptionPane.showInputDialog(frame, "Enter a password:", "User Registration",
                        JOptionPane.PLAIN_MESSAGE);
                if (newPassword != null) {  // Check if the user clicked cancel
                    userCredentials.put(newUsername, newPassword);
                    JOptionPane.showMessageDialog(frame, "Registration successful!\nYou can now log in.", "User Registration",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private static void createShoppingPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Sample Product Data
        displayProductOptions(productCatalog);

        // User's Shopping Cart
        addToCart(shoppingCart, productCatalog);

        // Generate Bill
        generateBill(shoppingCart, productCatalog);

        // Modify Products (Change quantity, remove, or add more)
        modifyProducts(shoppingCart, productCatalog);

        // Generate Bill Again
        generateBill(shoppingCart, productCatalog);

        // Enter Billing Address
        enterBillingAddress();

        // Ask the user if they want to continue shopping
        JButton continueShoppingButton = new JButton("Continue Shopping");
        continueShoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                createShoppingPanel();
                frame.revalidate();
                frame.repaint();
            }
        });

        panel.add(continueShoppingButton, BorderLayout.SOUTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private static boolean authenticateUser(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    private static void displayProductOptions(Map<String, Product> productCatalog) {
        // Implement your logic for displaying product options in the GUI
        // This can include buttons, labels, or any other Swing components to show the products.
    }

    private static void addToCart(Map<String, Integer> shoppingCart, Map<String, Product> productCatalog) {
        // Implement your logic for adding to cart in the GUI
        // This can include buttons, input fields, or any other Swing components to facilitate adding to the cart.
    }

    private static void generateBill(Map<String, Integer> shoppingCart, Map<String, Product> productCatalog) {
        // Implement your logic for generating bill in the GUI
        // This can include displaying the bill, total price, and any other relevant information.
    }

    private static void modifyProducts(Map<String, Integer> shoppingCart, Map<String, Product> productCatalog) {
        // Implement your logic for modifying products in the GUI
        // This can include buttons, input fields, or any other Swing components for modification.
    }

    private static void enterBillingAddress() {
        // Implement your logic for entering billing address in the GUI
        // This can include input fields or any other Swing components for entering the billing address.
    }

  
}
