package project.database;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class SelectRecordsTest {
    @Test
    void testPrintCredsByIDNonExistentID() {
        int id = -1;
        ArrayList<Object> result = SelectRecords.printCredsByID(id);
        assertNull(result);
    }

    @Test
    void testPrintCredsByID() {
        ArrayList<Object> result = SelectRecords.printCredsByID(5);

        assertNotNull(result, "Result should not be null");
        assertEquals(4, result.size(), "Result should contain 4 elements");

        // Assuming the structure of the data (id, username, password, role)
        assertEquals(5, result.get(0), "ID should be 1");
        assertEquals("Yasmine", result.get(1), "Username should be testuser");
        assertEquals(PasswordHashing.hashPassword("0000"), result.get(2), "Password should be testpassword");
        assertEquals("admin", result.get(3), "Role should be user");
    }

    @Test
    void testGetUsernameByID() {
        String username = SelectRecords.getUsernameByID(5);

        assertNotNull(username, "Username should not be null");
        assertEquals("Yasmine", username, "Username should be testuser");
    }

    @Test
    void testGetIDByUsername() {
        int id = SelectRecords.getIDbyusername("Yasmine");

        assertEquals(5, id, "ID should be 5");
    }

    @Test
    void testSelectAllFromCreds() {
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to be tested
        SelectRecords.selectAllFromCreds();

        // Restore the original System.out
        System.setOut(System.out);

        // Get the captured output as a string
        String output = outContent.toString();

        // Verify if the output is as expected
        String expectedOutput = String.format("id                            username                      password                      full_name                     email                         role                          phone_number                  \n" +
                "1                             Mohitha                       %sMohitha Mohan                 mmoh@gmail.com                admin                         1234567808                    \n" +
                "2                             Kathy                         %sKathy Kritharides             kkri@gmail.com                admin                         1234567936                    \n" +
                "3                             Niranjana                     %sNiranjana Saxena              nsax@gmail.com                admin                         1234577024                    \n" +
                "4                             Shreeya                       %sShreeya Gupta                 sgup@gmail.com                admin                         1234577024                    \n" +
                "5                             Yasmine                       %sYasmine Ibrahim               yibr@gmail.com                admin                         1234577024                    \n" +
                "6                             General                       %sGeneral User                  gen@gmail.com                 general                       1234577024                    \n", PasswordHashing.hashPassword("0000"), PasswordHashing.hashPassword("0000"), PasswordHashing.hashPassword("0000"), PasswordHashing.hashPassword("0000"), PasswordHashing.hashPassword("0000"), PasswordHashing.hashPassword("0000"));

//        assertEquals(expectedOutput, output);
    }

    @Test
    void testSelectAllFromScrolls() {
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to be tested
        SelectRecords.selectAllFromScrolls();

        // Restore the original System.out
        System.setOut(System.out);

        // Get the captured output as a string
        String output = outContent.toString();

        // Verify if the output is as expected
        String expectedOutput = "sid                           name                          uploaderID                    password                      upload_date                   \n" +
                "1                             History of The Whiskers       1                             curiousCats                   2023-10-24                    \n" +
                "2                             Eternal Echoes                1                             echoesForever                 2023-10-24                    \n" +
                "3                             Whispers in the Wind          1                             catGhost                      2023-10-24                    \n" +
                "4                             The Forgotten Symphony        1                             null                          2023-10-24                    \n" +
                "5                             The Enchanted Labyrinth       1                             null                          2023-10-24                    \n" +
                "6                             Untitled.txt                  6                             null                          2023-10-24                    \n";

        assertEquals(expectedOutput, output);
    }
    @Test
    public void testSelectScrollNames() {
        ArrayList<String> result = SelectRecords.selectScrollNames();

        // Replace expectedValues with the expected list of scroll names
        ArrayList<String> expectedValues = new ArrayList<>();
        expectedValues.add("History of The Whiskers");
        expectedValues.add("Eternal Echoes");
        expectedValues.add("Whispers in the Wind");
        expectedValues.add("The Forgotten Symphony");
        expectedValues.add("The Enchanted Labyrinth");
        expectedValues.add("Untitled.txt");

        // Compare the actual result with the expected result
        assertEquals(expectedValues, result);
    }
//    @Test
//    void testSelectAllFromTotalDownloads() {
//        // Create a ByteArrayOutputStream to capture the output
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        // Call the method to be tested
//        SelectRecords.selectAllFromTotalDownloads();
//
//        // Restore the original System.out
//        System.setOut(System.out);
//
//        // Get the captured output as a string
//        String output = outContent.toString();
//
//
//        // Verify if the output is as expected
//        String expectedOutput = "SID                           name                          downloads                     \n";
//
//
//        assertEquals(expectedOutput, output);
//    }
//    @Test
//    void testSelectAllFromTotalUpdates() {
//        // Create a ByteArrayOutputStream to capture the output
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        // Call the method to be tested
//        SelectRecords.selectAllFromTotalDownloads();
//
//        // Restore the original System.out
//        System.setOut(System.out);
//
//        // Get the captured output as a string
//        String output = outContent.toString();
//
//
//        // Verify if the output is as expected
//        String expectedOutput = "SID                           name                          downloads                     \n";
//
//
//
//        assertEquals(expectedOutput, output);
//    }
//    @Test
//    void testSelectUpdatesFromUpdates(){
//        int result = SelectRecords.selectUpdatesFromTotalUpdates(1);
//        int expectedValue = 0;
//        assertEquals(expectedValue, result);
//    }
    @Test
    void testSelectNumberofScrolls(){
        int result = SelectRecords.selectNumberScrolls();
        int expectedValue = 6;
        assertEquals(expectedValue, result);
    }
    @Test
    void testSelectDownloadsFromUpdates(){
        int result = SelectRecords.selectDownloadsFromTotalDownloads(1);
        int expectedValue = 0;
        assertEquals(expectedValue, result);
    }
    @Test
    void testSelectRowsWithUploaderID() {
        int uploaderID = 1;
        boolean result = SelectRecords.selectRowsWithUploaderID(uploaderID);
        assertTrue(result);
    }
    @Test
    void testDoesIdExist() {
        int existingID = 1; // Set an existing ID

        // Call your method
        boolean resultForExistingID = SelectRecords.doesIdExist(existingID);

        assertTrue(resultForExistingID, "ID exists in the database.");
    }

    @Test
    void testDoesIdExistForNonExistentID() {
        int nonExistentID = -1; // Set a non-existent ID

        // Call your method
        boolean resultForNonExistentID = SelectRecords.doesIdExist(nonExistentID);

        assertFalse(resultForNonExistentID, "ID does not exist in the database.");
    }
    @Test
    void testDoesUsernameExist() {
        boolean resultForExistingID = SelectRecords.doesUsernameExist("Yasmine");
        assertTrue(resultForExistingID, "ID exists in the database.");
    }
    @Test
    void testDoesUsernameExistFalse() {
        boolean resultForExistingID = SelectRecords.doesUsernameExist("lsdkjflsdkjfsldkjfALKSDJFALSDsdkfjsld");
        assertFalse(resultForExistingID, "ID doesnt exist in the database.");
    }
    @Test
    public void testSelectScrollNamesWoPassword() {
        ArrayList<String> expectedScrollNames = new ArrayList<>();
        expectedScrollNames.add("The Forgotten Symphony");
        expectedScrollNames.add("The Enchanted Labyrinth");
        expectedScrollNames.add("Untitled.txt");

        ArrayList<String> actualScrollNames = SelectRecords.selectScrollNamesWoPassword();

        assertEquals(expectedScrollNames, actualScrollNames);
    }
    @Test
    void testSelectScrollName(){
        String expected = "History of The Whiskers";
        String actual = SelectRecords.selectScrollName(1);
        assertEquals(expected, actual);
    }
    @Test
    void testSelectScrollID(){
        int expected = 1;
        int actual = SelectRecords.selectScrollId("History of The Whiskers");
        assertEquals(expected, actual);
    }

    @Test
    void testSeletScrollUploaderID(){
        int expected = 1;
        int actual = SelectRecords.selectScrollUploaderID(1);
        assertEquals(expected, actual);
    }
    @Test
    public void testSelectRowsWithScrollID() {
        int sid = 1;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        boolean result = SelectRecords.selectRowsWithScrollID(sid);
        assertTrue(result);
//        String expectedOutput = "sid                           name                          uploaderID                    password                      upload_date                   \n" +
//                "1                             History of The Whiskers       1                             curiousCats                   2023-10-23                    \n";
//        assertEquals(expectedOutput, outContent);
    }
    @Test
    public void testSelectRowsWithString() {
        String name = "example"; // Replace with the actual name you want to test
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        boolean result = SelectRecords.selectRowsWithString(name);

        assertTrue(result);
//        String expected = "";
//        assertEquals(expected, outContent);
    }
    @Test
    public void testSelectRowsWithUploadDate() {
        String dateString = "2023-10-23"; // Replace with the actual date you want to test
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        boolean result = SelectRecords.selectRowsWithUploadDate(dateString);

        assertTrue(result);
//        String expected = "sid                           name                          uploaderID                    password                      upload_date                   \n";
//        assertEquals(expected, outContent);
    }
    @Test
    public void testSelectScrollIdsNames() {
        Map<Integer, String> result = SelectRecords.selectScrollIdsNames();

        // Add assertions to verify the returned map
        assertTrue(result.containsKey(1)); // Check if it contains a specific key
        assertEquals("History of The Whiskers", result.get(1)); // Check if the value matches for a specific key
        // Add more assertions as needed
    }
    @Test
    public void testDoesScrollNameExist() {
        boolean result1 = SelectRecords.doesScrollNameExist("History of The Whiskers");
        boolean result2 = SelectRecords.doesScrollNameExist("NonExistingName");

        assertTrue(result1);
        assertFalse(result2);
    }
    @Test
    public void testDoesScrollIDExist() {
        boolean result1 = SelectRecords.doesScrollIDExist(1);
        boolean result2 = SelectRecords.doesScrollIDExist(1000);

        assertTrue(result1);
        assertFalse(result2);
    }
//    @Test
//    public void testGetMaxID() {
//        int result1 = SelectRecords.getMaxCID();
//        int expected = 6;
//        assertEquals(expected, result1);
//    }

}
