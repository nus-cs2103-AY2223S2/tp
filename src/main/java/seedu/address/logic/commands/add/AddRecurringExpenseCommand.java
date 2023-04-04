package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESPAN;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.expense.RecurringExpenseManager;
import seedu.address.ui.ScreenType;

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
