package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Help instruction for Lab command
 */
public class HelpLabCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help lab";

    public static final String HEADER = "Labs Input Format:\n";

    public static final String ADD_LAB = "Add: vim Lab/LAB_NAME";

    public static final String DELETE_LAB = "Delete: delete Lab/INDEX";

    public static final String EDIT_LAB = "Edit: coming soon~";

    public static final String ADD_STUDENT = "Add Student: addStudent INDEX Lab/LAB_NAME";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER
                + ADD_LAB + "\n"
                + DELETE_LAB + "\n"
                + EDIT_LAB + "\n"
                + ADD_STUDENT);
    }
}
