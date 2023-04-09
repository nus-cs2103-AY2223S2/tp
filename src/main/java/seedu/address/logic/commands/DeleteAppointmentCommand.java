package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.appointment.Appointment;
import seedu.address.model.client.appointment.AppointmentName;
import seedu.address.model.client.appointment.MeetupDate;

/**
 * Deletes an appointment to a specific client in the program.
 */
public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "deleteApt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an appointment to the client identified "
            + "by the index number used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 ";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment";
    private final Index index;

    /**
     * Creates a DeleteAppointmentCommand to delete the specified {@code Appointment}.
     *
     * @param index The index of the person to add the appointment to.
     */
    public DeleteAppointmentCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        assert index.getOneBased() > 0;
        Client clientToDeleteAppointment = lastShownList.get(index.getZeroBased());
        Client editedClient = new Client(clientToDeleteAppointment.getName(), clientToDeleteAppointment.getPhone(),
                clientToDeleteAppointment.getEmail(), clientToDeleteAppointment.getAddress(),
                clientToDeleteAppointment.getPolicyList(),
                new Appointment(new AppointmentName(), new MeetupDate()));
        Client deleteAppointmentClient = editedClient.cloneClient();
        model.setClient(clientToDeleteAppointment, editedClient);
        model.updateFilteredClientList(Model.PREDICATE_SHOW_ALL_CLIENTS);

        return new CommandResult(MESSAGE_DELETE_APPOINTMENT_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteAppointmentCommand)) {
            return false;
        }

        DeleteAppointmentCommand otherAppointment = (DeleteAppointmentCommand) other;
        return index.equals(otherAppointment.index);
    }
}
