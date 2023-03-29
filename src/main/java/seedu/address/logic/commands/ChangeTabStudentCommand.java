package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Changes the TabPane to student tab.
 */
public class ChangeTabStudentCommand extends Command {

    public static final String COMMAND_WORD = "cd_student";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Changing tabs to student ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, true, false);
    }

}
