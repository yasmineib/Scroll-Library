package project.UI;
import project.database.Scroll;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;


public class AdminDisplayTest {

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
        AdminDisplay ad = new AdminDisplay(1);
        ad.displayUserType("Mohitha");
        Assertions.assertEquals("\nMohitha is an Admin\n", output.toString());
    }

    @Test
    public void viewScrollsTest() {
        //TODO: Update view scrolls test (same for admin + general user) - loggedindisplaytest maybe?
//        AdminDisplay ad = new AdminDisplay(1);
//        ad.viewScrolls();
//        Assertions.assertEquals("No scrolls available.\n", output.toString());
    }

}
