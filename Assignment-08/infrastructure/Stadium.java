package infrastructure;

public class Stadium extends Venue {

    private RunningTrack[] tracks;

    public Stadium(
            String name,
            String phoneNumber,
            int capacity
    ) {

        super(name, phoneNumber, capacity);

        tracks = new RunningTrack[8];

        for (int i = 0; i < tracks.length; i++) {

            tracks[i] = new RunningTrack(
                    i + 1,
                    100,
                    this
            );
        }
    }

    // SYNCHRONIZED TRACK ACCESS
    public RunningTrack prepareRaceTrack() {

        synchronized (tracks) {

            for (RunningTrack track : tracks) {

                if (!track.isInUse()) {

                    track.setInUse(true);

                    return track;
                }
            }
        }

        return null;
    }

    public int getTrackCount() {
        return tracks.length;
    }

    public RunningTrack[] getTracks() {
        return tracks;
    }

    @Override
    public double returnCapacity() {
        return getMatchCapacity();
    }

    @Override
    public String toString() {

        return "Stadium | " +
                super.toString() +
                " | Capacity: " +
                returnCapacity() +
                " | Tracks: " +
                tracks.length;
    }
}