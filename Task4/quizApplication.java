import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText; // Represents the text of the question.
    private List<String> options; // Represents a list of options associated with the question.
    private int correctOptionIndex; // Represents the index of the correct option in the 'options' list.

    public Question(String questionText, List<String> options, int correctOptionIndex) { //Constructor for the Question class.
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText; //Gets the text of the question.
    }

    public List<String> getOptions() {
        return options; //Gets the list of options associated with the question.
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;  // Gets the index of the correct option in the 'options' list.
    }
}

class Quiz {
    private List<Question> questions; //Represents a list of questions for the quiz.
    private int currentQuestionIndex; //Represents the index of the current question being presented in the quiz.
    private int userScore; //Represents the user's score in the quiz.
    private Timer timer; //Represents a timer associated with the quiz.
    private int round; //Represents the current round of the quiz.

    public Quiz(List<Question> questions) { // Constructor for class Quiz
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.userScore = 0;
        this.timer = new Timer();
        this.round = 1;
    }

    public void startQuiz() { //Starts the quiz by either displaying the next question or ending the round if all questions have been answered.
        if (currentQuestionIndex < questions.size()) {    // Check if there are more questions to display
            displayQuestion(); // If there are more questions, display the next question
        } else {
            endRound(); // If all questions have been answered, end the round
        }
    }

    private void displayQuestion() { //Displays the current question to the user, collects their answer, and proceeds to the next question or end the round.
        Question currentQuestion = questions.get(currentQuestionIndex); //Get the current question from the list of questions

        System.out.println("Round " + round + " - " + currentQuestion.getQuestionText()); // Display the question and its options
        List<String> options = currentQuestion.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        startTimer(); // Start a timer for the user to answer

        Scanner sc = new Scanner(System.in);
        System.out.print("Choose an option (1-" + options.size() + "): ");  // Collect the user's choice
        int userChoice = sc.nextInt();

        // Check if it's time for the user to decide whether to continue
        checkAnswer(userChoice - 1);

        currentQuestionIndex++;  // Move to the next question

        if (currentQuestionIndex % 5 == 0) {  // Check if the user's choice is correct
            askToContinue();  // Check if it's time for the user to decide whether to continue or start the next question
        } else {
            startQuiz();
        }
    }

    private void startTimer() {
        int timeLimit = 10; // 10 seconds timer
        TimerTask timerTask = new TimerTask() {
            int timeRemaining = timeLimit;

            @Override
            public void run() {
                if (timeRemaining == 0) {
                    System.out.println("\nTime's up! Moving to the next question.");
                    currentQuestionIndex++;

                    // Check if it's time for the user to decide whether to continue
                    if (currentQuestionIndex % 5 == 0) {
                        askToContinue();
                    } else {
                        startQuiz();
                    }
                } else {
                    System.out.println("Time remaining: " + timeRemaining + " seconds");
                    timeRemaining--;
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000); // Update every 1 second
    }

    private void checkAnswer(int userChoice) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (userChoice == currentQuestion.getCorrectOptionIndex()) {
            System.out.println("Correct!");
            userScore++;
        } else {
            System.out.println("Incorrect. Correct answer: " +
                    (currentQuestion.getCorrectOptionIndex() + 1));
        }
    }

    private void askToContinue() {
        stopTimer(); // Stop the timer before asking to continue

        Scanner sc = new Scanner(System.in);
        System.out.print("\nDo you want to continue to the next round? (yes/no): ");
        String response = sc.nextLine().toLowerCase();

        if ("yes".equals(response)) {
            round++;
            startQuiz();
        } else {
            endRound();
        }
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private void endRound() {
        System.out.println("\nRound " + round + " completed!");
        System.out.println("Your final score for this round: " + userScore + " out of " + questions.size());
        userScore = 0; // Reset score for the next round
        displaySummary();
    }

    private void displaySummary() {
        System.out.println("\nGame Over!");
        System.out.println("Total rounds played: " + (round - 1));
        System.out.println("Your overall score: " + userScore);
    }
}

public class quizApplication {
    public static void main(String[] args) {
        // Sample quiz with 15 questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", List.of("Berlin", "Paris", "Madrid"), 1));
        questions.add(new Question("Which planet is known as the Red Planet?", List.of("Earth", "Mars", "Jupiter"), 1));
        questions.add(new Question("What is the largest mammal?", List.of("Elephant", "Whale", "Giraffe"), 1));
        // ... (add more questions as needed)

        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
