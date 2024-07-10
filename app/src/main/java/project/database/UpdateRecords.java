package project.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateRecords {

    static Connection conn;
    /**
     * This works for every column update except for phone number,
     * because that's an integer.
//     * @param updatingID
//     * @param toChangeTo
     */
    public static boolean updateCredsID(int oldID, int newID) {
        boolean isUnique = false;
        String selectSQL = "SELECT 1 FROM Credentials WHERE id = ?";
        String updateSQL = "UPDATE Credentials SET id = ? WHERE id = ?";
        PreparedStatement selectStatement = null;
        PreparedStatement updateStatement = null;

        try {
            conn = DBConnect.getConnection();
            selectStatement = conn.prepareStatement(selectSQL);
            selectStatement.setInt(1, newID);
            ResultSet rs = selectStatement.executeQuery();

            if (!rs.next()) {
                // no row with the new id exists, perform the update
                updateStatement = conn.prepareStatement(updateSQL);
                updateStatement.setInt(1, newID);
                updateStatement.setInt(2, oldID);
                updateStatement.executeUpdate();
                isUnique = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (updateStatement != null) {
                    updateStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUnique;
    }

    public static void updateCredsUsername(int updatingID, String toChangeTo){
        String updateSQL = "UPDATE Credentials SET username = ? WHERE id = ?";

        try {
            conn = DBConnect.getConnection();
            PreparedStatement statement = conn.prepareStatement(updateSQL);
            statement.setString(1, toChangeTo);
            statement.setInt(2, updatingID);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateCredsPassword(int updatingID, String toChangeTo){
        String updateSQL = "UPDATE Credentials SET password = ? WHERE id = ?";

        try {
            conn = DBConnect.getConnection();
            PreparedStatement statement = conn.prepareStatement(updateSQL);
            statement.setString(1, toChangeTo);
            statement.setInt(2, updatingID);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateCredsFullName(int updatingID, String toChangeTo){
        String updateSQL = "UPDATE Credentials SET full_name = ? WHERE id = ?";

        try {
            conn = DBConnect.getConnection();
            PreparedStatement statement = conn.prepareStatement(updateSQL);
            statement.setString(1, toChangeTo);
            statement.setInt(2, updatingID);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateCredsEmail(int updatingID, String toChangeTo){
        String updateSQL = "UPDATE Credentials SET email = ? WHERE id = ?";
        Connection conn;

        try {
            conn = DBConnect.getConnection();
            PreparedStatement statement = conn.prepareStatement(updateSQL);
            statement.setString(1, toChangeTo);
            statement.setInt(2, updatingID);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Remember that the role can only be admin/guest/member
     * @param updatingID
     * @param toChangeTo
     */
    public static void updateCredsRole(int updatingID, String toChangeTo){
        String updateSQL = "UPDATE Credentials SET role = ? WHERE id = ?";

        try {
            conn = DBConnect.getConnection();
            PreparedStatement statement = conn.prepareStatement(updateSQL);
            statement.setString(1, toChangeTo);
            statement.setInt(2, updatingID);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateCredsPhoneNumber(int updatingID, int toChangeTo){
        String updateSQL = "UPDATE Credentials SET phone_number = ? WHERE id = ?";

        try {
            conn = DBConnect.getConnection();
            PreparedStatement statement = conn.prepareStatement(updateSQL);
            statement.setInt(1, toChangeTo);
            statement.setInt(2, updatingID);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateScrollsName(int updatingSID, String toChangeTo){
        String updateSQL1 = "UPDATE Scrolls SET name = ?, upload_date = ? WHERE sid = ?";

        try{
            conn = DBConnect.getConnection();
            PreparedStatement statement1 = conn.prepareStatement(updateSQL1);
            statement1.setString(1, toChangeTo);
            statement1.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            statement1.setInt(3, updatingSID);
            statement1.executeUpdate();

            updateTotalUploadsNumberByOne(updatingSID, toChangeTo);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

//    public static void updateScrollsBinary(int updatingSID, String toChangeTo){
//        String updateSQL1 = "UPDATE Scrolls SET binary = ? WHERE id = ?";
//        String updateSQL2 = "UPDATE Scrolls SET upload_date = ?, WHERE id = ?";
//
//        try{
//            conn = DBConnect.getConnection();
//            PreparedStatement statement1 = conn.prepareStatement(updateSQL1);
//            statement1.setString(1, toChangeTo);
//            statement1.setInt(2, updatingSID);
//            statement1.executeUpdate();
//
//            PreparedStatement statement2 = conn.prepareStatement(updateSQL2);
//            statement2.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
//            statement2.setInt(2, updatingSID);
//            statement2.executeUpdate();
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

    /**
     * Updates the total uploads for a given scroll id by 1
     * Meant to be used in conjunction with other update statements in this class,
     * for example when you update the contents or name of a scroll.
     * @param sid
     */
    public static void updateTotalUploadsNumberByOne(int sid, String new_name){
        String updateSQL1 = "UPDATE Total_Updates SET updates = updates + 1, name = ? WHERE sid = ?";

        try{
            conn = DBConnect.getConnection();
            PreparedStatement statement1 = conn.prepareStatement(updateSQL1);
            statement1.setString(1, new_name);
            statement1.setInt(2, sid);
            statement1.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Updates the total uploads for a given scroll id by 1
     * Meant to be used in conjunction with other update statements in this class,
     * for example when you update the contents or name of a scroll.
     * @param sid
     */
    public static void updateTotalDownloadsNumberByOne(int sid){
        String updateSQL1 = "UPDATE Total_Downloads SET downloads = downloads + 1 WHERE sid = ?";

        try{
            conn = DBConnect.getConnection();
            PreparedStatement statement1 = conn.prepareStatement(updateSQL1);
            statement1.setInt(1, sid);
            statement1.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}