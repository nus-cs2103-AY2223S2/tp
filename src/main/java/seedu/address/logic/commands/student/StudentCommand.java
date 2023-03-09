package seedu.address.logic.commands.student;

import seedu.address.logic.commands.Command;

/**
 * An abstract class for "student" command
 */
public abstract class StudentCommand extends Command {
    public static final String COMMAND_WORD = "student";

    public static final String MESSAGE_USAGE = "1. student <CLASS_NAME> add";
}
