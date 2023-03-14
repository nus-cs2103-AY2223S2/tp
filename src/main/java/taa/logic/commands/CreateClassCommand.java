package taa.logic.commands;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_CLASS_TAG;

import taa.logic.commands.exceptions.CommandException;
import taa.model.ClassList;
import taa.model.Model;

/**
 * Adds a student to the address book.
 */
public class CreateClassCommand extends Command {

    public static final String COMMAND_WORD = "create_class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new class "
            + "Parameters: "
            + "[" + PREFIX_CLASS_TAG + "CLASS_NAME]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASS_TAG + "T01 ";

    public static final String MESSAGE_SUCCESS = "New class added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This class already exists";

    private final ClassList toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public CreateClassCommand(ClassList classList) {
        requireNonNull(classList);
        toAdd = classList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClassList(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.addClassList(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateClassCommand // instanceof handles nulls
                && toAdd.equals(((CreateClassCommand) other).toAdd));
    }
}
