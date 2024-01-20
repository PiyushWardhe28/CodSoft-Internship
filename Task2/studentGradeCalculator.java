package Task2;

import java.util.*;

// Student grade calculator

public class studentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("************ Welcome To Student Grade Calculator System ************");
        System.out.println();
        System.out.println();
        // Get the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = sc.nextInt();
        sc.nextLine(); // Consume the newline character
        System.out.println();

        // Initialize variables and make initially 0
        int totalMarks = 0;

        // Get marks for each subject
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter the name of subject " + i + ": ");
            String subjectName = sc.nextLine();

            System.out.print("Enter the marks for " + subjectName + " (out of 100): ");
            int marks = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            System.out.print("You have entered " + marks+" marks for subject: "+ subjectName);
            System.out.println();
            System.out.println();

            // Validate marks (assuming marks are between 0 and 100)
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Marks should be between 0 and 100. Exiting.");
                return;
            }

            // Accumulate total marks
            totalMarks += marks;
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Assign grades based on average percentage
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        // Display results
        System.out.println("\nResults: :");
        System.out.println("Total Marks obtained: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
        if(grade=='A'&& grade == 'B' && grade == 'C' && grade == 'D'){
            System.out.println("Congratulations for securing grade: "+grade);
        } else{
            System.out.println("Opps sorry :( but you did your level best!");
            System.out.println("Remember one thing marks are just a numbers but learning is life long so work on your skills...");
        }

        // Close the scanner
        sc.close();
    }
}