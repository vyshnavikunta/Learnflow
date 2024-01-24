import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EcommercePlatform {
    public static void main(String[] args) {
        // Sample Product Data
        Map<String, Product> productCatalog = initializeProductCatalog();

        // User's Shopping Cart
        Map<String, Integer> shoppingCart = new HashMap<>();

        // Display Product Options
        displayProductOptions(productCatalog);

        // Choose Products and Quantity
        addToCart(shoppingCart, productCatalog);

        // Generate Bill
        generateBill(shoppingCart, productCatalog);
    }

    static class Product {
        String name;
        double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    static Map<String, Product> initializeProductCatalog() {
        Map<String, Product> productCatalog = new HashMap<>();
        productCatalog.put("1", new Product("Product A", 10.99));
        productCatalog.put("2", new Product("Product B", 5.99));
        productCatalog.put("3", new Product("Product C", 8.49));
        productCatalog.put("4", new Product("Product D", 15.99));
        productCatalog.put("5", new Product("Product E", 3.99));
        return productCatalog;
    }

    static void displayProductOptions(Map<String, Product> productCatalog) {
        System.out.println("Product Options:");
        for (Map.Entry<String, Product> entry : productCatalog.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().name + " - $" + entry.getValue().price);
        }
    }

    static void addToCart(Map<String, Integer> shoppingCart, Map<String, Product> productCatalog) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter product number (or 'done' to finish and 'bill' to get the price): ");
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("done") || userInput.equalsIgnoreCase("bill")) {
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
    }

    static void generateBill(Map<String, Integer> shoppingCart, Map<String, Product> productCatalog) {
        System.out.println("\nGenerated Bill:");
        double totalAmount = 0;

        if (shoppingCart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            for (Map.Entry<String, Integer> entry : shoppingCart.entrySet()) {
                String productId = entry.getKey();
                int quantity = entry.getValue();
                Product product = productCatalog.get(productId);
                double itemTotal = quantity * product.price;

                System.out.println(product.name + ": Quantity " + quantity + " - $" + itemTotal);
                totalAmount += itemTotal;
            }

            System.out.println("\nTotal Price in Cart: $" + totalAmount);
        }
    }
}
