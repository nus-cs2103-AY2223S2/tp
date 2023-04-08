package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts address book based on lead attribute.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons based on a specific attribute "
            + "the specified attribute (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: ATTRIBUTE \n"
            + "Example: " + COMMAND_WORD + " name";
    private final String attribute;

    public SortCommand(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.sortPersonList(attribute);
        } catch (RuntimeException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_SORTED_OVERVIEW, attribute));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && attribute.equals(((SortCommand) other).attribute)); // state check
    }
}
