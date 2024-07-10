package project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.database.DeleteRecords;
import project.database.SelectRecords;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UploadHandlerTest {

    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
//    private final DeleteRecords dr = new DeleteRecords();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }
    @Test
    public void uploadFileSuccessTest() {
        UploadHandler uh = new UploadHandler();
        uh.uploadFile(Paths.get("src/main/resources/My Computer/Upload Test"), "Success Upload Test");
        Assertions.assertEquals("File has been uploaded successfully!\n", output.toString());
        output.reset();

        String uploadedPathString = "src/main/resources/Library of Agility/Success Upload Test";

        try {
            Files.deleteIfExists(Paths.get(uploadedPathString));
        } catch (IOException e) {
            System.out.println("The success test for uploading has FAILED.");
            throw new RuntimeException(e);
        }
        Map<Integer, String> scrolls = SelectRecords.selectScrollIdsNames();
        if (scrolls.containsValue(uploadedPathString)) {
            Set<Map.Entry<Integer, String>> indexSet = scrolls.entrySet();
            for (Map.Entry<Integer, String> entry : indexSet) {
                if (entry.getValue().equals(uploadedPathString)) {
                    //remove the scroll with that id from the scrolls table
                    int sid = entry.getKey();
                    DeleteRecords.deleteFromScrolls(sid);
                }
            }

        }


    }

    @Test
    public void uploadFileFailTest() {
        UploadHandler uh = new UploadHandler();
        uh.uploadFile(Paths.get("src/main/resources/My Computer/Nonexistent"), "Fail Upload Test");
        Assertions.assertEquals("Sorry, the file you tried to upload is not valid.\n", output.toString());
        output.reset();
    }

    @Test
    public void userUploadSuccessTest() {
        UploadHandler uh = new UploadHandler();
        String userinput = "src/main/resources/My Computer/Upload Test\nSuccessful Upload\nn\n";
        ByteArrayInputStream input = new ByteArrayInputStream(userinput.getBytes());
        System.setIn(input);
        uh.userUpload(6);

        Assertions.assertTrue(output.toString().contains("\nUPLOAD PAGE\n" +
                "Enter the path of the file you want to upload\n" +
                "\tIf you are using MacOS, enter \"/Users/<YourUsername>/<FilePath>/destinationFile.txt\"\n" +
                "\tIf you are using windows, enter \"C:/Users/<YourUsername>/<FilePath>/destinationFile.txt\"\n" +
                "\tIf you are using Linux, enter \"/home/<YourUsername>/<FilePath>/destinationFile.txt\"\n" +
                "\tReplace <YourUsername> with your local computer username\n" +
                "\tReplace <FilePath> with the filepath to the folder your file is in (e.g. Downloads)\n" +
                "OR if you do not want to upload a file, press m to return to the options menu.\n" +
                "Please enter a unique name to upload the file with (e.g. uploaded_file.txt):\n" +
                "Would you like to set a password for this scroll?(y/n): "
        ));

//        uh.uploadFile(Paths.get("src/main/resources/bintest.txt"), "uploadTest.txt");
//        Assertions.assertEquals("File has been uploaded successfully!\n", output.toString());
//
//        uh.uploadFile(Paths.get("src/main/resources/nonexistent.txt"), "failUploadTest.txt");
//        Assertions.assertEquals("Sorry, the file you tried to upload is not valid.\n", output.toString());

//        System.setIn(System.in);
    }

    @Test
    public void userUploadFailTest() {
        UploadHandler uh = new UploadHandler();
        String userinput = "src/main/resources/My Computer/nonexistent.txt\nsrc/main/resources/bintest.txt\nfail.txt\n";
        ByteArrayInputStream input = new ByteArrayInputStream(userinput.getBytes());
        System.setIn(input);
        uh.userUpload(6);


        Assertions.assertTrue(output.toString().contains("\nUPLOAD PAGE\n" +
                "Enter the path of the file you want to upload\n" +
                "\tIf you are using MacOS, enter \"/Users/<YourUsername>/<FilePath>/destinationFile.txt\"\n" +
                "\tIf you are using windows, enter \"C:/Users/<YourUsername>/<FilePath>/destinationFile.txt\"\n" +
                "\tIf you are using Linux, enter \"/home/<YourUsername>/<FilePath>/destinationFile.txt\"\n" +
                "\tReplace <YourUsername> with your local computer username\n" +
                "\tReplace <FilePath> with the filepath to the folder your file is in (e.g. Downloads)\n" +
                "OR if you do not want to upload a file, press m to return to the options menu.\n" +
                "Sorry, that file path is not valid. " +
                "Please try again (press m to return to options menu):\n"
        ));

//        uh.uploadFile(Paths.get("src/main/resources/bintest.txt"), "uploadTest.txt");
//        Assertions.assertEquals("File has been uploaded successfully!\n", output.toString());
//
//        uh.uploadFile(Paths.get("src/main/resources/nonexistent.txt"), "failUploadTest.txt");
//        Assertions.assertEquals("Sorry, the file you tried to upload is not valid.\n", output.toString());

    }
}