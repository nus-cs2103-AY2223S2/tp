package seedu.address.model.event.exceptions;

/**
 * Signals that the event is not in the isolated event list.
 */
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Cannot find such event");
    }
}

