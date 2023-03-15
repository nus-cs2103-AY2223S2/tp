package seedu.vms.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.vms.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.CommandResult;
import seedu.vms.model.Model;

/**
 * Lists all appointment in the appointment manager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
