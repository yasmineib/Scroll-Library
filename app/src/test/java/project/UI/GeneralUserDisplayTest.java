package project.UI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GeneralUserDisplayTest {

    //This test class was based on GuestDisplayTest


    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void displayUserTypeTest() {
        GeneralUserDisplay gu = new GeneralUserDisplay(1); // Pass any integer as a placeholder
        gu.displayUserType("general");
        Assertions.assertEquals("\ngeneral is a General User\n", output.toString());

    }

//    @Test
//    public void displayMenuTest() {
//        GeneralUserDisplay gu = new GeneralUserDisplay(1); // Pass any integer as a placeholder
//        String userinput = "q\n";
//        ByteArrayInputStream input = new ByteArrayInputStream(userinput.getBytes());
//        System.setIn(input);
//        gu.displayMenu();
//        String expectedOutput = "\nOPTIONS:\n" +
//                "1. Update your profile information\n" +
//                "2. View scrolls\n"+
//                "3. Add/upload a scroll\n" +
//                "4. Edit a scroll\n" +
//                "5. Remove a scroll\n" +
//                "What would you like to do? (Input a number or press q to quit)\n" +
//                "\n" +
//                "Thank you for visiting!\n";
//        //SelectRecords.selectAllFromScrolls();
//        // expectedOutput
////        String completeOutput = expectedOutput + expectedOutput + expectedOutput + "Thank you for visiting!\n";
//        Assertions.assertTrue(output.toString().contains(expectedOutput));
//
//    }

//    @Test
//    public void viewScrollsTest() {
//        //TODO: Update view scrolls test (same for admin + general user) - loggedindisplaytest maybe?
//        GeneralUserDisplay gu = new GeneralUserDisplay(1); // Pass any integer as a placeholder
//        String userinput = "q\n";
//        ByteArrayInputStream input = new ByteArrayInputStream(userinput.getBytes());
//        System.setIn(input);
//        gu.viewScrolls();
//        String expectedOutput = "\nSCROLLS:\n" +
//                "sid                           name                       uploaderID" +
//                "                    password                        upload_date                   \n" +
//                "1                             History of The Whiskers       1" +
//                "                             null                         2023-10-20                    \n" +
//                "2                             Eternal Echoes                1" +
//                "                             null                         2023-10-20                    \n" +
//                "3                             Whispers in the Wind          1" +
//                "                             null                         2023-10-20                    \n" +
//                "4                             The Forgotten Symphony        1" +
//                "                             null                         2023-10-20                    \n" +
//                "5                             The Enchanted Labyrinth       1" +
//                "                             null                         2023-10-20                    \n";
//        Assertions.assertTrue(output.toString().contains(expectedOutput));
//        Assertions.assertTrue(output.toString().contains("\nScroll Options:\n" +
//                "1. Preview a scroll\n" +
//                "2. Download a scroll\n" +
//                "3. Search scrolls with a filter\n\n" +
//                "What would you like to do? (Input a number)\n" +
//                "OR press q to quit, press m to return to the main options menu\n" +
//                "Thank you for visiting!\n"));
//    }


}
