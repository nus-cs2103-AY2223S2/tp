package seedu.address.logic.commands;

import seedu.address.experimental.model.Model;
import seedu.address.logic.parser.UiSwitchMode;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Reroll as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, UiSwitchMode.NONE);
    }

}
