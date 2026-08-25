package infrastructure;

public class Hall extends Venue {

    private boolean eveningGames;

    public Hall(String name, String phoneNumber, int matchCapacity, boolean eveningGames) {
        super(name, phoneNumber, matchCapacity);
        this.eveningGames = eveningGames;
    }

    public boolean isEveningGames() {
        return eveningGames;
    }

    public void setEveningGames(boolean eveningGames) {
        this.eveningGames = eveningGames;
    }

    @Override
    public double returnCapacity() {

        double capacity = getMatchCapacity();

        if (eveningGames) {
            capacity = capacity * 1.2;
        }

        return capacity;
    }

    @Override
    public String toString() {
        return super.toString() + "," + eveningGames;
    }
}