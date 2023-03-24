package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Lists all persons in the address book to the user.
 */
public class SortAscCommand extends Command {

    public static final String COMMAND_WORD = "sortAsc";

    public static final String MESSAGE_SUCCESS = "sorted by ascending business size";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList("asc");
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
