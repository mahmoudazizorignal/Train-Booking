/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train_booking;

/**
 *
 * @author mahmo
 */
public class TripBookingInfo {
    private final String trip_source;
    private final String trip_destination;
    private final String trip_date;

    public TripBookingInfo(String trip_source, String trip_destination, String trip_date) {
        this.trip_source = trip_source;
        this.trip_destination = trip_destination;
        this.trip_date = trip_date;
    }

    public String getTrip_source() {
        return trip_source;
    }

    public String getTrip_destination() {
        return trip_destination;
    }

    public String getTrip_date() {
        return trip_date;
    }
    
}
