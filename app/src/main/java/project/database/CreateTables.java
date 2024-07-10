package project.database;

import java.sql.*;
import java.util.Date;

public class CreateTables{

    /**
     * Take note that when creating a new user, their role can only be
     * admin/guest/user.
     */
    public static void createCredsTable(){
        Connection connection = null;
        Statement statement = null;


        String createTableSQL =
//                "DROP TABLE IF EXISTS Credentials;" + // <-- deleting this means that the databse wont be refreshed after every run
                "CREATE TABLE IF NOT EXISTS Credentials (" +
                        "id INTEGER PRIMARY KEY, " +
                        "username VARCHAR(50) , " + // uniqueness is tested before inserting
                        "password VARCHAR(50), " +
                        "full_name VARCHAR(100), " +
                        "email VARCHAR(100), " +
                        "role VARCHAR(50), " +
                        "phone_number INTEGER)";

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();

            //Execute each line of sql by semicolon
            String[] sqlStatements = createTableSQL.split(";");
            for (String sql : sqlStatements) {
                statement.execute(sql);
            }

        }catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void createScrollsTable(){
        Connection connection = null;
        Statement statement = null;


        String createTableSQL =
//                "DROP TABLE IF EXISTS Scrolls;" +
                "CREATE TABLE IF NOT EXISTS Scrolls (" +
                        "sid INTEGER PRIMARY KEY, " +
                        "name VARCHAR(50), " +
                        "uploaderID INTEGER REFERENCES Credentials(ID), " +
                        "password VARCHAR(50), " +
                        "upload_date DATE)";

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();

            //Execute each line of sql by semicolon
            String[] sqlStatements = createTableSQL.split(";");
            for (String sql : sqlStatements) {
                statement.execute(sql);
            }

        }catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void createTotalDownloads(){
        Connection connection = null;
        Statement statement = null;

        String createTableSQL =
//                "DROP TABLE IF EXISTS Total_Downloads;" +
                "CREATE TABLE IF NOT EXISTS Total_Downloads (" +
                        "SID FLOAT REFERENCES Scrolls(SID), " +
                        "name VARCHAR(50)," +
                        "downloads INTEGER)";

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();

            //Execute each line of sql by semicolon
            String[] sqlStatements = createTableSQL.split(";");
            for (String sql : sqlStatements) {
                statement.execute(sql);
            }

        }catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static void createTotalUpdates(){
        Connection connection = null;
        Statement statement = null;


        String createTableSQL =
//                "DROP TABLE IF EXISTS Total_Updates;" +
                "CREATE TABLE IF NOT EXISTS Total_Updates (" +
                        "SID FLOAT REFERENCES Scrolls(SID), " +
                        "name VARCHAR(50)," +
                        "updates INTEGER)";

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();

            //Execute each line of sql by semicolon
            String[] sqlStatements = createTableSQL.split(";");
            for (String sql : sqlStatements) {
                statement.execute(sql);
            }

        }catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}