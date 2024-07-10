package project.UI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.database.CreateTables;
import project.database.PopulateTables;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class PreviewTest {
    Preview p = new Preview();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
//    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayInputStream inputStream = new ByteArrayInputStream("y\n1\n2\nq\n".getBytes());



    @BeforeEach
    void setUpStreams() {
        CreateTables.createScrollsTable();
        PopulateTables.populateScrolls();
        System.setOut(new PrintStream(outContent));
        PopulateTables.populateScrolls();
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void previewFileTest()
    {
//        fail("please fail this test");

        Preview p = new Preview();
        p.previewFile("The Enchanted Labyrinth");
        String output = outContent.toString();
//        System.out.println(output);

        assertTrue(output.contains("101010111010011010100110101010101010101010100110010101001010101010101011001010110100101010011001010100101001010010101001010010101001001010100100101"));
//        assertTrue(output.contains("hello"));

    }
}
