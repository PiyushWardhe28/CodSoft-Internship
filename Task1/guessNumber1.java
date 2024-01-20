package Task1;

import java.util.*;
// number game in java
public class guessNumber1 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int lowerL = 1; // Lower Limit
        int upperL = 100; // Upper Limit
        int Attempts = 10; // Number of attempts you have
        int rounds = 0;  // it count the number of rounds you play
        int totalAttempt = 0;
        int totalRoundsWon = 0;
        System.out.println();
        System.out.println("************ Welcome to the Number Guessing Game! ************");

        boolean playAgain;
        do {
            int generatedNumber = random.nextInt(upperL - lowerL + 1) + lowerL;
            int userGuess;
            int attempts = 0;
            boolean roundWon = false;

            System.out.println("\nRound " + (rounds + 1)); // Our number of rounds will starts from 1 always
            System.out.println("I have generated a number between " + lowerL + " and " + upperL + ". You have " + Attempts + " attempts to guess it.");

            do {
                System.out.print("Enter your guess: "); // give your guess
                userGuess = sc.nextInt(); // take guess from user
                attempts++; // increament the guess

                if (userGuess == generatedNumber) { // condition for check whether your guess match with the generated number or not
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts."); // if yes
                    roundWon = true; // make roundwon as true
                } else if (userGuess < generatedNumber) { // if guess number is less than the generated number
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

            } while (!roundWon && attempts < Attempts); // run while loop till roundWon and attempts not become less than Attempts

            totalAttempt = totalAttempt + attempts;
            if (roundWon) {
                totalRoundsWon++; // increament the totalRoundsWon
            }

            rounds++;

            System.out.print("Do you want to play again? (yes/no): "); // Choice whether user want to play again or not
            String playAgainInput = sc.next().toLowerCase();
            playAgain = playAgainInput.equals("yes");

        } while (playAgain);

        System.out.println("\nGame Over!");
        System.out.println("Total rounds played: " + rounds); // display total number of rounds you played
        System.out.println("Total rounds won: " + totalRoundsWon); // display total number of rounds you won
        System.out.println("Total attempts: " + totalAttempt); // it's the total of attempts in how many attempts you guess the number correct
        System.out.println("Average attempts per round: " + (double) totalAttempt / rounds); // avegrage of your game

        sc.close();
    }
}
