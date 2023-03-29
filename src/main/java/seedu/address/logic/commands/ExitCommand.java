package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    private final boolean isModifying = false;
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public boolean checkModifiable() {
        return isModifying;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, null);
    }

}
