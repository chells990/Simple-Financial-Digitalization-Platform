package raw;

abstract class Account {
    protected double balance;

    // constructor
    public Account(double balance) {
        this.balance = balance;
    }

    //  method
    public void deposit(double amount) {
        balance += amount;
    }

    // abstract method
    public abstract void withdraw(double amount);
}
