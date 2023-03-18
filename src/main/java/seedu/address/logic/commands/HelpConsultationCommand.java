package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Help instruction for Consultation command
 */
public class HelpConsultationCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help consultation";

    public static final String CONSULTATION_SYNTAX = "Consultation Input Format:\n"
            + "Syntax to be decided, do stay tune";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(CONSULTATION_SYNTAX);
    }
}
