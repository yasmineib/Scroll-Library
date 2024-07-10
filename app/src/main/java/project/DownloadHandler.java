package project;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import project.database.InsertRecords;
import project.database.SelectRecords;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class DownloadHandler {
    public void downloadFile(String sourceFile) {
        String sourcePathString = "src/main/resources/Library of Agility/"+sourceFile;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the path where you want to store the downloaded file");
            System.out.println("If you are using MacOS, to get the file in your downloads folder enter \"/Users/YourUsername/Downloads/destinationFile.txt\"");
            System.out.println("If you are using windows, enter \"C:/Users/YourUsername/Downloads/destinationFile.txt\"");
            System.out.println("If you are using Linux, enter \"/home/YourUsername/Downloads/destinationFile.txt\"");
            String destinationPathString = sc.nextLine();

            // Convert the path strings to Path objects
            Path sourcePath = Paths.get(sourcePathString);
            Path destinationPath = Paths.get(destinationPathString);

            try {
                // Copy the file to the destination path
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File copied successfully!");
                updateDownloadRecords(sourceFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void updateDownloadRecords(String name)
        {
            int sid = SelectRecords.selectScrollId(name);
            InsertRecords.insertIntoTotalDownloads(sid, name);
            System.out.println("downloads table updated");
        }

    }
//}



