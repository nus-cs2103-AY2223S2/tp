package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.EventTask;

/**
 * Adds a DeadlineTask to the address book.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task with the specified duration "
            + "Parameters: "
            + PREFIX_TASK + "TASK_DESCRIPTION "
            + PREFIX_DATE + "DATE "
            + PREFIX_DATE + "DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK + "manage open house"
            + PREFIX_DATE + "02/11/2023"
            + PREFIX_DATE + "07/11/2023";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    private final EventTask toAdd;

    /**
     * Creates an AddCommand to add the specified {@code DeadlineTask}
     */
    public EventCommand(EventTask task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventCommand // instanceof handles nulls
                && toAdd.equals(((EventCommand) other).toAdd));
    }
}
