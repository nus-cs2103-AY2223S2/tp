package fasttrack.logic.commands.add;

import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_NAME;
import static fasttrack.logic.parser.CliSyntax.PREFIX_PRICE;
import static java.util.Objects.requireNonNull;

import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.ui.ScreenType;


/**
 * Adds an expense to the Expense Tracker.
 */
public class AddExpenseCommand implements AddCommand {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an expense to FastTrack "
            + "Parameters: "
            + PREFIX_NAME + "EXPENSE NAME "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_PRICE + "AMOUNT "
            + "[" + PREFIX_DATE + "DATE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Milk "
            + PREFIX_CATEGORY + "groceries "
            + PREFIX_PRICE + "4.50 "
            + PREFIX_DATE + "2/10/23";

    public static final String MESSAGE_SUCCESS = "New expense added: %1$s";

    private final Expense newExpense;

    /**
     * Creates an AddExpenseCommand to add the specified {@code Expense}
     */
    public AddExpenseCommand(Expense expense) {
        requireNonNull(expense);
        newExpense = expense;
    }

    @Override
    public CommandResult execute(Model dataModel) throws CommandException {
        requireNonNull(dataModel);
        Category newCategory = newExpense.getCategory();
        Category existingCategory = dataModel.getCategoryInstance(newCategory);
        if (existingCategory != null) {
            newExpense.setCategory(existingCategory);
        } else {
            dataModel.addCategory(newCategory);
        }
        dataModel.addExpense(newExpense);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newExpense), ScreenType.EXPENSE_SCREEN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddExpenseCommand // instanceof handles nulls
                && newExpense.equals(((AddExpenseCommand) other).newExpense));
    }
}
