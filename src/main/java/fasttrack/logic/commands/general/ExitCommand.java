package fasttrack.logic.commands.general;

import fasttrack.logic.commands.CommandResult;
import fasttrack.model.Model;
import fasttrack.ui.ScreenType;

/**
 * Terminates the program.
 */
public class ExitCommand implements GeneralCommand {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Expense Tracker as requested ...";

    @Override
    public CommandResult execute(Model dataModel) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, ScreenType.EXPENSE_SCREEN);
    }

}
