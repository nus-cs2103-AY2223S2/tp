package seedu.address.logic.commands.student;

import seedu.address.logic.commands.Command;

/**
 * An abstract class for "student" command
 */
public abstract class StudentCommand extends Command {
    public static final String PERSON_WORD = "student";

    public static final String MESSAGE_USAGE = "1. student <CLASS_NAME> add\n"
                                              + "2. student <CLASS_NAME> comment";
    public static final String MESSAGE_SUCCESS = "New student added: <STUDENT_NAME>";
}
