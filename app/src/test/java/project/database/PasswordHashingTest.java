package project.database;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordHashingTest {

    @Test
    public void testHashPassword() {
        // Password to be hashed
        String password = "mySecurePassword";

        // Calculate the hash of the password
        String hashedPassword = PasswordHashing.hashPassword(password);

        // Ensure the hashed password is not null
        assertNotNull(hashedPassword);

        // Ensure the hashed password is not the same as the original password
        assertNotEquals(password, hashedPassword);
    }

    @Test
    public void testVerifyPassword() {
        // Password to be hashed
        String password = "mySecurePassword";

        // Calculate the hash of the password
        String hashedPassword = PasswordHashing.hashPassword(password);

        // Verify the password using the stored salt and hashed password
        assertTrue(PasswordHashing.verifyPassword(password, "$2a$10$zaLHJ5CoqkHHNbZUubz5ce", hashedPassword));
    }
}

