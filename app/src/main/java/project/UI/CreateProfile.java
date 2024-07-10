package project.UI;

import project.database.InsertRecords;
import project.database.PasswordHashing;
import project.database.SelectRecords;

import java.util.Scanner;

public class CreateProfile {
    private float phoneNumber;
    private String email;
    private String name;
    private String username;
    private String password;
    private String role;
    private int id;

    //this will make a new profile i.e. populate the creds table with the new user info
    public void createProfile(){
        name = getName();
        email = getEmail();
        phoneNumber = getPhoneNumber();
        role = getRole();
        username = getUsername();
        password = getPassword();
        InsertRecords.insertIntoCreds(username, password, name, email, role, phoneNumber);
        System.out.println("Your account as been created Successfully!");
        System.out.println("Your ID number is: " + getId());
        System.out.println("You can change this number in the 'Update Profile' menu if you like. \n\n\n");

        // Send the new user to their display
        if(role.equals("admin")){
            DisplayManager.displayUserType("admin", getId());

        }else if(role.equals("user")){
            DisplayManager.displayUserType("general", getId());
        }
    }
    
    public int getPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        int phoneNumber = -1; // Initialize to a value indicating an error

        while (phoneNumber == -1) {
            System.out.print("Enter phone number: ");
            String phoneNumberString = scanner.nextLine();

            //the if statement below ensures that the input is only numbers
            if (!phoneNumberString.matches("^\\d+$")) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue; // Continue to the next iteration of the loop
            }

            try {
                phoneNumber = Integer.parseInt(phoneNumberString);
                if (phoneNumber < 0) {
                    System.out.println("Invalid input. Number must be positive.");
                    phoneNumber = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return phoneNumber;
    }

    public String getEmail(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email: ");
        return scanner.nextLine();
    }
    public String getName(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter full name: ");
        name = scanner.nextLine();
        return name;
    }
    public String getRole(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you an admin? (y/n) ");
        if(scanner.nextLine().equalsIgnoreCase("y")){
            role = "admin";
        }else{
            role = "user";
        }
        return role;
    }
    public String getUsername(){
        boolean isUnique = false;
        while(!isUnique){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new username: ");
            username = scanner.nextLine();
            //check if username exist in db
            if(!SelectRecords.doesUsernameExist(username)){
                //its unique (doesnt exist)
                isUnique = true;
            }else {
                System.out.println("This username already exists. Try again.");
            }
        }
        return username;
    }
    public String getPassword(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new password: ");
        password = scanner.nextLine();
        password = PasswordHashing.hashPassword(password);
        return password;
    }

    public void setPhoneNumber(float phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        this.id = SelectRecords.getIDbyusername(username);
        return id;
    }
}