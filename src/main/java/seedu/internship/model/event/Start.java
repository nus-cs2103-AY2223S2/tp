package seedu.internship.model.event;

import java.time.LocalDateTime;

/**
 * Represents an Event's start timing (time and date).
 */
public class Start extends DateTime {

    /**
     * Constructs a {@code Start}.
     *
     * @param startDateTime A valid startDateTime for a Start.
     */
    public Start(LocalDateTime startDateTime) {
        super(startDateTime);
    }

}
