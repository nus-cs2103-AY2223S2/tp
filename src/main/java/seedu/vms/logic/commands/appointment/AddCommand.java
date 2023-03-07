package seedu.vms.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.vms.logic.parser.CliSyntax.DELIMITER;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.CommandResult;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;
import seedu.vms.model.appointment.Appointment;

/**
 * Adds a patient to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the address book. "
            + "Parameters: "
            + DELIMITER + PREFIX_TIME + " TIME "
            + DELIMITER + PREFIX_PATIENT + " PATIENT ID \n"
            + "Example: appointment " + COMMAND_WORD + " "
            + DELIMITER + PREFIX_TIME + " 2024-01-01 "
            + DELIMITER + PREFIX_PATIENT + " A01234567X ";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists"
            + " in the appointment manager";

    private final Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Patient}
     */
    public AddCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
