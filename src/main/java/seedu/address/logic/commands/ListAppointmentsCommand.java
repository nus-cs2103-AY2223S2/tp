package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.address.model.Model;

/**
 * Lists all appointments in the shop to the user.
 */
public class ListAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "listappointments";

    public static final String MESSAGE_SUCCESS = "Currently listing all appointments.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        model.updateAppointmentComparator((a, b) -> a.getDateStatus().compareTo(b.getDateStatus()));
        model.selectAppointment(lst -> lst.isEmpty() ? null : lst.get(0));
        return new CommandResult(MESSAGE_SUCCESS, Tab.APPOINTMENTS);
    }
}










