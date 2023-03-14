package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds an expense to FastTrack.
 */
public class AddExpenseCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an expense to FastTrack "
            + "Parameters: "
            + PREFIX_NAME + "EXPENSE NAME "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_PRICE + "AMOUNT "
            + PREFIX_DATE + "[DATE]"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Milk"
            + PREFIX_CATEGORY + "groceries"
            + PREFIX_PRICE + "4.50"
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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Category category = newExpense.getCategory();
        //TODO find out how to deal with existing categories
        model.addExpense(newExpense);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newExpense));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddExpenseCommand // instanceof handles nulls
                && newExpense.equals(((AddExpenseCommand) other).newExpense));
    }
}
