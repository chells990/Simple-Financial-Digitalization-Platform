package raw;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String name, username, password;
    private static String platformName;
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<User> users = new ArrayList<>();

    public User(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(){
        this("", "", "");
    }

    public User(String username, String password){
        this("", username, password);
    }

    // can add static
    public String getPlatformName() {
        return platformName;
    }

    // can add static
    public void setPlatName(String platformName) {
        this.platformName = platformName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void receiveCredentials(){
        scanner = new Scanner(System.in);
        System.out.println("\nCreate Account\n--------------------------");
        System.out.print("Enter name: ");
        name = scanner.nextLine();
        System.out.print("Enter new username: ");
        username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String passwordFirstTyped = scanner.nextLine();
        System.out.print("Retype new password: ");
        String passwordRetyped = scanner.nextLine();
        while(!passwordFirstTyped.equals(passwordRetyped)){
            System.out.println("Retyped password does not match! Try again.");
            System.out.print("Enter new password: ");
            passwordFirstTyped = scanner.nextLine();
            System.out.print("Retype new password: ");
            passwordRetyped = scanner.nextLine();
        }
        password = passwordFirstTyped;
    }

    public boolean isValid(){
        boolean isValid = true;
        for (User usr: users){
            if (!(usr.username.equals(username) && usr.password.equals(password)))
                isValid = false;
        }
        return isValid;
    }

    public void createNewUser(){
        receiveCredentials();
        users.add(this);
        System.out.println("User Successfully Created.");
    }
}
