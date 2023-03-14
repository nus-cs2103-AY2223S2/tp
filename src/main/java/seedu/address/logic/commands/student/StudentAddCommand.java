package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAILSTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGESTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.student.Student;


/**
 * A class for "student Class Name add" command"
 */
public class StudentAddCommand extends StudentCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "student CLASS_NAME " + COMMAND_WORD
            + ": Adds a student to the address book. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_INDEXNUMBER + "INDEX NUMBER "
            + PREFIX_SEX + "SEX "
            + PREFIX_PARENTNAME + "NOK NAME "
            + PREFIX_PHONEPARENT + "NOK CONTACT NUMBER "
            + PREFIX_RELATIONSHIP + "NOK RELATIONSHIP TO STUDENT "
            + "["
            + PREFIX_AGE + "AGE "
            + PREFIX_IMAGESTUDENT + "IMAGE STUDENT "
            + PREFIX_EMAILSTUDENT + "EMAIL STUDENT "
            + PREFIX_PHONE + "PHONE STUDENT "
            + PREFIX_CCA + "CCA "
            + "]\n"
            + "Example: " + "student 1A " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tan Ah Cow "
            + PREFIX_INDEXNUMBER + "13 "
            + PREFIX_SEX + "M "
            + PREFIX_PARENTNAME + "Tan Ah Niu "
            + PREFIX_PHONEPARENT + "91234567 "
            + PREFIX_RELATIONSHIP + "Father "
            + PREFIX_AGE + "14 "
            + PREFIX_IMAGESTUDENT + "C:// "
            + PREFIX_EMAILSTUDENT + "tanahcow@gmail.com "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_CCA + "Captain Ball";

    public static final String MESSAGE_SUCCESS = "New student added:";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public StudentAddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new DuplicatePersonException();
        }

        model.addStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentAddCommand // instanceof handles nulls
                && toAdd.equals(((StudentAddCommand) other).toAdd));
    }
}
