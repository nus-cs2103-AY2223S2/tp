package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.ui.ScreenType;

/**
 * Lists all recurring expenses in the FastTrack to the user.
 */
public class ListRecurringExpensesCommand implements ListCommand {

    public static final String COMMAND_WORD = "lrec";

    public static final String MESSAGE_SUCCESS = "Listed all recurring expenses";


    @Override
    public CommandResult execute(Model dataModel) {
        requireNonNull(dataModel);
        dataModel.updateFilteredExpensesList(PREDICATE_SHOW_ALL_EXPENSES);
        return new CommandResult(MESSAGE_SUCCESS, ScreenType.RECURRING_EXPENSE_SCREEN);
    }
}
