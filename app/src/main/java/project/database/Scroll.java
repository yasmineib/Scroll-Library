package project.database;

public class Scroll {
    private int id;
    private String title;
    private String description;
    private String name;

    // Constructor for creating a Scroll object
    public Scroll(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Other methods for the Scroll class
    public int getId() {
        return this.id;
    }
    public String getName(){return this.name; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}