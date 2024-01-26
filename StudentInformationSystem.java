import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    String firstName;
    String lastName;
    int rollNumber;
    Map<String, String> coursesGrades;

    public Student(String firstName, String lastName, int rollNumber, Map<String, String> coursesGrades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNumber = rollNumber;
        this.coursesGrades = coursesGrades;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void printStudentDetails() {
        System.out.println("Student Details:");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Courses and Grades:");
        System.out.println("Course \t\t Grade");
        for (Map.Entry<String, String> entry : coursesGrades.entrySet()) {
            System.out.println(entry.getKey() + " \t\t " + entry.getValue());
        }
        System.out.println();
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter Roll Number:");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check for uniqueness of roll number
        if (isRollNumberUnique(rollNumber)) {
            System.out.println("Enter Courses and Grades (comma-separated, e.g., Math:A,English:B,Science:C):");
            String[] coursesGradesArray = scanner.nextLine().split(",");
            Map<String, String> coursesGrades = new HashMap<>();
            for (String courseGrade : coursesGradesArray) {
                String[] parts = courseGrade.split(":");
                coursesGrades.put(parts[0].trim(), parts[1].trim());
            }

            Student newStudent = new Student(firstName, lastName, rollNumber, coursesGrades);
            students.add(newStudent);
            System.out.println("Student added successfully!\n");
        } else {
            System.out.println("Roll number already exists. Please enter a unique roll number.\n");
        }
    }

    private boolean isRollNumberUnique(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return false;
            }
        }
        return true;
    }

    public void findStudentByRollNumber(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                student.printStudentDetails();
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.\n");
    }

    public void generateTranscript(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Transcript for Student with Roll Number " + rollNumber + ":");
                student.printStudentDetails();
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.\n");
    }

    public void displayTotalStudents() {
        System.out.println("Total number of students: " + students.size() + "\n");
    }

    public void deleteStudent(int rollNumber) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNumber() == rollNumber) {
                students.remove(i);
                System.out.println("Student with Roll Number " + rollNumber + " deleted successfully!\n");
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found. Deletion failed.\n");
    }

    public void updateStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Select field to update:");
                System.out.println("1. First Name");
                System.out.println("2. Last Name");
                System.out.println("3. Courses and Grades");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter new First Name:");
                        student.firstName = scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Enter new Last Name:");
                        student.lastName = scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("Enter new Courses and Grades (comma-separated):");
                        String[] coursesGradesArray = scanner.nextLine().split(",");
                        student.coursesGrades.clear();
                        for (String courseGrade : coursesGradesArray) {
                            String[] parts = courseGrade.split(":");
                            student.coursesGrades.put(parts[0].trim(), parts[1].trim());
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. No fields updated.");
                        break;
                }

                System.out.println("Student details updated successfully!\n");
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found. Update failed.\n");
    }
}

public class StudentInformationSystem {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Student Information Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Find Student by Roll Number");
            System.out.println("3. Generate Transcript");
            System.out.println("4. Display Total Students");
            System.out.println("5. Delete Student");
            System.out.println("6. Update Student");
            System.out.println("7. Exit");
            System.out.println("Enter your choice:");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    sms.addStudent();
                    break;
                case 2:
                    System.out.println("Enter Roll Number:");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    sms.findStudentByRollNumber(rollNumber);
                    break;
                case 3:
                    System.out.println("Enter Roll Number to generate transcript:");
                    int rollNumberForTranscript = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    sms.generateTranscript(rollNumberForTranscript);
                    break;
                case 4:
                    sms.displayTotalStudents();
                    break;
                case 5:
                    System.out.println("Enter Roll Number to delete:");
                    int rollNumToDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    sms.deleteStudent(rollNumToDelete);
                    break;
                case 6:
                    System.out.println("Enter Roll Number to update:");
                    int rollNumToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    sms.updateStudent(rollNumToUpdate);
                    break;
                case 7:
                    System.out.println("Exiting Program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 7);
        scanner.close();
    }
}
