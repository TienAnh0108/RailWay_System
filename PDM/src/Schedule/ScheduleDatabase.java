package Schedule;

import Main.Database;
import Station.Station;
import Train.TrainDatabase;
import Station.StationDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDatabase {
    public static ArrayList<Schedule> getAllSchedules(Database database) throws SQLException {
        ArrayList<Schedule> schedules = new ArrayList<>();
        ArrayList<Integer> trains = new ArrayList<>();
        String select = "SELECT * FROM `schedules`;";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            Schedule schedule = new Schedule();
            schedule.setId(rs.getInt("ID"));
            schedules.add(schedule);
        }
        return schedules;
    }

    public static String[] getIDs(Database database) throws SQLException {
        ArrayList<Schedule> schedules = getAllSchedules(database);
        String[] array = new String[schedules.size()];
        for (int i = 0; i < schedules.size(); i++) {
            array[i] = String.valueOf(schedules.get(i).getId());
        }
        return array;
    }

    public static Schedule getSchedule(String id, Database database) throws SQLException {
        Schedule schedule = new Schedule();
        Station startStation, endStation;
        String select = "SELECT `ID`, `TrainID`, `Start`, `Destination`, `DepartureTime`, `ArriveTime` FROM `schedules` WHERE `TrainID` = " + id + ";";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            schedule.setId(rs.getInt("ID"));
            schedule.setStart(rs.getString("Start"));
            schedule.setDestination(rs.getString("Destination"));
            schedule.setDepartureTime(rs.getString("DepartureTime"));
            schedule.setArrivalTime(rs.getString("ArriveTime"));
        }
        startStation = StationDatabase.getStationByID(schedule.getStart(), database);
        endStation = StationDatabase.getStationByID(schedule.getDestination(), database);
        schedule.setStartStation(startStation.getName());
        schedule.setDestinationStation(endStation.getName());
        return schedule;
    }

    public static String getScheduleID(String id, Database database) throws SQLException {
        Schedule schedule = new Schedule();
        String select = "SELECT `ID` FROM `schedules` WHERE `TrainID` = " + id + ";";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            schedule.setId(rs.getInt("ID"));
        }
        return String.valueOf(schedule.getId());
    }
}
