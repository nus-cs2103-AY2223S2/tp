package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGESTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAILSTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGESTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONESTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.student.Student;


/**
 * A class for "student <Class Name> <Index number> grade" command"
 */
public class StudentGradeCommand extends StudentCommand {
    public static final String COMMAND_WORD = "grade";

    public static final String COMMAND_FORMAT = "student <CLASS_NAME> grade <INDEX> <GRADE>";

    public static final String MESSAGE_USAGE = "student CLASS_NAME " + COMMAND_WORD
            + ": Adds a grade to the student. "
            + "Parameters: "
            + "INDEX "
            + "GRADE "
            + "Example: " + "student 1A " + COMMAND_WORD + " "
            + "1 "
            + "A+";

    public static final String MESSAGE_SUCCESS = "New marks added:";

    private Student student;
    private String assignment;

    public StudentGradeCommand(Student student, String assignment) {
        this.student = student;
        this.assignment = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasStudent(student)) {
            throw new PersonNotFoundException();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
