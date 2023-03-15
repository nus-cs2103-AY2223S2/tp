package taa.logic.commands;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_CLASS_TAG;
import static taa.logic.parser.CliSyntax.PREFIX_NAME;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.student.Student;

/**
 * Adds a student to the address book.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "add_student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_CLASS_TAG + "CLASS_NAME]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_CLASS_TAG + "Tutorial_T01 "
            + PREFIX_CLASS_TAG + "Lab_L01";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the class list";

    private final Student toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddStudentCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.addStudent(toAdd);
        model.addStudentToTaggedClasses(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStudentCommand // instanceof handles nulls
                && toAdd.equals(((AddStudentCommand) other).toAdd));
    }
}
