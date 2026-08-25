package si.um.opj.ozturk.ui;

import sport.*;
import infrastructure.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataStore implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final List<Athlete> athletes = new ArrayList<>();
    public static final List<Event> events = new ArrayList<>();
    public static final List<Venue> venues = new ArrayList<>();
    public static final List<Match> matches = new ArrayList<>();
}