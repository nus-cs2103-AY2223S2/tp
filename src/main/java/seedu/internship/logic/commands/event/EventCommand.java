package seedu.internship.logic.commands.event;

import seedu.internship.logic.commands.Command;

/**
 * Represents an event type command with hidden internal logic and the ability to be executed.
 */
public abstract class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
}
