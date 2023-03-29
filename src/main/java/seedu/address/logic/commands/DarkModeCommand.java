package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Changes the style of NeoBook to dark mode.
 */
public class DarkModeCommand extends Command {
    public static final String COMMAND_WORD = "dark";

    public static final String MESSAGE_SUCCESS = "Changed NeoBook to dark mode.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true);
    }
}
