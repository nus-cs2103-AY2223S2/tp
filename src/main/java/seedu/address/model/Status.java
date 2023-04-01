package seedu.address.model;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeParseException;

/**
 * Represents the abstract status that can be possessed by a class, that has a timestamp of
 * when its status was set.
 */
public abstract class Status {

    public abstract String getInstantInIso();

    public abstract Instant getTimestamp();

    public abstract Duration getDurationSinceLastUpdate();

    /**
     * Returns the English representation of a Duration, only showing the least granular time.
     */
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        if (seconds < 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        if (seconds == 0) {
            return "0 seconds";
        }
        if (seconds >= 60 * 60 * 24 * 365) {
            long years = seconds / (60 * 60 * 24 * 365);
            return years + " year" + (years > 1 ? "s" : "");
        }
        if (seconds >= 60 * 60 * 24) {
            long days = seconds / (60 * 60 * 24);
            return days + " day" + (days > 1 ? "s" : "");
        }
        if (seconds >= 60 * 60) {
            long hours = seconds / (60 * 60);
            return hours + " hour" + (hours > 1 ? "s" : "");
        }
        if (seconds >= 60) {
            long minutes = seconds / 60;
            return minutes + " minute" + (minutes > 1 ? "s" : "");
        }
        return seconds + " second" + (seconds > 1 ? "s" : "");
    }

    /**
     *  Returns true if the input String is parsable into a timestamp.
     */
    public static boolean isValidTimestamp(String test) {
        try {
            Instant.parse(test);
        } catch (DateTimeParseException dtpe) {
            return false;
        }
        return true;
    }
}
