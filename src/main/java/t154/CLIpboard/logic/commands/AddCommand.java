package t154.CLIpboard.logic.commands;

import static java.util.Objects.requireNonNull;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_EMAIL;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_MODULE;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_NAME;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_PHONE;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static t154.CLIpboard.logic.parser.CliSyntax.PREFIX_TAG;

import t154.CLIpboard.logic.commands.exceptions.CommandException;
import t154.CLIpboard.model.Model;
import t154.CLIpboard.model.student.Student;

/**
 * Adds a student to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_STUDENTID + "STUDENTID "
            + PREFIX_MODULE + "MODULECODE... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_STUDENTID + "A1234567X "
            + PREFIX_MODULE + "CS2103T ";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the address book";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
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
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
