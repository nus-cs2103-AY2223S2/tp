package seedu.connectus.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.connectus.model.ConnectUs;
import seedu.connectus.model.Model;

/**
 * Clears ConnectUS.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all entries from the ConnectUS app.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "ConnectUS has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setConnectUs(new ConnectUs());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
