package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes the appointment identified by its id.
 */
public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "deleteappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the appointment identified by the id number displayed by the list command.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment %d";
    private final int id;

    public DeleteAppointmentCommand(int id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getShop().removeAppointment(id);
            model.resetSelected();
            return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, id),
                Tab.APPOINTMENTS);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteAppointmentCommand // instanceof handles nulls
            && id == ((DeleteAppointmentCommand) other).id); // state check
    }
}
