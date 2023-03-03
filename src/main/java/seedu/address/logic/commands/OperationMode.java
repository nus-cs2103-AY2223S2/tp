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
}
