package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.UiSwitchMode;

/**
 * Edits the details of an existing person in the address book.
 */
public class BackCommand extends Command {

    public static final String COMMAND_WORD = "back";

    public static final String MESSAGE_SUCCESS = "Exited Edit Mode";

    public BackCommand() {
    }

    @Override
    public CommandResult execute(seedu.address.experimental.model.Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, UiSwitchMode.LIST);
    }
}

