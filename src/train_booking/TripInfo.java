/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train_booking;

/**
 *
 * @author mahmo
 */
public class TripInfo {
    private final String train_id;
    private final String trip_source;
    private final String trip_destination;
    private final String trip_date;
    private final String trip_duration;
    private final double trip_price_unit;
    private final int trip_seats;

    TripInfo(String train_id, String trip_source, String trip_destination, String trip_date, String trip_duration, double trip_price_unit, int trip_seats) {
        this.train_id = train_id;
        this.trip_source = trip_source;
        this.trip_destination = trip_destination;
        this.trip_date = trip_date;
        this.trip_duration = trip_duration;
        this.trip_price_unit = trip_price_unit;
        this.trip_seats = trip_seats;
    }

    public String getTrain_id() {
        return train_id;
    }
    public double getTrip_price_unit() {
        return trip_price_unit;
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

