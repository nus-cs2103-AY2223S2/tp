package ezschedule.slots;

/**
 * Represent a booking made for a venue/location.
 */
public class VenueBooking extends Slot {

    private String venue;
    private String bookedBy;

    VenueBooking(String venue, String bookedBy, String date, String startTime, String endTime) {
        super(date, startTime, endTime);
        this.venue = venue;
        this.bookedBy = bookedBy;
    }
}
