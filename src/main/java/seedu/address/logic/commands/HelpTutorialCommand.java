package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Help instruction for Tutorial command
 */
public class HelpTutorialCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help tutorial";

    public static final String HEADER_1 = "Tutorial Basic Inputs:\n";

    public static final String HEADER_2 = "\nOther Inputs:\n";

    public static final String ADD_TUTORIAL = "Add: touch Tutorial/TUTORIAL_NAME";

    public static final String DELETE_TUTORIAL = "Delete: delete Tutorial/INDEX";

    public static final String EDIT_TUTORIAL = "Edit: editEvent EVENT_INDEX Tutorial/NEW_NAME -date NEW_DATE -file NEW_FILEPATH";

    public static final String ADD_STUDENT = "Add Student: addStudent INDEX Tutorial/TUTORIAL_NAME";

    public static final String DELETE_STUDENT = "Delete Student: deleteStudent STUDENT_INDEX_IN_LIST Tutorial/TUTORIAL_NAME";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER_1
                + ADD_TUTORIAL + "\n"
                + DELETE_TUTORIAL + "\n"
                + HEADER_2
                + EDIT_TUTORIAL + "\n"
                + ADD_STUDENT + "\n"
                + DELETE_STUDENT);
    }
}
