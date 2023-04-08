package fasttrack.logic.commands.general;

import fasttrack.logic.commands.CommandResult;
import fasttrack.model.Model;
import fasttrack.ui.ScreenType;

/**
 * Format full help instructions for every command for display.
 */
public class ClearCommand implements GeneralCommand {

    public static final String COMMAND_WORD = "CLEAR";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes all entries in the FastTrack database.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Deleted all prior entries.";
    @Override
    public CommandResult execute(Model dataModel) {
        dataModel.clearCategory();
        dataModel.clearExpense();
        dataModel.clearRecurringExpenseGenerator();
        return new CommandResult(MESSAGE_SUCCESS, ScreenType.EXPENSE_SCREEN);
    }
}
