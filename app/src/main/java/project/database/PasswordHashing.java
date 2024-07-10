package project.database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.mindrot.jbcrypt.BCrypt;


public class PasswordHashing {
    public static String hashPassword(String password) {
        // Password to be hashed
        String pass_str = password;

        // there could be challenges with labraries of different teammates producing different salt
        // hence to avoid the particular issues, we generate the salt once and reuse every time
        // String encoded_salt_value = BCrypt.gensalt(10);
        // System.out.println("encoded_salt_value: " + encoded_salt_value);
        // The output value was: $2a$10$zaLHJ5CoqkHHNbZUubz5ce
        String encoded_salt_value = "$2a$10$zaLHJ5CoqkHHNbZUubz5ce";


        // Hash a password for the first time
        String hashed_pass_str = BCrypt.hashpw(pass_str, encoded_salt_value);

        // gensalt's log_rounds parameter determines the complexity
        // the work factor is 2**log_rounds, and the default is 10

//        System.out.println("Hashed Password: " + hashed_pass_str);
        return hashed_pass_str;
    }

    // Verify a password against a hashed password and salt
    public static boolean verifyPassword(String enteredPassword, String storedSalt, String storedHashedPassword) {
        String computedHash = hashPassword(enteredPassword);
        return computedHash.equals(storedHashedPassword);
    }
}

