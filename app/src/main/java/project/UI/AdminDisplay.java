package project.UI;

import project.UploadHandler;
import org.checkerframework.checker.units.qual.C;
import project.database.*;
import project.database.DeleteRecords;
import project.database.Scroll;
import project.database.SelectRecords;

import java.util.List;
import java.util.Scanner;
import java.util.List;


public class AdminDisplay extends LoggedInDisplay {

    AdminDisplay(int id) {
        super(id);
    }

    public List<Scroll> getScrolls() {
        return scrolls;  // Return the list of scrolls
    }


    @Override
    public void displayUserType(String username) {
        System.out.printf("\n%s is an Admin\n", username);
    }


    @Override
    public void displayMenu(){
        Scanner sc = new Scanner(System.in);
        String continue_viewing;
        String choice;

        while (true) {
            System.out.println();
            System.out.println("OPTIONS:");
            System.out.println("1. Update your profile information\n" +
                    "2. View scrolls\n" +
                    "3. Add/upload a scroll\n" +
                    "4. Edit a scroll\n" +
                    "5. Remove a scroll\n" +
                    "6. View all users (and their profiles)\n" +
                    "7. Add a user\n" +
                    "8. Delete a user\n" +
                    "9. View the number of uploads and downloads for each scroll\n");

            System.out.println("What would you like to do? (Input a number or press q to quit)");

            choice = sc.nextLine();

            if (choice.strip().equalsIgnoreCase("q")) {
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
            } else if (choice.strip().equalsIgnoreCase("6")) {
                viewUsers();
                System.out.println();
                System.out.println("Main Menu - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")){
                    quit();
                    break;
                }
            } else if (choice.strip().equalsIgnoreCase("7")) {
                addUser();
                System.out.println();
                System.out.println("Main Menu - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")){
                    quit();
                    break;
                }
            } else if (choice.strip().equalsIgnoreCase("8")) {
                deleteUser();
                System.out.println();
                System.out.println("Main Menu - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")){
                    quit();
                    break;
                }
            } else if (choice.strip().equalsIgnoreCase("9")) {
                viewStats();
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

    public void viewUsers() {
        SelectRecords.selectAllFromCreds();
    }

    public void addUser() {
        CreateProfile profile = new CreateProfile();
        profile.createProfile();
    }

    public void deleteUser() {
        int id;
        Scanner sc = new Scanner(System.in);
        SelectRecords.selectAllFromCreds();
        while (true) {
            System.out.println("Enter the id of the user you'd like to delete: ");
            id = Integer.parseInt(sc.nextLine());
            if (SelectRecords.doesIdExist(id)) {
                DeleteRecords.deleteFromCreds(id);
                break;
            } else {
                System.out.println("ID does not exist, please try again.");
            }
        }
    }

    public void viewStats() {
        // TODO ask them which table they'd like to view.
        System.out.println("Total downloads table: ");
        SelectRecords.selectAllFromTotalDownloads();
        System.out.println();

        System.out.println("Total uploads/updates table: ");
        SelectRecords.selectAllFromTotalUpdates();
        System.out.println();
    }


}
