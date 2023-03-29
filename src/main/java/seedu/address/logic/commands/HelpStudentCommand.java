package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Displays the different Student helps available
 */
public class HelpStudentCommand extends HelpCommand {

    public static final String COMMAND_WORD = "student";

    public static final String HEADER_1 = "----- Student Control Commands -----\n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER_1
                + AddCommand.MESSAGE_USAGE + "\n"
                + DeleteCommand.MESSAGE_USAGE + "\n"
                + EditCommand.MESSAGE_USAGE + "\n"
                + FindCommand.MESSAGE_USAGE + "\n"
                + PerformanceCommand.MESSAGE_USAGE + "\n"
                + PhotoCommand.MESSAGE_USAGE + "\n"
                + RemarkCommand.MESSAGE_USAGE + "\n"
                + "List Syntax: list" + "\n"
                + "Clear Syntax: clear");
    }
}
