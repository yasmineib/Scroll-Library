package project.UI;

import project.database.SelectRecords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Preview {

    public void previewFile(String filename) {
        if (filename.equals("")) {
//            filePath = "src/main/resources/bintest.txt"; // Replace with the actual file path.
            filename = "Library of Agility/bintest.txt";
        }

        String filePath = "src/main/resources/Library of Agility/" + filename;
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Unable to preview the contents of this scroll. Please run again to try again.");
            }
        }
    }
