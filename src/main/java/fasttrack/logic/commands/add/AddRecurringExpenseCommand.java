package fasttrack.logic.commands.add;

import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_END_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_NAME;
import static fasttrack.logic.parser.CliSyntax.PREFIX_PRICE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_START_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_TIMESPAN;
import static java.util.Objects.requireNonNull;

import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.category.Category;
import fasttrack.model.expense.RecurringExpenseManager;
import fasttrack.ui.ScreenType;

/**
 * Adds a category to the Expense Tracker.
 */
public class AddRecurringExpenseCommand implements AddCommand {

    public static final String COMMAND_WORD = "addrec";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recurring expense to FastTrack. "
            + "Parameters: "
            + PREFIX_NAME + "RECURRING_EXPENSE_NAME"
            + PREFIX_CATEGORY + "CATEGORY_NAME "
            + PREFIX_PRICE + "AMOUNT "
            + PREFIX_TIMESPAN + "TIMESPAN "
            + PREFIX_START_DATE + "START_DATE "
            + "[" + PREFIX_END_DATE + "END_DATE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Netflix "
            + PREFIX_CATEGORY + "Subscription "
            + PREFIX_PRICE + "10 "
            + PREFIX_TIMESPAN + "week "
            + PREFIX_START_DATE + "01/03/23 "
            + PREFIX_END_DATE + "01/03/24\n";
    public static final String MESSAGE_SUCCESS = "New recurring expense added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECURRING_EXPENSE =
            "This recurring expense already exists in FastTrack";

    private final RecurringExpenseManager toAdd;

    /**
     * Creates an AddCategoryCommand to add the specified {@code Category}
     */
    public AddRecurringExpenseCommand(RecurringExpenseManager toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model dataModel) throws CommandException {
        requireNonNull(dataModel);
        if (dataModel.hasRecurringExpense(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECURRING_EXPENSE);
        }

        Category newCategory = toAdd.getExpenseCategory();
        Category existingCategory = dataModel.getCategoryInstance(newCategory);
        if (existingCategory != null) {
            toAdd.setExpenseCategory(existingCategory);
        } else {
            dataModel.addCategory(newCategory);
        }

        dataModel.addRecurringGenerator(toAdd);
        dataModel.addRetroactiveExpenses();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ScreenType.RECURRING_EXPENSE_SCREEN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRecurringExpenseCommand // instanceof handles nulls
                && toAdd.equals(((AddRecurringExpenseCommand) other).toAdd));
    }
}
