package seedu.address.logic.commands.student;

import seedu.address.logic.commands.Command;

/**
 * An abstract class for "student" command
 */
public abstract class StudentCommand extends Command {
    public static final String PERSON_WORD = "student";

    public static final String MESSAGE_USAGE = "1. student <CLASS_NAME> add\n"
                                              + "2. student <CLASS_NAME> comment\n"
                                              + "3. student <CLASS_NAME> grade\n"
                                              + "4. student <CLASS_NAME> delete\n"
                                              + "5. student <CLASS_NAME> gradedelete\n"
                                              + "6. student <CLASS_NAME> edit\n"
                                              + "7. student <CLASS_NAME> attendance\n"
                                              + "8. student <CLASS_NAME> list\n"
                                              + "9. student <CLASS_NAME> find\n";


    public static final String MESSAGE_SUCCESS = "New student added: <STUDENT_NAME>";
}
