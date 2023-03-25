package seedu.address.model;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeParseException;

public abstract class Status {

    public abstract String getInstantInISO();

    public abstract Instant getTimestamp();

    public abstract Duration getDurationSinceLastUpdate();

    public static boolean isValidTimestamp(String test) {
        try {
            Instant.parse(test);
        } catch (DateTimeParseException dtpe) {
            return false;
        }
        return true;
    }
}
