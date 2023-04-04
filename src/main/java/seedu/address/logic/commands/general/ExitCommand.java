package seedu.address.logic.commands.general;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.ui.ScreenType;

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
