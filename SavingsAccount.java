package raw;

// inheritance
class SavingsAccount extends Account {
    private double INTEREST_RATE;

    // class constructor
    public SavingsAccount(double balance) {
        super(balance);
    }
    // setter method
    public void setInterestRate(double INTEREST_RATE){
        this.INTEREST_RATE = INTEREST_RATE;
    }
    // getter method
    public double getInterestRate(){
        return INTEREST_RATE;
    }

    @Override //polymorphism with abstract class implementation
    public void withdraw(double amount) {
        // bisa kosong
        if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
        }
    }

    public void addInterest() {
        balance += balance * INTEREST_RATE;
    }
}
