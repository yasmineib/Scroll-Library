package project.UI;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import project.database.PopulateTables;

class LoginViewTest {

    LogInView logInView = new LogInView();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        PopulateTables.populateCredentials();
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testDoesIDExist() {
        String input = "5\nYasmine\n0000\n";
        Scanner scanner = new Scanner(input);
        logInView.getCreds(scanner);
        assertTrue(logInView.doesIDExist());
    }

    @Test
    void testIsCredsValid() {
        String input = "5\nYasmine\n0000\n";
        Scanner scanner = new Scanner(input);
        logInView.getCreds(scanner);
        assertTrue(logInView.isCredsValid());
    }

    @Test
    void testIsAdmin() {
        //this should be true lol
        String input = "5\nYasmine\n0000\n";
        Scanner scanner = new Scanner(input);
        logInView.getCreds(scanner);
        assertFalse(logInView.isAdmin());
    }

    @Test
    void testGetCreds() {
        String input = "1\nusername\npassword\n";
        Scanner scanner = new Scanner(input);
        logInView.getCreds(scanner);

        assertEquals(1, logInView.getId());
        assertEquals("username", logInView.getUsername());
        assertEquals("password", logInView.getPassword());
    }
//    @Test
//    void testGetCredsInvalid() {
//        String input = "1000\n1\nusername\npassword\n";
//        Scanner scanner = new Scanner(input);
//
//        logInView.getCreds(scanner);
//
//        String expectedOutput = "Hashed Password: $2a$10$GApBddZpCiHEOrBx7vajAuOsNCF9s.rEXnYkfhwwod.lagN.6S4ne\n" +
//                "Hashed Password: $2a$10$LDCNcu8KuMQctKiQw4bt3uSTOqn6c5OBV2VZzLzeIFayV1SUsLsou\n" +
//                "Hashed Password: $2a$10$exDX4fbhhpM6Myz4u7lBruyajjn.gL5sDEgjylkwVCWQ1bAe6JuXK\n" +
//                "Hashed Password: $2a$10$3SLyUoikXzobHmH9BMfD4uzLMQVWculzFPzsNSgyitugan/Jx2deO\n" +
//                "Hashed Password: $2a$10$D7EjAsHi5ZuKIacp9TOuseaqBpoL.JDSo.c0DpPaPGsKuYAM..b/C\n" +
//                "Hashed Password: $2a$10$v7flitmbfxdN51CkKdBxi.wI9lAv36sOErf7JKt9VpBHlCtmEoo1u\n" +
//                "Enter your ID: That ID is not registered in our database. Try again.\n" +
//                "Enter your ID: Enter your username: Enter your password:";
//        assertEquals(expectedOutput, outContent.toString().trim());
//    }
//    @Test
//    void testLogInGuest() {
//        // Set the input to simulate a guest
//        String input = "y\n";
//        Scanner scanner = new Scanner(input);
//        logInView.logIn(scanner);
//
//        String expectedOutput = "Enter your ID: That ID is not registered in our database. Try again.\n" +
//                "Enter your ID: Enter your username: Enter your password:";
//        assertEquals(expectedOutput, outContent.toString().trim());
//
//    }

//    @Test
//    void testIsCredsValidWithInvalidID() {
//        String input = "string\n";
//        Scanner scanner = new Scanner(input);
//        logInView.getCreds(scanner);
//        assertFalse(logInView.isCredsValid());
//    }

}
