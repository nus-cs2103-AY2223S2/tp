package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;

/**
 * JavaDoc
 */
public class EditExpenseCommand extends Command {
    public static final String COMMAND_WORD = "eexp";

    private final Index targetIndex;
    private final String newExpenseName;
    private final Double newExpenseAmount;
    private final LocalDate newExpenseDate;
    private final String newExpenseCategoryInString;

    /**
     * JavaDoc
     * @param targetIndex xx
     * @param newExpenseName xx
     * @param newExpenseAmount xx
     * @param newExpenseDate xx
     * @param newExpenseCategory xx
     */
    public EditExpenseCommand(Index targetIndex, String newExpenseName, Double newExpenseAmount,
                              LocalDate newExpenseDate, String newExpenseCategory) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.newExpenseName = newExpenseName;
        this.newExpenseAmount = newExpenseAmount;
        this.newExpenseDate = newExpenseDate;
        this.newExpenseCategoryInString = newExpenseCategory;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Expense> lastShownListOfExpenses = model.getFilteredExpenseList();
        List<Category> lastShownListOfCategories = model.getFilteredCategoryList();
        Category toBeAllocated = null;

        for (Category category : lastShownListOfCategories) {
            if (category.getCategoryName().equals(this.newExpenseCategoryInString)) {
                toBeAllocated = category;
                break;
            }
        }

        if (newExpenseName == null && newExpenseAmount == null
                && newExpenseDate == null && newExpenseCategoryInString == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EDIT_FOR_EXPENSE);
        }

        //Check if index is valid
        if (targetIndex == null || targetIndex.getZeroBased() >= lastShownListOfExpenses.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
        }

        Expense expenseToEdit = lastShownListOfExpenses.get(targetIndex.getZeroBased());

        if (toBeAllocated != null) {
            expenseToEdit.setCategory(toBeAllocated);
        } else if (this.newExpenseCategoryInString != null && toBeAllocated == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_CATEGORY);
        }

        if (newExpenseName != null) {
            expenseToEdit.setName(newExpenseName);
        }

        if (newExpenseAmount != null) {
            expenseToEdit.setAmount(newExpenseAmount);
        }

        if (newExpenseDate != null) {
            expenseToEdit.setDate(newExpenseDate);
        }
        return new CommandResult(String.format(Messages.MESSAGE_SUCCESSFULLY_EDITED_EXPENSE, expenseToEdit));
    }
}
