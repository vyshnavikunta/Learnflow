import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeePayrollSystem {
    private static Map<Integer, Employee> employeeDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. HR Login");
            System.out.println("2. View Employee Details");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter HRLogin username:");
                    String username = scanner.next();
                    System.out.println("Enter HRLogin password:");
                    String password = scanner.next();
                    if (username.equals("admin") && password.equals("admin")) {
                        hrLogin();
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;
                case 2:
                    viewEmployeeDetails();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void hrLogin() {
        boolean a = true;
        while (a) {
            System.out.println("HR Login");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. View Employee Details");
            System.out.println("5. View All Employee Details");
            System.out.println("6. Logout");

            int hrChoice = scanner.nextInt();

            switch (hrChoice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    removeEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    viewEmployeeDetails();
                    break;
                case 5:
                    viewAllEmployeeDetails();
                    break;
                case 6:
                    System.out.println("Exiting HR Login.");
                    a = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addEmployee() {
        System.out.println("Enter Employee ID:");
        int employeeId = scanner.nextInt();

        System.out.println("Enter Employee Name:");
        String employeeName = scanner.next();

        System.out.println("Enter Salary:");
        double salary = scanner.nextDouble();

        System.out.println("Enter Tax Deductions:");
        double taxDeductions = scanner.nextDouble();

        double totalSalary = salary - taxDeductions;

        Employee employee = new Employee(employeeId, employeeName, salary, taxDeductions, totalSalary);
        employeeDatabase.put(employeeId, employee);

        System.out.println("Employee added successfully!");
    }

    private static void removeEmployee() {
        System.out.println("Enter Employee ID to remove:");
        int employeeIdToRemove = scanner.nextInt();

        if (employeeDatabase.containsKey(employeeIdToRemove)) {
            employeeDatabase.remove(employeeIdToRemove);
            System.out.println("Employee removed successfully!");
        } else {
            System.out.println("Employee not found with ID: " + employeeIdToRemove);
        }
    }

    private static void updateEmployee() {
        System.out.println("Enter Employee ID to update:");
        int employeeIdToUpdate = scanner.nextInt();

        if (employeeDatabase.containsKey(employeeIdToUpdate)) {
            System.out.println("Enter new Salary:");
            double newSalary = scanner.nextDouble();

            System.out.println("Enter new Tax Deductions:");
            double newTaxDeductions = scanner.nextDouble();

            double newTotalSalary = newSalary - newTaxDeductions;

            Employee updatedEmployee = employeeDatabase.get(employeeIdToUpdate);
            updatedEmployee.setSalary(newSalary);
            updatedEmployee.setTaxDeductions(newTaxDeductions);
            updatedEmployee.setTotalSalary(newTotalSalary);

            employeeDatabase.put(employeeIdToUpdate, updatedEmployee);

            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Employee not found with ID: " + employeeIdToUpdate);
        }
    }

    private static void viewEmployeeDetails() {
        System.out.println("Enter Employee ID to view details:");
        int employeeIdToView = scanner.nextInt();

        if (employeeDatabase.containsKey(employeeIdToView)) {
            Employee employee = employeeDatabase.get(employeeIdToView);
            System.out.println("Employee Details:");
            System.out.println("ID: " + employee.getEmployeeId());
            System.out.println("Name: " + employee.getEmployeeName());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("Tax Deductions: " + employee.getTaxDeductions());
            System.out.println("Total Salary: " + employee.getTotalSalary());
        } else {
            System.out.println("Employee not found with ID: " + employeeIdToView);
        }
    }

    private static void viewAllEmployeeDetails() {
        System.out.println("All Employee Details:");
        for (Map.Entry<Integer, Employee> entry : employeeDatabase.entrySet()) {
            Employee employee = entry.getValue();
            System.out.println("ID: " + employee.getEmployeeId() +
                    ", Name: " + employee.getEmployeeName() +
                    ", Salary: " + employee.getSalary() +
                    ", Tax Deductions: " + employee.getTaxDeductions() +
                    ", Total Salary: " + employee.getTotalSalary());
        }
    }
}

class Employee {
    private int employeeId;
    private String employeeName;
    private double salary;
    private double taxDeductions;
    private double totalSalary;

    public Employee(int employeeId, String employeeName, double salary, double taxDeductions, double totalSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.taxDeductions = taxDeductions;
        this.totalSalary = totalSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public double getTaxDeductions() {
        return taxDeductions;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setTaxDeductions(double taxDeductions) {
        this.taxDeductions = taxDeductions;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
}
