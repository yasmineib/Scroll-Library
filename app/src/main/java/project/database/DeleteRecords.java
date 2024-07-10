package project.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRecords {

    // TODO perhaps think about how to renumber all the ID's after deleting one from the middle.
    static Connection conn;
    public static void deleteFromCreds(int idToDelete){
        String statement = "DELETE FROM Credentials WHERE id = ?";

        try {
            conn = DBConnect.getConnection();

            PreparedStatement pstatement = conn.prepareStatement(statement);
            pstatement.setInt(1, idToDelete);
            pstatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteFromScrolls(int sidToDelete){
        String statement = "DELETE FROM Scrolls WHERE sid = ?";

        try {
            conn = DBConnect.getConnection();
            PreparedStatement pstatement1 = conn.prepareStatement(statement);
            pstatement1.setInt(1, sidToDelete);
            pstatement1.executeUpdate();

            deleteFromTotalDownloads(sidToDelete);
            deleteFromTotalUpdates(sidToDelete);
        } catch (SQLException e){
            System.out.println("Deletion didn't work.??");
            e.printStackTrace();
        }
    }

    public static void deleteFromTotalDownloads(int sid){
        String statement = "DELETE FROM Total_Downloads WHERE sid = ?";

        try {
            conn = DBConnect.getConnection();
            PreparedStatement pstatement1 = conn.prepareStatement(statement);
            pstatement1.setInt(1, sid);
            pstatement1.executeUpdate();

        } catch (SQLException e){
            System.out.println("Deletion didn't work.??");
            e.printStackTrace();
        }
    }

    public static void deleteFromTotalUpdates(int sid){
        String statement = "DELETE FROM Total_Updates WHERE sid = ?";

        try {
            conn = DBConnect.getConnection();
            PreparedStatement pstatement1 = conn.prepareStatement(statement);
            pstatement1.setInt(1, sid);
            pstatement1.executeUpdate();

        } catch (SQLException e){
            System.out.println("Deletion didn't work.??");
            e.printStackTrace();
        }
    }
}
