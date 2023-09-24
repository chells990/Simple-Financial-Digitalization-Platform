package raw;

import java.util.*;

// 5. trying to add GUI

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Warehouse<Account> platform = new Warehouse<>();

    public static void main(String[] args) {
        User user = new User();
        System.out.print("Create your partners name: ");
        String platName = scanner.nextLine();
        user.setPlatName(platName);
        user.createNewUser();
        String username = "", password = "";
        boolean isValid = false;
        while (!isValid){
            System.out.println("\n"  + user.getPlatformName() + " partners Login");
            for(int i=0; i<user.getPlatformName().length()+6; i++){
                System.out.print("-");
            }
            System.out.print("\nEnter username ('0' to CLOSE PROGRAM): ");
            username = scanner.nextLine();
            if (username.equals("0")) {
                System.exit(0);
            }
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            user = new User(username, password);
            isValid = user.isValid();
//          System.out.println(user.getUsername());
//          System.out.println(user.getPassword());
        }
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    investment();
                    break;
                case 5:
                    creditSimulation();
                    break;
                case 6:
                    listAccounts();
                    break;
                case 7:
                    remove();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }




    private static void printMenu() {
        System.out.println("\tFINANCIAL PARTNERS SIMULATION\t");
        System.out.println("1. Create account");
        System.out.println("2. Add Money        --- (Income)");
        System.out.println("3. Take Money       --- (Outcome)");
        System.out.println("4. Passive Income   --- (Investment)"); // invest to saving account
        System.out.println("5. Passive Outcome  --- (Credit/Paylater)");
        System.out.println("6. List accounts");
        System.out.println("7. Remove account");
        System.out.println("8. Quit\n");

        System.out.print("Enter your choice: ");
    }


    private static void createAccount() {
        System.out.println("Creating Account:");
        System.out.print("'1' Daily account\n'2' Savings account\n");
        System.out.print("Input: ");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        if (type == 1) {
            platform.addAccount(new DailyAccount(balance));
        }
        else if (type == 2) {
            platform.addAccount(new SavingsAccount(balance));
        } else {
            System.out.println("Invalid account type");
        }
    }


    private static void deposit() { //uang masuk
        if(platform.getNumAccounts()==0){
            System.out.println("-----Empty-----");
        }
        else{
            System.out.print("Enter account number: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            index-=1;
            try {
                Account account = platform.getAccount(index);
                //DailyAccount accounts = bank.getAccount(DailyAccount.class, index);
                //SavingsAccount accounts = bank.getAccount(SavingsAccount.class, index);
                if (account == null) {
                    System.out.println("Invalid account number");
                } else {
                    System.out.print("Enter amount to deposit: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    account.deposit(amount);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: invalid index. No account found at that position.");
            }
        }
    }


    private static void withdraw() { //uang keluar
        if(platform.getNumAccounts()==0){
            System.out.println("Empty");
            return;
        } else {
            System.out.print("Enter account number: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            index -= 1;
            try {
                DailyAccount accounts = platform.getAccount(DailyAccount.class, index);
                if (accounts == null) {
                    System.out.println("Invalid account type");
                } else {
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    if (accounts == null) {
                        System.out.println("Invalid account number");
                    } else {
                        accounts.withdraw(amount);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: invalid index. No account found at that position.");
            }
        }
    }


    private static void listAccounts() {
        if(platform.getNumAccounts()==0){
            System.out.println("Empty");
            return;
        } else{
            for (int i = 0; i < platform.getNumAccounts(); i++) {
                Account account = platform.getAccount(i);
                if (account instanceof DailyAccount) {
                    System.out.println("Account number " + (i+1) + ":\n  Type      :  Daily account\n  Balance   :  " + account.balance);
                    System.out.println();
                } else if (account instanceof SavingsAccount) {
                    System.out.println("Account number " + (i+1) + ":\n  Type      :  Savings account\n  Balance   :  " + account.balance);
                    System.out.println();
                }
            }
        }
    }


    private static void investment() {
        if(platform.getNumAccounts()==0){
            System.out.println("-----Empty-----");
            return;
        } else {
            char option = ' ';
            do {
                System.out.print("Enter account number: ");
                int index = scanner.nextInt();
                scanner.nextLine();
                index -= 1;
                try {
                    SavingsAccount account = platform.getAccount(SavingsAccount.class, index);
                    if (account == null) {
                        System.out.println("Invalid account type");
                    } else {
                        System.out.print("Enter interest rate: ");
                        double interestRate = scanner.nextDouble();
                        scanner.nextLine();
                        account.setInterestRate(interestRate);
                        double last_balance = account.balance;
                        double profit;
                        account.addInterest();
                        System.out.println("With Interest Rate " + account.getInterestRate());
                        System.out.println("New Balance    :  " + String.format("%.2f", account.balance));
                        System.out.println("Profit         :  " + String.format("%.2f", (profit = account.balance - last_balance)));
                        System.out.println("Monthly profit :  " + String.format("%.5f", (profit / 12)));
                        System.out.println("Try another invest? (Y/N)");
                        String optionS = scanner.nextLine();
                        if (optionS.length() >= 1) option = Character.toUpperCase(optionS.charAt(0));
                        if (option == 'Y') {
                        } else if (option == 'N') break;
                        else System.out.println("Invalid input detected! Try again.");
                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: invalid index. No account found at that position.");
                    return;
                }
            } while (option != 'N');
        }
    }

    private static void creditSimulation() {
        if(platform.getNumAccounts()==0){
            System.out.println("-----Empty-----");
            return;
        } else {
            System.out.print("Enter account number: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            index -= 1;
            try {
                DailyAccount accounts = platform.getAccount(DailyAccount.class, index);
                if (accounts == null) {
                    System.out.println("Invalid account type");
                } else {
                    double last_balance = accounts.balance;
                    System.out.println("Card Limit   = " + accounts.getLimit());
                    System.out.println("User balance = " + last_balance);
                    System.out.print("Enter amount to pay: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    accounts.setAmount(amount);
                    System.out.print("Enter the interest rate: ");
                    double rate = scanner.nextDouble();
                    scanner.nextLine();
                    accounts.setInterestRate(rate);
                    accounts.validate();
                    if (!accounts.validate()) {
                        System.out.println("Transaction out of limit");
                        return;
                    } else {
                        accounts.addInterest();
                        double loss;
                        System.out.println("With Interest Rate " + accounts.getInterestRate());
                        System.out.println("New Balance    :  " + String.format("%.2f", accounts.balance));
                        System.out.println("loss           :  " + String.format("%.2f", loss = (amount * accounts.getInterestRate())));
                        System.out.println("Monthly loss   :  " + String.format("%.3f", Math.abs(loss / 12)));
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: invalid index. No account found at that position.");
            }
        }
    }

    private static void remove(){
        if(platform.getNumAccounts()==0){
            System.out.println("-----Empty-----");
            return;
        } else {
            listAccounts();
            System.out.print("Enter account number to be removed: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            try {
                platform.removeAccount(index - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: invalid index. No account found at that position.");
            }
        }
    }
}