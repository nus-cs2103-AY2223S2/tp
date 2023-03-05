package seedu.address.logic.commands.student;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class StudentAddCommand extends StudentCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "student " + COMMAND_WORD + ": Adds a student to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_INDEXNUMBER + "INDEX NUMBER "
            + PREFIX_SEX + "SEX "
            + "["
            + PREFIX_PARENTNAME + "PARENT NAME "
            + PREFIX_AGESTUDENT + "AGE "
            + PREFIX_IMAGESTUDENT + "IMAGE STUDENT "
            + PREFIX_EMAILSTUDENT + "EMAIL STUDENT "
            + PREFIX_PHONESTUDENT + "PHONE STUDENT "
            + PREFIX_CCA + "CCA "
            + "]\n"
            + "Example: " + "student " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tan Ah Cow "
            + PREFIX_INDEXNUMBER + "25 "
            + PREFIX_SEX + "M "
            + PREFIX_PARENTNAME + "Tan Ah Choi "
            + PREFIX_AGESTUDENT + "14 "
            + PREFIX_IMAGESTUDENT + "C:// "
            + PREFIX_EMAILSTUDENT + "tanahcow@gmail.com "
            + PREFIX_PHONESTUDENT + "91234567 "
            + PREFIX_CCA + "Captain Ball";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the address book";

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
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
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
