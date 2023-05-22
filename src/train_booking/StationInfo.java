/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train_booking;

/**
 *
 * @author mahmo
 */
public class StationInfo {
    private final String trip_id;
    private final String station_name;
    private final String duration;

    public StationInfo(String trip_id, String station_name, String duration) {
        this.trip_id = trip_id;
        this.station_name = station_name;
        this.duration = duration;
    }

    public String getStation_name() {
        return station_name;
    }
    public String getDuration() {
        return duration;
    }
    public String getTrip_id() {
        return trip_id;
    }
}
