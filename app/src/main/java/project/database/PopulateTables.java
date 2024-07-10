package project.database;

import java.util.Date;

public class PopulateTables {

    /**
     * Inserts tuples into the credentials table
     */
    public static void populateCredentials(){

        // instead of giving clear strings to the InsertRecords,
        // provide it the respective hashed password from the array

        InsertRecords.insertIntoCreds("Mohitha", PasswordHashing.hashPassword("0000"), "Mohitha Mohan", "mmoh@gmail.com", "admin", 1234567787);
        InsertRecords.insertIntoCreds("Kathy", PasswordHashing.hashPassword("0000"), "Kathy Kritharides", "kkri@gmail.com", "admin", 1234567891);
        InsertRecords.insertIntoCreds("Niranjana", PasswordHashing.hashPassword("0000"), "Niranjana Saxena", "nsax@gmail.com", "admin", 1234576981);
        InsertRecords.insertIntoCreds("Shreeya", PasswordHashing.hashPassword("0000"), "Shreeya Gupta", "sgup@gmail.com", "admin", 1234576981);
        InsertRecords.insertIntoCreds("Yasmine", PasswordHashing.hashPassword("0000"), "Yasmine Ibrahim", "yibr@gmail.com", "admin", 1234576981);
        InsertRecords.insertIntoCreds("General", PasswordHashing.hashPassword("0000"), "General User", "gen@gmail.com", "general", 1234576981);
    }

    public static void populateScrolls(){
        // What we're doing with the date is getting the current date in the form of milliseconds, and converting
        // that to a java.sql.Date type.
        InsertRecords.insertIntoScrolls("History of The Whiskers", 1, "curiousCats",
                new java.sql.Date(new java.util.Date().getTime()));
        InsertRecords.insertIntoScrolls("Eternal Echoes", 1, "echoesForever",
                new java.sql.Date(new java.util.Date().getTime()));
        InsertRecords.insertIntoScrolls("Whispers in the Wind", 1, "catGhost",
                new java.sql.Date(new java.util.Date().getTime()));
        InsertRecords.insertIntoScrolls("The Forgotten Symphony", 1, null,
                new java.sql.Date(new java.util.Date().getTime()));
        InsertRecords.insertIntoScrolls("The Enchanted Labyrinth", 1, null,
                new java.sql.Date(new java.util.Date().getTime()));

    }
}
