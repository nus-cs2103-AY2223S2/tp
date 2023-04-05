package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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
            + ": Adds an appointment of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing appointment will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "APPOINTMENT (must be a valid appointment)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + "an/Travel insurance discussion "
            + "ad/01.01.2021 ";

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

        return new CommandResult(generateSuccessMessage());
    }

    /**
     * Generates a command execution success message based on the appointment added.
     *
     * @return success message
     */
    private String generateSuccessMessage() {
        return String.format("Added Appointment: %1$s to client: %2$s", appointment, index.getOneBased());
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
