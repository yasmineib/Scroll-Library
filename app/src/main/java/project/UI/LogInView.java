package project.UI;

import project.UI.CreateProfile;
import project.database.SelectRecords;
import project.UI.GuestDisplay;
import java.util.ArrayList;
import java.util.Scanner;
import project.database.*;

//
//import static project.UI.DisplayManager.displayUserType;

public class LogInView{

    private String username;
    private String password;
    private int id;
    private ArrayList<Object> data;

    public void main(){
        //for the sake of testing
        Scanner scanner = new Scanner(System.in);
        logIn(scanner);
    }

    public void createProfile(){
        CreateProfile createProfile = new CreateProfile();
        createProfile.createProfile();
    }

    public void logIn(Scanner scanner){

        System.out.print("Hello! Would you like to continue as a guest? (y/n): ");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){

            //return a guest display
            DisplayManager.displayUserType("guest", this.id);

        }
        else {
            System.out.print("Would you like to create a new account? (y/n): ");
            String newAcc = scanner.nextLine();
            if (newAcc.equalsIgnoreCase("y") || newAcc.equalsIgnoreCase("yes")) {
                createProfile();
            } else {
                //they are a registered user
                boolean isCredsValid = false;
                while(!isCredsValid){
                    getCreds(scanner);
                    if (isCredsValid()) {
                        if (isAdmin()) {
                            DisplayManager.displayUserType("admin", this.id);
                        }
                        // Not an admin, return a normal display
                        DisplayManager.displayUserType("general", this.id);
                    }

                    // Creds isnt valid
                    System.out.println("Your credentials are not registered in our database. Try again.");
                }
            }
        }
    }
    public void getCreds(Scanner scanner) {
        boolean validID = false;

        while (!validID) {
            System.out.print("Enter your ID: ");
            try {
                id = Integer.parseInt(scanner.nextLine());

                // Does the ID exist in the database?
                if (doesIDExist()) {
                    validID = true; //break
                } else {
                    System.out.println("That ID is not registered in our database. Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Input must be an integer. Try again.");
            }
        }

        System.out.print("Enter your username: ");
        username = scanner.nextLine();

        System.out.print("Enter your password: ");
        password = scanner.nextLine();
    }

    public boolean doesIDExist(){
        return SelectRecords.doesIdExist(id);
    }

    public boolean isCredsValid(){
        // Logic to ensure the user/pass/ID are stored in the database and are correct
        data = SelectRecords.printCredsByID(id);
//        System.out.println("data: " + data);
        String newly_hashed_password = PasswordHashing.hashPassword(password);
//        System.out.println("newly hashed password: " + newly_hashed_password);
        return data != null && data.contains(username) && data.contains(newly_hashed_password); //true: given creds is valid
    }

    public boolean isAdmin() {
        //logic to check the role of the user.
        return data != null && data.contains("admin");
    }
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public int getId(){return this.id;}
}
