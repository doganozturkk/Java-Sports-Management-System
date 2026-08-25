package infrastructure;

public class Venue {

    private String name;
    private String phoneNumber;

    // Default constructor
    public Venue() {
    }

    // Constructor with name
    public Venue(String name) {
        this.name = name;
    }

    // Constructor with all attributes
    public Venue(String name, String phoneNumber) {
        this(name);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Venue:" + name + "," + phoneNumber;
    }
}