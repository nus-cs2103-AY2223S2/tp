package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.service.appointment.AppointmentIdPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewAppointmentCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "viewappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display customer details given the customer id (not"
            + "vehicle plate).\n"
            + "Parameters: ID\n"
            + "Example: " + COMMAND_WORD + " 8";

    private final AppointmentIdPredicate predicate;

    public ViewAppointmentCommand(AppointmentIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENT_VIEW_OVERVIEW));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewAppointmentCommand // instanceof handles nulls
                && predicate.equals(((ViewAppointmentCommand) other).predicate)); // state check
    }
}
