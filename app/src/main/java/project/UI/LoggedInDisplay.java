package project.UI;
import project.DownloadHandler;
import project.UploadHandler;
import project.database.Scroll;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import project.database.*;
import java.util.List; 
import java.util.ArrayList;

import project.database.Scroll;

import java.util.*;

public abstract class LoggedInDisplay extends GuestDisplay {
    int id;
    LoggedInDisplay(int id) {
        this.id = id;
    }
    
    protected List<Scroll> scrolls = new ArrayList<>();
    private int nextScrollId = 1;
    private static UploadHandler uh = new UploadHandler();

    @Override
    public void displayStart() {
        //display user type with username
        String username = SelectRecords.getUsernameByID(id);
        if (username == null) {
            System.out.printf("No username found!");
        }
        else {
            displayUserType(username);
        }
        while (true) {
            displayMenu();
        }
    }

    /**
     * Method overloading to handle default value of viewAllScrolls here.
     */
    public void viewScrolls(){
        viewScrolls(true);
    }

    /**
     * Functionality available when in the viewing scrolls menu.
     * @param viewAllScrolls
     */
    public void viewScrolls(boolean viewAllScrolls) {
        Scanner sc = new Scanner(System.in);
        String continue_viewing;
        String choice;

        while (true) {

            if (viewAllScrolls){
                System.out.println("\nSCROLLS:");
                SelectRecords.selectAllFromScrolls(); //print out all scrolls
            }

            //view should then have preview, download, search options
            System.out.println("\nScroll Options:");
            System.out.println("1. Preview a scroll\n" +
                    "2. Download a scroll\n" +
                    "3. Search scrolls with a filter\n");
            System.out.println("What would you like to do? (Input a number)");
            System.out.println("OR press q to quit, press m to return to the main options menu");

            choice = sc.nextLine();

            if (choice.strip().equalsIgnoreCase("q")) {
                quit();
                break;
            } else if (choice.strip().equalsIgnoreCase("m")) {
                break; //acts as a return
            } else if (choice.strip().equalsIgnoreCase("1")) {
                previewScroll();
                System.out.println();
                System.out.println("Scroll Options - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")) {
                    quit();
                    break;
                }
            } else if (choice.strip().equalsIgnoreCase("2")) {
                downloadScroll();
                System.out.println();
                System.out.println("Scroll Options - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")) {
                    quit();
                    break;
                }
            } else if (choice.strip().equalsIgnoreCase("3")) {
                searchFilter();
                System.out.println();
                System.out.println("Scroll Options - Would you like to continue? (y/n)");
                continue_viewing = sc.nextLine();
                if (continue_viewing.strip().equalsIgnoreCase("n")) {
                    quit();
                    break;
                }
            }
        }
    }

    public void uploadScroll() {
        // Create a new scroll and add it to the collection.
//        Scroll newScroll = new Scroll(nextScrollId, title, description);
//        scrolls.add(newScroll);

        boolean uploadSucceeds = uh.userUpload(this.id);
        if (uploadSucceeds) {
            nextScrollId++;
            System.out.println("Scroll uploaded successfully.");
        }
    }

    public void editScroll() {
//        int id = 0;
        Scanner sc =  new Scanner(System.in);
//        String newTitle = null;
//        String newDescription = null;


        // TODO integrate the password thing once it's done.
        System.out.println("\nEDITING SCROLLS");
        SelectRecords.selectAllFromScrolls();
        System.out.println();
        System.out.println("Which scroll would you like to update?");
        int sid = Integer.parseInt(sc.nextLine());
        if (SelectRecords.doesScrollIDExist(sid)){
            System.out.println(SelectRecords.selectScrollName(sid));
            if (SelectRecords.selectScrollUploaderID(sid) == id) {
                System.out.println("\nWhat would you like to edit/update: ");
                System.out.println("1. Scroll name\n2. Scroll contents");
                int choice = Integer.parseInt(sc.nextLine());
                String new_name;
                String new_content;
                switch (choice) {
                    case 1:
                        System.out.println("\nWhat would you like the new name to be?");
                        new_name = sc.nextLine();
                        UpdateRecords.updateScrollsName(sid, new_name);
                        System.out.println("Scroll name updated successfully!");
                        break;

                    case 2:
                        System.out.println("\nWhat content would you like to input into the scroll?");
                        new_content = sc.nextLine();
                        // TODO finish this.

                        String filePath = "D:/USyd/2nd Year/Semester 2/Units/SOFT2412 - Agile Software Development Practices/Assignments/Assignment 2/Lab03-Hunter-Group04-A2/app/src/main/resources/Library of Agility/"+SelectRecords.selectScrollName(sid);
//                        String content = "Hello, world!";

                        try (FileOutputStream fos = new FileOutputStream(filePath)) {
                            byte[] contentBytes = new_content.getBytes();
                            fos.write(contentBytes);
                            UpdateRecords.updateTotalUploadsNumberByOne(sid, SelectRecords.selectScrollName(sid));
                            System.out.println("Data has been written to the file.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    default:
                        System.out.println("That is not a valid option, updation of scroll failed.");
                }
            } else {
                System.out.println("You cannot edit this scroll, as you are not the scroll owner, updation failed.");
            }
        } else {
            System.out.println("The scroll has not been found, updation failed.");
        }



        //TODO: handle input of which scroll to edit

        // I think everything in this function
        // that I've commented out is very old code and
        // we need to get rid of it:: ~ Mohitha

//        for (Scroll scroll : scrolls) {
//            if (scroll.getId() == id) {
//                // Update the scroll's title and description.
//
//                scroll.setTitle(newTitle);
//                scroll.setDescription(newDescription);
//                System.out.println("Scroll edited successfully.");
//                return;
//            }
//        }
//        System.out.println("Scroll not found. Edit failed.");
    }

    public void removeScroll() {
        int sid = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println();
        SelectRecords.selectAllFromScrolls();
        System.out.println("\nWhich scroll would you like to remove?");
        sid = Integer.parseInt(sc.nextLine());

        // TODO: Password column has not been created in table yet, so after that
        // finish checking for the password before deleting.

        if (SelectRecords.doesScrollIDExist(sid)){
            if (SelectRecords.selectScrollUploaderID(sid) == id) {
                System.out.println(SelectRecords.selectScrollName(sid));
                System.out.println(id);
            if (checkPassword(SelectRecords.selectScrollName(sid))) {
                DeleteRecords.deleteFromScrolls(sid);
                System.out.println("Scroll removed successfully.");
            }
            } else {
                System.out.println("You cannot delete this scroll as you are not the uploader. Removal failed.");
            }
        } else {
            System.out.println("Scroll not found. Removal failed.");
        }

        // TODO make sure you remove the scroll from the downloads and uploads tables as well!!

//        for (Scroll scroll : scrolls) {
//            if (scroll.getId() == id) {
//                if(checkPassword(scroll.getName())){
//                    scrolls.remove(scroll);
//                    System.out.println("Scroll removed successfully.");
//                }
//                return;
//            }
//        }
//        System.out.println("Scroll not found. Removal failed.");
    }

    public void downloadScroll() {
        while (true) {
            System.out.println("Which Scroll would you like to download? (Please input the Scroll's Name): ");
            System.out.println("OR press m to return to the scroll options menu, press q to quit");

            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();

            if (name.equalsIgnoreCase("q")) {
                quit();
                break;
            } else if (name.equalsIgnoreCase("m")) {
                //return to scroll view
                break; //this implicitly returns the method
            } else if (SelectRecords.doesScrollNameExist(name)) {
                if (checkPassword(name)) {
                    DownloadHandler downloadHandler = new DownloadHandler();
                    downloadHandler.downloadFile(name);
                    // TODO get scroll id from name and update the total Downloads file.
                    UpdateRecords.updateTotalDownloadsNumberByOne(SelectRecords.selectSidFromScrollsWithName(name));
                }
                break;
            }
        }
    }



    public void updateProfile() {
        UpdateProfile updateProfile = new UpdateProfile();
        updateProfile.updateProfile();
    }

    public void previewScroll() {
        while (true) {
            System.out.println("Which Scroll would you like to view? (Please input the Scroll's Name): ");
            System.out.println("OR press m to return to the scroll options menu, press q to quit");

            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();

            if (name.equalsIgnoreCase("q")) {
                quit();
                break;
            } else if (name.equalsIgnoreCase("m")) {
                //return to scroll view
                break; //this implicitly returns the method
            } else if (SelectRecords.doesScrollNameExist(name)){
                if(checkPassword(name)){
                    Preview preview = new Preview();
                    preview.previewFile(name);
                }
                break;
            }
        }


//        Map<Integer, String> scrolls = SelectRecords.selectScrollIdsNames();
//        Set<Map.Entry<Integer, String>> scrollSet = scrolls.entrySet();
//        for (Map.Entry<Integer, String> entry : scrollSet) {
//
//        }

    }

    public boolean checkPassword(String name){
        String password = SelectRecords.getScrollPassword(name);

        if(password != null){
            boolean correctPass = false;
            System.out.println("\nThis scroll is password-protected and requires authentication to access.");

            for(int i = 0; i < 3; i++){
                // Give the user three tries
                System.out.print("Enter the password: ");
                Scanner sc = new Scanner(System.in);
                if(sc.nextLine().equals(password)){
                    correctPass = true;
                    return true;
                }
                System.out.println("Incorrect password. Please try again.");
            }

            if(!correctPass){
                System.out.println("Sorry. You've exceeded the maximum number of attempts. Please log in to try again.");
                quit();
            }
        }
        return true;
    }

    public void searchFilter() {
        //TODO: implement searchFilter - take in user input etc..

        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWhat would you like to search scrolls on? ");
        System.out.println("1. Uploader ID");
        System.out.println("2. Scroll ID");
        System.out.println("3. Scroll name");
        System.out.println("4. Scroll upload date");
        choice = Integer.parseInt(sc.nextLine());
        switch (choice){
            case 1:
                System.out.println("\nEnter the uploader id you'd like to search for: ");
                int uid = Integer.parseInt(sc.nextLine());

                // Perhaps check if scroll is there and then send to view scrolls function
                // Otherwise you blahh.

                //TODO fix this. Make it return the rows instead of printing it in the function
                if (SelectRecords.selectRowsWithUploaderID(uid)){
                    viewScrolls(false);
                };
                break;

            case 2:
                System.out.println("\nEnter the scroll id you'd like to search for: ");
                int sid = Integer.parseInt(sc.nextLine());

                // Perhaps check if scroll is there and then send to view scrolls function
                // Otherwise you blahh.

                //TODO fix this. Make it return the rows instead of printing it in the function
                if (SelectRecords.selectRowsWithScrollID(sid)) {
                    viewScrolls(false);
                };
                break;

            case 3:
                System.out.println("\nEnter the string you'd like to search for in the scroll names: ");
                String name = sc.nextLine();

                // Perhaps check if scroll is there and then send to view scrolls function
                // Otherwise you blahh.

                //TODO fix this. Make it return the rows instead of printing it in the function
                if (SelectRecords.selectRowsWithString(name)) {
                    viewScrolls(false);
                };
                break;

            case 4:
                System.out.println("\nWhat upload date would you like to search for?");
                System.out.println("(Must enter in yyyy-mm-dd format!)");
                String date = sc.nextLine().strip();

                //TODO fix this. Make it return the rows instead of printing it in the function
                if (SelectRecords.selectRowsWithUploadDate(date)) {
                    viewScrolls(false);
                };
                break;


            default:
                System.out.println("Invalid choice entered, search filter failed.");
        }



    //    List<Scroll> searchResults = new ArrayList<>();

    //    for (Scroll scroll : scrolls) {
    //        if (scroll.getTitle().toLowerCase().contains(query.toLowerCase()) || scroll.getDescription().toLowerCase().contains(query.toLowerCase())) {
    //            // If the scroll's title or description contains the query, add it to the search results.
    //            searchResults.add(scroll);
    //        }
    //    }

    //    if (searchResults.isEmpty()) {
    //        System.out.println("No results found for the query: " + query);
    //    } else {
    //        System.out.println("Search results:");
    //        for (Scroll result : searchResults) {
    //            System.out.println(result.getTitle() + ": " + result.getDescription());
    //        }
    //    }
    }

    public List<Scroll> getScrolls() {
        return this.scrolls;
    }

}
