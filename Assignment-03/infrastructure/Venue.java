package infrastructure;

import sport.Match;
import sport.SportsDiscipline;

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

    public void addMatch(Match match) {

        for (int i = 0; i < matchList.length; i++) {

            if (matchList[i] == null) {
                matchList[i] = match;
                return;
            }

        }
    }

    public void removeMatches() {

        for (int i = 0; i < matchList.length; i++) {
            matchList[i] = null;
        }

    }

    public double returnOccupancy() {

        int count = 0;

        for (Match match : matchList) {

            if (match != null) {
                count++;
            }

        }

        return (count / returnCapacity()) * 100;
    }

    public abstract double returnCapacity();

    public SportsDiscipline getSportsDiscipline() {
        return sportsDiscipline;
    }

    public void setSportsDiscipline(SportsDiscipline sportsDiscipline) {
        this.sportsDiscipline = sportsDiscipline;
    }

    protected int getMatchCapacity() {
        return matchList.length;
    }

    @Override
    public String toString() {
        return "Venue:" + name + "," + phoneNumber + "," + sportsDiscipline;
    }
}