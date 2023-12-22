import java.util.Scanner;
import java.util.Random;

public class BullsAndCowsGame {
    private static final int DIGITS = 4;

    // Function to generate a random four-digit number
    private static int generateRandomNumber() {
        Random rand = new Random();
        int number = 0;
        for (int i = 0; i < DIGITS; ++i) {
            int digit;
            do {
                digit = rand.nextInt(10);
            } while (digit == 0 && i == 0);  // Ensure the first digit is not zero
            number = number * 10 + digit;
        }
        return number;
    }

    // Function to count bulls and cows
    private static void countBullsAndCows(int secretNumber, int guess, int[] bulls, int[] cows) {
        bulls[0] = 0;
        cows[0] = 0;

        int[] secretDigits = new int[DIGITS];
        int[] guessDigits = new int[DIGITS];

        // Parse the numbers into digits
        for (int i = 0; i < DIGITS; ++i) {
            secretDigits[i] = secretNumber % 10;
            guessDigits[i] = guess % 10;
            secretNumber /= 10;
            guess /= 10;
        }

        // Count bulls and cows
        for (int i = 0; i < DIGITS; ++i) {
            if (guessDigits[i] == secretDigits[i]) {
                bulls[0]++;
            } else {
                for (int j = 0; j < DIGITS; ++j) {
                    if (guessDigits[i] == secretDigits[j]) {
                        cows[0]++;
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        int secretNumber = generateRandomNumber();
        int attempts = 0;
        int guess;

        System.out.println("Welcome to the 'Bulls and Cows' game!");
        System.out.println("Try to guess the four-digit number.");

        do {
            // Get the user's guess
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();

            // Check the input
            if (guess < 1000 || guess > 9999) {
                System.out.println("Please enter a four-digit number.");
                continue;
            }

            // Count bulls and cows
            int[] bulls = new int[1];
            int[] cows = new int[1];
            countBullsAndCows(secretNumber, guess, bulls, cows);

            // Display the result
            System.out.printf("Result of attempt: %d bull(s) and %d cow(s)%n", bulls[0], cows[0]);

            // Increase the number of attempts
            attempts++;
        } while (secretNumber != guess);

        // Display the congratulations message
        System.out.printf("Congratulations! You guessed the number %d in %d attempts.%n", secretNumber, attempts);

        scanner.close();
    }
}
