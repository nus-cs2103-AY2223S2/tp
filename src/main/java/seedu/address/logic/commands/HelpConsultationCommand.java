package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Help instruction for Consultation command
 */
public class HelpConsultationCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help consultation";

    public static final String HEADER_1 = "----- Consultation Basic Inputs -----\n";

    public static final String HEADER_2 = "\n----- Student Inputs -----\n";

    public static final String HEADER_3 = "\n----- Note Inputs -----\n";

    public static final String ADD_CONSULTATION = "Add:                         "
            + "mkdir Consultation/CONSULTATION_NAME";

    public static final String ADD_RECUR = "Add Multiple:            "
            + "schedule Recur/Consultation/CONSULTATION_NAME -n REPETITIONS";

    public static final String DELETE_CONSULTATION = "Delete:                     "
            + "delete Consultation/INDEX";

    public static final String EDIT_CONSULTATION = "Edit:                         "
            + "editEvent EVENT_INDEX Consultation/NEW_NAME -date NEW_DATE -file NEW_FILEPATH";

    public static final String ADD_STUDENT = "Add Student:            "
            + "addStudent STUDENT_INDEX_IN_LIST Consultation/CONSULTATION_INDEX_IN_CONSULTATION_LIST";

    public static final String DELETE_STUDENT = "Delete Student:        "
            + "deleteStudent STUDENT_INDEX_IN_LIST Consultation/CONSULTATION_INDEX_IN_CONSULTATION_LIST";

    public static final String ADD_NOTE = "Add Note:         "
            + "add-note -content NOTE ?????";

    public static final String EDIT_NOTE = "Edit Note:          "
            + "edit-note -content NOTE ?????";

    public static final String DELETE_NOTE = "Delete Note:      "
            + "rm-note -type Consultation -name STUDENT_NAME_OR_INDEX -index NOTE_INDEX";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER_1
                + ADD_CONSULTATION + "\n"
                + ADD_RECUR + "\n"
                + DELETE_CONSULTATION + "\n"
                + EDIT_CONSULTATION + "\n"
                + HEADER_2
                + ADD_STUDENT + "\n"
                + DELETE_STUDENT + "\n"
                + HEADER_3
                + ADD_NOTE + "\n"
                + DELETE_NOTE + "\n"
                + EDIT_NOTE);
    }
}
