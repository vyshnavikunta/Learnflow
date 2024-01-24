import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Employee {
    private int employeeId;
    private String name;
    private double hourlyRate;
    private double totalHoursWorked;
    private double totalSalary;
    private List<Integer> workingDays;

    public Employee(int employeeId, String name, double hourlyRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.totalHoursWorked = 0;
        this.totalSalary = 0;
        this.workingDays = new ArrayList<>();
    }

    public void addWorkingDay(int day) {
        workingDays.add(day);
        totalHoursWorked += 8; // Assuming 8 hours of work per day
    }

    public double calculateSalary() {
        totalSalary = totalHoursWorked * hourlyRate;
        return totalSalary;
    }

    public String getName() {
        return name;
    }

    public double getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public int getEmployeeId() {
        return employeeId;
    }
}

class PayrollSystem {
    private Map<Integer, Employee> employees;

    public PayrollSystem() {
        this.employees = new HashMap<>();
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.getEmployeeId(), employee);
    }

    public void processPayslip(int employeeId) {
        Employee employee = employees.get(employeeId);
        if (employee != null) {
            double salary = employee.calculateSalary();

            // Generating payslip
            System.out.println("Payslip for Employee ID: " + employeeId);
            System.out.println("Employee Name: " + employee.getName());
            System.out.println("Total Hours Worked: " + employee.getTotalHoursWorked());
            System.out.println("Total Salary: " + salary);
        } else {
            System.out.println("Employee not found.");
        }
    }
}

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();

        Employee emp1 = new Employee(1, "John Doe", 20.0);
        emp1.addWorkingDay(1); // Example: Employee worked on day 1
        emp1.addWorkingDay(3); // Employee worked on day 3

        payrollSystem.addEmployee(emp1);

        payrollSystem.processPayslip(1); // Generate payslip for Employee ID 1
    }
}
