package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SCHEDULED;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * List all patients having specified appointment
 */
public class FindAppointmentCommand extends MakeAppointmentCommand {

    public static final String COMMAND_WORD = "list_appointment";
    public static final String MESSAGE_SUCCESS = "Listed all persons with appointment time sorted";

    // notion: list_appointment
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFindAppointment(PREDICATE_SCHEDULED);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
