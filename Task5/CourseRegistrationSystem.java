package Task5;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    String studentID;
    String name;
    List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    private Map<String, Course> courses;
    private Map<String, Student> students;

    public CourseRegistrationSystem() {
        this.courses = new HashMap<>();
        this.students = new HashMap<>();
    }

    public void addCourse(String code, String title, String description, int capacity, String schedule) {
        Course course = new Course(code, title, description, capacity, schedule);
        courses.put(code, course);
    }

    public void addStudent(String studentID, String name) {
        Student student = new Student(studentID, name);
        students.put(studentID, student);
    }

    public void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (Map.Entry<String, Course> entry : courses.entrySet()) {
            Course course = entry.getValue();
            int availableSlots = course.capacity - studentsRegisteredForCourse(entry.getKey()).size();
            System.out.println("Code: " + course.code + ", Title: " + course.title +
                    ", Capacity: " + course.capacity + ", Available Slots: " + availableSlots);
        }
        System.out.println();
    }

    public void registerStudentForCourse(String studentID, String courseCode) {
        if (courses.containsKey(courseCode) && students.containsKey(studentID)) {
            Course course = courses.get(courseCode);
            Student student = students.get(studentID);

            if (studentsRegisteredForCourse(courseCode).size() < course.capacity) {
                student.registeredCourses.add(courseCode);
                System.out.println("Student " + student.name + " registered for course " + course.title);
            } else {
                System.out.println("Course " + course.title + " is full. Cannot register student " + student.name);
            }
        } else {
            System.out.println("Invalid student ID or course code");
        }
    }

    public void dropStudentFromCourse(String studentID, String courseCode) {
        if (courses.containsKey(courseCode) && students.containsKey(studentID)) {
            Course course = courses.get(courseCode);
            Student student = students.get(studentID);

            if (student.registeredCourses.contains(courseCode)) {
                student.registeredCourses.remove(courseCode);
                System.out.println("Student " + student.name + " dropped from course " + course.title);
            } else {
                System.out.println("Student " + student.name + " is not registered for course " + course.title);
            }
        } else {
            System.out.println("Invalid student ID or course code");
        }
    }

    private List<String> studentsRegisteredForCourse(String courseCode) {
        List<String> registeredStudents = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.registeredCourses.contains(courseCode)) {
                registeredStudents.add(student.studentID);
            }
        }
        return registeredStudents;
    }
    public void showRegisteredStudents() {
        System.out.println("\nRegistered Students:");
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            Student student = entry.getValue();
            System.out.println("Student ID: " + student.studentID + ", Student Name: " + student.name);

            for (String courseCode : student.registeredCourses) {
                Course course = courses.get(courseCode);
                System.out.println("  Course ID: " + course.code + ", Course Name: " + course.title);
            }
            System.out.println();
        }
    }
    public static void main(String args[]){
        String url = "jdbc:mysql://localhost:3306/students";

        String username = "root";
        String password = "APiyush@2528";
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");
        }
        catch(SQLException e){
            System.out.println("Connection failed: "+ e.getMessage());
        }
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Display Available Courses");
            System.out.println("4. Register Student for Course");
            System.out.println("5. Drop Student from Course");
            System.out.println("6. Show Registered Students");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Adding courses
                    System.out.println("\nEnter course details:");
                    System.out.print("Course Code: ");
                    String code = scanner.next();
                    System.out.print("Course Title: ");
                    String title = scanner.next();
                    System.out.print("Course Description: ");
                    String description = scanner.next();
                    System.out.print("Capacity: ");
                    int capacity = scanner.nextInt();
                    System.out.print("Schedule: ");
                    String schedule = scanner.next();

                    registrationSystem.addCourse(code, title, description, capacity, schedule);
                    break;

                case 2:
                    // Adding students
                    System.out.println("\nEnter student details:");
                    System.out.print("Student ID: ");
                    String studentID = scanner.next();
                    System.out.print("Student Name: ");
                    String name = scanner.next();

                    registrationSystem.addStudent(studentID, name);
                    break;

                case 3:
                    // Displaying available courses
                    registrationSystem.displayAvailableCourses();
                    break;

                case 4:
                    // Registering students for courses
                    System.out.println("\nEnter registration details:");
                    System.out.print("Enter student ID: ");
                    studentID = scanner.next();
                    System.out.print("Enter course code: ");
                    code = scanner.next();

                    registrationSystem.registerStudentForCourse(studentID, code);
                    break;

                case 5:
                    // Dropping students from courses
                    System.out.println("\nEnter drop details:");
                    System.out.print("Enter student ID: ");
                    studentID = scanner.next();
                    System.out.print("Enter course code: ");
                    code = scanner.next();

                    registrationSystem.dropStudentFromCourse(studentID, code);
                    break;

                case 6:
                    registrationSystem.showRegisteredStudents();
                    break;

                case 7:
                    // Exit
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 7);

        scanner.close();
    }

}
