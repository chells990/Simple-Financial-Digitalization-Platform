package raw;

// inheritance
class DailyAccount extends Account {
    private double cardLimits = 100000;
    private double rate;
    private double amount;

    // class constructor
    public DailyAccount(double balance) {
        super(balance);
    }

    @Override // polymorphism with abstract class implementation
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
        }
    }

    // setter method
    public void setInterestRate(double rate){
        this.rate = rate;
    }

    // getter method
    public double getInterestRate(){
        return rate;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    // getter method
    public double getAmount(){
        return amount;
    }

    // calculate method
    public void addInterest() {
        cardLimits -= amount;
        amount += amount * rate;
        balance -= amount;
    }

    public boolean validate() {
        if (amount < cardLimits  && amount < balance){
            return true;
        }
        else {
            return false;
        }
    }

    public double getLimit() {
        return cardLimits;
    }
}
