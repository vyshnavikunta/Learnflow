import java.util.ArrayList;
import java.util.Scanner;

class Account {
    String accountNumber;
    String username;
    String password;
    double balance;
    String phoneNumber;
    ArrayList<String> transactionHistory;

    // Constructor
    public Account(String accountNumber, String username, String password, double balance, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
        this.transactionHistory = new ArrayList<>();
    }
}

public class OnlineBankingSystem {
    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Account loggedInAccount = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Accountant Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    accountantLogin();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private static void accountantLogin() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
    
        // Assuming the admin credentials are "admin" for both username and password
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Accountant Login Successful!");
    
            while (true) {
                System.out.println("1. Add Account");
                System.out.println("2. Remove Account");
                System.out.println("3. Update Account");
                System.out.println("4. View account details");
                System.out.println("5. Logout");
    
                int choice = scanner.nextInt();
    
                switch (choice) {
                    case 1:
                        addAccount();
                        break;
                    case 2:
                        removeAccount();
                        break;
                    case 3:
                        updateAccount();
                        break;
                    case 4:
                        viewAccountDetails();
                        break;
                    case 5:
                        loggedInAccount = null;
                        System.out.println("Accountant Logout Successful!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        } else {
            System.out.println("Invalid credentials. Accountant login failed.");
        }
    }
    

    private static void customerLogin() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Check if the provided username and password match any customer account
        for (Account account : accounts) {
            if (account.username.equals(username) && account.password.equals(password)) {
                loggedInAccount = account;
                System.out.println("Customer Login Successful!");

                while (true) {
                    System.out.println("1. Add Money");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Transfer Money to another account");
                    System.out.println("4. Profile");
                    System.out.println("5. View Balance");
                    System.out.println("6. View Transaction History");
                    System.out.println("7. Logout");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            addMoney();
                            break;
                        case 2:
                            withdraw();
                            break;
                        case 3:
                            transferMoney();
                            break;
                        case 4:
                            viewProfile();
                            break;
                        case 5:
                            viewBalance();
                            break;
                        case 6:
                            viewTransactionHistory();
                            break;
                        case 7:
                            loggedInAccount = null;
                            System.out.println("Customer Logout Successful!");
                            return;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    }
                }
            }
        }

