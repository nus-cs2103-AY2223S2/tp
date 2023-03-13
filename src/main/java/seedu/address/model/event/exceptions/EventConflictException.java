package seedu.address.model.event.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Signals that there is a conflict of events
 */
public class EventConflictException extends CommandException {
    public EventConflictException(String eventConflictedWith) {
        super("Event conflict with " + eventConflictedWith);
    }
}
