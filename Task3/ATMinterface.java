package Task3;

import java.util.*;

// ATM Card Last 4 digits = 1234
// ATM Card pin = 4321
class BankAccount {
    private double balance; // initialize balance variable

    public BankAccount(double initialBalance) {
        this.balance = initialBalance; //assigning the value
    }

    public double getBalance() {
        return balance; // display the balance
    }

    public void deposit(double amount) {
        if (amount > 0) { // condition for check whether entered amount is greater than 0 or not
            balance = balance + amount; // adding the entered amount to initial amount
            System.out.println(amount + "0rs Deposit successful. Current balance: " + balance + "0rs");
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) { // check whether entered amount is greater than 0 and less than or equal to the current balance
            balance = balance - amount; // subtract the balance from the total amount
            System.out.println(amount + "0rs" + " Withdrawal successful. Current balance: " + balance + "0rs");
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        } else {
            System.out.println("Insufficient funds. Withdrawal not allowed.");
        }
    }
}

class ATMMachine {
    private BankAccount userAccount;
    private final String cardLast4Digits = "1234"; // Sample last 4 digits of ATM card
    private final String pin = "4321"; // Sample PIN (In a real-world scenario, use secure methods)

    public ATMMachine(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public boolean authenticateUser() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter ATM card last 4 digits: ");
        String enteredCardLast4Digits = sc.nextLine();

        System.out.print("Enter PIN: ");
        String enteredPIN = sc.nextLine();

        return cardLast4Digits.equals(enteredCardLast4Digits) && pin.equals(enteredPIN);
    }

    public void displayMenu() {
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    public void performTransaction(int option, Scanner sc) {
        if (option != 4 && !authenticateUser()) {
            System.out.println("Authentication failed. Exiting...");
            System.exit(0);
        }

        switch (option) {
            case 1:
                System.out.println("Current Balance: " + userAccount.getBalance() + "0rs");
                break;
            case 2:
                System.out.print("Enter deposit amount: ");
                double depositAmount = sc.nextDouble();
                userAccount.deposit(depositAmount);
                break;
            case 3:
                System.out.print("Enter withdrawal amount: ");
                double withdrawalAmount = sc.nextDouble();
                userAccount.withdraw(withdrawalAmount);
                break;
            case 4:
                System.out.println("Exiting ATM. Thanks for using our ATM services!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }
}

public class ATMinterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter initial account balance: ");
        double initialBalance = sc.nextDouble();

        BankAccount userAccount = new BankAccount(initialBalance);
        ATMMachine atmMachine = new ATMMachine(userAccount);

        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.print("Insert your ATM card to continue... ");
            if (atmMachine.authenticateUser()) {
                System.out.println("Authentication successful!");
                break;
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        }

        while (true) {
            atmMachine.displayMenu();

            int option = sc.nextInt();
            atmMachine.performTransaction(option, sc);
        }
    }
}
