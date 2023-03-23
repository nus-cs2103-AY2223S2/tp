package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts all persons in the address book and shows it to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortAddressBook();
        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
