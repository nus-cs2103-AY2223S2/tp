package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.patient.Patient;

/**
 * Adds a person to the patientist book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the patientist book. "
                                               + "Parameters: "
                                               + PREFIX_NAME + "NAME "
                                               + PREFIX_ID + "PID "
                                               + PREFIX_PHONE + "PHONE "
                                               + PREFIX_EMAIL + "EMAIL "
                                               + PREFIX_ADDRESS + "ADDRESS "
                                               + "[" + PREFIX_TAG + "TAG]...\n"
                                               + "Example: " + COMMAND_WORD + " "
                                               + PREFIX_NAME + "John Doe "
                                               + PREFIX_ID + "A12345B"
                                               + PREFIX_PHONE + "98765432 "
                                               + PREFIX_EMAIL + "johnd@example.com "
                                               + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                                               + PREFIX_TAG + "friends "
                                               + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This patient already exists in the patientist book";

    private final Patient toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
