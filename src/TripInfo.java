public class TripInfo {
    private String train_id;
    private String trip_source;
    private String trip_destination;
    private String trip_date;
    private String trip_duration;
    private double trip_unit_price;
    private int trip_seats;

    TripInfo(String train_id, String trip_source, String trip_destination, String trip_date, String trip_duration, double trip_unit_price, int trip_seats) {
        this.train_id = train_id;
        this.trip_source = trip_source;
        this.trip_destination = trip_destination;
        this.trip_date = trip_date;
        this.trip_duration = trip_duration;
        this.trip_unit_price = trip_unit_price;
        this.trip_seats = trip_seats;
    }

    public String getTrain_id() {
        return train_id;
    }
    public double getTrip_unit_price() {
        return trip_unit_price;
    }
    public String getTrip_date() {
        return trip_date;
    }
    public int getTrip_seats() {
        return trip_seats;
    }
    public String getTrip_destination() {
        return trip_destination;
    }
    public String getTrip_duration() {
        return trip_duration;
    }
    public String getTrip_source() {
        return trip_source;
    }
}
