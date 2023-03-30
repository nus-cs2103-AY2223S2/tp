package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

/**
 * Lists all appointments in the AutoM8 system to the user.
 */
public class ListAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "listappointments";

    public static final String MESSAGE_SUCCESS = "Currently listing all appointments.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
//        model.selectAp(model.getFilteredCustomerList().get(0));
        return new CommandResult(MESSAGE_SUCCESS, Tab.APPOINTMENTS);
    }
}










