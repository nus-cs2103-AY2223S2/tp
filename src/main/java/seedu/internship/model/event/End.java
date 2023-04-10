package seedu.internship.model.event;

import java.time.LocalDateTime;

/**
 * Represents an Event's end timing (time and date).
 */
public class End extends DateTime {
    /**
     * Constructs a {@code End}.
     *
     * @param endDateTime A valid endDateTime for a End.
     */
    public End(LocalDateTime endDateTime) {
        super(endDateTime);
    }

}
