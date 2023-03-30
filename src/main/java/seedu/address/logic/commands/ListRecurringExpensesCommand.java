package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;

import seedu.address.model.Model;
import seedu.address.ui.ScreenType;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCategoryCommand extends Command {

    public static final String COMMAND_WORD = "lcat";

    public static final String MESSAGE_SUCCESS = "Listed all categories";


    @Override
    public CommandResult execute(Model dataModel) {
        requireNonNull(dataModel);
        dataModel.updateFilteredExpensesList(PREDICATE_SHOW_ALL_EXPENSES);
        return new CommandResult(MESSAGE_SUCCESS, ScreenType.CATEGORY_SCREEN);
    }
}
