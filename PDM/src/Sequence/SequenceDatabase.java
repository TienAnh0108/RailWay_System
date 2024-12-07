package Sequence;

import Main.Database;
import Station.Station;
import Station.StationDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SequenceDatabase {
    public static ArrayList<Sequence> getAllSequences(String schedule, Database database) throws SQLException {
        ArrayList<Sequence> sequences = new ArrayList<>();
        String select = "SELECT * FROM `sequences` WHERE `ScheduleID` = " + schedule + ";";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            Sequence sequence = new Sequence();
            sequence.setNumber(rs.getInt("Number"));
            sequences.add(sequence);
        }
        return sequences;
    }

    public static String[] getSequencesNums(String schedule, Database database) throws SQLException {
        ArrayList<Sequence> sequences = getAllSequences(schedule, database);
        String[] array = new String[sequences.size()];
        for (int i = 0; i < sequences.size(); i++) {
            array[i] = String.valueOf(sequences.get(i).getNumber());
        }
        return array;
    }

    public static Sequence getSequenceNumber(String schedule, String num, Database db) throws SQLException {
        Sequence sequence = new Sequence();
        Station station;
        String select = "SELECT `StationID`, `ArriveTime`, `DepartureTime` FROM `sequences` WHERE `Number` = " + num + " AND `ScheduleID` = " + schedule + ";";
        ResultSet rs = db.getStatement().executeQuery(select);
        while (rs.next()) {
            sequence.setStation(rs.getString("StationID"));
            sequence.setArrivalTime(rs.getString("ArriveTime"));
            sequence.setDepartureTime(rs.getString("DepartureTime"));
        }
        station = StationDatabase.getStationByID(sequence.getStation(), db);
        sequence.setStation_name(station.getName());
        return sequence;
    }
}
