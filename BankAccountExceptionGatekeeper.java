
import java.util.Scanner;
import java.util.InputMismatchException;

// STEP 1: Custom Exception Class
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}

// STEP 2: Bank Account Class (Encapsulation)
class BankAccount {
    private String accountNumber, accountHolder;
    private double balance;

    public BankAccount(String number, String holder, double balance) {
        this.accountNumber = number;
        this.accountHolder = holder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // STEP 3: Deposit Method
    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Invalid deposit!");

        balance += amount;
    }

    // STEP 3: Withdrawal Method
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0)
            throw new IllegalArgumentException("Invalid withdrawal!");

        if (amount > balance)
            throw new InsufficientFundsException("Insufficient funds!");

        balance -= amount;
    }
}

// STEP 4: Main Application and Exception Handling
public class BankAccountExceptionGatekeeper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount acc = new BankAccount(
            "2468", "Jhon Marvin Bajarias", 1000000.00
        );

        System.out.println("=== BANK ACCOUNT EXCEPTION GATEKEEPER ===");
        System.out.println("Account: " + acc.getAccountNumber());
        System.out.println("Holder: " + acc.getAccountHolder());
        System.out.printf("Balance: $%.2f%n", acc.getBalance());

        // Withdrawal Transaction
        try {
            System.out.print("Enter withdrawal: ");
            acc.withdraw(sc.nextDouble());

            System.out.println("Withdrawal successful!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.nextLine();

        } catch (InsufficientFundsException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            System.out.println("[TRANSACTION LOG UPDATED]");
        }

        // Deposit Transaction
        try {
            System.out.print("Enter deposit: ");
            acc.deposit(sc.nextDouble());

            System.out.println("Deposit successful!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.nextLine();

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            System.out.println("[SESSION COMPLETE]");
            System.out.printf("Current Balance: $%.2f%n",
                acc.getBalance());
        }

        sc.close();
    }
}
