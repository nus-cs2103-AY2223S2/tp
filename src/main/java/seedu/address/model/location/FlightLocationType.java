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
            return "Departing from";
        case LOCATION_ARRIVAL:
            return "Arriving at";
        default:
            return "Unknown";
        }
    }
}
