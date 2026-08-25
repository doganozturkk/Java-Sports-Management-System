package infrastructure;

public class Hall extends Venue {

    private boolean eveningGames;

    public Hall(String name, String phoneNumber, int capacity, boolean eveningGames) {
        super(name, phoneNumber, capacity);
        this.eveningGames = eveningGames;
    }

    @Override
    public double returnCapacity() {
        double capacity = getMatchCapacity();
        if (eveningGames) capacity *= 1.2;
        return capacity;
    }

    @Override
    public String toString() {
        return super.toString() + "," + eveningGames;
    }
}