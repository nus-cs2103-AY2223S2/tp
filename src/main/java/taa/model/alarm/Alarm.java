package taa.model.alarm;

import javafx.animation.Timeline;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        Double timeLeft = this.timeline.getTotalDuration().
                subtract(this.timeline.getCurrentTime()).toMinutes();
        return Double.parseDouble(df.format(timeLeft));
    }

    public void stopTimeLine() {
        this.timeline.stop();
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
        return "Time left: " + this.getRemainingTime()
                + " mins | Note: " + this.message;
    }

    @Override
    public boolean equals(Object other) {
        return ((Alarm)other).hashCode == this.hashCode;
    }

}
