package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.ModelNew.PREDICATE_SHOW_ALL_OPENINGS;

import seedu.address.model.ModelNew;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommandNew extends CommandNew {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResultNew execute(ModelNew model) {
        requireNonNull(model);
        model.updateFilteredOpeningList(PREDICATE_SHOW_ALL_OPENINGS);
        return new CommandResultNew(MESSAGE_SUCCESS);
    }
}
