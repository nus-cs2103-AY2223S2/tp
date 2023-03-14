package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Doctor;

/**
 * Adds a doctor to the address book.
 */
public class AddDoctorCommand extends AddCommand {

    public static final String COMMAND_WORD = "add-doc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_SPECIALTY + "SPECIALTY "
            + PREFIX_YOE + "YEARS OF EXPERIENCE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_SPECIALTY + "Cardiology "
            + PREFIX_YOE + "5 "
            + PREFIX_TAG + "surgeon";

    public static final String MESSAGE_SUCCESS = "New doctor added: %1$s";
    public static final String MESSAGE_DUPLICATE_DOCTOR = "This doctor already exists in the address book";

    private final Doctor toAdd;

    /**
     * Creates an AddDoctorCommand to add the specified {@code Person}
     */
    public AddDoctorCommand(Doctor doctor) {
        super(doctor);
        requireNonNull(doctor);
        toAdd = doctor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDoctor(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DOCTOR);
        }

        model.addDoctor(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDoctorCommand // instanceof handles nulls
                && toAdd.equals(((AddDoctorCommand) other).toAdd));
    }
}
