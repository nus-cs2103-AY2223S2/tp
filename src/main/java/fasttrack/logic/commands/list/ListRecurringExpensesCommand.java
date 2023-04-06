package fasttrack.logic.commands.list;

import static fasttrack.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static java.util.Objects.requireNonNull;

import fasttrack.logic.commands.CommandResult;
import fasttrack.model.Model;
import fasttrack.ui.ScreenType;

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
