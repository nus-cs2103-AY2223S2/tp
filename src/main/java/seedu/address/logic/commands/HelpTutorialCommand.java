package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Help instruction for Tutorial command
 */
public class HelpTutorialCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help tutorial";

    public static final String HEADER = "Tutorial Input Format:\n";

    public static final String ADD_TUTORIAL = "Add: touch Tutorial/TUTORIAL_NAME";

    public static final String DELETE_TUTORIAL = "Delete: delete Tutorial/INDEX";

    public static final String EDIT_TUTORIAL = "Edit: coming soon~";

    public static final String ADD_STUDENT = "Add Student: addStudent INDEX Tutorial/TUTORIAL_NAME";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER
                + ADD_TUTORIAL + "\n"
                + DELETE_TUTORIAL + "\n"
                + EDIT_TUTORIAL + "\n"
                + ADD_STUDENT);
    }
}
