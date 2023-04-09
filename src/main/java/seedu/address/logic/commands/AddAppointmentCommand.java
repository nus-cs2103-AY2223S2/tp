package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.appointment.Appointment;

/**
 * Adds an Appointment to a speciic client in the program.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "addApt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an appointment to the client identified "
            + "by the index number used in the displayed client list. "
            + "Existing appointment will be overwritten.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_APPOINTMENT_NAME + "APPOINTMENT_NAME "
            + PREFIX_APPOINTMENT_DATE + "MEETUP_DATE " + "\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_APPOINTMENT_NAME + "Travel insurance discussion "
            + PREFIX_APPOINTMENT_DATE + "01.01.2026 ";

    private final Index index;
    private final Appointment appointment;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Appointment}
     *
     * @param index       The index of the person to add the policy to.
     * @param appointment The appointment to be added.
     */
    public AddAppointmentCommand(Index index, Appointment appointment) {
        requireAllNonNull(index, appointment);
        this.index = index;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }
        Client clientToAddAppointment = lastShownList.get(index.getZeroBased());

        Client editedClient = new Client(clientToAddAppointment.getName(), clientToAddAppointment.getPhone(),
                clientToAddAppointment.getEmail(), clientToAddAppointment.getAddress(),
                clientToAddAppointment.getPolicyList(),
                appointment);
        Client addedAppointmentClient = editedClient.cloneClient();
        model.setClient(clientToAddAppointment, editedClient);
        model.updateFilteredClientList(Model.PREDICATE_SHOW_ALL_CLIENTS);

        return new CommandResult(generateSuccessMessage(editedClient));
    }

    /**
     * Generates a command execution success message based on the appointment added.
     *
     * @return success message
     */
    private String generateSuccessMessage(Client client) {
        return String.format("Added Appointment: %1$s to Client: %2$s", appointment.getAppointmentName(),
                client);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddAppointmentCommand)) {
            return false;
        }

        AddAppointmentCommand otherAppointment = (AddAppointmentCommand) other;
        return index.equals(otherAppointment.index)
                && appointment.equals(otherAppointment.appointment);
    }
}
