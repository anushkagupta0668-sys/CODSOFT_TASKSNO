
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class QuizApplication {

    public static void main(String[] args) throws InterruptedException {

        // Store quiz questions
        String[] questions = {
            "Which language is used for Android development?",
            "Which keyword is used to create an object in Java?",
            "Which of these is not a primitive data type in Java?",
            "Which method is the starting point of a Java program?",
            "Which data structure follows FIFO?"
        };

        // Store multiple-choice options
        String[][] options = {
            {"A. Java", "B. HTML", "C. CSS", "D. SQL"},
            {"A. class", "B. new", "C. void", "D. static"},
            {"A. int", "B. boolean", "C. String", "D. char"},
            {"A. start()", "B. run()", "C. main()", "D. print()"},
            {"A. Stack", "B. Queue", "C. Tree", "D. Set"}
        };

        // Store correct answers
        char[] correctAnswers = {'A', 'B', 'C', 'C', 'B'};

        int score = 0;
        int correctCount = 0;
        int incorrectCount = 0;
        int unansweredCount = 0;

        int timeLimit = 15;

        // Queue for receiving user answers
        BlockingQueue<String> answerQueue =
                new ArrayBlockingQueue<>(1);

        // Create a separate thread for keyboard input
        Thread inputThread = new Thread(() -> {

            StringBuilder input = new StringBuilder();

            try {
                while (true) {

                    int character = System.in.read();

                    if (character == -1) {
                        break;
                    }

                    if (character == '\n') {

                        String answer = input.toString().trim();

                        answerQueue.offer(answer);

                        input.setLength(0);

                    } else if (character != '\r') {

                        input.append((char) character);

                    }
                }

            } catch (IOException e) {
                System.out.println("Input error occurred.");
            }

        });

        inputThread.setDaemon(true);
        inputThread.start();

        
        System.out.println("JAVA QUIZ");
       

        System.out.println("You have 15 seconds for each question.");
        System.out.println("Enter A, B, C or D.");
        System.out.println("Let's start the quiz!");

        // Display questions one by one
        for (int i = 0; i < questions.length; i++) {

            // Clear old answers
            answerQueue.clear();

            
            System.out.println("Question " + (i + 1));
            System.out.println(questions[i]);

            // Display options
            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.print("Enter your answer: ");
            System.out.flush();

            // Wait for the answer for 15 seconds
            String answer = answerQueue.poll(
                    timeLimit, TimeUnit.SECONDS
            );

            // Check whether time is over
            if (answer == null) {

                System.out.println("\nTime is up!");

                System.out.println(
                    "Correct answer: " + correctAnswers[i]
                );

                unansweredCount++;
                continue;
            }

            answer = answer.toUpperCase();

            // Validate the selected option
            if (answer.length() != 1 ||
                    "ABCD".indexOf(answer.charAt(0)) == -1) {

                System.out.println("Invalid option!");

                System.out.println(
                    "Correct answer: " + correctAnswers[i]
                );

                incorrectCount++;

            } else if (answer.charAt(0) == correctAnswers[i]) {

                System.out.println("Correct answer!");

                score++;
                correctCount++;

            } else {

                System.out.println("Wrong answer!");

                System.out.println(
                    "Correct answer: " + correctAnswers[i]
                );

                incorrectCount++;
            }
        }

        // Display final result
        
        System.out.println("QUIZ RESULT");
       

        System.out.println("Total Questions: " + questions.length);
        System.out.println("Correct Answers: " + correctCount);
        System.out.println("Incorrect Answers: " + incorrectCount);
        System.out.println("Unanswered Questions: " + unansweredCount);

        System.out.println(
            "Final Score: " + score + " / " + questions.length
        );

        double percentage =
                (double) score / questions.length * 100;

        System.out.printf("Percentage: %.2f%%%n", percentage);

        
        System.out.println("Thank you for taking the quiz!");
    }
}
