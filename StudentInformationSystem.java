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
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Courses and Grades:");
        System.out.printf("%-15s%-10s\n", "Course", "Grade");
    
        for (Map.Entry<String, String> entry : coursesGrades.entrySet()) {
            System.out.printf("%-15s%-10s\n", entry.getKey(), entry.getValue());
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
            System.out.println("Enter Courses and Grades\nCourse name:");
            String courseName = scanner.nextLine();
    
            System.out.println("Enter Grade:");
            String grade = scanner.nextLine();
    
            Map<String, String> coursesGrades = new HashMap<>();
            coursesGrades.put(courseName.trim(), grade.trim());
    
            Student newStudent = new Student(firstName, lastName, rollNumber, coursesGrades);
            students.add(newStudent);
            System.out.println("Student added successfully!\n");
        } else {
            System.out.println("Roll number already exists. Please enter a unique roll number.\n");
        }
        // Do not close Scanner(System.in) here
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
        Scanner scanner = new Scanner(System.in);
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                //Scanner scanner = new Scanner(System.in);
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
                        System.out.println("Enter new Courses and Grades (Enter 'done' to finish):");
                        Map<String, String> updatedCoursesGrades = new HashMap<>();
                        
                        while (true) {
                            System.out.println("Enter Course name:");
                            String courseName = scanner.nextLine();
                            
                            if (courseName.equalsIgnoreCase("done")) {
                                break;
                            }
    
                            System.out.println("Enter Grade:");
                            String grade = scanner.nextLine();
                            
                            updatedCoursesGrades.put(courseName.trim(), grade.trim());
                        }
                        
                        student.coursesGrades.clear();
                        student.coursesGrades.putAll(updatedCoursesGrades);
                        break;
                    default:
                        System.out.println("Invalid choice. No fields updated.");
                        break;
                }
    
                System.out.println("Student details updated successfully!\n");
                //scanner.close();
                return;
            }
        }
        scanner.close();
        System.out.println("Student with Roll Number " + rollNumber + " not found. Update failed.\n");    
        
    }
    
}

public class StudentInformationSystem {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("Student Information Management System");
            System.out.println("1. Add Student");
           
            System.out.println("2. Generate Transcript");
            
            System.out.println("3. Delete Student");
            System.out.println("4. Update Student");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");

            //String input ;
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            switch (choice) {
                case 1:
                    sms.addStudent();
                    break;
                
                case 2:
                    System.out.println("Enter Roll Number to generate transcript:");
                    int rollNumberForTranscript = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    sms.generateTranscript(rollNumberForTranscript);
                    break;
               
                case 3:
                    System.out.println("Enter Roll Number to delete:");
                    int rollNumToDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    sms.deleteStudent(rollNumToDelete);
                    break;
                case 4:
                    System.out.println("Enter Roll Number to update:");
                    int rollNumToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    sms.updateStudent(rollNumToUpdate);
                    break;
                case 5:
                    System.out.println("Exiting Program.Best Wishes!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 5);
        scanner.close();
    }
}
