package taa.model.alarm;

import javafx.animation.Timeline;

import java.util.Objects;

/**
 * Defines the Alarm class
 */
public class Alarm {
    private final int minutes;
    private final String message;
    private final int hashCode;
    private Timeline timeline;
    /**
     * Construct an alarm instance with the specified minutes and message
     * @param minutes the minutes
     * @param message the message
     */
    public Alarm(int minutes, String message) {
        this.minutes = minutes;
        this.message = message;
        this.hashCode = Objects.hash(this);
        this.timeline = null;
    }

    public int getTime() {
        return this.minutes;
    }

    public double getRemainingTime() {
        return this.timeline.getTotalDuration().
                subtract(this.timeline.getCurrentTime()).toMinutes();
    }

    public String getMessage() {
        return this.message;
    }

    public void addTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public String toString() {
        return "time left:" + this.getRemainingTime()
                + " minutes | notes: " + this.message;
    }

}
