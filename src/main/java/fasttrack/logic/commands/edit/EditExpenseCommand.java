package fasttrack.logic.commands.edit;

import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_NAME;
import static fasttrack.logic.parser.CliSyntax.PREFIX_PRICE;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import fasttrack.commons.core.Messages;
import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.Price;
import fasttrack.ui.ScreenType;

/**
 * Edits an Expense in the expense tracker.
 */
public class EditExpenseCommand implements EditCommand {
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
            if (newExpenseName.stripTrailing().isEmpty()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_NAME);
            }
            name = newExpenseName;
        }

        if (newExpenseAmount != null) {
            if (!Price.isValidPrice(String.valueOf(newExpenseAmount))) {
                throw new CommandException(Price.MESSAGE_CONSTRAINTS);
            }
            amount = newExpenseAmount;
        }

        if (newExpenseDate != null) {
            date = newExpenseDate;
        }

        Expense newExpense = new Expense(name, amount, date, category);
        model.setExpense(expenseToEdit, newExpense);
        return new CommandResult(
            String.format(Messages.MESSAGE_SUCCESSFULLY_EDITED_EXPENSE, newExpense),
            ScreenType.EXPENSE_SCREEN);
    }
}
