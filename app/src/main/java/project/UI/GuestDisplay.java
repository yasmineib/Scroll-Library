package project.UI;

import project.database.SelectRecords;

import java.util.Scanner;

public class GuestDisplay implements Display {
    @Override
    public void displayUserType(String username) {
        System.out.println("\nThis user is a Guest");
    }

    @Override
    public void displayStart() {
        displayUserType(null); // no username to be entered since this is a Guest
        while (true) {
            displayMenu();
        }
    }

    @Override
    public void displayMenu() {

        System.out.println("\nOPTIONS:");
        System.out.println("1. View Scrolls");

        System.out.println("Main Menu - What would you like to do? (Press q to quit)");

        boolean inputValid = false;
        Scanner sc = new Scanner(System.in);
        String input;
        int optionNo = 0;

        while (!inputValid && sc.hasNextLine()) {
            input = sc.nextLine().toLowerCase();

            if (input.equals("q")) {
                // quitting program
                quit();
            }
            else if (input.equals("1. view scrolls") || input.equals("view scrolls")) {
                // valid input
                viewScrolls();
                inputValid = true;
            } else {
                try {
                    optionNo = Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("Input is invalid. Input either the option name / its number, or q to quit.");
                    continue; //input invalid
                }

                if (optionNo == 1) {
                    //input valid
                    viewScrolls();
                    inputValid = true;
                }
            }
        }
    }

    @Override
    public void viewScrolls() {
        System.out.println("\nSCROLLS:");
        SelectRecords.selectAllFromScrolls(); //print out all scrolls
    }
}
