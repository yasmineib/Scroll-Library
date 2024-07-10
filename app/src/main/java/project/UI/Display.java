package project.UI;

public interface Display {

    public abstract void displayUserType(String username);
    public void displayStart();
    public abstract void displayMenu();
    public abstract void viewScrolls();

    public default void quit() {
        System.out.println("Thank you for visiting!");
        System.exit(0);
    }
}
