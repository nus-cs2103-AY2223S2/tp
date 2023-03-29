package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Help instruction for Consultation command
 */
public class HelpConsultationCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help consultation";

    public static final String HEADER_1 = "Consultation Basic Inputs:\n";

    public static final String HEADER_2 = "\nOther Inputs:\n";

    public static final String ADD_CONSULTATION = "Add: mkdir Consultation/CONSULTATION_NAME";

    public static final String DELETE_CONSULTATION = "Delete: delete Consultation/INDEX";

    public static final String EDIT_CONSULTATION = "Edit: editEvent EVENT_INDEX Consultation/NEW_NAME -date NEW_DATE -file NEW_FILEPATH";

    public static final String ADD_STUDENT = "Add Student: addStudent INDEX Consultation/CONSULTATION_NAME";

    public static final String DELETE_STUDENT = "Delete Student: deleteStudent STUDENT_INDEX_IN_LIST Consultation/CONSULTATION_NAME";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER_1
                + ADD_CONSULTATION + "\n"
                + DELETE_CONSULTATION + "\n"
                + HEADER_2
                + EDIT_CONSULTATION + "\n"
                + ADD_STUDENT + "\n"
                + DELETE_STUDENT);
    }
}
