package infrastructure;

import java.io.Serializable;

public class RunningTrack implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean inUse;
    private int length;
    private int trackNumber;
    private Stadium stadium;

    // Constructor
    public RunningTrack(
            int trackNumber,
            int length,
            Stadium stadium
    ) {

        this.trackNumber = trackNumber;
        this.length = length;
        this.stadium = stadium;

        this.inUse = false;
    }

    // Returns whether the track is occupied
    public boolean isInUse() {
        return inUse;
    }

    // Changes the usage status of the track
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    // Returns the track length
    public int getLength() {
        return length;
    }

    // Returns the track number
    public int getTrackNumber() {
        return trackNumber;
    }

    // Returns the stadium of this track
    public Stadium getStadium() {
        return stadium;
    }

    // String representation used in console/debug output
    @Override
    public String toString() {

        return "Track #" + trackNumber +
                " | Length: " + length;
    }
}