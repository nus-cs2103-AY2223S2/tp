package seedu.internship.model.event;

import java.time.LocalDateTime;

/**
 * Represents an Event's end timing (time and date).
 */
public class End extends DateTime {
    /**
     * Constructs a {@code Start}.
     *
     * @param endDateTime A valid startDateTime for a Start.
     */
    public End(LocalDateTime endDateTime) {
        super(endDateTime);
    }

}
