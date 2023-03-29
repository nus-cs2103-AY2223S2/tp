package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentHasOverlapPredicate;

/**
 * Finds and lists all appointments that are happening on the current day.
 * Keyword matching is case insensitive.
 */
public class TodayCommand extends Command {

    public static final String COMMAND_WORD = "today";

    public static final String MESSAGE_SUCCESS = "Listed all appointments for today";

    private final Predicate<Appointment> predicate = AppointmentHasOverlapPredicate.todayPredicate();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, model.getFilteredAppointmentList().size()));
    }
}
