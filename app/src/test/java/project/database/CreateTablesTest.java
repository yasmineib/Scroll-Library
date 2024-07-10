package project.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateTablesTest {

    private Connection connection;
    private Statement statement;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void testCreateCredsTable() throws SQLException {
        CreateTables.createCredsTable();

        // Check if table exists
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='Credentials'")) {
            assertTrue(rs.next());
        }

        // Check if records were inserted
//        try (Connection conn = DBConnect.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM Credentials")) {
//            assertTrue(rs.next());
//            assertEquals("Garlic Bread", rs.getString("name"));
//        }
    }

//    @Test
//    public void testCreateCredsTable() throws SQLException {
//        CreateTables.createCredsTable();
//
//        // Check if the Credentials table exists
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM Credentials");
//        assertNotNull(resultSet);
//        resultSet.close();
//    }

    @Test
    public void testCreateScrollsTable() throws SQLException {
        CreateTables.createScrollsTable();

        // Check if table exists
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='Scrolls'")) {
            assertTrue(rs.next());
        }
    }

    @Test
    public void testCreateTotalDownloads() throws SQLException {
        CreateTables.createTotalDownloads();

        // Check if table exists
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='Total_Downloads'")) {
            assertTrue(rs.next());
        }
    }

    @Test
    public void testCreateTotalUpdates() throws SQLException {
        CreateTables.createTotalUpdates();

        // Check if table exists
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='Total_Updates'")) {
            assertTrue(rs.next());
        }
    }


}
