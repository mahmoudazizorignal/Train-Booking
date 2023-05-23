/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train_booking;

/**
 *
 * @author mahmo
 */
public class TripInfo extends TripBookingInfo {
    private final String train_id;
    private final String trip_duration;
    private final double trip_price_unit;
    private final int trip_seats;

    TripInfo(String train_id, String trip_source, String trip_destination, String trip_date, String trip_duration, double trip_price_unit, int trip_seats) {
        super(trip_source, trip_destination, trip_date);
        this.train_id = train_id;
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
    public int getTrip_seats() {
        return trip_seats;
    }
    public String getTrip_duration() {
        return trip_duration;
    }

}

