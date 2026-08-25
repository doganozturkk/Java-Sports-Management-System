package sport;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private String name;
    private ScheduledDate scheduledDate;

    private List<Match> matches = new ArrayList<>();

    public Event(String name, ScheduledDate scheduledDate) {
        this.name = name;
        this.scheduledDate = scheduledDate;
    }

    public String getName() {
        return name;
    }

    public ScheduledDate getScheduledDate() {
        return scheduledDate;
    }

    public void addMatch(Match m) {
        if (matches.contains(m)) {
            throw new IllegalArgumentException("Match already added!");
        }
        matches.add(m);
    }

    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        return name + " | Matches: " + matches.size();
    }
}