        System.out.println("Invalid credentials. Customer login failed.");
    }

    private static void addAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter balance: ");
        double balance = scanner.nextDouble();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.next();

        Account newAccount = new Account(accountNumber, username, password, balance, phoneNumber);
        accounts.add(newAccount);

        System.out.println("Account added successfully!");
    }

    private static void removeAccount() {
        System.out.print("Enter username of the account to be removed: ");
        String username = scanner.next();

        for (Account account : accounts) {
            if (account.username.equals(username)) {
                accounts.remove(account);
                System.out.println("Account removed successfully!");
                return;
            }
        }

        System.out.println("Account not found with the given username.");
    }

    private static void updateAccount() {
        System.out.print("Enter Account Number of the account to be updated: ");
        String ac = scanner.next();

        for (Account account : accounts) {
            if (account.accountNumber.equals(ac)) {
                System.out.println("Select attribute to update:");
                System.out.println("1. Username");
                System.out.println("2. Password");
                System.out.println("3. Balance");
                System.out.println("4. Phone Number");

                int attributeChoice = scanner.nextInt();

                switch (attributeChoice) {
                    case 1:
                        System.out.print("Enter new username: ");
                        account.username = scanner.next();
                        System.out.println("Username updated successfully!");
                        break;
                    case 2:
                        System.out.print("Enter new password: ");
                        account.password = scanner.next();
                        System.out.println("Password updated successfully!");
                        break;
                    case 3:
                        System.out.print("Enter new balance: ");
                        account.balance = scanner.nextDouble();
                        System.out.println("Balance updated successfully!");
                        break;
                    case 4:
                        System.out.print("Enter new phone number: ");
                        account.phoneNumber = scanner.next();
                        System.out.println("Phone Number updated successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                        break;
                }

                return;
            }
        }

        System.out.println("Account not found with the given username.");
    }

    

    private static void viewAccountDetails() {
        System.out.print("Enter Account Number of the account to view details: ");
        String ac = scanner.next();
    
        for (Account account : accounts) {
            if (account.accountNumber.equals(ac)) {
                System.out.println("Account Details:");
                System.out.println("Account Number: " + account.accountNumber);
                System.out.println("Username: " + account.username);
                System.out.printf("Balance: $%.2f\n", account.balance);
                System.out.println("Phone Number: " + account.phoneNumber);
                return;
            }
        }
    
        System.out.println("Account not found with the given account number.");
    }

    private static void addMoney() {
        if (loggedInAccount != null) {
            System.out.print("Enter the amount to be added: $");
            double amount = scanner.nextDouble();
            loggedInAccount.balance += amount;
            loggedInAccount.transactionHistory.add("Added $" + amount);
            System.out.println("Money added successfully. New balance: $" + loggedInAccount.balance);
        } else {
            System.out.println("No account logged in.");
        }
    }

    private static void withdraw() {
        if (loggedInAccount != null) {
            System.out.print("Enter the amount to be withdrawn: $");
            double amount = scanner.nextDouble();

            if (loggedInAccount.balance >= amount) {
                loggedInAccount.balance -= amount;
                loggedInAccount.transactionHistory.add("Withdrawn $" + amount);
                System.out.println("Money withdrawn successfully. New balance: $" + loggedInAccount.balance);
            } else {
                System.out.println("Insufficient funds. Withdrawal failed.");
            }
        } else {
            System.out.println("No account logged in.");
        }
    }

    private static void transferMoney() {
        if (loggedInAccount != null) {
            System.out.print("Enter the account number of the recipient account: ");
            String recipientAccountNumber = scanner.next();

            Account recipientAccount = null;

            for (Account account : accounts) {
                if (account.accountNumber.equals(recipientAccountNumber)) {
                    recipientAccount = account;
                    break;
                }
            }

            if (recipientAccount != null) {
                System.out.print("Enter the amount to be transferred: $");
                double amount = scanner.nextDouble();

                if (loggedInAccount.balance >= amount) {
                    loggedInAccount.balance -= amount;
                    recipientAccount.balance += amount;
                    loggedInAccount.transactionHistory.add("Transferred $" + amount + " to Account Number " + recipientAccount.accountNumber);
                    recipientAccount.transactionHistory.add("Received $" + amount + " from Account Number " + loggedInAccount.accountNumber);
                    System.out.println("Money transferred successfully. Your new balance: $" + loggedInAccount.balance);
                } else {
                    System.out.println("Insufficient funds. Transfer failed.");
                }
            } else {
                System.out.println("Recipient account not found with the given account number.");
            }
        } else {
            System.out.println("No account logged in.");
        }
    }

    private static void viewProfile() {
        if (loggedInAccount != null) {
            System.out.println("Your Profile:");
            System.out.println("Account Number: " + loggedInAccount.accountNumber);
            System.out.println("Username: " + loggedInAccount.username);
            System.out.printf("Balance: $%.2f\n", loggedInAccount.balance);
            System.out.println("Phone Number: " + loggedInAccount.phoneNumber);
        } else {
            System.out.println("No account logged in.");
        }
    }

    private static void viewBalance() {
        if (loggedInAccount != null) {
            System.out.printf("Your current balance: $%.2f\n", loggedInAccount.balance);
        } else {
            System.out.println("No account logged in.");
        }
    }
    

    private static void viewTransactionHistory() {
        if (loggedInAccount != null) {
            System.out.println("Transaction History for Account Number " + loggedInAccount.accountNumber + ":");
            for (String transaction : loggedInAccount.transactionHistory) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("No account logged in.");
        }
    }
}
