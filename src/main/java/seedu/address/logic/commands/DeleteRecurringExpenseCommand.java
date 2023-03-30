package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expense.RecurringExpenseManager;
import seedu.address.ui.ScreenType;

/**
 * Deletes an expense from the expense tracker.
 */
public class DeleteRecurringExpenseCommand extends Command {

    public static final String COMMAND_WORD = "delrec";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the recurring expense identified by the index number used in the displayed "
            + "recurring expenses list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_RECURRING_EXPENSE_SUCCESS = "Deleted recurring expense: %1$s";
    private final Index targetIndex;
    /**
     * Creates an DeleteExpenseCommand to delete the specified {@code Expense}
     * @param targetIndex index of the expense in the filtered expense list to delete
     */
    public DeleteRecurringExpenseCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model dataModel) throws CommandException {
        requireNonNull(dataModel);
        List<RecurringExpenseManager> lastShownList = dataModel.getRecurringExpenseGenerators();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECURRING_EXPENSE_DISPLAYED_INDEX);
        }

        RecurringExpenseManager recurringExpenseManager = lastShownList.get(targetIndex.getZeroBased());
        dataModel.deleteRecurringExpense(recurringExpenseManager);
        return new CommandResult(String.format(MESSAGE_DELETE_RECURRING_EXPENSE_SUCCESS, recurringExpenseManager),
                ScreenType.RECURRING_EXPENSE_SCREEN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRecurringExpenseCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteRecurringExpenseCommand) other).targetIndex)); // state check
    }
}
