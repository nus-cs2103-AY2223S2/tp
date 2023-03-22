package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Displays the different Student helps available
 */
public class HelpStudentCommand extends HelpCommand {

    public static final String COMMAND_WORD = "student";

    public static final String STUDENT_CATEGORIES = "blabla";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(STUDENT_CATEGORIES);
    }
}
