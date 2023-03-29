package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Help instruction for Lab command
 */
public class HelpLabCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help lab";

    public static final String HEADER_1 = "Labs Basic Inputs:\n";

    public static final String HEADER_2 = "\nOther Inputs:\n";

    public static final String ADD_LAB = "Add: vim Lab/LAB_NAME";

    public static final String DELETE_LAB = "Delete: delete Lab/INDEX";

    public static final String EDIT_LAB = "Edit: editEvent EVENT_INDEX Lab/NEW_NAME -date NEW_DATE -file NEW_FILEPATH";

    public static final String ADD_STUDENT = "Add Student: addStudent INDEX Lab/LAB_NAME";

    public static final String DELETE_STUDENT = "Delete Student: deleteStudent STUDENT_INDEX_IN_LIST Lab/LAB_NAME";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER_1
                + ADD_LAB + "\n"
                + DELETE_LAB + "\n"
                + HEADER_2
                + EDIT_LAB + "\n"
                + ADD_STUDENT + "\n"
                + DELETE_STUDENT);
    }
}
