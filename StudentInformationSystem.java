import java.util.*;

class Course {
    private int courseId;
    private String courseName;
    private int creditHours;

    public Course(int courseId, String courseName, int creditHours) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }
}

class Student {
   
    private String name;
    private List<Course> registeredCourses;
    private Map<Course, String> grades;

    public Student(int studentId, String name) {
       
        this.name = name;
        this.registeredCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public void enrollCourse(Course course) {
        registeredCourses.add(course);
    }

    public void assignGrade(Course course, String grade) {
        grades.put(course, grade);
    }

    public void generateTranscript() {
        System.out.println("Transcript for " + name + ":");
        for (Course course : registeredCourses) {
            String grade = grades.getOrDefault(course, "Grade not assigned");
            System.out.println(course.getCourseId() + " - " + course.getCourseName() + ": " + grade);
        }
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }
}

public class StudentInformationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student's ID:");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.println("Enter student's name:");
        String studentName = scanner.nextLine();

        Student student = new Student(studentId, studentName);

        // Course Enrollment
        while (true) {
            System.out.println("Enter course ID (0 to finish):");
            int courseId = scanner.nextInt();
            if (courseId == 0) {
                break;
            }
            scanner.nextLine(); // Consume newline character

            System.out.println("Enter course name:");
            String courseName = scanner.nextLine();

            System.out.println("Enter credit hours:");
            int creditHours = scanner.nextInt();

            Course course = new Course(courseId, courseName, creditHours);
            student.enrollCourse(course);
        }

        // Grade Assignment
        for (Course course : student.getRegisteredCourses()) {
            System.out.println("Enter grade for " + course.getCourseName() + ":");
            String grade = scanner.next();
            student.assignGrade(course, grade);
        }

        // Generating Transcript
        student.generateTranscript();

        scanner.close();
    }
}
