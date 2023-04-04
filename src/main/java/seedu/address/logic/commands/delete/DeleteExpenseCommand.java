package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expense.Expense;
import seedu.address.ui.ScreenType;

/**
 * Deletes an expense from the expense tracker.
 */
public class DeleteExpenseCommand implements DeleteCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the expense identified by the index number used in the displayed expenses list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EXPENSE_SUCCESS = "Deleted expense: %1$s";
    private final Index targetIndex;
    /**
     * Creates an DeleteExpenseCommand to delete the specified {@code Expense}
     * @param targetIndex index of the expense in the filtered expense list to delete
     */
    public DeleteExpenseCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model dataModel) throws CommandException {
        requireNonNull(dataModel);
        List<Expense> lastShownList = dataModel.getFilteredExpenseList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Expense expense = lastShownList.get(targetIndex.getZeroBased());
        dataModel.deleteExpense(expense);
        return new CommandResult(String.format(MESSAGE_DELETE_EXPENSE_SUCCESS, expense), ScreenType.EXPENSE_SCREEN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteExpenseCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteExpenseCommand) other).targetIndex)); // state check
    }
}
