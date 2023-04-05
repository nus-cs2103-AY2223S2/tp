package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.enums.LightDarkMode;
import seedu.address.model.Model;
import seedu.address.ui.result.ResultDisplay;

/**
 * Changes the style of NeoBook to light mode.
 */
public class LightModeCommand extends Command {
    public static final String COMMAND_WORD = "light";

    public static final String MESSAGE_SUCCESS = "Changed NeoBook to light mode.";
    public static final String MESSAGE_ALREADY_IN_MODE = "NeoBook is already in light mode.";
    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD, "Changes NeoBook to light mode.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE, COMMAND_WORD);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        return new CommandResult(MESSAGE_SUCCESS, false, false, LightDarkMode.LIGHT);
    }
}
