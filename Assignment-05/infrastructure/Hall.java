package infrastructure;

public class Hall extends Venue {

    private boolean eveningGames;

    public Hall(String name, String phoneNumber, int capacity, boolean eveningGames) {
        super(name, phoneNumber, capacity);
        this.eveningGames = eveningGames;
    }

    @Override
    public double returnCapacity() {
        return getMatchCapacity();
    }

    public boolean hasEveningGames() {
        return eveningGames;
    }

    @Override
    public String toString() {
        return "Hall | " + super.toString() +
                " | Evening: " + (eveningGames ? "Yes" : "No") +
                " | Capacity: " + getMatchCapacity();
    }
}