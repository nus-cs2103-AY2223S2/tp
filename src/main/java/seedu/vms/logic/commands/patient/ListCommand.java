package seedu.vms.logic.commands.patient;

import static java.util.Objects.requireNonNull;
import static seedu.vms.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.model.Model;

/**
 * Lists all patients in the patient manager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all patients";


    @Override
    public CommandMessage execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandMessage(MESSAGE_SUCCESS);
    }
}
