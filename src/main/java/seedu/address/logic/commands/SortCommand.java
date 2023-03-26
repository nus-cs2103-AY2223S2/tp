package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts all persons in the address book and shows it to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts list by given attribute\n"
            + "Parameters: ATTRIBUTE (1 - pay rate or 2 - name)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Sorted all persons according to given attribute.\n";

    private final int attribute;

    public SortCommand(int attribute) {
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
