import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> subjectMarks = new ArrayList<>();
        
        System.out.println("==================================================");
        System.out.println("      DECODELABS STUDENT GRADE CALCULATOR         ");
        System.out.println("==================================================");
        
        System.out.print("Enter the number of subjects: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid number of subjects: ");
            scanner.next(); // Clear invalid token
        }
        int totalSubjects = scanner.nextInt();
        
        if (totalSubjects <= 0) {
            System.out.println("Error: Number of subjects must be greater than 0. Exiting.");
            scanner.close();
            return;
        }

        for (int i = 1; i <= totalSubjects; i++) {
            int marks = -1;
            while (true) {
                System.out.print("Enter marks for Subject " + i + " (0-100): ");
                if (scanner.hasNextInt()) {
                    marks = scanner.nextInt();
                    if (marks >= 0 && marks <= 100) {
                        break; // Valid input received
                    } else {
                        System.out.println("⚠️ Rule Violation: Marks must be strictly between 0 and 100.");
                    }
                } else {
                    System.out.println("⚠️ Invalid Input: Please enter an integer value.");
                    scanner.next(); // Consume the invalid token to prevent infinite loop
                }
            }
            subjectMarks.add(marks);
        }

        // Resolving the Scanner Buffer Trap (clearing the trailing newline if any future input is needed)
        scanner.nextLine(); 

        // 2. PROCESSING & TRANSFORMATION PHASE
        int totalMarks = 0;
        for (int marks : subjectMarks) {
            totalMarks += marks;
        }
        
        double averagePercentage = (double) totalMarks / totalSubjects;
        char assignedGrade = calculateGrade(averagePercentage);

        // 3. PRESENTATION PHASE (OUTPUT DISPLAY)
        System.out.println("\n==================================================");
        System.out.println("                PERFORMANCE REPORT                ");
        System.out.println("==================================================");
        System.out.printf("Total Subjects     : %d\n", totalSubjects);
        System.out.printf("Total Marks Earned : %d / %d\n", totalMarks, (totalSubjects * 100));
        System.out.printf("Average Percentage : %.2f%%\n", averagePercentage);
        System.out.println("--------------------------------------------------");
        System.out.printf("Final Assigned Grade: %c\n", assignedGrade);
        System.out.println("==================================================");
        
        scanner.close();
    }

    /**
     * Deterministic Rules Engine Core for Grade Assignment
     */
    private static char calculateGrade(double percentage) {
        if (percentage >= 90.0) {
            return 'A';
        } else if (percentage >= 80.0) {
            return 'B';
        } else if (percentage >= 70.0) {
            return 'C';
        } else if (percentage >= 60.0) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
