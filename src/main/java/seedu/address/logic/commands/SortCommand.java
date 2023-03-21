package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Sorts address book based on lead attribute.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    //NEEDS TO BE CHANGEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";
    private final String attribute;

    public SortCommand(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(attribute);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && attribute.equals(((SortCommand) other).attribute)); // state check
    }
}
