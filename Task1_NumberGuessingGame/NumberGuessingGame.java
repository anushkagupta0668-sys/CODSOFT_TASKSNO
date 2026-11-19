import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {

        // Create Scanner object for user input
        Scanner sc = new Scanner(System.in);

        // Create Random object
        Random random = new Random();

        // Generate a random number between 1 and 100
        int number = random.nextInt(100) + 1;

       
        System.out.println("     NUMBER GUESSING GAME");
       

        System.out.println("I have selected a number between 1 and 100.");
        System.out.println("Can you guess the number?");

        // Take the first guess from the user
        System.out.print("\nEnter your guess: ");
        int guess = sc.nextInt();

        // Repeat until the user guesses correctly
        while (guess != number) {

            if (guess < number) {

                System.out.println("Too low! Try a higher number.");

            } else {

                System.out.println("Too high! Try a lower number.");

            }

            // Ask the user to guess again
            System.out.print("Enter your guess again: ");
            guess = sc.nextInt();
        }

        // Display success message
        System.out.println("\nCongratulations!");
        System.out.println("You guessed the correct number: " + number);

        System.out.println("Thank you for playing!");

        // Close Scanner
        sc.close();
    }
}

