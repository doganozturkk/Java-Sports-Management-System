package infrastructure;

public class Stadium extends Venue {

    public Stadium(String name, String phoneNumber, int capacity) {
        super(name, phoneNumber, capacity);
    }

    @Override
    public double returnCapacity() {
        return getMatchCapacity();
    }
}