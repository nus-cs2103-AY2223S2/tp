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
