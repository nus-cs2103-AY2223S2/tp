package taa.model.alarm;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

import javafx.animation.Timeline;

/**
 * Defines the Alarm class
 */
//Credits: Solution below adapted from chatGPT3.5 codes
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

    /**
     * Return the preset time of the alarm
     * @return the preset time of the alarm in integer minutes
     */
    public int getTime() {
        return this.minutes;
    }

    public double getRemainingTime() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        Double timeLeft = this.timeline.getTotalDuration()
                        .subtract(this.timeline.getCurrentTime()).toMinutes();
        return Double.parseDouble(df.format(timeLeft));
    }

    /**
     * Stops the timeline of the alarm
     */
    public void stopTimeLine() {
        this.timeline.stop();
    }

    /**
     * Return the message of the alarm
     * @return the message of the alarm
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Add the new timeline to the alarm
     * @param timeline the timeline to add
     */
    public void addTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public String toString() {
        return "Time left: " + this.getRemainingTime()
                + " mins | Note: " + this.message;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Alarm) {
            return ((Alarm) other).hashCode == this.hashCode;
        }
        return false;
    }

}
