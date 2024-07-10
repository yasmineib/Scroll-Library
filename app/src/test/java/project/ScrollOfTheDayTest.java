package project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.database.CreateTables;
import project.database.PopulateTables;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScrollOfTheDayTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
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
    public void printScrollTest()
    {
        ScrollOfTheDay s = new ScrollOfTheDay();
        s.printScroll();
        String output = outContent.toString();
        assertTrue(output.contains("!!!SCROLL OF THE DAY!!!"));
        assertTrue(output.contains("SCROLL OF THE DAY FIN :)"));

    }
}