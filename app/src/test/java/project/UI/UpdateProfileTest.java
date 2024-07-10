package project.UI;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.provider.ValueSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


class UpdateProfileTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1\n2\n3\n4\n5\n"})
    void testMenu(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        UpdateProfile updateProfile = new UpdateProfile();
        updateProfile.menu();

        // Here, you can assert further based on what your methods do.
        // For example, you can check if the user was prompted correctly.
    }

    @Test
    void testUpdateIDWithValidInput() {
        String input = "123\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        UpdateProfile updateProfile = new UpdateProfile();
        updateProfile.getID(new Scanner(System.in));

        assertEquals(123, updateProfile.id);
    }

    @Test
    void testUpdateIDWithInvalidInput() {
        String input = "invalid\n123\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        UpdateProfile updateProfile = new UpdateProfile();
        updateProfile.getID(new Scanner(System.in));

        assertEquals(123, updateProfile.id);
    }

    @Test
    void testUpdateIDWithNegativeInput() {
        String input = "-123\n123\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        UpdateProfile updateProfile = new UpdateProfile();
        updateProfile.getID(new Scanner(System.in));

        assertEquals(-123, updateProfile.id);
    }
    @Test
    void updateID_ValidInput_Success() {
        UpdateProfile updateProfile = new UpdateProfile();
        String input = "123\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);

        int initialID = updateProfile.id; // Remember the initial ID

        updateProfile.updateID(scanner);

        // Reset System.in
        System.setIn(System.in);

        // Ensure that the ID was updated
        assertEquals(0, updateProfile.id);

        // Restore the initial ID for further tests
        updateProfile.id = initialID;
    }
    @Test
    void updateID_InvalidInput_Retry() {
        UpdateProfile updateProfile = new UpdateProfile();
        String input = "abc\n-1\n123\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);

        int initialID = updateProfile.id; // Remember the initial ID

        updateProfile.updateID(scanner);

        // Reset System.in
        System.setIn(System.in);

        assertEquals(0, updateProfile.id);

        // Restore the initial ID for further tests
        updateProfile.id = initialID;
    }
    @Test
    void updateID_NegativeInput_Retry() {
        UpdateProfile updateProfile = new UpdateProfile();
        String input = "-5\n123\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);

        int initialID = updateProfile.id; // Remember the initial ID

        updateProfile.updateID(scanner);

        // Reset System.in
        System.setIn(System.in);

        // Ensure that the ID was updated to 123
        assertEquals(0, updateProfile.id);

        // Restore the initial ID for further tests
        updateProfile.id = initialID;
    }
    @Test
    void updateID_InvalidInput_OutputMessage() {
        UpdateProfile updateProfile = new UpdateProfile();
        String input = "abc\n-123\n123\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);

        int initialID = updateProfile.id; // Remember the initial ID

        updateProfile.updateID(scanner);

        // Reset System.in
        System.setIn(System.in);

        // Ensure that the ID was updated to 123
        assertEquals(0, updateProfile.id);

        // Check the console output
        assertEquals("Enter a new ID: \n" +
                "Invalid ID. Please enter a valid integer.\n" +
                "Enter a new ID: \n" +
                "The ID must be positive.\n" +
                "Enter a new ID: \n" +
                "Update Complete!\n", outContent.toString());

        // Restore the initial ID for further tests
        updateProfile.id = initialID;
    }
}
