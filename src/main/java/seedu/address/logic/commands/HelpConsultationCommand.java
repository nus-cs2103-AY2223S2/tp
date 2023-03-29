package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Help instruction for Consultation command
 */
public class HelpConsultationCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help consultation";

    public static final String HEADER = "Consultation Input Format:\n";

    public static final String ADD_CONSULTATION = "Add: mkdir Consultation/CONSULTATION_NAME";

    public static final String DELETE_CONSULTATION = "Delete: delete Consultation/INDEX";

    public static final String EDIT_CONSULTATION = "Edit: coming soon~";

    public static final String ADD_STUDENT = "Add Student: addStudent INDEX Consultation/CONSULTATION_NAME";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER
                + ADD_CONSULTATION + "\n"
                + DELETE_CONSULTATION + "\n"
                + EDIT_CONSULTATION + "\n"
                + ADD_STUDENT);
    }
}
