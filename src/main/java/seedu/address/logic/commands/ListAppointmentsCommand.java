package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.address.model.Model;
import seedu.address.ui.CalendarCard;

/**
 * Lists all appointments to the user.
 */
public class ListAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "list_appt";
    public static final String COMMAND_ALIAS = "la";
    public static final String MESSAGE_SUCCESS = "Listed all appointments";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        if (CalendarCard.getIsInstantiated()) {
            CalendarCard.addAppointmentsToCalendar(model.getFilteredAppointmentList());
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
