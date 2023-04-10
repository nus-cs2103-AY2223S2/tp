package wingman.model.location;

/**
 * The types of locations that a pilot member
 * can have in a link relation.
 */
public enum PilotLocationType {
    /**
     * The location where the pilot stays
     */
    LOCATION_USING;

    @Override
    public String toString() {
        switch (this) {
        case LOCATION_USING:
            return "Pilot";
        default:
            return "Unknown";
        }
    }
}
