package infrastructure;

import sport.*;

public abstract class Venue {

    private String name;
    private String phoneNumber;
    private Match[] matchList;
    private SportsDiscipline sportsDiscipline;

    public Venue(String name, String phoneNumber, int matchCapacity) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.matchList = new Match[matchCapacity];
    }

    public void addMatch(Match match) throws AddingMatchException {

        if (match == null) {
            throw new AddingMatchException("Match is null!");
        }

        if (this instanceof Stadium) {
            int day = match.getScheduledDate().getScheduledDate().getDayOfMonth();
            if (day >= 20) {
                throw new AddingMatchException("Stadium cannot host after day 20!");
            }
        }

        for (Match m : matchList) {
            if (m != null && m.equals(match)) {
                throw new AddingMatchException("Match already added!");
            }
        }

        for (int i = 0; i < matchList.length; i++) {
            if (matchList[i] == null) {
                matchList[i] = match;
                match.setVenue(this);
                return;
            }
        }

        throw new AddingMatchException("Venue is full!");
    }

    public Match[] getMatchList() {
        return matchList;
    }

    // 🔥 GEREKLİ
    protected int getMatchCapacity() {
        return matchList.length;
    }

    // 🔥 GEREKLİ (override hatası için)
    public abstract double returnCapacity();

    public void setSportsDiscipline(SportsDiscipline sportsDiscipline) {
        this.sportsDiscipline = sportsDiscipline;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String n = (name != null && !name.isBlank()) ? name : "Unnamed";
        String p = (phoneNumber != null && !phoneNumber.isBlank()) ? phoneNumber : "No Phone";
        String d = (sportsDiscipline != null) ? sportsDiscipline.toString() : "N/A";

        return n + " | Phone: " + p + " | Discipline: " + d;
    }
}