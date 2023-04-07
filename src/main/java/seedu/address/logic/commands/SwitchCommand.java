package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Switches between different tabs under student's score panel
 */
public class SwitchCommand extends Command {
    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Switches between score text tab and score chart tab.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SWITCH_SUCCESS = "Score list tab switched.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SWITCH_SUCCESS, true);
    }
}
