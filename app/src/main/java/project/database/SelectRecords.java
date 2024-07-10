package project.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SelectRecords {

    /////////////////////////////
    ///// CREDENTIALS TABLE /////
    /////////////////////////////
    public static void selectAllFromCreds(){
        Connection conn;
        String sql = "SELECT * FROM Credentials";

        try {
            conn = DBConnect.getConnection();
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rss = rs.getMetaData();

            for (int i = 1; i <= rss.getColumnCount(); i++) {
                System.out.printf("%-30s",rss.getColumnName(i));
            }
            System.out.println();

            // TODO maybe try to fix the spacing/printing out of this.

            while (rs.next()) {
                System.out.printf("%-30d%-30s%-30s%-30s%-30s%-30s%-30s\n", rs.getInt("id"), rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("phone_number")
                );
            }

            conn.close();
            statement.close();
            rs.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Object> printCredsByID(int id) {
        //Returns the data for the given id. if it doesnt exist, return null
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Object> data = new ArrayList<>();

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT * FROM Credentials WHERE id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            rs = statement.executeQuery();

            if (rs.next()) {
                //add the data
                data.add(rs.getInt("id"));
                data.add(rs.getString("username"));
                data.add(rs.getString("password"));
                data.add(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //return the data or null
        if(data.size() > 0){
            return data;
        }
        return null;
    }

    public static String getUsernameByID(int id) {
        //Returns the data for the given id. if it doesnt exist, return null
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String data = null;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT username FROM Credentials WHERE id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            rs = statement.executeQuery();

            if (rs.next()) {
                //add the data
                data = rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //return the data or null
        return data;
    }

    public static int getIDbyusername(String username) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int data = -1; // Set to a value that indicates no matching username found

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT id FROM Credentials WHERE username = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            rs = statement.executeQuery();

            if (rs.next()) {
                data = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        return data;
    }
    public static int getMaxCID() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int data = 1;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT MAX(id) FROM Credentials";
            statement = conn.prepareStatement(sql);

            rs = statement.executeQuery();

            if (rs.next()) {
                data = rs.getInt("MAX(id)");
                return data;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
    public static int getMaxSID() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int data = 1;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT MAX(sid) FROM Scrolls";
            statement = conn.prepareStatement(sql);

            rs = statement.executeQuery();

            if (rs.next()) {
                data = rs.getInt("MAX(sid)");
                return data;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static boolean doesIdExist(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean doesExist = false;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT 1 FROM Credentials WHERE id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            rs = statement.executeQuery();

            doesExist = rs.next(); // If there's a row, the id exists
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return doesExist;
    }

    public static boolean doesUsernameExist(String username) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean doesExist = false;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT 1 FROM Credentials WHERE username = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            rs = statement.executeQuery();

            doesExist = rs.next(); // If there's a row, the id exists
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return doesExist;
    }


    /////////////////////////////
    /////// SCROLLS TABLE ///////
    /////////////////////////////

    public static void selectAllFromScrolls() {
        Connection conn = null;
        String sql = "SELECT * FROM Scrolls";

        try {
            conn = DBConnect.getConnection();
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rss = rs.getMetaData();


            // TODO maybe try to fix the spacing/printing out of this.
            for (int i = 1; i <= rss.getColumnCount(); i++) {
                System.out.printf("%-30s",rss.getColumnName(i));
            }
            System.out.println();

            while (rs.next()) {
                System.out.printf("%-30d%-30s%-30d%-30s%-30s\n", rs.getInt("sid"),
                        rs.getString("name"), rs.getInt("uploaderID"),
                        rs.getString("password"), rs.getDate("upload_date"));
            }
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<String> selectScrollNames()
    {
        Connection conn = null;
        String sql = "SELECT name FROM Scrolls";
        ArrayList<String> scrollNames= new ArrayList<>();

        try {
            conn = DBConnect.getConnection();
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rss = rs.getMetaData();


            // TODO maybe try to fix the spacing/printing out of this.
            for (int i = 1; i <= rss.getColumnCount(); i++) {
                System.out.printf("%-30s",rss.getColumnName(i));
            }
            System.out.println();

            while (rs.next()) {
                scrollNames.add(rs.getString("name"));
//                System.out.printf("%-30d%-30s%-30d%-30s%-30s\n", rs.getInt("sid"),
//                        rs.getString("name"), rs.getInt("uploaderID"),
//                        rs.getString("password"), rs.getDate("upload_date"));
            }
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return scrollNames;

    }

    public static ArrayList<String> selectScrollNamesWoPassword()
    {
        Connection conn = null;
        String sql = "SELECT name FROM Scrolls";
        ArrayList<String> scrollNames= new ArrayList<>();

        try {
            conn = DBConnect.getConnection();
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rss = rs.getMetaData();


            // TODO maybe try to fix the spacing/printing out of this.
            for (int i = 1; i <= rss.getColumnCount(); i++) {
                System.out.printf("%-30s",rss.getColumnName(i));
            }
            System.out.println();
            String scrollName = "";
            while (rs.next()) {
                scrollName = rs.getString("name");
                if(getScrollPassword(scrollName) == null)
                {
                    scrollNames.add(scrollName);
                }
//                System.out.printf("%-30d%-30s%-30d%-30s%-30s\n", rs.getInt("sid"),
//                        rs.getString("name"), rs.getInt("uploaderID"),
//                        rs.getString("password"), rs.getDate("upload_date"));
            }
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return scrollNames;

    }

    public static String selectScrollName(int sid){
        Connection conn = null;
        String sql = "SELECT name FROM Scrolls WHERE sid = ?";
        String name = "";
        PreparedStatement statement;

        try {
            conn = DBConnect.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, sid);

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();


//            for (int i = 1; i <= rss.getColumnCount(); i++) {
//                System.out.printf("%-30s",rss.getColumnName(i));
//            }
//            System.out.println();

            name = rs.getString("name");
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return name;
    }
    public static int selectScrollId(String scrollName){
        Connection conn = null;
        String sql = "SELECT sid FROM Scrolls WHERE name = ?";
        int sid = 0;

        PreparedStatement statement;

        try {
            conn = DBConnect.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, scrollName);

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();


//            for (int i = 1; i <= rss.getColumnCount(); i++) {
//                System.out.printf("%-30s",rss.getColumnName(i));
//            }
//            System.out.println();

            sid = rs.getInt("sid");
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return sid;


    }

    /**
     * Returns uploadid of the scroll with given sid. Returns -1 if there isn't an sid.
     * @param sid
     * @return
     */
    public static int selectScrollUploaderID(int sid){
        Connection conn = null;
        String sql = "SELECT uploaderid FROM Scrolls WHERE sid = ?";
        int uploaderid = -1;
        PreparedStatement statement;

        try {
            conn = DBConnect.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, sid);

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();


//            for (int i = 1; i <= rss.getColumnCount(); i++) {
//                System.out.printf("%-30s",rss.getColumnName(i));
//            }
//            System.out.println();

            uploaderid = rs.getInt("uploaderid");
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return uploaderid;
    }


    public static int selectNumberScrolls() {
        Connection conn = null;
        String sql = "SELECT COUNT(*) FROM Scrolls";
        int count = 0;

        try {
            conn = DBConnect.getConnection();
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rss = rs.getMetaData();

//            for (int i = 1; i <= rss.getColumnCount(); i++) {
//                System.out.printf("%-30s",rss.getColumnName(i));
//            }
//            System.out.println();

            while (rs.next()) {
                count = rs.getInt("COUNT(*)");
            }
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return count;

    }

    /**
     * Selects and prints all rows with a given uploaderID.
     * @param uid
     */
    public static boolean selectRowsWithUploaderID(int uid){
        Connection conn = null;
        String sql = "SELECT * FROM Scrolls WHERE uploaderid = ?";

        PreparedStatement statement;
        try {
            conn = DBConnect.getConnection();
            statement = conn.prepareStatement(sql);
//            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, uid);

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();

//            if (!rs.next()){
//                System.out.println("There are no rows with that uploaderID.");
//                conn.close();
//                return false;
//            }
//            else {
                for (int i = 1; i <= rss.getColumnCount(); i++) {
                    System.out.printf("%-30s",rss.getColumnName(i));
                }
                System.out.println();

                // Setting result set back to the beginning, because we'd moved it ahead in the
                // above code to check if there was anything in the set.
//                rs.first();

                while (rs.next()) {
                    System.out.printf("%-30d%-30s%-30d%-30s%-30s\n", rs.getInt("sid"),
                            rs.getString("name"), rs.getInt("uploaderID"),
                            rs.getString("password"), rs.getDate("upload_date"));
                }
                conn.close();
                return true;
//            }


        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean selectRowsWithScrollID(int sid){
        Connection conn = null;
        String sql = "SELECT * FROM Scrolls WHERE sid = ?";

        PreparedStatement statement;
        try {
            conn = DBConnect.getConnection();
            statement = conn.prepareStatement(sql);
//            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, sid);

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();

            for (int i = 1; i <= rss.getColumnCount(); i++) {
                System.out.printf("%-30s",rss.getColumnName(i));
            }
            System.out.println();

            // Setting result set back to the beginning, because we'd moved it ahead in the
            // above code to check if there was anything in the set.
//            rs.first();

            while (rs.next()) {
                System.out.printf("%-30d%-30s%-30d%-30s%-30s\n", rs.getInt("sid"),
                        rs.getString("name"), rs.getInt("uploaderID"),
                        rs.getString("password"), rs.getDate("upload_date"));
            }
            conn.close();
            return true;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the SID of a scroll with a specific name
     * @param name
     * @return
     */
    public static int selectSidFromScrollsWithName(String name){
        Connection conn = null;
        String sql = "SELECT sid FROM Scrolls WHERE name = ?";
        int sid = -1;
        PreparedStatement statement;

        try {
            conn = DBConnect.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();


//            for (int i = 1; i <= rss.getColumnCount(); i++) {
//                System.out.printf("%-30s",rss.getColumnName(i));
//            }
//            System.out.println();

            sid = rs.getInt("sid");
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return sid;
    }


    public static boolean selectRowsWithString(String name){
        Connection conn = null;
        String ignorecase = "%"+name.toLowerCase()+"%";
        String sql = "SELECT * FROM Scrolls WHERE name like ?  ";

        PreparedStatement statement;
        try {
            conn = DBConnect.getConnection();
            statement = conn.prepareStatement(sql);
//            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, ignorecase);

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();

            for (int i = 1; i <= rss.getColumnCount(); i++) {
                System.out.printf("%-30s",rss.getColumnName(i));
            }
            System.out.println();

            while (rs.next()) {
                System.out.printf("%-30d%-30s%-30d%-30s%-30s\n", rs.getInt("sid"),
                        rs.getString("name"), rs.getInt("uploaderID"),
                        rs.getString("password"), rs.getDate("upload_date"));
            }
            conn.close();
            return true;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public static boolean selectRowsWithUploadDate(String dateString){
        Connection conn = null;
//        String date;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date dateToSelect = Date.valueOf("2023-10-23");

        String sql = "SELECT * FROM Scrolls WHERE upload_date > '2023-10-23'";

//        PreparedStatement statement;
        try {
            conn = DBConnect.getConnection();
            Statement statement = conn.createStatement();

//            java.util.Date utilDate = sdf.parse(dateString);
//            statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            statement.setDate(1, dateToSelect);

            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rss = rs.getMetaData();

            for (int i = 1; i <= rss.getColumnCount(); i++) {
                System.out.printf("%-30s",rss.getColumnName(i));
            }
            System.out.println();

            while (rs.next()) {
                System.out.printf("%-30d%-30s%-30d%-30s%-30s\n", rs.getInt("sid"),
                        rs.getString("name"), rs.getInt("uploaderID"),
                        rs.getString("password"), rs.getDate("upload_date"));
            }
            conn.close();
            return true;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static Map<Integer, String> selectScrollIdsNames()
    {
        Connection conn = null;
        String sql = "SELECT sid, name FROM Scrolls";
        Map<Integer, String> scrolls = new HashMap<Integer, String>();

        try {
            conn = DBConnect.getConnection();
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rss = rs.getMetaData();


            // TODO maybe try to fix the spacing/printing out of this.
            for (int i = 1; i <= rss.getColumnCount(); i++) {
                System.out.printf("%-30s",rss.getColumnName(i));
            }
            System.out.println();

            while (rs.next()) {
                scrolls.put(rs.getInt("sid"), rs.getString("name"));
            }
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return scrolls;

    }

    public static boolean doesScrollNameExist(String name) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean doesExist = false;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT 1 FROM Scrolls WHERE name = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            rs = statement.executeQuery();

            doesExist = rs.next(); // If there's a row, the id exists
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return doesExist;
    }

    public static boolean doesScrollIDExist(int sid){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean doesExist = false;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT 1 FROM Scrolls WHERE sid = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, sid);

            rs = statement.executeQuery();

            doesExist = rs.next(); // If there's a row, the id exists
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return doesExist;
    }

    public static String getScrollPassword(String name) {
        //Returns the data for the given id. if it doesnt exist, return null
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String data = null;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT password FROM Scrolls WHERE name = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            rs = statement.executeQuery();

            if (rs.next()) {
                //add the data
                data = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //return the data or null
        return data;
    }

    /////////////////////////////////
    ///// TOTAL_DOWNLOADS TABLE /////
    /////////////////////////////////
    public static void selectAllFromTotalDownloads(){
        Connection conn = null;
        String sql = "SELECT * FROM Total_Downloads";

        try {
            conn = DBConnect.getConnection();
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rss = rs.getMetaData();


            // TODO maybe try to fix the spacing/printing out of this.
            for (int i = 1; i <= rss.getColumnCount(); i++) {
                System.out.printf("%-30s",rss.getColumnName(i));
            }
            System.out.println();

            while (rs.next()) {
                System.out.printf("%-30d%-30s%-30d\n", rs.getInt("sid"),
                        rs.getString("name"), rs.getInt("downloads"));
            }

            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the total downloads of a scroll with given scroll id as an integer.
     * If anything goes wrong it returns -1.
     * @param sid
     * @return
     */
    public static int selectDownloadsFromTotalDownloads(int sid){
        Connection conn = null;
        String sql = "SELECT downloads FROM Total_Downloads WHERE sid = ?";
        int n = -1;
        PreparedStatement statement;

        try {
            conn = DBConnect.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, sid);

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();


//            for (int i = 1; i <= rss.getColumnCount(); i++) {
//                System.out.printf("%-30s",rss.getColumnName(i));
//            }
//            System.out.println();

            n = rs.getInt("downloads");
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return n;
    }


    /////////////////////////////////
    ///// TOTAL_UPDATES TABLE ///////
    /////////////////////////////////
    public static void selectAllFromTotalUpdates(){
        Connection conn = null;
        String sql = "SELECT * FROM Total_Updates";

        try {
            conn = DBConnect.getConnection();
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rss = rs.getMetaData();


            // TODO maybe try to fix the spacing/printing out of this.
            for (int i = 1; i <= rss.getColumnCount(); i++) {
                System.out.printf("%-30s",rss.getColumnName(i));
            }
            System.out.println();

            while (rs.next()) {
                System.out.printf("%-30d%-30s%-30d\n", rs.getInt("sid"),
                        rs.getString("name"), rs.getInt("updates"));
            }

            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the total updates of a scroll with given scroll id as an integer.
     * If anything goes wrong it returns -1.
     * @param sid
     * @return
     */
    public static int selectUpdatesFromTotalUpdates(int sid){
        Connection conn = null;
        String sql = "SELECT updates FROM Total_Updates WHERE sid = ?";
        int n = -1;
        PreparedStatement statement;

        try {
            conn = DBConnect.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, sid);

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();


//            for (int i = 1; i <= rss.getColumnCount(); i++) {
//                System.out.printf("%-30s",rss.getColumnName(i));
//            }
//            System.out.println();

            n = rs.getInt("updates");
            conn.close();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return n;
    }



}
