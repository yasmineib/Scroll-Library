package project.UI;

import project.UploadHandler;
import project.database.SelectRecords;

import java.util.Scanner;

public class GeneralUserDisplay extends LoggedInDisplay {

    GeneralUserDisplay(int id) {
        super(id);
    }

    @Override
    public void displayUserType(String username) {
        System.out.printf("\n%s is a General User\n", username);
    }

    @Override
    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        String continue_viewing;
        String choice;

        while (true) {
            System.out.println();
            System.out.println("OPTIONS:");
            System.out.println("1. Update your profile information\n" +
                    "2. View scrolls\n" + //view should then have preview, download, search options
                    "3. Add/upload a scroll\n" +
                    "4. Edit a scroll\n" +
                    "5. Remove a scroll\n");

            System.out.println("What would you like to do? (Input a number or press q to quit)");

            choice = sc.nextLine();

            if (choice.equalsIgnoreCase("q")) {
                quit();
                break;
            } else if (choice.strip().equalsIgnoreCase("1")) {
                updateProfile();
                System.out.println();
                System.out.println("Main Menu - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")){
                    quit();
                    break;
                }
            } else if (choice.strip().equalsIgnoreCase("2")) {
                viewScrolls();
                System.out.println();
                System.out.println("Main Menu - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")){
                    quit();
                    break;
                }
            } else if (choice.strip().equalsIgnoreCase("3")) {
                uploadScroll();
                System.out.println();
                System.out.println("Main Menu - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")){
                    quit();
                    break;
                }
            } else if (choice.strip().equalsIgnoreCase("4")) {
                editScroll();
                System.out.println();
                System.out.println("Main Menu - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")){
                    quit();
                    break;
                }
            } else if (choice.strip().equalsIgnoreCase("5")) {
                removeScroll();
                System.out.println();
                System.out.println("Main Menu - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")){
                    quit();
                    break;
                }
            } else {
                System.out.println("Invalid input, please try again or enter q to quit.");
            }
        }

    }
}
