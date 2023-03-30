package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;
import seedu.address.ui.ScreenType;

/**
 * Edits an Expense in the expense tracker.
 */
public class EditExpenseCommand extends Command {
    public static final String COMMAND_WORD = "edexp";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the expense identified by the index number used in the displayed expenses list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_NAME + "EXPENSE NAME] "
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_PRICE + "AMOUNT] "
            + "[" + PREFIX_DATE + "DATE] \n"
            + "Example: " + COMMAND_WORD + " 1 n/KFC c/food p/10 d/20/03/23 ";

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
        if (targetIndex == null) {
            throw new CommandException(MESSAGE_USAGE);
        }
        if (targetIndex.getZeroBased() >= lastShownListOfExpenses.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
        }

        Expense expenseToEdit = lastShownListOfExpenses.get(targetIndex.getZeroBased());
        String name = expenseToEdit.getName();
        double amount = expenseToEdit.getAmount();
        Category category = expenseToEdit.getCategory();
        LocalDate date = expenseToEdit.getDate();

        if (toBeAllocated != null) {
            category = toBeAllocated;
        } else if (this.newExpenseCategoryInString != null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_CATEGORY);
        }

        if (newExpenseName != null) {
            name = newExpenseName;
        }

        if (newExpenseAmount != null) {
            amount = newExpenseAmount;
        }

        if (newExpenseDate != null) {
            date = newExpenseDate;
        }

        Expense newExpense = new Expense(name, amount, date, category);
        model.setExpense(targetIndex.getZeroBased(), newExpense);
        return new CommandResult(
            String.format(Messages.MESSAGE_SUCCESSFULLY_EDITED_EXPENSE, newExpense),
            ScreenType.EXPENSE_SCREEN);
    }
}
