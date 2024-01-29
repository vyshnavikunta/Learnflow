import java.util.*;

public class EcommercePlatform {

    public static final Map<String, String> userCredentials = new HashMap<>();
    public static final Map<String, Product> productCatalog = initializeProductCatalog();
    public static final Map<String, Integer> shoppingCart = new HashMap<>();
    public static boolean billGenerated = false;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // New User Registration
        registerNewUser(scanner);

        authenticateUser(scanner);
        

        while (true) {
          

           // Sample Product Data
           displayProductOptions(productCatalog);

           // User's Shopping Cart
           addToCart(shoppingCart, productCatalog);

           // Generate Bill
           //generateBill(shoppingCart, productCatalog);

           // Modify Products (Change quantity, remove, or add more)
           modifyProducts(shoppingCart, productCatalog);

           // Generate Bill Again
           generateBill(shoppingCart, productCatalog);

           // Enter Billing Address
           enterBillingAddress(scanner);

           // Ask the user if they want to continue shopping
           System.out.print("Do you want to continue shopping? (yes/no): ");
           String continueShopping = scanner.nextLine().trim().toLowerCase();
           if (!continueShopping.equals("yes")) {
               generateBill(shoppingCart, productCatalog); 
               break;
           }
        }
        System.out.println("Thank you for shopping with us!");
    }

  

    public static Map<String, Product> initializeProductCatalog() {
        Map<String, Product> productCatalog = new HashMap<>();
        productCatalog.put("1", new Product("Product A", 10.99));
        productCatalog.put("2", new Product("Product B", 5.99));
        productCatalog.put("3", new Product("Product C", 8.49));
        productCatalog.put("4", new Product("Product D", 15.99));
        productCatalog.put("5", new Product("Product E", 3.99));
        return productCatalog;
    }

    public static void displayProductOptions(Map<String, Product> productCatalog) {
        System.out.println("Product Options:");
        for (Map.Entry<String, Product> entry : productCatalog.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().name + " - $" + entry.getValue().price);
        }
    }

    public static void addToCart(Map<String, Integer> shoppingCart, Map<String, Product> productCatalog) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter product number (or 'done' to finish and 'bill' to get the price): ");
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("done") || userInput.equalsIgnoreCase("bill")) {
                generateBill(shoppingCart, productCatalog);
                break;
            }

            if (productCatalog.containsKey(userInput)) {
                try {
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // consume the newline

                    if (quantity > 0) {
                        shoppingCart.put(userInput, shoppingCart.getOrDefault(userInput, 0) + quantity);
                    } else {
                        System.out.println("Quantity must be greater than 0. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid quantity.");
                    scanner.nextLine(); // consume the invalid input
                }
            } else {
                System.out.println("Invalid product number. Please try again.");
            }
        }
        //scanner.close();
    }

    public static void generateBill(Map<String, Integer> shoppingCart, Map<String, Product> productCatalog) {
        System.out.println("\nGenerated Bill:");
    double totalAmount = 0;

    if (shoppingCart.isEmpty()) {
        System.out.println("Your shopping cart is empty.");
    } else {
        for (Map.Entry<String, Integer> entry : shoppingCart.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = productCatalog.get(productId);
            if (product != null) {
                double itemTotal = quantity * product.price;
                System.out.println(product.name + ": Quantity " + quantity + " - $" + itemTotal);
                totalAmount += itemTotal;
            }
        }

        System.out.println("\nTotal Price in Cart: $" + totalAmount);
    }

    // Set the flag to indicate that the bill has been generated
    billGenerated = true;
    }

    public static void modifyProducts(Map<String, Integer> shoppingCart, Map<String, Product> productCatalog) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nModify Products Menu:");
            System.out.println("1. Change quantity");
            System.out.println("2. Remove product");
            System.out.println("3. Add more products");
            System.out.println("4. Generate Bill");
            System.out.println("5. Continue to billing");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline
    
                switch (choice) {
                    case 1:
                        changeQuantity(shoppingCart, productCatalog);
                        break;
                    case 2:
                        removeProduct(shoppingCart, productCatalog);
                        break;
                    case 3:
                        addToCart(shoppingCart, productCatalog);
                        break;
                    case 4:
                        if (!billGenerated) {
                            generateBill(shoppingCart, productCatalog);
                        }
                        break;
                    case 5:
                        enterBillingAddress(scanner);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume the invalid input
            }
        }
    }

    public static void changeQuantity(Map<String, Integer> shoppingCart, Map<String, Product> productCatalog) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product number to change quantity: ");
        String productNumber = scanner.nextLine().trim();

        if (shoppingCart.containsKey(productNumber)) {
            System.out.print("Enter new quantity: ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            if (newQuantity > 0) {
                shoppingCart.put(productNumber, newQuantity);
                System.out.println("Quantity updated successfully.");
            } else {
                System.out.println("Quantity must be greater than 0. No changes were made.");
            }
        } else {
            System.out.println("Product not found in the cart.");
        }
       // scanner.close();
    }

    public static void removeProduct(Map<String, Integer> shoppingCart, Map<String, Product> productCatalog) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product number to remove: ");
        String productNumber = scanner.nextLine().trim();

        if (shoppingCart.containsKey(productNumber)) {
            shoppingCart.remove(productNumber);
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found in the cart.");
        }
       // scanner.close();
    }


    public static class Product {
        String name;
        double price;
    
        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    public static void enterBillingAddress(Scanner scanner) {
        System.out.print("\nDo you want to proceed to billing? (yes/no): ");
        String proceedToBilling = scanner.nextLine().trim().toLowerCase();
    
        if (proceedToBilling.equals("yes")) {
            processPayment(scanner);
        } else {
            System.out.println("Billing process canceled. Continue shopping.");
        }
    }

    static void processPayment(Scanner scanner) {
        System.out.print("Enter your credit card number: ");
        String creditCardNumber = scanner.nextLine().trim();
    
        // Validate the credit card number (this is a basic example, you should use a secure validation process)
        if (isValidCreditCard(creditCardNumber)) {
            System.out.println("Processing payment...");
            System.out.println("Payment successful! Thank you for your purchase.");
            System.exit(0);
        } else {
            System.out.println("Invalid credit card number. Payment failed.");
        }
    }
    
    static boolean isValidCreditCard(String creditCardNumber) {
        // In a real-world scenario, use a secure method to validate the credit card number
        // This is a basic example and should not be used for actual transactions
        return creditCardNumber.matches("\\d{16}");
    }
    

    public static void registerNewUser(Scanner scanner) {
        System.out.println("Welcome to the Ecommerce Platform! Please register to continue.");
        System.out.print("Enter a new username: ");
        String newUsername = scanner.nextLine().trim();

        while (userCredentials.containsKey(newUsername)) {
            System.out.println("Username already exists. Please choose a different username.");
            System.out.print("Enter a new username: ");
            newUsername = scanner.nextLine().trim();
        }

        System.out.print("Enter a password: ");
        String newPassword = scanner.nextLine().trim();

        userCredentials.put(newUsername, newPassword);
        System.out.println("Registration successful!\n");
    }

    public static void authenticateUser(Scanner scanner) {
        System.out.println("Please log in to continue.");
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine().trim();

            if (userCredentials.containsKey(enteredUsername) && userCredentials.get(enteredUsername).equals(enteredPassword)) {
                System.out.println("Login successful!\n");
                return;
            } else {
                attempts--;
                System.out.println("Incorrect username or password. Attempts left: " + attempts);
            }
        }

        System.out.println("Too many unsuccessful attempts. Exiting program.");
        System.exit(0);
    }

}

