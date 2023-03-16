package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.experimental.model.Model;
import seedu.address.logic.parser.UiSwitchMode;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Toggled view mode";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, UiSwitchMode.TOGGLE);
    }
}
