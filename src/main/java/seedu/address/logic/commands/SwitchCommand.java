package seedu.address.logic.commands;

import java.util.logging.Logger;

import seedu.address.AppParameters;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;

/**
 * Switches between different panels under student task list.
 */
public class SwitchCommand extends Command {
    public static final String MESSAGE_SWITCH_SUCCESS = "Score list tab switched.";
    public static final String COMMAND_WORD = "switch";
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Switches between score text tab and score chart tab.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the switch command result for display.
     */
    @Override
    public CommandResult execute(Model model) {
        logger.info("Tab switch success.");

        return new CommandResult(MESSAGE_SWITCH_SUCCESS, true);
    }
}
