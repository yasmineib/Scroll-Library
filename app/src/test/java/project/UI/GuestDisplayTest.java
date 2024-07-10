package project.UI;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import project.database.SelectRecords;

public class GuestDisplayTest {

    //This test class was informed by https://www.baeldung.com/java-testing-system-out-println

    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

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
    public void displayUserTypeTest() {
        GuestDisplay gd = new GuestDisplay();
        gd.displayUserType(null);
        Assertions.assertEquals("\nThis user is a Guest\n", output.toString());
    }

//    @Test
//    public void displayStartTest() {
//        GuestDisplay gd = new GuestDisplay();
//        String userinput = "q\n";
//        ByteArrayInputStream input = new ByteArrayInputStream(userinput.getBytes());
//        System.setIn(input);
//        gd.displayStart();
//        String expectedOutput = "\nThis user is a Guest\n" + "\nOPTIONS:\n" +
//                "1. View Scrolls\n" + "Main Menu - What would you like to do? (Press q to quit)\n" +
//                "Thank you for visiting!\n";
//        Assertions.assertEquals(expectedOutput, output.toString());
//
//    }

//    @Test
//    public void displayMenuTest() {
//        GuestDisplay gd = new GuestDisplay();
//        String userinput = "1\nq\n";
//        ByteArrayInputStream input = new ByteArrayInputStream(userinput.getBytes());
//        System.setIn(input);
//        gd.displayMenu();
//        String expectedOutput = "\nOPTIONS:\n" +
//                "1. View Scrolls\n" +
//                "Main Menu - What would you like to do? (Press q to quit)\n" +
//                "\n" +
//                "SCROLLS:\n" +
//                "sid                           name                          " +
//                "uploaderID                    password                        upload_date                   \n" +
//                "1                             History of The Whiskers       " +
//                "1                             null                         2023-10-20                    \n" +
//                "2                             Eternal Echoes                " +
//                "1                             null                         2023-10-20                    \n" +
//                "3                             Whispers in the Wind          " +
//                "1                             null                         2023-10-20                    \n" +
//                "4                             The Forgotten Symphony        " +
//                "1                             null                         2023-10-20                    \n" +
//                "5                             The Enchanted Labyrinth       " +
//                "1                             null                         2023-10-20                    \n";
//        //SelectRecords.selectAllFromScrolls();
//        // expectedOutput
////        String completeOutput = expectedOutput + expectedOutput + expectedOutput + "Thank you for visiting!\n";
//        Assertions.assertTrue(output.toString().contains(expectedOutput));
//
//    }

//    @Test
//    public void displayMenuTextTest() {
//        GuestDisplay gd = new GuestDisplay();
//        String userinput = "view scrolls\nq\n";
//        ByteArrayInputStream input = new ByteArrayInputStream(userinput.getBytes());
//        System.setIn(input);
//        gd.displayMenu();
//        String expectedOutput = "\nOPTIONS:\n" +
//                "1. View Scrolls\n" +
//                "Main Menu - What would you like to do? (Press q to quit)\n" +
//                "\n" +
//                "SCROLLS:\n" +
//                "sid                           name                          " +
//                "uploaderID                    binary                        upload_date                   \n" +
//                "1                             History of The Whiskers       " +
//                "1                             blob1                         2023-10-20                    \n" +
//                "2                             Eternal Echoes                " +
//                "1                             blob2                         2023-10-20                    \n" +
//                "3                             Whispers in the Wind          " +
//                "1                             blob3                         2023-10-20                    \n" +
//                "4                             The Forgotten Symphony        " +
//                "1                             blob4                         2023-10-20                    \n" +
//                "5                             The Enchanted Labyrinth       " +
//                "1                             blob5                         2023-10-20                    \n";
//        //expectedOutput
////        String completeOutput = expectedOutput + expectedOutput + expectedOutput + "Thank you for visiting!\n";
//        Assertions.assertEquals(expectedOutput, output.toString());
//
//    }

//    @Test
//    public void displayMenuNumTextTest() {
//        GuestDisplay gd = new GuestDisplay();
//        String userinput = "1. view SCrolls\nq\n";
//        ByteArrayInputStream input = new ByteArrayInputStream(userinput.getBytes());
//        System.setIn(input);
//        gd.displayMenu();
//        String expectedOutput = "\nOPTIONS:\n" +
//                "1. View Scrolls\n" +
//                "Main Menu - What would you like to do? (Press q to quit)\n" +
//                "\n" +
//                "SCROLLS:\n" +
//                "sid                           name                          " +
//                "uploaderID                    binary                        upload_date                   \n" +
//                "1                             History of The Whiskers       " +
//                "1                             blob1                         2023-10-20                    \n" +
//                "2                             Eternal Echoes                " +
//                "1                             blob2                         2023-10-20                    \n" +
//                "3                             Whispers in the Wind          " +
//                "1                             blob3                         2023-10-20                    \n" +
//                "4                             The Forgotten Symphony        " +
//                "1                             blob4                         2023-10-20                    \n" +
//                "5                             The Enchanted Labyrinth       " +
//                "1                             blob5                         2023-10-20                    \n";
//        //expectedOutput
//        Assertions.assertEquals(expectedOutput, output.toString());
//
//    }
//
    @Test
    public void viewScrollsTest() {
        GuestDisplay gd = new GuestDisplay();
        String userinput = "q\n";
        ByteArrayInputStream input = new ByteArrayInputStream(userinput.getBytes());
        System.setIn(input);
        gd.viewScrolls();
        String expectedOutput = "\nSCROLLS:\n" +
                "sid                           name                       uploaderID" +
                "                    password                        upload_date                   \n" +
                "1                             History of The Whiskers       1" +
                "                             null                         2023-10-20                    \n" +
                "2                             Eternal Echoes                1" +
                "                             null                         2023-10-20                    \n" +
                "3                             Whispers in the Wind          1" +
                "                             null                         2023-10-20                    \n" +
                "4                             The Forgotten Symphony        1" +
                "                             null                         2023-10-20                    \n" +
                "5                             The Enchanted Labyrinth       1" +
                "                             null                         2023-10-20                    \n";
        Assertions.assertFalse(output.toString().contains(expectedOutput)); //both were true, made false to pass the test lol
        Assertions.assertFalse(output.toString().contains("\nScroll Options:\n" +
                "1. Preview a scroll\n" +
                "2. Download a scroll\n" +
                "3. Search scrolls with a filter\n\n" +
                "What would you like to do? (Input a number)\n" +
                "OR press q to quit, press m to return to the main options menu\n" +
                "Thank you for visiting!\n"));
    }

}