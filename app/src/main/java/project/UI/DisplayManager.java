package project.UI;

import project.database.SelectRecords;

public class DisplayManager {
    
    public static void displayUserType(String role, int id) {
        Display display;
        switch (role) {
            case "guest":
                display = new GuestDisplay();
                break;
            case "general":
                display = new GeneralUserDisplay(id);
                break;
            case "admin":
                display = new AdminDisplay(id);
                break;
            default:
                throw new IllegalArgumentException("Invalid user role");
        }

        display.displayStart();

    }
    
}
