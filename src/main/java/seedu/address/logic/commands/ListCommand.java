package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.FitBookModel.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.FitBookModel;

/**
 * Lists all clients in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";


    @Override
    public CommandResult execute(FitBookModel model) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
