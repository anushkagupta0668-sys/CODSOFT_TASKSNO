
import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {

        
        // Create Scanner object
        Scanner sc = new Scanner(System.in);

        
        System.out.println(" STUDENT GRADE CALCULATOR");
        

        // Take number of subjects
        System.out.print("Enter the number of subjects: ");
        int subjects = sc.nextInt();

        // Check whether the number of subjects is valid
        while (subjects <= 0) {
            System.out.print("Please enter a valid number of subjects: ");
            subjects = sc.nextInt();
        }

        int totalMarks = 0;

        // Take marks for each subject
        for (int i = 1; i <= subjects; i++) {

            System.out.print("Enter marks for Subject " + i + " (out of 100): ");
            int marks = sc.nextInt();

            // Validate marks
            while (marks < 0 || marks > 100) {
                System.out.print("Invalid marks! Enter marks between 0 and 100: ");
                marks = sc.nextInt();
            }

            totalMarks = totalMarks + marks;
        }

        // Calculate average percentage
        double average = (double) totalMarks / subjects;

        // Calculate grade
        String grade;

        if (average >= 90) {
            grade = "A+";
        }
        else if (average >= 80) {
            grade = "A";
        }
        else if (average >= 70) {
            grade = "B";
        }
        else if (average >= 60) {
            grade = "C";
        }
        else if (average >= 50) {
            grade = "D";
        }
        else {
            grade = "F";
        }

        // Display final result
        
        System.out.println(" STUDENT RESULT");
    

        System.out.println("Total Subjects: " + subjects);

        System.out.println("Total Marks: " + totalMarks + " / " + (subjects * 100));

        System.out.printf("Average Percentage: %.2f%%%n", average);

        System.out.println("Grade: " + grade);

        

        sc.close();
    }
}
