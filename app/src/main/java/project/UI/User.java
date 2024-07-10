package project.UI;

public class User {
    private String role; // "guest", "general", "admin"

    public User(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
