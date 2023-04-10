package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts all persons in the address book and shows it to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all athletes in the contact list according to the provided attribute.\n"
            + "Parameters: ATTRIBUTE (1 - Name or 2 - Pay rate)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Sorted all athletes successfully.\n";
    public static final String MESSAGE_INVALID_SORT_FAILURE = "Invalid sort index, please enter either: "
            + "sort 1 or sort 2.\n";

    private final int attribute;

    /**
     * sort command constructor
     * @param attribute
     * @throws CommandException
     */
    public SortCommand(int attribute) throws CommandException {
        if (attribute != 1 && attribute != 2) {
            throw new CommandException(MESSAGE_INVALID_SORT_FAILURE);
        }
        this.attribute = attribute;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortAddressBook(attribute);
        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
