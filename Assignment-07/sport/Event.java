package sport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private ScheduledDate scheduledDate;

    private List<Match> matches = new ArrayList<>();

    public Event(String name, ScheduledDate scheduledDate) {
        this.name = name;
        this.scheduledDate = scheduledDate;
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public ScheduledDate getScheduledDate() {
        return scheduledDate;
    }

    public List<Match> getMatches() {
        return matches;
    }

    // SETTERS (UPDATE için kritik)
    public void setName(String name) {
        this.name = name;
    }

    public void setScheduledDate(ScheduledDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    // MATCH ADD
    public void addMatch(Match m) {

        if (m == null) {
            throw new IllegalArgumentException("Match is null!");
        }

        if (matches.contains(m)) {
            throw new IllegalArgumentException("Match already added!");
        }

        matches.add(m);
    }

    // MATCH REMOVE (serialization sonrası cleanup için iyi olur)
    public void removeMatch(Match m) {
        matches.remove(m);
    }

    // VALID CHECK HELPER (opsiyonel ama güzel)
    public boolean containsMatch(Match m) {
        return matches.contains(m);
    }

    // DISPLAY
    @Override
    public String toString() {

        return name +
                " | Date: " +
                (scheduledDate != null
                        ? scheduledDate.getScheduledDate()
                        : "N/A") +
                " | Matches: " +
                matches.size();
    }
}