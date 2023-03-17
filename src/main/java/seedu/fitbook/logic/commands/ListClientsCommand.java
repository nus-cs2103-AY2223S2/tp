package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.model.FitBookModel.PREDICATE_SHOW_ALL_PERSONS;

import seedu.fitbook.model.FitBookModel;

/**
 * Lists all clients in FitBook to the user.
 */
public class ListClientsCommand extends Command {

    public static final String COMMAND_WORD = "listClients";

    public static final String MESSAGE_SUCCESS = "Listed all clients";


    @Override
    public CommandResult execute(FitBookModel model) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
