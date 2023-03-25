package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_WARD;

import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.ward.Ward;

/**
 * Adds a person to the patientist book.
 */
public class AddPatientCommand extends Command {

    public static final String COMMAND_WORD = "addpat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to the patientist book. "
                                               + "Parameters: "
                                               + PREFIX_NAME + "NAME "
                                               + PREFIX_ID + "ID "
                                               + PREFIX_WARD + "WARD "
                                               + PREFIX_PHONE + "PHONE "
                                               + PREFIX_EMAIL + "EMAIL "
                                               + PREFIX_ADDRESS + "ADDRESS "
                                               + "[" + PREFIX_TAG + "TAG]...\n"
                                               + "Example: " + COMMAND_WORD + " "
                                               + PREFIX_NAME + "John Doe "
                                               + PREFIX_ID + "A12345B "
                                               + PREFIX_WARD + "Block B Ward 2 "
                                               + PREFIX_PHONE + "98765432 "
                                               + PREFIX_EMAIL + "johnd@example.com "
                                               + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
                                               + PREFIX_TAG + "friends "
                                               + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This patient already exists in the patientist book";
    public static final String MESSAGE_WARD_NOT_FOUND = "Ward not found: %1$s";

    private final String wardToAdd;
    private final Patient toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddPatientCommand(String ward, Patient patient) {
        requireNonNull(patient);
        wardToAdd = ward;
        toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (!model.hasWard(new Ward(wardToAdd))) {
            throw new CommandException(String.format(MESSAGE_WARD_NOT_FOUND, wardToAdd));
        }

        model.addPatient(toAdd, model.getWard(wardToAdd));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPatientCommand // instanceof handles nulls
                    && toAdd.equals(((AddPatientCommand) other).toAdd)
                    && wardToAdd.equals(((AddPatientCommand) other).wardToAdd));
    }
}
