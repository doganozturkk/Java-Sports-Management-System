package infrastructure;

public class Stadium extends Venue {

    public Stadium(String name, String phoneNumber, int matchCapacity) {
        super(name, phoneNumber, matchCapacity);
    }

    @Override
    public double returnCapacity() {
        return getMatchCapacity();
    }
}