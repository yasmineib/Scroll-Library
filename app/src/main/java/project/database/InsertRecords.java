package project.database;

import java.sql.*;
import java.util.Date;

public class InsertRecords {

    static int currentCredsID = SelectRecords.getMaxCID() + 1;
    static int currentScrollsID =  SelectRecords.getMaxSID() + 1;

    public static void insertIntoCreds(String username, String password, String full_name,
                                       String email, String role, float phone_number) {
        Connection connection = null;
        PreparedStatement selectStatement = null;
        PreparedStatement insertStatement = null;

        // SQL statement to check if the username already exists
        String selectSQL = "SELECT 1 FROM Credentials WHERE username = ?";

        // SQL statement to insert the record
        String insertSQL = "INSERT INTO Credentials (id, username, password, full_name, email, role, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            connection = DBConnect.getConnection();

            // Check if the username already exists
            selectStatement = connection.prepareStatement(selectSQL);
            selectStatement.setString(1, username);
            ResultSet rs = selectStatement.executeQuery();

            if (!rs.next()) {
                // Username doesn't exist, perform the insertion
                if(currentCredsID == 0){
                    currentCredsID = 1;
                }
                insertStatement = connection.prepareStatement(insertSQL);
                insertStatement.setDouble(1, currentCredsID);
                insertStatement.setString(2, username);
                insertStatement.setString(3, password);
                insertStatement.setString(4, full_name);
                insertStatement.setString(5, email);
                insertStatement.setString(6, role);
                insertStatement.setDouble(7, phone_number);
                insertStatement.executeUpdate();

                currentCredsID++;
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (insertStatement != null) {
                    insertStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void insertIntoScrolls(String name, int uploaderID, String password, Date upload_date) {
        Connection connection = null;
        PreparedStatement selectStatement = null;
        PreparedStatement insertStatement = null;

        String selectSQL = "SELECT 1 FROM Scrolls WHERE name = ?";
        String insertSQL = "INSERT INTO Scrolls (sid, name, uploaderID, password, upload_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            connection = DBConnect.getConnection();

            // Check if the name already exists
            selectStatement = connection.prepareStatement(selectSQL);
            selectStatement.setString(1, name);
            ResultSet rs = selectStatement.executeQuery();

            if (!rs.next()) {
                // Name doesn't exist, perform the insertion
                Date givenDate = upload_date;
                java.sql.Date sqlDate = new java.sql.Date(givenDate.getTime());

                insertStatement = connection.prepareStatement(insertSQL);
                insertStatement.setInt(1, currentScrollsID);
                insertStatement.setString(2, name);
                insertStatement.setInt(3, uploaderID);
                insertStatement.setString(4, password);
                insertStatement.setDate(5, sqlDate);

                insertStatement.executeUpdate();
//                int rowsAffected = insertStatement.executeUpdate();

//                if (rowsAffected > 0) {
//                    System.out.println("Insertion was successful. " + rowsAffected + " rows affected.");
//                } else {
//                    System.out.println("Insertion failed. No rows affected.");
//                }
                // Insert into TotalDownloads and TotalUpdates
                insertIntoTotalDownloads(currentScrollsID, name);
                insertIntoTotalUpdates(currentScrollsID, name);

                // Update the current scroll's SID to avoid repetition
                currentScrollsID++;
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("did i get here?");
        } finally {
            try {
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (insertStatement != null) {
                    insertStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * To insert into the total downloads table when a scroll is just uploaded. Therefore
     * total downloads will be 0.
     * @param sid
     * @param name
     */
    public static void insertIntoTotalDownloads(int sid, String name) {
        Connection conn = null;
        PreparedStatement selectStatement = null;
        PreparedStatement pstatement = null;

        String selectSQL = "SELECT * FROM Total_Downloads WHERE sid = ? AND name = ?";
        String insertSQL = "INSERT INTO Total_Downloads(sid, name, downloads) VALUES (?, ?, ?)";
        String updateSQL = "UPDATE Total_Downloads SET downloads = downloads + ? WHERE sid = ? AND name = ?";

        try {
            // Connect to the database
            conn = DBConnect.getConnection();

            // Check if record already exists
            selectStatement = conn.prepareStatement(selectSQL);
            selectStatement.setInt(1, sid);
            selectStatement.setString(2, name);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                // Record already exists, so update downloads
//                pstatement = conn.prepareStatement(updateSQL);
//                pstatement.setInt(1, 1); //Increment by one
//                pstatement.setInt(2, sid);
//                pstatement.setString(3, name);
//                pstatement.executeUpdate();
            } else {
                // Record doesn't exist, so insert it
                pstatement = conn.prepareStatement(insertSQL);
                pstatement.setInt(1, sid);
                pstatement.setString(2, name);
                pstatement.setInt(3, 0); // Default downloads is zero
                pstatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (pstatement != null) {
                    pstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * To insert into the total uploads/updates table when a scroll is just uploaded. Therefore
     * total updates will be 1.
     * @param sid
     * @param name
     */

    public static void insertIntoTotalUpdates(int sid, String name) {
        Connection conn = null;
        PreparedStatement selectStatement = null;
        PreparedStatement pstatement = null;

        String selectSQL = "SELECT * FROM Total_Updates WHERE sid = ? AND name = ?";
        String insertSQL = "INSERT INTO Total_Updates(sid, name, updates) VALUES (?, ?, ?)";
        String updateSQL = "UPDATE Total_Updates SET updates = updates + ? WHERE sid = ? AND name = ?";

        try {
            // Connect to the database
            conn = DBConnect.getConnection();

            // Check if record already exists
            selectStatement = conn.prepareStatement(selectSQL);
            selectStatement.setInt(1, sid);
            selectStatement.setString(2, name);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                // Record already exists, so update downloads
//                pstatement = conn.prepareStatement(updateSQL);
//                pstatement.setInt(1, 1); //Increment by one
//                pstatement.setInt(2, sid);
//                pstatement.setString(3, name);
//                pstatement.executeUpdate();
            } else {
                // Record doesn't exist, so insert it
                pstatement = conn.prepareStatement(insertSQL);
                pstatement.setInt(1, sid);
                pstatement.setString(2, name);
                pstatement.setInt(3, 1); // Default uploads is zero
                pstatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (pstatement != null) {
                    pstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
