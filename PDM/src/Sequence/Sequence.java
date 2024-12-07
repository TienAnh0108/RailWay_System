package Sequence;

import Schedule.Schedule;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Sequence {
    private int id;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String station, station_name;
    private int number;
    private Schedule schedule;

    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Sequence() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }
}
