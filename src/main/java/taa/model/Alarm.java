package taa.model;

/**
 * Defines the Alarm class
 */
public class Alarm {
    private final int minutes;
    private final String message;

    /**
     * Construct an alarm instance with the specified minutes and message
     * @param minutes the minutes
     * @param message the message
     */
    public Alarm(int minutes, String message) {
        this.minutes = minutes;
        this.message = message;
    }

    public int getTime() {
        return this.minutes;
    }

    public String getMessage() {
        return this.message;
    }
}
