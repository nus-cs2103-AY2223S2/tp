package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliEventSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliEventSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliEventSyntax.PREFIX_START;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds a person to the scheduler.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the scheduler. "
        + "Parameters: "
        + PREFIX_NAME + "NAME 1 "
        + PREFIX_START + "2023-05-01 "
        + PREFIX_END + "2023-05-05 "
        + "Parameters: "
        + PREFIX_NAME + "NAME 2 "
        + PREFIX_START + "2024-05-01 "
        + PREFIX_END + "2024-05-05 ";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the scheduler";

    private final Event toAdd;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddEventCommand // instanceof handles nulls
            && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
