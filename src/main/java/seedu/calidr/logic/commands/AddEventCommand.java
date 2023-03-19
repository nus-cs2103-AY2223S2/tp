package seedu.calidr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.calidr.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.calidr.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.calidr.logic.parser.CliSyntax.PREFIX_TO;

import seedu.calidr.logic.commands.exceptions.CommandException;
import seedu.calidr.model.Model;
import seedu.calidr.model.task.Event;

/**
 * Adds an event to the task list.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a to-do to the task list. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_FROM + "START DATE-TIME "
            + PREFIX_TO + " END DATE-TIME"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Lecture "
            + PREFIX_FROM + "05-04-2023 1400 "
            + PREFIX_TO + "05-04-2023 1600";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the task list.";

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

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
