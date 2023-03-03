package seedu.address.logic.commands;

/**
 * The mode at which the command is executed.
 */
public enum OperationMode {
    /**
     * The manager is currently managing pilots.
     */
    PILOT,
    /**
     * The manager is currently managing planes.
     */
    PLANE,
    /**
     * The manager is currently managing flights.
     */
    FLIGHT,
    /**
     * The manager is currently managing crew.
     */
    CREW,
    /**
     * The manager is currently managing locations.
     */
    LOCATION;

    /**
     * Converts an integer to a mode.
     *
     * @param i the integer to convert, should be between 0 and 4.
     * @return the mode corresponding to the integer.
     */
    public static OperationMode fromInt(int i) {
        switch (i) {
        case 0:
            return PILOT;
        case 1:
            return PLANE;
        case 2:
            return FLIGHT;
        case 3:
            return CREW;
        case 4:
            return LOCATION;
        default:
            throw new IllegalArgumentException(
                    "Invalid mode index: " + i + ", should be between 0 and 4");
        }
    }

    /**
     * Converts the mode to an integer, for storage.
     *
     * @return the integer representation of the mode.
     */
    public int toInt() {
        switch (this) {
        case PILOT:
            return 0;
        case PLANE:
            return 1;
        case FLIGHT:
            return 2;
        case CREW:
            return 3;
        case LOCATION:
            return 4;
        default:
            throw new IllegalArgumentException("Invalid mode: " + this);
        }
    }
}
