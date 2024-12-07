package Train;

import Main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainDatabase {

    public static ArrayList<Train> getAllTrains(Database database) throws SQLException {
        String select = "SELECT * FROM `trains`";
        ArrayList<Train> trains = new ArrayList<>();
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            Train train = new Train();
            train.setId(rs.getInt("ID"));
            train.setName(rs.getString("Name"));
            trains.add(train);
        }
        return trains;
    }

    public static String[] getTrainIDs(Database database) throws SQLException {
        ArrayList<Train> trains = getAllTrains(database);
        String[] array = new String[trains.size()];
        for (int i = 0; i < trains.size(); i++) {
            array[i] = String.valueOf(trains.get(i).getId());
        }
        return array;
    }

    public static Train getTrainByID(String id, Database database) throws SQLException {
        Train t = new Train();
        String select = "SELECT `ID`, `Name` FROM `trains` WHERE ID = " + id + ";";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            t.setId(rs.getInt("ID"));
            t.setName(rs.getString("Name"));
        }
        return t;
    }

    public static String getTrainName(Database database) throws SQLException {
        String select = "SELECT `Name` FROM `trains`";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            return rs.getString("Name");
        }
        return null;
    }
}
