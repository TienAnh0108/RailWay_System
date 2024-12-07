package Schedule;

import Station.Station;
import Train.Train;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
    private int id;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Train train;
    private String start, destination;
    private String startStation, destinationStation;

    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Schedule() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getDepartureTime() {
        return departureTime.format(timeFormatter);
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = LocalTime.parse(departureTime, timeFormatter);
    }

    public String getArrivalTime() {
        return arrivalTime.format(timeFormatter);
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = LocalTime.parse(arrivalTime, timeFormatter);
    }

    public String getDeptHour() {
        return departureTime.format(DateTimeFormatter.ofPattern("HH"));
    }

    public String getDeptMinute() {
        return arrivalTime.format(DateTimeFormatter.ofPattern("mm"));
    }

    public String getArrivalHour() {
        return arrivalTime.format(DateTimeFormatter.ofPattern("HH"));
    }

    public String getArrivalMinute() {
        return arrivalTime.format(DateTimeFormatter.ofPattern("mm"));
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }
}
