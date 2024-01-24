import java.util.*;

class BankAccount {
    private String accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds");
            return false;
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}

class Bank {
    private Map<String, BankAccount> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber) {
        BankAccount account = new BankAccount(accountNumber);
        accounts.put(accountNumber, account);
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.getOrDefault(accountNumber, null);
    }

    public void transferFunds(String fromAccount, String toAccount, double amount) {
        BankAccount sender = getAccount(fromAccount);
        BankAccount receiver = getAccount(toAccount);

        if (sender != null && receiver != null) {
            if (sender.withdraw(amount)) {
                receiver.deposit(amount);
                System.out.println("Transfer successful");
            } else {
                System.out.println("Transfer failed");
            }
        } else {
            System.out.println("Invalid account number");
        }
    }
}

public class OnlineBankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        bank.createAccount("12345");
        bank.createAccount("67890");

        System.out.println("Welcome to the Online Banking System!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Funds");
            System.out.println("4. View Account Details");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter account number:");
                    String accNumDeposit = scanner.next();
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = scanner.nextDouble();
                    BankAccount depositAccount = bank.getAccount(accNumDeposit);
                    if (depositAccount != null) {
                        depositAccount.deposit(depositAmount);
                        System.out.println("Deposit successful");
                    } else {
                        System.out.println("Invalid account number");
                    }
                    break;
                case 2:
                    System.out.println("Enter account number:");
                    String accNumWithdraw = scanner.next();
                    System.out.println("Enter amount to withdraw:");
                    double withdrawAmount = scanner.nextDouble();
                    BankAccount withdrawAccount = bank.getAccount(accNumWithdraw);
                    if (withdrawAccount != null) {
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Invalid account number");
                    }
                    break;
                case 3:
                    System.out.println("Enter sender's account number:");
                    String senderAcc = scanner.next();
                    System.out.println("Enter receiver's account number:");
                    String receiverAcc = scanner.next();
                    System.out.println("Enter amount to transfer:");
                    double transferAmount = scanner.nextDouble();
                    bank.transferFunds(senderAcc, receiverAcc, transferAmount);
                    break;
                case 4:
                    System.out.println("Enter account number:");
                    String accNumDetails = scanner.next();
                    BankAccount detailsAccount = bank.getAccount(accNumDetails);
                    if (detailsAccount != null) {
                        detailsAccount.displayAccountDetails();
                        detailsAccount.displayTransactionHistory();
                    } else {
                        System.out.println("Invalid account number");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the Online Banking System!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
