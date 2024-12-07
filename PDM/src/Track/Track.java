package Track;

import Station.Station;

public class Track {
    private int id;
    private String StartStationName, EndStationName;
    private String start, end;

    public Track() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartStationName() {
        return StartStationName;
    }

    public void setStartStationName(String startStationName) {
        StartStationName = startStationName;
    }

    public String getEndStationName() {
        return EndStationName;
    }

    public void setEndStationName(String endStationName) {
        EndStationName = endStationName;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
