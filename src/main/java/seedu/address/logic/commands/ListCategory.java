package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CATEGORY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCategory extends Command {

    public static final String COMMAND_WORD = "lcat";

    public static final String MESSAGE_SUCCESS = "Listed all category";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCategoryList(PREDICATE_SHOW_ALL_CATEGORY);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
