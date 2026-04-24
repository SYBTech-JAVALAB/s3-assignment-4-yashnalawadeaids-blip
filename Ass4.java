// Abstract class
abstract class BankAccount {
    protected String accountNumber;
    protected double balance;

    BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    abstract void deposit(double amount);
    abstract void withdraw(double amount);

    void displayBalance() {
        System.out.println("Account " + accountNumber + " balance: ₹" + balance);
    }
}

// Savings Account with ₹1000 minimum balance restriction
class SavingsAccount extends BankAccount {
    private static final double MIN_BALANCE = 1000;

    SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited ₹" + amount + " in Savings Account.");
    }

    @Override
    void withdraw(double amount) {
        if (balance - amount < MIN_BALANCE) {
            System.out.println("Withdrawal denied! Minimum balance of ₹1000 must be maintained.");
        } else {
            balance -= amount;
            System.out.println("Withdrew ₹" + amount + " from Savings Account.");
        }
    }
}

// Current Account with ₹5000 overdraft limit
class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 5000;

    CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited ₹" + amount + " in Current Account.");
    }

    @Override
    void withdraw(double amount) {
        if (balance - amount < -OVERDRAFT_LIMIT) {
            System.out.println("Withdrawal denied! Overdraft limit of ₹5000 exceeded.");
        } else {
            balance -= amount;
            System.out.println("Withdrew ₹" + amount + " from Current Account.");
        }
    }
}

// Demonstration class
public class BankDemo {
    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount("SA1001", 5000);
        CurrentAccount ca = new CurrentAccount("CA2001", 2000);

        System.out.println("\n--- Savings Account Transactions ---");
        sa.displayBalance();
        sa.withdraw(4500); // should fail
        sa.withdraw(3000); // should succeed
        sa.deposit(2000);
        sa.displayBalance();

        System.out.println("\n--- Current Account Transactions ---");
        ca.displayBalance();
        ca.withdraw(6000); // within overdraft limit
        ca.withdraw(2000); // exceeds overdraft limit
        ca.deposit(4000);
        ca.displayBalance();
    }
}
