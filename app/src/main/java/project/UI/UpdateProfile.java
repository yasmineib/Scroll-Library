package project.UI;

import project.database.*;

import java.util.Scanner;

public class UpdateProfile {
    int id;
    public void updateProfile(){
        menu();
    }
    public void getID(Scanner scanner){
        id = -1;
        while (id == -1) {
            System.out.print("Enter your ID: ");
            String inputed = scanner.nextLine();
            try {
                id = Integer.parseInt(inputed);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a valid integer.");
            }
        }
    }
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        getID(scanner);

        System.out.println("\nWhat would you like to update? ");
        System.out.println("1. Your ID");
        System.out.println("2. Your Username");
        System.out.println("3. Your Password");
        System.out.println("4. Your Personal Details");
        System.out.println("5. Go back to the main menu\n");
        System.out.print("Enter your prefernce: ");

        String input = scanner.nextLine();

        boolean validInput = false;
        while(!validInput) {
            if (input.equals("1")) {
                validInput = true;
                updateID(scanner);
            } else if (input.equals("2")) {
                validInput = true;
                updateUsername(scanner);
            } else if (input.equals("3")) {
                validInput = true;
                updatePassword(scanner);
            } else if (input.equals("4")) {
                validInput = true;
                updatePersonalInfo(scanner);
            } else if (input.equals("5")) {
                break;
            }else {
                System.out.println("Please enter a valid input");
            }
            SelectRecords.selectAllFromCreds();
        }
    }

    void updateID(Scanner scanner){
        int newid = -1;
        while (newid == -1) {
            System.out.println("Enter a new ID: ");
            String idinput = scanner.nextLine();
            try {
                newid = Integer.parseInt(idinput);
                if (newid < 0) {
                    System.out.println("The ID must be positive.");
                    newid = -1;
                }else{
                    boolean isUnique = UpdateRecords.updateCredsID(id, newid);
                    if(!isUnique){
                        System.out.println("This ID already exists in the database. Try again.");
                        newid = -1;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a valid integer.");
            }
        }
        System.out.println("Update Complete!");

    }
    void updateUsername(Scanner scanner){
        boolean isUnique = false;
        String newUsername = null;
        while(!isUnique){
            System.out.print("Enter new username: ");
            newUsername = scanner.nextLine();
            //check if username exist in db
            if(!SelectRecords.doesUsernameExist(newUsername)){
                //its unique (doesnt exist)
                isUnique = true;
            }else {
                System.out.println("This username already exists. Try again.");
            }
        }
        UpdateRecords.updateCredsUsername(id, newUsername);

    }
    void updatePassword(Scanner scanner){
        System.out.println("Enter a new password: ");
        String password = scanner.nextLine();
        UpdateRecords.updateCredsPassword(id, PasswordHashing.hashPassword(password));
    }
    void updatePersonalInfo(Scanner scanner){
        System.out.println("What would you like to update? ");
        System.out.println("1. Your name");
        System.out.println("2. Your phone number");
        System.out.println("3. Your email");
        System.out.print("Enter your preference: ");

        String input = scanner.nextLine();
        boolean validInput = false;
        while(!validInput) {
            if (input.equals("1")) {
                validInput = true;
                updateName();
            } else if (input.equals("2")) {
                validInput = true;
                updatePhoneNum();
            } else if (input.equals("3")) {
                validInput = true;
                updateEmail();
            } else {
                System.out.println("Please enter a valid input");
            }
        }
    }
    void updateName(){
        CreateProfile createProfile = new CreateProfile();
        String name = createProfile.getName();
        UpdateRecords.updateCredsFullName(id, name);
    }
    void updatePhoneNum(){
        CreateProfile createProfile = new CreateProfile();
        int num = createProfile.getPhoneNumber();
        UpdateRecords.updateCredsPhoneNumber(id,num);
    }
    void updateEmail(){
        CreateProfile createProfile = new CreateProfile();
        String email = createProfile.getEmail();
        UpdateRecords.updateCredsEmail(id, email);
    }

}
