package seedu.internship.logic.commands.event;


import seedu.internship.logic.commands.Command;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
}