package Track;

import Main.Database;
import Station.Station;
import Station.StationDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackDatabase {
    public static ArrayList<Track> getAllTracks(Database database) throws SQLException {
        String select = "SELECT * FROM `tracks`";
        ArrayList<Track> tracks = new ArrayList<>();
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            Track track = new Track();
            track.setId(rs.getInt("ID"));
            tracks.add(track);
        }
        return tracks;
    }

    public static String[] getTrackIDs(Database database) throws SQLException {
        ArrayList<Track> tracks = getAllTracks(database);
        String[] array = new String[tracks.size()];
        for (int i = 0; i < tracks.size(); i++) {
            array[i] = String.valueOf(tracks.get(i).getId());
        }
        return array;
    }

    public static Track getTrackByID(String id, Database database) throws SQLException {
        Track track = new Track();
        Station startStation, endStation;
        String select = "SELECT `ID`, `StartStation`, `EndStation` FROM `tracks` WHERE ID = " + id + ";";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            track.setId(rs.getInt("ID"));
            track.setStart(rs.getString("StartStation"));
            track.setEnd(rs.getString("EndStation"));
        }
        startStation = StationDatabase.getStationByID(track.getStart(), database);
        endStation = StationDatabase.getStationByID(track.getEnd(), database);
        track.setStartStationName(startStation.getName());
        track.setEndStationName(endStation.getName());
        return track;
    }
}
