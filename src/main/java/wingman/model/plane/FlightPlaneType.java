package wingman.model.plane;

/**
 * The type of link between a plane and a flight
 */
public enum FlightPlaneType {

    /**
     * The plane being used
     */
    PLANE_USING;

    @Override
    public String toString() {
        switch (this) {
        case PLANE_USING:
            return "Plane";
        default:
            return "Unknown";
        }
    }
}
