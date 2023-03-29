package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Changes the style of NeoBook to light mode.
 */
public class LightModeCommand extends Command {
    public static final String COMMAND_WORD = "light";

    public static final String MESSAGE_SUCCESS = "Changed NeoBook to light mode.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }
}
