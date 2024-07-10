package project;

import project.database.InsertRecords;
import project.database.SelectRecords;

import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.Scanner;

public class UploadHandler {


    //This method assumes that the path given is a valid, existing file.
    //Should only really be used within userUpload() function.
    public static boolean uploadFile(Path uploadPath, String filename) {
        //returns true on success, false on failure
        //Some parts based on DownloadHandler

        //The destination of the file (in program storage)
        String destinationPathString = "src/main/resources/Library of Agility/" + filename;
//        System.out.println(destinationPathString);
        // Convert the path strings to Path objects
        Path destinationPath = Paths.get(destinationPathString);


        try {
            //Copy the file to the destination path
            Files.copy(uploadPath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File has been uploaded successfully!");
            return true;
        } catch (NoSuchFileException e) {
            System.out.println("Sorry, the file you tried to upload is not valid.");
            return false;
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
            return false;
        }

    }

    public boolean userUpload(int userID) {
        //returns true if upload succeeds, false if fails or is aborted voluntarily.
        System.out.println("\nUPLOAD PAGE");
        System.out.println("Enter the path of the file you want to upload");
        System.out.println("\tIf you are using MacOS, enter \"/Users/<YourUsername>/<FilePath>/destinationFile.txt\"");
        System.out.println("\tIf you are using windows, enter \"C:/Users/<YourUsername>/<FilePath>/destinationFile.txt\"");
        System.out.println("\tIf you are using Linux, enter \"/home/<YourUsername>/<FilePath>/destinationFile.txt\"");
//        System.out.println("Please give a valid path in the form '/Users/computeruser/<filepath>'");
        System.out.println("\tReplace <YourUsername> with your local computer username");
        System.out.println("\tReplace <FilePath> with the filepath to the folder your file is in (e.g. Downloads)");
        System.out.println("OR if you do not want to upload a file, press m to return to the options menu.");
        Scanner sc = new Scanner(System.in);
        String input;
        String uploadPathString;
        Path uploadPath = null;

        boolean inputValid = false;
        while (!inputValid && sc.hasNextLine()) {
            input = sc.nextLine().strip();
            if (input.equalsIgnoreCase("m")) {
                //return to menu
                return false;
            }
            else {
                uploadPathString = input;
                uploadPath = Paths.get(uploadPathString);

                if (Files.exists(uploadPath)) {
                    inputValid = true;
                }
                else {
                    System.out.println("Sorry, that file path is not valid. " +
                            "Please try again (press m to return to options menu):");
                }

            }
        }

        System.out.println("Please enter a unique name to upload the file with (e.g. uploaded_file.txt):");
        String filename = "Untitled.txt";
        sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            //System.out.println("in has next loop");
            filename = sc.nextLine().strip();
        }
        String password = null;

        System.out.print("Would you like to set a password for this scroll?(y/n): ");
        if(sc.hasNextLine() && sc.nextLine().equalsIgnoreCase("y")){
            boolean isValid = false;
            while(!isValid){
                System.out.print("Enter a password: ");
                password = sc.nextLine();
                if(!password.equals("null")){
                    isValid = true;
                    break;
                }
                System.out.println("That is not a valid password.");
            }

        }
        //Call uploadFile function
        boolean uploadWorks = uploadFile(uploadPath, filename);


        if (uploadWorks) {
            //update scrolls table
//            System.out.println("REMOVE LATER - in uploadWorks?");
            InsertRecords.insertIntoScrolls(filename, userID, password, new Date());
        }

        SelectRecords.selectAllFromScrolls();
        SelectRecords.selectAllFromTotalUpdates();
        SelectRecords.selectAllFromCreds();

        System.out.println(SelectRecords.selectNumberScrolls());

        return uploadWorks;

    }

}
