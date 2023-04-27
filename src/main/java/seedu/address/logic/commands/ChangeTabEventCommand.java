package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Changes the TabPane to event tab.
 */
public class ChangeTabEventCommand extends Command {

    public static final String COMMAND_WORD = "cd_event";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Changing tabs to event ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, false, true);
    }

}
