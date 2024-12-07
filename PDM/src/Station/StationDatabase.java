package Station;

import Main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StationDatabase {
    public static ArrayList<Station> getAllStations(Database database) throws SQLException {
        String select = "SELECT * FROM `stations`";
        ArrayList<Station> stations = new ArrayList<>();
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            Station station = new Station();
            station.setId(rs.getInt("ID"));
            station.setName(rs.getString("Name"));
            stations.add(station);
        }
        return stations;
    }

    public static String[] getStationIDs(Database database) throws SQLException {
        ArrayList<Station> stations = getAllStations(database);
        String[] array = new String[stations.size()];
        for (int i = 0; i < stations.size(); i++) {
            array[i] = String.valueOf(stations.get(i).getId());
        }
        return array;
    }

    public static Station getStationByID(String id, Database database) throws SQLException {
        Station st = new Station();
        String select = "SELECT `ID`, `Name` FROM `stations` WHERE ID = " + id + ";";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            st.setId(rs.getInt("ID"));
            st.setName(rs.getString("Name"));
        }
        return st;
    }
}
