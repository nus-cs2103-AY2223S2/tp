package wingman.model.crew;

/**
 * The types of crew for a flight.
 */
public enum FlightCrewType {
    /**
     * The cabin service director
     */
    CABIN_SERVICE_DIRECTOR,
    /**
     * The senior flight attendant
     */
    SENIOR_FLIGHT_ATTENDANT,
    /**
     * The flight attendant
     */
    FLIGHT_ATTENDANT,
    /**
     * The trainee
     */
    TRAINEE;

    @Override
    public String toString() {
        switch (this) {
        case CABIN_SERVICE_DIRECTOR:
            return "Cabin Service Director";
        case SENIOR_FLIGHT_ATTENDANT:
            return "Senior Flight Attendant";
        case FLIGHT_ATTENDANT:
            return "Flight Attendant";
        case TRAINEE:
            return "Trainee";
        default:
            return "Unknown";
        }
    }
}
