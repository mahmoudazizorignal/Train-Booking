public class StationInfo {
    private String trip_id;
    private String station_name;
    private String duration;

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
