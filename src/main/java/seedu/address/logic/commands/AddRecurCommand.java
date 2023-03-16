package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECUR;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds a recurring event to the address book.
 */
public class AddRecurCommand extends Command {

    //Change in next version
    public static final String COMMAND_WORD = "recur";

    public static final String MESSAGE_USAGE = "To customise in next version";

    public static final String MESSAGE_SUCCESS = "New recurring event added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECUR = "This recurring event already exists in the address book";

    private final Event toAdd;

    /**
     * Creates an AddRecur to add the specified {@code Recur}
     */
    public AddRecurCommand(Event recurring, boolean lab, boolean tutorial, boolean consultation) {
        requireNonNull(recurring);
        toAdd = recurring;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasRecur(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECUR);
        }

        model.addRecur(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));

        for (int i=0;i<)


    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRecurCommand // instanceof handles nulls
                && toAdd.equals(((AddRecurCommand) other).toAdd));
    }
}
