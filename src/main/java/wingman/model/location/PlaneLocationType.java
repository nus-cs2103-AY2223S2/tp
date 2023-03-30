package wingman.model.location;

/**
 * The types of locations that a plane
 * can have in a link relation.
 * This means that a plane is currently located
 * at a place.
 */
public enum PlaneLocationType {
    /**
     * The location where the plane stays
     */
    LOCATION_USING;

    @Override
    public String toString() {
        switch (this) {
        case LOCATION_USING:
            return "Plane";
        default:
            return "Unknown";
        }
    }
}
