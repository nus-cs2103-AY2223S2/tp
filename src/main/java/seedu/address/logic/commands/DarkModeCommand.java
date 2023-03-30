package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.ui.enums.LightDarkMode;
import seedu.address.ui.result.ResultDisplay;

/**
 * Changes the style of NeoBook to dark mode.
 */
public class DarkModeCommand extends Command {
    public static final String COMMAND_WORD = "dark";

    public static final String MESSAGE_SUCCESS = "Changed NeoBook to dark mode.";
    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD, "Changes NeoBook to dark mode.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE, COMMAND_WORD);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        return new CommandResult(MESSAGE_SUCCESS, false, false, LightDarkMode.DARK);
    }
}
