package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.address.model.Budget;
import seedu.address.model.Model;
import seedu.address.ui.ScreenType;

/**
 * Sets the monthly budget for FastTrack.
 */
public class SetBudgetCommand extends Command {


    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets monthly budget.\n"
            + "Parameters: "
            + PREFIX_PRICE + "BUDGET_AMOUNT "
            + "Example: " + COMMAND_WORD + " " + PREFIX_PRICE + " 1000";

    public static final String MESSAGE_SUCCESS = "Monthly budget successfully set to ";
    private final Budget budget;

    public SetBudgetCommand(Budget budget) {
        this.budget = budget;
    }

    @Override
    public CommandResult execute(Model dataModel) {
        dataModel.setBudget(budget);
        return new CommandResult(MESSAGE_SUCCESS + this.budget, ScreenType.EXPENSE_SCREEN);
    }
}
