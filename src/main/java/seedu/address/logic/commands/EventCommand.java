package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds a lab to the address book.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "touch";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the address book. "
            + "Parameters: "
            + "Tutorial / Lab " + "TUTORIAL_NAME / LAB_NAME "
            + "Restrictions: Not allowed to add lab and student with the same command!";

    /**
     * Creates an AddLab to add the specified {@code Lab}
     */
    public EventCommand(Event event) {
        requireNonNull(event);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
