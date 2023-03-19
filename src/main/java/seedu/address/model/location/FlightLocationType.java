package seedu.address.model.location;

public enum FlightLocationType {
    /**
     * The departure location.
     */
    LOCATION_DEPARTURE,
    /**
     * The arrival location.
     */
    LOCATION_ARRIVAL;

    @Override
    public String toString() {
        switch (this) {
        case LOCATION_DEPARTURE:
            return "Location Departure";
        case LOCATION_ARRIVAL:
            return "Location Arrival";
        default:
            return "Unknown";
        }
    }
}
