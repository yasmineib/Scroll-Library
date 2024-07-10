package project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect{
    public static Connection getConnection() throws SQLException {
        //Load the SQLite JDBC driver
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Connect to the database
        String url = "jdbc:sqlite:database.db";
        return DriverManager.getConnection(url);
    }
}